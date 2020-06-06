package com.neutar.tecrubesi.topic.exception;

import java.util.UUID;

public class TopicNotFoundException extends RuntimeException {

    private static final String TOPIC_NOT_FOUND = "Topic not found. Topic Id: ";

    public TopicNotFoundException(UUID topicId) {
        super(TOPIC_NOT_FOUND + topicId);
    }
}
