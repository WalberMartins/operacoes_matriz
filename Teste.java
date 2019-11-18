public class Teste {

    public static void main(String[] args) {
        Matriz matriz = new Matriz(2, 2);
        
        matriz.adicionar(1);
        matriz.adicionar(2);
        matriz.adicionar(-5);
        matriz.adicionar(-3);

        System.out.println(matriz);

        System.out.println(matriz.getDeterminante());
        

/*        Matriz outraMatriz = new Matriz(6, 4);
        for(int i = 0; i < 24; i++)
            outraMatriz.adicionar(i - 1);


        System.out.println(outraMatriz);

        matriz.multiplicar(outraMatriz);

        System.out.println(matriz); */
    
    } 

}