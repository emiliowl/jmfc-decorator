/**
 * 
 */
package com.expert.adapter;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.expert.jmfc.store.BookStore;
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
	public void onCallGetInnerFieldsShouldRetrieveAllFieldsInOrder() throws BusinessException {
		MFBook mfbook = new MFBook(BookStore.getBook("book"));
		//iterating trough the first level
		Map<String, Field> innerFields = mfbook.getInnerFields();
		Set<String> fields = innerFields.keySet();
		Iterator<String> fieldsIterator = fields.iterator();
		String studentDetails = fieldsIterator.next();
		assertEquals(studentDetails,"StudentDetails");
		assertEquals(fieldsIterator.hasNext(), false);
		
		//going deeper in the book trough the second level
		Field studentDetailsField = innerFields.get(studentDetails);
		Set<String> studentDetailsInnerFields = studentDetailsField.getInnerFields().keySet();
		Iterator<String> studentDetailsIterator = studentDetailsInnerFields.iterator();
		String studentId  = studentDetailsIterator.next();
		String studentName = studentDetailsIterator.next();
		assertEquals(studentId, "StudentId");
		assertEquals(studentName, "StudentName");
		assertEquals(studentDetailsIterator.hasNext(), false);
		
		//getting finished in the book trough the third level
		Field studentIdField = studentDetailsField.get(studentId);
		assertEquals(studentIdField.getInnerFields(), null);
		Field studentNameField = studentDetailsField.get(studentName);
		Iterator<String> studentNameIterator = studentNameField.getInnerFields().keySet().iterator();
		String firstName  = studentNameIterator.next();
		String middleInitial  = studentNameIterator.next();
		String surname  = studentNameIterator.next();
		assertEquals(firstName, "FirstName");
		assertEquals(middleInitial, "MiddleInitial");
		assertEquals(surname, "Surname");
		assertEquals(studentNameIterator.hasNext(), false);
		
		//asserting that the book was runned to the deeper level
		assertEquals(studentNameField.get(firstName).getInnerFields(), null);
		assertEquals(studentNameField.get(middleInitial).getInnerFields(), null);
		assertEquals(studentNameField.get(surname).getInnerFields(), null);
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