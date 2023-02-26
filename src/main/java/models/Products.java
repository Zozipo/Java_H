package models;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "tbl_products")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 100, nullable = false)
    private String name;
    @Column(nullable = false)
    private double price;
    @Column(length = 5000, nullable = false)
    private String description;
    @ManyToOne
    @JoinColumn(name="category_id",nullable = false)
    private  Categories categories;


}
