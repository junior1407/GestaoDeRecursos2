package Resources;

import Exceptions.PermissionDeniedException;
import Users.*;

import java.util.ArrayList;

/**
 * Created by Aluno IC on 01/09/2017.
 */
public abstract class IResources<T extends IResources> {


    private int code;
    private T next;
    private static String name;
    private ArrayList<ResourceBooking> bookings;

    public IResources() {
    }

    public IResources(int code, String name) {
        this.code = code;
        this.name = name;
        this.next = null;
        this.bookings = new ArrayList<ResourceBooking>();


}
    public void isPermitted(User u) throws PermissionDeniedException {
        if (!((u.getPermission() == Permission.PROFESSOR)^(u.getPermission() ==  Permission.ADMIN) ^(u.getPermission() == Permission.RESEARCHER)))
        {
            throw new PermissionDeniedException();
        }
    }

}

