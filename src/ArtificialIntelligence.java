
import static sun.org.mozilla.javascript.internal.Context.exit;

/**
 * Created with IntelliJ IDEA.
 * User: Eless
 * Date: 02.08.13
 * Time: 11:48
 * To change this template use File | Settings | File Templates.
 * Тут будет ИИ, задающий ходы машины
 */

public class ArtificialIntelligence {
    private void computersStep(GameField field, char sign){
        if (writeInCell(field, 1, 1, sign)) exit();
        if (writeInCell(field, 0, 0, sign)) exit();
        if (writeInCell(field, 2, 2, sign)) exit();
        if (writeInCell(field, 0, 2, sign)) exit();
        if (writeInCell(field, 2, 0, sign)) exit();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                 if(writeInCell(field, i, j, sign)) exit();
            }
        }
    }
    private boolean writeInCell(GameField field, int i, int j, Character sign){
        if (field.get(i, j) == ' '){
            field.set(i, j, sign);
            return true;
        }
        return false;

    }

}
