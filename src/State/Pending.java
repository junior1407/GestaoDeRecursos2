package State;

import Exceptions.AlreadyConcludedException;
import Exceptions.NotAvailableException;
import Exceptions.PermissionDeniedException;
import Resources.ResourceBooking;
import Users.Permission;
import Users.User;

public class Pending implements State {
    @Override
    public void changeState(ResourceBooking r, User u) throws AlreadyConcludedException, NotAvailableException, PermissionDeniedException {

        if (u.getPermission() == Permission.ADMIN){
            if (r.getDescription()!=null || (!r.getDescription().isEmpty()))
            {
                r.setState(new Allocated());
            }
            else
            {
                throw new NotAvailableException();
            }
        }
        else
        {
            throw new PermissionDeniedException();
        }
    }
}
