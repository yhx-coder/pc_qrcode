package decoder;


import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author: ming
 * @date: 2022/2/23 16:00
 */
public class QrcodeDecoder {
    public String decode(String filepath,boolean isdelete) throws IOException, NotFoundException {
        File file = new File(filepath);
        BufferedImage bufferedImage = ImageIO.read(new FileInputStream(file));
        if(bufferedImage == null){
            return "image is not exit";
        }
        LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
        Binarizer binarizer = new HybridBinarizer(source);
        BinaryBitmap bitmap = new BinaryBitmap(binarizer);

        HashMap<DecodeHintType, Object> decodeHints = new HashMap<DecodeHintType, Object>();
        decodeHints.put(DecodeHintType.CHARACTER_SET, "UTF-8");

        Result result = new MultiFormatReader().decode(bitmap, decodeHints);
        if (isdelete){
            boolean delete = file.delete();
            if (!delete){
                System.out.println("文件未正常删除");
            }
        }
        return result.getText();
    }

    public static void main(String[] args) {
        QrcodeDecoder qrcodeDecoder = new QrcodeDecoder();
        try {
            String d = qrcodeDecoder.decode("d:/a下载/qrcode_mis.bjtu.edu.cn2.png",false);
            System.out.println(d);
        } catch (IOException | NotFoundException e) {
            e.printStackTrace();
        }
    }
}
