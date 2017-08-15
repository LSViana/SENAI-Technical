
/**
 * This application converts temperature values between many different scales.
 * @author Lucas Viana
 */
import javax.swing.SpinnerNumberModel;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;

public class Converter {
	public static void main(String[] args) {
		showWelcome();
	}

	public static void showWelcome() {
		// Components and Variables
		int choice = 0;
		StringBuilder result = new StringBuilder();
		JRadioButton celsius = new JRadioButton("Celsius");
		JRadioButton fahr = new JRadioButton("Fahrenheit");
		JRadioButton kelvin = new JRadioButton("Kelvin");
		JSpinner value = new JSpinner(new SpinnerNumberModel(0.0, 0.0, 10000000.0, 1.0)); 
		ButtonGroup scales = new ButtonGroup();
		{
			scales.add(celsius);
			scales.add(fahr);
			scales.add(kelvin);
		}
		//
		do {
			// input = JOptionPane.showInputDialog(null,
			// "Digite 'F', 'C' ou 'K' para selecionar a escala de temperatura desejada",
			// "Conversor de Temperatura", JOptionPane.QUESTION_MESSAGE);
			choice = JOptionPane.showOptionDialog(null,
					new Object[] { "Escolha a escala desejada: ", celsius, fahr, kelvin }, "Conversor de Temperatura",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[] { "Calcular" }, null);
			if (choice == -1)
				return;
			else {
				try {
					value.setValue(0.0);
					choice = (JOptionPane.showOptionDialog(null,
							new Object[] { "Digite a temperatura desejada: ", value }, "Conversor de Temperatura", JOptionPane.OK_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[] { "Calcular" }, null));
					if (fahr.isSelected()) {
						result.append(String.format(
								"O respectivo valor em Celsius é de: %.2fºC\nO respectivo valor em Kelvin é de: %.2fK",
								FahrenheitToCelsius((double) value.getValue()), FahrenheitToKelvin((double) value.getValue())));
					} else if (celsius.isSelected()) {
						result.append(String.format(
								"O respectivo valor em Fahrenheit é de: %.2fºF\nO respectivo valor em Kelvin é de: %.2fK",
								CelsiusToFahrenheit((double) value.getValue()), CelsiusToKelvin((double) value.getValue())));
					} else if (kelvin.isSelected()) {
						result.append(String.format(
								"O respectivo valor em Fahrenheit é de: %.2fºF\nO respectivo valor em Celsius é de: %.2fºC",
								KelvinToFahrenheit((double) value.getValue()), KelvinToCelsius((double) value.getValue())));
					} else {
						JOptionPane.showMessageDialog(null,
								"Algo deu errado, tente novamente com uma das opções especificadas!",
								"Conversor de Temperatura", JOptionPane.ERROR_MESSAGE, null);
						continue;
					}
					JOptionPane.showMessageDialog(null, result, "Conversor de Temperatura", JOptionPane.INFORMATION_MESSAGE);
				} catch (NumberFormatException exc) {
					JOptionPane.showMessageDialog(null,
							"A conversão falhou! Insira um número válido de acordo à sua cultura!",
							"Conversor de Temperatura", JOptionPane.ERROR_MESSAGE);
				}
				result.setLength(0);
			}
		} while (choice != -1);
	}

	// Core methods
	public static double CelsiusToKelvin(double input) {
		return input + 273.15;
	}

	public static double CelsiusToFahrenheit(double input) {
		return input * 1.8 + 32;
	}
	
	public static double FahrenheitToCelsius(double input) {
		return (input - 32) / 1.8;
	}

	// Returnable methods from API
	public static double KelvinToCelsius(double input) {
		return input + (input - CelsiusToKelvin(input));
	}

//	public static double FahrenheitToCelsius(double input) {
//		return input * Math.pow((input / CelsiusToFahrenheit(input)), 2);
//	}

	public static double KelvinToFahrenheit(double input) {
		return CelsiusToFahrenheit(KelvinToCelsius(input));
	}

	public static double FahrenheitToKelvin(double input) {
		return CelsiusToKelvin(FahrenheitToCelsius(input));
	}
}
