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

		int[][][][] salas_dataInicio = new int[3][MAX][MAX][5]; // [TIPO_SALA] [nº SALA] [POSICAO REGISTRO]
		// [dd/mm/aaaa/hh:mm]
		int[][][][] salas_dataFim = new int[3][MAX][MAX][5];
		int[][][] salas_cpfResp = new int[3][MAX][MAX];
		int[][][][] salas_alunos = new int[3][MAX][MAX][MAX];
		int[][][] salas_codigo = new int[3][MAX][MAX];

		usuarios_nome[contador_usuarios] = "Valdir";
		usuarios_cpf[contador_usuarios] = 1;
		usuarios_tipo[contador_usuarios] = 4;
		contador_usuarios++;

		// Recurso
		String[] recursos_iden = new String[MAX];
		// [ID_RECURSO] [NUM_PEDIDO] [0 - dia, 1 - mes, 2 - ano, 3 -hora, 4- minuto]
		int[][][] recursos_dataInicio = new int[3][MAX][5];
		int[][][] recursos_dataFim = new int[3][MAX][5];
		int[][] recursos_estado = new int[MAX][MAX]; // 0 Em processo, 1 -Alocado, 2 - Em andamento, 3 - 4 Concluido.
		int[][] recursos_cpf = new int[MAX][MAX];
		int[][] recursos_codigoAtividade = new int[MAX][MAX];
		int[][] recursos_tipo_sala = new int[MAX][MAX];
		int[][] recursos_num_sala = new int[MAX][MAX];
		contador_recursos = 2; // Numero de projetores.
		recursos_iden[0] = "Projetor 1";
		recursos_iden[1] = "Projetor 2";

		int[] registro_recursos = new int[MAX];

		int op = 0;
		do {
			System.out.print("Digite 1- Cadastro de Usuario.\n2- Criar Atividade\n 3 - Cadastrar Recurso\n"
					+ "4 - Alocar Recurso" + "\n 5 - Consulta Completa Atividade\n"
					+ "6- Mostrar Todos Recursos\n7- Alterar estado Recursos");
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

						arrayShift(salas_dataInicio[tipo][sala], contador_atividades[tipo][sala], pos);
						arrayShift(salas_dataFim[tipo][sala], contador_atividades[tipo][sala], pos);
						arrayShift(salas_cpfResp[tipo][sala], contador_atividades[tipo][sala], pos);
						arrayShift(salas_alunos[tipo][sala], contador_atividades[tipo][sala], pos);
						arrayShift(salas_codigo[tipo][sala], contador_atividades[tipo][sala], pos);

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

						System.out.println("O código da sua atividade e " + contador_atividades[tipo][sala]);
						salas_codigo[tipo][sala][pos] = contador_atividades[tipo][sala];
						contador_atividades[tipo][sala]++;
						// arrayShift(salas_dataInicio[tipo][sala], 3, 0);
						// arrayShift(salas_dataInicio[tipo][sala], 3, 0);
					} else {
						System.out.println("Hora n disponivel");
					}
				}
				break;
			}

			case 3: {
				System.out.println("Digite seu cpf");
				int cpf = scannerInt.nextInt();
				if ((!temPermissoes(usuarios_tipo, usuarios_cpf, cpf))) {
					System.out.println("Voce precisa de mais permissões!");
				} else {
					System.out.println("Digite o nome do recurso");
					recursos_iden[contador_recursos] = scannerString.nextLine();
					System.out.println("Recurso salvo com codigo " + contador_recursos);
					contador_recursos++;
				}
				break;
			}
			case 4: {
				System.out.println("Digite seu cpf");
				int cpf = scannerInt.nextInt();
				if ((!temPermissoes(usuarios_tipo, usuarios_cpf, cpf))) {
					System.out.println("Voce precisa de mais permissões!");
				} else if (hasProjetoEmAndamento(cpf, recursos_cpf, contador_recursos, registro_recursos,
						recursos_estado)) { // Verificar
					System.out.println("Você ja tem um projeto em andamento!");
				} else {
					System.out.println(" 0 - Aula, 1 - Apresentacao, 2 - Lab");
					int tipo = scannerInt.nextInt();
					System.out.println("Qual sala?");
					int sala = scannerInt.nextInt();
					System.out.println("Digite o codigo da atividade");
					int codigo_atividade = scannerInt.nextInt();
					int pos_atividade = getIndex(salas_codigo[tipo][sala], codigo_atividade,
							contador_atividades[tipo][sala]);
					System.out.println("Digite o codigo do recurso");
					int codigo = scannerInt.nextInt();
					int hora_ini = salas_dataInicio[tipo][sala][pos_atividade][3];
					int min_ini = salas_dataInicio[tipo][sala][pos_atividade][4];
					int hora_fim = salas_dataFim[tipo][sala][pos_atividade][3];
					int min_fim = salas_dataFim[tipo][sala][pos_atividade][4];
					int dia = salas_dataInicio[tipo][sala][pos_atividade][0];
					int mes = salas_dataInicio[tipo][sala][pos_atividade][1];
					int ano = salas_dataInicio[tipo][sala][pos_atividade][2];
					int pos = isDisponivel(hora_ini * 60 + (min_ini), (hora_fim * 60) + min_fim,
							recursos_dataInicio[codigo], recursos_dataFim[codigo], dia, mes, ano,
							registro_recursos[codigo]);

					if (pos != -1) {
						arrayShift(recursos_dataInicio[codigo], registro_recursos[codigo], pos);
						arrayShift(recursos_dataFim[codigo], registro_recursos[codigo], pos);
						arrayShift(recursos_cpf[codigo], registro_recursos[codigo], pos);
						arrayShift(recursos_estado[codigo], registro_recursos[codigo], pos);
						arrayShift(recursos_codigoAtividade[codigo], registro_recursos[codigo], pos);

						arrayShift(recursos_tipo_sala[codigo], registro_recursos[codigo], pos);
						arrayShift(recursos_num_sala[codigo], registro_recursos[codigo], pos);
						// recursos tipo sala, recursos num sala

						recursos_tipo_sala[codigo][pos] = tipo;
						recursos_num_sala[codigo][pos] = sala;
						recursos_codigoAtividade[codigo][pos] = codigo_atividade;
						recursos_dataInicio[codigo][pos][0] = dia;
						recursos_dataInicio[codigo][pos][1] = mes;
						recursos_dataInicio[codigo][pos][2] = ano;
						recursos_dataInicio[codigo][pos][3] = hora_ini;
						recursos_dataInicio[codigo][pos][4] = min_ini;

						recursos_dataFim[codigo][pos][0] = dia;
						recursos_dataFim[codigo][pos][1] = mes;
						recursos_dataFim[codigo][pos][2] = ano;
						recursos_dataFim[codigo][pos][3] = hora_fim;
						recursos_dataFim[codigo][pos][4] = min_fim;

						recursos_cpf[codigo][pos] = cpf;
						recursos_estado[codigo][pos] = 0;

						registro_recursos[codigo]++;
					} else {
						System.out.println("O recurso já esta ocupado :) ");
					}

				}

				break;
			}

			case 5: {
				System.out.println(" 0 - Aula, 1 - Apresentacao, 2 - Lab");
				int tipo = scannerInt.nextInt();
				System.out.println("Qual sala?");
				int sala = scannerInt.nextInt();
				System.out.println("Qual codigo da atividade?");
				int codigo_atividade = scannerInt.nextInt();

				int pos_atividade = getIndex(salas_codigo[tipo][sala], codigo_atividade,
						contador_atividades[tipo][sala]);

				System.out.printf("Data: %d/%d/%d\nInicio: %dh%d Fim: %dh%d\nCPF Responsavel: %d\n",
						salas_dataInicio[tipo][sala][pos_atividade][0], salas_dataInicio[tipo][sala][pos_atividade][1],
						salas_dataInicio[tipo][sala][pos_atividade][2], salas_dataInicio[tipo][sala][pos_atividade][3],
						salas_dataInicio[tipo][sala][pos_atividade][4], salas_dataFim[tipo][sala][pos_atividade][3],
						salas_dataFim[tipo][sala][pos_atividade][4], salas_cpfResp[tipo][sala][pos_atividade]);
				int codigo_Recurso;

				System.out.println("Lista de alunos:");
				for (int i = 0; i < MAX && salas_alunos[tipo][sala][pos_atividade][i] != 0; i++) {
					System.out.printf("#%d. CPF: %d\n", i + 1, salas_alunos[tipo][sala][pos_atividade][i]);
				}

				showRecursosAlocados(recursos_codigoAtividade, recursos_iden, contador_recursos, registro_recursos,
						recursos_num_sala, recursos_tipo_sala, tipo, sala, codigo_atividade);
				break;
			}
			case 6: {
				for (int i = 0; i < contador_recursos; i++) {
					System.out.printf("Recurso de Codigo %d - %s\n", i, recursos_iden[i]);
				}
				System.out.println("\n");
				break;
			}

			case 7: {
				System.out.println(" 0 - Aula, 1 - Apresentacao, 2 - Lab");
				int tipo = scannerInt.nextInt();
				System.out.println("Qual sala?");
				int sala = scannerInt.nextInt();
				System.out.println("Qual codigo da atividade?");
				int codigo_atividade = scannerInt.nextInt();
				int pos_atividade = getIndex(salas_codigo[tipo][sala], codigo_atividade,
						contador_atividades[tipo][sala]);
				if (pos_atividade != -1) {
					System.out.println("Qual codigo do recurso?");
					int pos_recurso = scannerInt.nextInt();
					int posicao_recurso_registro = getIndex(recursos_codigoAtividade[pos_recurso], codigo_atividade,
							registro_recursos[pos_recurso]);
					if (posicao_recurso_registro != -1) {
						switch (recursos_estado[pos_recurso][posicao_recurso_registro]) {
						case 0: // EM processo -> Alocado. So adm. E se tiver tudo preenchido.
						case 1: // Alocado -> Em andamento . SO CPF responsavel
						case 2: // Adm, caso Descricao do uso do recurso esteja completa na atividade
						case 3: // Ja concludio. Nada a fazer.

						}

					} else {
						System.out.println("Parece que o recurso não foi encontrado");
					}
				} else {
					System.out.println("Parece que a atividade não foi achada");
				}

				break;

			}

			case 8: {
				// Adicionar comentario do uso do recurso em x atividade.
				break;
			}

			case 9: {

				System.out.println("Digite o cpf do usuario");
				int cpf = scannerInt.nextInt();
				int pos_usuario = getPosCpf(usuarios_cpf, cpf);
				System.out.printf("Nome %s e atua como ", usuarios_nome[pos_usuario]);
				switch (usuarios_tipo[pos_usuario]) {

				// Digite; 1 - Graduando, 2 - Mestrado, 3 - Doutor,4- Professor,5- Pesquisador,
				// 6 - Admin
				case 1: {
					System.out.printf("Aluno de Graduação\n");
					break;
				}
				case 2: {
					System.out.printf("Aluno de Mestrado\n");
					break;
				}
				case 3: {
					System.out.printf("Aluno de Doutorado\n");
					break;
				}
				case 4: {
					System.out.printf("Professor \n");
					break;
				}
				case 5: {
					System.out.printf("Pesquisador\n");
					break;
				}
				case 6: {
					System.out.printf("Administrador\n");
					break;
				}
				}

				// recursos_cpf, recursos_iden, recursos_dataInicio
				int ja_locou = 0;
				for (int i = 0; i < contador_recursos; i++) {
					for (int j = 0; j < registro_recursos[i]; j++) {
						if (recursos_cpf[i][j] == cpf) {
							if (ja_locou == 0) {
								System.out.println("O usuario alocou os seguintes recursos");
								ja_locou = 1;
							}
							System.out.printf("%s %d/%d/%d %d:%d\n", recursos_iden[i], recursos_dataInicio[i][j][0],
									recursos_dataInicio[i][j][1], recursos_dataInicio[i][j][2],
									recursos_dataInicio[i][j][3], recursos_dataInicio[i][j][4]);
							break;
						}
						break;

					}
				}
				if (ja_locou == 0) {
					System.out.println("O usuario não alocou nenhum recurso.");
				}

				for (int i = 0; i < registro_recursos.length; i++) {
					// Atividades que ele participou.
				}

				break;
			}
			case 10: {
				// Consulta por recurso. Dados do recurso, usuarios que já botaram a mao.
				break;

			}
			case 11: {
				System.out.printf("Relatório Completo\nExistem %d usuarios.\n", contador_usuarios);
				int[] contador_estados = new int[4];
				for (int i = 0; i < contador_recursos; i++) {
					for (int j = 0; j < registro_recursos[i]; j++) {
						contador_estados[recursos_estado[i][j]]++;
					}
				}
				System.out.printf("%d Recursos em Processo de Alocacao\n", contador_estados[0]);
				System.out.printf("%d Recursos Alocados\n", contador_estados[1]);
				System.out.printf("%d Recursos em Andamento\n", contador_estados[2]);
				System.out.printf("%d Recuros Concluido\n", contador_estados[3]);
				System.out.printf("%d  Recursos no total\n", contador_recursos);
				int[] contador_atividades_tipo = new int[3];
				for (int i = 0; i <= 2; i++) {
					for (int j = 0; j < quantidades_salas[i]; j++) {
						contador_atividades_tipo[i] += contador_atividades[i][j];
					}
				}
				System.out.printf("Existem %d Atividades em Sala de Aula Normal\n", contador_atividades_tipo[0]);
				System.out.printf("Existem %d Atividades em Auditorios\n", contador_atividades_tipo[1]);
				System.out.printf("Existem %d Atividades em LAB\n\n", contador_atividades_tipo[2]);

				break;
			}
			}

		} while (op != 0);

	}

	private static void showRecursosAlocados(int[][] recursos_codigoAtividade, String[] recursos_iden,
			int length_recursos, int[] length_registro_recursos, int[][] recursos_num_sala, int[][] recursos_tipo_sala,
			int tipo, int sala, int codigo_atividade) {
		// int pos_recurso;
		int contador = 0;

		for (int i = 0; i < length_recursos; i++) {
			for (int j = 0; j < length_registro_recursos[i]; j++) {
				if (recursos_codigoAtividade[i][j] == codigo_atividade && recursos_tipo_sala[i][j] == tipo
						&& recursos_num_sala[i][j] == sala) {
					if (contador == 0) {
						System.out.println("Lista de Recursos:");
					}
					System.out.printf("#%d. %s\n", contador, recursos_iden[i]);
					contador++;
				}
			}
		}

		if (contador == 0) {
			System.out.println("Não foram alocados recursos para essa atividade");
		}

	}

	private static boolean hasProjetoEmAndamento(int cpf, int[][] recursos_cpf, int quantidade_recursos,
			int[] quantidade_registro_recursos, int[][] recursos_estado) {
		for (int i = 0; i < quantidade_recursos; i++) {
			for (int j = 0; j < quantidade_registro_recursos[i]; j++) {
				if (recursos_cpf[i][j] == cpf && recursos_estado[i][j] == 2) {
					return true;
				}
			}
		}

		return false;
	}

	private static int getIndex(int[] arr, int cod, int length) {

		for (int i = 0; i < length; i++) {
			if (arr[i] == cod) {
				return i;
			}
		}
		return -1;
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