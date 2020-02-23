package lv.accenture.roadmaptask.controller;

import lv.accenture.roadmaptask.Service.Service;
import lv.accenture.roadmaptask.db.UserDAO;
import lv.accenture.roadmaptask.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserController extends MainController {

    @Autowired
    UserDAO userDAO;
    Service service;

    @RequestMapping(value = "add/user", method = RequestMethod.POST)
    public ModelAndView saveUser(@RequestParam(value = "username", required = true) String username,
                                 ModelMap userModel) {
        service.saveUser(username);
        return new ModelAndView("redirect:/users");
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ModelAndView userListPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("users");
        List<User> usersList = (List<User>) userDAO.findAll();
        modelAndView.addObject("usersList", usersList);
        return modelAndView;
    }

    @RequestMapping(value = "update/user/{id}", method = RequestMethod.GET)
    public String updateUserPage(@PathVariable("id") long userId, ModelMap userModel) {
        userModel.addAttribute("id", userId);
        if (userDAO.findById(userId).isPresent()) {
            userModel.addAttribute("userDetail", userDAO.findById(userId).get());
        }
        return "userUpdate";
    }

    @RequestMapping(value = "update/user", method = RequestMethod.POST)
    public String updateUser(@RequestParam long id, @RequestParam(value = "username", required = true) String username,
                             ModelMap userModel) {
        service.updateUser(id,username);
        return "redirect:/users";
    }
}
