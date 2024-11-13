package com.exo1.exo1.user;

import java.util.List;
import java.util.Set;

public record UserDto(Long id, String name, String email, Set<Long> projectIds) {
    
}
