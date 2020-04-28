import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * Класс предоставляет графический интерфейс для отображения фракталов.
 */
public class FractalExplorer {
    /** Размер экрана. **/
    private int dispSize;

    /** Ссылка JImageDisplay, для обновления отображения в разных
     * методах в процессе вычисления фрактала. **/
    private JImageDisplay img;

    /** Ссылка на базовый класс для отображения других видов фракталов в будущем. **/
    private FractalGenerator fGen;

    /** Диапазон комплекснй плоскости, которая выводится на экран **/
    private Rectangle2D.Double range;

    /** Конструктор, который принимает и сохраняет значение
     размера отображения, а также инициализирует объекты диапазона и
     фрактального генератора.
     */
    public FractalExplorer(int dispSize) {
        this.dispSize = dispSize;
        this.fGen = new Mandelbrot();
        this.range = new Rectangle2D.Double(0, 0, 0, 0);
        fGen.getInitialRange(this.range);

    }

    /** Метод инициализирует графический интерфейс Swing. **/
    public void createAndShowGUI() {
        JFrame frame = new JFrame("Fractal Explorer");
        img = new JImageDisplay(dispSize, dispSize);
        JButton resetButton = new JButton("Reset");

        ActionHandler aHandler = new ActionHandler();
        MouseHandler mHandler = new MouseHandler();
        resetButton.addActionListener(aHandler);
        img.addMouseListener(mHandler);

        frame.setLayout(new java.awt.BorderLayout());
        frame.add(img, java.awt.BorderLayout.CENTER);
        frame.add(resetButton, java.awt.BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }

    /** Вспомогательный метод с типом доступа private для вывода на экран фрактала. **/
    private void drawFractal() {
        for (int i = 0; i < dispSize; i++) {
            for (int j = 0; j < dispSize; j++) {
                double xCoord = FractalGenerator.getCoord(range.x,
                        range.x + range.width, dispSize, i);
                double yCoord = FractalGenerator.getCoord(range.y,
                        range.y + range.width, dispSize, j);
                double numIters = fGen.numIterations(xCoord, yCoord);

                if (numIters == -1) {
                    img.drawPixel(i, j, 0);
                }
                else {
                    float hue = 0.7f + (float) numIters / 200f;
                    int rgbColor = Color.HSBtoRGB(hue, 1f, 1f);
                    img.drawPixel(i, j, rgbColor);
                }
            }
        }
        img.repaint();
    }

    /** Обработчик для сбрасываения масштаба. **/
    public class ActionHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            fGen.getInitialRange(range);
            drawFractal();
        }
    }

    /** Обработчик для увеличения масштаба нажатого пикселя. **/
    public class MouseHandler extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            double xCoord = FractalGenerator.getCoord(range.x,
                    range.x + range.width, dispSize, e.getX());
            double yCoord = FractalGenerator.getCoord(range.y,
                    range.y + range.width, dispSize, e.getY());
            fGen.recenterAndZoomRange(range, xCoord, yCoord, 0.5);
            drawFractal();
        }
    }

    /** Метод запуска приложения. **/
    public static void main(String[] args) {
        FractalExplorer fracExp = new FractalExplorer(800);
        fracExp.createAndShowGUI();
        fracExp.drawFractal();
    }
}
