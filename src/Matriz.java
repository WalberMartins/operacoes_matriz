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
            int[][] matrizSarros = new int[matriz.length][matriz.length+2];

            int c = matriz.length;
            for(int i = 0; i < matriz.length; i++) {
                for(int j = 0; j < matriz[0].length; j++) {
                    matrizSarros[i][j] = matriz[i][j];
                    if(i <= 1) {
                        matrizSarros[j][i+c] = matriz[j][i];
                    }
                }
            }

            int[] produto = {(matrizSarros[0][0] * matrizSarros[1][1] * matrizSarros[2][2]), 
                             (matrizSarros[0][1] * matrizSarros[1][2] * matrizSarros[2][3]),
                             (matrizSarros[0][2] * matrizSarros[1][3] * matrizSarros[2][4]),
                             (matrizSarros[0][4] * matrizSarros[1][3] * matrizSarros[2][2]),
                             (matrizSarros[0][3] * matrizSarros[1][2] * matrizSarros[2][1]),
                             (matrizSarros[0][2] * matrizSarros[1][1] * matrizSarros[2][0])};

            int determinante = produto[0] + produto[1] + produto[2] - produto[3] - produto[4] - produto[5];

            return determinante;
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