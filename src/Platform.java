import java.awt.*;

public class Platform {
    int x = 50+200 - 20; //координаты платформы
    int y = 400;
    int width = 40; //габариты платформы
    int platformHeight = 10;

    //рисует объёмную платформу
    public void draw(Graphics g){
        g.fill3DRect(x, y, width, platformHeight, true);
    }
}
