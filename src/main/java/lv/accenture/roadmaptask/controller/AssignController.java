package lv.accenture.roadmaptask.controller;

import lv.accenture.roadmaptask.db.BookRepository;
import lv.accenture.roadmaptask.db.UserDAO;
import lv.accenture.roadmaptask.entity.Book;
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
import java.util.Optional;

@Controller
public class AssignController {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    UserDAO userDAO;


    @RequestMapping(value = "book/{id}", method = RequestMethod.GET)
    public ModelAndView getBook(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("book");

        Optional<Book> byId = bookRepository.findById(id);
        modelAndView.addObject("book", byId.get());

        List<User> usersList = (List<User>) userDAO.findAll();
        modelAndView.addObject("usersList", usersList);

        return modelAndView;
    }

    @RequestMapping(value = "assign/user", method = RequestMethod.POST)
    public String assignUser(@RequestParam long id,
                             @RequestParam(value = "title", required = true) String name,
                             @RequestParam(value = "aname", required = true) String aname,
                             @RequestParam(value = "userId", required = true) Long userId,
                             @RequestParam(value = "username", required = true) String username,
                             ModelMap bookModel) {
        Book bookDetail = new Book();
        bookDetail.setId(id);
        bookDetail.setTitle(name);
        bookDetail.setAuthorName(aname);
        User user =new User();
        user.setId(userId);
        user.setUsername(username);
        bookDetail.setUser(user);
        bookRepository.save(bookDetail);

        return "redirect:/books";
    }
}
