package com.ptfinder.ptfinderback.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ptfinder.ptfinderback.global.error.ErrorResponse;
import com.ptfinder.ptfinderback.global.error.exception.AuthenticationNotFoundException;
import com.ptfinder.ptfinderback.global.error.exception.BusinessException;
import com.ptfinder.ptfinderback.redis.RedisUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class JwtVerificationFilter extends OncePerRequestFilter {
    // 인증에서 제외할 url
    private static final List<String> EXCLUDE_URL =
            List.of("/",
                    "/h2",
                    "/auth/sign-up",
                    "/auth/login",
                    "/auth/login/callback",
                    "/auth/reissue",
                    "/trainer/create");
    private final JwtTokenProvider jwtTokenProvider;
    private final RedisUtils redisUtils;

    // JWT 인증 정보를 현재 쓰레드의 SecurityContext에 저장(가입/로그인/재발급 Request 제외)
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            String accessToken = jwtTokenProvider.resolveAccessToken(request);
            if (StringUtils.hasText(accessToken) && doNotLogout(accessToken)
                    && jwtTokenProvider.validateToken(accessToken, response)) {
                setAuthenticationToContext(accessToken);
            } else {
                throw new AuthenticationNotFoundException();
            }
        } catch (RuntimeException e) {
            if (e instanceof BusinessException) {
                ObjectMapper objectMapper = new ObjectMapper();
                String json = objectMapper.writeValueAsString(ErrorResponse.of(((BusinessException) e).getErrorCode()));
                response.getOutputStream().write(json.getBytes());
                response.setStatus(((BusinessException) e).getErrorCode().getStatus());
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    private boolean doNotLogout(String accessToken) {
        String isLogout = redisUtils.getData(accessToken);
        return isLogout.equals("false");
    }

    // EXCLUDE_URL과 동일한 요청이 들어왔을 경우, 현재 필터를 진행하지 않고 다음 필터 진행
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
//        log.info(request.getServletPath());
        boolean result = EXCLUDE_URL.stream().anyMatch(exclude -> exclude.equalsIgnoreCase(request.getServletPath()));

        return result;
    }

    private void setAuthenticationToContext(String accessToken) {
        Authentication authentication = jwtTokenProvider.getAuthentication(accessToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        log.info("# Token verification success!");
    }
}