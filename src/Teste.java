import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.Arrays;

public class Teste {

	private static final int MAX = 40;

	public static void main(String[] args) {

		Scanner scannerInt = new Scanner(System.in);
		Scanner scannerString = new Scanner(System.in);
		int contador_usuarios = 0;
		int contador_recursos = 0;
	
 
		int[] quantidades_salas = { 2, 2, 2 }; // [0] - Sala, [1] - Auditorio, [2] - LAB
		String[] usuarios_nome = new String[MAX];
		int[] usuarios_cpf = new int[MAX];
		int[] usuarios_tipo = new int[MAX];
		// 1 - Graduando, 2 - Mestrado, 3 -
		// Doutor, 4- Professor,5-
		// Pesquisador, 6 - Admin.

		// Sala(1-Sala nOrmal, 2-Auditorio, 3 - Lab)
		// Sala[TIPO] [nº SALA] [ Posicao_Registro][dd/mm/aaaa/hh:mm]

		// Atividade
		// 
		 
		int [][]contador_atividades = new int[3][MAX]; //[TIPO_SALA][N� SALA] =  Posicao do Contador. 
		
		int [][][][] salas_dataInicio = new int[3][MAX][MAX][5];//[TIPO_SALA] [n� SALA] [POSICAO REGISTRO] [dd/mm/aaaa/hh:mm]
		int [][][][] salas_dataFim = new int[3][MAX][MAX][5];
		int[][][][] salas_cpfResp = new int[3][MAX][MAX][MAX];
		int[][][][] salas_alunos = new int[3][MAX][MAX][MAX];
	
		
		
		
		// Recurso
		String[] recursos_iden = new String[MAX];
		// [ID_RECURSO] [NUM_PEDIDO] [0 - dia, 1 - mes, 2 - ano, 3 -hora, 4- minuto]
		int [][][] recursos_dataInicio = new int[3][MAX][5];
		int [][][] recursos_dataFim = new int[3][MAX][5];
		int[][] recursos_estado = new int[MAX][MAX];

		int op = 0;
		do {
			System.out.print("Digite 1- Cadastro de Usuario.\n2- Criar Atividade\n 3 - Alocar REcursos");
			op = scannerInt.nextInt();

			switch (op) {

			case 1: {
				System.out.println("Digite seu nome");
				usuarios_nome[contador_usuarios] = scannerString.nextLine();
				System.out.println("Digite seu cpf");
				usuarios_cpf[contador_usuarios] = scannerInt.nextInt();
				System.out.println(
						"Digite; 1 - Graduando, 2 - Mestrado, 3 - Doutor,4- Professor,5- Pesquisador, 6 - Admin.");
				usuarios_tipo[contador_usuarios] = scannerInt.nextInt();
				contador_usuarios++;

				break;
			}
			case 2: {

				System.out.println("Digite seu cpf");
				int cpf = scannerInt.nextInt();
				System.out.println(" 0 - Aula, 1 - Apresentacao, 2 - Lab");
				int tipo = scannerInt.nextInt();
				if ((tipo != 1) && (!isProfessor(usuarios_tipo, usuarios_cpf, cpf))) {
					System.out.println("Voce precisa ser professor!");
				} else {
					System.out.println("Digite o dia"); int dia = scannerInt.nextInt();
					System.out.println("Digite o mes"); int mes = scannerInt.nextInt();
				    System.out.println("Digite o ano"); int ano = scannerInt.nextInt();
					System.out.println("Digite o hora_ini"); int hora_ini = scannerInt.nextInt();
					System.out.println("Digite o min_ini"); int min_ini = scannerInt.nextInt();
					System.out.println("Digite o hora_fim"); int hora_fim = scannerInt.nextInt();
					System.out.println("Digite o min_fim"); int min_fim = scannerInt.nextInt();
				
					// Checa se poss�vel inserir e insere. Guarda pos;
		
							
					//salas_dataInicio = new Gr		
					int sim = 1;
					while(sim==1)
					{
						
						System.out.println("Digite 1 para inserir mais um aluno");
						
					}
					
					
					
				}
				break;
			} /*
				 * case 7: { System.out.println("Digite seu cpf"); int cpf =
				 * scannerInt.nextInt(); if (!temPermissoes(usuarios_tipo, usuarios_cpf, cpf)) {
				 * System.out.println("Você não tem permissão."); } else{ System.out.
				 * println("Digite 0 - Para Lab, 1 - Audit de Apresent, 2  - Sala Normal");
				 * recursos_tipo_atividade[contador_recursos] = scannerInt.nextInt();
				 * System.out.println("Qual você quer? (1 a "+quantidades[
				 * recursos_tipo_atividade[contador_recursos]]+")");
				 * recursos_sala_atividade[contador_recursos] = scannerInt.nextInt();
				 * System.out.println("Digite a identificação do Recurso");
				 * recursos_iden[contador_recursos] = scannerString.nextLine();
				 * System.out.println("Digite o dia"); int dia = scannerInt.nextInt();
				 * System.out.println("Digite o mes"); int mes = scannerInt.nextInt();
				 * System.out.println("Digite o ano"); int ano = scannerInt.nextInt();
				 * System.out.println("Digite o hora_ini"); int hora_ini = scannerInt.nextInt();
				 * System.out.println("Digite o min_ini"); int min_ini = scannerInt.nextInt();
				 * System.out.println("Digite o hora_fim"); int hora_fim = scannerInt.nextInt();
				 * System.out.println("Digite o min_fim"); int min_fim = scannerInt.nextInt();
				 * recursos_dataInicio[contador_recursos] = new GregorianCalendar( ano, mes - 1,
				 * dia, hora_ini, min_ini, 0); recursos_dataFim[contador_recursos] = new
				 * GregorianCalendar( ano, mes - 1, dia, hora_fim, min_fim, 0);
				 * recursos_estado[contador_recursos] = 0;
				 * 
				 * if (isDisponivel()) {
				 * 
				 * 
				 * 
				 * 
				 * 
				 * contador_recursos++; } else { System.out.println("Local já ocupado"); }
				 * 
				 * }break; }
				 */

			}

		} while (op != 0);

	}

	public static int getPosCpf(int[] array_cpfs, int cpf) {
		for (int i = 0; i < array_cpfs.length; i++) {
			if (array_cpfs[i] == cpf) {
				return i;
			}
		}
		return -1;
	}

	public static boolean temPermissoes(int[] array_tipo, int[] array_cpfs, int cpf) {
		int pos = getPosCpf(array_cpfs, cpf);
		if (array_tipo[pos] >= 4 && array_tipo[pos] <= 6) {
			return true;
		}
		return false;
	}

	public static boolean isProfessor(int[] array_tipo, int[] array_cpfs, int cpf) {
		int pos = getPosCpf(array_cpfs, cpf);
		if (array_tipo[pos] == 4) {
			return true;
		}
		return false;
	}

	public static boolean isDisponivel() {
		return true;
	}

}
