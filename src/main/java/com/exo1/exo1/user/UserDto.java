package com.exo1.exo1.user;

import java.util.List;

public record UserDto(Long id, String name, String email, List<Long> projectIds) {
    
}
