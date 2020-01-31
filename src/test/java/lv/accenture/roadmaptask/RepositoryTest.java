//package lv.accenture.roadmaptask;
//
//import lv.accenture.roadmaptask.db.BookRepository;
//import lv.accenture.roadmaptask.entity.BookDO;
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
//        private BookDO bookDO;
//       private BookRepository bookRepository;
//
//
//
//        @Before
//        public void init() {
//            BookDO b1 = new BookDO();
//
//            b1.setTitle("TestBook");
//            b1.setAuthorName("TestAuthor");
//            bookRepository.save(b1);
//
//            BookDO b2 = new BookDO();
//            b2.setTitle("TestBook_2");
//            b2.setAuthorName("TestAuthor_2");
//            bookRepository.save(b2);
//
//        }
//
//        //@Ignore
//        @Test
//        public void whenNoNotificationFound_thenReturnEmptyList() {
//            List<BookDO> booksList = (List<BookDO>) bookRepository.findAll();
//            System.out.println(booksList);
//            assertNull(booksList);
//        }
//}
