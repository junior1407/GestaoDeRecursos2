package Resources;

import Users.Professor;

/**
 * Created by Aluno IC on 01/09/2017.
 */
public class Laboratory extends IResources<Laboratory>{
    public Laboratory(String code, String name) {
        super(code, name);
    }

    @Override

    public Laboratory getPrototype(String code) {
        return new Laboratory(code, "Laboratory");
    }




}



