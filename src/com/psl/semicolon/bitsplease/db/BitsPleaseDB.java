package com.psl.semicolon.bitsplease.db;

import java.io.Closeable;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("bitsPleaseDB")
public class BitsPleaseDB implements Closeable {

	private static final String PERSISTENT_FILE = "BITS_PLEASE_DB_DUMP.db";
	private static final Map<String, Long> INTERNAL_DB = new HashMap<>();
	private static final Map<Long, String> INTERNAL_DB_ID = new HashMap<>();
	private final AtomicLong IDX;

	private final BitsPleaseFileIO fileIO;

	public BitsPleaseDB(@Value("${internal.db.persistent.path}") String filePath) {
		this.fileIO = new BitsPleaseFileIO(filePath + "/" + PERSISTENT_FILE, INTERNAL_DB);
		this.loadIdIndex();
		this.IDX = new AtomicLong(this.fileIO.getIdx());
	}

	public Long findIndex(String text) {
		Long id = INTERNAL_DB.get(text);
		if (id == null) {
			id = this.IDX.incrementAndGet();
			this.fileIO.write(id, text);
			INTERNAL_DB.put(text, id);
			INTERNAL_DB_ID.put(id, text);
		}
		return id;
	}

	public String findText(Long id) {
		String text = INTERNAL_DB_ID.get(id);
		if (text == null) {
			text = "";
		}
		return text;
	}

	@Override
	public void close() throws IOException {
		this.fileIO.close();
	}

	private void loadIdIndex() {
		for (Entry<String, Long> entry : INTERNAL_DB.entrySet()) {
			INTERNAL_DB_ID.put(entry.getValue(), entry.getKey());
		}
	}

}
