import Activities.*;
import Exceptions.*;
import Proxy.*;
import Resources.*;
import Resources.Resources;
import Resources.ResourcesPrototypes;
import State.Allocated;
import State.Concluded;
import State.InProcess;
import State.Pending;
import Users.*;
import Utilities.InputProcessor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aluno IC on 01/09/2017.
 */
public class Main {

    static InputProcessor input = new InputProcessor();
    static IDatabase db = new ProxyDatabase();
    /* TODO: "IMPLEMENT SINGLETON" */

    static ActivitiesPrototypes actPrototypes = new ActivitiesPrototypes();
    static ResourcesPrototypes resPrototype = new ResourcesPrototypes();

    public static void option1() {
        System.out.println("Type your name:");
        String name = input.getString(true);
        System.out.println("Type your CPF:");
        String cpf = input.getString(true);
        System.out.println("Type 1 for Admin\n"
                + "Type 2 for Master Degree\n"
                + "Type 3 for PHD\n"
                + "Type 4 for Professor\n"
                + "Type 5 for Researcher\n"
                + "Type 6 for Undergradiate\n:");
        int user_type = input.getInteger("Type an Integer!");
        if (user_type == 1) {
            db.addUser(new Admin(name, cpf));
        }
        if (user_type == 2) {
            db.addUser(new Master(name, cpf));
        }
        if (user_type == 3) {
            db.addUser(new Phd(name, cpf));
        }
        if (user_type == 4) {
            db.addUser(new Professor(name, cpf));
        }
        if (user_type == 5) {
            db.addUser(new Researcher(name, cpf));
        }
        if (user_type == 6) {
            db.addUser(new Undergraduate(name, cpf));
        }
        System.out.println("Sucess!");
    }

    public static void option2() throws UserNotFoundException, PermissionDeniedException, InvalidOptionException {
        System.out.println("Type your CPF");
        String cpf = input.getString(true);
        ;
        User u = db.getUser(cpf);
        System.out.println("Type 1 for ClassAct\nType 2 for Presentation\nType 3 for LaboratoryAct\n");
        int tipo = input.getInteger("Type an Integer!");
        IActivity activity = null;
        switch (tipo) {
            case 1:
                activity = actPrototypes.getPrototype(Activities.CLASS);
                break;
            case 2:
                activity = actPrototypes.getPrototype(Activities.PRESENTATION);
                break;
            case 3:
                activity = actPrototypes.getPrototype(Activities.LABORATORY);
                break;
            default:
                throw new InvalidOptionException();
        }
        activity.isPermitted(u);
        activity.setId(db.getNextActivityId());
        activity.setParticipants(readParticipants());
        activity.setMaterials(readMaterials());
        activity.setResponsible(u);
        System.out.println("Type the start day/time");
        activity.setStart(input.getDate());
        System.out.println("Type the end day/time");
        activity.setEnd(input.getDate());
        db.addActivity(activity);
        System.out.println("Sucess! Your activity id is " + activity.getId());

    }

    public static void option3() throws UserNotFoundException, InvalidOptionException, PermissionDeniedException {
        //prof, resear, admin
        System.out.println("Type your CPF");
        String cpf = input.getString(true);
        ;
        User u = db.getUser(cpf);
        System.out.println("Type 1 for Auditorium\n" +
                "Type 2 for Classroom\nType 3 for LaboratoryAct\n"
                + "Type 4 for Projector\n");
        int tipo = input.getInteger("Type an Integer!");
        IResources resource;
        switch (tipo) {
            case 1:
                resource = resPrototype.getPrototype(Resources.AUDITORIUM);
                break;
            case 2:
                resource = resPrototype.getPrototype(Resources.CLASSROOM);
                break;
            case 3:
                resource = resPrototype.getPrototype(Resources.LABORATORY);
                break;
            case 4:
                resource = resPrototype.getPrototype(Resources.PROJECTOR);
                break;
            default:
                throw new InvalidOptionException();
        }
        resource.isPermitted(u);
        resource.setCode(db.getNextResourceId());
        db.addResource(resource);
        System.out.println("Sucess! Your resource id is " + resource.getCode());
    }


    public static void option4() throws UserNotFoundException, ActivityNotFoundException, PermissionDeniedException, NotAvailableException {
        System.out.println("Type your CPF");
        String cpf = input.getString(true);
        User u = db.getUser(cpf);
        System.out.println("Type the activity's id");
        int id = input.getInteger("Type a valid integer");
        IActivity a = db.getActivity(id);
        a.isPermitted(u);
        IResources r = db.getFirstResource(readResourceType());
        LocalDateTime start = a.getStart();
        LocalDateTime end = a.getEnd();
        String description = input.getString(false);
        ResourceBooking booking = new ResourceBooking(a, u, start, end, description, db.getNextBookingId());
        r = r.isAvaliable(booking);
        booking.setResource(r);
        r.addBooking(booking);
        a.getResources().add(r);
    }

    public static void option5() throws ActivityNotFoundException {
        System.out.println("Type the activity's id");
        int id = input.getInteger("Type a valid integer");
        IActivity a = db.getActivity(id);
        System.out.println(a);

    }

    public static void option6() {
        ArrayList<IResources> resources = db.getResources();
        for(IResources r : resources)
        {
            System.out.println(r);
        }
    }

    public static void option7() throws UserNotFoundException, ActivityNotFoundException, NotAvailableException {
        System.out.println("Type your CPF");
        String cpf = input.getString(true);
        User u = db.getUser(cpf);
        System.out.println("Type the activity's id");
        int id = input.getInteger("Type a valid integer");
        IActivity a = db.getActivity(id);
        a.printResources();
        System.out.println("Type the resource's code");
        int code = input.getInteger("Type a valid integer");
        IResources selected = a.getResources().stream().filter(x -> x.getCode() == code).findFirst().orElse(null);
        ResourceBooking booking = selected.getBooking(id);
        booking.changeState(u);
        db.addBooking(booking);
    }

    public static void option8() throws UserNotFoundException, ActivityNotFoundException, NotAvailableException {
        //description
        System.out.println("Type your CPF");
        String cpf = input.getString(true);
        User u = db.getUser(cpf);
        System.out.println("Type the activity's id");
        int id = input.getInteger("Type a valid integer");
        IActivity a = db.getActivity(id);
        a.printResources();
        System.out.println("Type the resource's code");
        int code = input.getInteger("Type a valid integer");
        IResources selected = a.getResources().stream().filter(x -> x.getCode() == code).findFirst().orElse(null);
        ResourceBooking booking = selected.getBooking(id);
        System.out.println("What is the usage of this resource?");
        String descrip = input.getString(true);
        booking.setDescription(descrip);
    }

    public static void option9() throws UserNotFoundException {

        // consulta de usuario: Todos resource booking, todas activity

        System.out.println("Type your CPF");
        String cpf = input.getString(true);
        User u = db.getUser(cpf);
        List<IActivity> activities= db.getActivities(cpf);
        for(IActivity a : activities)
        {
            System.out.println(a);
            for(IResources r: a.getResources())
            {
                System.out.println(r);
            }
            System.out.println();
        }
    }

    public static void option10() throws ResourceNotFundException {
        // consulta por recurso, dados do recurso, usuario responsavel, atividades que esta sendo usado.
        System.out.println("Type the resource's code");
        int code = input.getInteger("Type a valid integer");
        IResources r = db.getResource(code);
        System.out.println(r);
        for(IActivity a : db.getActivities())
        {
            if (a.getResources().contains(r))// DANDO EXCEPTION NULL POINTER
            {
                System.out.println(a);
            }
        }
    }

    public static void option11() {
        // Relatorio completo. # usuarios, #resourcebooking em cada estado, #ResourceBooking, #Activities by type.
        System.out.printf("#Users : %d\n",db.getUsers().size());
        int allocatted=0, concluded=0, inprocess=0,pending=0;
        for (ResourceBooking b: db.getBookings())
        {
            if (b.getState() instanceof Allocated)
            {
               allocatted++;
            }
            if (b.getState() instanceof Concluded)
            {
                concluded++;
            }
            if(b.getState() instanceof InProcess)
            {
                inprocess++;
            }
            if (b.getState() instanceof Pending)
            {
                pending++;
            }
        }
        System.out.println("There are "+db.getBookings().size()+" bookings");
        System.out.printf("%d Allocated\n%d Concluded\n%d In Process\n%d Pending\n",allocatted,concluded,inprocess,pending);
        int classAct=0, lab=0, presentation=0;
        for(IActivity a: db.getActivities())
        {
            if (a instanceof ClassAct)
            {
                classAct++;
            }
            if (a instanceof LaboratoryAct)
            {
                lab++;
            }
            if (a instanceof Presentation)
            {
                presentation++;
            }
        }
        System.out.printf("%d Class Activities\n%d Laboratory activities\n%d Presentations!", classAct, lab,presentation);
    }

    public static int showMenu() {
        System.out.print("=====MENU=====\nDigite 1 - Cadastro de Usuario."
                + "\nDigite 2 - Criar Atividade\nDigite 3 - Cadastrar Recurso\n"
                + "Digite 4 - Alocar Recurso a Atividade " + "\nDigite 5 - Consulta Completa Atividade\n"
                + "Digite 6 - Mostrar Todos Recursos\nDigite 7 - Alterar estado Recursos\n"
                + "Digite 8 - Comentar uso de Recurso em Atividade\nDigite 9 -Consulta Usu�rio\n"
                + "Digite 10 - Consulta Por Recurso\nDigite 11 - Relatorio completo\nDigite 0 -Sair\n==============\n");
        return input.getInteger("Type a valid integer");
    }

    public static ArrayList<User> readParticipants() {
        ArrayList<User> list = new ArrayList<User>();
        int counter = 1;
        int stop = 0;

        do {
            System.out.println("Type the cpf of the #" + counter + " participant (x to stop)");
            String cpf = input.getString(true);
            if (!cpf.contains("x")) {
                try {
                    list.add(db.getUser(cpf));
                    counter++;
                } catch (UserNotFoundException e) {
                    System.out.println("User not found. Try again.");
                }
            } else {
                stop = 1;
            }
        } while (stop == 0);
        return list;

    }

    public static ArrayList<String> readMaterials() {
        ArrayList<String> list = new ArrayList<String>();
        int counter = 1;
        int stop = 0;

        do {
            System.out.println("Type the #" + counter + " material (-1 to stop)");
            String material = input.getString(true);
            if (!material.contains("-1")) {
                list.add(material);
                counter++;
            } else {
                stop = 1;
            }
        } while (stop == 0);

        return list;
    }

    public static Resources readResourceType() {
        try {
            System.out.println("Type 1 for Auditorium\n" +
                    "Type 2 for Classroom\nType 3 for LaboratoryAct\n"
                    + "Type 4 for Projector\n");
            int tipo = input.getInteger("Type a valid integer!");
            switch (tipo) {
                case 1:
                    return Resources.AUDITORIUM;
                case 2:
                    return Resources.CLASSROOM;
                case 3:
                    return Resources.CLASSROOM;
                case 4:
                    return Resources.PROJECTOR;
                default:
                    throw new InvalidOptionException();
            }
        } catch (InvalidOptionException e) {
            return readResourceType();
        }

    }

    public static void testezin() {
        User u1 = new Professor("Valdir", "0");
        db.addUser(u1);
        User u2 = new Admin("Admin","1" );
        db.addUser(u2);
        IResources r1 = new Auditorium(0, "Auditorium");
        r1.setResponsible(u1);
        IResources r2 = new Auditorium(1, "Auditorium");
        r2.setResponsible(u1);
        r1.setNext(r2);
        db.addResource(r1);
        db.addResource(r2);
        LocalDateTime s1 = LocalDateTime.of(2017, 10, 10, 10, 00);
        LocalDateTime e1 = LocalDateTime.of(2017, 10, 10, 11, 00);
        IActivity a1 = new ClassAct(0, "Class", s1, e1, u1);
        IActivity a2 = new ClassAct(1, "Class", s1.plusHours(2), e1.plusHours(2), u1);
        IActivity a3 = new ClassAct(2, "Class", s1, e1, u1);
        db.addActivity(a1);
        db.addActivity(a2);
        db.addActivity(a3);

        ResourceBooking book1 = new ResourceBooking(a1, u1, s1, e1, " ",0);
        r1.addBooking(book1);
        book1.setResource(r1);
        a1.getResources().add(r1);

        // Atividade 1, 10/10/10 -> 10h00 ->  11h00
        // Atividade 2 10/10/0 ->  12h00 -> 14h00

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
                    try {
                        option2();
                    } catch (UserNotFoundException e) {
                        System.out.println("User not found!");
                    } catch (PermissionDeniedException e) {
                        System.out.println("You don't have permission!");
                    } catch (InvalidOptionException e) {
                        System.out.println("Invalid option!");
                    }
                    break;
                }
                case 3: {
                    try {
                        option3();
                    } catch (UserNotFoundException e) {
                        System.out.println("User not found!");
                    } catch (InvalidOptionException e) {
                        System.out.println("Invalid option!");
                    } catch (PermissionDeniedException e) {
                        System.out.println("You don't have permission!");
                    }
                    break;
                }

                case 4: {

                    try {
                        option4();
                    } catch (UserNotFoundException e) {
                        System.out.println("The user wasn't found!");
                    } catch (ActivityNotFoundException e) {
                        System.out.println("The activity wasn't found!");
                    } catch (PermissionDeniedException e) {
                        System.out.println("You don't have permission!");
                    } catch (NotAvailableException e) {
                        System.out.println("There are no resources!");
                    }

                    break;
                }
                case 5:{
                    try {
                        option5();
                    } catch (ActivityNotFoundException e) {
                        System.out.println("The activity wasn't found!");
                    }
                    break;}

                case 6:{
                    option6();
                    break;}
                case 7: {
                    try {
                        option7();
                    } catch (NotAvailableException e) {
                        System.out.println("There are no resource bookings!");
                    } catch (UserNotFoundException e) {
                        System.out.println("The user wasn't found!");
                    } catch (ActivityNotFoundException e) {
                        System.out.println("The activity wasn't found!");
                    }
                    break;
                }
                case 8:{
                    try {
                        option8();
                    } catch (UserNotFoundException e) {
                        System.out.println("The user wasn't found!");
                    } catch (ActivityNotFoundException e) {
                        System.out.println("The activity wasn't found!");
                    } catch (NotAvailableException e) {
                        System.out.println("There aren't resources!");
                    }
                    break;}
                case 9:{
                    try {
                        option9();
                    } catch (UserNotFoundException e) {
                        System.out.println("The user wasn't found!");
                    }
                    break;}
                case 10:{
                    try {
                        option10();
                    } catch (ResourceNotFundException e) {
                        System.out.println("The resource wasn't found!");
                    }
                    break;}
                case 11:{option11();break;}
            }
        } while (option != 0);
    }


}
