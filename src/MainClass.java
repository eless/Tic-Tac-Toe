
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
    public static void main (String[] args) throws IOException {

        newGame();




    }
    private static void newGame() throws IOException {

        field = new GameField();
        printField();

        for (int i = 0; i < 9; i++) {
            if (i % 2 != 0){
                runStep('O');
            }
            else runStep('X');
        }
        System.out.println("Ничья! Победила дружба :)");
        newOrExit();
        System.exit(0);
    }

    private static String winner(Character sign){
        if (sign == 'X')
            return "Первый игрок победил!";
        else
            return "Второй игрок победил!";

    }

    private static void newOrExit() throws IOException {
        String answer;
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Сыграть еще раз? y/n");
        answer = buffer.readLine().toLowerCase();
        if (answer == "y")
            newGame();
        else {
            System.out.println("Удачи! :)");
            System.exit(0);
        }
    }

    private static void runStep(Character sign) throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите координаты вашего хода через пробел (1-3 1-3):");
        int i = scanner.nextInt() - 1;
        int j = scanner.nextInt() - 1;
        //System.out.println(i + ", " + j);
        field.set(i, j, sign);
        printField();
        if (stepCheck(i, j, sign)){
            System.out.println(winner(sign));
            newOrExit();
        }
    }
    static void printField(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print("[" + field.get(i, j) + "]");
            }
            System.out.println();
        }

    }
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
