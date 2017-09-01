package Resources;


import State.*;
import Users.User;

import java.util.Calendar;

/**
 * Created by Aluno IC on 01/09/2017.
 */
public class ResourceBooking {

    private IResources resource;
    private User user;
    private Calendar start,end;
    private State state;


    public ResourceBooking(User user, Calendar start, Calendar end,) {
        this.user = user;
        this.start = start;
        this.end = end;
        state = new InProcess();
    }



    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Calendar getStart() {
        return start;
    }

    public void setStart(Calendar start) {
        this.start = start;
    }

    public Calendar getEnd() {
        return end;
    }

    public void setEnd(Calendar end) {
        this.end = end;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
