
/**
 * Esta Solu��o de Software � capaz de calcular o �ndice de Massa Corp�rea de um paciente atrav�s dos dados pessoais de peso e altura por ele inseridos, exibindo, a cada execu��o, os resultados obtidos.
 * @author Lucas Viana
 */
// Importando as classes utilizadas da Java API
import javax.swing.JOptionPane;
import javax.swing.JSpinner;

// Classe principal
public class IMC {
	// Ponto de entrada da aplica��o
	public static void main(String[] args) {
		showScreen();
	}

	// M�todo utilizado na exibi��o da tela e na chamada de m�todos de c�lculo
	static void showScreen() {
		// Components
		JSpinner weight = new JSpinner(new javax.swing.SpinnerNumberModel(0d, 0d, 300d, 1d));
		JSpinner height = new JSpinner(new javax.swing.SpinnerNumberModel(0, 0, 300, 10));
		Object[] components = new Object[] { "Digite seu peso: [kg] (Utilize v�rgula)", weight, "Digite sua altura: [cm] (N�meros inteiros)", height };
		//
		Object[] options = new Object[] { "Calcular", "Sair" };
		// Executing
		int choice;
		do {
			choice = JOptionPane.showOptionDialog(null, components, "Calculadora de IMC", JOptionPane.NO_OPTION,
					JOptionPane.PLAIN_MESSAGE, null, options, null);
			if(((double) weight.getValue() == 0 || (int) height.getValue() == 0) && choice == 0) {
				JOptionPane.showMessageDialog(null, "Insira valores v�lidos, nenhum deles pode ser nulo!", "Calculadora de IMC", JOptionPane.PLAIN_MESSAGE);
			}
			else {
			switch (choice) {
			case 0:
				double result = calculateImc((double) weight.getValue(), (int) height.getValue());
				String classif = "";
				if(result < 18.5) {
					classif = "Abaixo do Peso Normal";
				}
				else if(result < 25) {
					classif = "Peso Normal";
				}
				else if(result < 30) {
					classif = "Excesso de Peso";
				}
				else if(result < 35) {
					classif = "Obesidade Classe I";
				}
				else if(result < 40) {
					classif = "Obesidade Classe II";
				}
				else {
					classif = "Obesidade Classe III";
				}
				//
				JOptionPane.showMessageDialog(null,
						String.format("O seu �ndice de Massa Corp�rea � de: %.2f\nClassifica��o: %s", result, classif),
						"Calculadora de IMC",
						JOptionPane.INFORMATION_MESSAGE);
				// Performing a fix on the values at components
				height.setValue(0);
				weight.setValue(0d);
				break;
			case 1:
				JOptionPane.showMessageDialog(null, "Volte sempre, adoramos sua visita!", "Calculadora de IMC",
						JOptionPane.INFORMATION_MESSAGE);
			default:
				System.exit(0);
				break;
			}
			}
		} while (choice == 0);
	}

	// M�todo para calcular o �ndice de Massa Corp�rea
	static double calculateImc(double weight, int height) {
		return weight / Math.pow((height / 100d), 2);
	}
}