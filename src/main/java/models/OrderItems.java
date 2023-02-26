package models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tbl_order_items")
public class OrderItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private double priceBuy;
    @Column(nullable = false)
    private int count;
    @ManyToOne
    @JoinColumn(name="product_id",nullable = false)
    private  Products products;
    @ManyToOne
    @JoinColumn(name="order_id",nullable = false)
    private  Orders orders;
}
