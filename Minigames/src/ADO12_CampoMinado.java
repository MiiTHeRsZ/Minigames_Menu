import java.util.Scanner;
import java.util.Random;

public class ADO12_CampoMinado {

	static boolean gameOver = false;
	static int fieldSize, optionFieldL, optionFieldC, points = 0, totalBombs = 0;
	static char[][] field;
	static int[][] bombs;

	static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		Menu();
	}

	public static void Menu() {
		String optionMenu;
		boolean optionMenuPlay = false;
		
		System.out.println("BEM VINDO AO JOGO CAMPO MINADO SIMPLIFICADO");
		
		do {
			System.out.print("\nJogar\nRegras\nSair\n\nOpção: ");
			optionMenu = input.next();
			
			if(optionMenu.equalsIgnoreCase("Jogar")) {
				Play();
				optionMenuPlay = true;
			}else if(optionMenu.equalsIgnoreCase("Regras") || optionMenu.equalsIgnoreCase("Regra")) {
				System.out.println("\n---Regras---\nNeste jogo o jogador deverá informar o tamanho do Campo, esse sendo maior do que 1. Exmeplo: 2x2\nDe acordo com o valor informado ser�o gerados bombas aleatóriamente pelo campo, sendo corespondentes a metado do tamanho do Campo.\nQuando escolher uma posição segura será exibido 'X' no lugar de '_',\ne quando escolhar uma posição contendo uma bomba esse '_' mudará para 'B' e será Game Over!");
			}else if(optionMenu.equalsIgnoreCase("Sair")) {
				System.out.println("Obrigado por jogar, espero que volte em breve ;3");
				System.exit(0);
			}else {
				System.err.println("Comando inválido, por favor digite apenas alguma das opções informadas!");
			}
		}while(!optionMenuPlay);
		System.out.println("\nSua pontuação: " + points + " de " + ((fieldSize * fieldSize) - totalBombs) + " pontos.");
	}
	
	public static void Play() {
		boolean confirm = false;
		
		do {
			System.out.print("\nQual o tamanho deseja para o Campo Minado? Lembre-se que deverá ser maior do que 1x1\nTamanho: ");
			fieldSize = input.nextInt();
			
			if(fieldSize > 1) {
				confirm = true;
			}else {
				System.err.println("\nTamanho fora do parâmetro informado, por favor digite novamente.");
			}
		}while(!confirm);
		
		field = new char[fieldSize][fieldSize];
		bombs = new int[fieldSize][fieldSize];
		
		for(int i = 0; i < fieldSize; i++) {
			for(int j = 0; j < fieldSize; j++) {
				field[i][j] = '_';
				bombs[i][j] = 0;
			}
		}
		
		RandomBombs();
		
		Game();
	}
	
	public static void RandomBombs(){
		boolean fieldValid;
		int fieldBombL, fieldBombC;
		
		Random random = new Random();
		
		if(fieldSize % 2 != 0) {
			totalBombs = fieldSize * fieldSize / 2 - 1;
		}else {
			totalBombs = fieldSize * fieldSize / 2;
		}
		
		for(int i = 0; i < totalBombs; i++) {
			do {
				fieldBombL = random.nextInt(fieldSize);
				fieldBombC = random.nextInt(fieldSize);
				
				if(field[fieldBombL][fieldBombC] == -1) {
					fieldValid = false;
				}else {
					fieldValid = true;
				}
			}while(!fieldValid);
			
			bombs[fieldBombL][fieldBombC] = -1;
		}
	}
	
	public static void Game() {
		do {
			System.out.println("\n--Campo Minado--\n");
			
			System.out.print("  ");
			for(int i = 1; i <= fieldSize; i++) {
				System.out.print(i + " ");
			}
			System.out.println();
			for(int i = 0; i < fieldSize; i++) {
				System.out.print((i+1) + " ");
				for(int j = 0; j < fieldSize; j++) {
					System.out.print(field[i][j] + " ");
				}
				System.out.println();
			}
			
			System.out.println("\nEscolha ");
			System.out.print("Linha: ");
			optionFieldL = input.nextInt();
			System.out.print("Coluna: ");
			optionFieldC = input.nextInt();
			
			TestBombs();
		}while(!gameOver);
		System.out.println("\n--Bombas--\n");
		System.out.print("  ");
		for(int i = 1; i <= fieldSize; i++) {
			System.out.print(i + " ");
		}
		System.out.println();
		for(int i = 0; i < fieldSize; i++) {
			System.out.print((i+1) + " ");
			for(int j = 0; j < fieldSize; j++) {
				System.out.print(field[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public static void TestBombs() {
		if(optionFieldL < 1 || optionFieldL > fieldSize || optionFieldC < 1 || optionFieldC > fieldSize) {
			System.err.println("Digite apenas o valor de uma das posições!");
		}else {
			if(bombs[(optionFieldL-1)][(optionFieldC-1)] == -1) {
				for(int i = 0; i < fieldSize; i++) {
					for(int j = 0; j < fieldSize; j++) {
						if(bombs[i][j] == -1) {
							field[i][j] = 'B';
						}
					}
				}
				System.err.println("\nVocê pisou em uma Bomba.\n\nGAME OVER!");
				
				gameOver = true;
			}else if(bombs[(optionFieldL-1)][(optionFieldC-1)] == 1) {
				System.err.println("Você já tentou essa posição, tente outra.");
			}else if(bombs[(optionFieldL-1)][(optionFieldC-1)] == 0) {
				field[(optionFieldL-1)][(optionFieldC-1)] = 'X';
				bombs[(optionFieldL-1)][(optionFieldC-1)] = 1;
				points++;
				
				if(((fieldSize * fieldSize) - totalBombs) == points) {
					for(int i = 0; i < fieldSize; i++) {
						for(int j = 0; j < fieldSize; j++) {
							if(bombs[i][j] == -1) {
								field[i][j] = 'B';
							}
						}
					}
					System.err.println("\nParab�ns, vocâ ganhou o jogo!");
					gameOver = true;
				}else {
					if(optionFieldL > 1 && optionFieldL < fieldSize && optionFieldC > 1 && optionFieldC < fieldSize) {
						if(bombs[(optionFieldL-2)][(optionFieldC-2)] == -1 || bombs[optionFieldL-2][optionFieldC-1] == -1 || bombs[(optionFieldL-2)][(optionFieldC)] == -1 || bombs[optionFieldL-1][optionFieldC-2] == -1 || bombs[(optionFieldL-1)][(optionFieldC)] == -1 || bombs[optionFieldL][optionFieldC-2] == -1 || bombs[(optionFieldL)][(optionFieldC-1)] == -1 || bombs[optionFieldL][optionFieldC] == -1) {
							System.err.println("Cuidado: bomba(s) próxima(s)!");
						}
					}else if(optionFieldL == 1) {
						if(optionFieldC > 1 && optionFieldC < fieldSize) {
							if(bombs[optionFieldL-1][optionFieldC-2] == -1 || bombs[optionFieldL-1][optionFieldC] == -1 || bombs[optionFieldL][optionFieldC-2] == -1 || bombs[optionFieldL][optionFieldC-1] == -1 || bombs[optionFieldL][optionFieldC] == -1) {
								System.err.println("Cuidado: bomba(s) próxima(s)!");
							}
						}else if(optionFieldC == 1) {
							if(bombs[optionFieldL-1][optionFieldC] == -1 || bombs[optionFieldL][optionFieldC-1] == -1 || bombs[optionFieldL][optionFieldC] == -1) {
								System.err.println("Cuidado: bomba(s) próxima(s)!");
							}
						}else if(optionFieldC == fieldSize) {
							if(bombs[optionFieldL-1][optionFieldC-2] == -1 || bombs[optionFieldL][optionFieldC-2] == -1 || bombs[optionFieldL][optionFieldC-1] == -1) {
								System.err.println("Cuidado: bomba(s) próxima(s)!");
							}
						}
					}else if(optionFieldL == fieldSize) {
						if(optionFieldC > 1 && optionFieldC < fieldSize) {
							if(bombs[optionFieldL-2][optionFieldC-2] == -1 || bombs[optionFieldL-2][optionFieldC-1] == -1 || bombs[optionFieldL-2][optionFieldC] == -1 || bombs[optionFieldL-1][optionFieldC-2] == -1 || bombs[optionFieldL-1][optionFieldC] == -1) {
								System.err.println("Cuidado: bomba(s) próxima(s)!");
							}
						}else if(optionFieldC == 1) {
							if(bombs[optionFieldL-2][optionFieldC-1] == -1 || bombs[optionFieldL-2][optionFieldC] == -1 || bombs[optionFieldL-1][optionFieldC] == -1) {
								System.err.println("Cuidado: bomba(s) próxima(s)!");
							}
						}else if(optionFieldC == fieldSize) {
							if(bombs[optionFieldL-2][optionFieldC-2] == -1 || bombs[optionFieldL-2][optionFieldC-1] == -1 || bombs[optionFieldL-1][optionFieldC-2] == -1) {
								System.err.println("Cuidado: bomba(s) próxima(s)!");
							}
						}
					}else if(optionFieldC == 1) {
						if(optionFieldL > 1 && optionFieldL < fieldSize) {
							if(bombs[optionFieldL-2][optionFieldC-1] == -1 || bombs[optionFieldL-2][optionFieldC] == -1 || bombs[optionFieldL-1][optionFieldC] == -1 || bombs[optionFieldL][optionFieldC-1] == -1 || bombs[optionFieldL][optionFieldC] == -1) {
								System.err.println("Cuidado: bomba(s) próxima(s)!");
							}
						}
					}else if(optionFieldC == fieldSize) {
						if(optionFieldL > 1 && optionFieldL < fieldSize) {
							if(bombs[optionFieldL-2][optionFieldC-2] == -1 || bombs[optionFieldL-2][optionFieldC-1] == -1 || bombs[optionFieldL-1][optionFieldC-2] == -1 || bombs[optionFieldL][optionFieldC-2] == -1 || bombs[optionFieldL][optionFieldC-1] == -1) {
								System.err.println("Cuidado: bomba(s) próxima(s)!");
							}
						}
					}
				}
			}
		}
	}
		
}