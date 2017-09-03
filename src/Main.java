import Activities.Activities;
import Activities.ActivitiesPrototypes;
import Activities.IActivity;
import Exceptions.PermissionDeniedException;
import Exceptions.UserNotFoundException;
import Proxy.*;
import Users.*;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * Created by Aluno IC on 01/09/2017.
 */
public class Main {

    static Scanner scannerInt = new Scanner(System.in);
    static Scanner scannerString = new Scanner(System.in);
    static IDatabase db = new ProxyDatabase();
    static ActivitiesPrototypes actprototypes = new ActivitiesPrototypes();

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
    public static void option2() throws UserNotFoundException, PermissionDeniedException {
        System.out.println("Type your CPF");
        String cpf = scannerString.nextLine();
        User u =db.getUser(cpf);
        System.out.println("Type 1 for Class\nType 2 for Presentation\nType 3 for Laboratory\n");
        int tipo = scannerInt.nextInt();
        IActivity activity = null;
        switch (tipo){
            case 1:  activity=actprototypes.getPrototype(Activities.CLASS);break;
            case 2:  activity= actprototypes.getPrototype(Activities.PRESENTATION);break;
            case 3:  activity = actprototypes.getPrototype(Activities.LABORATORY);break;
        }
        activity.isPermitted(u);
        activity.setId(db.getNextActivityId());


      //  activity.setParticipants();


       // Leitura participantes, materiais, localdates;







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
    public static ArrayList<User> readParticipants(){
        ArrayList<User> list = new ArrayList<User>();
        int counter=1;
        int stop=0;

        do {
            System.out.println("Type the cpf of the #"+counter+" participant (x to stop)");
            String cpf = scannerString.nextLine();
            if (!cpf.contains("x")){
                try {
                    list.add(db.getUser(cpf));
                    counter++;
                } catch (UserNotFoundException e) {
                   System.out.println("User not found. Try again.");
                }
            }else{
            stop =1;}
        }while(stop==0);

        return null;
    }

    public static void testezin(){
        for (int i =0; i< 10; i++){

        db.addUser(new Professor("Valdir",i+""));
        }
        System.out.println("Rodando");
        readParticipants();
        scannerString.nextLine();scannerString.nextLine();scannerString.nextLine();scannerString.nextLine();
    }


    public static void main(String[] args) {


        testezin();
        int option;
        do {
            option = showMenu();
            switch (option) {
                case 1: {
                    option1();
                    break;
                }
                case 2: {
                    try{
                    option2();}
                    catch(UserNotFoundException e)
                    {
                        System.out.println("User not found!");
                    } catch (PermissionDeniedException e) {
                        System.out.println("You don't have permission!");
                    }
                    break;
                }
            }
        } while (option != 0);
    }


}
