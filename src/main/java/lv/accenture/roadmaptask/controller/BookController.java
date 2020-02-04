package lv.accenture.roadmaptask.controller;

import lv.accenture.roadmaptask.db.BookRepository;
import lv.accenture.roadmaptask.entity.Book;
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
public class BookController extends MainController {

    @Autowired
    BookRepository bookRepository;

    @RequestMapping(value = "/books")
    public ModelAndView listBooks() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("books");
        List<Book> booksList = (List<Book>) bookRepository.findAll();
        modelAndView.addObject("bookList", booksList);
        return modelAndView;
    }

    @RequestMapping("/add")
    public ModelAndView addBook() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("add");
        return modelAndView;
    }

    @RequestMapping(value = "add/book", method = RequestMethod.POST)
    public ModelAndView saveBook(@ModelAttribute Book book) {
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
}
