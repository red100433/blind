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

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import com.school.models.vo.Subject;

import lombok.extern.java.Log;

@Log
public class FileSystemTest {

	@Rule
	public final TemporaryFolder testFolder = new TemporaryFolder();

	File tempFile;

	@Before
	public void setUp() throws IOException {
		tempFile = testFolder.newFile("data.txt");
		List<Subject> list = new ArrayList<>();
		list.add(new Subject("hihi"));
		list.add(new Subject("byebye"));
		list.add(new Subject("huhihasdf"));

		try (FileOutputStream f = new FileOutputStream(tempFile);
			ObjectOutputStream o = new ObjectOutputStream(new BufferedOutputStream(f))) {
			o.writeObject(list);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

	@Test
	public void readListObject() {
		List<Subject> rev = new ArrayList<>();
		try (FileInputStream f = new FileInputStream(tempFile);
			ObjectInputStream oi = new ObjectInputStream(new BufferedInputStream(f))) {

			try {
				rev = (ArrayList)oi.readObject();
				rev.stream().map(s -> s.toString()).forEach(log::info);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {

		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}
}
