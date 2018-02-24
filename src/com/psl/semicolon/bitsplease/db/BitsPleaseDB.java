package com.psl.semicolon.bitsplease.db;

import java.io.Closeable;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Component;

@Component("bitsPleaseDB")
public class BitsPleaseDB implements Closeable {

	private static final String PERSISTENT_FILE = "BITS_PLEASE_DB_DUMP.db";
	private static final Map<String, Long> INTERNAL_DB = new HashMap<>();
	private final AtomicLong IDX;

	private final BitsPleaseFileIO fileIO;

	public BitsPleaseDB() {
		this.fileIO = new BitsPleaseFileIO(PERSISTENT_FILE, INTERNAL_DB);
		this.IDX = new AtomicLong(this.fileIO.getIdx());
	}

	public Long findIndex(String text) {
		Long id = INTERNAL_DB.get(text);
		if (id == null) {
			id = this.IDX.incrementAndGet();
			this.fileIO.write(id, text);
			INTERNAL_DB.put(text, id);
		}
		return id;
	}

	@Override
	public void close() throws IOException {
		this.fileIO.close();
	}

}
