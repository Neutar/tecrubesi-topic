package com.neutar.tecrubesi.topic.service;

import com.neutar.tecrubesi.topic.domain.Topic;
import com.neutar.tecrubesi.topic.dto.*;
import com.neutar.tecrubesi.topic.exception.TopicNotFoundException;
import com.neutar.tecrubesi.topic.mapper.TopicMapper;
import com.neutar.tecrubesi.topic.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TopicService {

    private final TopicRepository topicRepository;

    @Transactional
    public void createTopic(TopicCreateDto topicCreateDto) {
        Topic topic = TopicMapper.TOPIC_MAPPER.mapTopicFrom(topicCreateDto);
        topic.initialize(topicCreateDto.getUserId());
        topicRepository.save(topic);
    }

    @Transactional
    public void updateTopic(UUID topicId, TopicUpdateDto topicUpdateDto){
        Topic topic = topicRepository.findById(topicId).orElseThrow(() -> new TopicNotFoundException(topicId));
        topic.updateTopic(topicUpdateDto);
        topicRepository.save(topic);
    }

    @Transactional
    public void addVote(UUID topicId, AddVoteDto addVoteDto) {
        Topic topic = topicRepository.findById(topicId).orElseThrow( () -> new TopicNotFoundException(topicId));
        topic.addVote(addVoteDto);
        topicRepository.save(topic);
    }

    @Transactional
    public void addFlag(UUID topicId, AddFlagDto addFlagDto) {
        Topic topic = topicRepository.findById(topicId).orElseThrow(() -> new TopicNotFoundException(topicId));
        topic.addFlag(addFlagDto);
        topicRepository.save(topic);
    }

    @Transactional
    public void addComment(UUID topicId, AddCommentDto addCommentDto) {
        Topic topic = topicRepository.findById(topicId).orElseThrow(() -> new TopicNotFoundException(topicId));
        topic.addComment(addCommentDto);
        topicRepository.save(topic);
    }

    @Transactional
    public void addFlagForComment(UUID topicId, UUID commentId, AddFlagDto addFlagDto) {
        Topic topic = topicRepository.findById(topicId).orElseThrow(() -> new TopicNotFoundException(topicId));
        topic.addFlagForComment(commentId, addFlagDto);
        topicRepository.save(topic);
    }

    @Transactional
    public void addTopicTag(UUID topicId, AddTopicTagDto addTopicTag) {
        Topic topic = topicRepository.findById(topicId).orElseThrow(() -> new TopicNotFoundException((topicId)));
        topic.addTag(addTopicTag);
        topicRepository.save(topic);
    }

    @Transactional
    public void addBookmark(UUID topicId, AddBookmarkDto addBookmarkDto) {
        Topic topic = topicRepository.findById(topicId).orElseThrow(() -> new TopicNotFoundException(topicId));
        topic.addBookmark(addBookmarkDto);
        topicRepository.save(topic);
    }

    @Transactional
    public void addFollow(UUID topicId, AddFollowDto addFollowDto) {
        Topic topic = topicRepository.findById(topicId).orElseThrow(() -> new TopicNotFoundException(topicId));
        topic.addFollowerUserId(addFollowDto.getUserId());
        topicRepository.save(topic);
    }

    @Transactional
    public List<TopicForUserDto> getAllTopicsForUser(UUID userId) {
        List<Topic> topicList = topicRepository.findAll();
        return topicList.stream().map(topic -> TopicMapper.TOPIC_MAPPER.mapTopicForUserDtoFrom(topic, userId)).collect(Collectors.toList());
    }


    public TopicDetailForUserDto getTopicDetailForUser(UUID topicId, UUID userId) {
        Topic topic = topicRepository.findById(topicId).orElseThrow(() -> new TopicNotFoundException(topicId));
        return TopicMapper.TOPIC_MAPPER.mapTopicDetailForUserDtoFrom(topic, userId);
    }
}
