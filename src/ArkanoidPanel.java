import javax.swing.*;
import java.awt.*;

public class ArkanoidPanel extends JPanel {
    World world = new World(); //здесь создаётся единичный уникальный мир игры, где всё происходит. Он сидит на панели. которая выглядывает в окно

    @Override
    //рисуем по-настоящему
    protected void paintComponent(Graphics g){
        //проверка жизней и наличие кирпичей
        if (world.lives > 0){
            if (world.bricks.size() == 0) {
                world.end(g);
            } else {
                world.draw(g);
            }
        } else{
            world.delete(g);
            world.draw(g);
        }
        //рамка, чтобы было ясно, где летает мяч
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3.0f));  // широкая и заметная рамка, толщина равна 10
        g2.drawRect(50, 50, 400, 400);
    }
}
