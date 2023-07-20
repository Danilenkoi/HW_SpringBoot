package ru.skypro.lessons.springboot.hw_springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLogin(String userName);
}
