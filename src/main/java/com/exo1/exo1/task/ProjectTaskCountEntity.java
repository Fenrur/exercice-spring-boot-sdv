package com.exo1.exo1.task;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity(name = "project_task_count")
public class ProjectTaskCountEntity {
    
    @Id
    @Column(name = "project_id")
    private Long id;

    @Column(name = "project_name")
    private String projectName;

    @Column(name = "task_count")
    private Long taskCount;
}
