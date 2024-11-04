package com.example.runningmanager.dao.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "user_run_data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRunData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_run_data_id")
    private Integer userRunDataId;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "mileage", nullable = false)
    private Integer mileage;

    @Column(name = "day", nullable = false)
    private Integer day;

    @Column(name = "state", nullable = false, columnDefinition = "tinyint(1) default 0")
    private boolean state;


}