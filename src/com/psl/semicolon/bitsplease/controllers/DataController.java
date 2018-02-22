package com.psl.semicolon.bitsplease.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.psl.semicolon.bitsplease.db.InMemoryDatabase;
import com.psl.semicolon.bitsplease.exception.BitsPleaseException;

@Controller("dataController")
public class DataController {

	@Autowired
	private InMemoryDatabase database;

	@RequestMapping(path = { "/gesture" }, method = RequestMethod.PUT)
	public ResponseEntity<Void> gesture(@RequestBody String message) {
		try {
			this.database.save(message);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		} catch (BitsPleaseException bpe) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(path = { "/gesture" }, method = RequestMethod.GET)
	public ResponseEntity<String> gesture() {
		String result = "";
		try {
			result = this.database.getMessage();
		} catch (BitsPleaseException bpe) {
			return new ResponseEntity<String>("", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

}
