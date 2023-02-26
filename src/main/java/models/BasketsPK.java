package models;

import lombok.Data;

import java.io.Serializable;

@Data
public class BasketsPK implements Serializable {
    private Products products;
    private User user;
}
