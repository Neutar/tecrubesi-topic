package com.neutar.tecrubesi.topic.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Flag {

    @Id
    @GeneratedValue
    private UUID id;

    @NotNull(message = "User id is mandatory")
    private UUID userId;

    @NotNull(message = "Reason is mandatory")
    private String reason;

}
