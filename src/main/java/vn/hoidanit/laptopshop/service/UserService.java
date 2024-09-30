package vn.hoidanit.laptopshop.service;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import vn.hoidanit.laptopshop.domain.PasswordResetToken;
import vn.hoidanit.laptopshop.domain.Role;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.domain.dto.RegisterDTO;
import vn.hoidanit.laptopshop.repository.*;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    public final PasswordResetTokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository, ProductRepository productRepository, OrderRepository orderRepository, PasswordResetTokenRepository tokenRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.tokenRepository = tokenRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    public List<User> getAllUsersByEmail(String email) {
        return this.userRepository.findOneByEmail(email);
    }

    public User handleSaveUser(User user) {
        User eric = this.userRepository.save(user);
        System.out.println(eric);
        return eric;
    }

    public User getUserById(long id) {
        return this.userRepository.findById(id);
    }

    public Role getRoleByName(String name) {
        return this.roleRepository.findByName(name);
    }

    public void deleteUser(long id) {
        this.userRepository.deleteById(id);
    }

    public User registerDTOtoUser(RegisterDTO registerDTO) {
        User user = new User();
        user.setFullName(registerDTO.getFirstName() + " " + registerDTO.getLastName());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(registerDTO.getPassword());
        return user;
    }

    public boolean checkEmailExist(String email) {
        return this.userRepository.existsByEmail(email);
    }

    public User getUserByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    public long countUsers() {
        return this.userRepository.count();
    }

    public long countProducts() {
        return this.productRepository.count();
    }

    public long countOrders() {
        return this.orderRepository.count();
    }

    public void createPasswordResetToken(User user, String token){
        PasswordResetToken resetToken = new PasswordResetToken(token, user);
        tokenRepository.save(resetToken);
    }

    public void updatePassword(User user, String newPassword){
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }
}
