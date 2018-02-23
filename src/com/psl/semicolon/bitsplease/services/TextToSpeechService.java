package com.psl.semicolon.bitsplease.services;

import java.util.List;

import com.ibm.watson.developer_cloud.text_to_speech.v1.TextToSpeech;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.Voice;

public class TextToSpeechService {

	public static void main(String[] args) {
		TextToSpeech service = new TextToSpeech();
		service.setUsernameAndPassword("555b516f-47bd-4bec-b2ca-f926e84f7b09", "B6pwx0mSjRzE");

		List<Voice> voices = service.getVoices().execute();
		System.out.println(voices);
	}

}
