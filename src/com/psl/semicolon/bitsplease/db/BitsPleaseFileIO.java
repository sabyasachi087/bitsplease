package com.psl.semicolon.bitsplease.db;

import java.io.Closeable;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;

import org.apache.logging.log4j.Logger;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class BitsPleaseFileIO implements Closeable {

	private final Logger LOGGER = org.apache.logging.log4j.LogManager.getLogger(BitsPleaseFileIO.class);
	private final Writer writer;

	private final CSVWriter csvWriter;
	private final String FILE_NAME;
	private Long lastIdx = 0l;

	public BitsPleaseFileIO(String filename, Map<String, Long> inMemDb) {
		this.FILE_NAME = filename;
		this.load(inMemDb);
		try {
			if (!Files.exists(Paths.get(this.FILE_NAME))) {
				writer = Files.newBufferedWriter(Paths.get(FILE_NAME));
			} else {
				writer = Files.newBufferedWriter(Paths.get(FILE_NAME), StandardOpenOption.APPEND);
			}
			csvWriter = new CSVWriter(writer, CSVWriter.DEFAULT_SEPARATOR, CSVWriter.NO_QUOTE_CHARACTER,
					CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
		} catch (Exception ex) {
			LOGGER.error(ex);
			throw new RuntimeException("Internal DB Write file access error");
		}
	}

	public void write(Long id, String data) {
		String[] nextLine = { id.toString(), data };
		this.csvWriter.writeNext(nextLine);
		if (id > lastIdx) {
			this.lastIdx++;
		}
	}

	private void load(Map<String, Long> map) {
		if (!Files.exists(Paths.get(this.FILE_NAME))) {
			return;
		}
		try (Reader reader = Files.newBufferedReader(Paths.get(this.FILE_NAME));
				CSVReader csvReader = new CSVReader(reader);) {
			// Reading Records One by One in a String array
			String[] nextRecord;
			while ((nextRecord = csvReader.readNext()) != null) {
				map.put(nextRecord[1], Long.valueOf(nextRecord[0]));
				if (Long.valueOf(nextRecord[0]) > this.lastIdx) {
					this.lastIdx = Long.valueOf(nextRecord[0]);
				}
			}
		} catch (Exception ex) {
			LOGGER.error(ex);
			throw new RuntimeException("Internal DB read file access error");
		}
	}

	public Long getIdx() {
		return this.lastIdx;
	}

	@Override
	public void close() throws IOException {
		this.csvWriter.close();
		this.writer.close();
	}

}
