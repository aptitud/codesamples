package se.aptitud.jelmstrom.montyhall;


import org.junit.Test;
import se.aptitud.jelmstrom.montyhall.MontyHallSimulation;

import static org.junit.Assert.assertTrue;
import static se.aptitud.jelmstrom.montyhall.MontyHallSimulation.*;

/**
 * A test attempting to prove that in a MontyHall problem scenario it is always better to change the
 * selection when given the opportunity.
 *
 * DISCLAIMER:
 * Asserting that the probability is around 33 / 66 % is not reliable, the tests could be unlucky. These tests will most
 * likely catch if someone changes the logic (i.e alters the number of boxes) but can fail without a code change, which
 * is not ideal.
 *
 *
 */
public class MontyHallTest {



    @Test
    public void successRateOf10000GamesShouldBeAbout33PercentIfNotChangingBox(){
        int wins = 0;
        for(int game=0; game<10000; game++){
            if(Prize.MONEY.equals(MontyHallSimulation.runGameWithoutChangingBox())){
                wins++;
            }
        }
        assertTrue(wins > 3000);
        assertTrue(wins < 3500);
    }

    @Test
    public void successRateOf10000GamesShouldBeAbout66PercentIfChangingBox(){
        int wins = 0;
        for(int game=0; game<10000; game++){
            if(Prize.MONEY.equals(MontyHallSimulation.runGameWithChangingBox())){
                wins++;
            }
        }

        assertTrue(wins > 6400);
        assertTrue(wins < 6900);
    }

}
