package me.jumptop.springbootdeveloper.repository;

import me.jumptop.springbootdeveloper.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> { // Generic 1번으미 : 이 Repository 다룰 엔티티 클래스, 2번의미 : PK의 타입
    Optional<RefreshToken> findByUserId(Long userId);
    Optional<RefreshToken> findByRefreshToken(String refreshToken);
}
