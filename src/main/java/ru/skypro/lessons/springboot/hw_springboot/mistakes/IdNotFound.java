package ru.skypro.lessons.springboot.hw_springboot.mistakes;

public class IdNotFound extends RuntimeException {
    public IdNotFound() {
        super("Nothing found for this ID");
    }
}
