import Exceptions.PermissionDeniedException;
import Proxy.ProxyLabBooking;
import Users.Professor;
import Users.Student;

import java.util.Calendar;

/**
 * Created by Aluno IC on 01/09/2017.
 */
public class Teste2 {

    public static void main(String[] args)
    {
        IResourceBooking b = new ProxyLabBooking();
        try {
            b.schedule(new Professor(), Calendar.getInstance(), Calendar.getInstance());
            System.out.println("Foi1");
            b.schedule(new Student(), Calendar.getInstance(), Calendar.getInstance());
            System.out.println("Foi2");
        } catch (PermissionDeniedException e) {
            e.printStackTrace();
        }

    }

}
