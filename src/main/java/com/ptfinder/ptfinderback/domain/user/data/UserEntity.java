package com.ptfinder.ptfinderback.domain.user.data;

import com.ptfinder.ptfinderback.domain.user.AccountType;
import com.ptfinder.ptfinderback.domain.user.dto.UserDto;
import com.ptfinder.ptfinderback.domain.user.UserRole;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "USER")
@EntityListeners(AuditingEntityListener.class)
public class UserEntity {

    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "ROLE")
    @Enumerated(value = EnumType.STRING)
    private UserRole userRole;

    @Column(name = "ACCOUNT_TYPE")
    @Enumerated(value = EnumType.STRING)
    private AccountType accountType;

    @Column(name = "ACCESS_TOKEN", length = 2000)
    private String accessToken;

    @Column(name = "REFRESH_TOKEN", length = 2000)
    private String refreshToken;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Builder
    private UserEntity(String username, String email, String password, UserRole userRole, AccountType accountType) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.userRole = userRole;
        this.accountType = accountType;
    }

    public static UserEntity create(UserDto userDto){
        return UserEntity.builder()
                .username(userDto.getUsername())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .userRole(UserRole.valueOf(userDto.getUserRole()))
                .accountType(AccountType.valueOf(userDto.getAccountType()))
                .build();
    }

    public UserEntity setTokens(String accessToken, String refreshToken){
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        return this;
    }
}
