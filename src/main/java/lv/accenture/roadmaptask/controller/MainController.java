package lv.accenture.roadmaptask.controller;

import lv.accenture.roadmaptask.db.BookRepository;
import lv.accenture.roadmaptask.entity.BookDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

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

    @GetMapping("/book")
    public String hello(Model model, String name) {
//        model.addAttribute("name", name);
        return "book";
    }

    @GetMapping("/books")
    public String allBooks(ModelMap modelMap) {
        modelMap.addAttribute("Books", bookRepository.findAll());
        return "books";
    }
//

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