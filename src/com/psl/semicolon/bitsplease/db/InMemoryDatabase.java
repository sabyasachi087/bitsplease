package com.psl.semicolon.bitsplease.db;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.psl.semicolon.bitsplease.exception.BitsPleaseException;

@Component("inMemDb")
public class InMemoryDatabase {

	private static final Logger LOGGER = LogManager.getLogger(InMemoryDatabase.class);
	private static final BlockingQueue<String> MESSAGES = new ArrayBlockingQueue<>(999999);

	public void save(String message) {
		try {
			MESSAGES.put(message);
		} catch (InterruptedException e) {
			LOGGER.error("Error saving messages", e);
			throw new BitsPleaseException(e);
		}
	}

	public String getMessage() {
		String result = "";
		try {
			if ((result = MESSAGES.poll(1, TimeUnit.SECONDS)) == null) {
				return "";
			}
		} catch (InterruptedException e) {
			LOGGER.error("Error retrieving messages", e);
			throw new BitsPleaseException(e);
		}
		return result;
	}

}
