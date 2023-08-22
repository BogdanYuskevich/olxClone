package com.buyit.olxclone.controllers;

import com.buyit.olxclone.enums.Role;
import com.buyit.olxclone.models.User;
import com.buyit.olxclone.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@Slf4j
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

    @GetMapping("/changeRoles/{userId}")
    public String showChangeRolesPage(@PathVariable Long userId, Model model) {
        User user = userService.findById(userId);
        if (user == null) {
            return "redirect:/admin"; // Redirect to admin panel or handle differently
        }

        model.addAttribute("user", user);
        return "user-edit"; // Thymeleaf template name
    }

    @GetMapping("/editRoles/{userId}")
    public String editUserRoles(@PathVariable Long userId, Model model) {
        User user = userService.findById(userId);
        List<Role> allRoles = Arrays.asList(Role.values()); // або будь-яким іншим способом отримати список ролей

        model.addAttribute("user", user);
        model.addAttribute("allRoles", allRoles);

        return "user-edit"; // повертаємо ім'я шаблону
    }

    @PostMapping("/editRoles/{userId}")
    public String updateUserRoles(@PathVariable Long userId, @RequestParam("selectedRoles") List<String> roles) {
        User user = userService.findById(userId);
        if (user != null) {
            Set<Role> newRoles = roles.stream()
                    .map(Role::valueOf)
                    .collect(Collectors.toSet());

            user.setRoleSet(newRoles);
            userService.updateUser(user); // Оновлення користувача
            log.info("Roles changed for user with id = {}; email: {}", user.getId(), user.getEmail());
        }

        return "redirect:/admin"; // Redirect back to admin panel
    }



}
