package com.expert.adapter.helpers;

import org.w3c.dom.Element;

public class BlankXmlFactory {

	/**
	 * Return a blank xml with the root name informed via parameter or null if
	 * something goes wrong.
	 * 
	 * @param rootNodeName
	 * @return {@link JmfcXmlDocument}
	 */
	public static JmfcXmlDocument getNewBlankDocument(String rootNodeName) {
		JmfcXmlDocument doc = new JmfcXmlDocument();
		Element rootElement = doc.createElement(rootNodeName);
		doc.appendChild(rootElement);
		
		return doc;
	}

}