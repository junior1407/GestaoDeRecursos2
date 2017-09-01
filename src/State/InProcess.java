package State;

import Exceptions.AlreadyConcludedException;
import Resources.ResourceBooking;

/**
 * Created by Aluno IC on 01/09/2017.
 */
public class InProcess extends State<InProcess> {
    @Override
    public void changeState(ResourceBooking r) throws AlreadyConcludedException {
            r.setState(new Allocated());
    }

}
