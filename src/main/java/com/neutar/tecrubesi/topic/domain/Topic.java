package com.neutar.tecrubesi.topic.domain;

import com.neutar.tecrubesi.topic.dto.*;
import com.neutar.tecrubesi.topic.exception.CommentNotFoundException;
import com.neutar.tecrubesi.topic.mapper.TopicMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Topic {
    @Id
    @GeneratedValue
    private UUID id;

    @NotBlank(message = "Title is mandatory")
    private String title;

    @NotBlank(message = "Description is mandatory")
    private String description;

    @NotNull(message = "User id is mandatory")
    private UUID userId;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "topic_id")
    @Builder.Default
    private List<Comment> commentList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "topic_id")
    @Builder.Default
    private List<TopicTag> tagList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "topic_id")
    @Builder.Default
    private List<Vote> voteList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "topic_id")
    @Builder.Default
    private List<Flag> flagList = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "topic_bookmarked_user", joinColumns = @JoinColumn(name = "topic_id"))
    @Column(name = "bookmarked_user_id")
    @Builder.Default
    private List<UUID> bookmarkedUserIdList = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "topic_followed_user", joinColumns = @JoinColumn(name = "topic_id"))
    @Column(name = "followed_user_id")
    @Builder.Default
    private List<UUID> followedUserIdList = new ArrayList<>();

    private UUID correctAnswerId;

    @Enumerated(EnumType.STRING)
    private Status status;

    public void updateTopic(TopicUpdateDto topicUpdateDto) {
        title = topicUpdateDto.getTitle();
        description = topicUpdateDto.getDescription();
    }

    public void addComment(AddCommentDto addCommentDto) {
        commentList.add(TopicMapper.TOPIC_MAPPER.mapCommentFrom(addCommentDto));
    }

    public void addFollowerUserId(UUID userId){
        followedUserIdList.add(userId);
    }

    public void addVote(AddVoteDto addVoteDto) {
        voteList.add(TopicMapper.TOPIC_MAPPER.mapVoteFrom(addVoteDto));
    }

    public void addFlag(AddFlagDto addFlagDto){
        flagList.add(TopicMapper.TOPIC_MAPPER.mapFlagFrom(addFlagDto));
    }

    public void addFlagForComment(UUID commentId, AddFlagDto addFlagDto) {
        Comment comment = commentList.stream().filter(c -> c.getId().equals(commentId)).findFirst().orElseThrow(() -> new CommentNotFoundException(commentId, id));
        comment.addFlag(TopicMapper.TOPIC_MAPPER.mapFlagFrom(addFlagDto));
    }

    public void setOpenStatus() {
        status = Status.OPEN;
    }

    public void addTag(AddTopicTagDto addTopicTag){
        tagList.add(TopicMapper.TOPIC_MAPPER.mapTopicTagFrom(addTopicTag));
    }

    public void addBookmark(AddBookmarkDto addBookmarkDto){
        bookmarkedUserIdList.add(addBookmarkDto.getUserId());
    }

    public void initialize(UUID userId) {
        addFollowerUserId(userId);
        setOpenStatus();
    }
}