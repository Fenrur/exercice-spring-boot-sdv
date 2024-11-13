package com.exo1.exo1.user;

import com.exo1.exo1.Mapper;
import com.exo1.exo1.project.ProjectEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

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
    public UserDto create(String name, String email, Set<Long> projectIds) {
        var projectEntities = projectIds
                .stream()
                .map(projectId -> ProjectEntity.builder().id(projectId).build())
                .collect(Collectors.toSet());

        var userEntity = UserEntity.builder()
                .name(name)
                .email(email)
                .projects(projectEntities)
                .build();

        userRepository.save(userEntity);
        
        return mapper.toUserDto(userEntity);
    }

    @Override
    @Transactional
    public UserDto get(Long id) {
        var userEntity = userRepository.findById(id).orElseThrow();
        return mapper.toUserDto(userEntity);
    }

    @Override
    @Transactional
    public UserDto update(Long id, String name, String email, Set<Long> projectIds) {
        var userEntity = userRepository.findById(id).orElseThrow();
        
        var projectEntities = projectIds
                .stream()
                .map(projectId -> ProjectEntity.builder().id(projectId).build())
                .collect(Collectors.toSet());
        
        userEntity.setName(name);
        userEntity.setEmail(email);
        
        userRepository.save(userEntity);
        
        return mapper.toUserDto(userEntity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
    
    @Override
    public Page<UserDto> list(Pageable pageable) {
        return userRepository.findAll(pageable).map(mapper::toUserDto);
    }
}
