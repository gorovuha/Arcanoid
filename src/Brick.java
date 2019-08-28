import java.awt.*;

public class Brick {
    int x; //координата кирпича
    int y;
    int width; //габариты кирпича
    int height;
    int solidity; //прочность кирпича
    Color color; //цвет

    //конструктор
    public Brick(int x, int y, int solidity, int width, int height , Color color){
        this.x = x;
        this.y = y;
        this.solidity = solidity;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    //рисует цветной объёмный кирпич
    public void draw(Graphics g, Color color){
        g.setColor(color);
        g.fill3DRect(x, y, width, height, true);
    }
}
