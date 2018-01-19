package com.school.business;

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

/**
 *
 * @author daeyun-jang
 *
 */

//List Data Read and Write class
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

		try (FileOutputStream f = new FileOutputStream(new File(path));
			ObjectOutputStream o = new ObjectOutputStream(new BufferedOutputStream(f))) {

			o.writeObject(list);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public <T> List<? extends T> readListObject(String path) {
		List<T> rev = new ArrayList<>();
		System.out.println("Read Object path" + new File(path).getAbsolutePath());
		try (FileInputStream f = new FileInputStream(new File(path));
			ObjectInputStream oi = new ObjectInputStream(new BufferedInputStream(f))) {

			try {
				rev = (ArrayList)oi.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			writeListObject(rev, path);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		return rev;
	}
}