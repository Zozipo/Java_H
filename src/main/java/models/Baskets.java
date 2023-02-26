package models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="tbl_baskets")
@IdClass(BasketsPK.class)
public class Baskets {
    @Id
    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;
    @Id
    @ManyToOne
    @JoinColumn(name="product_id", nullable = false)
    private Products products;
}
