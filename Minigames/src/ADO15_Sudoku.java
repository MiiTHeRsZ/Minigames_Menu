import java.util.Scanner;

public class ADO15_Sudoku {
	static Scanner input = new Scanner(System.in);
	
	public static int vetorDeSudokus[][][] = { { { 1, 2, 3, 4 }, { 4, 3, 2, 1 }, { 3, 4, 1, 2 }, { 2, 1, 4, 3 } },

			{ { 2, 1, 4, 3 }, { 3, 4, 1, 2 }, { 1, 2, 3, 4 }, { 4, 3, 2, 1 } },

			{ { 3, 4, 1, 2 }, { 2, 1, 4, 3 }, { 4, 3, 2, 1 }, { 1, 2, 3, 4 } },

			{ { 4, 3, 1, 2 }, { 1, 2, 4, 3 }, { 3, 4, 2, 1 }, { 2, 1, 3, 4 } } };

	public static void main(String[] args) {
		int matrizDoSudoku[][] = new int[4][4];
		jogarSudoku(matrizDoSudoku);
	}

	public static void sorteiaSudoku(int matriz[][], int vetorDeMatrizes[][][]) {
		int t = (int) Math.random() * vetorDeMatrizes.length;
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
				matriz[i][j] = vetorDeMatrizes[t][i][j];
			}
		}
	}

	public static void imprimeSudoku(int matriz[][]) {
		System.out.println("\n--Sudoku--\n");
		System.out.println("    1   2   3   4");
		System.out.println("  |---|---|---|---|");
		for (int i = 0; i < 4; i++) {
			System.out.print((i+1) + " |");
			for (int j = 0; j < 4; j++) {
				if (matriz[i][j] == 0) {
					System.out.print("   |");
				} else
					System.out.print(" " + matriz[i][j] + " |");
			}
			System.out.println("\n  |---|---|---|---|");
		}
	}

	public static void setaNivelDoSudoku(int nivel, int matriz[][]) {
		int i, j, contador = 0;
		switch (nivel) {
		case 1: // N�vel f�cil
			do {
				i = (int) (Math.random() * 4);
				j = (int) (Math.random() * 4);
				if (matriz[i][j] != 0) {
					matriz[i][j] = 0;
					contador++;
				}
			} while (contador < 4);
			break;
		case 2: // Nível médio
			do {
				i = (int) (Math.random() * 4);
				j = (int) (Math.random() * 4);
				if (matriz[i][j] != 0) {
					matriz[i][j] = 0;
					contador++;
				}
			} while (contador < 8);
			break;
		case 3: // Nível difícil
			do {
				i = (int) (Math.random() * 4);
				j = (int) (Math.random() * 4);
				if (matriz[i][j] != 0) {
					matriz[i][j] = 0;
					contador++;
				}
			} while (contador < 12);
			break;
		default:
			System.out.println("Op��es v�lidas: 1, 2 e 3");
		}
	}

	public static boolean verificaSudoku(int matriz[][]) {
		// Verifica linhas
		for (int i = 0; i < 4; i++)
			if ((matriz[i][0] + matriz[i][1] + matriz[i][2] + matriz[i][3]) != 10)
				return false;
		// Verifica colunas
		for (int j = 0; j < 4; j++)
			if ((matriz[0][j] + matriz[1][j] + matriz[2][j] + matriz[3][j]) != 10)
				return false;
		// Verifica submatriz
		if ((matriz[0][0] + matriz[0][1] + matriz[1][0] + matriz[1][1]) != 10)
			return false;
		// Verifica submatriz
		if ((matriz[0][2] + matriz[0][3] + matriz[1][2] + matriz[1][3]) != 10)
			return false;
		// Verifica submatriz
		if ((matriz[2][0] + matriz[2][1] + matriz[3][0] + matriz[3][1]) != 10)
			return false;
		// Verifica submatriz
		if ((matriz[2][2] + matriz[2][3] + matriz[3][2] + matriz[3][3]) != 10)
			return false;
		return true;
	}

	public static void jogarSudoku(int matriz[][]) {
		Menu(matriz);
	}
	
	public static void Menu(int matriz[][]) {
		String optionMenu;
		boolean optionMenuPlay = false;
		
		System.out.println("BEM VINDO AO JOGO SUDOKU SIMPLIFICADO");
		
		do {
			System.out.print("\nJogar\nRegras\nSair\n\nOpção: ");
			optionMenu = input.next();
			
			if(optionMenu.equalsIgnoreCase("Jogar")) {
				Play(matriz);
				optionMenuPlay = true;
			}else if(optionMenu.equalsIgnoreCase("Regras") || optionMenu.equalsIgnoreCase("Regra")) {
				System.out.println("\n---Regras---\nVocê deverá selecionar a dificuldade, logo após será possóvel visualizar o Sudoku e deverá ser informado nesta ordem: linha, coluno e valor desejado a ser inserido no local.\nAo final será verificado se a solução está correta ou não.");
			}else if(optionMenu.equalsIgnoreCase("Sair")) {
				System.out.println("Obrigado por jogar, espero que volte em breve ;3");
				System.exit(0);
			}else {
				System.err.println("Comando inválido, por favor digite apenas alguma das opções informadas!");
			}
		}while(!optionMenuPlay);
	}
	
	public static void Play(int matriz[][]) {
		int nivel, linha, coluna, valor;
		boolean confirm = false;
		
		do {
			System.out.print("Qual nível de dificuldade deseja?\n1 - Fácil\n2 - Médio\n3 - Difícil\n\nEscolha: ");
			nivel = input.nextInt();
			
			if(nivel == 1 || nivel == 2 || nivel == 3) {
				confirm = true;
			}else {
				System.err.println("Comando inválido, por favor digite apenas alguma das opções informadas!");
			}
		}while(!confirm);
		
		sorteiaSudoku(matriz, vetorDeSudokus);
		setaNivelDoSudoku(nivel, matriz);
		
		confirm = false;
		do {
			do {
				imprimeSudoku(matriz);
				System.out.print("Linha: ");
				linha = input.nextInt();
				System.out.print("Coluna: ");
				coluna = input.nextInt();
				System.out.print("Valor de 1 a 4: ");
				valor = input.nextInt();
				
				if(linha > 0 && linha < 5 && coluna > 0 && coluna < 5 && valor > 0 && valor < 5) {
					if(matriz[linha-1][coluna-1] == 0) {
						matriz[linha-1][coluna-1] = valor;
						
						confirm = true;
					}else {
						System.err.println("Essa posição já está preenchida, tente outra.");
					}
				}else {
					System.err.println("Digite apenas o valor de uma das posições!");
				}
			}while(!confirm);
		}while(!verificaMatrizCompleta(matriz));
		
		if(verificaSudoku(matriz)) {
			System.out.println("Parabéns, a solução do Sudoku está correta!");
		}else {
			System.err.println("A solução está incorreta!");
		}
	}
	
	public static boolean verificaMatrizCompleta(int matriz[][]) {
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				if(matriz[i][j] == 0) {
					return false;
				}
			}
		}
		return true;
	}
}