package Proxy;

import Activities.IActivity;
import Exceptions.ActivityNotFoundException;
import Exceptions.NotAvaliableException;
import Exceptions.UserAlreadyExistsException;
import Exceptions.UserNotFoundException;
import Resources.*;
import Resources.Resources;
import Users.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RealDatabase implements IDatabase {
    private ArrayList<IActivity> activities;
    private ArrayList<IResources> resources;
    private ArrayList<User> users;

    public RealDatabase() {
        activities= new ArrayList<IActivity>();
        resources= new ArrayList<IResources>();
        users= new ArrayList<User>();
    }



    public ArrayList<IActivity> getActivities() {
        return activities;
    }

    public void setActivities(ArrayList<IActivity> activities) {
        this.activities = activities;
    }

    @Override
    public IResources getFirstResource(Resources type) throws NotAvaliableException {
        IResources r = null;
       switch (type)
       {
           case LABORATORY: r =resources.stream().filter(x -> x instanceof Laboratory).findFirst().orElse(null); break;
           case AUDITORIUM: r =resources.stream().filter(x -> x instanceof Auditorium).findFirst().orElse(null);break;
           case CLASSROOM: r =resources.stream().filter(x -> x instanceof Classroom).findFirst().orElse(null);break;
           case PROJECTOR: r =resources.stream().filter(x -> x instanceof Projector).findFirst().orElse(null);break;
       }
        if (r== null)
        {
            throw new NotAvaliableException();
        }
        return r;
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
    public IActivity getActivity(int id) throws ActivityNotFoundException {
        List<IActivity> a = activities.stream().filter(x -> x.getId()==id).collect(Collectors.toList());
        if (a.size()==0){throw new ActivityNotFoundException(); }
        return a.get(0);
    }

    @Override
    public int getNextActivityId() {
        return activities.size();
    }

    @Override
    public int getNextResourceId() {
        return resources.size();
    }


    @Override
    public void checkAlreadyExists(String cpf) throws UserAlreadyExistsException {
        if (users.stream().filter(user -> user.getCpf().equals(cpf)).count()!=0)
        {
            throw new UserAlreadyExistsException();
        }

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
        List<IResources> list = null;
        if (r instanceof Auditorium)
        {
            list = resources.stream().filter(Auditorium.class::isInstance).collect(Collectors.toList());
        }
        if (r instanceof Classroom){
            list = resources.stream().filter(Classroom.class::isInstance).collect(Collectors.toList());
        }
        if (r instanceof Laboratory)
        {
            list = resources.stream().filter(Laboratory.class::isInstance).collect(Collectors.toList());
        }
        if (r instanceof  Projector)
        {
            list = resources.stream().filter(Projector.class::isInstance).collect(Collectors.toList());
        }
        if (list != null && list.size() != 0) {
            IResources last = list.get(list.size() - 1);
            last.setNext(r);
        }
        resources.add(r);
        }
    }



