//Anthony Mace  CSC205AB
//This program ...

import java.util.*;

public class program5 {

    Scanner console = new Scanner(System.in);

    public static void main(String[] args) {
        Cylinder test = new Cylinder(5, 4, 3, 2);
        System.out.println(test);
        RectangularBox test1 = new RectangularBox(5, 4, 3, 2, 1);
        System.out.println(test1);
        Container[] collect = new Container[2];
        collect[0] = test;
        collect[1] = test1;
        for (int i = 0; i < collect.length; i++) {
            System.out.println(collect[i]);
        }
    }

}

abstract class Container {
    private double _wallThickness;
    private double _wallDensity;

    public Container(double wallThickness, double wallDensity) {
        _wallThickness = wallThickness;
        _wallDensity = wallDensity;
    }

    public double getWallThickness() {
        return _wallThickness;
    }

    public double getWallDensity() {
        return _wallDensity;
    }

    abstract public double computeExteriorVolume();

    abstract public double computeInteriorVolume();

    public double wallWeight() {
        double wallVolume = 0.0;
        double wallWeight = 0.0;
        wallVolume = computeExteriorVolume() - computeInteriorVolume();
        wallWeight = _wallDensity * wallVolume;
        return wallWeight;
    }

    public String toString() {
        return getClass() + "\n" +
               "Wall thickness: " + _wallThickness + "\n" +
               "Wall density: " + _wallDensity + "\n";
    }
}

class Cylinder extends Container {
    private double _radius;
    private double _length;

    public Cylinder(double wallThickness, double wallDensity, double radius,
                    double length) {
        super(wallThickness, wallDensity);
        _radius = radius;
        _length = length;
    }

    public double getRadius() {
        return _radius;
    }

    public double getLength() {
        return _length;
    }

    public double computeExteriorVolume() {
        double extVol = 0.0;
        extVol = Math.PI * Math.pow(_radius, 2) * _length;
        return extVol;
    }

    public double computeInteriorVolume() {
        double intVol = 0.0;
        intVol = Math.PI * Math.pow(_radius * getWallThickness(), 2) *
                (_length - (2 * getWallThickness()));
        return intVol;
    }

    public String toString() {
        return super.toString() +
               "Radius: " + _radius + "\n" +
               "Length: " + _length + "\n";
    }
}

class RectangularBox extends Container {
    private double _height;
    private double _width;
    private double _length;

    public RectangularBox(double wallThickness, double wallDensity, double height,
                          double width, double length) {
        super(wallThickness, wallDensity);
        _height = height;
        _width = width;
        _length = length;
    }

    public double getHeight() {
        return _height;
    }

    public double getWidth() {
        return _width;
    }

    public double getLength() {
        return _length;
    }

    public double computeExteriorVolume() {
        double extVol = 0.0;
        extVol = _height * _width * _length;
        return extVol;
    }

    public double computeInteriorVolume() {
        double intVol = 0.0;
        intVol = (_height * (2 * getWallThickness())) *
                 (_width * (2 * getWallThickness())) *
                 (_length * (2 * getWallThickness()));
        return intVol;
    }

    public String toString() {
        return super.toString() +
               "Height: " + _height + "\n" +
               "Width: " + _width + "\n" +
               "Length: " + _length + "\n";
    }
}
