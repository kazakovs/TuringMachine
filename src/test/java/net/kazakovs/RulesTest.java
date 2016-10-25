package net.kazakovs;

import junit.framework.TestCase;

/**
 * Created by reefer on 25.10.16.
 */
public class RulesTest extends TestCase {

    private Rules rules;
    public void setUp() throws Exception {
        super.setUp();
        rules = new Rules<String>(new String[]{
                "1|1<:>2|-1|r",
                "2|2<:>3|1|e",
                "3|2<:>5|0| "
        });
    }

    public void testGetNextState() throws Exception {
        assertEquals(2, rules.forCurrentState(1,"1").getNextState());
    }

    public void testGetNextSymbol() throws Exception {
        assertEquals("e", rules.forCurrentState(2,"2").getNextSymbol());
    }

    public void testGetNextDirection() throws Exception {
        assertEquals(0, rules.forCurrentState(3,"2").getNextDirection());
    }

}