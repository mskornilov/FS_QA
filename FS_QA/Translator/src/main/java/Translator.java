import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class Translator {
    public static void main(String[] args) throws InterruptedException{

            Context context = new Context();
            context.setStrategy(new RuEng());
            String text = "";
            while (!text.equals("?q")){
                System.out.println("Текущее направление перевода: " + context.getStrategy().toString());
                System.out.println("Для завершения работы программы наберите: \"?q\"");
                text = askForText();
                checkAndExecute(text, context);
                Thread.sleep(1500);
                System.out.println("************************");
            }
    }

    private static String askForText(){
        String text;
        Scanner in = new Scanner(System.in);
        System.out.println("Введите текст для перевода: ");
        text = in.nextLine().trim();
        // Смена кодировки введенного текста
        try {
            text = new String(text.getBytes("windows-1251"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return text;
    }

    private static void checkAndExecute(String text, Context context){
        if (text.equals("?EngRu")) {
            context.setStrategy(new EngRu());
        } else if (text.equals("?RuEng")){
            context.setStrategy(new RuEng());
        } else if (text.matches("^[а-яА-Я].*") && (context.getStrategy() instanceof EngRu)){
            System.out.println("Выбрано неверное направление перевода. Введите \"?RuEng\", чтобы переключить направление.");
        } else if (text.matches("^[a-zA-Z].*") && (context.getStrategy() instanceof RuEng)) {
            System.out.println("Выбрано неверное направление перевода. Введите \"?EngRu\", чтобы переключить направление.");
        } else if (!text.equals("?q")){
            System.out.println(context.executeStrategy(text));
        }
    }
}
