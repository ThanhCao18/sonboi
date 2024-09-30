package vn.hoidanit.laptopshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.hoidanit.laptopshop.domain.PasswordResetToken;
import vn.hoidanit.laptopshop.domain.User;

@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
     PasswordResetToken findByToken(String token);
     PasswordResetToken findByUser(User user);
}
