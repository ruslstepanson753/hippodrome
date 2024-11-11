import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class HippodromeTest {

    @Test
    void throwingExceptionInConstructorWhenArgumentNull() {
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    Hippodrome hippodrome = new Hippodrome(null);
                }
        );
    }

    @Test
    void checkingExceptionTextInConstructorWhenArgumentNull() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    Hippodrome hippodrome = new Hippodrome(null);
                }
        );
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    void throwingExceptionInConstructorWhenArgumentEmpty() {
        List<Horse> horses = new ArrayList<>();
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    Hippodrome hippodrome = new Hippodrome(horses);
                }
        );
    }

    @Test
    void checkingExceptionTextInConstructorWhenArgumentEmpty() {
        List<Horse> horses = new ArrayList<>();
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    Hippodrome hippodrome = new Hippodrome(horses);
                }
        );
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    private List<Horse> getListWithThirtyHorses() {
        List listWithHorses = new ArrayList();
        for (int i = 0; i < 30; i++) {
            Horse horse = new Horse(String.valueOf(i), 1, i);
            listWithHorses.add(horse);
        }
        return listWithHorses;
    }

    @org.junit.jupiter.api.Test
    void getHorsesTest() {
        List<Horse> listWithHorses = getListWithThirtyHorses();
        Hippodrome hippodrome = new Hippodrome(listWithHorses);
        assertEquals(listWithHorses, hippodrome.getHorses());
    }

    @org.junit.jupiter.api.Test
    void chekMovingAllHorses() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(mock(Horse.class));
        }
        new Hippodrome(horses).move();
        for (Horse horse : horses) {
            verify(horse).move();
        }
    }

    @org.junit.jupiter.api.Test
    void chekWinnerCalculationTest() {
        List<Horse> listWithHorses = getListWithThirtyHorses();
        Hippodrome hippodrome = new Hippodrome(listWithHorses);
        assertSame(listWithHorses.get(29), hippodrome.getWinner());
    }
}