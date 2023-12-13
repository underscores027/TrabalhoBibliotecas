import java.util.Comparator;
public class LivroComparator implements Comparator<Livro> {
    @Override
    public int compare(Livro livro1, Livro livro2) {
        // Comparação com base no título do livro
        String titulo1 = livro1.getTitulo();
        String titulo2 = livro2.getTitulo();

        // Lógica de comparação
        if (titulo1.equals(titulo2)) {
            return 0; // Livros são iguais
        } else if (titulo1.compareTo(titulo2) < 0) {
            return -1; // livro1 é menor que livro2
        } else {
            return 1; // livro1 é maior que livro2
        }
    }
}
