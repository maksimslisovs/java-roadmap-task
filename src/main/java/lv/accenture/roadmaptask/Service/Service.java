package lv.accenture.roadmaptask.Service;

import lv.accenture.roadmaptask.db.BookRepository;
import lv.accenture.roadmaptask.db.UserDAO;
import lv.accenture.roadmaptask.entity.Book;
import lv.accenture.roadmaptask.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

public class Service {

    @Autowired
    UserDAO userDAO;
    BookRepository bookRepository;

    public void saveBook(String title, String authorName)    {
        Book bookDetail = new Book();
        bookDetail.setTitle(title);
        bookDetail.setAuthorName(authorName);
        bookRepository.save(bookDetail);
    }

    public void updateBook(long id, String title, String aname){
        Book bookDetail = new Book();
        bookDetail.setId(id);
        bookDetail.setTitle(title);
        bookDetail.setAuthorName(aname);
        bookRepository.save(bookDetail);
    }
    public void saveUser(String username){
        User userDetail = new User();
        userDetail.setUsername(username);
        userDAO.save(userDetail);
    }
    public void updateUser(long id,String username){
        User userDetail = new User();
        userDetail.setId(id);
        userDetail.setUsername(username);
        userDAO.save(userDetail);
    }

    public void assignUser(long id,String name,String aname,Long userId,String username){
        Book bookDetail = new Book();
        bookDetail.setId(id);
        bookDetail.setTitle(name);
        bookDetail.setAuthorName(aname);
        User user = new User();
        user.setId(userId);
        user.setUsername(username);
        bookDetail.setUser(user);
        bookRepository.save(bookDetail);
    }
}
