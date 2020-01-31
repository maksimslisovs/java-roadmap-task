package lv.accenture.roadmaptask.controller;

import lv.accenture.roadmaptask.db.BookRepository;
import lv.accenture.roadmaptask.db.UserDAO;
import lv.accenture.roadmaptask.entity.Book;
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
import java.util.Optional;


@Controller
public class MainController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserDAO userDAO;

    @RequestMapping(value = "/books")
    public ModelAndView listPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("books");
        List<Book> booksList = (List<Book>) bookRepository.findAll();
        modelAndView.addObject("bookList", booksList);
        return modelAndView;
    }


    @RequestMapping(value = "book/{id}", method = RequestMethod.GET)
    public String getBookDetail(@PathVariable long id, ModelMap bookModel) {
        Optional<Book> byId = bookRepository.findById(id);
        bookModel.addAttribute("bookDetail", byId.get());
        return "book";
    }

    @RequestMapping("/add")
    public ModelAndView addPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("add");
        return modelAndView;
    }

    @RequestMapping(value = "add/book", method = RequestMethod.POST)
    public ModelAndView saveBook(@ModelAttribute Book book) {
        bookRepository.save(book);
        return new ModelAndView("redirect:/books");
    }

    @RequestMapping(value = "add/user", method = RequestMethod.POST)
    public ModelAndView saveUser(@ModelAttribute User user) {
        userDAO.save(user);
        return new ModelAndView("redirect:/users");
    }

    @RequestMapping(value = "delete/book/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable("id") long id, ModelMap bookModel) {
        bookRepository.deleteById(id);
        bookModel.addAttribute("bookDetail", bookRepository.findAll());
        if (bookRepository.findById(id).isPresent()) {
            bookModel.addAttribute("msg", "Book with id : " + id + " deleted successfully.");
        } else {
            bookModel.addAttribute("msg", "Book with id : " + id + " deletion failed.");
        }
        return "redirect:/books";
    }

    @RequestMapping(value = "update/book/{id}", method = RequestMethod.GET)
    public String updatePage(@PathVariable("id") long id, ModelMap bookModel) {
        bookModel.addAttribute("id", id);
        bookModel.addAttribute("bookDetail", bookRepository.findById(id).get());
        return "update";
    }

    @RequestMapping(value = "update/book", method = RequestMethod.POST)
    public String updateBook(@RequestParam long id, @RequestParam(value = "name", required = true) String name,
                             @RequestParam(value = "aname", required = true) String aname, ModelMap bookModel) {
        Book bookDetail = new Book();
        bookDetail.setId(id);
        bookDetail.setTitle(name);
        bookDetail.setAuthorName(aname);
        bookRepository.save(bookDetail);

        return "redirect:/books";
    }

    @RequestMapping(value = "return/book/{book}", method = RequestMethod.GET)
    public String returnBook(@PathVariable("book") Book book, ModelMap bookModel) {
        book.setUser(null);
        bookRepository.save(book);
        return "redirect:/books";
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
    public String updateUser(@RequestParam long userId, @RequestParam(value = "userName", required = true) String userName,
                             ModelMap userModel) {
        User userDetail = new User();
        userDetail.setId(userId);
        userDetail.setUserName(userName);
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