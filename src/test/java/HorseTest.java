import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class HorseTest {

    private Horse commonHorse;

    @BeforeEach
    void setUpBeforeClass() throws Exception {
        commonHorse = new Horse("Лошадка", 1, 2);
    }

    @Test
    void throwingExceptionInConstructorWhenFirstArgumentNull() {
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    Horse horse = new Horse(null, 1, 1);
                }
        );
    }

    @Test
    void checkingExceptionTextInConstructorWhenFirstArgumentNull() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    Horse horse = new Horse(null, 1, 1);
                }
        );
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @MethodSource("argsForFirstArgumentInConstructor")
    void throwingExceptionInConstructorWhenSecondArgumentNull(String argument) {
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    Horse horse = new Horse(argument, 1, 1);
                }
        );
    }

    static Stream<String> argsForFirstArgumentInConstructor() {
        return Stream.of("", " ", "    ");
    }

    @ParameterizedTest
    @MethodSource("argsForFirstArgumentInConstructor")
    void checkingExceptionTextInConstructorOnIllegalFirstArgument(String argument) {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    Horse horse = new Horse(argument, 1, 1);
                }
        );
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    void throwingExceptionInConstructorOnNegativeSecondArgument() {
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    Horse horse = new Horse("horse", -1, 1);
                }
        );
    }

    @Test
    void checkingExceptionTextInConstructorOnNegativeSecondArgument() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    Horse horse = new Horse("horse", -1, 1);
                }
        );
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    void throwingExceptionInConstructorOnNegativeThirdArgument() {
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    Horse horse = new Horse("horse", 1, -1);
                }
        );
    }

    @Test
    void checkingExceptionTextInConstructorOnNegativeThirdArgument() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    Horse horse = new Horse("horse", 1, -1);
                }
        );
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }


    @Test
    void getName() {
        assertEquals("Лошадка", commonHorse.getName());
    }

    @Test
    void getSpeed() {
        assertEquals(1, commonHorse.getSpeed());
    }

    @Test
    void getDistanceWithThreeParametrsInConstructor() {
        assertEquals(2, commonHorse.getDistance());
    }

    @Test
    void getDistanceWithTwoParametrsInConstructor() {
        Horse horse = new Horse("horse", 1);
        assertEquals(0, horse.getDistance());
    }

    @Test
    void checkingGetRandomExecutionInMove() {
        try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)) {
            commonHorse.move();
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @CsvSource({
            "0.2,0.9"
    })
    void checkingCalculationDistanseInMove(Double a, Double b) {
        try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)) {
            mockedStatic.when(() -> Horse.getRandomDouble(a, b)).thenReturn(3.0);
            commonHorse.move();
            assertEquals(5.0, commonHorse.getDistance());
        }
    }

}


