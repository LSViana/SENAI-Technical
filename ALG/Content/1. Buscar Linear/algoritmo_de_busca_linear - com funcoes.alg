//Função para procurar o maior elemento do vetor
//retorna: O índice do maior elemento
funcao maior() : inteiro
var
maiorIndice, i : inteiro
inicio
   //Marca o primeiro índice como o maior
   maiorIndice <- 0

   //Laço de comparação
   para i de 1 ate 9 passo 1 faca
      se (alturas[i] > alturas[maiorIndice]) entao
         maiorIndice <- i
      fimse
   fimpara
   
   //Retorno da função
   retorne maiorIndice
fimfuncao


Algoritmo "Maior"
Var
alturas : vetor[0..9] de real
i, maior : inteiro
Inicio

//Recebendo os valores
para i de 0 ate 9 passo 1 faca
   //Armazena valor aleatório
   alturas[i] <- randi(10)
fimpara

//Chamando a função e armazenando o retorno
maior <- maior()



//Exibindo informações
escreval("O maior valor esta no índice: ", maior)
escreval("Seu valor é: ", alturas[maior])


Fimalgoritmo