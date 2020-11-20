package de.hfu;

import junit.framework.TestCase;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase
{
    @Test
    public void testIstErstesHalbjahr()
    {
        final int eingabe = 5;
        assertTrue("Methode funtkioniert", Util.istErstesHalbjahr(eingabe));
        assertEquals("Nicht erstes Halbjahr", false, Util.istErstesHalbjahr(8));
        try {
        	Util.istErstesHalbjahr(15);
        	fail("Erwartete Exception wurde nicht geworfen");
        } catch (IllegalArgumentException e) {}
        try {
        	Util.istErstesHalbjahr(0);
        	fail("Erwartete Fehlermeldung nicht geworfen.");
        } catch (IllegalArgumentException e) {}
    }
    
    public void testEnqueue() {
    	Queue queue = new Queue(3);
    	try {
    		queue.enqueue(3);
    		queue.enqueue(5);
    		queue.enqueue(4);
    		queue.enqueue(2);
    	} catch (ArrayIndexOutOfBoundsException e) {
    		fail("Arrayüberlauf");
    	}
    }
    
    public void testQueue() {
    	try {
    		Queue queue = new Queue(-3);
    		fail("Erwarteter Fehler nicht geworfen.");
    	}catch (IllegalArgumentException e) {}
    }
    
    public void testDequeue() {
    	Queue queue = new Queue(3);
    	try {
    		queue.dequeue();
    		fail("Erwartete Fehlermeldung nicht geworfen.");
    	} catch (IllegalStateException e) {}
    	queue.enqueue(3);
    	queue.enqueue(4);
    	queue.enqueue(5);
    	queue.enqueue(6);
    	queue.enqueue(7);
    	assertEquals("dequeue überschreibt längsten existierenden Wert", 3, queue.dequeue());
    }
    
}
