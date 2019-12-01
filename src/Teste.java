package src;

public class Teste {

    public static void main(String[] args) {
        Matriz matriz = new Matriz(2, 2);
        
        matriz.adicionar(-1);
        matriz.adicionar(7);
        matriz.adicionar(3);
        matriz.adicionar(7);
       
        System.out.println(matriz);

        System.out.println(matriz.verificarSimetria());
    } 

}