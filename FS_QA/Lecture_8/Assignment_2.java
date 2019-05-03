//Угадываем число от 0 до 9, в одиночку или вдвоем

import java.util.*;


public class Assignment_2 {
    public static void main(String[] args) {
        int number = (int) (Math.random() * 10);
        String answer = String.valueOf(number);
        System.out.println(answer);
        String[] answers = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        int winner = 0;
        while (winner == 0){
            System.out.print("Выберите количество игроков (1,2): ");
            Scanner players_in = new Scanner(System.in);
            if (players_in.hasNextInt()) {
                int players = players_in.nextInt();
                if (players == 1){
                    System.out.println("Компьютер загадал число от 0 до 9. Попробуйте угадать.");
                    while (true) {
                        Scanner in = new Scanner(System.in);
                        System.out.print("Введите число от 0 до 9: ");
                        String guess = in.nextLine();
                        if (Arrays.asList(answers).contains(guess)) {
                            System.out.println("Вы ввели число " + guess);
                            if (guess.equals(answer)) {
                                System.out.println("Вы угадали! Поздравляем!");
                                winner = 1;
                                break;
                            } else {
                                System.out.println("Неверно. Попробуйте еще раз.");
                            }
                        } else {
                            System.out.println("Можно вводить только числа от 0 до 9");
                        }
                    }
                } else if (players == 2){
                    System.out.println("Компьютер загадал число от 0 до 9. Попробуйте угадать.");
                    String player_one_guess;
                    String player_two_guess;
                    Scanner in = new Scanner(System.in);
                    while (true) {
                        System.out.print("Первый игрок, введите число от 0 до 9: ");
                        player_one_guess = in.nextLine();
                        if (Arrays.asList(answers).contains(player_one_guess)) {
                            System.out.println("Первый игрок ввел число " + player_one_guess);
                        } else {
                            System.out.println("Можно вводить только целые числа от 0 до 9.");
                        }
                            System.out.print("Второй игрок, ввведите число от 0 до 9: ");
                            player_two_guess = in.nextLine();
                            if (Arrays.asList(answers).contains(player_two_guess)) {
                                System.out.println("Второй игрок ввел число " + player_two_guess);
                            } else {
                                System.out.println("Можно вводить только целые числа от 0 до 9.");
                            }
                                if (player_one_guess.equals(answer)) {
                                    System.out.println("*******************************************");
                                    System.out.println("Первый игрок победил!");
                                    System.out.println();
                                    winner = 1;
                                    break;
                                } else if (player_two_guess.equals(answer)) {
                                    System.out.println("*******************************************");
                                    System.out.println("Второй игрок победил!");
                                    System.out.println();
                                    winner = 1;
                                    break;
                                } else {
                                    System.out.println("*******************************************");
                                    System.out.println("Никто не угадал, давайте попробуем еще раз.");
                                    System.out.println();
                                }
                    }
                }
            } else {
                System.out.println("beep boop Я вас не понял, попробуем еще раз.");
            }

        }
    }
}
