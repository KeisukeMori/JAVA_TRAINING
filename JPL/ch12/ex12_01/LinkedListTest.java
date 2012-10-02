package ch12.ex12_01;


import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class LinkedListTest {

	public class TestEntry{
		public String str;
		public TestEntry(String str){
			this.str = str;
		}
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}
	String str1 = "one";
	String str2 = "two";

	@Test
	public void constructorTest1(){
		LinkedList<String> l = new LinkedListImpl<String>(str1);
		assertEquals(str1, l.get());
		assertFalse(l.hasNext());

		LinkedList<String> l2 = new LinkedListImpl<String>(str2, l);
		assertEquals(str2, l2.get());
		assertEquals(l, l2.next());
		assertTrue(l2.hasNext());

		LinkedList<String> l3 = new LinkedListImpl<String>(str1);
		l3.addLast(str2);

		assertEquals(new LinkedListImpl<String>(str2).get(), l3.next().get());

	}
	@Test
	public void addLastTest(){
		LinkedList<String> list = new LinkedListImpl<String>("one");
		list.addLast("two");
		list.addLast("three");

		assertEquals("three", list.next().next().get());
	}

	@Test
	public void constructorTest(){
		LinkedList<String> list = new LinkedListImpl<String>("one", "two", "three", "four");

		assertEquals("one", list.get());
		assertEquals("two", list.next().get());
		assertEquals("three", list.next().next().get());
		assertEquals("four", list.next().next().next().get());
	}

	public void sizeTest(){
		LinkedList<String> list = new LinkedListImpl<String>();
		assertEquals(list.size(), 0);

		list.addLast("1");
		list.addLast("2");
		assertEquals(list.size(), 2);
	}

	@Test
	public void cloneTest(){
		TestEntry entry1 = new TestEntry("one");
		TestEntry entry2 = new TestEntry("two");
		TestEntry entry3 = new TestEntry("three");
		TestEntry entry1_5 = new TestEntry("one point five");

		LinkedList<TestEntry> list = new LinkedListImpl<TestEntry>(entry1, entry2, entry3);
		LinkedList<TestEntry> list2 = list.clone();

		//list2の1と2の間にList挿入
		list2.setNext(new LinkedListImpl<TestEntry>(entry1_5, list2.next()));

		assertEquals(entry1, list.get());
		assertEquals(entry1, list2.get());
		assertEquals(entry2, list.next().get());
		assertEquals(entry1_5, list2.next().get());

		((TestEntry)list2.get()).str = "replace";

		assertEquals("replace", ((TestEntry)list.get()).str);
		assertEquals("replace", ((TestEntry)list2.get()).str);
	}


	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

}
