package com.psl.semicolon.bitsplease.db;

import org.junit.Before;
import org.junit.Test;

public class TestBitsPleaseDB {

	private String[] text = { "sabya", "sachi", "is", "a", "sachi", "hello", "sabya", "kaka" };
	private String[] newText = { "abhi", "shek", "is", "a", "good", "hello", "daddy" };

	@Before
	public void init() {

	}

	@Test
	public void test() {

		try (BitsPleaseDB db = new BitsPleaseDB()) {
			for (String str : text) {
				System.out.println(str + " : " + db.findIndex(str));
			}
			for (String str : newText) {
				System.out.println(str + " : " + db.findIndex(str));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
