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
		int[][] contador_atividades = new int[3][MAX];

		int[][][][] salas_dataInicio = new int[3][MAX][MAX][5];
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
		String[][] recursos_descricao_atividade = new String[MAX][MAX];
		contador_recursos = 2; // Numero de projetores.

		recursos_iden[0] = "Projetor 1";
		recursos_iden[1] = "Projetor 2";

		int[] registro_recursos = new int[MAX];

		int op = 0;
		do {
			System.out.print("=====MENU=====\nDigite 1 - Cadastro de Usuario."
					+ "\nDigite 2 - Criar Atividade\nDigite 3 - Cadastrar Recurso\n"
					+ "Digite 4 - Alocar Recurso a Atividade " + "\nDigite 5 - Consulta Completa Atividade\n"
					+ "Digite 6 - Mostrar Todos Recursos\nDigite 7 - Alterar estado Recursos\n"
					+ "Digite 8 - Comentar uso de Recurso em Atividade\nDigite 9 -Consulta Usuário\n"
					+ "Digite 10 - Consulta Por Recurso\nDigite 11 - Relatorio completo\nDigite 0 -Sair\n==============\n");
			op = scannerInt.nextInt();

			switch (op) {

			case 1: {
				System.out.println("=====Cadastro de Usuário=====");
				System.out.println("Digite seu Nome:");
				usuarios_nome[contador_usuarios] = scannerString.nextLine();
				System.out.println("Digite seu CPF:");
				usuarios_cpf[contador_usuarios] = scannerInt.nextInt();
				System.out.println(
						"Digite:\n1 - Para Graduando\n2 - Para Mestrado\n3 - Para Doutor\n4 - Para Professor\n5 - Para Pesquisador\n6 - Para Administrador");
				usuarios_tipo[contador_usuarios] = scannerInt.nextInt();
				contador_usuarios++;

				System.out.println("Cadastro de usuário concluído com sucesso!");
				break;
			}
			case 2: {
				System.out.println("=====Reserva de Atividade=====");
				System.out.println("Digite seu CPF");
				int cpf = scannerInt.nextInt();
				System.out.println("Digite o número para qual tipo de sala você quer reservar"
						+ "; 0 - Sala de Aula, 1 - Auditório, 2 - Laboratório");
				int tipo = scannerInt.nextInt();
				if ((tipo != 1) && (!isProfessor(usuarios_tipo, usuarios_cpf, cpf))) {
					System.out.println("Voce precisa ser professor para esse tipo de sala!");
				} else {
					System.out.println("Qual o número da sala que você deseja reservar?");
					int sala = scannerInt.nextInt();
					System.out.println("Digite o dia escolhido");
					int dia = scannerInt.nextInt();
					System.out.println("Digite o mês");
					int mes = scannerInt.nextInt();
					System.out.println("Digite o ano");
					int ano = scannerInt.nextInt();
					System.out.println("Digite o hora de Ínicio da Reserva");
					int hora_ini = scannerInt.nextInt();
					System.out.println("Digite o minuto de Inicio da reserva");
					int min_ini = scannerInt.nextInt();
					System.out.println("Digite o hora de Fim da Reserva");
					int hora_fim = scannerInt.nextInt();
					System.out.println("Digite o minuto de Fim da Reserva");
					int min_fim = scannerInt.nextInt();

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
							System.out.println("Digite o CPF do aluno " + n_alun + 1);
							salas_alunos[tipo][sala][pos][n_alun] = scannerInt.nextInt();
							System.out.println("Digite 1 para inserir mais um aluno");
							n_alun++;
							sim = scannerInt.nextInt();
						}

						System.out.println("O código da sua atividade e " + contador_atividades[tipo][sala]);
						salas_codigo[tipo][sala][pos] = contador_atividades[tipo][sala];
						contador_atividades[tipo][sala]++;

					} else {
						System.out.println("Horário não disponivel");
					}
				}
				break;
			}

			case 3: {

				System.out.println("=====Cadastro de Recurso=====");
				System.out.println("Digite seu cpf");
				int cpf = scannerInt.nextInt();
				if ((!temPermissoes(usuarios_tipo, usuarios_cpf, cpf))) {
					System.out.println("Voce precisa ser Professor, Pesquisador ou Administrador do Sistema!");
				} else {
					System.out.println("Digite o nome do novo recurso");
					recursos_iden[contador_recursos] = scannerString.nextLine();
					System.out.println("Recurso salvo com codigo " + contador_recursos);
					contador_recursos++;

				}
				break;
			}
			case 4: {
				System.out.println("=====Alocação de Recursos =====");
				System.out.println("Digite seu CPF");
				int cpf = scannerInt.nextInt();
				if ((!temPermissoes(usuarios_tipo, usuarios_cpf, cpf))) {
					System.out.println("Voce precisa ser Professor, Pesquisador ou Administrador do Sistema!!");
				} else if (hasProjetoEmAndamento(cpf, recursos_cpf, contador_recursos, registro_recursos,
						recursos_estado)) { // Verificar
					System.out.println("Você ja tem uma atividade em andamento!");
				} else {
					System.out.println("Digite: 0 - Sala de Aula, 1 - Auditório, 2 - Laboratório");
					int tipo = scannerInt.nextInt();
					System.out.println("Qual o numero da sala?");
					int sala = scannerInt.nextInt();
					System.out.println("Digite o codigo da atividade");
					int codigo_atividade = scannerInt.nextInt();
					int pos_atividade = getIndex(salas_codigo[tipo][sala], codigo_atividade,
							contador_atividades[tipo][sala]);
					if (salas_cpfResp[tipo][sala][pos_atividade] == cpf) {
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
							arrayShift(recursos_descricao_atividade[codigo], registro_recursos[codigo], pos);
							// recursos tipo sala, recursos num sala

							recursos_descricao_atividade[codigo][pos] = null;
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
							System.out.println("Recurso alocado com sucesso!");
						} else {
							System.out.println("O recurso já esta ocupado! ");
						}

					} else {
						System.out.println("Voce não e o responsavel pela atividade");
					}

				}

				break;
			}

			case 5: {

				System.out.println("=====Consulta de Atividade=====");
				System.out.println("Digite: 0 - Sala de Aula, 1 - Auditório, 2 - Laboratório");
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
						recursos_num_sala, recursos_tipo_sala, tipo, sala, codigo_atividade,
						recursos_descricao_atividade);
				break;
			}
			case 6: {
				System.out.println("=====Todos Recursos=====");
				for (int i = 0; i < contador_recursos; i++) {
					System.out.printf("Recurso de Codigo %d - %s\n", i, recursos_iden[i]);
				}
				System.out.printf("\n");
				break;
			}

			case 7: {
				System.out.println("=====Alteração Estados Recursos=====");
				System.out.println("Digite o seu CPF");
				int cpf = scannerInt.nextInt();
				int pos_usuario = getPosCpf(usuarios_cpf, cpf);
				System.out.println("Digite: 0 - Sala de Aula, 1 - Auditório, 2 - Laboratório");
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
						case 0: {
							if (usuarios_tipo[pos_usuario] == 6) // admin
							{
								System.out.println(
										"Digite 1 para confirmar a transferencia de \"Em processo de Alocacao\" para \"Alocado\"?");
								int resposta = scannerInt.nextInt();
								if (resposta == 1) {
									recursos_estado[pos_recurso][posicao_recurso_registro]++;
									System.out.println("Movido com sucesso");
								}

							} else {
								System.out.println("Voce precisa ser Administrador");
							}
							break;
						} // EM processo -> Alocado. So adm. E se tiver tudo preenchido.
						case 1: {
							if (recursos_cpf[pos_recurso][posicao_recurso_registro] == cpf) {
								System.out.println(
										"Digite 1 para confirmar a transferencia de \"Alocado\" para \"Em andamento\"?");
								int resposta = scannerInt.nextInt();
								if (resposta == 1) {
									recursos_estado[pos_recurso][posicao_recurso_registro]++;
									System.out.println("Movido com sucesso");
								}
							} else {
								System.out.println("E preciso ser o responsavel pelo recurso");
							}
							break;
						} // Alocado -> Em andamento . SO CPF responsavel

						case 2: {
							if (usuarios_tipo[pos_usuario] == 6) // admin
							{
								if (true) { // If Tiver preenchido descricao do uso do recurso na atividade.
									System.out.println(
											"Digite 1 para confirmar a transferencia de \"Alocado\" para \"Concluido\"?");
									int resposta = scannerInt.nextInt();
									if (resposta == 1) {
										recursos_estado[pos_recurso][posicao_recurso_registro]++;
										System.out.println("Movido com sucesso");
									}
								}

							} else {
								System.out.println("Voce precisa ser o Administrador");
							}

							break;
						} // Adm, caso Descricao do uso do recurso esteja completa na atividade
						case 3: // Ja concludio. Nada a fazer.
							System.out.println("O processo ja se encontra concluido. Nao ha nada a fazer");
							break;
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
				// Adicionar comentario do uso do recurso em x atividade.x
				System.out.println("=====Inserção uso do Recurso=====");
				System.out.println("Digite o seu CPF");
				int cpf = scannerInt.nextInt();
				int pos_usuario = getPosCpf(usuarios_cpf, cpf);
				System.out.println(" Digite: 0 - Sala de Aula, 1 - Auditório, 2 - Laboratório");
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
					if (recursos_cpf[pos_recurso][posicao_recurso_registro] == cpf) { // If vc é o responsavel
						if (posicao_recurso_registro != -1) {
							System.out.println("Digite a descricao do uso do recurso");
							recursos_descricao_atividade[pos_recurso][posicao_recurso_registro] = scannerString
									.nextLine();
						} else {
							System.out.println("Recurso não ligado a essa atividade");
						}
					} else {
						System.out.println("Voce não é o responsavel pela atividade");
					}

				} else {
					System.out.println("Atividade não encontrada");
				}

				break;
			}

			case 9: {
				System.out.println("=====Informações de Usuario=====");
				System.out.println("Digite o cpf do usuario");
				int cpf = scannerInt.nextInt();
				int pos_usuario = getPosCpf(usuarios_cpf, cpf);
				System.out.printf("Nome %s e atua como ", usuarios_nome[pos_usuario]);
				switch (usuarios_tipo[pos_usuario]) {
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

					}
				}
				if (ja_locou == 0) {
					System.out.println("O usuario não alocou nenhum recurso.");
				}

				int responsavel = 0;

				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < quantidades_salas[i]; j++) {
						for (int k = 0; k < contador_atividades[i][j]; k++) {

							if (salas_cpfResp[i][j][k] == cpf) {
								if (responsavel == 0) {
									responsavel = 1;
									System.out.println("O usuario ja coordenou seguintes atividades");

								}
								
								System.out.printf(
										"Tipo de Sala %d, Numero da sala %d, Codigo da Atividade %d, Inicio %d/%d/%d %d:%d, Fim %d/%d/%d %d:%d\n",
										i, j, salas_codigo[i][j][k],
										salas_dataInicio[i][j][k][0], salas_dataInicio[i][j][k][1],
										salas_dataInicio[i][j][k][2], salas_dataInicio[i][j][k][3],
										salas_dataInicio[i][j][k][4], salas_dataFim[i][j][k][0],
										salas_dataFim[i][j][k][1], salas_dataFim[i][j][k][2],
										salas_dataFim[i][j][k][3], salas_dataFim[i][j][k][4]);

							}
						}
					}
				}

				int participou = 0;
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < quantidades_salas[i]; j++) {
						for (int k = 0; k < contador_atividades[i][j]; k++) {
							for (int l = 0; l < MAX; l++) {

								if (salas_alunos[i][j][k][l] == cpf) {

									if (participou == 0) {
										participou = 1;
										System.out.println("O usuario ja participou das seguintes atividades");
									}

									System.out.printf(
											"Tipo de Sala %d, Numero da sala %d, Codigo da Atividade %d, CPF_Responsavel %d, Inicio %d/%d/%d %d:%d, Fim %d/%d/%d %d:%d\n",
											i, j, salas_codigo[i][j][k], salas_cpfResp[i][j][k],
											salas_dataInicio[i][j][k][0], salas_dataInicio[i][j][k][1],
											salas_dataInicio[i][j][k][2], salas_dataInicio[i][j][k][3],
											salas_dataInicio[i][j][k][4], salas_dataFim[i][j][k][0],
											salas_dataFim[i][j][k][1], salas_dataFim[i][j][k][2],
											salas_dataFim[i][j][k][3], salas_dataFim[i][j][k][4]);

								}
							}
						}
					}
				}

				break;
			}
			case 10: {
				System.out.println("=====Informações de Recurso=====");
				System.out.println("Digite o codigo do recurso");
				int codigo_recurso = scannerInt.nextInt();
				System.out.printf("Identificação:  %s\n", recursos_iden[codigo_recurso]);
				if (registro_recursos[codigo_recurso] > 0) {
					System.out.println("Historico de Alocacoes");
				}
				// recursos_cpf, recursos_dataInicio, recursos_dataFim,
				// recursos_estado, recursos_num_sala,codigo_recurso
				for (int i = 0; i < registro_recursos[codigo_recurso]; i++) {
					System.out.printf(
							"Alocacao %d:\nCPF Responsavel %d, Estado Locacao %d,"
									+ " Tipo Sala %d, Num Sala %d\nInicio %d/%d/%d %d:%d, Fim %d/%d/%d %d:%d\n",
							i + 1, recursos_cpf[codigo_recurso][i], recursos_estado[codigo_recurso][i],
							recursos_tipo_sala[codigo_recurso][i], recursos_num_sala[codigo_recurso][i],
							recursos_dataInicio[codigo_recurso][i][0], recursos_dataInicio[codigo_recurso][i][1],
							recursos_dataInicio[codigo_recurso][i][2], recursos_dataInicio[codigo_recurso][i][3],
							recursos_dataInicio[codigo_recurso][i][4], recursos_dataFim[codigo_recurso][i][0],
							recursos_dataFim[codigo_recurso][i][1], recursos_dataFim[codigo_recurso][i][2],
							recursos_dataFim[codigo_recurso][i][3], recursos_dataFim[codigo_recurso][i][4]);
				}
				// Consulta por recurso. Dados do recurso, usuarios que já botaram a mao.
				break;

			}
			case 11: {
				System.out.println("=====Relatório Completo=====");
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
		scannerInt.close();
		scannerString.close();
	}

	private static void arrayShift(String[] arr, int fim, int start) {
		for (int i = fim - 1; i >= start; i--) {
			arr[i + 1] = arr[i];
		}

	}

	private static void showRecursosAlocados(int[][] recursos_codigoAtividade, String[] recursos_iden,
			int length_recursos, int[] length_registro_recursos, int[][] recursos_num_sala, int[][] recursos_tipo_sala,
			int tipo, int sala, int codigo_atividade, String[][] recursos_descricao_atividade) {
		// int pos_recurso;
		int contador = 0;

		for (int i = 0; i < length_recursos; i++) {
			for (int j = 0; j < length_registro_recursos[i]; j++) {
				if (recursos_codigoAtividade[i][j] == codigo_atividade && recursos_tipo_sala[i][j] == tipo
						&& recursos_num_sala[i][j] == sala) {
					if (contador == 0) {
						System.out.println("Lista de Recursos:");
					}
					System.out.printf("#%d. %s. Uso: %s\n", contador, recursos_iden[i], recursos_codigoAtividade[i][j]);
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