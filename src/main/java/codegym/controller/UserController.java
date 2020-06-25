package codegym.controller;


import codegym.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class UserController {

    @GetMapping(value = "/")
    public ModelAndView showForm(){
        ModelAndView modelAndView = new ModelAndView("form");
        modelAndView.addObject("user",new User());
        return modelAndView;
    }

    @PostMapping(value = "/submit")
    public ModelAndView submit(@Validated @Valid @ModelAttribute("user") User user, BindingResult bindingResult){
        new User().validate(user,bindingResult);
        if(bindingResult.hasFieldErrors()){
            ModelAndView modelAndView = new ModelAndView("form");
            return modelAndView;
        }
        ModelAndView modelAndView = new ModelAndView("form");
        return modelAndView;
    }
}
