package Activities;

import Users.User;

import java.time.LocalDate;

public class Presentation extends IActivity {

    public Presentation(int id, String title, LocalDate start, LocalDate end, User responsible) {
        super(id, title, start, end, responsible);
    }
}
