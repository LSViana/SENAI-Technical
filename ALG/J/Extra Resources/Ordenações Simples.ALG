procedimento bubbleSort()
var
   i, j, troca : inteiro
   iteracoes : inteiro
inicio

   para i de 0 ate tamanho - 1 passo 1 faca

      //Conta iteracoes
      iteracoes <- iteracoes + 1

      escreval("Checando até o índice ", (tamanho - 2 - i))

      para j de 0 ate tamanho - 2 - i passo 1 faca

         //Conta iteracoes
         iteracoes <- iteracoes + 1

         escreval("[",j,"] atual: ", numeros[j], ";[",j+1,"]", "próximo: ", numeros[j+1])

         se (numeros[j + 1] < numeros[j]) entao
            troca <- numeros[j]
            numeros[j] <- numeros[j+1]
            numeros[j+1] <- troca
         fimse
      fimpara
   fimpara
   
   escreval("==========RELATÓRIO================")
   escreval("Número de iterações: ", iteracoes)
   escreval("-----------------------------------")
   escreva("Aperte ENTER para continuar")
   leia(comando)
fimprocedimento

//--------------------------------------------------------------------

procedimento insertionSort()
var
   i, j, troca : inteiro

   iteracoes : inteiro
inicio

   //Percorre do segundo elemento do vetor até o último
   para i de 1 ate tamanho - 1 passo 1 faca
      //Conta iteracoes
      iteracoes <- iteracoes + 1
      
      //j recebe o elemento atual de i
      j <- i

      //Se o anterior do número j for menor que o numero j atual...
      enquanto ((numeros[j-1]) > numeros[j]) faca

         //Conta iteracoes
         iteracoes <- iteracoes + 1

         escreval("[ ",j," ] Atual: ", numeros[j], "; [ ",j," ]Anterior: ", numeros[j-1])

         //Realiza a troca
         troca <- numeros[j]
         numeros[j] <- numeros[j-1]
         numeros[j-1] <- troca
         

         //O j vai para o anterior a ele
         j <- j - 1

         //Se o j for o primeiro significa que ele não tem anteriores para comparar
         //e interrompe o laço
         se (j = 0) entao
            interrompa
         fimse
      fimenquanto

      escreval("-------------------------------------------------")

   fimpara

   escreval("==========RELATÓRIO================")
   escreval("Número de iterações: ", iteracoes)
   escreval("-----------------------------------")
   escreval("Aperte ENTER para continuar")
   leia(comando)

fimprocedimento

//-------------------------------------------------------------------------

procedimento selectionSort()
var
//Iteradores do laço
i, j : inteiro
//Armazena o indice do 'suposto' menor elemento
min : inteiro
troca : inteiro
iteracoes : inteiro
inicio

para j de 0 ate tamanho - 1  passo 1 faca
   //Considera que o primeiro elemento da iteração é o menor
   min <- j
   
   //Conta iteracoes
   iteracoes <- iteracoes + 1
   
   //Testa os elementos depois de j para encontrar o menor
   para i de j + 1 ate tamanho - 1 passo 1 faca
      //Conta iteracoes
      iteracoes <- iteracoes + 1
   
      //Se o elemento atual é menor do que o marcado como menor
      se (numeros[i] < numeros[min]) entao
         //Marca o índice atual (i) como o menor
         min <- i
      fimse
   fimpara
   
   //Caso o minimo trocou (não for mais igual a j)
   se (min <> j) entao
      //Troca o elemento
      troca <- numeros[j]
      numeros[j] <- numeros[min]
      numeros[min] <- troca
   fimse

fimpara

escreval("==================RELATÓRIO======================")
escreval("Número de iterações: ", iteracoes)
escreval("-------------------------------------------------")
escreval("Aperte ENTER para continuar")
leia(comando)

fimprocedimento


//-------------------------------------------------------

procedimento preencherCrescente()
var
   i : inteiro
inicio

   para i de 0 ate 5 passo 1 faca
      numeros[i] <- i
   fimpara

fimprocedimento

procedimento preencherDecrescente()
var
   i : inteiro
inicio

   para i de 0 ate 5 passo 1 faca
      numeros[i] <- 5 - i
   fimpara

fimprocedimento

procedimento preencherRandomico()
var
   i : inteiro
inicio

   para i de 0 ate 5 passo 1 faca
      numeros[i] <- randi(100)
   fimpara

fimprocedimento

procedimento menu()
var
   i : inteiro
inicio
   escreva("{")
   para i de 0 ate tamanho - 1 passo 1 faca
      escreva(numeros[i], ";")
   fimpara
   escreval("}")
   escreval("---------------------------------")

   escreval("[1]. Preencher crescente")
   escreval("[2]. Preencher decrescente")
   escreval("[3]. Preencher randômico")
   escreval("---------------------------")
   escreval("[4]. Bubble sort")
   escreval("[5]. Selection sort")
   escreval("[6]. Insertion sort")
   escreval
   escreval("[0]. Sair")
   escreva("CMD:> ")
fimprocedimento

Algoritmo "INSERTION SORT"
Var
   numeros : vetor[0..5] de inteiro
   tamanho : inteiro
   comando : caractere
Inicio
   tamanho <- 6

   repita

      //Desenhando o vetor
      menu()
      leia(comando)
      limpatela

      escolha (comando)
      caso "1"
         preencherCrescente()
      caso "2"
         preencherDecrescente()
      caso "3"
         preencherRandomico()
      caso "4"
         bubbleSort()
      caso "5"
         selectionSort()
      caso "6"
         insertionSort()
      fimescolha
      
      limpatela

   ate (comando = "0")


Fimalgoritmo