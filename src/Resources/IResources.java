package Resources;

import Users.*;

import java.util.ArrayList;

/**
 * Created by Aluno IC on 01/09/2017.
 */
public abstract class IResources<T extends IResources> {


    private String code;
    private T next;
    private static String name;
    private ArrayList<ResourceBooking> bookings;

    public IResources() {
    }

    public IResources(String code, String name) {
        this.code = code;
        this.name = name;
        this.next = null;
        this.bookings = new ArrayList<ResourceBooking>();
    }

    public abstract T getPrototype(String code);


}
