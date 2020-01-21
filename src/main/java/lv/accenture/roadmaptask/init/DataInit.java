package lv.accenture.roadmaptask.init;

import lv.accenture.roadmaptask.db.BookRepository;
import lv.accenture.roadmaptask.entity.BookDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


@Component
public class DataInit implements CommandLineRunner {

    private BookRepository bookRepository;
    @Autowired
    public DataInit(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        long count = bookRepository.count();
        final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        if (count == 0) {
            BookDO b1 = new BookDO();

            b1.setName("TestBook");
            b1.setAuthorName("TestAuthor");
//            Date d1 = df.parse("1999-09-19");
//            b1.setDate(d1);

            bookRepository.save(b1);

            BookDO b2 = new BookDO();
            b2.setName("TestBook_2");
            b2.setAuthorName("TestAuthor_2");
//            Date d1 = df.parse("1999-09-19");
//            b1.setDate(d1);

            bookRepository.save(b2);

        }

    }
}
