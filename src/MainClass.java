
import javafx.application.Platform;
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
    public static void main (String[] args) throws IOException {

        newGame();




    }
    //начинаем новую игру (иницализируем игровое поле, заполненое пробелами)
    private static void newGame() throws IOException {

        field = new GameField();
        printField();

        for (count = 0; count < 9; count++) {
            if (count % 2 != 0){
                System.out.println("Ходит второй игрок.");
                runStep('O');
            }
            else {
                System.out.println("Ходит первый игрок.");
                runStep('X');
            }

        }
        System.out.println("Ничья! Победила дружба :)");
        newOrExit();
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
    private static void newOrExit() throws IOException {
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

        System.out.println("Введите координаты вашего хода через пробел (1-3 1-3):");
        int i = scanner.nextInt() - 1;
        int j = scanner.nextInt() - 1;
        //System.out.println(i + ", " + j);
        if (field.get(i, j) == ' '){
            field.set(i, j, sign);
        }
        else {
            System.out.println("Данная ячейка уже занята!");
            count--;
        }
        printField();
        if (stepCheck(i, j, sign)){
            System.out.println(winner(sign));
            newOrExit();
        }
    }
    //а здесь выводим игровое поле
    static void printField(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print("[" + field.get(i, j) + "]");
            }
            System.out.println();
        }

    }
    //определение, не получилась ли линия из одинаковых знаков
    static boolean stepCheck(int n, int m, Character sign){
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
