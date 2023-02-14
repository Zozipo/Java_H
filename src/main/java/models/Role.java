package models;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="tbl_roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 255, nullable = false)
    private String name;

    @OneToMany(mappedBy = "role")
    private List<UserRole> userRoles;

    public Role() {
        userRoles = new ArrayList<>();
    }
}
