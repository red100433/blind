package com.school.filesystem;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.runners.MockitoJUnitRunner;

import com.school.models.Type;
import com.school.models.vo.Subject;

@RunWith(MockitoJUnitRunner.class)
public class FileSystemTest {
	//	@Rule
	//	public final TemporaryFolder testFolder = new TemporaryFolder();

	String path;

	@InjectMocks
	FileSystem mock;
	List<Subject> list = new ArrayList<>();

	@Before
	public void setUp() throws Exception {
		path = Type.BASIC_PATH + "empObject.txt";
		list.add(new Subject("kkk"));
		list.add(new Subject("hihi"));
	}

	@Ignore
	@Test
	public void testGetInstance() throws Exception {
		throw new RuntimeException("not yet implemented");
	}

	@Test(expected = RuntimeException.class)
	public void testWriteListObject() throws Exception {
		doThrow(new RuntimeException()).when(mock).writeListObject(Matchers.anyListOf(Subject.class), anyString());
		mock.writeListObject(list, path);
		verify(mock, atLeastOnce()).writeListObject(list, path);
	}

	@Test
	public void testReadListObject() throws Exception {
		List<Subject> expectList = Arrays.asList(new Subject("kkk"), new Subject("hihi"));

		//		when(mock.readListObject(path)).thenReturn(new ArrayList<Subject>() {new Subject("kkk"), new Subject("hihi")});

		assertThat(list, is(mock.readListObject(path)));
	}
}
