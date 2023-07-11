import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class HippodromeTest {

    @Test
    void testConstructorWithNullArgument() {
        Throwable exep1 = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        Throwable exep2 = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
        assertEquals("Horses cannot be null.", exep1.getMessage());
        assertEquals("Horses cannot be empty.", exep2.getMessage());
    }

    @Test
    void getHorses() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("Horse" + i, Math.random() * 3));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    @ExtendWith(MockitoExtension.class)
    void move() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(Mockito.mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        for (int i = 0; i < 50; i++) {
            hippodrome.getHorses().get(i).move();
            Mockito.verify(horses.get(i)).move();
        }
    }

    @Test
    void getWinner() {
        Horse horse1 = new Horse("Лашадь", 2.2, 1.0);
        Horse horse2 = new Horse("Лашадь", 2.2, 1.0);
        Horse horse3 = new Horse("Лашадь", 2.2, 5.0);
        Horse horse4 = new Horse("Лашадь", 2.2, 1.0);

        Hippodrome h = new Hippodrome(List.of(horse1, horse2, horse3, horse4));
        assertEquals(horse3, h.getWinner());
    }
}