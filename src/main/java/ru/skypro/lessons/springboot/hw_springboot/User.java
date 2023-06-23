package ru.skypro.lessons.springboot.hw_springboot;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skypro.lessons.springboot.hw_springboot.transfers.Roles;

@Entity
@Table(name = "app_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "login", length = 25, unique = true, nullable = false)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "roles")
    @Enumerated(EnumType.STRING)
    private Roles roles;
}
