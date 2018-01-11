package com.school.handler;

import java.util.Scanner;

public class ScannerHandler {
	private static ScannerHandler t;
	Scanner scanner;

	public ScannerHandler() {
		this.scanner = new Scanner(System.in);
	}

	public static ScannerHandler getInstance() {
		synchronized (ScannerHandler.class) {
			if (t == null) {
				t = new ScannerHandler();
			}
		}
		return t;
	}

	public Scanner getScanner() {
		return this.scanner;
	}

	public void close() {
		scanner.close();
	}

}
