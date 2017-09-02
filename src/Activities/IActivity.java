package Activities;

import Resources.IResources;
import Users.User;
import java.time.LocalDate;
import java.util.ArrayList;

public abstract class IActivity {
    private int id;
    private String title;
    private ArrayList<User> participants;
    private ArrayList<String> materials;
    private ArrayList<IResources> resources;
    private LocalDate start, end;
    private User responsible;

    // DEFINIR GRAU DE PERMISSAO
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

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }
}
