import Proxy.IDatabase;
import Proxy.ProxyDatabase;
import Proxy.RealDatabase;

import java.time.LocalTime;

/**
 * Created by Aluno IC on 01/09/2017.
 */
public class Teste2 {

    public static void main(String[] args)
    {
        LocalTime l1 = LocalTime.now();
        System.out.printf("%d -> %d", l1.getHour(),l1.getMinute());
    }

}
