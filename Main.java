import java.util.Scanner;


public class Main {
   public static void main(String[] args) {
       Scanner sc = new Scanner(System.in);
       ArvoreBinaria arvore = new ArvoreBinaria();


       while (true) {
           System.out.println("\n===== MENU =====");
           System.out.println("1. Inserir valores");
           System.out.println("2. Remover valor");
           System.out.println("3. Percurso");
           System.out.println("4. Nova arvore");
           System.out.println("5. Visualizar arvore");
           System.out.println("6. Sair");
           System.out.print("Escolha uma opcao: ");


           String opcao = sc.nextLine().trim();


           switch (opcao) {
               case "1":
                   System.out.print("Digite os valores para inserir (ex: 10,20,30): ");
                   String entradaInsercao = sc.nextLine().trim();
                   String[] valores = entradaInsercao.split(",");


                   for (String val : valores) {
                       arvore.inserir(Integer.parseInt(val.trim()));
                   }


                   if (valores.length == 1) {
                       System.out.println("\nArvore apos inserir " + entradaInsercao.trim() + ":");
                   } else {
                       System.out.println("\nArvore montada:");
                   }


                   arvore.imprimirArvore();
                   break;


               case "2":
                   System.out.print("Digite os valores para remover (ex: 10,20): ");
                   String entradaRemocao = sc.nextLine().trim();


                   for (String val : entradaRemocao.split(",")) {
                       int valorRemover = Integer.parseInt(val.trim());
                       arvore.remover(valorRemover);
                       System.out.println("\nArvore apos remover " + valorRemover + ":");
                       arvore.imprimirArvore();
                   }
                   break;


               case "3":
                   System.out.print("Qual percurso? (Pre / Em / Pos): ");
                   arvore.percurso(sc.nextLine().trim());
                   break;


               case "4":
                   System.out.print("Deseja iniciar uma nova arvore? (sim / nao): ");
                   if (sc.nextLine().trim().equalsIgnoreCase("sim")) {
                       arvore = new ArvoreBinaria();
                       System.out.print("Digite os valores da nova arvore (ex: 10,20,30): ");
                       String entradaNova = sc.nextLine().trim();
                       String[] valoresNovos = entradaNova.split(",");


                       for (String val : valoresNovos) {
                           arvore.inserir(Integer.parseInt(val.trim()));
                       }


                       System.out.println("\nNova arvore criada com sucesso!");
                       System.out.println("\nArvore montada:");
                       arvore.imprimirArvore();
                   }
                   break;


               case "5":
                   System.out.println("\nArvore atual:");
                   arvore.imprimirArvore();
                   break;


               case "6":
                   System.out.println("Ate mais!");
                   sc.close();
                   return;


               default:
                   System.out.println("Opcao invalida! Tente novamente.");
           }
       }
   }
}
