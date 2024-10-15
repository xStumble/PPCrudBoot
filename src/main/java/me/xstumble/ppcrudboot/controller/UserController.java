package me.xstumble.ppcrudboot.controller;

import jakarta.validation.Valid;
import me.xstumble.ppcrudboot.model.User;
import me.xstumble.ppcrudboot.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String showUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "index";
    }

    @GetMapping(value = "/adduser")
    public String showAddUser(@ModelAttribute("user") User user) {
        return "adduser";
    }
    @PostMapping()
    public String createUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "adduser";
        }

        userService.addUser(user);
        return "redirect:/";
    }

    @GetMapping(value = "/edituser")
    public String showEditUser(Model model, @RequestParam long id) {
        model.addAttribute("user", userService.getUser(id));
        return "edituser";
    }
    @PostMapping("/edituser/edit")
    public String updateUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
                             @RequestParam long id) {
        if (bindingResult.hasErrors()) {
            return "edituser";
        }

        userService.updateUser(id, user);
        return "redirect:/";
    }

    @PostMapping("/deleteuser")
    public String deleteUser(@RequestParam long id) {
        userService.deleteUser(id);
        return "redirect:/";
    }
}
