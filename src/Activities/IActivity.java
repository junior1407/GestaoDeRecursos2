package Activities;

import Exceptions.NotAvailableException;
import Exceptions.PermissionDeniedException;
import Resources.IResources;
import Users.Permission;
import Users.User;

import java.time.LocalDateTime;
import java.util.ArrayList;

public abstract class IActivity {
    private int id;
    private String title;
    private ArrayList<User> participants;
    private ArrayList<String> materials;
    private ArrayList<IResources> resources;
    private LocalDateTime start, end;


    public IActivity(int id, String title, LocalDateTime start, LocalDateTime end, User responsible) {
        this.id = id;
        this.title = title;
        this.start = start;
        this.end = end;
        this.responsible = responsible;
        participants = new ArrayList<>();
        materials = new ArrayList<>();
        resources = new ArrayList<>();
    }

    public ArrayList<IResources> getResources() {
        return resources;
    }

    public void setResources(ArrayList<IResources> resources) {
        this.resources = resources;
    }

    public User getResponsible() {
        return responsible;
    }

    public void setResponsible(User responsible) {
        this.responsible = responsible;
    }

    private User responsible;


    public void isPermitted(User u) throws PermissionDeniedException {
        if (u.getPermission()== Permission.GRADUATE)
        {
            throw new PermissionDeniedException();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<User> getParticipants() {
        return participants;
    }

    public void setParticipants(ArrayList<User> participants) {
        this.participants = participants;
    }

    public ArrayList<String> getMaterials() {
        return materials;
    }

    public void setMaterials(ArrayList<String> materials) {
        this.materials = materials;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public void printResources() throws NotAvailableException {
        if (resources.size()==0){ throw new NotAvailableException();}
        for(IResources r: resources)
        {
            System.out.printf("Code: %d | Name: %s\n",r.getCode(),r.getName());
        }
    }

    @Override
    public String toString() {
        return "id=" + id +
                "\ntitle='" + title + '\'' +
                "\nstart=" + start +
                "\nend=" + end +
                "\nresponsible=" + responsible.getName();
    }
}
