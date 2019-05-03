//ищем такое n, которое при 1+2+3+...+т дает число k

public class Assignment_6 {
    public static void main(String[] args) {
        //из условия следует, что k > 1
        int k = 7;
        int sum = 0;
        int answer = 0;
        while (k != sum && answer < k) {
            for (int i = 1; i <= answer; i++){
                sum += i;
            }
            if (sum == k) {
                System.out.println("Ответ: " + answer);
            } else {
                sum = 0;
                answer++;
            }
        }
        if (answer >= k) {
            System.out.printf("Нет ответа при k == %d", k);
        }
    }
}
