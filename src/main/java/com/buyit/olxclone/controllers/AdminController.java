package com.buyit.olxclone.controllers;

import com.buyit.olxclone.enums.Role;
import com.buyit.olxclone.models.User;
import com.buyit.olxclone.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminController {
    private final UserService userService;

    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("users", userService.findAllUsers());
        return "admin";
    }

    @PostMapping("admin/user/ban/{id}")
    public String userBan(@PathVariable("id") Long id) {
        userService.banUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/admin/user/edit/{user}")
    public String userEdit(@PathVariable("user") User user, Model model) {
        List<Role> availableRoles = Arrays.asList(Role.values());

        model.addAttribute("user", user);
        model.addAttribute("availableRoles", availableRoles);

        return "user-edit";
    }

    @PostMapping("/admin/user/edit")
    public String userEdit(
            @RequestParam("userId") User user,
            @RequestParam Map<String, String> form
    ) {
        userService.changeUserRoles(user, form);
        return "redirect:/admin";
    }
}
