package ch.ffhs.dua.greedy;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestTSP
{
    @Test
    public void testInput()
    {
        assertThrows(IllegalArgumentException.class, ()->
                TSP_Greedy.checkInput(new int[][] {{1, 1}, {3}}, 0 ));
        assertThrows(IllegalArgumentException.class, ()->
                TSP_Greedy.checkInput(new int[][] {{1, 2}, {3, 4}}, 2 ));
        assertThrows(IllegalArgumentException.class, ()->
                TSP_Greedy.checkInput(new int[][] {{1, 2}, {3, 4}}, -1 ));
        assertThrows(IllegalArgumentException.class, ()->
                TSP_Greedy.checkInput(new int[][] {{1, 2}, {3, 4}, {5, 6}}, 0 ));
        assertThrows(IllegalArgumentException.class, ()->
                TSP_Greedy.checkInput(new int[][] {{1, 2, 3}, { 4, 5, 6}}, 0 ));
    }

    @Test
    public void testTSP()
    {
        int[][] adj =  {
                        {0, 1, 2, 3},
                        {2, 0, 1, 2},
                        {2, 3, 0, 1},
                        {1, 2, 3, 0}};
        int[] pfad = TSP_Greedy.findPath(adj, 0);
        assertArrayEquals(new int[] {1,2,3,0}, pfad);
    }
}
