package util;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class Utils {
    public static void centerFrame(JFrame frame){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((screenSize.width-frame.getWidth())/2, (screenSize.height-frame.getHeight())/2);
    }
}
