package com.orangeandbronze.enlistment;

public enum Period {
	P0830("8:30 - 10:00"), P1000 ("10:00 - 11:30"), P1130 ("11:30 - 1:00"), 
	P1300 ("1:00 - 2:30"), P1430 ("2:30 - 4:00"), P1600("4:00 - 5:30");
	
	private final String scheduleString;
	
	private Period(String scheduleString) {
		this.scheduleString = scheduleString;
	}
	
	@Override
	public String toString() {
		return scheduleString;
	}
	
}
