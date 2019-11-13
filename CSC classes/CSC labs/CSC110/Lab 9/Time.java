public class Time {
	private int hour;
	private int minute;
	private double second;

	public Time() {
		this.hour= 0;
		this.minute= 0;
		this.second= 0.0;
	}

	public Time(int hr, int min, double sec) {
		hour= hr;
		minute= min;
		second= sec;
	}

	public int getHour() {
		return this.hour;
	}

	public void setHour(int hour) {
		this.hour= hour;
	}

	public int getMinute() {
		return this.minute;
	}

	public void setMinute(int minute) {
		this.minute= minute;
	}

	public double getSecond() {
		return this.second;
	}

	public void setSecond(double second) {
		this.second= second;
	}
}