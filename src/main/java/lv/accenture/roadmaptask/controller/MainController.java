package lv.accenture.roadmaptask.controller;

import lv.accenture.roadmaptask.db.BookRepository;
import lv.accenture.roadmaptask.entity.BookDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
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

//    @GetMapping("/")
//    public String index() {
//        Iterable<BookDO> all = bookRepository.findAll();
//
//        StringBuilder sb = new StringBuilder();
//
//        all.forEach(p -> sb.append(p.getName() + "<br>"));
//
//        return sb.toString();
//    }
//
//    @GetMapping("/book")
//    public String hello(Model model, String name) {
//        model.addAttribute("name", name);
//        return "book";
//    }
//
//    @GetMapping("/books")
//    public String allBooks(Model model, String name) {
//        model.addAttribute("bookList", bookRepository.findAll());
//        return "books";
//    }
//
    @RequestMapping(value="/books")
    public ModelAndView testPage(){
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

//    @RequestMapping(value = "books", method = RequestMethod.GET)
//    public String getUsersDetails(ModelMap userModel) {
//        userModel.addAttribute("bookList", bookRepository.findAll());
//        return "books";
//    }

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

//    @RequestMapping(value = "/add/book", method = RequestMethod.POST)
//    public String submit(
//            @ModelAttribute("employee") Employee employee,
//            BindingResult result, ModelMap model) {
//        if (result.hasErrors()) {
//            return "error";
//        }
//        model.addAttribute("name", employee.getName());
//        model.addAttribute("id", employee.getId());
//
//        employeeMap.put(employee.getId(), employee);
//
//        return "employeeView";
//    }

//    @RequestMapping(value = "/saveEmployee", method = RequestMethod.POST)
//    public ModelAndView saveEmployee(@ModelAttribute Employee employee) {
//        if (employee.getId() == 0) { // if employee id is 0 then creating the
//            // employee other updating the employee
//            employeeService.addEmployee(employee);
//        } else {
//            employeeService.updateEmployee(employee);
//        }
//        return new ModelAndView("redirect:/");
//    @RequestMapping(value="/save",method = RequestMethod.POST)
//    public ModelAndView save(@ModelAttribute("addBook") ){
//        //write code to save emp object
//        //here, we are displaying emp object to prove emp has data
//        System.out.println(emp.getName()+" "+emp.getSalary()+" "+emp.getDesignation());
//
//        //return new ModelAndView("empform","command",emp);//will display object data
//        return new ModelAndView("redirect:/viewemp");//will redirect to viewemp request mapping
//    }
//    @RequestMapping(value = "/add/book", method = RequestMethod.POST)
//    public String addBook(@RequestParam(value = "name", required = true) String name,
//                          @RequestParam(value = "aname", required = true) String aname, ModelMap bookModel) {
//        BookDO bookDetail = new BookDO();
//        bookDetail.setName(name);
//        bookDetail.setAuthorName(aname);
//
//        BookDO resp = bookRepository.save(bookDetail);
//        if ( resp != null ) {
//            bookModel.addAttribute("msg", "Book with id : " + resp.getId() + " added successfully.");
//            bookModel.addAttribute("bookList", bookRepository.findAll());
//            return "books";
//        } else {
//            bookModel.addAttribute("msg", "Book addition failed.");
//            return "add";
//        }
//    }

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


//    @GetMapping("/book")
//        public ModelAndView book(Model model) {
//            //CustomErrorBean errorBean = (CustomErrorBean) model.asMap().get("customErrorBean");
//
//            final ModelAndView modelAndView = new ModelAndView("book");
//            //modelAndView.addObject("notificationSearchCriteria", new NotificationSearchCriteria());
//            //modelAndView.addObject("noticeSearchErrorBean", errorBean);
//
//            return modelAndView;
//    }

//    @GetMapping(value = {"", "/"})
//    public ModelAndView home(HttpServletRequest request, HttpServletResponse response, Model model) {
//        getAndSetUserLang(request, response);
//
//        CustomErrorBean errorBean = (CustomErrorBean) model.asMap().get("customErrorBean");
//        return new ModelAndView("home", "errorBean", errorBean);
//    }

//    @GetMapping("/noAccessError")
//    public ModelAndView error403NoAccess() {
//        return new ModelAndView("noAccessError");
//    }
//
//    @GetMapping("/notFoundError")
//    public ModelAndView error404NotFound() {
//        return new ModelAndView("notFoundError");
//    }
//
//    @GetMapping("/internalServerError")
//    public ModelAndView error500InternalError() {
//        return new ModelAndView("errors");
//    }
//    @RequestMapping(value = "book", method = RequestMethod.GET)
//    public String getUsersDetails(ModelMap userModel) {
//        userModel.addAttribute("userDetail", book.getAllUserDetail());
//        return "book";
}