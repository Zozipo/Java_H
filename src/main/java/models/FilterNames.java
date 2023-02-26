package models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="tbl_filter_names")
public class FilterNames {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 100, nullable = false)
    private String name;
}
