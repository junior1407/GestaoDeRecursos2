package Resources;


import Activities.IActivity;
import Exceptions.AlreadyConcludedException;
import Exceptions.NotAvailableException;
import Exceptions.PermissionDeniedException;
import State.*;
import Users.User;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Created by Aluno IC on 01/09/2017.
 */
public class ResourceBooking {


    private int code;
    private IResources resource;
    private IActivity activity;
    private User responsible;
    private State state;
    private LocalDateTime start, end;
    private String description;

    public ResourceBooking(IActivity activity, User responsible, LocalDateTime start, LocalDateTime end, String description, int code) {
        this.activity = activity;
        this.responsible = responsible;
        state = new InProcess();
        this.start = start;
        this.end = end;
        this.description = description;
        this.code = code;
    }

    public void changeState(User u)
    {
        try {
            state.changeState(this, u);
            System.out.println("Sucess!");
        } catch (AlreadyConcludedException e) {
           System.out.println("The allocation is already concluded!");
        } catch (NotAvailableException e) {
            System.out.println("This option is not avaliable!");
        } catch (PermissionDeniedException e) {
            System.out.println("Permission denied!");
        }
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

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

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public Boolean conflicts(ResourceBooking r)
    {
        LocalTime s1 = getStart().toLocalTime();
        LocalTime e1 = getEnd().toLocalTime();
        LocalTime s2 = r.getStart().toLocalTime();
        LocalTime e2 = r.getEnd().toLocalTime();

        Boolean b1 = (e2.isBefore(s1));
        Boolean b2 = (s2.isAfter(e1));
        Boolean b3 = (s1.equals(s2));
        Boolean b4 = (e1.equals(e2));

        return  ((e2.isBefore(s1)) || (s2.isAfter(e1)) || (s1.equals(s2)) || (e1.equals(e2)));

       // return e2.isBefore(s1) && s2.isAfter(e1);


    }

}
