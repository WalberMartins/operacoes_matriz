package src;

public class Matriz {

    private int[][] matriz;
    private int linha;
    private int coluna;

    public Matriz(int linha, int coluna) {
        matriz = new int[linha][coluna];
        this.linha = 0;
        this.coluna = 0;
    }

    public void adicionar(int num) {
        if(linha == matriz.length) {
            throw new IllegalStateException("Matriz cheia!");
        }
        matriz[linha][coluna] = num;
        if(coluna == (matriz[0].length - 1)) {
            coluna = 0;
            linha++;
        }
        else
            coluna++;
    }

    public int[] buscar(int num) {
        int[] indice = {-1, -1};
        for(int i = 0; i < matriz.length; i++) {
            for(int j = 0; j < matriz[0].length; j++) {
                if(num == matriz[i][j]) {
                    indice[0] = i;
                    indice[1] = j;
                    return indice;
                }
            }  
        }
        return indice;
    }

    public boolean remover(int num) {
        int[] indice = buscar(num);
        if(indice[0] == -1)
            return false;
        for(int i = indice[0]; i <= indice[0]; i++) {
            for(int j = indice[1]; j < matriz[0].length-1; j++) 
                matriz[i][j] = matriz[i][j+1];
            matriz[i][matriz[0].length-1] = 0; 
        }
        
        return true;
    }

    public void somar(Matriz outraMatriz) {
        if(!verificarOrdem(outraMatriz))
            throw new IllegalArgumentException("Matriz de ordem diferente");

        for(int i = 0; i < matriz.length; i++) {
            for(int j = 0; j < matriz[0].length; j++) {
               matriz[i][j] += outraMatriz.matriz[i][j]; 
            }
        }   
    }

    public void subtrair(Matriz outraMatriz) {
        if(!verificarOrdem(outraMatriz))
            throw new IllegalArgumentException("Matriz de ordem diferente");

        for(int i = 0; i < matriz.length; i++) {
            for(int j = 0; j < matriz[0].length; j++) {
                matriz[i][j] -= outraMatriz.matriz[i][j]; 
            }
        }
    }

    public void multiplicar(Matriz outraMatriz) {
        if(!(matriz.length == outraMatriz.matriz[0].length))
            throw new IllegalArgumentException("Impossível multiplicar por essa matriz!");
        
        int[][] novaMatriz = new int[matriz.length][outraMatriz.matriz[0].length];    
        
        for(int i = 0; i < novaMatriz.length; i++) {
            for(int j = 0; j < novaMatriz[0].length; j++) {
                int soma = 0;
                for(int k = 0; k < outraMatriz.matriz.length; k++) {
                    soma += matriz[i][k] * outraMatriz.matriz[k][j];
                }
                novaMatriz[i][j] = soma;
            }
        }
        matriz = novaMatriz;
    }

    public void multiplicarPorEscalar(double escalar) {
        for(int i = 0; i < matriz.length; i++) {
            for(int j = 0; j < matriz[0].length; j++) {
                matriz[i][j] *= escalar; 
            }
        }
    }

    public int getDeterminante() {
        if(matriz.length != matriz[0].length)
            throw new IllegalStateException("A Matriz não é quadrada");
        if(matriz.length == 1)
            return matriz[0][0];
        if(matriz.length == 2) {
            int[] produto = {(matriz[0][0] * matriz[1][1]), (matriz[0][1] * matriz[1][0])};
            return produto[0] - produto[1];
        }
        if(matriz.length == 3) {
            
        }

        return 0;
    }

    private boolean verificarOrdem(Matriz outraMatriz) {
        if(matriz.length == outraMatriz.matriz.length && matriz[0].length == outraMatriz.matriz[0].length)
            return true;
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < matriz.length; i++) {
            for(int j = 0; j < matriz[0].length; j++)
                sb.append(String.format("%-4s ", matriz[i][j]));
            sb.append("\n");
        }
        return sb.toString();
    }

}