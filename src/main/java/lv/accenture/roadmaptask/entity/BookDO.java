package lv.accenture.roadmaptask.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "LIBRARY")
@Data
public class BookDO {

    @Id
    @GeneratedValue
    @Column(name = "Id", nullable = false)
    private Long id;

    @Column(name = "Name", length = 64, nullable = false)
    private String name;

    @Column(name = "Author_Name", length = 64)
    private String authorName;

//    @Temporal(TemporalType.DATE)
//    @Column(name = "Date")
//    private Date date;


}
