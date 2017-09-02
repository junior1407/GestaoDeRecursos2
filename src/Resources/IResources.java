package Resources;

import Users.*;

/**
 * Created by Aluno IC on 01/09/2017.
 */
public abstract class IResources<T extends IResources> {

    public boolean hasPermission(User u)
    {
        if ((u instanceof Admin) ^  (u instanceof Professor) ^ ( u instanceof Researcher))
        {
            return true;
        }
        return false;
    }
    public abstract T getPrototype();
}
