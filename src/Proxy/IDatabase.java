package Proxy;

import Activities.*;
import Exceptions.*;
import Resources.*;
import Users.User;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import java.util.ArrayList;
import java.util.List;

public interface IDatabase {

    void addActivity(IActivity a);
    void addUser(User u);
    void addResource(IResources r);
    ResourceBooking getBooking(int actvCode, int resCode) throws NotFound;
    User getUser(String cpf) throws UserNotFoundException;
    IActivity getActivity(int id) throws ActivityNotFoundException;
    void checkAlreadyExists(String cpf) throws UserAlreadyExistsException;
    int getNextActivityId();
    int getNextResourceId();
    int getNextBookingId();

    IResources getFirstResource(Resources type) throws NotAvailableException;

    ArrayList<IResources> getResources();

    List<IActivity> getActivities(String cpf);
    List<IActivity> getActivities();
    ArrayList<User> getUsers();

    void addBooking(ResourceBooking booking);

    IResources getResource(int code) throws ResourceNotFundException;
}

