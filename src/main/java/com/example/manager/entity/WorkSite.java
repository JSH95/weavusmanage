package com.example.manager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;


@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "worksite")
public class WorkSite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer no;
    private String userId;
    private String companyName;
    private String station;
    private String projectName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;

}
