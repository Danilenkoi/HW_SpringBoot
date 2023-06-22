package ru.skypro.lessons.springboot.hw_springboot.impl;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.skypro.lessons.springboot.hw_springboot.dto.UserDTO;
import ru.skypro.lessons.springboot.hw_springboot.repository.UserRepository;
import ru.skypro.lessons.springboot.hw_springboot.servic.UserMap;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserDetailsImpl userDetails;
    private final UserMap userMap;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDTO userDto = userRepository
                .findByLogin(username)
                .map(userMap::toDto)
                .orElseThrow(() -> new UsernameNotFoundException("No user with login " + username));
        userDetails.setUserDTO(userDto);
        return userDetails;
    }
}
