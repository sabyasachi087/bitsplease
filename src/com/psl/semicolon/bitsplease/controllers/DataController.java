package com.psl.semicolon.bitsplease.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.psl.semicolon.bitsplease.db.BitsPleaseDB;
import com.psl.semicolon.bitsplease.db.InMemoryDatabase;
import com.psl.semicolon.bitsplease.exception.BitsPleaseException;
import com.psl.semicolon.bitsplease.services.HardwareCommunicator;

@Controller("dataController")
public class DataController {

	@Autowired
	private InMemoryDatabase database;

	@Autowired
	private BitsPleaseDB bitsDB;

	@Autowired
	private ApplicationContext context;

	private HardwareCommunicator getCommunicator() {
		return this.context.getBean("hdwComm", HardwareCommunicator.class);
	}

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

	@RequestMapping(path = { "/train" }, method = RequestMethod.PUT)
	public ResponseEntity<String> train(@RequestBody String message) {
		try {
			Long id = this.bitsDB.findIndex(message);
			this.getCommunicator().send(id);
			return new ResponseEntity<String>("ok", HttpStatus.ACCEPTED);
		} catch (BitsPleaseException bpe) {
			return new ResponseEntity<String>("Error : " + bpe.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
