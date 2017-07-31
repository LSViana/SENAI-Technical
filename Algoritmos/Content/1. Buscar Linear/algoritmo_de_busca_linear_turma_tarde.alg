Algoritmo "Maior"
Var
alturas : vetor[0..9] de real
i, maiorIndice : inteiro
maiorAltura : real
Inicio
//Recebendo os valores
para i de 0 ate 9 passo 1 faca
   alturas[i] <- i - 10000
fimpara

maiorAltura := alturas[0]

//Verificando maior valor
para i de 1 ate 9 passo 1 faca
   se (maiorAltura < alturas[i]) entao
      maiorAltura <- alturas[i]
      maiorIndice <- i
   fimse
fimpara

escreval("O maior valor é: ", maiorAltura)
escreva("O indice do maior é: ", maiorIndice)
Fimalgoritmo