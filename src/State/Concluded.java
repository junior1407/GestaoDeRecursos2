package State;

import Exceptions.AlreadyConcludedException;
import Resources.ResourceBooking;

/**
 * Created by Aluno IC on 01/09/2017.
 */
public class Concluded implements State {

    @Override
    public void changeState(ResourceBooking r) throws AlreadyConcludedException {
        throw new AlreadyConcludedException();
    }
}
