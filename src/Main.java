import aula25_grafos.Grafo;
import lib.ArvoreBinariaExemplo;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArvoreBinariaExemplo<Livro> arvoreLivros = new ArvoreBinariaExemplo<>(new LivroComparator());
        Grafo<Livro> grafoLivros = new Grafo<>();
        Scanner scanner = new Scanner(System.in);

        int escolha;
        do {
            exibirMenu();
            escolha = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do teclado

            switch (escolha) {
                //aqui são todas as escolhas das operações que o usuário pode fazer.
                case 1:
                    // Adicionar livro
                    Livro.adicionarLivro(arvoreLivros, grafoLivros, scanner);
                    break;
                case 2:
                //verificar se há algum livro registrado
                if(arvoreLivros.quantidadeNos() != 0) {
                    // Excluir livro
                    Livro.excluirLivro(arvoreLivros, grafoLivros, scanner);
                }
                else{
                    System.out.println("não há nenhum livro registrado.");
                }
                break;
                case 3:
                    //verificar se há algum livro registrado
                    if(arvoreLivros.quantidadeNos() != 0) {
                        Livro.consultarLivro(arvoreLivros, scanner);
                    }
                    else{
                        System.out.println("não há nenhum livro registrado.");
                    }
                    break;
                case 4:
                    if(arvoreLivros.quantidadeNos() != 0) {
                        // Pesquisar livro
                        Livro.adicionarDependencia(arvoreLivros, grafoLivros, scanner);
                    }
                    else{
                        System.out.println("não há nenhum livro registrado.");
                    }
                    break;
                case 5:
                    //verificar se há algum livro registrado
                    if(arvoreLivros.quantidadeNos() != 0) {
                        // Verificar ciclos de dependências
                        if (grafoLivros.temCiclo()) {
                            System.out.println("há ciclo entre os livros.");
                        } else {
                            System.out.println("não há ciclo entre os livros.");
                        }
                    }
                    else{
                        System.out.println("não há nenhum livro registrado.");
                    }
                    break;
                case 6:
                    //verificar se há algum livro registrado
                    if(arvoreLivros.quantidadeNos() != 0){
                        System.out.println("Uma possível possível de leitura é:" +  Livro.ordenacaoTopologicaTitulos(grafoLivros));
                    }
                    else{
                        System.out.println("não há nenhum livro registrado.");
                    }
                    break;
                case 7:
                    //verificar se há algum livro registrado
                    if(arvoreLivros.quantidadeNos() != 0) {
                        // Exibir livros (navegação)
                        Livro.ExibirLivros(arvoreLivros, scanner);
                    }
                    else{
                        System.out.println("não há nenhum livro registrado.");
                    }
                    break;
                case 0:
                    // Sair do programa
                    System.out.println("Encerrando o programa.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (escolha != 0);

        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("\n=== Menu ===");
        System.out.println("1. Adicionar Livro");
        System.out.println("2. Excluir Livro");
        System.out.println("3. consultar Livro (Navegação)");
        System.out.println("4. Adicionar Dependencia");
        System.out.println("5. Verificar se há ciclos de dependências");
        System.out.println("6. Recomendar uma ordem de leitura dos livros");
        System.out.println("7. Exibir Livros (Navegação)");
        System.out.print("Escolha uma opção: ");
    }
}
