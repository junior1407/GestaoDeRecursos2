package Resources;

import Exceptions.NotAvailableException;
import Exceptions.PermissionDeniedException;
import Users.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Aluno IC on 01/09/2017.
 */
public abstract class IResources {


    private int code;
    private IResources next;
    private String name;
    private ArrayList<ResourceBooking> bookings;
    private User responsible;
    public IResources() {
    }
    public IResources(int code, String name) {
        this.code = code;
        this.name = name;
        this.next = null;
        responsible=null;
        this.bookings = new ArrayList<ResourceBooking>();


}
    public void isPermitted(User u) throws PermissionDeniedException {
        if (!((u.getPermission() == Permission.PROFESSOR)^(u.getPermission() ==  Permission.ADMIN) ^(u.getPermission() == Permission.RESEARCHER)))
        {
            throw new PermissionDeniedException();
        }
        responsible=u;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public IResources getNext() {
        return next;
    }

    public void setNext(IResources next) {
        this.next = next;
    }

    public  String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addBooking(ResourceBooking r)
    {
        bookings.add(r);
    }
    public ResourceBooking getBooking(int activityId)
    {
        throw new NotImplementedException();
    }

    public IResources isAvaliable(ResourceBooking r ) throws NotAvailableException {
        Boolean done = true;
        List<ResourceBooking> l = bookings.stream().filter(x -> x.getStart().toLocalDate() == r.getStart().toLocalDate()).collect(Collectors.toList());
        for(ResourceBooking curr: l)
        {
            if (curr.conflicts(r)){
                done=false;
                break;
            }
        }
        if (!done && next==null)
        {
            throw new NotAvailableException();
        }
        else if(!done)
        {
            return next.isAvaliable(r);
        }
        else {
            return this;
        }

    }
    public User getResponsible() {
        return responsible;
    }

    public void setResponsible(User responsible) {
        this.responsible = responsible;
    }
}

