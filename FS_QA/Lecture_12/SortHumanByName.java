package Lecture_12;

import java.util.Comparator;

// дополнительный класс-компаратор перегружающий метод compare
// для сортировки списка людей по имени
class SortHumanByName implements Comparator<Human> {

    @Override
    public int compare(Human h1, Human h2) {
        return h1.name.compareTo(h2.name);
    }
}