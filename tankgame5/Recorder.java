package tankgame5;//

import java.io.*;
import java.util.SplittableRandom;
import java.util.Vector;

/**
 * @author 向忠龙
 * @version 1.0\
 * 记录相关信息
 */
public class Recorder {
    //定义变量，我方击毁敌人tank数量
    private static int allEnemyTankNum = 0;

    //定义IO对象，准备写数据到文件
    private static FileWriter fw = null;
    private static BufferedReader br = null;
    private static BufferedWriter bw = null;
    private static String recorderFile = "d:/myRecord.txt";

    //定义一个Vector指向panel对象的敌人坦克Vector
    private static Vector<Enemytank> enemytanks = null;

    public static void setEnemytanks(Vector<Enemytank> enemytanks) {
        Recorder.enemytanks = enemytanks;
    }

    //定义一个Node的Vector用于保存敌人的信息node
    private static Vector<Node> nodes = new Vector<>();

    //增加一个方法，用于读取recordFile文件，恢复相关信息
    public static Vector<Node> getNodesAndallEnemyTankNum() {
        try {
            br = new BufferedReader(new FileReader(recorderFile));
            allEnemyTankNum = Integer.parseInt(br.readLine());
            //循环读取文件生成nodes集合
            String line = " ";
            while ((line = br.readLine()) != null) {
                String[] xyd = line.split(" ");
                Node node = new Node(Integer.parseInt(xyd[0]),
                        Integer.parseInt(xyd[1]),
                        Integer.parseInt(xyd[2]));
                nodes.add(node);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(br!=null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return nodes;
    }

    //增加一个方法，当游戏退出时，把allEnemyTankNum保存到recordFile文件中
    //升级keepRecord，保存敌人tank的坐标和方向
    public static void keepRecord() {
        try {
            bw = new BufferedWriter(new FileWriter(recorderFile));
            bw.write(allEnemyTankNum + "\r\n");
            //遍历敌人tank的Vector然后根据情况保存
            for (int i = 0; i < enemytanks.size(); i++) {
                //取出敌人tank
                Enemytank enemytank = enemytanks.get(i);
                if (enemytank.isLive) {
                    //保存enemytank的信息
                    String record = enemytank.getX()
                            + " " + enemytank.getY()
                            + " " + enemytank.getDirect();
                    //写入到文件
                    bw.write(record + "\r\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static int getAllEnemyTankNum() {
        return allEnemyTankNum;
    }

    public static void setAllEnemyTankNum(int allEnemyTankNum) {
        Recorder.allEnemyTankNum = allEnemyTankNum;
    }

    //当我方击毁一个敌人tank就allEnemyTankNum++
    public static void addAllEnemyTankNum() {
        Recorder.allEnemyTankNum++;
    }
}
