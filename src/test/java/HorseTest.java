import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.*;

class HorseTest {

    @Test
    public void testConstructorWithNullFirstArgument() {
        //Класс Horse Constructor - 1 пункт
        Throwable exeption = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1.2, 200));
        //Класс Horse Constructor - 2 пункт
        assertEquals("Name cannot be null.", exeption.getMessage());
    }


    @ParameterizedTest
    @CsvSource(value = {
            " , 1.2, 200.0",
            "       , 1.2, 200.0"
    }, ignoreLeadingAndTrailingWhitespace = false)
    void testConstructorWithEmptyFirstArgument(String name, double speed, double distance) {
        //Класс Horse Constructor - 3 пункт
        Throwable exeption = assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed, distance));
        //Класс Horse Constructor - 4 пункт
        assertEquals("Name cannot be blank.", exeption.getMessage());
    }

    @Test
    public void testConstructorWithNegativeSecondArgument() {
        //Класс Horse Constructor - 5 пункт
        Throwable exeption = assertThrows(IllegalArgumentException.class, () -> new Horse("Пиковый валет", -1.2, 200));
        //Класс Horse Constructor - 6 пункт
        assertEquals("Speed cannot be negative.", exeption.getMessage());
    }

    @Test
    public void testConstructorWithNegativeThirdArgument() {
        //Класс Horse Constructor - 7 пункт
        Throwable exeption = assertThrows(IllegalArgumentException.class, () -> new Horse("Пиковый валет", 1.2, -200));
        //Класс Horse Constructor - 8 пункт
        assertEquals("Distance cannot be negative.", exeption.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"Лощадь", "йцу"})
    public void getName(String name) {
        Horse horse = new Horse(name, 2.2, 200.0);
        assertEquals(name, horse.getName());
    }

    @ParameterizedTest
    @ValueSource(doubles = {2., 3.5})
    public void getSpeed(double speed) {
        Horse horse = new Horse("Лошадь", speed, 200.0);
        assertEquals(speed, horse.getSpeed());
    }

    @ParameterizedTest
    @ValueSource(doubles = {200., 300.5})
    public void getDistance(double distance) {
        Horse horse = new Horse("Лошадь", 2.2, distance);
        assertEquals(distance, horse.getDistance());
    }

    @Test
    public void checkHorseConstructorForTwoParams() {
        Horse horse = new Horse("Лошадь", 2.2);
        assertEquals(0, horse.getDistance());
    }

    @Test
    @ExtendWith(MockitoExtension.class)
    public void move() {
        try (MockedStatic<Horse> mockedStaticHorse = Mockito.mockStatic(Horse.class)) {
            Horse horse = new Horse("qwe", 10.0);
            mockedStaticHorse.when( () -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(0.5);
            horse.move();
            mockedStaticHorse.verify(() -> Horse.getRandomDouble(0.2, 0.9));
            assertEquals(5, horse.getDistance());

        }
    }
}