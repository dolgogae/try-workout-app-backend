package com.tryworkout.backend.domain.gym.data;

import com.tryworkout.backend.domain.gym.dto.GymCreateDto;
import com.tryworkout.backend.domain.gym.dto.GymFeeUpdateDto;
import com.tryworkout.backend.domain.gym.dto.GymInfoUpdateDto;
import com.tryworkout.backend.domain.gym.dto.GymOperatingTimeDto;
import com.tryworkout.backend.domain.trainer.data.Trainer;
import lombok.Builder;
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
    @Column(name = "GYM_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "GYM_NAME")
    private String gymName;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "ROAD_ADDRESS")
    private String roadAddress;

    @Column(name = "INFORMATION")
    private String information;

    @Column(name = "PARKING_YN")
    private Boolean parkingYn;

    @Column(name = "PARKING_FEE")
    private Integer parkingFee;

    @Column(name = "WEAR_YN")
    private Boolean wearYn;

    @Column(name = "WEAR_FEE")
    private Integer wearFee;

    @Column(name = "LOCKER_YN")
    private Boolean lockerYn;

    @Column(name = "LOCKER_FEE")
    private Integer lockerFee;

    @Column(name = "BUSINESS_HOURS_WEEK")
    private String businessHoursWeek;

    @Column(name = "BUSINESS_HOURS_WEEKEND")
    private String businessHoursWeekend;

    @Column(name = "HOLIDAY")
    private String holiday;

    @Column(name = "MAP_X")
    private Float mapX;

    @Column(name = "MAP_Y")
    private Float mapY;

    @OneToMany(mappedBy = "gym",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Trainer> trainers = new ArrayList<>();

    @CreatedDate
    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;

    @Builder
    private Gym(String gymName, String address, String roadAddress, Float mapX, Float mapY) {
        this.gymName = gymName;
        this.address = address;
        this.roadAddress = roadAddress;
        this.mapX = mapX;
        this.mapY = mapY;
    }

    public static Gym create(GymCreateDto gymCreateDto){
        return Gym.builder()
                .gymName(gymCreateDto.getGymName())
                .address(gymCreateDto.getAddress())
                .roadAddress(gymCreateDto.getRoadAddress())
                .mapX(gymCreateDto.getMapX())
                .mapY(gymCreateDto.getMapY())
                .build();
    }

    public Gym updateGymFees(GymFeeUpdateDto gymFeeUpdateDto){
        this.parkingYn = gymFeeUpdateDto.getParkingYn();
        this.parkingFee = gymFeeUpdateDto.getParkingFee();
        this.lockerYn = gymFeeUpdateDto.getLockerYn();
        this.lockerFee = gymFeeUpdateDto.getLockerFee();
        this.wearYn = gymFeeUpdateDto.getWearYn();
        this.wearFee = gymFeeUpdateDto.getWearFee();
        return this;
    }

    public Gym updateOperatingTime(GymOperatingTimeDto dto){
        this.businessHoursWeek = dto.getBusinessHoursWeek();
        this.businessHoursWeekend = dto.getBusinessHoursWeekend();
        this.holiday = dto.getHoliday();
        return this;
    }

    public Gym updateGymInfo(GymInfoUpdateDto dto){
        this.information = dto.getInformation();
        return this;
    }
}
