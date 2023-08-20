package tankgame5;//

import sun.awt.image.ToolkitImage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

/**
 * @author 向忠龙
 * @version 1.0
 * 绘图区
 */
//为了监听键盘事件，也就是用键盘控制tank的方向和移动
//为了让panel不停的重绘子弹，需要将panel实现Runnable，当做一个线程
public class panel extends JPanel implements KeyListener, Runnable {

    //定义我的tank-画出我的tank
    tankgame5.mytank mytank = null;

    //定义敌人tank放入到集合中Vector
    Vector<Enemytank> enemytanks = new Vector<>();
    //定义一个存放Node对象的Vector用于恢复敌人tank的坐标和方向
    Vector<Node> nodes = new Vector<>();

    int enemytankSize = 3;//敌人坦克的个数

    //定义炸弹Bomb放入集合Vector
    //当子弹击中了tank时，就加入Bomb对象到bombs
    Vector<Bomb> bombs = new Vector<>();

    //定义三张炸弹图片，用于显示爆炸
    Image image1 = null;
    Image image2 = null;
    Image image3 = null;


    public panel(String key) {
        nodes = Recorder.getNodesAndallEnemyTankNum();
        //panel对象的 enemytanks 设置给Record的 enemytanks
        Recorder.setEnemytanks(enemytanks);

        //初始化我的tank
        mytank = new mytank(500, 100);//初始化我的tank位置
        mytank.setSpeed(10);//初始化我的tank速度
        switch (key) {
            case "1"://新游戏
                //初始化敌人的tank
                for (int i = 0; i < enemytankSize; i++) {
                    //创建一个敌人tank
                    Enemytank enemytank = new Enemytank(100 * (i + 1), 0);
                    //将enemytanks设置给 Enemytank对象!!!
                    enemytank.setEnemytanks(enemytanks);
                    //初始化敌人tank位置向下
                    enemytank.setDirect(2);
                    //启动敌人tank线程
                    new Thread(enemytank).start();
                    //初始化敌人tank的子弹
                    Shot shot = new Shot(enemytank.getX() + 20, enemytank.getY() + 60, enemytank.getDirect());
                    //加入集合
                    enemytank.shots.add(shot);
                    //启动线程
                    new Thread(shot).start();
                    //加入集合
                    enemytanks.add(enemytank);
                }
                break;
            case "2"://继续游戏
                //初始化敌人的tank
                for (int i = 0; i < nodes.size(); i++) {
                    Node node = nodes.get(i);
                    //创建一个敌人tank
                    Enemytank enemytank = new Enemytank(node.getX(), node.getY());
                    //将enemytanks设置给 Enemytank对象!!!
                    enemytank.setEnemytanks(enemytanks);
                    //初始化敌人tank位置向下
                    enemytank.setDirect(node.getDirect());
                    //启动敌人tank线程
                    new Thread(enemytank).start();
                    //初始化敌人tank的子弹
                    Shot shot = new Shot(enemytank.getX() + 20, enemytank.getY() + 60, enemytank.getDirect());
                    //加入集合
                    enemytank.shots.add(shot);
                    //启动线程
                    new Thread(shot).start();
                    //加入集合
                    enemytanks.add(enemytank);
                }
                break;
            default:
                System.out.println("你的输入有误");
        }
        //初始化炸弹图片
        image1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_1.gif"));
        image2 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_2.gif"));
        image3 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_3.gif"));

    }

    //编写方法显示我方击毁敌方tank的信息
    public void showInfo(Graphics g) {
        //画出玩家的总成绩
        g.setColor(Color.black);
        Font font = new Font("宋体", Font.BOLD, 25);
        g.setFont(font);

        g.drawString("累计击毁敌方坦克", 1020, 30);
        drawTank(1020, 60, g, 0, 0);//画出一个敌方tank
        g.setColor(Color.black);
        g.drawString(Recorder.getAllEnemyTankNum() + "", 1080, 100);

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0, 0, 1000, 750);//填充矩形
        showInfo(g);
        //判断我的tank是否已经被击中
        if (mytank != null && mytank.isLive) {
            //画出我的tank-封装
            drawTank(mytank.getX(), mytank.getY(), g, mytank.getDirect(), 0);
        }
        //画出我的tank射出的子弹
        //将mytank的子弹集合shots遍历集合取出绘制
        for (int i = 0; i < mytank.shots.size(); i++) {
            Shot shot = mytank.shots.get(i);
            if (shot != null && shot.isLive == true) {
                g.fill3DRect(shot.x, shot.y, 3, 3, false);
            } else {//如果该shot对象已经被销毁了，就从shots集合中删除
                mytank.shots.remove(shot);
            }
        }

        //画出敌人的tank，遍历Vector
        for (int i = 0; i < enemytanks.size(); i++) {
            //取出一个tank
            Enemytank enemytank = enemytanks.get(i);
            //判断当前tank是否存活
            if (enemytank.isLive) {//敌人tank是存活的才去画出
                drawTank(enemytank.getX(), enemytank.getY(), g, enemytank.getDirect(), 1);
                //画出敌人的tank的子弹
                for (int j = 0; j < enemytank.shots.size(); j++) {
                    //取出子弹
                    Shot shot = enemytank.shots.get(j);
                    //绘制
                    if (shot.isLive) {
                        g.fill3DRect(shot.x, shot.y, 3, 3, false);
                    } else {
                        //从Vector移除销毁的子弹
                        enemytank.shots.remove(shot);
                    }
                }
            }
        }
        //如果bombs集合中有对象就画出
        for (int i = 0; i < bombs.size(); i++) {
            //取出炸弹
            Bomb bomb = bombs.get(i);
            //根据对象的life值去判断
            if (bomb.life > 6) {
                g.drawImage(image1, bomb.x, bomb.y, 60, 60, this);
            } else if (bomb.life > 3) {
                g.drawImage(image2, bomb.x, bomb.y, 60, 60, this);
            } else {
                g.drawImage(image3, bomb.x, bomb.y, 60, 60, this);
            }
            //让炸弹的生命值减少
            bomb.lifeDown();
            //如果bomb life为0，就从bombs集合中删除
            if (bomb.life == 0) {
                bombs.remove(bomb);
            }
        }
    }

    /**
     * @param x      tank左上角x坐标
     * @param y      tank左上角y坐标
     * @param g      画笔
     * @param direct tank方向
     * @param type   tank类型（敌人或自己）
     */
    public void drawTank(int x, int y, Graphics g, int direct, int type) {
        //根据tank的类型不同设置不同的颜色
        switch (type) {
            case 0: //我们的tank
                g.setColor(Color.cyan);
                break;
            case 1: //敌人的tank
                g.setColor(Color.yellow);
                break;
        }
        //根据tank方向来绘制不同的tank
        //direct表示方向（0上，1右，2下，3左）来表示不同的方向
        switch (direct) {
            //绘制tank
            case 0://向上
                g.fill3DRect(x, y, 10, 60, false);//tank左边的轮子
                g.fill3DRect(x + 30, y, 10, 60, false);//tank右边的轮子
                g.fill3DRect(x + 10, y + 10, 20, 40, false);//tank的盖子
                g.fillOval(x + 10, y + 20, 20, 20);//tank的炮台
                g.drawLine(x + 20, y + 30, x + 20, y);//tank的炮管
                break;
            case 1://向右
                g.fill3DRect(x, y, 60, 10, false);//tank上边的轮子
                g.fill3DRect(x, y + 30, 60, 10, false);//tank下边的轮子
                g.fill3DRect(x + 10, y + 10, 40, 20, false);//tank的盖子
                g.fillOval(x + 20, y + 10, 20, 20);//tank的炮台
                g.drawLine(x + 30, y + 20, x + 60, y + 20);//tank的炮管
                break;
            case 2://向下
                g.fill3DRect(x, y, 10, 60, false);//tank左边的轮子
                g.fill3DRect(x + 30, y, 10, 60, false);//tank右边的轮子
                g.fill3DRect(x + 10, y + 10, 20, 40, false);//tank的盖子
                g.fillOval(x + 10, y + 20, 20, 20);//tank的炮台
                g.drawLine(x + 20, y + 30, x + 20, y + 60);//tank的炮管
                break;
            case 3://向左
                g.fill3DRect(x, y, 60, 10, false);//tank上边的轮子
                g.fill3DRect(x, y + 30, 60, 10, false);//tank下边的轮子
                g.fill3DRect(x + 10, y + 10, 40, 20, false);//tank的盖子
                g.fillOval(x + 20, y + 10, 20, 20);//tank的炮台
                g.drawLine(x + 30, y + 20, x, y + 20);//tank的炮管
                break;

            default:
                System.out.println("其他情况暂时不处理");
        }
    }

    //如果tank发射多颗子弹，判断我方子弹是否击中时，就需要把所有子弹取出和所有敌人坦克进行比较
    public void hitEnemyTank() {
        //遍历子弹
        for (int j = 0; j < mytank.shots.size(); j++) {
            Shot shot = mytank.shots.get(j);
            //判断是否击中
            if (shot != null && shot.isLive) {//我的子弹还存活
                //遍历敌人所有tank
                for (int i = 0; i < enemytanks.size(); i++) {
                    Enemytank enemytank = enemytanks.get(i);
                    hitTank(shot, enemytank);
                }
            }
        }
        //单颗子弹
        if (mytank.shot != null && mytank.shot.isLive) {//我的子弹还存活
            //遍历敌人所有tank
            for (int i = 0; i < enemytanks.size(); i++) {
                Enemytank enemytank = enemytanks.get(i);
                hitTank(mytank.shot, enemytank);
            }
        }

    }

    //编写方法判断敌方子弹是否击中
    public void hitmyTank() {
        //遍历所有敌人tank
        for (int i = 0; i < enemytanks.size(); i++) {
            //取出敌人tank
            Enemytank enemytank = enemytanks.get(i);
            //遍历enemytank对象的所有子弹
            for (int j = 0; j < enemytank.shots.size(); j++) {
                //取出子弹
                Shot shot = enemytank.shots.get(j);
                //判断shot是否击中我的tank
                if (mytank.isLive && shot.isLive) {
                    hitTank(shot, mytank);
                }
            }

        }
    }

    //编写方法判断我方子弹是否击中
    //什么时候去判断-循环run
    public void hitTank(Shot shot, tank enemytank) {
        //判断shot击中tank
        switch (enemytank.getDirect()) {
            case 0://敌人tank向上
            case 2://敌人tank向下
                if (shot.x > enemytank.getX() && shot.x < enemytank.getX() + 40 &&
                        shot.y > enemytank.getY() && shot.y < enemytank.getY() + 60) {
                    shot.isLive = false;
                    enemytank.isLive = false;
                    //当子弹击中tank之后将enemytank从Vector集合中删除
                    enemytanks.remove(enemytank);
                    //当我们击毁一个敌人tank时候对数据allEnemyTankNum++
                    if (enemytank instanceof Enemytank) {
                        Recorder.addAllEnemyTankNum();
                    }
                    //创建Bomb对象加入bombs集合
                    Bomb bomb = new Bomb(enemytank.getX(), enemytank.getY());
                    bombs.add(bomb);
                }
                break;
            case 1://敌人tank向右
            case 3://敌人tank向左
                if (shot.x > enemytank.getX() && shot.x < enemytank.getX() + 60 &&
                        shot.y > enemytank.getY() && shot.y < enemytank.getY() + 40) {
                    shot.isLive = false;
                    enemytank.isLive = false;
                    //创建Bomb对象加入bombs集合
                    //当子弹击中tank之后将enemytank从Vector集合中删除
                    enemytanks.remove(enemytank);
                    //当我们击毁一个敌人tank时候对数据allEnemyTankNum++
                    if (enemytank instanceof Enemytank) {
                        Recorder.addAllEnemyTankNum();
                    }
                    Bomb bomb = new Bomb(enemytank.getX(), enemytank.getY());
                    bombs.add(bomb);
                }
                break;
        }
    }

    //处理键盘按下的情况
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {//按下w键
            // 改变tank的方向
            mytank.setDirect(0);
            // 修改tank的坐标 - 在tank里设置
            if (mytank.getY() > 0) {
                mytank.moveUp();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_D) {//按下d键
            mytank.setDirect(1);
            if (mytank.getX() + 60 < 1000) {
                mytank.moveRight();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_S) {//按下s键
            mytank.setDirect(2);
            if (mytank.getY() + 60 < 750) {
                mytank.moveDown();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_A) {//按下a键
            mytank.setDirect(3);
            if (mytank.getX() > 0) {
                mytank.moveLeft();
            }
        }
        //如果用户按下的是j就需要发射子弹了
        if (e.getKeyCode() == KeyEvent.VK_J) {
//            //判断mytank的子弹是否销毁,发射一颗子弹
//            if (mytank.shot == null || !mytank.shot.isLive) {
//                mytank.shotEnemytank();
//            }
            mytank.shotEnemytank();

        }
        this.repaint();//面板重绘
    }

    @Override
    public void run() {//每隔0.1s重绘一次

        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //判断我们的子弹是否击中了敌人
            hitEnemyTank();
            //判读敌人的子弹是否击中了我们
            hitmyTank();
            this.repaint();
        }
    }


    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
