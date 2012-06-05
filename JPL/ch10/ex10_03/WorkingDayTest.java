package ch10.ex10_03;

import static org.junit.Assert.*;
import org.junit.Test;
public class WorkingDayTest {
	@Test	public void test() {
		try {
			//if
			assertTrue(WorkingDay.workDayIf(Day.MONDAY));
			assertTrue(WorkingDay.workDayIf(Day.THUESEDAY));
			assertTrue(WorkingDay.workDayIf(Day.WEDNESDAY));
			assertTrue(WorkingDay.workDayIf(Day.TUESDAY));
			assertTrue(WorkingDay.workDayIf(Day.FRIDAY));
			
			assertFalse(WorkingDay.workDayIf(Day.SATURDAY));
			assertFalse(WorkingDay.workDayIf(Day.SUNDAY));
			
			//switch
			assertTrue(WorkingDay.workDaySwitch(Day.MONDAY));
			assertTrue(WorkingDay.workDaySwitch(Day.THUESEDAY));
			assertTrue(WorkingDay.workDaySwitch(Day.WEDNESDAY));
			assertTrue(WorkingDay.workDaySwitch(Day.TUESDAY));
			assertTrue(WorkingDay.workDaySwitch(Day.FRIDAY));
			
			assertFalse(WorkingDay.workDaySwitch(Day.SATURDAY));
			assertFalse(WorkingDay.workDaySwitch(Day.SUNDAY));
		} catch (NullPointerException e) {
			System.out.println("エラー");
		}	
	}
	}
