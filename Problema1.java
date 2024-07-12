public class Problema1 {

    public static void main(String[] args) {
        int[] l = {10, 1, 10, 10};
        int[] h = {5, 50, 5, 1};
        
        // Calcula o valor máximo do plano
        solveP1(l, h);
    }
    
    // Função para calcular o valor máximo do plano
    public static void solveP1(int[] l, int[] h) {
        int n = l.length;
        int[] maxAcumulado = new int[n + 1]; // Valores máximos acumulados até cada semana
        String[] semana = new String[n + 1]; // Escolhas feitas em cada semana
        String[] semana2 = new String[n + 1]; // Verificar os valores sem trabalho
        maxAcumulado[0] = 0;

        // Inicializa a primeira semana
        if (l[0] > h[0]) {
            maxAcumulado[1] = l[0];
            semana[1] = "baixa";
            semana2[1] = "";

        } else {
            maxAcumulado[1] = h[0];
            semana[1] = "alta";
            semana2[1] = "";
        }
        
        // Loop para calcular os valores maxAcumulado e as escolhas para cada semana subsequente
        for (int i = 2; i <= n; i++) {
            // Verifica se escolher uma tarefa de baixa dificuldade é mais vantajoso
            // Analisa se a tarefa atual fácil e a da semana passada possuem valor maior que a tarefa atual dificil 
            // se maxAcumulado[1] + l[1] > maxAcumulado[0] + h[1] -> na primeira rodada
            if (maxAcumulado[i-1] + l[i-1] > maxAcumulado[i-2] + h[i-1]) {
                maxAcumulado[i] = maxAcumulado[i-1] + l[i-1];
                semana[i] = "baixa";
                semana2[i] = "";
            } else {
                // Caso contrário, escolhe uma tarefa de alta dificuldade e marca a semana anterior como "nada"
                // Faz um backtracking para a semana passada e segue para o ramo que escolheu ficar com
                // fazer nada e uma tarefa difícil
                // maxAcumulado[2] = maxAcumulado[0] + h[1] -> na primeira rodada
                maxAcumulado[i] = maxAcumulado[i-2] + h[i-1];
                semana[i] = "alta";
                semana2[i] = "";
                semana2[i-1] = "nada"; // Marca a semana anterior como "nada"
            }
        }
        
        // Imprime as escolhas feitas em cada semana
        for (int i = 1; i <= n; i++) {
            if (semana2[i].equalsIgnoreCase("nada")  && i+1 <= n && !semana2[i+1].equalsIgnoreCase("nada")) {
                System.out.println("Semana " + i + ": " + semana2[i]);
            }else{
                System.out.println("Semana " + i + ": " + semana[i]);
            }
        }
        
        // Imprime no terminal o valor máximo acumulado
        System.out.println("Valor máximo: " + maxAcumulado[n]);
    }

    
}
