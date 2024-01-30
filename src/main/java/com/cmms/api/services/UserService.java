package com.cmms.api.services;

import com.cmms.api.models.User;
import com.cmms.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    public User createUser(User user) {
        // Puedes realizar validaciones u operaciones adicionales antes de guardar el usuario
        return userRepository.save(user);
    }

    public User updateUser(User user) {
        if (user != null && user.getId() != null) {
            Optional<User> existingUser = userRepository.findById(user.getId());

            if (existingUser.isPresent()) {
                User updatedUser = existingUser.get();

                // Actualiza los campos según sea necesario
                updatedUser.setFirstName(user.getFirstName());
                updatedUser.setLastName(user.getLastName());
                updatedUser.setUsername(user.getUsername());
                updatedUser.setPassword(user.getPassword());
                updatedUser.setEmail(user.getEmail());
                updatedUser.setPhone(user.getPhone());

                // Puedes actualizar otros campos aquí

                return userRepository.save(updatedUser);
            } else {
                return null; // Manejar el caso de usuario no encontrado
            }
        } else {
            return null; // Manejar el caso de usuario nulo o sin ID
        }
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    public User getUserByUsername(String username) {
        List<User> users = getAllUsers();

        if (users != null){
            for (User user : users) {
                if(user.getUsername().equals(username)){
                    return user;
                }
            }
        }

        return null;
    }
}
