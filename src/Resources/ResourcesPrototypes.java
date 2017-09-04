package Resources;

import java.util.Hashtable;

/**
 * Created by Aluno IC on 04/09/2017.
 */
public class ResourcesPrototypes {
    private Hashtable<Resources, IResources> hash = new Hashtable<Resources, IResources>();


    public ResourcesPrototypes()
    {
        //Auditorium, ClassRoom, laboratory, projector
        Auditorium a = new Auditorium(0,"Auditorium");
        Classroom c = new Classroom(0, "Classroom");
        Laboratory l = new Laboratory(0, "Laboratory");
        Projector p = new Projector(0,"Projector");
        hash.put(Resources.AUDITORIUM, a);
        hash.put(Resources.CLASSROOM, c);
        hash.put(Resources.LABORATORY, l);
        hash.put(Resources.PROJECTOR, p);
    }

    public IResources getPrototype(Resources a)
    {
        return hash.get(a);
    }
}
