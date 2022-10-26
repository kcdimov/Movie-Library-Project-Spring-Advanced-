package bg.softuni.movies.models.entity;



import bg.softuni.movies.models.enums.UserRoleEnum;

import javax.persistence.*;

@Entity
@Table(name ="user_roles")
public class UserRoleEntity {

    private Long id;
    private UserRoleEnum userRole;

    public UserRoleEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    public UserRoleEnum getUserRole() {
        return userRole;
    }

    public UserRoleEntity setUserRole(UserRoleEnum userRole) {
        this.userRole = userRole;
        return this;
    }
}
