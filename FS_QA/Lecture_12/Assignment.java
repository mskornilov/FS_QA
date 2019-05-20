package Lecture_12;

import java.util.*;

/**
 Задание 1
 Создайте List<Integer> из 100 элементов, заполните случайными
 значениями
 отсортируйте этот лист по возрастанию.
 отсортируйте этот лист по убыванию
 Сортировать необходимо встроенными алгоритмами, писать свою
 сортировку не нужно :)
 до и после сортировки выводите значение листа на консоль
 По желанию сделайте сортировку несколькими способами

 Задание 2
 Создайте класс Human, который содержит поля возраст (age) и имя
 (name)
 Создайте List<Human> из 5 элементов
 отсортируйте этот лист по возрастанию.
 отсортируйте этот лист по убыванию
 до и после сортировки выводите значение листа на консоль

 Задание 3
 Создайте класс Human, который содержит поля возраст (age) и имя
 (name)
 создайте Set на основе TreeSet
 Поместите несколько объектов класса Human в созданный Set
 выведите на консоль содержимое объекта set


 */
public class Assignment {
    public static void main(String[] args) {
        List<Integer> li = new ArrayList<Integer>();
        // заполняем List случайными значениями
        Random ran = new Random();
        for (int i = 0; i < 100; i++){
            li.add(ran.nextInt(1000));
        }
        // List до сортировки
        System.out.println("List до сортировки:");
        System.out.println(li.toString());
        // сортировка по возрастанию
        Collections.sort(li);
        System.out.println();
        System.out.println("List после сортировки по возрастанию:");
        System.out.println(li.toString());
        // сортировка по убыванию
        li.sort(Collections.reverseOrder());
        System.out.println();
        System.out.println("List после сортировки по убыванию:");
        System.out.println(li.toString());

        // создаем List<Human> и заполняем пятью экземплярами класса
        List<Human> lh = new ArrayList<Human>();
        lh.add(new Human("Masha", 23));
        lh.add(new Human("Vasya", 45));
        lh.add(new Human("Korneliy", 31));
        lh.add(new Human("Gosha", 15));
        lh.add(new Human("Sergey Yurevich", 72));

        System.out.println();
        System.out.println("Список людей до сортировки:");
        System.out.println(lh.toString());
        // сортировка по возрастанию по полю age
        Collections.sort(lh);
        System.out.println();
        System.out.println("Список людей после сортировки по возрасту (по возрастанию):");
        System.out.println(lh.toString());
        // сортировка по убыванию по полю age
        lh.sort(Collections.reverseOrder());
        System.out.println();
        System.out.println("Список людей после сортировки по возрасту (по убыванию):");
        System.out.println(lh.toString());
        // соритиворка по полю name
        lh.sort(new SortHumanByName());
        System.out.println();
        System.out.println("Список людей после сортировки по алфавиту:");
        System.out.println(lh.toString());

        // создаем и заполняем Set<Human>
        Set<Human> sh = new TreeSet<Human>();
        sh.add(new Human("Masha", 23));
        sh.add(new Human("Vasya", 45));
        sh.add(new Human("Korneliy", 31));
        sh.add(new Human("Gosha", 15));
        sh.add(new Human("Sergey Yurevich", 72));

        System.out.println();
        System.out.println("Set людей. Список создается отсортированным по полю age, благодаря хранению элементов в \"красно-черном\" дереве. :)");
        System.out.println(sh);
    }
}