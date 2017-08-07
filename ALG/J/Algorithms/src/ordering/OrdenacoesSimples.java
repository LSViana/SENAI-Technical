package ordering;
import java.util.Random;

public class OrdenacoesSimples {
	public static void main (String[] args) {
		int[] vetor = gerarVetor(50000);
		bubbleSort(vetor);
		insectionSort(vetor);
		selectionSort(vetor);
	} 

	public static void bomDia() {
		System.out.println(" *!Bom dia!* ");
	} 

	public static void imprimirVetor(int[] vetor) {
		for(int i = 0; i < vetor.length; i++) {
			System.out.print(vetor[i] + ", ");
		} 

		System.out.println();
	}

	public static int[] gerarVetor(int qntd){
		int[] novoVetor = new int[qntd]; 
		Random aleatoriador3000 = new Random();
		for(int i = 0; i < qntd; i++){

			novoVetor[i] = aleatoriador3000.nextInt(999);
		} 

		return novoVetor;
	} 

	public static void bubbleSort(int[] vetor) {

		System.out.println("*****Iniciando Bubble Sort*****");

		long iteracoes = 0; 
		long trocas = 0; 
		long tempoInicio = System.currentTimeMillis(); 

		for(int i = 0; i < vetor.length -1; i++){

			iteracoes++; 

			for(int j = 0; j < vetor.length - i - 1; j++){

				iteracoes++;

				if(vetor[j] > vetor[j+1]){

					int aux = vetor[j];
					vetor[j] = vetor[j+1];
					vetor[j+1] = aux;

					trocas++;
				} 

			} 

		} 

		long tempFim = System.currentTimeMillis(); 

	System.out.println("*****Finalizando Bubble Sort*****");
		
		System.out.println();
		System.out.println("RELATORIO:");
		System.out.println("Quantidade de Elementos: " + vetor.length);
		System.out.println("Iteracoes: " + iteracoes );
		System.out.println("Trocas: " + trocas);
		System.out.println("Tempo realizado(milis): " + (tempFim - tempoInicio));
	} 

	public static void insectionSort(int[] vetor){

		System.out.println("*****Iniciando Insection Sort*****");

		long iteracoes = 0; 
		long trocas = 0; 
		long tempoInicio = System.currentTimeMillis(); 

		for(int i = 1; i < vetor.length - 1; i++){

			iteracoes++;

			int j = i;

			do{

				iteracoes++;

				//trocas
				trocas++;
				int aux = vetor[j];
				vetor[j] = vetor[j-1];
				vetor[j-1] = aux;

				j = j -1;

				if(j == 0){

					break;
				}

			}while(vetor[j-1] > vetor[j]);

			long tempFim = System.currentTimeMillis();
		}

		long tempFim = System.currentTimeMillis();

		System.out.println("*****Finalizando Insection Sort*****");

		System.out.println(" ");
		System.out.println("!RELATORIO!:");
		System.out.println("Quantidade de Elementos:" + vetor.length);
		System.out.println("Iteracoes: " + iteracoes );
		System.out.println("Trocas: " + trocas);
		System.out.println("Tempo realizado(milis): " + (tempFim - tempoInicio));
	} 

	public static void selectionSort(int[] vetor)	{

	System.out.println("*****Iniciando Selection Sort*****");

		long iteracoes = 0; 
		long trocas = 0; 
		long tempoInicio = System.currentTimeMillis(); 
		int min = 0; 

		for (int j = 0; j < vetor.length - 1; j++){

			min = j;

			iteracoes++;

			for(int i = j + 1; i < vetor.length - 1; i++ ){

			iteracoes++;

				if(vetor[i] < vetor[min]){

					min = i;
				} 

			} 
				if(min != j){

					//Troca 
					trocas++;
					int aux = vetor[j];
					vetor[j] = vetor[min];
					vetor[min] = aux;
				} 
		} 

		long tempFim = System.currentTimeMillis();

	System.out.println("*****Finalizando Selection Sort*****");

		System.out.println(" ");
		System.out.println("!RELATORIO!:");
		System.out.println("Quantidade de Elementos:" + vetor.length);
		System.out.println("Iteracoes: " + iteracoes );
		System.out.println("Trocas: " + trocas);
		System.out.println("Tempo realizado(milis): " + (tempFim - tempoInicio));

	} 


} 