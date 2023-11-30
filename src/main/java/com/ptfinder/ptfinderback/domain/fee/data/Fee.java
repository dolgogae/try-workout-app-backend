package com.ptfinder.ptfinderback.domain.fee.data;

import com.ptfinder.ptfinderback.domain.fee.dto.FeeCreateDto;
import com.ptfinder.ptfinderback.domain.trainer.data.Trainer;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "FEE")
@EntityListeners(AuditingEntityListener.class)
public class Fee {
    @Id
    @Column(name = "FEE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TIMES")
    private Integer times;

    @Column(name = "PRICE")
    private Integer price;

    @Column(name = "DISCOUNT_RATE")
    private Float discountRate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TRAINER_ID")
    private Trainer trainer;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Builder
    private Fee(Integer times, Integer price, Float discountRate, Trainer trainer) {
        this.times = times;
        this.price = price;
        this.discountRate = discountRate;
        this.trainer = trainer;
    }

    public static Fee create(FeeCreateDto feeCreateDto, Trainer trainer){
        return Fee.builder()
                .times(feeCreateDto.getTimes())
                .price(feeCreateDto.getPrice())
                .discountRate(feeCreateDto.getDiscountRate())
                .trainer(trainer)
                .build();
    }
}
