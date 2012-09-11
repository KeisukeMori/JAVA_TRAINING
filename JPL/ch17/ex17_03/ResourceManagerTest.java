package ch17.ex17_03;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ResourceManagerTest {

	int SIZE = 500000;
	ResourceManager manager;
	String key;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		manager = new ResourceManager();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {

		Resource[] resArr = new Resource[SIZE];
		for(int i = 0; i < SIZE; i++){
			key = String.valueOf("str:"+i);
			resArr[i] = manager.getResource(key);
			resArr[i].use(key, new Integer(i));
			key = null;
		}
		
		for(int i = SIZE; i < SIZE / 2; i++){
			resArr[i].release();
		}
		System.out.println("gc　Run.");
		Runtime.getRuntime().gc();
		manager.shutdown();

	}

}
