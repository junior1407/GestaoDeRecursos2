package Proxy;

import Activities.IActivity;
import Exceptions.UserAlreadyExistsException;
import Resources.IResources;
import Users.User;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class RealDatabase implements IDatabase {
    private ArrayList<IActivity> activities;
    private ArrayList<IResources> resources;
    private ArrayList<User> users;



    @Override
    public void checkAlreadyExists(String cpf) throws UserAlreadyExistsException {
        if (users.stream().filter(user -> user.getCpf().equals(cpf)).count()!=0)
        {
            throw new UserAlreadyExistsException();
        }

    }



    public RealDatabase() {
        activities= new ArrayList<IActivity>();
        resources= new ArrayList<IResources>();
        users= new ArrayList<User>();
    }


    @Override
    public void addActivity(IActivity a) {
        activities.add(a);
    }

    @Override
    public void addUser(User u) {
        users.add(u);

    }

    @Override
    public void addResource(IResources r) {
        resources.add(r);
    }
}
