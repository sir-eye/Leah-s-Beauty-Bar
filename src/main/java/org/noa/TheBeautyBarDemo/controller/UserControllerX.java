package org.noa.TheBeautyBarDemo.controller;

import org.noa.TheBeautyBarDemo.model.User;
import org.noa.TheBeautyBarDemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserControllerX {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerClient(@ModelAttribute("user") User user) {
        userService.createUser(user);
        return "redirect:/users/login"; // Redirect to login after registration
    }

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("user", new User());
        return "index";
    }

    @PostMapping("/login")
    public String loginClient(@RequestParam("email") String email,
                              @RequestParam("password") String password,
                              Model model) {
        User authenticatedUser = userService.authenticate(email, password);

        if (authenticatedUser != null) {
            return "redirect:/users/appointments?email=" + authenticatedUser.getEmail(); // Redirect with email
        } else {
            model.addAttribute("error", "Invalid email or password.");
            return "index"; // Stay on login page if authentication fails
        }
    }
}


















//package org.noa.TheBeautyBarDemo.controller;
//
//import org.noa.TheBeautyBarDemo.model.User;
//import org.noa.TheBeautyBarDemo.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//
//@Controller
//@RequestMapping ("/users")
//public class UserControllerX {
//    private UserService userService;
//@Autowired
//    public UserControllerX(UserService userService) {
//        this.userService = userService;
//    }
//    @GetMapping("/register")
//    public String registerForm (Model model) {
//        model.addAttribute("user", new User());
//       return "register";
//    }
//    @PostMapping("/register")
//    public String registerClient (@ModelAttribute("user") User user, Model model) {
//        userService.createUser(user);
//        return "redirect:/users/login";
//    }
//    @GetMapping("/login")
//    public String loginForm () {
//        return "index";
//    }
//    @PostMapping("/login")
//    public String loginClient(@ModelAttribute("user") User user, Model model, RedirectAttributes redirectAttributes) {
//        User authenticatedUser = userService.authenticate(user.getEmail(), user.getPassword());
//        if (authenticatedUser != null) {
//            redirectAttributes.addFlashAttribute("loggedInUser", authenticatedUser);
//            return "redirect:/users/appointments"; // Redirect to appointments page
//        } else {
//            model.addAttribute("error", "Invalid email or password.");
//            return "index"; // Stay on login page if authentication fails
//        }
//    }
//}
