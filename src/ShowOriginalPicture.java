import javax.swing.*;
import java.awt.*;

public class ShowOriginalPicture extends JFrame{
    JPanel panel;
    Image image;
    ShowOriginalPicture(Image image1){
        setTitle("Picture");
        setSize(580,580);
        setLayout(new BorderLayout());
        image = image1;
        ImageIcon icon = new ImageIcon(image);
        icon.setImage(icon.getImage().getScaledInstance(580,580,Image.SCALE_DEFAULT));
        panel = new JPanel(){
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                g.drawImage(icon.getImage(),0,0,panel);
            }
        };
        panel.setSize(580,580);
        add(panel,BorderLayout.CENTER);
        setResizable(false);
        setVisible(true);
    }
}
