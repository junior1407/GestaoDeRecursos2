package State;

import Exceptions.AlreadyConcludedException;
import Exceptions.NotAvailableException;
import Exceptions.PermissionDeniedException;
import Resources.ResourceBooking;
import Users.Permission;
import Users.User;

/**
 * Created by Aluno IC on 01/09/2017.
 */
public class InProcess implements State {
    @Override
    public void changeState(ResourceBooking r, User u) throws AlreadyConcludedException, NotAvailableException, PermissionDeniedException {
        if (u.getPermission()== Permission.ADMIN)
        {
                r.setState(new Allocated());
        }
        else
        {
            throw new PermissionDeniedException();
        }
    }

}
