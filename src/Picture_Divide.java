import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

public class Picture_Divide extends JPanel {

    File file;
    BufferedImage image = null;
    BufferedImage[] chunks;
    LabelsChunk[] labels;
    int OneLabelBeChoosed = 0;
    int[] PictureChunkBox = new int[2];
    static int WIDTH = 600;
    static int HEIGHT = 600;
    private int modeNumber = 1;
    static int EASY_MODE = 1;
    static int MIDDLE_MODE = 2;
    static int HARD_MODE = 3;
    Picture_Divide(String imageUrl,int mode) throws Exception{
        setSize(WIDTH,HEIGHT);
        setOpaque(false);
        modeNumber = mode;
        file = new File(imageUrl);
        image = ImageIO.read(file);
        /**
         * When mode == EASY_MODE ,
         * the Mainframe's size should be set to 600*650
         *
         * */
        if(mode == EASY_MODE){
            chunks = divid(mode);
            labels = new LabelsChunk[(3*mode)*(3*mode)];
            for(int i=0;i<mode*3*mode*3;i++){
                    labels[i] = new LabelsChunk();
                    labels[i].identify = i;
                    labels[i].number = i;
                    labels[i].setSize(chunks[0].getWidth(),
                            chunks[0].getHeight());
                    ImageIcon imageIcon = new ImageIcon(chunks[i]);
                    labels[i].setIcon(imageIcon);
//                    labels[i].setOpaque(false);
                    labels[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    labels[i].setLocation(0+chunks[0].getWidth(),0+chunks[0].getHeight());
                    labels[i].addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            super.mouseClicked(e);
                            win();
                            if(OneLabelBeChoosed == 0){
                                OneLabelBeChoosed = 1;
                                if(e.getSource() instanceof LabelsChunk){
                                    PictureChunkBox[0]=((LabelsChunk)e.getSource()).identify;
                                }
                                return ;
                            }else{
                                if(e.getSource() instanceof LabelsChunk){
                                    PictureChunkBox[1]=((LabelsChunk)e.getSource()).identify;
                                }
                                exchange();
                                OneLabelBeChoosed = 0;
                                win();
                            }
                        }
                    });
                    add(labels[i]);
            }
            chaos();

            /**
             * When mode == MIDDLE_MODE ,
             * the Mainframe's size should be set to 630*675
             *
             * */
        }else if(mode == MIDDLE_MODE){
            chunks = divid(mode);
            labels = new LabelsChunk[(3*mode)*(3*mode)];
            for(int i=0;i<mode*3*mode*3;i++){
                    labels[i] = new LabelsChunk();
                    labels[i].identify = i;
                    labels[i].number = i;
                    labels[i].setSize(chunks[0].getWidth(),
                            chunks[0].getHeight());
                    ImageIcon imageIcon = new ImageIcon(chunks[i]);
                    labels[i].setIcon(imageIcon);
//                    labels[i].setOpaque(false);
                    labels[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    labels[i].setLocation(0+chunks[0].getWidth(),0+chunks[0].getHeight());
                    labels[i].addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            super.mouseClicked(e);
                            win();
                            if(OneLabelBeChoosed == 0){
                                OneLabelBeChoosed = 1;
                                if(e.getSource() instanceof LabelsChunk){
                                    PictureChunkBox[0]=((LabelsChunk)e.getSource()).identify;
                                }
                                return ;
                            }else{
                                if(e.getSource() instanceof LabelsChunk){
                                    PictureChunkBox[1]=((LabelsChunk)e.getSource()).identify;
                                }
                                exchange();
                                OneLabelBeChoosed = 0;
                                win();
                            }
                        }
                    });
                    add(labels[i]);
            }
            chaos();

            /**
             * When mode == HARD_MODE ,
             * the Mainframe's size should be set to 650*695
             *
             * */
        }else if(mode == HARD_MODE){
            chunks = divid(mode);
            labels = new LabelsChunk[(3*mode)*(3*mode)];
            for(int i=0;i<mode*3*mode*3;i++){
                labels[i] = new LabelsChunk();
                labels[i].identify = i;
                labels[i].number = i;
                labels[i].setSize(chunks[0].getWidth(),
                        chunks[0].getHeight());
                ImageIcon imageIcon = new ImageIcon(chunks[i]);
                labels[i].setIcon(imageIcon);
//                    labels[i].setOpaque(false);
                labels[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                labels[i].setLocation(0+chunks[0].getWidth(),0+chunks[0].getHeight());
                labels[i].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        super.mouseClicked(e);
                        win();
                        if(OneLabelBeChoosed == 0){
                            OneLabelBeChoosed = 1;
                            if(e.getSource() instanceof LabelsChunk){
                                PictureChunkBox[0]=((LabelsChunk)e.getSource()).identify;
                            }
                            return ;
                        }else{
                            if(e.getSource() instanceof LabelsChunk){
                                PictureChunkBox[1]=((LabelsChunk)e.getSource()).identify;
                            }
                            exchange();
                            OneLabelBeChoosed = 0;
                            win();
                        }
                    }
                });
                add(labels[i]);
            }
            chaos();
        }

    }

    public BufferedImage[]divid(int mode) throws Exception{
            BufferedImage imagei = new BufferedImage(580, 580,BufferedImage.TYPE_INT_RGB );
            imagei.getGraphics().drawImage(image, 0, 0, 580, 580, null);
            image=imagei;
            int col = 3 * mode;
            int row = 3 * mode;
            int len = image.getWidth() / col;
            BufferedImage[][] subimage = new BufferedImage[row][col];
            for (int i = 0; i < row; i++)
                for (int j = 0; j < col; j++)
                    subimage[i][j] = image.getSubimage(j*len,
                            i*len, len, len);
            BufferedImage[] subimage1 = new BufferedImage[row*col];
            for(int i=0;i<row*col;i++){
                subimage1[i] = subimage[(int)(i/(3*mode))][i-(3*mode)*(int)(i/(3*mode))];
            }
            return subimage1;
    }

    public void exchange(){
        Boolean flag = false;
        if(modeNumber == EASY_MODE){
            for(int i=0;i<9;i++){
                for(int j=0;j<9;j++){
                    if(PictureChunkBox[0]==i&&PictureChunkBox[1]==j){
                        LabelsChunk temp = new LabelsChunk();
                        temp.number = labels[i].number;
                        temp.setIcon(labels[i].getIcon());
                        temp.Xlocation = labels[i].getX();
                        temp.Ylocation = labels[i].getY();
                        labels[i].number = labels[j].number;
                        labels[i].Xlocation = labels[j].Xlocation;
                        labels[i].Ylocation = labels[j].Ylocation;
                        labels[i].setIcon(labels[j].getIcon());

                        labels[j].number = temp.number;
                        labels[j].Xlocation = temp.Xlocation;
                        labels[j].Ylocation = temp.Ylocation;
                        labels[j].setIcon(temp.getIcon());
                        flag = true;
                        break;
                    }
                }
                if(flag){
                    flag = false;
                    break;
                }
            }
        }else if(modeNumber == MIDDLE_MODE){
            for(int i=0;i<36;i++){
                for(int j=0;j<36;j++){
                    if(PictureChunkBox[0]==i&&PictureChunkBox[1]==j){
                        LabelsChunk temp = new LabelsChunk();
                        temp.number = labels[i].number;
                        temp.setIcon(labels[i].getIcon());
                        temp.Xlocation = labels[i].getX();
                        temp.Ylocation = labels[i].getY();
                        labels[i].number = labels[j].number;
                        labels[i].Xlocation = labels[j].Xlocation;
                        labels[i].Ylocation = labels[j].Ylocation;
                        labels[i].setIcon(labels[j].getIcon());

                        labels[j].number = temp.number;
                        labels[j].Xlocation = temp.Xlocation;
                        labels[j].Ylocation = temp.Ylocation;
                        labels[j].setIcon(temp.getIcon());
                        flag = true;
                        break;
                    }
                }
                if(flag){
                    flag = false;
                    break;
                }
            }
        }else if(modeNumber == HARD_MODE){
            for(int i=0;i<81;i++){
                for(int j=0;j<81;j++){
                    if(PictureChunkBox[0]==i&&PictureChunkBox[1]==j){
                        LabelsChunk temp = new LabelsChunk();
                        temp.number = labels[i].number;
                        temp.setIcon(labels[i].getIcon());
                        temp.Xlocation = labels[i].getX();
                        temp.Ylocation = labels[i].getY();
                        labels[i].number = labels[j].number;
                        labels[i].Xlocation = labels[j].Xlocation;
                        labels[i].Ylocation = labels[j].Ylocation;
                        labels[i].setIcon(labels[j].getIcon());

                        labels[j].number = temp.number;
                        labels[j].Xlocation = temp.Xlocation;
                        labels[j].Ylocation = temp.Ylocation;
                        labels[j].setIcon(temp.getIcon());
                        flag = true;
                        break;
                    }
                }
                if(flag){
                    flag = false;
                    break;
                }
            }
        }
    }

    public void chaos(){
        Random random = new Random();
        if(modeNumber == EASY_MODE){
            for(int i=0;i<9;i++){
                int randnum = random.nextInt(9);
                LabelsChunk temp1 = new LabelsChunk();
                temp1.number = labels[i].number;
                temp1.setIcon(labels[i].getIcon());
                temp1.Xlocation = labels[i].getX();
                temp1.Ylocation = labels[i].getY();
                labels[i].number = labels[randnum].number;
                labels[i].Xlocation = labels[randnum].Xlocation;
                labels[i].Ylocation = labels[randnum].Ylocation;
                labels[i].setIcon(labels[randnum].getIcon());

                labels[randnum].number = temp1.number;
                labels[randnum].Xlocation = temp1.Xlocation;
                labels[randnum].Ylocation = temp1.Ylocation;
                labels[randnum].setIcon(temp1.getIcon());
            }
        }else if(modeNumber == MIDDLE_MODE){
            for(int i=0;i<36;i++){
                int randnum = random.nextInt(9);
                LabelsChunk temp1 = new LabelsChunk();
                temp1.number = labels[i].number;
                temp1.setIcon(labels[i].getIcon());
                temp1.Xlocation = labels[i].getX();
                temp1.Ylocation = labels[i].getY();
                labels[i].number = labels[randnum].number;
                labels[i].Xlocation = labels[randnum].Xlocation;
                labels[i].Ylocation = labels[randnum].Ylocation;
                labels[i].setIcon(labels[randnum].getIcon());

                labels[randnum].number = temp1.number;
                labels[randnum].Xlocation = temp1.Xlocation;
                labels[randnum].Ylocation = temp1.Ylocation;
                labels[randnum].setIcon(temp1.getIcon());
            }
        }else if(modeNumber == HARD_MODE){
            for(int i=0;i<81;i++){
                int randnum = random.nextInt(9);
                LabelsChunk temp1 = new LabelsChunk();
                temp1.number = labels[i].number;
                temp1.setIcon(labels[i].getIcon());
                temp1.Xlocation = labels[i].getX();
                temp1.Ylocation = labels[i].getY();
                labels[i].number = labels[randnum].number;
                labels[i].Xlocation = labels[randnum].Xlocation;
                labels[i].Ylocation = labels[randnum].Ylocation;
                labels[i].setIcon(labels[randnum].getIcon());

                labels[randnum].number = temp1.number;
                labels[randnum].Xlocation = temp1.Xlocation;
                labels[randnum].Ylocation = temp1.Ylocation;
                labels[randnum].setIcon(temp1.getIcon());
            }
        }
    }

    public void win(){
        Boolean isWin = false;
        if(modeNumber == EASY_MODE){
            for(int i=0;i<9;i++){
                if(labels[i].identify==labels[i].number){
                    isWin = true;
                }else {
                    isWin = false;
                    break;
                }
            }
            if(isWin){
                JOptionPane.showMessageDialog(this,"Congratulation!",
                        "You win",JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }else if(modeNumber == MIDDLE_MODE){
            for(int i=0;i<36;i++){
                if(labels[i].identify==labels[i].number){
                    isWin = true;
                }else {
                    isWin = false;
                    break;
                }
            }
            if(isWin){
                JOptionPane.showMessageDialog(this,"Congratulation!",
                        "You win",JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }else if(modeNumber == HARD_MODE){
            for(int i=0;i<81;i++){
                if(labels[i].identify==labels[i].number){
                    isWin = true;
                }else {
                    isWin = false;
                    break;
                }
            }
            if(isWin){
                JOptionPane.showMessageDialog(this,"Congratulation!",
                        "You win",JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }

    }
    ///
}

