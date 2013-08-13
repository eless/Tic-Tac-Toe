
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
    private boolean onAI = false;
    AI(){
        this.onAI = true;

    }
    private void computersStep(GameField field, char sign){
        
        if (field.setInCell(1, 1, sign)) return;
        if (field.setInCell(0, 0, sign)) return;
        if (field.setInCell(2, 2, sign)) return;
        if (field.setInCell(0, 2, sign)) return;
        if (field.setInCell(2, 0, sign)) return;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                 if(field.setInCell(i, j, sign)) return;
            }
        }
    }


}
