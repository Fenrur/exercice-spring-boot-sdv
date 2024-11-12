package com.exo1.exo1.project;

import com.exo1.exo1.task.TaskEntity;
import com.exo1.exo1.user.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "project")
public class ProjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String description;
    
    @ManyToMany(fetch = FetchType.LAZY)
    private List<UserEntity> users;
    
    @OneToMany(fetch = FetchType.LAZY)
    private List<TaskEntity> tasks;
}
