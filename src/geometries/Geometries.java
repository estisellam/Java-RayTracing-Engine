package geometries;

import primitives.*;
import java.util.LinkedList;
import java.util.List;

public class Geometries implements Intersectable {

    private final List<Intersectable> geometriesList = new LinkedList<>();

    public Geometries() {}

    public Geometries(Intersectable... geometries)
    {
        add(geometries);
    }

    public void add(Intersectable... geometries)
    {
        for (Intersectable geo : geometries)
        {
            geometriesList.add(geo);
        }
    }

    @Override
    public List<Point> findIntersections(Ray ray)
    {
        return null;
    }
}
