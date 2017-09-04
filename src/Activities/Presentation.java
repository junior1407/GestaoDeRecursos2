package Activities;

import Users.User;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Presentation extends IActivity {

    public Presentation(int id, String title, LocalDateTime start, LocalDateTime end, User responsible) {
        super(id, title, start, end, responsible);
    }
}
