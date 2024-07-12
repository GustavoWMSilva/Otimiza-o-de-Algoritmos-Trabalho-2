public class exemplo {

    public static void main(String[] args) {
        int n = 4;
        // int h[] = {5,50,5,1};
        // int l[] = {10,1,10,10};
        int h[] = {5,50,1000,1};
        int l[] = {10,1,11,10};
        int resultado[] = funcao(l, h, n);
        int soma = 0;
        for (int i = 0; i < n; i++){
            System.out.println("week "+(i+1)+" "+resultado[i]);
            soma += resultado[i];
        }
        System.out.println("Valor do Plano = "+ soma);
    }
    /* 
    o Problema é que esse algoritmo quando pega uma atividade de alta dificuldade,
    ele concerteza vai pegar uma de baixa deificuldade como próximo
     (se ainda houver próximo).
    Esse problema está de acordo com o pedido no trabalho.
     */ 
    public static int [] funcao(int l[], int h[], int n){
        int i = 0;
        int resultado [] = new int[n];
        while (i < n) {
            System.err.println("passou no inicio");
            if ( i+1 < n && h[i+1] > l[i] + l[i+1]){
                System.err.println("passou no H");
                System.out.println("Choose no job in week i: " + i);
                System.out.println("Choose a high-stress job in week i+1: " + (i + 1));
                resultado[i] = 0;
                resultado[i+1] = h[i+1];
                i += 2;  // é aqui que o problema desse algoritmo
                continue;
            } else{
                System.err.println("passou no L");
                System.out.println("Choose a low-stress job in week i: " + i);
                resultado[i] = l[i];
                i += 1;
                continue;
            }
            
        }

        return resultado;
    }
}