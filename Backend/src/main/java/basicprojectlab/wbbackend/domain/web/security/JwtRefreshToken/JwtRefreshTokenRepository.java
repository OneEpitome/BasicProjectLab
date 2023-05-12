package basicprojectlab.wbbackend.domain.web.security.JwtRefreshToken;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JwtRefreshTokenRepository extends JpaRepository<JwtRefreshToken, Long> {
    Optional<JwtRefreshToken> findJwtRefreshTokenByToken(String token);
}
