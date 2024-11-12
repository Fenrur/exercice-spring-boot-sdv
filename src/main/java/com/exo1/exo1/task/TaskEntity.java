package com.exo1.exo1.task;

import com.exo1.exo1.user.UserEntity;
import com.exo1.exo1.project.ProjectEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "task")
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;
    
    @Column(nullable = false)
    private TaskStatut statut;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private ProjectEntity project;
    
    @OneToOne(fetch = FetchType.LAZY)
    private UserEntity user;
}
