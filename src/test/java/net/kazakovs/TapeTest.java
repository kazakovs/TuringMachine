package net.kazakovs;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * Created by reefer on 24.10.16.
 */
public class TapeTest extends TestCase {
    Tape<String> tape;
    public void setUp() throws Exception {
        super.setUp();
        tape = new Tape<>(20, " ", new String[]{"1","2","3","4","5","6"});
    }

    public void testGetCurrentSym() throws Exception {
        Assert.assertEquals(tape.getCurrentSym(), "1");
    }

    public void testSetCurrentSym() throws Exception {
        tape.setCurrentSym("12");
        Assert.assertEquals(tape.getCurrentSym(), "12");
    }

    public void testMoveRight() throws Exception {
        tape.moveRight();
        Assert.assertEquals(tape.getCurrentSym(), "2");
    }

    public void testMoveLeft() throws Exception {
        tape.moveLeft();
        Assert.assertEquals(tape.getCurrentSym(), " ");
    }

}