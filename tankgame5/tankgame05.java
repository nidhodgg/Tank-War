package tankgame5;//

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;

/**
 * @author 向忠龙
 * @version 1.0
 * 开启游戏
 */
public class tankgame05 extends JFrame {
    //定义画板
    panel mp = null;

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        tankgame05 tankgame1 = new tankgame05();
    }

    public tankgame05() {
        System.out.println("请输入选择\n1:新游戏\n2:继续游戏");
        String key = scanner.next();
        if (key == "1" || key == "2") {
            mp = new panel(key);
        } else {
            return;
        }

        //将mp放入到Thread并启用
        Thread thread = new Thread(mp);
        thread.start();

        this.add(mp);//添加面板(游戏的绘图区域)
        this.setSize(1300, 950);//设置大小
        this.addKeyListener(mp);//增加监听事件 监听mp
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭程序
        this.setVisible(true);//显示

        //在JFrame中增加监听关闭窗口
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Recorder.keepRecord();
                System.exit(0);
            }
        });
    }

}
