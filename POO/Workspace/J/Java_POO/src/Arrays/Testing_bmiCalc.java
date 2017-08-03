package Arrays;

public class Testing_bmiCalc {
	public static void main(String[] args) {
		// Weight
		double weight = 70;
		// Height
		double height = 1.7;
		// Code
		double bmi = weight / Math.pow(height, 2);
		// Diagnostic
		String diagnostic = (bmi > 20 && bmi <= 25) ? "Ideal weight" : "Not ideal weight";
		System.out.printf("IMC: %.2f\nDiagnostic: %s\n", bmi, diagnostic);
	}
}
