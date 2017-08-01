package Arrays;

import javax.swing.JOptionPane;

public class Release_bmiCalc {
	public static void main(String[] args) {
		// Variables
		String str_UserWeight = "", str_UserHeight;
		double userWeight = 0, userHeight = 0, bmi = 0;
		// Asking the user for its weight
		do {
			str_UserWeight = JOptionPane.showInputDialog("What is your weight?");
		} while (!isNumeric(str_UserWeight));
		userWeight = Double.parseDouble(str_UserWeight);
		// Asking the user for its height
		do {
			str_UserHeight = JOptionPane.showInputDialog("What is your height?");
		} while (!isNumeric(str_UserHeight));
		userHeight = Double.parseDouble(str_UserHeight);
		//
		bmi = userWeight / Math.pow(userHeight, 2);
		// Showing the user the diagnostic
		JOptionPane.showMessageDialog(null, String.format("Your BMI is: %.2f", bmi), "BMI Calculator",
				JOptionPane.INFORMATION_MESSAGE);
	}

	public static boolean isNumeric(String value) {
		try {
			if (value == null || value.length() == 0)
				System.exit(0);
			Double.parseDouble(value);
			return true;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Invalid input value!", "BMI Calculator", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
}
