//Anthony Mace  CSC205AB
//This program ...

import java.util.*;

public class program5 {

    Scanner console = new Scanner(System.in);

    public static void main(String[] args) {
        Cylinder test = new Cylinder(2, 4, 6, 7);
        RectangularBox test1 = new RectangularBox(5, 4, 3, 2, 1);
        TrapezoidalBox test2 = new TrapezoidalBox(6, 5, 4, 3, 2, 1);
        Container[] collect = new Container[3];
        collect[0] = test;
        collect[1] = test1;
        collect[2] = test2;
        for (int i = 0; i < collect.length; i++) {
            System.out.println(collect[i]);
            System.out.println(collect[i].wallWeight());
        }
    }

}

abstract class RollingStock {
    private String _ownerName;
    private int _idNum;
    private double _baseFrameWeight;

    public RollingStock(String ownerName, int idNum, double baseFrameWeight) {
        _ownerName = ownerName;
        _idNum = idNum;
        _baseFrameWeight = baseFrameWeight;
    }

    public String getOwnerName() {
        return _ownerName;
    }

    public int getIDNum() {
        return _idNum;
    }

    public double getBaseFraeWeight() {
        return _baseFrameWeight;
    }

    public String toString() {
        return "Owner: " + _ownerName + "\n" +
               "ID: #" + _idNum + "\n" +
               "Base Frame Weight: " + _baseFrameWeight + "\n";
    }
}

class Engine extends RollingStock {
    private double _pullingCapacity;

    public Engine(String ownerName, int idNum, double baseFrameWeight,
                  double pullingCapacity) {
        super(ownerName, idNum, baseFrameWeight);
        _pullingCapacity = pullingCapacity;
    }

    public double getPullingCapacity() {
        return _pullingCapacity;
    }

    public String toString() {
        return super.toString() +
               "Pulling capacity: " + _pullingCapacity + "\n";
    }
}

class FreightCar extends RollingStock {
    private Contents _contents;
    private Container _container;
    private double _loadFactor;

    public FreightCar(String ownerName, int idNum, double baseFrameWeight,
                      Contents contents, Container container, double loadFactor) {
        super(ownerName, idNum, baseFrameWeight);
        _contents = contents;
        _container = container;
        _loadFactor = loadFactor;
    }

    public double getLoadFactor() {
        return _loadFactor;
    }

    public void setLoadFactor(double newLoadFactor) {
        _loadFactor = newLoadFactor;
    }

    public double computeTotalWeight() {
        double totalWeight = 0.0;
        totalWeight = _container.wallWeight() + (_contents.getDensity() *
                                                _container.computeInteriorVolume());
        return totalWeight;
    }

    public double computeTotalValue() {
        double totalValue = 0.0;
        totalValue = _contents.getValue() * ((_container.computeInteriorVolume() *
                                            _loadFactor) * _contents.getDensity());
        return totalValue;
    }

    public String toString() {
        return super.toString() +
               "Contents: " + _contents.getType() + "\n" +
               "Container: " + _container + "\n" +
               "Load factor: " + _loadFactor + "\n";
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
        intVol = Math.PI * Math.pow(_radius - getWallThickness(), 2) *
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

class TrapezoidalBox extends Container {
    private double _height;
    private double _width;
    private double _upperLength;
    private double _lowerLength;

    public TrapezoidalBox(double wallThickness, double wallDensity, double height,
                          double width, double upperLength, double lowerLength) {
        super(wallThickness, wallDensity);
        _height = height;
        _width = width;
        _upperLength = upperLength;
        _lowerLength = lowerLength;
    }

    public double getHeight() {
        return _height;
    }

    public double getWidth() {
        return _width;
    }

    public double getUpperLength() {
        return _upperLength;
    }

    public double getLowerLength() {
        return _lowerLength;
    }

    public double computeExteriorVolume() {
        double extVol = 0.0;
        extVol = (1.0/2.0) * (_upperLength + _lowerLength) * _width * _height;
        return extVol;
    }

    public double computeInteriorVolume() {
        double intVol = 0.0;
        intVol = (1.0/2.0) * (_upperLength - (2* getWallThickness()) + _lowerLength) *
                 _width * _height;
        return intVol;
    }

    public String toString() {
        return super.toString() +
               "Height: " + _height + "\n" +
               "Width: " + _width + "\n" +
               "Upper Length: " + _upperLength + "\n" +
               "Lower Length: " + _lowerLength + "\n";
    }
}

class Contents {
    private String _type;
    private double _density;
    private double _value;

    public Contents(String type, double density, double value) {
        _type = type;
        _density = density;
        _value = value;
    }

    public String getType() {
        return _type;
    }

    public double getDensity() {
        return _density;
    }

    public double getValue() {
        return _value;
    }

    public String toString() {
        return "Type: " + _type + "\n" +
               "Denisty :" + _density + "\n" +
               "Value: " + _value + "\n";
    }
}
