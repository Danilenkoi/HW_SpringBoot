package ru.skypro.lessons.springboot.hw_springboot.servic;

import org.springframework.stereotype.Component;
import ru.skypro.lessons.springboot.hw_springboot.dto.UserDTO;

@Component
public class UserMap {
    public UserDTO toDto(User user) {
        UserDTO userDTO =  new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setLogin(user.getLogin());
        userDTO.setPassword(user.getPassword());
        userDTO.setRole(user.getRoles().name());
        return userDTO;
    }
}
