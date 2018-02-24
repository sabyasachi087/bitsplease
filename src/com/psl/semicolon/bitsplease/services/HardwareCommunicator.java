package com.psl.semicolon.bitsplease.services;

import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("hdwComm")
@Scope("prototype")
public class HardwareCommunicator implements Closeable {

	private static final Logger LOGGER = LogManager.getLogger(HardwareCommunicator.class);
	private final Socket socket;

	protected HardwareCommunicator(@Value("${aurdino.host}") String hdwHost, @Value("${aurdino.port}") int port)
			throws UnknownHostException, IOException {
		socket = new Socket(hdwHost, port);
	}

	public void send(Long id) {
		try (DataOutputStream dout = new DataOutputStream(this.socket.getOutputStream())) {
			dout.write(("r " + id.toString()).getBytes());
			dout.flush();
			dout.close();
		} catch (Exception ex) {
			LOGGER.error(ex);
		}
	}

	@Override
	public void close() throws IOException {
		this.socket.close();
	}

}
