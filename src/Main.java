import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        //создаём окно
        JFrame frame = new JFrame();

        //делаем панель
        ArkanoidPanel panel = new ArkanoidPanel();
        frame.add(panel);

        //слушаеми мышь
        panel.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {                //мышь передвигает платформу
                if (e.getX() >= 50 && e.getX() <= 400+50-40){
                    panel.world.platform.x = e.getX();
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });

        //рутина с окном
        frame.setVisible(true);
        frame.setTitle("Arkanoid");
        frame.setSize(500, 550); //???абсолютно непонятные мне пляски с размером окна в зависимости от рамки
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //закидываем брики в масив бриков
        //чтобы было удобно менять, если что
        int brickWidth = 29; //ширина
        int space = 2;       //пробел между бриками
        int brickHeight = 20;//высота
        int solidity = 6;    //максимальная прочность, тк по задумке она извеняется с уровнем
        for (int i = 0; i < 6; i++) { //как матрицу заполинять удобно
            for (int j = 0; j < 12; j++) {
                Color newColor = new Color(0, 215, 255); //цвет красивый
                panel.world.bricks.add(new Brick(65+j*(brickWidth+space), (brickHeight + space)*i+65, solidity-i, brickWidth, brickHeight, newColor));
            }
        }

        //как обычно, перерисовка и спать
        while(true){
            frame.repaint();
            Thread.sleep(30);
            panel.world.updateWorld();
        }
    }

}
