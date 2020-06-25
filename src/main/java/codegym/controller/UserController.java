package codegym.controller;


import codegym.model.User;
import codegym.repository.UserRepository;
import codegym.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.validation.Valid;

@Controller
@EnableWebMvc
public class UserController {

    @Autowired
    private UserService userService;
    @GetMapping(value = "")
    public String showList(){
        return "list";
    }
    @GetMapping(value = "/create")
    public ModelAndView showForm(){
        ModelAndView modelAndView = new ModelAndView("form");
        modelAndView.addObject("user",new User());
        return modelAndView;
    }

    @ModelAttribute("userList")
    public Iterable<User> showAllUser(){
        return userService.showAll();
    }

    @PostMapping(value = "/submit")
    public ModelAndView submit(@Validated @Valid @ModelAttribute("user") User user, BindingResult bindingResult){
        new User().validate(user,bindingResult);
        if(bindingResult.hasFieldErrors()){
            ModelAndView modelAndView = new ModelAndView("form");
            return modelAndView;
        }
        ModelAndView modelAndView = new ModelAndView("redirect:/");
        userService.save(user);
        return modelAndView;
    }
}
