package lv.accenture.roadmaptask.init;

import lv.accenture.roadmaptask.db.BookRepository;
import lv.accenture.roadmaptask.entity.BookDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;



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

        if (count == 0) {
            BookDO b1 = new BookDO();

            b1.setName("TestBook1");
            b1.setAuthorName("TestAuthor00");
            //b1.setDate(Date);



            bookRepository.save(b1);

        }

    }
}
