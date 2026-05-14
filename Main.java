import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArvoreBinaria arvore = new ArvoreBinaria();

        while (true) {
            System.out.println("\n===== MENU =====");
            System.out.println("1 - Inserir");
            System.out.println("2 - Remover");
            System.out.println("3 - Percurso");
            System.out.println("4 - Nova arvore");
            System.out.println("5 - Sair");
            System.out.print("Escolha: ");

            int opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("Valor: ");
                    arvore.inserir(sc.nextInt());
                    break;

                case 2:
                    System.out.print("Valor: ");
                    arvore.remover(sc.nextInt());
                    break;

                case 3:
                    System.out.println("Qual percurso? (Pre / Em / Pos)");
                    arvore.percurso(sc.next());
                    break;

                case 4:
                    arvore = new ArvoreBinaria();
                    System.out.println("Nova arvore criada!");
                    break;

                case 5:
                    System.out.println("Ate mais!");
                    sc.close();
                    return;

                default:
                    System.out.println("Opcao invalida.");
            }
        }
    }
}
