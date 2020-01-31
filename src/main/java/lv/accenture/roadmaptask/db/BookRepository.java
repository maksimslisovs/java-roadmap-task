package lv.accenture.roadmaptask.db;

import lv.accenture.roadmaptask.entity.BookDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookDO, Long> {
//    BookDO findByUserID (long userId);

}
