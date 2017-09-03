package Activities;

import Exceptions.PermissionDeniedException;
import Users.Permission;
import Users.User;

import java.time.LocalDate;

public class Laboratory extends IActivity {
    public Laboratory(int id, String title, LocalDate start, LocalDate end, User responsible) {
        super(id, title, start, end, responsible);
    }

    @Override
    public void isPermitted(User u) throws PermissionDeniedException {
        super.isPermitted(u);
        if (u.getPermission() != Permission.PROFESSOR)
        {
            throw new PermissionDeniedException();
        }
    }
}
