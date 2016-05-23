package com.abc;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class DateProviderTest {

	@Test
    public void testProviderType() {
        DateProvider dp = DateProvider.getInstance() ;
        assertTrue(dp instanceof DateProvider);
    }
	
	@Test
    public void testProviderSingleInstance() {
        DateProvider dp1 = DateProvider.getInstance() ;
        DateProvider dp2 = DateProvider.getInstance() ;
        assertTrue(dp1 == dp2);
    }
	
}

