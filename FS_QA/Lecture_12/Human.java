package Lecture_12;

class Human implements Comparable<Human>{
    String name;
    int age;
    Human(String name, int age){
        this.name = name;
        this.age = age;
    }


    @Override
    public int compareTo(Human h1){
        return this.age - h1.age;
    }

    @Override
    public String toString(){
        return "Human: Name: " + this.name + ", age: " + this.age;
    }
}
