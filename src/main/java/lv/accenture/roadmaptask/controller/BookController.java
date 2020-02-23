package lv.accenture.roadmaptask.controller;

import lv.accenture.roadmaptask.Service.Service;
import lv.accenture.roadmaptask.db.BookRepository;
import lv.accenture.roadmaptask.entity.Book;
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
public class BookController extends MainController {

    @Autowired
    BookRepository bookRepository;
    Service service;

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public ModelAndView listBooks() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("books");
        List<Book> booksList = (List<Book>) bookRepository.findAll();
        modelAndView.addObject("bookList", booksList);
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addBook() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("add");
        return modelAndView;
    }

    @RequestMapping(value = "add/book", method = RequestMethod.POST)
    public String saveBookPost(@RequestParam(value = "title", required = true) String title,
                           @RequestParam(value = "authorName", required = true) String authorName, ModelMap bookModel) {
        service.saveBook(title,authorName);
        return "redirect:/books";
    }

    @RequestMapping(value = "delete/book/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable("id") long id, ModelMap bookModel) {
        bookRepository.deleteById(id);
        bookModel.addAttribute("bookDetail", bookRepository.findAll());
        return "redirect:/books";
    }

    @RequestMapping(value = "update/book/{id}", method = RequestMethod.GET)
    public String updatePage(@PathVariable("id") long id, ModelMap bookModel) {
        bookModel.addAttribute("id", id);
            bookModel.addAttribute("bookDetail", bookRepository.findById(id).get());
        return "update";
    }

    @RequestMapping(value = "update/book", method = RequestMethod.POST)
    public String updateBook(@RequestParam long id, @RequestParam(value = "title", required = true) String title,
                             @RequestParam(value = "aname", required = true) String aname, ModelMap bookModel) {
    service.updateBook(id,title,aname);
    return "redirect:/books";
    }

    @RequestMapping(value = "return/book/{book}", method = RequestMethod.GET)
    public String returnBook(@PathVariable("book") Book book, ModelMap bookModel) {
        book.setUser(null);
        bookRepository.save(book);
        return "redirect:/books";
    }
}
