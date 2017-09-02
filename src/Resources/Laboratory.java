package Resources;

import Users.Professor;

/**
 * Created by Aluno IC on 01/09/2017.
 */
public class Laboratory extends IResources<Laboratory>{
    @Override
    public Laboratory getPrototype() {
        return new Laboratory();
    }

    private Laboratory next;
    private String code;



}



