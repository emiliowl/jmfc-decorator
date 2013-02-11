package com.expert.adapter;

import com.expert.jmfc.util.Book;

public class MFBook extends Book {
	
	public Book decoratedBook = null;
	
	public MFBook(Book bookToDecorate) {
		this.decoratedBook = bookToDecorate;
	}
	
	public String toXml() {
		return "<root>"+
				"<item id='xml_1'>"+
					"<content>"+
						"<name><![CDATA[Root node 1]]></name>"+
					"</content>"+
				"</item>"+
				"<item id='outra_coisa'>"+
					"<content>"+
						"<name><![CDATA[Root node 2]]></name>"+
					"</content>"+
					"<item id='xml_2'>"+
						"<content>"+
							"<name><![CDATA[Child node 1(from root 2)]]></name>"+
						"</content>"+
					"</item>"+
				"</item>"+
			"</root>";
	}
}
