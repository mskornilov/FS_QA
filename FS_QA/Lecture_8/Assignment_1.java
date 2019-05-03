//Песенка про бутылки

public class Assignment_1 {
    public static void main(String[] args) {
        for (int i=99; i >= 0; i--) {
            System.out.printf("%s bottle%s of beer on the wall,%n", i==0?"No":i, i==1?"":"s");
            System.out.printf("%s bottle%s of beer%n", i==0?"No":i, i==1?"":"s");
            System.out.println(i==0 ? "Go to the store and buy some more," : "Take one down, pass it around,");
            System.out.printf(i==1 ? "No bottles of beer on the wall%n" : "%s bottle%s of beer on the wall!%n", i==0 ? 99 : i-1, i==1?"":"s");
            System.out.println();
        }
    }

}
