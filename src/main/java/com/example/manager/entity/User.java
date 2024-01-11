package com.example.manager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private String id;
    private String password;
    private String name;
    private String address;
    private String email;
    private String phone;
    private String birth;
    private LocalDate createDay;
    private LocalDate startDay;
    private String isActive;

//    @OneToMany
//    @JoinColumn(name = "WorkSite")
//    private List<WorkSite> workSiteList = new ArrayList<>();

}
