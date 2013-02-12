package com.expert.adapter.helpers;

import static org.junit.Assert.*;

import org.junit.Test;

public class BlankXmlFactoryTest {

	@Test
	public void test() {
		assertEquals(BlankXmlFactory.getNewBlankDocument("root").asString(), "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><root/>");
		
	}

}
