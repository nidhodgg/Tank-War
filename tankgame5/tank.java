package tankgame5;//

/**
 * @author 向忠龙
 * @version 1.0
 * 全部tank
 */
public class tank {
    private int x;//x坐标-横坐标
    private int y;//y坐标-纵坐标
    private int direct ;//表示tank方向 0 上 1 右 2 下 3 左
    private int speed = 5;//表示tank的速度
    boolean isLive = true;//是否存活

    //向上右下左移动
    public void moveUp() {
        y -= speed;
    }

    public void moveRight() {
        x += speed;
    }

    public void moveDown() {
        y += speed;
    }

    public void moveLeft() {
        x -= speed;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }

    public tank(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
