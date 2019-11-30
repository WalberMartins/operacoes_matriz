package src;

public class Teste {

    public static void main(String[] args) {
        Matriz matriz = new Matriz(5, 5);
        
        for(int i = 0; i < 25; i++)
            matriz.adicionar((int) (Math.random()*10+1));

        System.out.println(matriz);

        System.out.println(matriz.getDeterminante());
            
    } 

}