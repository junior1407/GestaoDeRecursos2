package Resources;

import Users.Professor;
import Users.User;

/**
 * Created by Aluno IC on 01/09/2017.
 */
public class Laboratory extends IResources<Laboratory>{
    public Laboratory(int code, String name) {
        super(code, name);
    }

    @Override
    public void isPermitted(User u) {

    }
}



