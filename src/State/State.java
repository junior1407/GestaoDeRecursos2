package State;

import Exceptions.AlreadyConcludedException;
import Resources.ResourceBooking;

/**
 * Created by Aluno IC on 01/09/2017.
 */
public interface State {

     void  changeState(ResourceBooking r) throws AlreadyConcludedException;

}
