package ru.skypro.lessons.springboot.hw_springboot.repository;

import org.springframework.data.repository.CrudRepository;
import ru.skypro.lessons.springboot.hw_springboot.position.Position;

public interface PositionRepository extends CrudRepository<Position, Long> {
}
