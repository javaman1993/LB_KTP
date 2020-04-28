import javax.swing.JComponent;
import java.awt.*;
import java.awt.image.BufferedImage;

/** Графический виджет для отображаения фракталов **/
public class JImageDisplay extends JComponent{

    /** Создаваемое изображаение **/
    private BufferedImage img;

    /** Конструктор задает изображение с заданной шириной и высотой **/
    public JImageDisplay(int width, int height) {
        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Dimension dim = new Dimension(width, height);
        super.setPreferredSize(dim);
    }

    /** Код отрисовки **/
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, img.getWidth(), img.getHeight(), null);
    }

    /** Метод устанавливает все пиксели изображения в черный цвет **/
    public void clearImage() {
        for (int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight(); j++) {
                img.setRGB(i, j, 0);
            }
        }
    }

    /** Метод устанавливает пиксель в определенный цвет **/
    public void drawPixel(int x, int y, int rgbColor) {
        img.setRGB(x, y, rgbColor);
    }
}
