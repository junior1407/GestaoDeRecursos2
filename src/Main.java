import Proxy.*;
import Users.*;

import java.util.Scanner;

/**
 * Created by Aluno IC on 01/09/2017.
 */
public class Main {

    static Scanner scannerInt = new Scanner(System.in);
    static Scanner scannerString = new Scanner(System.in);
    static IDatabase db = new ProxyDatabase();

    public static void option1() {
        System.out.println("Type your name:");
        String name = scannerString.nextLine();
        System.out.println("Type your CPF:");
        String cpf = scannerString.nextLine();
        System.out.println("Type 1 for Admin\n"
                + "Type 2 for Master Degree\n"
                + "Type 3 for PHD\n"
                + "Type 4 for Professor\n"
                + "Type 5 for Researcher\n"
                + "Type 6 for Undergradiate\n:");
        int user_type = scannerInt.nextInt();
        if (user_type == 1) {
            db.addUser(new Admin(name, cpf));
        }
        if (user_type == 2) {
            db.addUser(new Master(name, cpf));
        }
        if (user_type == 1) {
            db.addUser(new Phd(name, cpf));
        }
        if (user_type == 1) {
            db.addUser(new Professor(name, cpf));
        }
        if (user_type == 1) {
            db.addUser(new Researcher(name, cpf));
        }
        if (user_type == 1) {
            db.addUser(new Undergraduate(name, cpf));
        }
    }
    public static void option2() {
        System.out.println("Type your CPF");
        int cpf = scannerInt.nextInt();
        System.out.println("Type 1 for ");
        int tipo = scannerInt.nextInt();
    }

    public static int showMenu() {
        System.out.print("=====MENU=====\nDigite 1 - Cadastro de Usuario."
                + "\nDigite 2 - Criar Atividade\nDigite 3 - Cadastrar Recurso\n"
                + "Digite 4 - Alocar Recurso a Atividade " + "\nDigite 5 - Consulta Completa Atividade\n"
                + "Digite 6 - Mostrar Todos Recursos\nDigite 7 - Alterar estado Recursos\n"
                + "Digite 8 - Comentar uso de Recurso em Atividade\nDigite 9 -Consulta Usu�rio\n"
                + "Digite 10 - Consulta Por Recurso\nDigite 11 - Relatorio completo\nDigite 0 -Sair\n==============\n");
        return scannerInt.nextInt();
    }

    public static void main(String[] args) {
        int option;
        do {
            option = showMenu();
            switch (option) {
                case 1: {
                    option1();
                    break;
                }
                case 2: {
                    option2();
                    break;
                }
            }
        } while (option != 0);
    }


}
