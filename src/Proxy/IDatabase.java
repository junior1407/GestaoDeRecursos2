package Proxy;

import Activities.*;
import Exceptions.ActivityNotFoundException;
import Exceptions.NotAvaliableException;
import Exceptions.UserAlreadyExistsException;
import Exceptions.UserNotFoundException;
import Resources.*;
import Users.User;

public interface IDatabase {

    void addActivity(IActivity a);
    void addUser(User u);
    void addResource(IResources r);
    User getUser(String cpf) throws UserNotFoundException;
    IActivity getActivity(int id) throws ActivityNotFoundException;
    void checkAlreadyExists(String cpf) throws UserAlreadyExistsException;
    int getNextActivityId();
    int getNextResourceId();

    IResources getFirstResource(Resources type) throws NotAvaliableException;
}
