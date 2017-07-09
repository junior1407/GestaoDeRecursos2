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
		// Sala[TIPO] [nÂº SALA] [ Posicao_Registro][dd/mm/aaaa/hh:mm]

		// Atividade
		//

		int[][] contador_atividades = new int[3][MAX]; // [TIPO_SALA][Nº SALA] = Posicao do Contador.

		int[][][][] salas_dataInicio = new int[3][MAX][MAX][5];// [TIPO_SALA] [nº SALA] [POSICAO REGISTRO]
																// [dd/mm/aaaa/hh:mm]
		int[][][][] salas_dataFim = new int[3][MAX][MAX][5];
		int[][][] salas_cpfResp = new int[3][MAX][MAX];
		int[][][][] salas_alunos = new int[3][MAX][MAX][MAX];

		salas_dataInicio[0][0][0][0] = 10;
		salas_dataInicio[0][0][0][1] = 10;
		salas_dataInicio[0][0][0][2] = 10;
		salas_dataInicio[0][0][0][3] = 10;
		salas_dataInicio[0][0][0][4] = 00;

		salas_dataFim[0][0][0][0] = 10;
		salas_dataFim[0][0][0][1] = 10;
		salas_dataFim[0][0][0][2] = 10;
		salas_dataFim[0][0][0][3] = 10;
		salas_dataFim[0][0][0][4] = 59;
		contador_atividades[0][0] = 1;

		usuarios_nome[contador_usuarios] = "Valdir";
		usuarios_cpf[contador_usuarios] = 1;
		usuarios_tipo[contador_usuarios] = 4;
		contador_usuarios++;

		// Recurso
		String[] recursos_iden = new String[MAX];
		// [ID_RECURSO] [NUM_PEDIDO] [0 - dia, 1 - mes, 2 - ano, 3 -hora, 4- minuto]
		int[][][] recursos_dataInicio = new int[3][MAX][5];
		int[][][] recursos_dataFim = new int[3][MAX][5];
		int[][] recursos_estado = new int[MAX][MAX];

		int op = 0;
		do {
			System.out.print(
					"Digite 1- Cadastro de Usuario.\n2- Criar Atividade\n 3 - Alocar REcursos\n 4 - Consulta Completa Atividade");
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
					System.out.println("Qual sala?");
					int sala = scannerInt.nextInt();
					System.out.println("Digite o dia");
					int dia = scannerInt.nextInt();
					System.out.println("Digite o mes");
					int mes = scannerInt.nextInt();
					System.out.println("Digite o ano");
					int ano = scannerInt.nextInt();
					System.out.println("Digite o hora_ini");
					int hora_ini = scannerInt.nextInt();
					System.out.println("Digite o min_ini");
					int min_ini = scannerInt.nextInt();
					System.out.println("Digite o hora_fim");
					int hora_fim = scannerInt.nextInt();
					System.out.println("Digite o min_fim");
					int min_fim = scannerInt.nextInt();

					// int[][][][] salas_dataInicio = new int[3][MAX][MAX][5];// [TIPO_SALA] [nº
					// SALA] [POSICAO REGISTRO]
					int pos = isDisponivel((hora_ini * 60) + min_ini, (hora_fim * 60) + min_fim,
							salas_dataInicio[tipo][sala], salas_dataFim[tipo][sala], dia, mes, ano,
							contador_atividades[tipo][sala]);

					if (pos != -1) {

						/*
						 * 
						 * int[][] contador_atividades = new int[3][MAX]; // [TIPO_SALA][Nº SALA] =
						 * Posicao do Contador.
						 * 
						 * int[][][][] salas_dataInicio = new int[3][MAX][MAX][5];// [TIPO_SALA] [nº
						 * SALA] [POSICAO REGISTRO] // [dd/mm/aaaa/hh:mm] int[][][][] salas_dataFim =
						 * new int[3][MAX][MAX][5]; int[][][][] salas_cpfResp = new
						 * int[3][MAX][MAX][MAX]; int[][][][] salas_alunos = new int[3][MAX][MAX][MAX];
						 * 
						 */
						arrayShift(salas_dataInicio[tipo][sala], contador_atividades[tipo][sala], pos);
						arrayShift(salas_dataFim[tipo][sala], contador_atividades[tipo][sala], pos);
						arrayShift(salas_cpfResp[tipo][sala], contador_atividades[tipo][sala], pos);
						arrayShift(salas_alunos[tipo][sala], contador_atividades[tipo][sala], pos);
						salas_dataInicio[tipo][sala][pos][0] = dia;
						salas_dataInicio[tipo][sala][pos][1] = mes;
						salas_dataInicio[tipo][sala][pos][2] = ano;
						salas_dataInicio[tipo][sala][pos][3] = hora_ini;
						salas_dataInicio[tipo][sala][pos][4] = min_ini;

						salas_dataFim[tipo][sala][pos][0] = dia;
						salas_dataFim[tipo][sala][pos][1] = mes;
						salas_dataFim[tipo][sala][pos][2] = ano;
						salas_dataFim[tipo][sala][pos][3] = hora_fim;
						salas_dataFim[tipo][sala][pos][4] = min_fim;

						salas_cpfResp[tipo][sala][pos] = cpf;

						int sim = 1;
						int n_alun = 0;
						while (sim == 1) {
							System.out.println("Digite o cpf do aluno " + n_alun + 1);
							salas_alunos[tipo][sala][pos][n_alun] = scannerInt.nextInt();
							System.out.println("Digite 1 para inserir mais um aluno");
							n_alun++;
							sim = scannerInt.nextInt();
						}

						System.out.println("O código da sua atividade e " + pos);
						contador_atividades[tipo][sala]++;
						// arrayShift(salas_dataInicio[tipo][sala], 3, 0);
						// arrayShift(salas_dataInicio[tipo][sala], 3, 0);
					} else {
						System.out.println("Hora n disponivel");
					}
				}
				break;
			}
			case 4: {
				System.out.println(" 0 - Aula, 1 - Apresentacao, 2 - Lab");
				int tipo = scannerInt.nextInt();
				System.out.println("Qual sala?");
				int sala = scannerInt.nextInt();
				System.out.println("Qual codigo?");
				int codigo = scannerInt.nextInt();

				System.out.printf("Data: %d/%d/%d\nInicio: %dh%d Fim: %dh%d\nCPF Responsavel: %d\n",
						salas_dataInicio[tipo][sala][codigo][0], salas_dataInicio[tipo][sala][codigo][1],
						salas_dataInicio[tipo][sala][codigo][2], salas_dataInicio[tipo][sala][codigo][3],
						salas_dataInicio[tipo][sala][codigo][4], salas_dataFim[tipo][sala][codigo][3],
						salas_dataFim[tipo][sala][codigo][4], salas_cpfResp[sala][tipo][codigo]);

				break;
			}

			/*
			 * case 7: { System.out.println("Digite seu cpf"); int cpf =
			 * scannerInt.nextInt(); if (!temPermissoes(usuarios_tipo, usuarios_cpf, cpf)) {
			 * System.out.println("VocÃª nÃ£o tem permissÃ£o."); } else{ System.out.
			 * println("Digite 0 - Para Lab, 1 - Audit de Apresent, 2  - Sala Normal");
			 * recursos_tipo_atividade[contador_recursos] = scannerInt.nextInt();
			 * System.out.println("Qual vocÃª quer? (1 a "+quantidades[
			 * recursos_tipo_atividade[contador_recursos]]+")");
			 * recursos_sala_atividade[contador_recursos] = scannerInt.nextInt();
			 * System.out.println("Digite a identificaÃ§Ã£o do Recurso");
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
			 * contador_recursos++; } else { System.out.println("Local jÃ¡ ocupado"); }
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

	public static int isDisponivel(int hora_inicio, int hora_fim, int[][] salas_dataInicio, int[][] salas_dataFim,
			int dia, int mes, int ano, int counter) {
		int curr = 0;
		if (counter == 0) {
			return 0;
		}
		while (curr < counter) {
			// System.out.printf("%d/%d/%d %d:%d", salas_dataInicio[curr][0],
			// salas_dataInicio[curr][1],
			// salas_dataInicio[curr][2], salas_dataInicio[curr][3],
			// salas_dataInicio[curr][4]);

			System.out.printf("Atual Ini%d  FIm%d, veio %d %d\n",
					(salas_dataInicio[curr][3] * 60 + (salas_dataInicio[curr][4])),
					(salas_dataFim[curr][3] * 60 + (salas_dataFim[curr][4])), hora_inicio, hora_fim);

			if (salas_dataInicio[curr][0] == dia && salas_dataInicio[curr][1] == mes
					&& salas_dataInicio[curr][2] == ano) {

				if (((salas_dataInicio[curr][3] * 60 + (salas_dataInicio[curr][4])) > hora_fim)
						&& ((salas_dataInicio[curr][3] * 60 + (salas_dataInicio[curr][4])) > hora_inicio)) {
					System.out.println("Caso1");
					return curr;
					// index = curr;
					// MOVEPARADIREITA(HORAS INI, curr) && MOVEPARADIREITA(HORAS FIM, curr);
					// HOrasINI[curr] = HOrasININOVO;
					// HorasFIM[curr] = HORASFIMNOVO;
					// return index;

				} else if ((salas_dataInicio[curr][3] * 60 + (salas_dataInicio[curr][4])) <= hora_inicio
						&& hora_inicio <= (salas_dataFim[curr][3] * 60 + (salas_dataFim[curr][4]))) {
					System.out.printf("\n%d <= %d   %d<= %d\n",
							(salas_dataInicio[curr][3] * 60 + (salas_dataInicio[curr][4])), hora_inicio, hora_inicio,
							(salas_dataFim[curr][3] * 60 + (salas_dataFim[curr][4])));
					System.out.println("Caso2");
					return -1;

					// 689, 701 . (688, 889)
				} else if (((salas_dataInicio[curr][3] * 60 + (salas_dataInicio[curr][4])) <= hora_fim)
						&& (hora_fim <= (salas_dataFim[curr][3] * 60 + (salas_dataFim[curr][4])))) {
					System.out.println("Caso3");
					return -1;

				} else if (hora_inicio < (salas_dataInicio[curr][3] * 60 + (salas_dataInicio[curr][4]))
						&& hora_fim > (salas_dataFim[curr][3] * 60 + (salas_dataFim[curr][4]))) {
					System.out.println("Caso4");
					return -1;
				}
			}
			curr++;

		}
		System.out.println("Caso5");
		return counter;
	}

	public static void arrayShift(int[] arr, int fim, int start) {
		for (int i = fim - 1; i >= start; i--) {
			arr[i + 1] = arr[i];
		}
	}

	public static void arrayShift(int[][] arr, int fim, int start) {
		for (int i = fim - 1; i >= start; i--) {
			for (int j = 0; j < 5; j++) {
				arr[i + 1][j] = arr[i][j];
			}

		}
	}

}
