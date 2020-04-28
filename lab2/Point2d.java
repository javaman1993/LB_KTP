/**
 * двумерный класс точки
 **/
public class Point2d {
    /** координата X **/
    private double xCoord;
    /** координата Y **/
    private double yCoord;

    /**
     * Конструктор инициализации
     *
     * @param x
     * @param y
     */
    public Point2d(double x, double y) {
        xCoord = x;
        yCoord = y;
    }

    /** Конструктор по умолчанию */
    public Point2d() {
        this(0, 0);
    }

    /**
     *
     * возвращение координаты X
     */
    public double getX() {
        return this.xCoord;
    }

    /**
     *
     * возвращение координаты Y
     */
    public double getY() {
        return this.yCoord;
    }

    /**
     * Установка занчения координаты Х.
     *
     * @param val
     */
    public void setX(double val) {
        this.xCoord = val;
    }

    /**
     * Установка занчения координаты Y.
     *
     * @param val
     */
    public void setY(double val) {
        this.yCoord = val;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (!Point2d.class.isAssignableFrom(obj.getClass())) {
            return false;
        }

        final Point2d point = (Point2d) obj;
        if (this.getX() != point.getX() || this.getY() != point.getY()) {
            return false;
        }

        return true;
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        Point2d myPoint = new Point2d();
        Point2d myOtherPoint = new Point2d(5, 3);
        Point2d aThirtPoint = new Point2d();

        System.out.println(myPoint.equals(myOtherPoint));
        System.out.println(myPoint.equals(aThirtPoint));
    }
}