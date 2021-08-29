package com.restboilarplate.entity.baseEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String CreatedBy;

    @CreationTimestamp
    @Column(name = "created_at",updatable = false)
    private LocalDate createDate;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updateDateTime;

}
