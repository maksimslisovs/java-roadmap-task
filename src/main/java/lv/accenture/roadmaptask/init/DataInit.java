package lv.accenture.roadmaptask.init;

import lv.accenture.roadmaptask.db.BookRepository;
import lv.accenture.roadmaptask.db.UserDAO;
import lv.accenture.roadmaptask.entity.BookDO;
import lv.accenture.roadmaptask.entity.UserDO;
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
            BookDO b1 = new BookDO();
            UserDO u1 = new UserDO();
            u1.setUserName("User1");

            userDAO.save(u1);

            b1.setTitle("TestBook");
            b1.setAuthorName("TestAuthor");
            b1.setUserDO(u1);
            bookRepository.save(b1);

            BookDO b2 = new BookDO();
            b2.setTitle("TestBook_2");
            b2.setAuthorName("TestAuthor_2");

            bookRepository.save(b2);

            UserDO u2 = new UserDO();
            u2.setUserName("User2");

            userDAO.save(u2);



        }

    }
}
