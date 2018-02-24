package com.psl.semicolon.bitsplease.db;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import com.opencsv.CSVReader;

public class BitsPleaseDB {

	private static final String PERSISTENT_FILE = "BITS_PLEASE_DB_DUMP.db";
	private static final Map<Long, String> INTERNAL_DB = new HashMap<>();

	private void loadDB() {

		try (Reader reader = Files.newBufferedReader(Paths.get(PERSISTENT_FILE));
				CSVReader csvReader = new CSVReader(reader)) {

		} catch (Exception ex) {

		}

	}

}
