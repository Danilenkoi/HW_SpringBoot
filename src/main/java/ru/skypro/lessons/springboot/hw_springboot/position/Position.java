package ru.skypro.lessons.springboot.hw_springboot.position;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import ru.skypro.lessons.springboot.hw_springboot.employee.Employee;

import java.util.List;
@Entity
@Data
@NoArgsConstructor
@Accessors(chain = true)
@Table(name = "position")
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String positionName;

    public Position(String positionName) {
        this.positionName = positionName;
    }

    public Position(Long id, String positionName) {
        this.id = id;
        this.positionName = positionName;
    }

}
