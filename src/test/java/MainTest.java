import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    static Main start = new Main();


    @Disabled
    @Test
    @Timeout(22)
    void mainTest() throws Exception {
        String[] args = new String[0];
        Main.main(args);
    }
}