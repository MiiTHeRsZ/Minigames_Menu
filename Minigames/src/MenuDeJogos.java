import java.util.Scanner;

//Menu para unir todos os outros minijogos feitos pela ADO's
public class MenuDeJogos {
	public static void main(String[] args) {
        String option;
        boolean replay = false;

        Scanner input = new Scanner(System.in);

        do{
            System.out.print("BEM VINDO AO MENU DE MINIJOGOS, DIGITE O DESEJADO PARA PROSEGUIR.\n\n-Caminho Minado\n-Campo Minado\n-Jogo da Velha\n-Sudoku\n\nOpção: ");
            option = input.nextLine();

            if(option.equalsIgnoreCase("Caminho Minado")){
                System.err.println("\n\n-------------------------------\n\n");
                ADO11_CaminhoMinado.main(args);
                /*do{
                    System.err.println("\n\nDeseja voltar ao menu de minijogos? (Sim/Nao)");
                    option = input.next();
                }while(!option.equalsIgnoreCase("Sim") && !option.equalsIgnoreCase("Nao"));
                if(option.equalsIgnoreCase("Sim")){
                    replay = true;
                }else{
                    replay = false;
                }*/
            }else if(option.equalsIgnoreCase("Campo Minado")){
                System.err.println("\n\n-------------------------------\n\n");
                ADO12_CampoMinado.main(args);
            }else if(option.equalsIgnoreCase("Jogo da Velha")){
                System.err.println("\n\n-------------------------------\n\n");
                ADO14_JogoDaVelha.main(args);
            }else if(option.equalsIgnoreCase("Sudoku")){
                System.err.println("\n\n-------------------------------\n\n");
                ADO15_Sudoku.main(args);
            }else{
                System.err.println("\nPalavra errada, digite apenas uma das opções descritas!\n");
                replay = true;
            }
        }while(replay);

        input.close();
    }
}
