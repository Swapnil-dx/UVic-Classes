public class Car {
	private String maker;
	private String model;
	private int year;
	
	public Car() {
		this.maker= "";
		this.model= "";
		this.year= 1807;
	}
	
	public Car(String maker, String model, int year) {
		this.maker= maker;
		this.model= model;
		this.year= year;
	}
	
	public String getMaker() {
		return this.maker;
	}
	
	public void setMaker(String maker) {
		this.maker= maker;
	}

	public String getModel() {
		return this.model;
	}
	
	public void setModel(String model) {
		this.model= model;
	}

	public int getYear() {
		return this.year;
	}
	
	public void setYear(int year) {
		this.year= year;
	}
	
	public String toString() {
		String details= "Car: " + maker + " " + model + " " + year;
		return details;
	} 
}