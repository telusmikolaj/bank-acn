package com.accenture.entity.model.activity;

import com.accenture.entity.model.data.Address;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;


@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "meeting")
@Data
public class Meeting extends Activity {

    @OneToOne
    @JoinColumn(name = "address_id", nullable = false)
    @Cascade(CascadeType.PERSIST)
    private Address address;

}

