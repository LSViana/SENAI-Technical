package business;
import java.awt.Color;
public class Car {
	public String model;
	public int maxSpeed;
	public Color color;
	public double zeroToHundred;
	public Engine engine;
	//
	public Car() {
		// Standard Constructor
	}
	//
	public Car(String model, int maxSpeed, Color color, double zeroToHundred, Engine engine) {
		super();
		this.model = model;
		this.maxSpeed = maxSpeed;
		this.color = color;
		this.zeroToHundred = zeroToHundred;
		this.engine = engine;
	}
	//
	@Override
	public String toString() {
		return String.format("Model: %s - Max Speed: %dkm/h - Color: %s - Zero to hundred: %.2f seconds - Type of Engine: %s - Engine's Potency: %d horses", model, maxSpeed, color, zeroToHundred, engine != null ? engine.type.toString() : "[No Engine]", engine != null ? engine.potency : 0);
	}
	//
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public int getMaxSpeed() {
		return maxSpeed;
	}
	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public double getZeroToHundred() {
		return zeroToHundred;
	}
	public void setZeroToHundred(double zeroToHundred) {
		this.zeroToHundred = zeroToHundred;
	}
}
