package com.example.service;

import com.example.model.Answer;
import com.example.model.Question;

public interface OpenAiService {
	String getAnswer(String question);

	Answer getAnswer(Question question);
}
