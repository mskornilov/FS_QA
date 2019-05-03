//Cоздаем матрицу X на X, заполняем адресами ячеек и переворачиваем матрицу

import java.util.Arrays;
import java.util.Scanner;

public class Assignment_3 {
    public static void main(String[] args) {
        String[][] balances;
        int matrix_size = 0;
        while (matrix_size < 2){
            Scanner in = new Scanner(System.in);
            System.out.println("Введите размер квадратной матрицы (число, от 2 до разумных пределов):");
            if (in.hasNextInt()) {
                matrix_size = in.nextInt();
            }
        }
        balances = new String[matrix_size][matrix_size];
        for (int i=0; i < matrix_size; i++) {
            for (int j=0; j < matrix_size; j++) {
            balances[i][j] = String.valueOf(i) + String.valueOf(j);
            }
        }
        System.out.println("Получилась матрица:");
        System.out.println(Arrays.deepToString(balances));
        int max_index = balances.length - 1;
        int half_length;
        // проверяем размер матрицы на четность и прибавляем к половине размера матрицы единицу,
        // чтобы захватить центральный массив
        if (matrix_size % 2 == 0) {
            half_length = balances.length / 2;
        } else {
            half_length = balances.length / 2 + 1;
        }
        int sum;
        for (int i=0; i < half_length; i++) {
            for (int j=0; j < matrix_size; j++) {
                sum = Integer.valueOf(balances[i][j]) + Integer.valueOf(balances[max_index - i][max_index - j]);
                if (i == 0) {
                    balances[max_index - i][max_index - j] = "0" + String.valueOf(sum - Integer.valueOf(balances[max_index - i][max_index - j]));        // добавил проверку на случай нечетной длины массива
                    // если размер матрицы нечетный - обмен значений надо остановить на середине центрального массива
                } else if (matrix_size % 2 != 0 && i == half_length - 1 && j == half_length - 1) {
                    break;
                } else {
                    balances[max_index - i][max_index - j] = String.valueOf(sum - Integer.valueOf(balances[max_index - i][max_index - j]));
                }
                balances[i][j] = String.valueOf(sum - Integer.parseInt(balances[max_index - i][max_index - j]));
            }
        }
        System.out.println("Перевернутая матрица:");
        System.out.println(Arrays.deepToString(balances));
    }
}
