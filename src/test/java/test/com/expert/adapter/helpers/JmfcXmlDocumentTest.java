package test.com.expert.adapter.helpers;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.expert.adapter.helpers.BlankXmlFactory;
import com.expert.adapter.helpers.JmfcXmlDocument;

public class JmfcXmlDocumentTest {

	@Test
	public void testAsString() {
		JmfcXmlDocument doc = new JmfcXmlDocument();
		assertEquals(doc.asString().trim(), "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>");
	}
	
	@Test
	public void testAddItem() {
		JmfcXmlDocument doc = BlankXmlFactory.getNewBlankDocument("root");
		doc.addItem("1");
		String expectedXml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>" +
				"<root id=\"root\">" +
					"<item id=\"1\">" +
						"<content>" +
							"<name>" +
								"<![CDATA[1]]>" +
							"</name>" +
						"</content>" +
					"</item>" +
				"</root>";
		assertEquals(doc.asString(), expectedXml);
	}

}