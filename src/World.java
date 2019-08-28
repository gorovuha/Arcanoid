import java.awt.*;
import java.util.ArrayList;

public class World {
    Ball ball = new Ball();                         //в мире живут объекты игры - шар, стена кирпичей и платформа
    ArrayList<Brick> bricks = new ArrayList<>();
    Platform platform = new Platform();
    int radius = ball.ballRadius/2; //настоящий радиус мяча
    int lives = 3; //максимальное количество жизней

    //в мире рисуются все объекты
    public void draw(Graphics g){
        g.drawString(String.valueOf(lives), 50 + 200, 25);
        ball.draw(g);
        for (int i = 0; i < bricks.size(); ++i) {
            Color newColor = new Color(0, 215, 255);
            bricks.get(i).draw(g, newColor);
        }
        platform.draw(g);
    }


    //проверка коллизий, из названия ясно, что каждый метод проверяет
    void checkBallPlatformCollisions(){
        if ((ball.x >= platform.x) && (ball.x + ball.ballRadius <= (platform.x + platform.width)) && (ball.y+ball.ballRadius == platform.y) ){
            ball.vy = ball.vy*(-1); // чтобы от платформы отскочила
            ball.vx *= -1;          //??? как заставить мяч начинать движение через время и в сторону движения платформы
        }
    }

    void checkBallWallCollisions() throws InterruptedException {
        //чтобы от потолка отскочил
        if (ball.y <= 50){
            ball.vy = ball.vy*(-1);
        }
        //чтобы от бока отскочил
        if ((ball.x + ball.ballRadius >= 50 + 400) || (ball.x <= 50)){
            ball.vx = ball.vx *(-1);
        }
        //упал на пол
        if (ball.y + ball.ballRadius >= 50 + 400){
            platform.x = 50 + 200 - 20;
            ball.x = 50 + 200 - radius;
            ball.y = platform.y - ball.ballRadius;
            ball.vy = -2;
            lives -= 1;
        }
    }

    boolean isBallInsideBrickBottom(Brick brick, Ball ball){
        if (ball.x + radius >= brick.x && ball.x + radius <= brick.x +brick.width && ball.y <= brick.y + brick.height && ball.y + radius >= brick.y + brick.height){
            return true;
        } else{
            return false;
        }
    }

    boolean isBallInsideBrickRight(Brick brick, Ball ball){
        if (ball.y + radius >= brick.y && ball.y + radius <= brick.y + brick.height && ball.x <= brick.x + brick.width && ball.x + radius >= brick.x + brick.width){
            return true;
        }else{
            return false;
        }
    }

    boolean isBallInsideBrickLeft(Brick brick, Ball ball){
        if (ball.y + radius >= brick.y && ball.y + radius <= brick.y + brick.height && ball.x + ball.ballRadius >= brick.x && ball.x + radius <= brick.x){
            return true;
        } else {
            return false;
        }
    }

    boolean isBallInsideBrickTop(Brick brick, Ball ball){
        if (ball.x + radius >= brick.x && ball.x + radius <= brick.x +brick.width && ball.y + ball.ballRadius >= brick.y && ball.y + radius <= brick.y){
            return true;
        } else {
            return false;
        }
    }


    void checkBallBricksCollisions(){
        for (int i = 0; i < bricks.size(); i++) {
            //проверяем в какую стенку кирпича вошло и меняем скорость
            if (isBallInsideBrickBottom(bricks.get(i), ball) || isBallInsideBrickTop(bricks.get(i), ball)) {
                ball.vy *= -1;
                bricks.get(i).solidity -= 1;
            }
            if (isBallInsideBrickRight(bricks.get(i), ball) || isBallInsideBrickLeft(bricks.get(i), ball)) {
                ball.vx *= -1;
                bricks.get(i).solidity -= 1;
            }
            if (bricks.get(i).solidity == 0) {
                bricks.remove(i);
            }
        }
    }

    //когда жизни кончились
    void delete(Graphics g){
        bricks.clear();
        ball.vx = 0;
        ball.vy = 0;
        g.drawString("Game end", 200, 250);
    }

    //когда кирпичи кончились
    void end (Graphics g){
        ball.vx = 0;
        ball.vy = 0;
        platform.x = 50 + 200 - 20;
        ball.x = 50 + 200 - radius;
        ball.y = platform.y - ball.ballRadius;
        g.drawString("You are the winner", 200, 250);
    }

    //проверяем всё, что есть, и не обновляем мяч, чтобы он летел
    public void updateWorld() throws InterruptedException {
        checkBallPlatformCollisions();
        checkBallWallCollisions();
        checkBallBricksCollisions();

        ball.update();
    }
}
