import java.awt.*;

public class Ball {
    int x = 250+5; //координаты шара
    int y = 400-10;
    int ballRadius = 10; //диаметр, на самом деле
    int vx = 3; //скорость как суперпозиция по x и y
    int vy = 2;

    //изменяет координаты на величину скорости каждую отрисовку
    public void update(){
        x = x + vx;
        y = y + vy;
    }

    //рисует мяч
    public void draw(Graphics g){
        g.fillOval(x, y, ballRadius, ballRadius);
    }
}
