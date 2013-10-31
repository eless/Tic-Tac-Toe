
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: Eless
 * Date: 01.08.13
 * Time: 10:17
 * To change this template use File | Settings | File Templates.
 */

public class MainClass {
    private static GameField field;
    private static int count;
    static AI al;
    public static void main (String[] args) throws IOException {

        al = new AI(gameType());
        newGame();




    }
    //устанавливаем тип игры
    private static boolean gameType() throws IOException {
        Character answer;
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Играть против AI(введите \"1\") или PvP(2)?");
        answer = buffer.readLine().charAt(0);
        if (answer == '1')
           return true;

        return false;
    }

    //на будущее, может понадобится
    public static GameField getField(){
        return field;
    }
    //начинаем новую игру (иницализируем игровое поле, заполненое пробелами)
    private static void newGame() throws IOException {

        field = new GameField();
        field.printField();


        for (count = 0; count < 9; count++) {
            if (count % 2 != 0){
                System.out.println("Ходит второй игрок.");
        //пока что AI будет только вторым
                if (al.isOnAI()){
                    if (al.computersStep(field, 'O')){
                        field.printField();
                        System.out.println(winner('O'));
                        exitGame();
                    }
                }
                else
                runStep('O');
            }
            else {
                System.out.println("Ходит первый игрок.");

                    runStep('X');
            }

        }
        System.out.println("Ничья! Победила дружба :)");
        exitGame();
        System.exit(0);
    }
    // Определение победителя
    private static String winner(Character sign){
        if (sign == 'X')
            return "Первый игрок победил!";
        else
            return "Второй игрок победил!";

    }
    // Начать новую игру или выйти?
    private static void exitGame() throws IOException {
        Character answer;
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Сыграть еще раз? y/n");
        answer = buffer.readLine().charAt(0);
        if ((answer == 'y') || (answer == 'Y')){
            newGame();
        }
        else {
            System.out.println("Удачи! :)");
            System.exit(0);
        }
    }
    //Ввод координат, проверка победы (для простоты - после каждого шага)
    private static void runStep(Character sign) throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите координаты вашего хода через пробел (1-3 строка 1-3 столбик):");
        int i = scanner.nextInt() - 1;
        int j = scanner.nextInt() - 1;
        //System.out.println(i + ", " + j);

        if (!field.setInCell(i, j, sign)){
            System.out.println("Данная ячейка уже занята!");
            count--;
        }
        field.printField();
        if (stepCheck(i, j, sign)){
            System.out.println(winner(sign));
            exitGame();
        }
    }

    //определение, не получилась ли линия из одинаковых знаков
    public static boolean stepCheck(int n, int m, Character sign){
        boolean chacked1 = true;
        boolean chacked2 = true;
        boolean chacked3 = true;
        boolean chacked4 = true;
        for (int i = 0; i < 3; i++) {
            if (field.get(i, m) != sign) chacked1 = false;
            if (field.get(n, i) != sign) chacked2 = false;
            if (field.get(i, i) != sign) chacked3 = false;
            if (field.get(i, 2-i) != sign) chacked4 = false;
        }
        if (chacked1 || chacked2 || chacked3 || chacked4) return true;
        return false;

    }
}
