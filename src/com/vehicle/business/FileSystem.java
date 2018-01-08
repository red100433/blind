package com.vehicle.business;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class FileSystem {

	private static FileSystem t;

	private FileSystem() {

	}

	public static FileSystem getInstance() {
		synchronized (FileSystem.class) {
			if (t == null) {
				t = new FileSystem();
			}
		}
		return t;
	}

	public synchronized <T> void writeListObject(List<? extends T> list, String path) {

		try (FileOutputStream f = new FileOutputStream(new File("myObjects.txt"));
			ObjectOutputStream o = new ObjectOutputStream(new BufferedOutputStream(f))) {

			o.writeObject(list);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public List<?> readListObject(String path) {

		try (FileInputStream f = new FileInputStream(new File("myObjects.txt"));
			ObjectInputStream oi = new ObjectInputStream(new BufferedInputStream(f))) {
			try {
				return (ArrayList)oi.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return new ArrayList<>();

	}

}