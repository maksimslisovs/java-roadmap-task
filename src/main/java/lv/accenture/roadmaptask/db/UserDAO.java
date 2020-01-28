package lv.accenture.roadmaptask.db;

import lv.accenture.roadmaptask.entity.UserDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends JpaRepository<UserDO, Long> {
}
