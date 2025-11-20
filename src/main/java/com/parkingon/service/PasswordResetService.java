package com.parkingon.service;

import com.parkingon.entity.PasswordResetToken;
import com.parkingon.entity.User;
import com.parkingon.repository.PasswordResetTokenRepository;
import com.parkingon.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PasswordResetService {

    private final PasswordResetTokenRepository tokenRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * 비밀번호 재설정 토큰 생성
     */
    @Transactional
    public PasswordResetToken createResetToken(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        // 기존 미사용 토큰이 있으면 무효화
        Optional<PasswordResetToken> existingTokenOpt = tokenRepository.findByUserAndUsedFalseAndExpiryDateAfter(user, LocalDateTime.now());
        if (existingTokenOpt.isPresent()) {
            PasswordResetToken existingToken = existingTokenOpt.get();
            existingToken.setUsed(true);
            tokenRepository.save(existingToken);
        }

        // 새 토큰 생성
        String token = UUID.randomUUID().toString();
        PasswordResetToken resetToken = PasswordResetToken.builder()
                .token(token)
                .user(user)
                .used(false)
                .expiryDate(LocalDateTime.now().plusHours(24))
                .build();

        return tokenRepository.save(resetToken);
    }

    /**
     * 토큰 유효성 검증
     */
    public boolean validateToken(String token) {
        Optional<PasswordResetToken> resetToken = tokenRepository.findByToken(token);

        if (resetToken.isEmpty()) {
            return false;
        }

        PasswordResetToken passwordResetToken = resetToken.get();
        return !passwordResetToken.getUsed() && !passwordResetToken.isExpired();
    }

    /**
     * 비밀번호 재설정
     */
    @Transactional
    public boolean resetPassword(String token, String newPassword) {
        Optional<PasswordResetToken> resetTokenOpt = tokenRepository.findByToken(token);

        if (resetTokenOpt.isEmpty()) {
            return false;
        }

        PasswordResetToken resetToken = resetTokenOpt.get();

        // 토큰 검증
        if (resetToken.getUsed() || resetToken.isExpired()) {
            return false;
        }

        // 비밀번호 변경
        User user = resetToken.getUser();
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        // 토큰 사용 처리
        resetToken.setUsed(true);
        tokenRepository.save(resetToken);

        return true;
    }

    /**
     * 만료된 토큰 삭제 (스케줄러에서 호출)
     */
    @Transactional
    public void deleteExpiredTokens() {
        tokenRepository.deleteByExpiryDateBefore(LocalDateTime.now());
    }

    /**
     * 토큰으로 사용자 정보 조회
     */
    public Optional<User> getUserByToken(String token) {
        return tokenRepository.findByToken(token)
                .map(PasswordResetToken::getUser);
    }
}
