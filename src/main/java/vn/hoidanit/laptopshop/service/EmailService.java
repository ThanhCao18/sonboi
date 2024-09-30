package vn.hoidanit.laptopshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendPasswordResetEmail(String toEmail, String resetUrl){
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(toEmail);
            message.setSubject("Đặt lại mật khẩu");
            message.setText("Click vào link sau để đặt lại mật khẩu" + resetUrl);
            mailSender.send(message);
        }
        catch (Exception e)
        {
           System.out.println(e.getMessage());
           System.out.println("lỗi đây");
        }
    }
}
