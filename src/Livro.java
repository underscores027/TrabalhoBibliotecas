import aula25_grafos.Grafo;
import lib.ArvoreBinariaExemplo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Livro {
    private String titulo;
    private String autor;
    private int anoPublicacao;
    private String genero;
    public Livro(String titulo, String autor, int anoPublicacao, String genero) {
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
        this.genero = genero;
        // Inicialize outros atributos do livro, se necessário.
    }
    // Métodos de acesso get e set padrões
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }
    public int getAnoPublicacao() {
        return anoPublicacao;
    }
    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }
    public String getGenero() {
        return genero;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }

    //aqui temos cada método de cada um dos 7 possíveis cases, com exceção do case 5, que usa o método de cotém grafo direto.
    public static void adicionarLivro(ArvoreBinariaExemplo<Livro> arvoreLivros, Grafo<Livro> grafoLivros, Scanner scanner) {
        System.out.println("Adicionar Livro:");

        // Solicitar informações do livro ao usuário
        System.out.print("Título do livro: ");
        String titulo = scanner.nextLine();

        System.out.print("Autor do livro: ");
        String autor = scanner.nextLine();

        System.out.print("Ano de publicação: ");
        int anoPublicacao = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha

        System.out.print("Gênero do livro: ");
        String genero = scanner.nextLine();

        // Criar instância do livro com as informações fornecidas
        Livro novoLivro = new Livro(titulo, autor, anoPublicacao, genero);

        // Adicionar livro na árvore
        arvoreLivros.adicionar(novoLivro);

        // Adicionar livro no grafo
        grafoLivros.adicionarVertice(novoLivro);

    }
    public static void excluirLivro(ArvoreBinariaExemplo<Livro> arvoreLivros, Grafo<Livro> grafoLivros, Scanner scanner) {
        System.out.println("Excluir Livro:");

        // Exibir lista de livros registrados
        System.out.println("Livros registrados:");
        for (Livro livroRegistrado : arvoreLivros.obterTodos()) {
            System.out.println("- " + livroRegistrado.getTitulo());
        }

        // Solicitar ao usuário o título do livro a ser excluído
        System.out.print("Digite o título do livro a ser excluído: ");
        String tituloLivroExcluir = scanner.nextLine();

        // Pesquisar o livro na árvore
        Livro livroExcluir = arvoreLivros.pesquisar(new Livro(tituloLivroExcluir, "", 0, ""));

        if (livroExcluir != null) {
            // Remover livro da árvore
            arvoreLivros.remover(livroExcluir);

            // Remover livro do grafo
            grafoLivros.removerVertice(livroExcluir);

            System.out.println("Livro excluído com sucesso: " + livroExcluir.getTitulo());
        } else {
            System.out.println("Livro não encontrado. Certifique-se de digitar um título válido.");
        }
    }
    public static void adicionarDependencia(ArvoreBinariaExemplo<Livro> arvoreLivros, Grafo<Livro> grafoLivros, Scanner scanner) {
        System.out.println("Adicionar Dependência:");

        // Exibir lista de livros registrados
        System.out.println("Livros registrados:");
        for (Livro livroRegistrado : arvoreLivros.obterTodos()) {
            System.out.println("- " + livroRegistrado.getTitulo());
        }

        // Solicitar ao usuário o título do livro de origem
        System.out.print("Digite o título do livro de origem para adicionar a dependência: ");
        String tituloLivroOrigem = scanner.nextLine();

        // Solicitar ao usuário o título do livro de destino
        System.out.print("Digite o título do livro de destino para adicionar a dependência: ");
        String tituloLivroDestino = scanner.nextLine();

        // Pesquisar o livro de origem e de destino na árvore
        Livro livroOrigem = arvoreLivros.pesquisar(new Livro(tituloLivroOrigem, "", 0, ""));
        Livro livroDestino = arvoreLivros.pesquisar(new Livro(tituloLivroDestino, "", 0, ""));

        if (livroOrigem != null && livroDestino != null) {
            // Verificar se não é autodependência
            if (!livroOrigem.equals(livroDestino)) {
                // Adicionar dependência no grafo
                grafoLivros.adicionarAresta(livroOrigem, livroDestino);

                System.out.println("Dependência adicionada com sucesso: " + livroOrigem.getTitulo() + " -> " + livroDestino.getTitulo());
            } else {
                System.out.println("Erro: Não é permitido criar uma dependência para o próprio livro.");
            }
        } else {
            System.out.println("Erro: Livro de origem ou destino não encontrado. Certifique-se de digitar títulos válidos.");
        }
    }
    public static void ExibirLivros(ArvoreBinariaExemplo<Livro> arvoreLivros, Scanner scanner) {
        System.out.println("Livros registrados:");

        List<Livro> livrosOrdenados = arvoreLivros.caminharEmOrdem();

        int pageSize = 10;
        int pageCount = (int) Math.ceil((double) livrosOrdenados.size() / pageSize);

        for (int page = 0; page < pageCount; page++) {
            int start = page * pageSize;
            int end = Math.min(start + pageSize, livrosOrdenados.size());

            for (int i = start; i < end; i++) {
                Livro livro = livrosOrdenados.get(i);
                System.out.println("- Título: " + livro.getTitulo());
                System.out.println("  Autor: " + livro.getAutor());
                System.out.println("  Ano de Publicação: " + livro.getAnoPublicacao());
                System.out.println("  Gênero: " + livro.getGenero());
                System.out.println(); // Linha em branco para separar os livros
            }

            if (page < pageCount - 1) {
                System.out.print("Pressione Enter para ver mais livros...");
                scanner.nextLine(); // Aguarda a entrada do usuário antes de continuar
            }
        }
    }
    public static List<String> ordenacaoTopologicaTitulos(Grafo<Livro> grafo) {
        List<Livro> ordenacaoLivros = grafo.ordenacaoTopologica();
        List<String> ordenacaoTitulos = new ArrayList<>();

        for (Livro livro : ordenacaoLivros) {
            ordenacaoTitulos.add(livro.getTitulo());
        }

        return ordenacaoTitulos;
    }
    public static void consultarLivro(ArvoreBinariaExemplo<Livro> arvoreLivros, Scanner scanner) {
        System.out.println("Consultar Livro:");

        System.out.println("Livros disponíveis no sistema:");
        for (Livro livroDisponivel : arvoreLivros.obterTodos()) {
            System.out.println("- " + livroDisponivel.getTitulo());
        }

        // Solicitar ao usuário o título do livro a ser consultado
        System.out.print("Digite o título do livro a ser consultado: ");
        String tituloLivroConsultar = scanner.nextLine();

        // Pesquisar o livro na árvore
        Livro livroConsultar = arvoreLivros.pesquisar(new Livro(tituloLivroConsultar, "", 0, ""));

        if (livroConsultar != null) {
            // Exibir detalhes do livro
            System.out.println("Detalhes do livro:");
            System.out.println("Título: " + livroConsultar.getTitulo());
            System.out.println("Autor: " + livroConsultar.getAutor());
            System.out.println("Ano de Publicação: " + livroConsultar.getAnoPublicacao());
            System.out.println("Gênero: " + livroConsultar.getGenero());
        } else {
            System.out.println("Livro não encontrado. Certifique-se de digitar um título válido.");
        }
    }
}
