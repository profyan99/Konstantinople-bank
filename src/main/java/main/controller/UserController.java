package main.controller;

import main.dao.UserDao;
import main.entity.Bill;
import main.entity.Transaction;
import main.entity.User;
import main.response.UserProfile;
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
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/registration")
    public String createUser(@RequestParam("user") UserProfile user) {
        String link = "success";
        if(userService.getByName(user.getName()) == null) {
            long id = userService.create(user);
        }
        else
            link = "error";
        return link;
    }


    @PostMapping(path = "/login")
    public String loginUser(@RequestParam("login") String login,
                            @RequestParam("password") String password) {
        User user;
        String link = "success";
        if((user = userService.getByName(login)) != null) {
            if(!user.getPassword().equals(password)) {
                link = "invalid password";
            }
        }
        else
            link = "Not registered";
        return link;
    }

    @GetMapping(path = "/user/{id}")
    public @ResponseBody UserProfile userProfile(@PathVariable("id") long id) {
        return userService.getUserProfile(id);
    }

    @GetMapping(path = "/user/{id}/bills")
    public @ResponseBody Set<Bill> userBills(@PathVariable("id") long id) {
        return userService.getUserBills(id);
    }

    @GetMapping(path = "/user/{id}/transactions")
    public @ResponseBody Set<Transaction> userTransactions(@PathVariable("id") long id) {
        return userService.getUserTransactions(id);
    }

    @GetMapping(path = "/userlist")
    public @ResponseBody List<UserProfile> userList() {
        return userService.findAll();
    }
}
