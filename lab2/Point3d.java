import sun.security.ec.point.Point;

public class Point3d extends Point2d {
    double zCoord;

    /**
     * Конструктор инициализации
     *
     * @param x
     * @param y
     */
    public Point3d(double x, double y, double z) {
        super(x, y);
        this.zCoord = z;
    }

    /** Конструктор по умолчанию */
    public Point3d() {
        this(0, 0, 0);
    }

    /**
     *
     * возвращение координаты Z
     */
    public double getZ() {
        return this.zCoord;
    }

    /**
     * Установка занчения координаты Z.
     *
     * @param val
     */
    public void setZ(double val) {
        this.zCoord = val;
    }

    public static boolean pointsEqual(Point3d x1, Point3d x2) {
        return x1.equals(x2);
    }

    // перегруженный метод для сравнения заданных точек
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (!Point3d.class.isAssignableFrom(obj.getClass())) {
            return false;
        }

        final Point3d point = (Point3d) obj;
        if (this.getX() != point.getX() || this.getY() != point.getY() || this.getZ() != point.getZ()) {
            return false;
        }

        return true;
    }

    // вычисляем расстояние между двумя точками
    public double distanceTo(Point3d point) {
        return distance(point.getX(), point.getY(), point.getZ());
    }

    // вычисляем расстояние от текущей точки до заданной
    private double distance(double x1, double y1, double z1) {
        double x = this.getX() - x1;
        double y = this.getY() - y1;
        double z = this.getZ() - z1;
        return round(Math.sqrt(x * x + y * y + z * z), 2);
    }

    // собственное округление
    private double round(double number, int precision) {
        int pow = 1;
        if (precision == 0) {
            precision = 2;
        }
        for (int i = 0; i < precision; i++) {
            pow *= 10;
        }
        double tmp = number * pow;
        return (double) (int) ((tmp - (int) tmp) >= 0.5 ? tmp + 1 : tmp) / pow;
    }

    // вычисление площади треугольника по 3-м точкам в пространстве
    public static double computeArea(Point3d pointA, Point3d pointB, Point3d pointC) {
        double a = pointA.distanceTo(pointB);
        double b = pointA.distanceTo(pointC);
        double c = pointA.distanceTo(pointA);

        double p = (a + b + c) / 2;
        double h = 2 / a * Math.sqrt(p * (p - a) * (p - b) * (p - c));
        double s = 0.5 * a * h;
        return s;
    }

    public static void main(String[] args) {
        Point3d firstPoint = new Point3d(4, 6, 1);
        Point3d secondPoint = new Point3d(12, 13, 5);
        Point3d thirdPoint = new Point3d(13, 15, 22);

        double square = Point3d.computeArea(firstPoint, secondPoint, thirdPoint);
        System.out.print("Площадь заданного треугольника равна = ");
        System.out.println(square);
    }
}