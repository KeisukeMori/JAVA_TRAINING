package ch13.ex13_04;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class TypeValueCreaterTest {


    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testParse() {

        ArrayList expectedResult = new ArrayList();
        expectedResult.add(new Boolean(true));
        expectedResult.add(new Double(11111.56));

        String testData = 
            "Boolean true\n" +
            "Double 11111.56";
        
        ArrayList<?> result = TypeValueCreater.parse(testData);
        
        assertTrue(result != null);
        //assertEquals(expectedResult.size(), result.size());

        for (int i = 0; i < expectedResult.size(); i++) {
            assertEquals(expectedResult.get(i), result.get(i));
        }
        
    }

}