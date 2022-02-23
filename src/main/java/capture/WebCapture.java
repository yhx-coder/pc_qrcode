package capture;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

/**
 * @author: ming
 * @date: 2022/2/23 16:52
 */
public class WebCapture {

    public String capture(String url) throws IOException, URISyntaxException, AWTException {
        Desktop.getDesktop().browse(new URL(url).toURI());
        Robot robot = new Robot();
        robot.delay(10000);
        Dimension d = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
        int width = (int) d.getWidth();
        int height = (int) d.getHeight();
        // 最大化浏览器
        robot.keyRelease(KeyEvent.VK_F11);
        robot.delay(2000);
        Image image = robot.createScreenCapture(new Rectangle(0, 0, width, height));
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = bi.createGraphics();
        g.drawImage(image, 0, 0, width, height, null);

        String fileName = getCacheName();
        ImageIO.write(bi, "jpg", new File(fileName));
        return fileName;
    }

    public String getCachePath() throws IOException {
        InputStream inputStream = WebCapture.class.getClassLoader().getResourceAsStream("config.properties");
        Properties properties = new Properties();
        properties.load(inputStream);
        String cachePath = properties.getProperty("cachePath");
        File file = new File(cachePath);
        if (!file.exists()&&!file.isDirectory()){
            file.mkdir();
        }
        return cachePath;
    }


    public String getCacheName() throws IOException {
        String cachePath = getCachePath();
        SimpleDateFormat dateFormat = new SimpleDateFormat();
        dateFormat.applyPattern("yyyy-MM-dd_HH-mm-ss");
        Date date = new Date();
        Random random = new Random();
        return cachePath +
                dateFormat.format(date) +
                "_" +
                random.nextInt(10) +
                ".jpg";
    }
}
