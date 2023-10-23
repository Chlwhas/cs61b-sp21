package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove(){
        AListNoResizing<Integer> a1 = new AListNoResizing<>();
        a1.addLast(4);
        a1.addLast(5);
        a1.addLast(6);
        BuggyAList<Integer> b1 = new BuggyAList<>();
        b1.addLast(4);
        b1.addLast(5);
        b1.addLast(6);
        assertSame(a1.removeLast(), b1.removeLast());
        assertSame(a1.removeLast(), b1.removeLast());
        assertSame(a1.removeLast(), b1.removeLast());
    }

    @Test
    public void randomizedTest(){
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> B = new BuggyAList<>();

        int N = 20000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                B.addLast(randVal);
            } else if (operationNumber == 1) {
                // size
                int l_size = L.size();
                int b_size = B.size();
                assertSame(l_size, b_size);
            } else if (operationNumber == 2) {
                // getLast
                if (L.size() > 0) {
                    Integer l_last = L.getLast();
                    Integer b_last = B.getLast();
                    assertSame(l_last, b_last);
                }
            } else if (operationNumber == 3) {
                // removeLast
                if (L.size() > 0) {
                    Integer l_last = L.removeLast();
                    Integer b_last = B.removeLast();
                    assertSame(l_last, b_last);
                }
            }
        }
    }
}
