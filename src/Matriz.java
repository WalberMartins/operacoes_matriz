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
        if(!verificarMesmaOrdem(outraMatriz))
            throw new IllegalArgumentException("Matriz de ordem diferente");

        for(int i = 0; i < matriz.length; i++) {
            for(int j = 0; j < matriz[0].length; j++) {
               matriz[i][j] += outraMatriz.matriz[i][j]; 
            }
        }   
    }

    public void subtrair(Matriz outraMatriz) {
        if(!verificarMesmaOrdem(outraMatriz))
            throw new IllegalArgumentException("Matriz de ordem diferente");

        for(int i = 0; i < matriz.length; i++) {
            for(int j = 0; j < matriz[0].length; j++) {
                matriz[i][j] -= outraMatriz.matriz[i][j]; 
            }
        }
    }

    private boolean verificarMesmaOrdem(Matriz outraMatriz) {
        if(matriz.length == outraMatriz.matriz.length && matriz[0].length == outraMatriz.matriz[0].length)
            return true;
        return false;
    }

    public void multiplicar(Matriz outraMatriz) {
        if(!(matriz[0].length == outraMatriz.matriz.length))
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
        if(!verificarMatrizQuadrada())
            throw new IllegalStateException("A Matriz não é quadrada");
        if(matriz.length == 1)
            return matriz[0][0];
        if(matriz.length == 2)
            return matriz[0][0] * matriz[1][1] - matriz[0][1] * matriz[1][0];
        
        int determinante = 0;
        for(int i = 0; i <= 0; i++) {
            for(int j = 0; j < matriz[0].length; j++) {
                determinante += matriz[i][j] * getCofator(i, j);
            }
        }
        return determinante;
    }

    private int getCofator(int linha, int coluna) {
        return (int) Math.pow(-1, (linha+coluna)) * getMatrizMenor(linha, coluna).getDeterminante();
    }

    private Matriz getMatrizMenor(int linha, int coluna) {
        Matriz matrizMenor = new Matriz(matriz.length-1, matriz[0].length-1);

        int colunaAux = 0, linhaAux = 0;
        for (int i = 0; i < matriz.length; i++) {
            if (i == linha) 
                continue;
            for (int j = 0; j < matriz[0].length; j++) {
                if (j == coluna) 
                    continue;
                matrizMenor.matriz[linhaAux][colunaAux] = matriz[i][j];
                colunaAux = (colunaAux + 1) % (matriz.length - 1);
                if (colunaAux == 0) 
                    linhaAux++;
            }
        }

        return matrizMenor;
    }

    public boolean verificarSimetria() {
        if(!verificarMatrizQuadrada())
            throw new IllegalStateException("A matriz não é quadrada!");

        int c = 0;
        Matriz matrizTransposta = getTransposta();
        for(int i = 0; i < matriz.length; i++) {
            for(int j = 0; j < matriz.length; j++) {
                if(matriz[i][j] == matrizTransposta.matriz[i][j])
                    c++;
            }
        }

        if(c == matriz.length * matriz[0].length)
            return true;

        return false;
    }

    private boolean verificarMatrizQuadrada() {
        return matriz.length == matriz[0].length;
    }

    private Matriz getTransposta() {
        Matriz matrizTranposta = new Matriz(matriz[0].length, matriz.length);

        for(int i = 0; i < matrizTranposta.matriz.length; i++) {
            for(int j = 0; j < matrizTranposta.matriz[0].length; j++) {
                matrizTranposta.matriz[i][j] = matriz[j][i];
            }
        }

        return matrizTranposta;
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