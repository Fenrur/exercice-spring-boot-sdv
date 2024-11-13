package com.exo1.exo1.project;

import com.exo1.exo1.task.TaskEntity;
import com.exo1.exo1.user.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "project")
public class ProjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Long id;

    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String description;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_projet", joinColumns = @JoinColumn(name = "project_id"), inverseJoinColumns = @JoinColumn(name = "users_id"))
    private Set<UserEntity> users;

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<TaskEntity> tasks;
}
