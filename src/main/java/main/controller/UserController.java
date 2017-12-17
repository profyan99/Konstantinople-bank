package main.controller;

import main.dao.UserDao;
import main.entity.User;
import main.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class UserController {

    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("user",new User());
        return "registration";
    }

    @PostMapping(value = "/registration")
    public String createUser(@ModelAttribute(value = "user") User user) {
        String link = "registration";
        if(userService.getByName(user.getName()) == null) {
            long id = userService.create(user);
            link = "redirect:/user/" + id;
        }
        return link;
    }


    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

    @PostMapping(value = "/login")
    public String loginUser(@ModelAttribute("login") String login,
                            @ModelAttribute("password") String password, Model model) {
        User user;
        String link = "login";
        if((user = userService.getByName(login)) != null) {
            if(user.getPassword().equals(password))
                link = "redirect:/user/" + user.getId();
            else {
                model.addAttribute("Invalid password");
            }
        }
        return link;
    }

    @GetMapping(value = "/user/{id}")
    public String userProfile(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getById(id));
        return "userProfile";
    }
}
