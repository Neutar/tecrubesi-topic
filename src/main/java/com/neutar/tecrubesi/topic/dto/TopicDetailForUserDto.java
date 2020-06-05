package com.neutar.tecrubesi.topic.dto;

import com.neutar.tecrubesi.topic.domain.Comment;
import com.neutar.tecrubesi.topic.domain.Status;
import com.neutar.tecrubesi.topic.domain.TopicTag;
import lombok.Builder;
import lombok.Value;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Value
@Builder
public class TopicDetailForUserDto {
    private UUID topicId;

    private UUID userId;

    private String title;

    private String description;

    private Long upVoteCount;

    private Long downVoteCount;

    @Builder.Default
    private List<Comment> commentList = new ArrayList<>();

    @Builder.Default
    private List<TopicTag> tagList = new ArrayList<>();

    private boolean isBookmarked;

    private boolean isFollowed;

    private boolean isUpVoted;

    private boolean isDownVoted;

    private boolean isFlagged;

    private Status status;

}
