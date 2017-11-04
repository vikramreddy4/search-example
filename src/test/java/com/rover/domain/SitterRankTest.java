package com.rover.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class SitterRankTest extends SitterRank {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetRatingScoreString() throws Exception {
		Assert.assertTrue(this.getRatingScore("xx") == (5*(1)/26));
		Assert.assertTrue(this.getRatingScore("xy") == (5*(2)/26));
		Assert.assertTrue(this.getRatingScore("xxxxxxxxxxx") == (5*(1)/26));
		Assert.assertTrue(this.getRatingScore("xyz") == (5*(3)/26));
		Assert.assertTrue(this.getRatingScore("xyz") == (5*(3)/26));
		Assert.assertTrue(this.getRatingScore("x y z") == (5*(3)/26));
		Assert.assertTrue(this.getRatingScore("x  y z") == (5*(3)/26));
		Assert.assertTrue(this.getRatingScore("xy z") == (5*(3)/26));
		System.out.println((5*(7)/26));
		System.out.println(this.getRatingScore("Laurel B."));
		Assert.assertTrue(this.getRatingScore("Laurel B.") == (5*(7)/26));
	}

}
