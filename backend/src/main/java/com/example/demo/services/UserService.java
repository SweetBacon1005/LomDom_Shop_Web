package com.example.demo.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entities.User;
import com.example.demo.models.UserDTO;
import com.example.demo.repositories.UserRepository;

@Service
@Transactional
public class UserService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepository;

    private User mapToEntity(UserDTO userDTO, User user) {
        user = modelMapper.map(userDTO, User.class);
        return user;
    }

    private UserDTO mapToDTO(User user, UserDTO userDTO) {
        userDTO = modelMapper.map(user, UserDTO.class);
        return userDTO;
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User saveOrUpdate(User user) {
        return userRepository.save(user);
    }

    public List<UserDTO> findAllUser() {
        return userRepository.findAll().stream().map(user -> mapToDTO(user, new UserDTO()))
                .collect(Collectors.toList());
    }

    public User get(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(UserDTO userDTO) {
        final User user = new User();
        mapToEntity(userDTO, user);
        return userRepository.save(user).getId();
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}