package ch22.ex22_04;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import static org.junit.Assert.*;
import org.junit.Test;

public class AttrTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void constructorTest(){
		String name = "name";
		Integer value = 1;
		Attr<Integer> attr = new Attr<Integer>(name);
		assertEquals(name, attr.getName());

		Attr<Integer> attr2 = new Attr<Integer>(name, value);
		assertEquals(name, attr2.getName());
		assertEquals(value, attr2.getValue());
	}

	public void setValueTest(){
		String name = "name";
		String oldV = "old";
		String newV = "new";

		Attr<String> attr = new Attr<String>(name, oldV);

		assertEquals(oldV, attr.setValue(newV));
		assertEquals(newV, attr.getValue());
	}
}
