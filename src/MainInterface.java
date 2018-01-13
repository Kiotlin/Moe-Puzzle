import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class MainInterface extends JFrame{

    private static int WIDTH = 630;
    private static int HEIGHT = 675;
    JMenuBar menuBar = null;
    JMenu menu = null;
    JMenuItem menuItem = null;
    JPanel panel ;
    int modeNumber;
    String imageUrl = "res/chino.jpg";
    Image image ;
    MainInterface(String url,int difficulty){

        modeNumber = difficulty;
        imageUrl = url;
        Toolkit t = Toolkit.getDefaultToolkit();
        image = t.getImage(url);

        /**
         *  Fix frameSize
         *
         * */
        setResizable(false);

        /**
         * Set menubar
         * @two_menus
         **/
        menuBar = new JMenuBar();
        menuItem = new JMenuItem("Start_");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Newgame();
            }
        });
        menu = new JMenu("New");
        menu.add(menuItem);
        menuItem = new JMenuItem("Exit_");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        menu.add(menuItem);
        menuBar.add(menu);
        menu = new JMenu("Operate");
        menuItem = new JMenuItem("Origin");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ShowOriginalPicture(image);
            }
        });
        menu.add(menuItem);
        menuItem = new JMenuItem("Reset");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrameRefresh(e);
                dispose();
            }
        });
        menu.add(menuItem);
        menuBar.add(menu);

        /**
         * initiate panel for picture display
         * @imageUrl the picture file path
         * */
        try {
            if(modeNumber == 1){
                setSize(600,655);
                panel = new Picture_Divide(url,modeNumber);
            }else if(modeNumber == 2){
                setSize(630,675);
                panel = new Picture_Divide(url,modeNumber);
            }else if(modeNumber == 3){
                setSize(650,695);
                panel = new Picture_Divide(url,modeNumber);
            }
        } catch (Exception e) {

            e.printStackTrace();
        }

        /**
         * initiate main interface
         *
         * */
        setVisible(true);
        setLayout(new BorderLayout());
//        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
//        setLocation((dimension.width - WIDTH) / 2,(dimension.height - HEIGHT) / 2);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setJMenuBar(menuBar);
        this.add(panel,BorderLayout.CENTER);
    }

    /**
     * Start a new game config
     *
     * */
    private class Newgame extends JFrame{
        JList<String> list;
        JButton ensure,cancel;
        JRadioButton easy,middle,hard;
        ButtonGroup buttonGroup;
        String[] Picturelist;
        Newgame(){
            Picturelist = new String[]{"chino","eroman","krito"};
            list = new JList<>(Picturelist);
            ensure = new JButton("Ensure");
            cancel = new JButton("Cancel");
            easy = new JRadioButton("Easy");
            middle = new JRadioButton("Middle");
            hard = new JRadioButton("Hard");
            buttonGroup =new ButtonGroup();
            buttonGroup.add(easy);
            buttonGroup.add(middle);
            buttonGroup.add(hard);
            setLayout(new FlowLayout());
            ensure.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(list.isSelectionEmpty()){
                        JOptionPane.showMessageDialog(null,"Please choose at least one picture!",
                                "Warning",JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    imageUrl = "res/"+list.getSelectedValue()+".jpg";
                    if(easy.isSelected()){
                        modeNumber = 1;
                    }else if(middle.isSelected()){
                        modeNumber = 2;
                    }else if(hard.isSelected()){
                        modeNumber = 3;
                    }
                    new MainInterface(imageUrl,modeNumber);
                    dispose();
//                    Newgame.super.dispose();
                }
            });
            cancel.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });
            add(list);
            add(easy);
            add(middle);
            add(hard);
            add(ensure);
            add(cancel);
            setVisible(true);
            setBounds(0,0,450,100);
        }

    }

    protected void FrameRefresh(ActionEvent arg0) {
        String url = imageUrl;
        MainInterface frame1 = new MainInterface(url,modeNumber);
        SwingUtilities.updateComponentTreeUI(frame1);
        frame1.invalidate();
        frame1.validate();
        frame1.repaint();
    }

    public static void main(String[] args){
        new MainInterface("res/chino.jpg",1);
    }
}
