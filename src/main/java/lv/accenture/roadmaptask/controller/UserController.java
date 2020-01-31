package lv.accenture.roadmaptask.controller;

import lv.accenture.roadmaptask.db.UserDAO;
import lv.accenture.roadmaptask.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
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

    @RequestMapping(value = "add/user", method = RequestMethod.POST)
    public ModelAndView saveUser(@ModelAttribute User user) {
        userDAO.save(user);
        return new ModelAndView("redirect:/users");
    }

    @RequestMapping(value = "/users")
    public ModelAndView userListPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("users");
        List<User> usersList = (List<User>) userDAO.findAll();
        modelAndView.addObject("usersList", usersList);
        return modelAndView;
    }

    @RequestMapping(value = "update/user/{id}", method = RequestMethod.GET)
    public String updateUser(@PathVariable("id") long userId, ModelMap userModel) {
        userModel.addAttribute("id", userId);
        userModel.addAttribute("userDetail", userDAO.findById(userId).get());
        return "userUpdate";
    }

    @RequestMapping(value = "update/user", method = RequestMethod.POST)
    public String updateUser(@RequestParam long userId, @RequestParam(value = "username", required = true) String username,
                             ModelMap userModel) {
        User userDetail = new User();
        userDetail.setId(userId);
        userDetail.setUsername(username);
        userDAO.save(userDetail);

        return "redirect:/users";
    }

    @RequestMapping(value = "delete/user/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable("id") long userId, ModelMap userModel) {
//        Book userHasBooks = new Book();
//        userHasBooks = bookRepository.findByUserID(id);
//        if (userHasBooks == null) {
        userDAO.deleteById(userId);
        userModel.addAttribute("userDetail", userDAO.findAll());
        if (userDAO.findById(userId).isPresent()) {
            userModel.addAttribute("msg", "User with id : " + userId + " deleted successfully.");
        } else {
            userModel.addAttribute("msg", "User with id : " + userId + " deletion failed.");
        }
        return "redirect:/users";
//        } else {
//            userModel.addAttribute("msg", "User still has books : " + userHasBooks + " deletion failed.");
//            return "redirect:/books";
//        }
    }
}
