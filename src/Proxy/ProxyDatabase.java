package Proxy;

import Activities.IActivity;
import Exceptions.*;
import Resources.*;
import Resources.Resources;
import Users.User;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import java.util.ArrayList;
import java.util.List;

public class ProxyDatabase implements IDatabase{
    private RealDatabase db;

    @Override
    public IResources getResource(int code) throws ResourceNotFundException {
        if (db==null)
        {
            db = new RealDatabase();
        }
      return db.getResource(code);
    }

    @Override
    public IActivity getActivity(int id) throws ActivityNotFoundException {
        if (db==null)
        {
            db= new RealDatabase();
        }
        return db.getActivity(id);
    }

    @Override
    public int getNextActivityId() {
        if (db==null)
        {
            db= new RealDatabase();
        }
        return db.getNextActivityId();
    }

    @Override
    public int getNextResourceId() {
        if (db==null)
        {
            db= new RealDatabase();
        }
        return db.getNextResourceId();
    }

    @Override
    public int getNextBookingId() {
        return 0;
    }

    @Override
    public IResources getFirstResource(Resources type) throws NotAvailableException {
        if (db==null)
        {
            db= new RealDatabase();
        }
        return db.getFirstResource(type);
    }

    @Override
    public ArrayList<IResources> getResources() {
        return db.getResources();
    }

    @Override
    public List<IActivity> getActivities(String cpf) {
        if (db==null)
        {
            db = new RealDatabase();
        }
        return db.getActivities(cpf);
    }

    @Override
    public List<IActivity> getActivities() {
        if (db==null)
        {
            db = new RealDatabase();
        }
        return db.getActivities();
    }

    @Override
    public ArrayList<User> getUsers() {
        if (db==null)
        {
            db = new RealDatabase();
        }
        return db.getUsers();
    }

    @Override
    public void addBooking(ResourceBooking booking) {
        if (db==null)
        {
            db = new RealDatabase();
        }
        db.addBooking(booking);
    }

    @Override
    public void addActivity(IActivity a) {
        if (db==null)
        {
            db= new RealDatabase();
        }
        db.addActivity(a);
    }

    @Override
    public void addUser(User u) {
        if (db==null)
        {
            db= new RealDatabase();
        }
        db.addUser(u);
    }

    @Override
    public void addResource(IResources r) {
        if (db==null)
        {
            db= new RealDatabase();
        }
        db.addResource(r);
    }

    @Override
    public ResourceBooking getBooking(int actvCode, int resCode) throws NotFound {
        if (db==null)
        {
            db= new RealDatabase();
        }
       return db.getBooking(actvCode,resCode);
    }

    @Override
    public User getUser(String cpf) throws UserNotFoundException {
        if (db==null)
        {
            db= new RealDatabase();
        }
       return db.getUser(cpf);
    }

    @Override
    public void checkAlreadyExists(String cpf) throws UserAlreadyExistsException {
        if (db==null)
        {
            db= new RealDatabase();
        }
       db.checkAlreadyExists(cpf);
    }
}
