package com.pancho.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "RECORD")
public class RecordOperation {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String operationType;
    private Long userId;
    private int amount;
    private String operationResponse;
    private Date date;
    private boolean visible;

}
