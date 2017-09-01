/**
 * Created by Aluno IC on 01/09/2017.
 */
public class DataBase {
    private static DataBase ourInstance = new DataBase();

    public static DataBase getInstance() {
        return ourInstance;
    }


    private DataBase() {
    }


}
