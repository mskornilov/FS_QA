package StringsAndFiles;

/*
1. Работа со строками (отдельный класс):
        1.1 Разворот строки 2мя способами:
        а) Работая со строкой как с последовательностью char символов
        б) Работая со строкой при помощи объекта StringBuilder
        1.2 Форматирование строки:
        а) Удаление лишних пробелов в начале и в конце строки
        * удаление, работая со строкой как с последовательностью char
        б) Приведение всех символов строки к верхнему регистру
        1.3 Извлечение подстроки с уакзанием начального индекса и конечного
*/

class MyString {

    private static MyString instance;
    private String string;

    public static MyString getInstance(){
        if (instance == null) {
            instance = new MyString(UserInteractions.askForString());
        }
        return instance;
    }

    private MyString(String string){
        this.string = string;
    }

    String getString(){
        return this.string;
    }
    void setString(String path){
        this.string = path;
    }




    /**
     * Переворачивает строку
     * Работает со строкой как с последовательностью символов
     * @return String
     */
    String reverseAsChars(){
        char[] chars = string.toCharArray();
        int maxIndex = chars.length - 1;
        for (int i = 0; i < chars.length/2; i++){
            chars[i] = (char) (chars[i] + chars[maxIndex - i]);
            chars[maxIndex - i] = (char) (chars[i] - chars[maxIndex - i]);
            chars[i] = (char) (chars[i] - chars[maxIndex - i]);
        }
        setString(new String(chars));
        return string;
    }

    /**
     * Переворачивает строку
     * @return String
     */
    String reverseAsStringBuilder(){
        StringBuilder builder = new StringBuilder(string);
        builder.reverse();
        setString(builder.toString());
        return string;
    }

    /**
     * Удаляет пробелы из начала и из конца строки
     * @return String
     */
    String removeSpaces(){
        setString(string.trim());
        return string;
    }

    /**
     * Удаляет пробелы из начала и из конца строки,
     * Работает со строкой как с последовательностью символов
     * @return String
     */
    String removeSpacesAsChars(){
        char[] chars = string.toCharArray();
        char[] charsNoSpaces;
        int counter = 0;
        int start = 0;
        for (int i = 0; i < chars.length; i++){
            if (Character.isWhitespace(chars[i])){
                start++;
                counter++;
            } else {
                break;
            }
        }
        // если в строке не только пробелы
        if (counter != chars.length) {
            for (int i = chars.length - 1; i >= 0; i--) {
                if (Character.isWhitespace(chars[i])) {
                    counter++;
                } else {
                    break;
                }
            }
        }
        charsNoSpaces = new char[chars.length - counter];
        System.arraycopy(chars, start, charsNoSpaces, 0, chars.length - counter);
        setString(new String(charsNoSpaces));
        return string;
    }

    /**
     * Переводит символы в строке в верхний регистр
     * @return String
     */
    String upperCase(){
        setString(string.toUpperCase());
        return string;
    }

    /**
     * Принимает два индекса
     * Извлекает подстроку от start до end
     * @return String
     */
    String subString(int start, int end){
        setString(string.substring(start, end));
        return string;
    }

}
