package State;

import Exceptions.AlreadyConcludedException;
import Exceptions.NotAvailableException;
import Exceptions.PermissionDeniedException;
import Resources.ResourceBooking;
import Users.User;

/**
 * Created by Aluno IC on 01/09/2017.
 */
public class Allocated implements State {
    @Override
    public void changeState(ResourceBooking r, User u) throws AlreadyConcludedException, NotAvailableException, PermissionDeniedException {
            if (u == r.getResponsible())
            {
                r.setState(new Pending());
            }
    }
}
