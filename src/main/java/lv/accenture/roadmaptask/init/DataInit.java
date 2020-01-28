package lv.accenture.roadmaptask.init;

import lv.accenture.roadmaptask.db.BookRepository;
import lv.accenture.roadmaptask.db.UserDAO;
import lv.accenture.roadmaptask.entity.BookDO;
import lv.accenture.roadmaptask.entity.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


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
            BookDO b1 = new BookDO();
            UserDO u1 = new UserDO();
            u1.setUserName("User1");

            userDAO.save(u1);

            b1.setName("TestBook");
            b1.setAuthorName("TestAuthor");
            b1.setUserDO(u1);
            bookRepository.save(b1);
//
//            BookDO b2 = new BookDO();
//            b2.setName("TestBook_2");
//
//            UserDO u2 = new UserDO();
//            u2.setUserName("User2");
//            userDAO.save(u2);
//            b2.setUserDO(u2);
//
//            bookRepository.save(b2);
//

//
//
//
//
        }

    }
}
