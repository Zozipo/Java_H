package models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tbl_productImages")
public class ProductImages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 255, nullable = false)
    private String name;
    @Column(length = 20, nullable = false)
    private int priority;
    @ManyToOne
    @JoinColumn(name="product_id",nullable = false)
    private  Products products;
}
