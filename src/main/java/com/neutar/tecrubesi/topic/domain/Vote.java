package com.neutar.tecrubesi.topic.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Vote {

    @Id
    @GeneratedValue
    private UUID id;

    @NotNull(message = "User id is mandatory")
    private UUID userId;

    @NotNull(message = "Vote type is mandatory")
    @Enumerated(EnumType.STRING)
    private VoteType voteType;
}
