package com.javarush.spring10.controller.http;

import com.javarush.spring10.entity.User;
import com.javarush.spring10.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
@SessionAttributes({"currentUser"})
public class UserController {

    private final UserService userService;

    @GetMapping("/")
    public ModelAndView showAllUsers(ModelAndView view) {
        view.addObject("users", userService.findAll());
        view.setViewName("userpage");
        return view;
    }


    @GetMapping("/{id}/")
    public ModelAndView showOneUserAndUsers(ModelAndView view, @PathVariable Long id) {
        Optional<User> optionalUser = userService.get(id);
        if (optionalUser.isPresent()) {
            view.addObject("user", optionalUser.get());
            view.addObject("users", userService.findAll());
        }
        view.setViewName("userpage");
        return view;
    }

    @PostMapping("/")
    public String processNewUserOrLogin(User user,
                                        HttpSession session,
                                        @RequestParam(required = false) String createUser) {
        if (Objects.nonNull(createUser)) {
            user = userService.save(user);
            return "redirect:/users/%d/".formatted(user.getId());
        } else {
            log.info(" user {} login ", user);
            session.setAttribute("currentUser", user);
            return "redirect:/users/";
        }
    }


    @PostMapping("/{id}/")
    public String updateOrDeleteUser(User user,
                                     @RequestParam(required = false) String deleteUser) {
        if (Objects.nonNull(deleteUser)) {
            userService.delete(user);
            return "redirect:/users/";
        } else {
            userService.save(user);
            return "redirect:/users/%d/".formatted(user.getId());
        }
    }
}
