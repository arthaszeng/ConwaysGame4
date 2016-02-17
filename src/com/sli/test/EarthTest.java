import org.junit.Test;

import static org.junit.Assert.*;

public class EarthTest {
    @Test
    public void testEarthInit() throws Exception {
        Earth earth = new Earth();

        earth.init(4,8,3);

        assertEquals(4,earth.area.length, 4);
        assertEquals(4,earth.area[0].length, 8);
        assertFalse(earth.area[0][0].isLive());
        assertTrue(earth.area[1][1].isLive());
        assertTrue(earth.area[1][2].isLive());
        assertTrue(earth.area[1][3].isLive());
        assertFalse(earth.area[1][4].isLive());

        assertEquals(1, earth.area[1][1].getNumOfLiveNeighbor());
        assertEquals(2, earth.area[1][2].getNumOfLiveNeighbor());
        assertEquals(1, earth.area[1][3].getNumOfLiveNeighbor());
    }

    @Test
    public void testRunning() throws Exception {
        Earth earth = new Earth();
        earth.init(4, 8, 3);

        earth.run();
        assertFalse(earth.area[1][1].isLive());
        assertTrue(earth.area[1][2].isLive());
        assertFalse(earth.area[1][3].isLive());
        assertFalse(earth.area[1][4].isLive());
        assertFalse(earth.area[1][5].isLive());

        assertFalse(earth.area[2][1].isLive());
        assertTrue(earth.area[2][2].isLive());
        assertFalse(earth.area[2][3].isLive());
        assertFalse(earth.area[2][4].isLive());
        assertFalse(earth.area[2][5].isLive());
    }
}
