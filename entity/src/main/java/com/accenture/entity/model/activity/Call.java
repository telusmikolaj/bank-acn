package com.accenture.entity.model.activity;

import com.accenture.entity.model.data.ContactData;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "call")
@Data
public class Call extends Activity {

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Embedded
    private ContactData contactData;

}

