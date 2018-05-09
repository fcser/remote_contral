package server;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 
 * @version :v1.0.0
 * @description :��Ļ����
 * @author: zym
 * @date: 2017��10��31������2:53:02
 */
public class CaptureThread  extends Thread {
    public static final int DPS = 20;//���õ�dps��δ��
    public static final int THREAD_NUM = 5;//���洫���̣߳�δ��
    private DataOutputStream dos;//�ܵ�
    private Robot robot;//robot
    public CaptureThread(DataOutputStream dos) {
        this.dos = dos; 
    } 
    @Override
    public void run() {
        try {
            robot = new Robot();
        } catch (AWTException e1) {
            e1.printStackTrace();
        } 
        final Toolkit tk = java.awt.Toolkit.getDefaultToolkit();
        final Dimension dm = tk.getScreenSize();
        final Rectangle rec = new Rectangle(dm);
        try {
            dos.writeDouble(dm.getHeight());
            dos.writeDouble(dm.getWidth());
            dos.flush();
        } catch (IOException e1) {
            //U.error(U.f("send screen size[%dx%d] error!", dm.getHeight(), dm.getWidth()));
            return;
        }
        while(true) {
            try {
                long begin = System.currentTimeMillis();
                byte[] data = createImage(rec);
                dos.writeInt(data.length);
                dos.write(data);
                dos.flush();
                long end = System.currentTimeMillis();
              //  U.debug(U.f("time=%d,size=%d", end-begin, data.length));
                if((end-begin) < 1000/DPS) {
                    Thread.sleep(1000/DPS - (end-begin));
                }
            } catch (Exception e) {
               // U.debug("CaptrueThread over!");
                return;
            } 
                 
        }
         
    }
 
    @SuppressWarnings("restriction")
	private byte[] createImage(Rectangle rec) throws IOException {
        BufferedImage bufImage = robot.createScreenCapture(rec);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(baos);
        encoder.encode(bufImage);
        return baos.toByteArray();
    }
}
