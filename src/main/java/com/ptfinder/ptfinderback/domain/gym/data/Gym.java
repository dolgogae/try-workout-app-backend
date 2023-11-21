package com.ptfinder.ptfinderback.domain.gym.data;

import com.ptfinder.ptfinderback.domain.trainer.data.Trainer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "GYM")
@EntityListeners(AuditingEntityListener.class)
public class Gym {

    @Id
    @Column(name = "TRAINER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "GYM_NAME")
    private String gymName;

    @Column(name = "LOCATION")
    private String location;

    @Column(name = "INFORMATION")
    private String information;

    @Column(name = "PARKING_YN")
    private Boolean parkingYn;

    @Column(name = "PARKING_FEE")
    private Integer parkingFee;

    @Column(name = "WEAR")
    private Boolean wearYn;

    @Column(name = "WEAR_FEE")
    private Integer wearFee;

    @Column(name = "LOCKER_YN")
    private Boolean lockerYn;

    @Column(name = "lockerFee")
    private Boolean lockerFee;

    @Column(name = "BUSINESS_HOURS_WEEK")
    private String businessHoursWeek;

    @Column(name = "BUSINESS_HOURS_WEEKEND")
    private String businessHoursWeekend;

    @Column(name = "HOLIDAY")
    private String holiday;

    @OneToMany(mappedBy = "gym")
    private List<Trainer> trainers = new ArrayList<>();

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
