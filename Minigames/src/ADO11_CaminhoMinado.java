import java.util.Scanner;
import java.util.Random;

public class ADO11_CaminhoMinado {

	static boolean gameOver = false;
	static int fieldSize, optionField, points = 0, totalBombs = 0;
	static char[] field;
	static int[] bombs;

	static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		Menu();
	}

	public static void Menu() {
		String optionMenu;
		boolean optionMenuPlay = false;
		
		System.out.println("BEM VINDO AO JOGO CAMINHO MINADO SIMPLIFICADO");
		
		do {
			System.out.print("\nJogar\nRegras\nSair\n\nOpção: ");
			optionMenu = input.next();
			
			if(optionMenu.equalsIgnoreCase("Jogar")) {
				Play();
				optionMenuPlay = true;
			}else if(optionMenu.equalsIgnoreCase("Regras") || optionMenu.equalsIgnoreCase("Regra")) {
				System.out.println("\n---Regras---\nNeste jogo o jogador deverá informar o tamanho do Caminho, esse sendo maior do que 1.\nDe acordo com o valor informado ser�o gerados bombas aleatáriamente pelo caminho, sendo correspondentes a metado do tamanho do Caminho.\nQuando escolher uma posição segura será exibido 'X' no lugar de '_',\ne quando escolhar uma posição contendo uma bomba esse '_' mudará para 'B' e será Game Over!");
			}else if(optionMenu.equalsIgnoreCase("Sair")) {
				System.out.println("Obrigado por jogar, espero que volte em breve ;3");
				System.exit(0);
			}else {
				System.err.println("Comando inválido, por favor digite apenas alguma das opções informadas!");
			}
		}while(!optionMenuPlay);
		System.out.println("\nSua pontuação: " + points + " de " + (fieldSize - totalBombs) + " pontos.");
	}
	
	public static void Play() {
		boolean confirm = false;
		
		do {
			System.out.print("\nQual o tamanho deseja para o Caminho Minado? Lembre-se que dever� ser maior do que 1\nTamanho: ");
			fieldSize = input.nextInt();
			
			if(fieldSize > 1) {
				confirm = true;
			}else {
				System.err.println("\nTamanho fora do parâmetro informado, por favor digite novamente.");
			}
		}while(!confirm);
		
		field = new char[fieldSize];
		bombs = new int[fieldSize];
		
		for(int i = 0; i < fieldSize; i++) {
			field[i] = '_';
			bombs[i] = 0;
		}
		
		RandomBombs();
		
		Game();
	}
	
	public static void RandomBombs(){
		boolean fieldValid;
		int fieldBomb, fieldBombSize;
		
		Random random = new Random();
		
		if(fieldSize % 2 != 0) {
			fieldBombSize = (fieldSize - 1) / 2;
		}else {
			fieldBombSize = fieldSize / 2;
		}
		
		for(int i = 0; i < fieldBombSize; i++) {
			do {
				fieldBomb = random.nextInt(fieldSize);
				
				if(field[fieldBomb] == -1) {
					fieldValid = false;
				}else {
					fieldValid = true;
				}
			}while(!fieldValid);
			
			bombs[fieldBomb] = -1;
			totalBombs++;
		}
	}
	
	public static void Game() {
		do {
			System.out.println("\n\n--Caminho Minado--\n");
			
			for(int i = 0; i < fieldSize; i++) {
				System.out.print(field[i] + " ");
			}
			System.out.println("");
			for(int i = 1; i <= fieldSize; i++) {
				System.out.print(i + " ");
			}
			System.out.print("\nEscolha: ");
			optionField = input.nextInt();
			
			TestBombs();
		}while(!gameOver);
		
		System.out.println("\n--Bombas--\n");
		for(int i = 0; i < fieldSize; i++) {
			System.out.print(field[i] + " ");
		}
		System.out.println("");
		for(int i = 1; i <= fieldSize; i++) {
			System.out.print(i + " ");
		}
	}
	
	public static void TestBombs() {
		if(optionField < 1 || optionField > fieldSize) {
			System.err.println("Digite apenas o valor de uma das posições!");
		}else {
			if(bombs[(optionField-1)] == -1) {
				for(int i = 0; i < fieldSize; i++) {
					if(bombs[i] == -1) {
						field[i] = 'B';
					}
				}
				System.err.println("\n\nVoc� pisou em uma Bomba.\n\nGAME OVER!\n");
				gameOver = true;
			}else if(bombs[(optionField-1)] == 1) {
				System.err.println("Você já tentou essa posição, tente outra.");
			}else if(bombs[(optionField-1)] == 0) {
				field[(optionField-1)] = 'X';
				bombs[(optionField-1)] = 1;
				points++;
				
				if((fieldSize - totalBombs) == points) {
					for(int i = 0; i < fieldSize; i++) {
						if(bombs[i] == -1) {
							field[i] = 'B';
						}
					}
					System.err.println("\nParabéns, você ganhou o jogo!");
					gameOver = true;
				}else {
					if(optionField > 1 && optionField < fieldSize){
						if(bombs[optionField-2] == -1 || bombs[optionField] == -1) {
							System.err.println("Cuidado: bomba(s) próxima(s)!");
						}
					}else if(optionField == 1) {
						if(bombs[optionField] == -1) {
							System.err.println("Cuidado: bomba(s) próxima(s)!");
						}
					}
					else if(optionField == fieldSize) {
						if(bombs[optionField-2] == -1) {
							System.err.println("Cuidado: bomba(s) próxima(s)!");
						}
					}
				}
			}
		}
	}	
	
}