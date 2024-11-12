package com.exo1.exo1.user;

import java.util.List;

public interface UserService {
    
    void create(String name, String email, List<Long> projectIds);
    
    UserDto get(Long id);
    
    void update(Long id, String name, String email, List<Long> projectIds);
    
    void delete(Long id);
}
