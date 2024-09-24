package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Answer;
import com.example.model.Question;
import com.example.service.OpenAiService;

@RestController
public class OpenAiController {
	@Autowired
	private OpenAiService oi;

	@PostMapping("/ask")
	Answer getAnswer(@RequestBody Question question) {
		return oi.getAnswer(question);
	}

}
