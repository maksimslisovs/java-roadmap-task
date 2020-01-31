package lv.accenture.roadmaptask.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "BOOK")
@Data
public class Book {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "TITLE", length = 64, nullable = false)
    private String title;

    @Column(name = "AUTHOR_NAME", length = 64)
    private String authorName;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}
