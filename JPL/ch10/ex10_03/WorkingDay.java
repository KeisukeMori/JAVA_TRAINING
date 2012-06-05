package ch10.ex10_03;

/* * Switch文の方が明瞭。If文はday, Dayなど重複記述されていて冗長。 */
public class WorkingDay {
	
	static public Boolean workDayIf(Day day) throws NullPointerException {
		if (day == Day.MONDAY || day == Day.TUESDAY || day == Day.WEDNESDAY ||
				day == Day.THUESEDAY || day == Day.FRIDAY) {
			return true;
		} else if (
				day == Day.SATURDAY || day == Day.SUNDAY) {
			return false;
		} else {
			throw new NullPointerException("NULL");
		}
	}
	static public Boolean workDaySwitch(Day day) throws NullPointerException {
		switch (day) {
		case MONDAY:
		case TUESDAY:
		case WEDNESDAY:
		case THUESEDAY:
		case FRIDAY:
			return true;
		case SATURDAY:
		case SUNDAY:
			return false;
		default:
			throw new NullPointerException("NULL");
		}
	}
}
