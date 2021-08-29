package cpsc2150.MyDeque;

import org.junit.Test;
import static org.junit.Assert.*;

/*
 @author Mike Brandin
 @author Davis Weitzel
 date 03/04/2021
 */

public class TestArrayDeque {

    private IDeque<Character> MakeADeque() {
        IDeque<Character> o = new ArrayDeque<Character>();
        return o;
    }

    @Test
    public void testEnqueue_1() {//testing enqueuing a deque to its max length (100)
        IDeque<Character> test = MakeADeque();
        for (int i = 65; i < 126; i++) {
            test.enqueue((char) i);
        }
        for (int i = 65; i < 104; i++) {
            test.enqueue((char) i);
        }

        String testString = "Deque is:\n<A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z, [, \\, ], ^, _, `, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z, {, |, }, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z, [, \\, ], ^, _, `, a, b, c, d, e, f, g>\n";

        assertEquals(100, test.length());
        assertEquals(testString, test.toString());

    }

    @Test
    public void testEnqueue_2() {//testing enqueuing a deque to a length of 26
        IDeque<Character> test = MakeADeque();
        for (int i = 65; i < 91; i++) {
            test.enqueue((char) i);
        }
        String testString = "Deque is:\n<A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z>\n";

        assertEquals(26, test.length());
        assertEquals(testString, test.toString());

    }

    @Test
    public void testEnqueue_3() {//testing enqueuing a deque to a length of 1
        IDeque<Character> test = MakeADeque();
        test.enqueue('A');
        String testString = "Deque is:\n<A>\n";
        assertEquals(testString, test.toString());
    }

    @Test
    public void testDequeue_1() {//testing a full deque and fully dequeuing the the deque
        IDeque<Character> test = MakeADeque();
        for (int i = 65; i < 126; i++) {
            test.enqueue((char) i);
        }
        for (int i = 65; i < 104; i++) {
            test.enqueue((char) i);
        }
        for (int i = 65; i < 126; i++) {
            assertTrue((char) i == test.dequeue());
        }
        for (int i = 65; i < 104; i++) {
            assertTrue((char) i == test.dequeue());
        }

        String testString = "Deque is:\n<>\n";
        assertEquals(testString, test.toString());

    }

    @Test
    public void testDequeue_2() {//testing a deque of length 26 and dequeuing half of it (13)
        IDeque<Character> test = MakeADeque();
        for (int i = 65; i < 91; i++) {
            test.enqueue((char) i);
        }
        for (int i = 65; i < 78; i++) {
            assertTrue((char) i == test.dequeue());
        }

        String testString = "Deque is:\n<N, O, P, Q, R, S, T, U, V, W, X, Y, Z>\n";
        assertEquals(testString, test.toString());
    }

    @Test
    public void testDequeue_3() {//testing if deque works for a Deque of length 1, dequeuing it entirely
        IDeque<Character> test = MakeADeque();
        test.enqueue('A');
        assertTrue('A' == test.dequeue());
        String testString = "Deque is:\n<>\n";
        assertEquals(testString, test.toString());
    }

    @Test
    public void testInject_1() {//testing injecting an empty deque to a length of 1
        IDeque<Character> test = MakeADeque();
        test.inject('A');
        String testString = "Deque is:\n<A>\n";
        assertEquals(testString, test.toString());
    }

    @Test
    public void testInject_2() {//testing injecting a deque to a length of 26
        IDeque<Character> test = MakeADeque();
        for (int i = 65; i < 91; i++) {
            test.inject((char) i);
        }
        String testString = "Deque is:\n<Z, Y, X, W, V, U, T, S, R, Q, P, O, N, M, L, K, J, I, H, G, F, E, D, C, B, A>\n";
        assertEquals(testString, test.toString());
    }

    @Test
    public void testInject_3() {//testing injecting a deque to its max length (100)
        IDeque<Character> test = MakeADeque();
        for (int i = 65; i < 126; i++) {
            test.inject((char) i);
        }
        for (int i = 65; i < 104; i++) {
            test.inject((char) i);
        }
        String testString = "Deque is:\n<g, f, e, d, c, b, a, `, _, ^, ], \\, [, Z, Y, X, W, V, U, T, S, R, Q, P, O, N, M, L, K, J, I, H, G, F, E, D, C, B, A, }, |, {, z, y, x, w, v, u, t, s, r, q, p, o, n, m, l, k, j, i, h, g, f, e, d, c, b, a, `, _, ^, ], \\, [, Z, Y, X, W, V, U, T, S, R, Q, P, O, N, M, L, K, J, I, H, G, F, E, D, C, B, A>";
    }

    @Test
    public void testRemoveLast_1() {//remove the last element from deque of size 1
        IDeque<Character> test = MakeADeque();
        test.enqueue('A');
        assertTrue('A' == test.removeLast());
        String testString = "Deque is:\n<>\n";
        assertEquals(testString, test.toString());
    }

    @Test
    public void testRemoveLast_2() {//remove the last half of characters from a deque of length 26
        IDeque<Character> test = MakeADeque();

        for (int i = 65; i < 91; i++) {
            test.enqueue((char) i);
        }
        for (int i = 90; i >= 78; i--) {
            assertTrue((char) i == test.removeLast());
        }

        String testString = "Deque is:\n<A, B, C, D, E, F, G, H, I, J, K, L, M>\n";
        assertEquals(testString, test.toString());
    }

    @Test
    public void testRemoveLast_3(){//use remove last on all elements of a full deque
        IDeque<Character> test = MakeADeque();

        for (int i = 65; i < 126; i++) {
            test.enqueue((char) i);
        }
        for (int i = 65; i < 104; i++) {
            test.enqueue((char) i);
        }
        for (int i = 103; i >= 65; i--) {
            assertTrue((char) i == test.removeLast());
        }
        for (int i = 125;  i >= 65; i--) {
            assertTrue((char) i == test.removeLast());
        }

        String testString = "Deque is:\n<>\n";
        assertEquals(testString,test.toString());
    }

    @Test
    public void testClear_1(){//clear a deque of length 26
        IDeque<Character> test = MakeADeque();
        for (int i = 65; i < 91; i++) {
            test.enqueue((char)i);
        }
        test.clear();
        String testString = "Deque is:\n<>\n";
        assertEquals(testString,test.toString());
    }
    @Test
    public void testClear_2(){//clear a deque of max length (100)
        IDeque<Character> test = MakeADeque();
        for (int i = 65; i < 126; i++) {
            test.enqueue((char)i);
        }
        for(int i = 65; i < 104; i++){
            test.enqueue((char)i);
        }
        test.clear();
        String testString = "Deque is:\n<>\n";
        assertEquals(testString,test.toString());
    }
    @Test
    public void testClear_3(){//clear a deque of length 0
        IDeque<Character> test = MakeADeque();
        test.enqueue('A');
        test.clear();
        String testString = "Deque is:\n<>\n";
        assertEquals(testString,test.toString());
    }

    @Test
    public void testPeek_1(){//peek to the front of a deque of length 26
        IDeque<Character> test = MakeADeque();
        for (int i = 65; i < 91; i++) {
            test.enqueue((char)i);
        }
        assertTrue(test.peek() == 'A');
    }
    @Test
    public void testPeek_2(){//peek to the front of a deque of max length (100)
        IDeque<Character> test = MakeADeque();
        for (int i = 65; i < 126; i++) {
            test.enqueue((char)i);
        }
        for(int i = 65; i < 104; i++){
            test.enqueue((char)i);
        }
        assertTrue(test.peek() == 'A');
    }
    @Test
    public void testPeek_3() {//peek to the front of a deque of length 1
        IDeque<Character> test = MakeADeque();
        test.enqueue('A');
        assertTrue(test.peek() == 'A');
    }

    @Test
    public void testEndOfDeque_1(){//peek to the end of a deque of length 1
        IDeque<Character> test = MakeADeque();
        test.enqueue('A');
        assertTrue(test.endOfDeque() == 'A');
    }
    @Test
    public void testEndOfDeque_2(){//peek to the end of a deque of max length (100)
        IDeque<Character> test = MakeADeque();
        for (int i = 65; i < 126; i++) {
            test.enqueue((char)i);
        }
        for(int i = 65; i < 104; i++){
            test.enqueue((char)i);
        }
        assertTrue(test.endOfDeque() == 'g');
    }
    @Test
    public void testEndOfDeque_3(){//peek to the end of a deque of length 26
        IDeque<Character> test = MakeADeque();
        for (int i = 65; i < 91; i++) {
            test.enqueue((char)i);
        }
        assertTrue(test.endOfDeque() == 'Z');
    }

    @Test
    public void testInsert_1(){//insert an element to a deque of length 1
        IDeque<Character> test = MakeADeque();
        test.enqueue('B');
        test.insert('A',1);
        String testString = "Deque is:\n<A, B>\n";
        assertTrue(test.get(1) =='A');
        assertEquals(testString,test.toString());
    }
    @Test
    public void testInsert_2(){//insert an element to a deque of length 26
        IDeque<Character> test = MakeADeque();
        for (int i = 65; i < 91; i++) {
            test.enqueue((char)i);
        }
        String testString = "Deque is:\n<A, B, C, D, E, F, G, H, I, J, K, L, A, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z>\n";
        test.insert('A',13);
        assertTrue(test.get(13)=='A');
        assertEquals(testString,test.toString());
    }
    @Test
    public void testInsert_3(){//insert an element to a deque of max length (100)
        IDeque<Character> test = MakeADeque();
        for (int i = 65; i < 126; i++) {
            test.enqueue((char)i);
        }
        for(int i = 65; i < 103; i++){
            test.enqueue((char)i);
        }
        test.insert('A',99);
        String testString = "Deque is:\n<A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z, [, \\, ], ^, _, `, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z, {, |, }, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z, [, \\, ], ^, _, `, a, b, c, d, e, A, f>\n";
        assertTrue(test.get(99) == 'A');
        assertEquals(testString,test.toString());
    }

    @Test
    public void testRemove_1(){//remove an element from a deque of length 1 to check that it returns proper character, check that deque is empty
        IDeque<Character> test = MakeADeque();
        test.enqueue('A');

        assertTrue('A' == test.remove(1));
        String testString = "Deque is:\n<>\n";
        assertEquals(testString, test.toString());
    }
    @Test
    public void testRemove_2(){//remove all elements from a deque of length 26 to check that it returns proper character, and check that deque is empty
        IDeque<Character> test = MakeADeque();

        for (int i = 65; i < 91; i++) {
            test.enqueue((char)i);
        }

        int counter = 26;
        for (int i = 90; i >= 65; i--) {
            assertTrue((char)i == test.remove(counter));
            counter--;
        }

        String testString = "Deque is:\n<>\n";
        assertEquals(testString, test.toString());
    }
    @Test
    public void testRemove_3(){//remove all elements from a deque of max length (100) to check that it returns proper character, and check that deque is empty
        IDeque<Character> test = MakeADeque();
        for (int i = 65; i < 126; i++) {
            test.enqueue((char)i);
        }
        for(int i = 65; i < 104; i++){
            test.enqueue((char)i);
        }
        int counter = 100;
        for (int i = 103; i >= 65; i--) {
            assertTrue((char)i == test.remove(counter));
            counter--;
        }
        for (int i = 125;  i >= 65; i--) {
            assertTrue((char)i == test.remove(counter));
            counter--;
        }
        String testString = "Deque is:\n<>\n";
        assertEquals(testString, test.toString());

    }

    @Test
    public void testGet_1(){//get an element from a deque of length 1 to check that it returns proper character, and check that deque is unchanged
        IDeque<Character> test = MakeADeque();
        test.enqueue('A');

        assertTrue('A' == test.get(1));
        String testString = "Deque is:\n<A>\n";
        assertEquals(testString, test.toString());

    }
    @Test
    public void testGet_2(){//get all elements from a deque of length 26 to check that it returns proper character, and check that deque is unchanged
        IDeque<Character> test = MakeADeque();
        for (int i = 65; i < 91; i++) {
            test.enqueue((char)i);
        }
        for(int i = 1; i <= 26; i++){
            assertTrue(test.get(i) == (char)(i+64));
        }
    }
    @Test
    public void testGet_3() {//get all elements from a deque of max length (100) to check that it returns proper character, and check that deque is unchanged
        IDeque<Character> test = MakeADeque();
        for (int i = 65; i < 126; i++) {
            test.enqueue((char) i);
        }
        for(int i = 65; i < 104; i++){
            test.enqueue((char)i);
        }
        String testString = "Deque is:\n<A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z, [, \\, ], ^, _, `, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z, {, |, }, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z, [, \\, ], ^, _, `, a, b, c, d, e, f, g>\n";
        int counter = 100;
        for (int i = 103; i >= 65; i--) {
            assertTrue((char)i == test.get(counter));
            counter--;
        }
        for (int i = 125;  i >= 65; i--) {
            assertTrue((char)i == test.get(counter));
            counter--;
        }
    }
}