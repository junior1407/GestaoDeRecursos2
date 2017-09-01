package Resources;

import State.*;
import Users.Professor;
import Users.Student;

/**
 * Created by Aluno IC on 01/09/2017.
 */
public class Laboratory implements IResources<Laboratory>{
    @Override
    public Laboratory getPrototype() {
      return new Laboratory();
    }

    private Laboratory next;
    private String code;

    @Override
    public boolean hasPermission(Student u) {
        return false;
    }

    @Override
    public boolean hasPermission(Professor u) {
        return false;
    }


}



