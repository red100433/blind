package com.school.filesystem;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import com.school.models.vo.Subject;

public class FileSystemTest {
	@Rule
	public final TemporaryFolder testFolder = new TemporaryFolder();

	String path;

	FileSystem temp;
	List<Subject> list = new ArrayList<>();

	@Before
	public void setUp() throws Exception {
		path = testFolder.getRoot().toString() + "\\subObject.txt";
		list.add(new Subject("kkk"));
		list.add(new Subject("hihi"));
		temp = FileSystem.getInstance();

		temp.writeListObject(list, path);
	}

	@Test
	public void testGetInstance() throws Exception {
		FileSystem f1 = FileSystem.getInstance();
		FileSystem f2 = FileSystem.getInstance();
		assertEquals(f1, f2);
	}

	//expected = RuntimeException.class
	@Test
	public void testReadListObject() throws Exception {

		//given
		List<Subject> expectList = Arrays.asList(new Subject("kkk"), new Subject("hihi"));

		//when
		list = (List<Subject>)temp.readListObject(path);

		//then
		assertThat(expectList, is(list));
	}

}
