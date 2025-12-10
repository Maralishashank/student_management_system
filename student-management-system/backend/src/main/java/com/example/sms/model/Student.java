package com.example.sms.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "students")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name="first_name", nullable=false)
  private String firstName;

  @Column(name="last_name")
  private String lastName;

  private String email;
  private String rollNo;

  @Column(name="class_name")
  private String className;
}
