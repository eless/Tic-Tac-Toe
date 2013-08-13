import java.awt.*;


/**
 * Created with IntelliJ IDEA.
 * User: Eless
 * Date: 02.08.13
 * Time: 11:48
 * To change this template use File | Settings | File Templates.
 *
 * Тут будет ИИ, задающий ходы машины
 */

public class AI {
    //private GameField field;
    private boolean onAI;
    AI(boolean onAI){
        this.onAI = onAI;

    }

    public boolean isOnAI() {
        return onAI;
    }
    //логика хода компьютера по оптимальной стратегии (но без учета хода человека)
    public boolean computersStep(GameField field, char sign) {
        Point[] coord = new Point[]{new Point(1,1), new Point (0,0), new Point(2,2), new Point(0,2), new Point(2,0)};
        //костыль-визуализация хода для консоли
        try {
            Thread.sleep(600);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < coord.length; i++) {
            if (field.setInCell(coord[i].x, coord[i].y, sign)) {
                field.printField();
                if (MainClass.stepCheck(coord[i].x, coord[i].y, sign))
                    return true;
                else return false;
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                 if(field.setInCell(i, j, sign)) {
                     field.printField();
                     if (MainClass.stepCheck(i, j, sign))
                         return true;
                     else return false;
                 }
            }
        }
        return false;
    }



}
