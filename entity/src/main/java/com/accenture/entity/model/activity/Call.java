package com.accenture.entity.model.activity;

import com.accenture.entity.model.data.ContactData;
import jakarta.persistence.*;
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

    @ManyToOne
    @JoinColumn(name = "contact_data", nullable = false)
    private ContactData contactData;

}

