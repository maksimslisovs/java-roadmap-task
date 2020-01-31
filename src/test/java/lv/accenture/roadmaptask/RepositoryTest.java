//package lv.accenture.roadmaptask;
//
//import lv.accenture.roadmaptask.db.BookRepository;
//import lv.accenture.roadmaptask.entity.Book;
//import org.junit.Before;
//import org.junit.Ignore;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.List;
//
//import static org.hamcrest.core.Is.is;
//import static org.junit.Assert.assertNull;
//import static org.junit.Assert.assertThat;
//
//
//
//    @RunWith(SpringRunner.class)
//    @DataJpaTest
//    @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//    public class RepositoryTest {
//
//        @Autowired
//        private TestEntityManager entityManager;
//
//        private Book bookDO;
//       private BookRepository bookRepository;
//
//
//
//        @Before
//        public void init() {
//            Book b1 = new Book();
//
//            b1.setTitle("TestBook");
//            b1.setAuthorName("TestAuthor");
//            bookRepository.save(b1);
//
//            Book b2 = new Book();
//            b2.setTitle("TestBook_2");
//            b2.setAuthorName("TestAuthor_2");
//            bookRepository.save(b2);
//
//        }
//
//        //@Ignore
//        @Test
//        public void whenNoNotificationFound_thenReturnEmptyList() {
//            List<Book> booksList = (List<Book>) bookRepository.findAll();
//            System.out.println(booksList);
//            assertNull(booksList);
//        }
//}
