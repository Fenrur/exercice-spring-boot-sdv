package com.exo1.exo1.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface UserService {

    UserDto create(String name, String email, Set<Long> projectIds);
    
    UserDto get(Long id);

    UserDto update(Long id, String name, String email, Set<Long> projectIds);
    
    void delete(Long id);

    Page<UserDto> list(Pageable pageable);
}
