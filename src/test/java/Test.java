import capture.WebCapture;
import com.google.zxing.NotFoundException;
import decoder.QrcodeDecoder;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;


/**
 * @author: ming
 * @date: 2022/2/23 17:13
 */
public class Test {
    public static void main(String[] args) {
        WebCapture webCapture = new WebCapture();
        QrcodeDecoder qrcodeDecoder = new QrcodeDecoder();

        try {
            String capture = webCapture.capture("https://time.geekbang.org/");
            String decode = qrcodeDecoder.decode(capture, false);
            System.out.println(decode);
        } catch (IOException | URISyntaxException | AWTException | NotFoundException e) {
            e.printStackTrace();
        }

    }
}
