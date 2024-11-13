package com.exo1.exo1.user;

import com.exo1.exo1.Mapper;
import com.exo1.exo1.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/user")
public record UserController(UserService userService, Mapper mapper) {
    
    public record CreateUserRequestBody(String name, String email, Optional<Set<Long>> projectIds) { }
    public record CreateUserResponseBody(Long id, String name, String email, Set<Long> projectIds) { }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateUserResponseBody create(@RequestBody CreateUserRequestBody body) {
        var userDto = userService.create(body.name(), body.email(), body.projectIds().orElse(Set.of()));
        return mapper.toCreateUserResponseBody(userDto);
    }

    public record GetUserResponseBody(Long id, String name, String email, Set<Long> projectIds) { }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetUserResponseBody get(@PathVariable Long id) {
        var userDto = userService.get(id);
        return mapper.toGetUserResponseBody(userDto);
    }

    public record UpdateUserRequestBody(String name, String email, Optional<Set<Long>> projectIds) { }
    public record UpdateUserResponseBody(Long id, String name, String email, Set<Long> projectIds) { }
    
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UpdateUserResponseBody update(@RequestBody UpdateUserRequestBody body, @PathVariable Long id) {
        var update = userService.update(id, body.name(), body.email(), body.projectIds().orElse(Set.of()));
        return mapper.toUpdateUserResponseBody(update);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }
    
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<GetUserResponseBody> list(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        var pageable = Pageable.ofSize(size).withPage(page);
        
        return Page.from(
                userService
                        .list(pageable)
                        .map(mapper::toGetUserResponseBody)
        );
    }
}
