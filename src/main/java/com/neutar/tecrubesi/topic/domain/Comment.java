package com.neutar.tecrubesi.topic.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue
    private UUID id;

    @NotBlank
    private String message;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Flag> flag = new ArrayList<>();
}
