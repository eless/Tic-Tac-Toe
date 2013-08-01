/**
 * Created with IntelliJ IDEA.
 * User: Eless
 * Date: 01.08.13
 * Time: 9:49
 * To change this template use File | Settings | File Templates.
 */
public class GameField {
    private char[][] field = new char[3][3];
    final char TIC = 'X';
    final char TAC = 'O';

    GameField(){
        this.field = new char[][]{{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};

    }
    public void set(int i, int j, char sign){
        if (sign == TIC) {
            this.field[i][j] = TIC;
        }
        else {
            this.field[i][j] = TAC;
        }
    }

    public char get(int i,int j){
        return this.field[i][j];
    }

}
