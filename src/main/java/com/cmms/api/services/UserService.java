package com.cmms.api.services;

import com.cmms.api.models.User;
import com.cmms.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
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

    public User updateUser(Integer id, User user) {
        if (user != null && id != null) {
            Optional<User> existingUser = userRepository.findById(id);

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
                    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                    String hashedPassword = passwordEncoder.encode(user.getPassword());
                    updatedUser.setPassword(hashedPassword);
                }
                if (user.getEmail() != null) {
                    updatedUser.setEmail(user.getEmail());
                }
                if (user.getPhone() != null) {
                    updatedUser.setPhone(user.getPhone());
                }

                // Obtiene la fecha y hora actual en UTC+3
                LocalDateTime now = LocalDateTime.now(ZoneId.of("UTC-6"));

                // Actualiza la fecha de modificación
                updatedUser.setModifyDate(now.toString());

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
