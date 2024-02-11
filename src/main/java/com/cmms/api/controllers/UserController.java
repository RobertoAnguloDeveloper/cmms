package com.cmms.api.controllers;

// import com.cmms.api.auth.request.AuthRequest;
import com.cmms.api.auth.response.AuthResponse;
import com.cmms.api.auth.service.AuthService;
import com.cmms.api.models.SecurityUser;
import com.cmms.api.models.User;
// import com.cmms.api.services.JpaUserDetailsService;
import com.cmms.api.services.JwtService;
import com.cmms.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
// import org.springframework.security.access.prepost.PreAuthorize;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.BadCredentialsException;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @Autowired
    JwtService jwtService;

    // @Autowired
    // private AuthenticationManager authenticationManager;

    // @Autowired
    // private JpaUserDetailsService myUserDetailService;

    // public UserController(AuthService authService){
    // this.authService = authService;
    // }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody User user) {
        return ResponseEntity.ok(authService.authenticate(new SecurityUser(user)));
    }

    // @PostMapping("/login")
    // public AuthResponse createToken(@RequestBody AuthRequest
    // authenticationRequest) throws Exception {
    // try {
    // UsernamePasswordAuthenticationToken authentication = new
    // UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
    // authenticationRequest.getPassword());
    // authenticationManager.authenticate(authentication);
    // } catch (BadCredentialsException e) {
    // throw new Exception("Invalid username or password", e);
    // }
    // UserDetails userDetails =
    // myUserDetailService.loadUserByUsername(authenticationRequest.getUsername());
    // String token = jwtService.generateToken(userDetails);
    // return new AuthResponse(token);
    // }

    @PostMapping("/save")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(users);
        }
    }

    @GetMapping("/technicians")
    public ResponseEntity<List<User>> getAllTechnicians() {
        List<User> technicians = userService.getAllTechnicians();
        if (technicians.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(technicians);
        }
    }

    @GetMapping("/supervisors")
    public ResponseEntity<List<User>> getAllSupervisors() {
        List<User> supervisors = userService.getAllSupervisors();
        if (supervisors.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(supervisors);
        }
    }

    @GetMapping("/site-managers")
    public ResponseEntity<List<User>> getAllSiteManagers() {
        List<User> siteManagers = userService.getAllSiteManagers();
        if (siteManagers.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(siteManagers);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Integer id) {
        Optional<User> userOptional = userService.getUserById(id);

        return userOptional.map(user -> ResponseEntity.ok(user))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Integer id, @RequestBody User user) {
        User updatedUser = userService.updateUser(id, user);
        return updatedUser != null ? ResponseEntity.ok(updatedUser) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
