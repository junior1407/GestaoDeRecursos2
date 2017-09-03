package Proxy;

import Activities.IActivity;
import Exceptions.UserAlreadyExistsException;
import Exceptions.UserNotFoundException;
import Resources.IResources;
import Users.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RealDatabase implements IDatabase {
    private ArrayList<IActivity> activities;
    private ArrayList<IResources> resources;
    private ArrayList<User> users;

    public ArrayList<IActivity> getActivities() {
        return activities;
    }

    public void setActivities(ArrayList<IActivity> activities) {
        this.activities = activities;
    }

    @Override
    public User getUser(String cpf) throws UserNotFoundException {
        List<User> list  = users.stream()
                .filter(u -> u.getCpf().equals(cpf)).collect(Collectors.toList());

        if (list.size() ==0)
        {
            throw new UserNotFoundException();
        }
        return list.get(0);

    }

    @Override
    public int getNextActivityId() {
        return activities.size();
    }

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
