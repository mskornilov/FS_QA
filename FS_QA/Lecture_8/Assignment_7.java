import java.util.Scanner;

public class Assignment_7 {
    public static void main(String[] args) {
        String answer = "";
        while (answer.equals("")) {
            System.out.println("Введите номер месяца от 1 до 12:");
            Scanner in = new Scanner(System.in);
            if (in.hasNextInt()) {
                int month = in.nextInt();
                switch (month) {
                    case 12:
                    case 1:
                    case 2:
                        answer = "Зима";
                        break;
                    case 3:
                    case 4:
                    case 5:
                        answer = "Весна";
                        break;
                    case 6:
                    case 7:
                    case 8:
                        answer = "Лето";
                        break;
                    case 9:
                    case 10:
                    case 11:
                        answer = "Осень";
                        break;
                }
            }
        System.out.println(answer);
        }
    }
}
