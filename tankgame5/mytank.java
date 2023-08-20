package tankgame5;//

import java.util.Vector;

/**
 * @author 向忠龙
 * @version 1.0
 * 我的tank
 */
public class mytank extends tank {
    //定义一个shot对象,表示一个射击行为
    Shot shot = null;
    //可以发射多颗子弹
    Vector<Shot> shots = new Vector<>();

    public mytank(int x, int y) {
        super(x, y);
    }
    //射击
    public void shotEnemytank(){
        //最多一次性射出5颗子弹
        if (shots.size() ==5){
            return;
        }
        //创建shot对象，根据我的tank对象位置和方向
        switch (getDirect()){//得到我的tank方向
            case 0://上
                shot = new Shot(getX()+20,getY(),0);
                break;
            case 1://右
                shot = new Shot(getX()+60,getY()+20,1);
                break;
            case 2://下
                shot = new Shot(getX()+20,getY()+60,2);
                break;
            case 3://左
                shot = new Shot(getX(),getY()+20,3);
                break;

        }
        //把新创建的shot（可以发出多颗子弹）放入shots集合中
        shots.add(shot);
        //启动射击线程Shot
        new Thread(shot).start();
    }

}
