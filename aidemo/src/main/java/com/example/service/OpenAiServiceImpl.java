package com.example.service;

import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

import com.example.model.Answer;
import com.example.model.Question;

@Service
public class OpenAiServiceImpl implements OpenAiService {

	private final org.springframework.ai.chat.ChatClient chat;

	public OpenAiServiceImpl(ChatClient chat) {
		super();
		this.chat = chat;
	}

	@Override
	public String getAnswer(String question) {
		PromptTemplate pt = new PromptTemplate(question);
		Prompt pm = pt.create();
		ChatResponse ch = chat.call(pm);
		return ch.getResult().getOutput().getContent();

	}

	@Override
	public Answer getAnswer(Question question) {
		PromptTemplate pt = new PromptTemplate(question.question());
		Prompt pm = pt.create();
		ChatResponse ch = chat.call(pm);
		return new Answer(ch.getResult().getOutput().getContent());

	}

}
