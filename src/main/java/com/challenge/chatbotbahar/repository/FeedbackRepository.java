package com.challenge.chatbotbahar.repository;

import com.challenge.chatbotbahar.domain.Feedback;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FeedbackRepository extends MongoRepository<Feedback, String> {
}