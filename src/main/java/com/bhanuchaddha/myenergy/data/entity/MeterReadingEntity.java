package com.bhanuchaddha.myenergy.data.entity;

import com.bhanuchaddha.myenergy.service.meterreading.model.MeterReading;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table( name = "MATER_READING")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class MeterReadingEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    private double reading;

    @ManyToOne
    @JoinColumn(name = "METER_ID", insertable = false, updatable = false)
    private SmartMeterEntity smartMeterEntity;

    public MeterReading toMeterReading(){
        return MeterReading.builder()
                .time(timestamp)
                .reading(reading)
                .build();
    }

    public static MeterReadingEntity from(MeterReading meterReading){
        return MeterReadingEntity.builder()
                .timestamp(meterReading.getTime())
                .reading(meterReading.getReading())
                .build();

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MeterReadingEntity that = (MeterReadingEntity) o;
        return timestamp.equals(that.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp);
    }
}
