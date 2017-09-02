package Proxy;

import Activities.*;
import Exceptions.UserAlreadyExistsException;
import Resources.IResources;
import Users.User;

public interface IDatabase {

    void addActivity(IActivity a);
    void addUser(User u);
    void addResource(IResources r);

    void checkAlreadyExists(String cpf) throws UserAlreadyExistsException;
}
