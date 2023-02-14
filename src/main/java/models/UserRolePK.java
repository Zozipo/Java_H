package models;


import lombok.Data;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Data
public class UserRolePK implements Serializable {
    private User user;
    private Role role;
}
