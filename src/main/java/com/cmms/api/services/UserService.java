package com.cmms.api.services;

import com.cmms.api.models.User;
import com.cmms.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<User> getAllTechnicians() {
        return userRepository.findAllTechnicians();
    }

    public List<User> getAllSupervisors() {
        return userRepository.findAllSupervisors();
    }

    public List<User> getAllSiteManagers() {
        return userRepository.findAllSiteManagers();
    }

    @SuppressWarnings("null")
    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    public User createUser(User user) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        return userRepository.save(user);
    }

    @SuppressWarnings("null")
    public User updateUser(User user) {
        if (user != null && user.getId() != null) {
            Optional<User> existingUser = userRepository.findById(user.getId());

            if (existingUser.isPresent()) {
                User updatedUser = existingUser.get();

                // Actualiza los campos no nulos
                if (user.getFirstName() != null) {
                    updatedUser.setFirstName(user.getFirstName());
                }
                if (user.getLastName() != null) {
                    updatedUser.setLastName(user.getLastName());
                }
                if (user.getUsername() != null) {
                    updatedUser.setUsername(user.getUsername());
                }
                if (user.getPassword() != null) {
                    updatedUser.setPassword(user.getPassword());
                }
                if (user.getEmail() != null) {
                    updatedUser.setEmail(user.getEmail());
                }
                if (user.getPhone() != null) {
                    updatedUser.setPhone(user.getPhone());
                }

                // Actualiza la fecha de modificación
                updatedUser.setModifyDate(LocalDateTime.now().toString());

                return userRepository.save(updatedUser);
            } else {
                return null; // Manejar el caso de usuario no encontrado
            }
        } else {
            return null; // Manejar el caso de usuario nulo o sin ID
        }
    }

    @SuppressWarnings("null")
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    public User getUserByUsername(String username) {
        List<User> users = getAllUsers();

        if (users != null) {
            for (User user : users) {
                if (user.getUsername().equals(username)) {
                    return user;
                }
            }
        }

        return null;
    }
}
