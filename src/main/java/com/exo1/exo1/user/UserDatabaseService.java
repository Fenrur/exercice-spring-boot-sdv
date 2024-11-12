package com.exo1.exo1.user;

import com.exo1.exo1.Mapper;
import com.exo1.exo1.project.ProjectEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserDatabaseService implements UserService {
    private final UserRepository userRepository;
    private final Mapper mapper;

    public UserDatabaseService(UserRepository userRepository, Mapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public void create(String name, String email, List<Long> projectIds) {
        var projectEntities = projectIds
                .stream()
                .map(projectId -> ProjectEntity.builder().id(projectId).build())
                .toList();

        var userEntity = UserEntity.builder()
                .name(name)
                .email(email)
                .projects(projectEntities)
                .build();

        userRepository.save(userEntity);
    }

    @Override
    @Transactional
    public UserDto get(Long id) {
        var userEntity = userRepository.findById(id).orElseThrow();
        return mapper.toUserDto(userEntity);
    }

    @Override
    @Transactional
    public void update(Long id, String name, String email, List<Long> projectIds) {
        var userEntity = userRepository.findById(id).orElseThrow();
        
        var projectEntities = projectIds
                .stream()
                .map(projectId -> ProjectEntity.builder().id(projectId).build())
                .toList();
        
        userEntity.setName(name);
        userEntity.setEmail(email);
        
        userRepository.save(userEntity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
