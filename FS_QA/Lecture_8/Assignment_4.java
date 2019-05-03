//ищем k-ное число в последовательности из 99-ти двузначных чисел

import java.util.Random;

public class Assignment_4 {
    public static void main(String[] args) {
        int k = 2;
        Random ran = new Random();
        StringBuilder sequence_builder = new StringBuilder();
        for (int i=0; i < 99; i++) {
            sequence_builder.append(ran.nextInt(90) + 10);
        }
        String sequence = sequence_builder.toString();
        System.out.println(sequence);
        System.out.println(sequence.charAt(k));
    }
}
