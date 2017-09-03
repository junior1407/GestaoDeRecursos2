package Activities;

import java.util.Hashtable;

public class ActivitiesPrototypes
{
    private Hashtable<Activities, IActivity> hash = new Hashtable<Activities, IActivity>();

    public ActivitiesPrototypes() {
        Class c = new Class(0, "Aula",null, null, null);
        Laboratory l = new Laboratory(0,"Laboratorio",null,null, null);
        Presentation p = new Presentation(0,"Presentation", null, null, null);
        hash.put(Activities.CLASS, c);
        hash.put(Activities.LABORATORY, l);
        hash.put(Activities.PRESENTATION, p);
    }

    public IActivity getPrototype(Activities a)
    {
        return hash.get(a);
    }

}
/*
public class ItemRegistry {
    private Hashtable map = new Hashtable();
    public ItemRegistry
    {
        loadCache();
    }

    public Item createBasicItem(String type) {
        return map.get(type).clone();
    }

    private void loadCache() {
        Book book = new Book();
        book.setTitle("Design Patterns");
        book.setPrice(20.00);
        map.add("Book", book);
        CD cd = new CD();
        cd.setTitle("Various");
        cd.setPrice(10.00);
        map.add("CD", cd);
    }*/
