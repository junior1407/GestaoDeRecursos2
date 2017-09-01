package Resources;

import Users.*;

/**
 * Created by Aluno IC on 01/09/2017.
 */
public abstract class IResources<T extends IResources> {

    public abstract boolean hasPermission(Student u);
    public abstract boolean hasPermission(Professor u);

    public abstract T getPrototype();
}
