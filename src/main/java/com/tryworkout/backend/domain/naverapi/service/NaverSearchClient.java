package com.tryworkout.backend.domain.naverapi.service;

import com.tryworkout.backend.domain.naverapi.dto.SearchLocalReq;
import com.tryworkout.backend.domain.naverapi.dto.SearchLocalRes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class NaverSearchClient {

    @Value("${naver.client.search.id}")
    private String naverClientId;

    @Value("${naver.client.search.secret}")
    private String naverClientSecret;

    @Value("${naver.url.search.local}")
    private String naverLocalSearchUrl;

    public static<T> ResponseEntity<T> search(MultiValueMap<String, String> mv,
                                              ParameterizedTypeReference<T> responseType,
                                              String url, String id, String passwd){
        URI uri = UriComponentsBuilder.fromUriString(url)
                .queryParams(mv)
                .build()
                .encode()
                .toUri();

        HttpHeaders header = getHttpHeaders(id, passwd, MediaType.APPLICATION_JSON);

        HttpEntity<Object> httpEntity = new HttpEntity<>(header);

        ResponseEntity<T> responseEntity = new RestTemplate().exchange(
                uri,
                HttpMethod.GET,
                httpEntity,
                responseType
        );

        return responseEntity;
    }

    private static HttpHeaders getHttpHeaders(String id, String passwd, MediaType contentType){
        HttpHeaders header = new HttpHeaders();
        header.set("X-Naver-Client-Id", id);
        header.set("X-Naver-Client-Secret", passwd);
        header.setContentType(contentType);
        return header;
    }

    public SearchLocalRes searchLocal(SearchLocalReq searchLocalReq){
        MultiValueMap<String, String> mv = searchLocalReq.toMultiValueMap();
        ParameterizedTypeReference<SearchLocalRes> responseType = new ParameterizedTypeReference<>() {};
        ResponseEntity<SearchLocalRes> responseEntity = search(mv, responseType, naverLocalSearchUrl, naverClientId, naverClientSecret);

        return responseEntity.getBody();
    }
}