package ru.cft;

import com.google.gson.JsonObject;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;


import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static ru.cft.CoffeeType.*;
import static utils.testUtils.*;

public class CafeTests {

    private Cafe cafe;

    @BeforeEach
    void cafeCreation(){
        cafe = new Cafe();
    }

    // JUnit

    @Test
    @Tag("junit")
    void testCafeNotNull(){
        assertNotNull(cafe, "Looks like your cafe is closed for renovation or there is no cafe at all");
    }

    @Test
    @Tag("junit")
    void testCafeInitialState(){
        int[] stock = {cafe.getBeansInStock(), cafe.getMilkInStock()};
        int[] expectedStock = {0, 0};
        assertArrayEquals(expectedStock, stock);
    }

    @Test
    @Tag("junit")
    void testRestockBeansPositive(){
        cafe.restockBeans(500);
        assertEquals(cafe.getBeansInStock(), 500);
    }

    @Test
    @Tag("junit")
    void testRestockMilkPositive(){
        cafe.restockMilk(1000);
        assertEquals(cafe.getMilkInStock(), 1000);
    }

    @Test
    @Tag("junit")
    void testRestockBeansNegative(){
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> cafe.restockBeans(0)),
                () -> assertThrows(IllegalArgumentException.class, () -> cafe.restockBeans(-500))
        );
    }

    @Test
    @Tag("junit")
    void testRestockMilkNegative(){
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> cafe.restockMilk(0)),
                () -> assertThrows(IllegalArgumentException.class, () -> cafe.restockMilk(-500))
        );
    }

    @ParameterizedTest
    @EnumSource(CoffeeType.class)
    @Tag("junit")
    void testStockSubtraction(CoffeeType type){
        cafe.restockBeans(100);
        cafe.restockMilk(1000);
        cafe.brew(type);
        assertAll(
                () -> assertEquals(cafe.getBeansInStock(), 100 - type.getRequiredBeans()),
                () -> assertEquals(cafe.getMilkInStock(), 1000 - type.getRequiredMilk())
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5})
    @Tag("junit")
    void testStrength(int strength){
        cafe.restockBeans(100);
        cafe.brew(Espresso, strength);
        assertEquals(cafe.getBeansInStock(), 100 - Espresso.getRequiredBeans() * strength);
    }

    @RepeatedTest(2)
    @Tag("junit")
    void testBrewingMultipleEspresso(){
        cafe.restockBeans(7);
        cafe.brew(Espresso);
        assertEquals(cafe.getBeansInStock(), 0);
    }

    @Test
    @Tag("junit")
    void testBrewWithInsufficientBeans(){
        cafe.restockBeans(5);
        assertThrows(IllegalStateException.class, () -> cafe.brew(Espresso));
    }

    @Test
    @Tag("junit")
    void testBrewWithInsufficientMilk() {
        cafe.restockBeans(10);
        cafe.restockMilk(100);
        assertThrows(IllegalStateException.class, () -> cafe.brew(Latte));
    }

    @Test
    @Tag("junit")
    void testSameType(){
        cafe.restockBeans(100);
        cafe.restockMilk(100);
        Coffee firstCup = cafe.brew(Espresso);
        Coffee secondCup = cafe.brew(Espresso, 4);
        assertSame(firstCup.getType(), secondCup.getType());
    }

    // AssertJ

    @Test
    @Tag("assertJ")
    void testEspressoBrewing(){
        cafe.restockBeans(7);
        Coffee coffee = cafe.brew(Espresso);
        assertThat(coffee).isEqualToComparingFieldByField(new Coffee(Espresso, 7, 0));
    }

    @ParameterizedTest
    @EnumSource(CoffeeType.class)
    @Tag("assertJ")
    void testCoffeeToString(CoffeeType type){
        cafe.restockBeans(100);
        cafe.restockMilk(1000);
        Coffee coffee = cafe.brew(type);
        JsonObject jsonObject = resourceToJSONObject("CoffeeToString.json");
        assertThat(coffee.toString()).isEqualTo(jsonObject.get(type.toString()).getAsString());
    }

    @ParameterizedTest
    @EnumSource(CoffeeType.class)
    @Tag("assertJ")
    void testCoffeeBrewing(CoffeeType type){
        cafe.restockBeans(100);
        cafe.restockMilk(1000);
        Coffee coffee = cafe.brew(type);
        assertThat(coffee).isEqualToComparingFieldByField(new Coffee(type, type.getRequiredBeans(), type.getRequiredMilk()));
    }

    @ParameterizedTest
    @EnumSource(CoffeeType.class)
    @Tag("assertJ")
    void testCoffeeBrewingWithStrength(CoffeeType type){
        cafe.restockBeans(100);
        cafe.restockMilk(1000);
        Coffee coffee = cafe.brew(type,3);
        assertThat(coffee).isEqualToComparingFieldByField(
                new Coffee(type,type.getRequiredBeans() * 3,type.getRequiredMilk()* 3));
    }

    @Test
    @Tag("assertJ")
    void testFilteredCoffeeIsExactlyInstanceOfCoffee(){
        cafe.restockBeans(10);
        Coffee coffee = cafe.brew(FilterCoffee);
        assertThat(coffee).as(coffee.getType().toString()).isExactlyInstanceOf(Coffee.class);
    }

    @ParameterizedTest
    @EnumSource(CoffeeType.class)
    @Tag("assertJ")
    void testTypesInMenu(CoffeeType type){
        String menu = getResourceAsString("Menu.txt");
        assertThat(menu).contains(type.toString());
    }

    @Test
    @Tag("assertJ")
    void testCoffeeTypes(){
        assertThat(CoffeeType.values()).containsExactlyInAnyOrder(Latte, FilterCoffee, Espresso);
    }

}
