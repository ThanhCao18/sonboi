package vn.hoidanit.laptopshop.controller.client;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.hoidanit.laptopshop.domain.PasswordResetToken;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.repository.PasswordResetTokenRepository;
import vn.hoidanit.laptopshop.service.EmailService;
import vn.hoidanit.laptopshop.service.UserService;

import java.util.UUID;

@Controller
public class ResetPasswordController {

    private final UserService userService;
    private final EmailService emailService;

    public ResetPasswordController(UserService userService, EmailService emailService) {
        this.userService = userService;
        this.emailService = emailService;
    }

    @GetMapping("/forgotPassword")
    public String getPassword(Model model){
        return "client/auth/forgotPassword";
    }

    @PostMapping ("/reset-password")
    public String processForgotPassword(@RequestParam("email") String email, Model model){
        User user = userService.findUserByEmail(email);
        String message = "";
        if(user != null){
            String token = UUID.randomUUID().toString();
            userService.createPasswordResetToken(user, token);

            String resetUrl = "http://localhost:8080/resetpassword?token=" + token;
            emailService.sendPasswordResetEmail(user.getEmail(), resetUrl);
            message = "Link đặt lại đã được gửi đến email của bạn";

            model.addAttribute("message", message);
        }else {

            message = "Email người dùng không tồn tại";

            model.addAttribute("message", message);
        }
        return "client/auth/forgotPassword";
    }

    @PostMapping("/update-password")
    public String updatePassword(@RequestParam("token") String token, @RequestParam("password") String password, Model model){
        PasswordResetToken resetToken = this.userService.tokenRepository.findByToken(token);
        if (resetToken == null || resetToken.isExpired()){
            model.addAttribute("error", "Token không hợp lệ");
            return "client/auth/updatePassword";
        }

        User user = resetToken.getUser();
        userService.updatePassword(user,password);
        model.addAttribute("message", "Mật khẩu được đặt lại thành công");
        return "client/auth/updatePassword";
    }

    @GetMapping("/resetpassword")
    public String showResetPasswordForm(@RequestParam("token") String token, Model model) {
        System.out.println("Token nhận được: " + token);
        PasswordResetToken resetToken = this.userService.findByToken(token);

        if (resetToken == null) {
            model.addAttribute("error", "Token không hợp lệ hoặc đã hết hạn.");
            return "client/auth/forgotPassword";
        }

        model.addAttribute("token", token);
        return "client/auth/updatePassword";
    }

}
