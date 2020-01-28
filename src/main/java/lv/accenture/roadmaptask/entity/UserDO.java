package lv.accenture.roadmaptask.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.List;


@Entity
    @Table(name = "USERS")
    @Data
    public class UserDO {

        @Id
        @GeneratedValue
        @Column(name = "UserId", nullable = false)
        private Long userId;

        @Column(name = "UserName", length = 64, nullable = false)
        private String userName;

}
