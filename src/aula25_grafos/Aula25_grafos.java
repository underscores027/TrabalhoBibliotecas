package aula25_grafos;

public class Aula25_grafos {
    
    //classe main de teste, inserção de vértices e arestas
    public static void main(String[] args) {
        Grafo<String> grafo = new Grafo<String>();
        grafo.adicionarVertice("1");
        grafo.adicionarVertice("2");
        grafo.adicionarVertice("3");

        
        grafo.adicionarAresta("1", "2");
        grafo.adicionarAresta("2", "3");
        grafo.adicionarAresta("1", "3");
        grafo.adicionarAresta("3", "1");
        grafo.imprimirGrafo();
        
        System.out.println("O grafo é:" + grafo.temCiclo());
        if (grafo.temCiclo()) {
            System.out.println("Não dá para fazer ordenação, pois é um grafo cíclico"); }
        else{
            System.out.println("Uma possível ordenação topológica é:" +  grafo.ordenacaoTopologica());
        }
       
    }
    
}
