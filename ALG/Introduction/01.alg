Algoritmo "MaiorValor"
Var
   maiorAltura : inteiro
   altura : inteiro
Inicio
   Escreval("Seja bem-vindo ao algoritmo de cálculo de maiores valores")
   Escreval("[Digite zero para finalizar]")
   Escreval()
   Escreva("Digite o valor da altura (em cm): ")
   Leia(altura)
   Se (altura <> 0) Entao
      maiorAltura <- altura
      Enquanto (altura <> 0) Faca
         Escreva("Digite o valor da altura (em cm): ")
         Leia(altura)
         Se (altura > maiorAltura) Entao
            maiorAltura <- altura
         FimSe
      FimSe
   FimEnquanto
   Escreval()
   Escreval("A maior altura encontrada foi: ", maiorAltura:2:2, " centimetros.")
FimSe
FimAlgoritmo