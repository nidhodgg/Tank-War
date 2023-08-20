package tankgame5;//

import java.util.Vector;

/**
 * @author 向忠龙
 * @version 1.0
 * 敌人的tank
 */
public class Enemytank extends tank implements Runnable {
    //Vector保存多个Shot
    Vector<Shot> shots = new Vector<>();
    boolean isLive = true;
    //增加成员，Enemytank 可以得到敌人tank的Vector
    Vector<Enemytank> enemytanks = new Vector<>();


    public Enemytank(int x, int y) {
        super(x, y);
    }

    //提供一个方法，可以把panel的成员Vector<Enemytank> enemytanks = new Vector<>();
    //设置到Enemytank的成员enemytanks
    public void setEnemytanks(Vector<Enemytank> enemytanks) {
        this.enemytanks = enemytanks;
    }

    //编写方法判断当前这个敌人的tank是否和enemytanks集合中的其他tank发生了碰撞
    public boolean isTouchEnemytank() {
        //判断当前敌人tank（this.）方向
        switch (this.getDirect()) {
            case 0://上
                //让当前的敌人tank和其他所有的敌人tank比较
                for (int i = 0; i < enemytanks.size(); i++) {
                    //从Vector中取出一个敌人的tank
                    Enemytank enemytank = enemytanks.get(i);
                    //不和自己比较
                    if (enemytank != this) {
                        //如果敌人tank是上下方向
                        //x的范围
                        //y的范围
                        if (enemytank.getDirect() == 0 || enemytank.getDirect() == 2) {
                            //当前tank的左上角坐标
                            if (this.getX() >= enemytank.getX() &&
                                    this.getX() <= enemytank.getX() + 40 &&
                                    this.getY() >= enemytank.getY() &&
                                    this.getY() <= enemytank.getY() + 60) {
                                return true;
                            }
                            //当前tank的右上角坐标
                            if (this.getX() + 40 >= enemytank.getX() &&
                                    this.getX() + 40 <= enemytank.getX() + 40 &&
                                    this.getY() >= enemytank.getY() &&
                                    this.getY() <= enemytank.getY() + 60) {
                                return true;
                            }
                        }
                        //如果敌人tank是左右方向
                        //x的范围
                        //y的范围
                        if (enemytank.getDirect() == 1 || enemytank.getDirect() == 3) {
                            //当前tank的左上角坐标
                            if (this.getX() >= enemytank.getX() &&
                                    this.getX() <= enemytank.getX() + 60 &&
                                    this.getY() >= enemytank.getY() &&
                                    this.getY() <= enemytank.getY() + 40) {
                                return true;
                            }
                            //当前tank的右上角坐标
                            if (this.getX() + 40 >= enemytank.getX() &&
                                    this.getX() + 40 <= enemytank.getX() + 60 &&
                                    this.getY() >= enemytank.getY() &&
                                    this.getY() <= enemytank.getY() + 40) {
                                return true;
                            }
                        }
                    }
                }
                break;
            case 1://右
                //让当前的敌人tank和其他所有的敌人tank比较
                for (int i = 0; i < enemytanks.size(); i++) {
                    //从Vector中取出一个敌人的tank
                    Enemytank enemytank = enemytanks.get(i);
                    //不和自己比较
                    if (enemytank != this) {
                        //如果敌人tank是上下方向
                        //x的范围
                        //y的范围
                        if (enemytank.getDirect() == 0 || enemytank.getDirect() == 2) {
                            //当前tank的右上角坐标
                            if (this.getX() + 60 >= enemytank.getX() &&
                                    this.getX() + 60 <= enemytank.getX() + 40 &&
                                    this.getY() >= enemytank.getY() &&
                                    this.getY() <= enemytank.getY() + 60) {
                                return true;
                            }
                            //当前tank的右下角坐标
                            if (this.getX() + 60 >= enemytank.getX() &&
                                    this.getX() + 60 <= enemytank.getX() + 40 &&
                                    this.getY() + 60 >= enemytank.getY() &&
                                    this.getY() + 40 <= enemytank.getY() + 60) {
                                return true;
                            }
                        }
                        //如果敌人tank是右左方向
                        //x的范围
                        //y的范围
                        if (enemytank.getDirect() == 1 || enemytank.getDirect() == 3) {
                            //当前tank的左上角坐标
                            if (this.getX() + 60 >= enemytank.getX() &&
                                    this.getX() + 60 <= enemytank.getX() + 60 &&
                                    this.getY() >= enemytank.getY() &&
                                    this.getY() <= enemytank.getY() + 40) {
                                return true;
                            }
                            //当前tank的右上角坐标
                            if (this.getX() + 60 >= enemytank.getX() &&
                                    this.getX() + 60 <= enemytank.getX() + 60 &&
                                    this.getY() + 40 >= enemytank.getY() &&
                                    this.getY() + 40 <= enemytank.getY() + 40) {
                                return true;
                            }
                        }
                    }
                }
                break;
            case 2://下
                //让当前的敌人tank和其他所有的敌人tank比较
                for (int i = 0; i < enemytanks.size(); i++) {
                    //从Vector中取出一个敌人的tank
                    Enemytank enemytank = enemytanks.get(i);
                    //不和自己比较
                    if (enemytank != this) {
                        //如果敌人tank是上下方向
                        //x的范围
                        //y的范围
                        if (enemytank.getDirect() == 0 || enemytank.getDirect() == 2) {
                            //当前tank的左下角坐标
                            if (this.getX() >= enemytank.getX() &&
                                    this.getX() <= enemytank.getX() + 40 &&
                                    this.getY() + 60 >= enemytank.getY() &&
                                    this.getY() + 60 <= enemytank.getY() + 60) {
                                return true;
                            }
                            //当前tank的右下角坐标
                            if (this.getX() + 40 >= enemytank.getX() &&
                                    this.getX() + 40 <= enemytank.getX() + 40 &&
                                    this.getY() + 60 >= enemytank.getY() &&
                                    this.getY() + 60 <= enemytank.getY() + 60) {
                                return true;
                            }
                        }
                        //如果敌人tank是右左方向
                        //x的范围
                        //y的范围
                        if (enemytank.getDirect() == 1 || enemytank.getDirect() == 3) {
                            //当前tank的左下角坐标
                            if (this.getX() >= enemytank.getX() &&
                                    this.getX() <= enemytank.getX() + 60 &&
                                    this.getY() + 60 >= enemytank.getY() &&
                                    this.getY() + 60 <= enemytank.getY() + 40) {
                                return true;
                            }
                            //当前tank的右下角坐标
                            if (this.getX() + 40 >= enemytank.getX() &&
                                    this.getX() + 40 <= enemytank.getX() + 60 &&
                                    this.getY() + 60 >= enemytank.getY() &&
                                    this.getY() + 60 <= enemytank.getY() + 40) {
                                return true;
                            }
                        }
                    }
                }
                break;
            case 3://左
                //让当前的敌人tank和其他所有的敌人tank比较
                for (int i = 0; i < enemytanks.size(); i++) {
                    //从Vector中取出一个敌人的tank
                    Enemytank enemytank = enemytanks.get(i);
                    //不和自己比较
                    if (enemytank != this) {
                        //如果敌人tank是上下方向
                        //x的范围
                        //y的范围
                        if (enemytank.getDirect() == 0 || enemytank.getDirect() == 2) {
                            //当前tank的左上角坐标
                            if (this.getX() >= enemytank.getX() &&
                                    this.getX() <= enemytank.getX() + 40 &&
                                    this.getY() >= enemytank.getY() &&
                                    this.getY() <= enemytank.getY() + 60) {
                                return true;
                            }
                            //当前tank的左下角坐标
                            if (this.getX() >= enemytank.getX() &&
                                    this.getX() <= enemytank.getX() + 40 &&
                                    this.getY() + 40 >= enemytank.getY() &&
                                    this.getY() + 40 <= enemytank.getY() + 60) {
                                return true;
                            }
                        }
                        //如果敌人tank是右左方向
                        //x的范围
                        //y的范围
                        if (enemytank.getDirect() == 1 || enemytank.getDirect() == 3) {
                            //当前tank的左上角坐标
                            if (this.getX() >= enemytank.getX() &&
                                    this.getX() <= enemytank.getX() + 60 &&
                                    this.getY() >= enemytank.getY() &&
                                    this.getY() <= enemytank.getY() + 40) {
                                return true;
                            }
                            //当前tank的左下角坐标
                            if (this.getX() >= enemytank.getX() &&
                                    this.getX() <= enemytank.getX() + 60 &&
                                    this.getY() + 40 >= enemytank.getY() &&
                                    this.getY() + 40 <= enemytank.getY() + 40) {
                                return true;
                            }
                        }
                    }
                }
                break;
        }
        return false;
    }

    @Override
    public void run() {
        while (true) {
            //这里判断shots size如果等于0 = 集合没有子弹,创建一个子弹放入shots集合，并启动
            if (isLive && shots.size() == 0) {
                Shot shot = null;
                switch (getDirect()) {
                    //判断tank的方向，创建对应的子弹
                    case 0://上
                        shot = new Shot(getX() + 20, getY(), 0);
                        break;
                    case 1://右
                        shot = new Shot(getX() + 60, getY() + 20, 1);
                        break;
                    case 2://下
                        shot = new Shot(getX() + 20, getY() + 60, 2);
                        break;
                    case 3://左
                        shot = new Shot(getX(), getY() + 20, 3);
                        break;

                }
                shots.add(shot);
                //启动
                new Thread(shot).start();
            }
            //根据tank方向来继续移动
            switch (getDirect()) {
                case 0://上
                    //让tank保持一个方向行走30步
                    for (int i = 0; i < 30; i++) {
                        if (getY() > 0 && !isTouchEnemytank()) {
                            moveUp();
                        }
                        //休眠
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 1://右
                    for (int i = 0; i < 30; i++) {
                        if (getX() + 60 < 1000 && !isTouchEnemytank()) {
                            moveRight();
                        }
                        //休眠
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 2://下
                    for (int i = 0; i < 30; i++) {
                        if (getY() + 60 < 750 && !isTouchEnemytank()) {
                            moveDown();
                        }
                        //休眠
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 3://左
                    for (int i = 0; i < 30; i++) {
                        if (getX() > 0 && !isTouchEnemytank()) {
                            moveLeft();
                        }
                        //休眠
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
            }

            //随机改变tank方向
            setDirect((int) (Math.random() * 4));
            //什么时候退出多线程——一定要考虑！！！
            if (!isLive) {
                break;
            }
        }
    }
}
