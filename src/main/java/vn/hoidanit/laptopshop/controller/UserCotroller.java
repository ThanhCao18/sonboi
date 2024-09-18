package vn.hoidanit.laptopshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.hoidanit.laptopshop.service.UserService;

// @Controller
// public class UserCotroller {

//     @RequestMapping("/")
//     public String getHomePage() {
//         return "hello from controller";
//     }

// }

@RestController
public class UserCotroller {

    private UserService userService;

    public UserCotroller(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String getHomePage() {
        return this.userService.handleHello();
    }

}
