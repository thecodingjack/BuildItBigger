package com.thecodingjack.androidjokelib;

import org.junit.Test;
import static junit.framework.Assert.assertEquals;

/**
 * Created by lamkeong on 7/11/2017.
 */

public class EchoTest {
    @Test
    public void verifyGCMResponse(){
        assertEquals("This is another smashing joke", JokeActivity.echo("This is another smashing joke"));
    }
}
