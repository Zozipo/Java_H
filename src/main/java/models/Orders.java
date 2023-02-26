package models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tbl_order")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name="user_id",nullable = false)
    private  User user;
    @ManyToOne
    @JoinColumn(name="order_status_id",nullable = false)
    private  OrderStatuses orderStatuses;
}
