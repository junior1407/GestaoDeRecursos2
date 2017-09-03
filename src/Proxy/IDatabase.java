package Proxy;

import Activities.*;
import Exceptions.UserAlreadyExistsException;
import Exceptions.UserNotFoundException;
import Resources.IResources;
import Users.User;

public interface IDatabase {

    void addActivity(IActivity a);
    void addUser(User u);
    void addResource(IResources r);
    User getUser(String cpf) throws UserNotFoundException;
    void checkAlreadyExists(String cpf) throws UserAlreadyExistsException;
    int getNextActivityId();
}
