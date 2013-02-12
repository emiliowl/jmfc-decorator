package com.expert.adapter.helpers;

import static org.junit.Assert.*;

import org.junit.Test;

public class JmfcXmlDocumentTest {

	@Test
	public void testAsString() {
		JmfcXmlDocument doc = new JmfcXmlDocument();
		assertEquals(doc.asString().trim(), "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>");
	}

}
