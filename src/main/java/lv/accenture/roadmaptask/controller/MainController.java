package lv.accenture.roadmaptask.controller;

import lv.accenture.roadmaptask.db.BookRepository;
import lv.accenture.roadmaptask.db.UserDAO;
import lv.accenture.roadmaptask.entity.BookDO;
import lv.accenture.roadmaptask.entity.UserDO;
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

    @RequestMapping(value="/books")
    public ModelAndView listPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("books");
        List<BookDO> booksList = (List<BookDO>) bookRepository.findAll();
        modelAndView.addObject("bookList",booksList);
        return modelAndView;
    }


    @RequestMapping(value = "book/{id}", method = RequestMethod.GET)
    public String getBookDetail(@PathVariable long id, ModelMap bookModel) {
        Optional<BookDO> byId = bookRepository.findById(id);
        bookModel.addAttribute("bookDetail", byId.get());
    return "book";
}

    @RequestMapping("/add")
    public ModelAndView addBookPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("add");
        return modelAndView;
    }
    @RequestMapping(value = "add/book", method = RequestMethod.POST)
    public ModelAndView  saveBook(@ModelAttribute BookDO book) {
        bookRepository.save(book);
        return new ModelAndView("redirect:/books");
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
        BookDO bookDetail = new BookDO();
        bookDetail.setId(id);
        bookDetail.setName(name);
        bookDetail.setAuthorName(aname);
        bookRepository.save(bookDetail);

        return "redirect:/books";

    }

    @RequestMapping(value="/users")
    public ModelAndView userListPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("users");
        List<UserDO> usersList = (List<UserDO>) userDAO.findAll();
        modelAndView.addObject("usersList",usersList);
        return modelAndView;
    }

    @RequestMapping(value = "add/User", method = RequestMethod.POST)
    public ModelAndView  saveUser(@ModelAttribute UserDO userDO) {
        userDAO.save(userDO);
        return new ModelAndView("redirect:/users");
    }

}