package Proxy;

import Activities.IActivity;
import Exceptions.UserAlreadyExistsException;
import Resources.IResources;
import Users.User;
public class ProxyDatabase implements IDatabase{
    private RealDatabase db;

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
    public void checkAlreadyExists(String cpf) throws UserAlreadyExistsException {
        if (db==null)
        {
            db= new RealDatabase();
        }
       db.checkAlreadyExists(cpf);
    }
}
