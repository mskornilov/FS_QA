package Lecture_10;

import java.util.Arrays;

class MyCharSequence implements CharSequence {
    private char[] chars;
    MyCharSequence(String arg) {
        chars = new char[arg.length()];
        for (int i = 0; i < arg.length(); i++){
            chars[i] = arg.charAt(i);
        }
    }
    public int length(){
        return chars.length;
    }
    public char charAt(int index){
        if (index < 0 || index > chars.length){
            throw new IndexOutOfBoundsException("Index cannot be negative or greater than the length of the sequence.");
        } else {
            return chars[index];
        }
    }
    public MyCharSequence subSequence(int start, int end){
        if (start < 0 || end < 0){
            throw new IndexOutOfBoundsException("Index cannot be negative");
        } else if (end >= chars.length){
            throw new IndexOutOfBoundsException("index cannot be greater than the length of the sequence.");
        } else if (start > end) {
            throw new IndexOutOfBoundsException("First index cannot be greater than second.");
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = start; i < end; i++){
                sb.append(chars[i]);
            }
            return new MyCharSequence(sb.toString());
        }
    }
    public String toString(){
        return Arrays.toString(chars);
    }
}
