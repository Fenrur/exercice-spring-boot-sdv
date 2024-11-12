package com.exo1.exo1.user;

import com.exo1.exo1.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public record UserController(UserService userService, Mapper mapper) {
    
    public record CreateUserRequestBody(String name, String email, Optional<List<Long>> projectIds) { }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody CreateUserRequestBody body) {
        userService.create(body.name(), body.email(), body.projectIds().orElse(List.of()));
    }

    public record GetUserResponseBody(Long id, String name, String email, List<Long> projectIds) { }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetUserResponseBody get(@PathVariable Long id) {
        var userDto = userService.get(id);
        return mapper.toGetUserResponseBody(userDto);
    }

    public record PutUserRequestBody(String name, String email, Optional<List<Long>> projectIds) { }
    
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody PutUserRequestBody body, @PathVariable Long id) {
        userService.update(id, body.name(), body.email(), body.projectIds().orElse(List.of()));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }
}
