import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CellTest {
    private Cell[] neighbor;

    @Before
    public void setUp() throws Exception {
        neighbor = new Cell[8];
        for (int i = 0; i < 8; i++) {
            neighbor[i] = new Cell();
        }
    }

    @Test
    public void testAttributeOfCell() throws Exception {
        Cell cell = new Cell();

        assertFalse(cell.isLive());

        cell.revive();
        assertTrue(cell.isLive());

        cell.die();
        assertFalse(cell.isLive());
    }

    @Test
    public void testNeighbor() throws Exception {
        Cell cell = new Cell();
        cell.init(neighbor);

        assertEquals(0, cell.getNumOfLiveNeighbor());

        neighbor[0].revive();
        cell.init(neighbor);
        assertEquals(1, cell.getNumOfLiveNeighbor());


        cell.myNotify(true);
        assertEquals(1, neighbor[0].getNumOfLiveNeighborBuffer());
        assertEquals(1, neighbor[1].getNumOfLiveNeighborBuffer());
        assertEquals(1, neighbor[2].getNumOfLiveNeighborBuffer());
        assertEquals(1, neighbor[3].getNumOfLiveNeighborBuffer());
        assertEquals(1, neighbor[4].getNumOfLiveNeighborBuffer());
        assertEquals(1, neighbor[5].getNumOfLiveNeighborBuffer());
        assertEquals(1, neighbor[6].getNumOfLiveNeighborBuffer());
        assertEquals(1, neighbor[7].getNumOfLiveNeighborBuffer());

        cell.myNotify(false);
        assertEquals(0, neighbor[0].getNumOfLiveNeighborBuffer());
        assertEquals(0, neighbor[1].getNumOfLiveNeighborBuffer());
        assertEquals(0, neighbor[2].getNumOfLiveNeighborBuffer());
        assertEquals(0, neighbor[3].getNumOfLiveNeighborBuffer());
        assertEquals(0, neighbor[4].getNumOfLiveNeighborBuffer());
        assertEquals(0, neighbor[5].getNumOfLiveNeighborBuffer());
        assertEquals(0, neighbor[6].getNumOfLiveNeighborBuffer());
        assertEquals(0, neighbor[7].getNumOfLiveNeighborBuffer());
    }

    @Test
    public void testRenewalDoNotRevive() throws Exception {
        Cell cell = new Cell();
        neighbor[0].revive();
        neighbor[1].revive();
        cell.init(neighbor);

        cell.renewal();

        assertEquals(2, cell.getNumOfLiveNeighbor());
        assertFalse(cell.isLive());
        assertEquals(0, neighbor[0].getNumOfLiveNeighbor());
    }

    @Test
    public void testRenewalDoRevive() throws Exception {
        Cell cell = new Cell();
        neighbor[0].revive();
        neighbor[1].revive();
        neighbor[2].revive();
        cell.init(neighbor);

        cell.renewal();

        assertEquals(3, cell.getNumOfLiveNeighbor());
        assertTrue(cell.isLive());
        assertEquals(1, neighbor[0].getNumOfLiveNeighborBuffer());
    }

    @Test
    public void testRenewalWillDieByOvercrowding() throws Exception {
        Cell cell = new Cell();
        neighbor[0].revive();
        neighbor[1].revive();
        neighbor[2].revive();
        neighbor[3].revive();

        cell.init(neighbor);

        assertEquals(4, cell.getNumOfLiveNeighbor());
        assertFalse(cell.isLive());
        assertEquals(0, neighbor[0].getNumOfLiveNeighbor());
    }

    @Test
    public void testRenewalWillDieByUnderpopulation() throws Exception {
        Cell cell = new Cell();
        neighbor[0].revive();

        cell.init(neighbor);

        assertEquals(1, cell.getNumOfLiveNeighbor());
        assertFalse(cell.isLive());
        assertEquals(0, neighbor[0].getNumOfLiveNeighbor());
    }
}
