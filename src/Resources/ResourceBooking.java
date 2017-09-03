package Resources;


import Activities.IActivity;
import State.*;
import Users.User;

import java.time.LocalDate;

/**
 * Created by Aluno IC on 01/09/2017.
 */
public class ResourceBooking {

    private IResources resource;
    private IActivity activity;
    private User responsible;
    private State state;
    private LocalDate start, end;
    private String description;


    public IResources getResource() {
        return resource;
    }

    public void setResource(IResources resource) {
        this.resource = resource;
    }

    public User getResponsible() {
        return responsible;
    }

    public void setResponsible(User responsible) {
        this.responsible = responsible;
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
