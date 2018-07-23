package com.giladbendor.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.giladbendor.service.CategorizeService;
import org.json.JSONObject;

@Controller
public class CategorizeController {

	@Autowired
	private CategorizeService categorizeService;
	
	@RequestMapping("/categorize") 
	public ResponseEntity<String> categorize(@RequestParam(value = "phrase", required=true) final String phrase) {
		Map<String, Integer> mapping = categorizeService.categorize(phrase);
		JSONObject jsonObject = new JSONObject(mapping);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<String>(jsonObject.toString(), responseHeaders, HttpStatus.OK);
	}
	
}
