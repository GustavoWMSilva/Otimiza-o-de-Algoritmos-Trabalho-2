import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

// Classe Item que implementa Comparator para ordenar itens por valor/peso
class Item implements Comparator<Item> {
    int peso;
    int valor;
    double valorPorPeso;

    public Item() {}

    public Item(int peso, int valor) {
        this.peso = peso;
        this.valor = valor;
        this.valorPorPeso = (double) valor / peso;
    }

    @Override
    public int compare(Item a, Item b) {
        if (b.valorPorPeso > a.valorPorPeso) return 1;
        if (b.valorPorPeso < a.valorPorPeso) return -1;
        return 0;
    }

    @Override
    public String toString() {
        return String.format("Item(peso: %d, valor: %d)", peso, valor);
    }
}

// Classe Node que representa um nó na árvore de decisão
class Node {
    int level;
    int lucroAcumulado;
    int limite;
    int pesoAcumulado;
    List<Item> itensSelecionados;
    boolean[] inclusaoItens;  // Adicionado vetor para rastrear a inclusão dos itens

    // Construtor para inicializar os atributos do nó
    public Node(int level, int lucroAcumulado, int limite, int pesoAcumulado, List<Item> itensSelecionados, boolean[] inclusaoItens) {
        this.level = level;
        this.lucroAcumulado = lucroAcumulado;
        this.limite = limite;
        this.pesoAcumulado = pesoAcumulado;
        this.itensSelecionados = new ArrayList<>(itensSelecionados);
        this.inclusaoItens = Arrays.copyOf(inclusaoItens, inclusaoItens.length); // Copia do vetor de inclusão
    }
}

public class KnapsackBranchAndBound {

    // Método principal para resolver o problema da mochila
    public static void solveP2(int n, int[] wi, int[] vi, int W) {
        Item[] items = new Item[n];
        for (int i = 0; i < n; i++) {
            items[i] = new Item(wi[i], vi[i]);
        }

        // Ordenar itens por valor/peso em ordem decrescente
        // Arrays.sort(items, new Item());
        // System.out.println("Ordenado");

        // Chama o método knapsack para resolver o problema e obter o lucro máximo
        int maxLucro = knapsack(items, W, n);
        
        System.out.println("Max lucroAcumulado: " + maxLucro);
    }

    // Método que implementa o algoritmo Branch and Bound para a mochila
    public static int knapsack(Item[] items, int W, int n) {
        // Fila de prioridade para explorar os nós com maior limite primeiro
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(x -> -x.limite));
        
        // Inicialização dos nós atual e possível filho
        Node atual, possivelFilho;
        boolean[] inclusaoInicial = new boolean[n];  // Vetor de inclusão inicial
        atual = new Node(-1, 0, 0, 0, new ArrayList<>(), inclusaoInicial);  // Nó inicial representando a raiz da árvore de decisão
        possivelFilho = new Node(0, 0, 0, 0, new ArrayList<>(), inclusaoInicial);  // Nó filho
        
        // Adiciona o nó inicial à fila de prioridade
        queue.add(atual);
        
        int maxLucro = 0;  // Variável para armazenar o lucro máximo encontrado
        boolean[] melhorInclusao = new boolean[n];  // Vetor para armazenar a melhor inclusão encontrada
        
        // Loop principal que continua enquanto a fila de prioridade não estiver vazia
        while (!queue.isEmpty()) {
            atual = queue.poll();  // Remove o nó com maior limite da fila
            
            // Se atual.level == -1, é o nó inicial
            if (atual.level == -1) {
                possivelFilho.level = 0;
            }
            
            // Se todos os itens foram considerados, continua para o próximo nó
            if (atual.level == items.length - 1) {
                continue;
            }
            
            possivelFilho.level = atual.level + 1;  // Incrementa o nível do nó filho
            
            // Considera o item atual
            possivelFilho.pesoAcumulado = atual.pesoAcumulado + items[possivelFilho.level].peso;
            possivelFilho.lucroAcumulado = atual.lucroAcumulado + items[possivelFilho.level].valor;
            possivelFilho.itensSelecionados = new ArrayList<>(atual.itensSelecionados);
            possivelFilho.itensSelecionados.add(items[possivelFilho.level]);
            possivelFilho.inclusaoItens = Arrays.copyOf(atual.inclusaoItens, n);
            possivelFilho.inclusaoItens[possivelFilho.level] = true;
            
            // Atualiza o lucro máximo se o nó filho é viável e tem um lucro maior
            if (possivelFilho.pesoAcumulado <= W && possivelFilho.lucroAcumulado > maxLucro) {
                maxLucro = possivelFilho.lucroAcumulado;
                melhorInclusao = Arrays.copyOf(possivelFilho.inclusaoItens, n);
            }
            
            // Calcula o limite para o nó filho
            possivelFilho.limite = limite(possivelFilho, items, W);
            
            // Adiciona o nó filho à fila de prioridade se o limite é promissor
            if (possivelFilho.limite > maxLucro) {
                queue.add(new Node(possivelFilho.level, possivelFilho.lucroAcumulado, possivelFilho.limite, possivelFilho.pesoAcumulado, possivelFilho.itensSelecionados, possivelFilho.inclusaoItens));
            }
            
            // Considera não incluir o item atual
            possivelFilho.pesoAcumulado = atual.pesoAcumulado;
            possivelFilho.lucroAcumulado = atual.lucroAcumulado;
            possivelFilho.itensSelecionados = new ArrayList<>(atual.itensSelecionados);
            possivelFilho.inclusaoItens = Arrays.copyOf(atual.inclusaoItens, n);
            possivelFilho.limite = limite(possivelFilho, items, W);
            
            // Adiciona o nó filho à fila de prioridade se o limite é promissor
            if (possivelFilho.limite > maxLucro) {
                queue.add(new Node(possivelFilho.level, possivelFilho.lucroAcumulado, possivelFilho.limite, possivelFilho.pesoAcumulado, possivelFilho.itensSelecionados, possivelFilho.inclusaoItens));
            }
        }
        
        // Imprime o vetor de inclusão
        System.out.println("Inclusão dos itens: " + Arrays.toString(melhorInclusao));
        // Imprime os itens da sequencia de bits
        for(int i = 0; i< melhorInclusao.length; i++){
            if(melhorInclusao[i]){
                System.out.println(items[i]+" ");
            }
        }
        
        return maxLucro;  // lucro máximo encontrado
    }
    
    // Método para calcular o limite (limite superior) de um nó
    private static int limite(Node atual, Item[] items, int W) {
        // Se o peso do nó já excede a capacidade, o limite é 0
        if (atual.pesoAcumulado >= W) {
            return 0;
        }
        
        int ub = atual.lucroAcumulado;  // Lucro acumulado até agora
        
        int j = atual.level + 1;  // Próximo nível/item a ser considerado
        int totalWeight = atual.pesoAcumulado;  // Peso total acumulado
        
        // Adiciona itens enquanto a capacidade não for excedida
        while (j < items.length && totalWeight + items[j].peso <= W) {
            totalWeight += items[j].peso;
            ub += items[j].valor;
            j++;
        }
        
        // Adiciona a fração do próximo item, se houver
        if (j < items.length) {
            ub += (W - totalWeight) * items[j].valorPorPeso; // ub = vi[i]+(W - w)*(vi[i+1]/wi[i+1]);
        }
        
        return ub;  // limite superior calculado
    }

    public static void main(String[] args) {
        int n = 5;  
        int W = 11;  // Capacidade da mochila
        int wi[] = {7, 6, 2, 1, 5};  // Pesos
        int vi[] = {28, 22, 6, 1, 18};  // Valores

        solveP2(n, wi, vi, W);
    }
}
