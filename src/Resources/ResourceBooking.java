package Resources;


import State.*;
import Users.User;

import java.time.LocalDate;
import java.util.Calendar;

/**
 * Created by Aluno IC on 01/09/2017.
 */
public class ResourceBooking {

    private IResources resource;
    private User user;
    private State state;
    private LocalDate start, end;


    public ResourceBooking(IResources resource, User user, LocalDate start, LocalDate end) {
        this.resource = resource;
        this.user = user;
        this.start = start;
        this.end = end;
    }

    public IResources getResource() {
        return resource;
    }

    public void setResource(IResources resource) {
        this.resource = resource;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }
}
