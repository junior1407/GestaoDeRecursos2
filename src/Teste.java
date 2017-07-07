import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Teste {



	public static int getPosCpf(int[] array_cpfs, int cpf) {
		for (int i = 0; i < array_cpfs.length; i++) {
			if (array_cpfs[i] == cpf) {
				return i;
			}
		}
		return -1;
	}

	public static boolean temPermissoes(int[] array_tipo, int[] array_cpfs,
			int cpf) {
		int pos = getPosCpf(array_cpfs, cpf);
		if (array_tipo[pos] >= 4 && array_tipo[pos] <= 6) {
			return true;
		}
		return false;
	}
	public static boolean isProfessor(int[] array_tipo, int[] array_cpfs,
			int cpf) {
		int pos = getPosCpf(array_cpfs, cpf);
		if (array_tipo[pos]==4) {
			return true;
		}
		return false;
	}

	public static boolean isDisponivel()
	{
		return true;
	}
	public static void main(String[] args) {

		Scanner scannerInt = new Scanner(System.in);
		Scanner scannerString = new Scanner(System.in);
		int contador_usuarios = 0;
		int contador_recursos = 0;



		//int[] quantidades= new int[3]; //0 - Para Lab, 1 - Audit de Apresent, 2  - Sala Normal"-
		/*System.out.println("Quantos labs existem? Minimo 1.");
		quantidades[0] =  scannerInt.nextInt();
		System.out.println("Quantos audisd existem? Minimo 1.");
		 quantidades[1] = scannerInt.nextInt();
		System.out.println("Quantos salas existem? Minimo 1.");
		 quantidades[2] = scannerInt.nextInt();*/
		int[] quantidades= { 2,2,2};
		// Usuario




		String[] usuarios_nome = new String[40];
		int[] usuarios_cpf = new int[40];

		int[] usuarios_tipo = new int[40]; // 1 - Graduando, 2 - Mestrado, 3 -
											// Doutor, 4- Professor,5-
											// Pesquisador, 6 - Admin.

		// Recurso
		String[] recursos_iden = new String[40];
		Calendar[] recursos_dataInicio = new Calendar[40];
		Calendar[] recursos_dataFim = new Calendar[40];
		int[] recursos_cpfResp = new int[40];
		int[] recursos_tipo_atividade= new int[40];
		int[] recursos_sala_atividade = new int[40];
		int[] recursos_estado = new int[40];

		// Atividade

		// Sala de AUla
		// Quantas Salas, Quantos Labs.

		int op = 0;
		do {
			System.out.print("Digite 1- Cadastro de Usuário.\n2- Cadastro de REcurso\n 3 - Alocar REcursos");
			op = scannerInt.nextInt();

			switch (op) {

			case 3: {


				break;
			}
			case 1: {

				System.out.println("Digite seu nome1");
				usuarios_nome[contador_usuarios] = scannerString.nextLine();
				System.out.println("Digite seu cpf");
				usuarios_cpf[contador_usuarios] = scannerInt.nextInt();
				System.out.println("Digite; 1 - Graduando, 2 - Mestrado, 3 - Doutor,4- Professor,5- Pesquisador, 6 - Admin.");
				usuarios_tipo[contador_usuarios] = scannerInt.nextInt();
				contador_usuarios++;

				break;
			}


			case 2: {


				System.out.println("Digite seu cpf");
				int cpf = scannerInt.nextInt();

				if (!temPermissoes(usuarios_tipo, usuarios_cpf, cpf))
				{
					System.out.println("Você não tem permissão.");
				}
				else{

					System.out.println("Digite 0 - Para Lab, 1 - Audit de Apresent, 2  - Sala Normal");
					recursos_tipo_atividade[contador_recursos] = scannerInt.nextInt();
					System.out.println("Qual você quer? (1 a "+quantidades[recursos_tipo_atividade[contador_recursos]]+")");
					recursos_sala_atividade[contador_recursos] = scannerInt.nextInt();
					System.out.println("Digite a identificação do Recurso");
					recursos_iden[contador_recursos] = scannerString.nextLine();
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
					recursos_dataInicio[contador_recursos] = new GregorianCalendar(
							ano, mes - 1, dia, hora_ini, min_ini, 0);
					recursos_dataFim[contador_recursos] = new GregorianCalendar(
							ano, mes - 1, dia, hora_fim, min_fim, 0);
					recursos_estado[contador_recursos] = 0;

					if (isDisponivel())
					{
						contador_recursos++;
					}
					else
					{
						System.out.println("Local já ocupado");
					}

				}break;
			}

			}

		} while (op != 0);

	}

}
