package com.backend.db.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="user_exercise_log")
public class UserExerciseLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_exercise_sequence")
    private Integer userExerciseSequence;

    @ManyToOne
    @JoinColumn(name = "user_sequence")
    private User user;

    @Column(name = "exercise_date")
    private LocalDateTime date;

    private Integer count;

    @Column(name = "div_exercise")
    private Integer div;

}
