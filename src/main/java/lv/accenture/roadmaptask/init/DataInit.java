package lv.accenture.roadmaptask.init;

import lv.accenture.roadmaptask.db.BookRepository;
import lv.accenture.roadmaptask.db.UserDAO;
import lv.accenture.roadmaptask.entity.Book;
import lv.accenture.roadmaptask.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class DataInit implements CommandLineRunner {

    private BookRepository bookRepository;
    private UserDAO userDAO;

    @Autowired
    public DataInit(BookRepository bookRepository,UserDAO userDAO) {
        this.bookRepository = bookRepository;
        this.userDAO = userDAO;
    }

    @Override
    public void run(String... args) throws Exception {
        long count = bookRepository.count();

        if (count == 0) {
            Book b1 = new Book();
            User u1 = new User();
            u1.setUserName("User1");

            userDAO.save(u1);

            b1.setTitle("TestBook");
            b1.setAuthorName("TestAuthor");
            b1.setUser(u1);
            bookRepository.save(b1);

            Book b2 = new Book();
            b2.setTitle("TestBook_2");
            b2.setAuthorName("TestAuthor_2");

            bookRepository.save(b2);

            User u2 = new User();
            u2.setUserName("User2");

            userDAO.save(u2);



        }

    }
}
