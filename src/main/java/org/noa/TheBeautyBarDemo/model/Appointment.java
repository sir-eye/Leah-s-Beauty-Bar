package org.noa.TheBeautyBarDemo.model;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String appointmentDate;
    private String appointmentTime;
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // This links to the User table
    private User user;
}