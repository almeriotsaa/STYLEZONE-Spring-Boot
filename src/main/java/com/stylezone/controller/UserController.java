package com.stylezone.controller;

import com.stylezone.model.User;
import com.stylezone.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public User create(@RequestBody User user) {
        return service.save(user);
    }

    @GetMapping
    public List<User> list(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String role,
            @RequestParam(required = false) String email
    ) {
        return service.search(name, role, email);
    }

    @GetMapping("/{id}")
    public User detail(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public User update(
            @PathVariable Integer id,
            @RequestBody User user
    ) {
        user.setUserId(id);
        return service.save(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
