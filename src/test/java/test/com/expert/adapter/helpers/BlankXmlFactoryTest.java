package test.com.expert.adapter.helpers;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.expert.adapter.helpers.BlankXmlFactory;

public class BlankXmlFactoryTest {

	@Test
	public void test() {
		assertEquals(BlankXmlFactory.getNewBlankDocument("root").asString(), "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><root id=\"root\"/>");
		
	}

}
