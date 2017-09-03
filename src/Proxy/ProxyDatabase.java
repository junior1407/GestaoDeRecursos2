package Proxy;

import Activities.IActivity;
import Exceptions.UserAlreadyExistsException;
import Exceptions.UserNotFoundException;
import Resources.IResources;
import Users.User;
public class ProxyDatabase implements IDatabase{
    private RealDatabase db;

    @Override
    public int getNextActivityId() {
        if (db==null)
        {
            db= new RealDatabase();
        }
        return db.getNextActivityId();
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
