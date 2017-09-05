package Activities;

import Exceptions.PermissionDeniedException;
import Users.Permission;
import Users.User;

import java.time.LocalDateTime;

public class LaboratoryAct extends IActivity {
    public LaboratoryAct(int id, String title, LocalDateTime start, LocalDateTime end, User responsible) {
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
