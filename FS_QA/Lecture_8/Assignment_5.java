//ищем количество целых чисел от a до b включительно, которые делятся на 12

public class Assignment_5 {
    public static void main(String[] args) {
        //задаем диапазон
        int a = 0;
        int b = 432;
        if (a > b){
            a = a + b;
            b = a - b;
            a = a - b;
        }
        int first_answer = a;
        int counter = 0;
        //ищем первое число, делящееся на 12
            while (first_answer % 12 != 0 && a <= b) {
                first_answer ++;
            }
            //если хотя бы одно подходящее число существует, ищем остальные
            if (first_answer % 12 == 0) {
                for (int i = first_answer; i <= b; i += 12) {
                    counter++;
                }
            }
        System.out.printf("Количество целых чисел в диапазоне от %d до %d включительно, делящихся на 12: %d", a, b, counter);
    }
}
