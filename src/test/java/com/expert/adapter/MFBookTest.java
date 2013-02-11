/**
 * 
 */
package com.expert.adapter;

import static org.junit.Assert.assertEquals;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.expert.jmfc.compiler.SyntacticAnalyzer;
import com.expert.jmfc.store.BookStore;
import com.expert.jmfc.util.Book;
import com.expert.jmfc.util.BusinessException;
import com.expert.jmfc.util.Field;

/**
 * @author emiliowl
 *
 */
public class MFBookTest {
		
	@Before
	public void loadBook() throws IOException, BusinessException {
		Map<String, String> bookFiles = new HashMap<String, String>();
		bookFiles.put("book", "target" + File.separator + "test-classes" + File.separator + "book.txt");
		BookStore.loadBooks(bookFiles);
	}

	@Test
	public void onCallToXmlShouldReturnBookXmlRepresentation() throws FileNotFoundException, IOException {
		//reading the book from test path
		BufferedReader bookStream = new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream("expected_to_xml_return.xml")));
		String xmlBook = "";
		String linha;
		while ((linha = bookStream.readLine()) != null) {
			xmlBook += linha;
		}
		xmlBook = xmlBook.replace("\t", "");
											
		try {
			MFBook mfbook = new MFBook(BookStore.getBook("book"));
			assertEquals(mfbook.toXml().trim(), xmlBook.trim());
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}

}