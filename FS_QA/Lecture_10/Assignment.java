package Lecture_10;


public class Assignment {
    public static void main(String[] args) {
        CharSequence chars = new MyCharSequence("YesYesYes");
        System.out.println("chars = " + chars);
        System.out.println("chars.charAt(7) = " + chars.charAt(7));
        System.out.println("chars.length() = " + chars.length());
        System.out.println("chars.subSequence(0,5) = " + chars.subSequence(0,5));
        System.out.println();
        CharSequence chars2 = new MyCharSequence("0123456789");
        System.out.println("chars2 = " + chars2);
        System.out.println("chars2.charAt(3) = " + chars2.charAt(3));
        System.out.println("chars2.length() = " + chars2.length());
        System.out.println("chars2.subSequence(1,3) = " + chars2.subSequence(1,3));
        System.out.println();
        CharSequence chars3 = new MyCharSequence("The quick brown fox jumps over the lazy dog");
        System.out.println("chars3 = " + chars3);
        System.out.println("chars3.charAt(18) = " + chars3.charAt(18));
        System.out.println("chars3.length() = " + chars3.length());
        System.out.println("chars3.subSequence(9,34) = " + chars3.subSequence(9,34));
    }
}