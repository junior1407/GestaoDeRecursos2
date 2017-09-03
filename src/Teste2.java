import Proxy.IDatabase;
import Proxy.ProxyDatabase;
import Proxy.RealDatabase;

/**
 * Created by Aluno IC on 01/09/2017.
 */
public class Teste2 {

    public static void main(String[] args)
    {
        RealDatabase db = new RealDatabase();

        System.out.println(db.getActivities().size());
        db.addActivity(null);
        System.out.println(db.getActivities().size());
        db.addActivity(null);
        System.out.println(db.getActivities().size());
    }

}
