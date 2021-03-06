package com.expert.adapter;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import com.expert.adapter.enumeration.FieldProperties;
import com.expert.adapter.helpers.BlankXmlFactory;
import com.expert.adapter.helpers.JmfcXmlDocument;
import com.expert.jmfc.util.Book;
import com.expert.jmfc.util.BusinessException;
import com.expert.jmfc.util.Field;
import static com.expert.adapter.enumeration.FieldProperties.*

;/**
 * This class is the Official decorator implementation for MFBook, which provides extra
 * functionallity to it as format conversors and another features
 * @author emiliowl
 *
 */
public class MFBook extends Book {
	
	private static final String ROOT_NODE = "root";
	public Book decoratedBook = null;
	
	public MFBook(Book bookToDecorate) {
		this.decoratedBook = bookToDecorate;
	}
	
	/**
	 * This method return a {@link String} representation of the Book object in a XML format.
	 * It build the XML into the expected JMFC format.
	 * @return
	 */
	public String toXml() {
		JmfcXmlDocument xmlDoc = BlankXmlFactory.getNewBlankDocument(ROOT_NODE);
		
		populateXmlWithBookData(xmlDoc);
		
		String xmlStringRepresentation = xmlDoc.asString().replace("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>", ""); 
		return xmlStringRepresentation;
	}

	/**
	 * This method populates the given (expected to be empty) {@link JmfcXmlDocument} with
	 * the data from this book object.
	 * @param xmlDoc
	 */
	private void populateXmlWithBookData(JmfcXmlDocument xmlDoc) {
		populateXmlWithFieldCollectionUntilDeepestLevel(this.getInnerFields().values(), xmlDoc, ROOT_NODE);
	}
	
	/**
	 * Helper method that populate the given {@link JmfcXmlDocument} with the {@link Collection} 
	 * given and respecting the given parentElement.
	 * 
	 * @param fields
	 * @param xmlDoc
	 * @param parentElementId
	 */
	private void populateXmlWithFieldCollectionUntilDeepestLevel(Collection<Field> fields, JmfcXmlDocument xmlDoc, String parentElementId) {
		Iterator<Field> iterator = fields.iterator();
		while(iterator.hasNext()) {
			Field field = iterator.next();
			if (field.hasInnerFields()) {
				xmlDoc.addItem(field.getName(), parentElementId);
				populateXmlWithFieldCollectionUntilDeepestLevel(field.getInnerFields().values(), xmlDoc, field.getName());
			} else {
				xmlDoc.addItem(field.getName(), parentElementId);
			}
		}
	}
	
	/**
	 * return a {@link Map} containing the info for all the fields of this book. The Map contains
	 * the name of the fields as the key and a map containing all the properties of this field 
	 * (given in the enum {@link FieldProperties}. 
	 * 
	 * @return
	 */
	public Map<String, Map<FieldProperties, String>> getFieldsInfo() {
		Map<String, Map<FieldProperties,String>> mapToPopulate = new HashMap<String, Map<FieldProperties,String>>();
		fillMapWithFieldsInfo(this.getInnerFields().values(), mapToPopulate);
		return mapToPopulate;
	}
	
	/**
	 * 
	 * @param fields
	 * @param mapToPopulate
	 */
	private void fillMapWithFieldsInfo(Collection<Field> fields, Map<String, Map<FieldProperties,String>> mapToPopulate) {
		Iterator<Field> iterator = fields.iterator();
		while(iterator.hasNext()) {
			Field field = iterator.next();
			if (field.hasInnerFields()) {
				mapToPopulate.put(field.getName(), getFieldInfo(field));
				fillMapWithFieldsInfo(field.getInnerFields().values(), mapToPopulate);
			} else {
				mapToPopulate.put(field.getName(), getFieldInfo(field));
			}
		}
	}
	
	/**
	 * Return a {@link Map} containin the {@link FieldProperties} as the key and the respective 
	 * value of it according to given field
	 * @param field
	 * @return
	 */
	private Map<FieldProperties, String> getFieldInfo(Field field) {
		Map<FieldProperties, String> fieldInfo = new LinkedHashMap<FieldProperties, String>();
		
		fieldInfo.put(LEVEL, "" + field.getLevel());
		fieldInfo.put(FIELD_TYPE, "" + field.getFieldType());
		fieldInfo.put(MAIN_LENGTH, "" + field.getMainLenght());
		fieldInfo.put(DECIMAL_POINTS, "" + field.getDecimalPoints());
		fieldInfo.put(FISICAL_LENGTH, ""  + field.getFisicalLenght());
		fieldInfo.put(OCCURS_SEQUENCE, ""  + field.getOccursSequence());
		fieldInfo.put(MIN_OCCURS, ""  + field.getMinOccurs());
		fieldInfo.put(MAX_OCCURS, ""  + field.getMaxOccurs());				
		fieldInfo.put(OCCURS_FIELD_NAME, ""  + field.getOccursFieldName());
		fieldInfo.put(VALUE, ""  + field.getValue());
		
		return fieldInfo;
	}

	/**
	 * @return
	 * @see com.expert.jmfc.util.Book#hasInnerFields()
	 */
	public boolean hasInnerFields() {
		return decoratedBook.hasInnerFields();
	}

	/**
	 * @return
	 * @see com.expert.jmfc.util.Book#clone()
	 */
	public Book clone() {
		return decoratedBook.clone();
	}

	/**
	 * @param arg0
	 * @return
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object arg0) {
		return decoratedBook.equals(arg0);
	}

	/**
	 * @return
	 * @see com.expert.jmfc.util.Book#getName()
	 */
	public String getName() {
		return decoratedBook.getName();
	}

	/**
	 * @return
	 * @see com.expert.jmfc.util.Book#getInnerFields()
	 */
	public Map<String, Field> getInnerFields() {
		return decoratedBook.getInnerFields();
	}

	/**
	 * @param innerFieldName
	 * @return
	 * @throws BusinessException
	 * @see com.expert.jmfc.util.Book#get(java.lang.String)
	 */
	public Field get(String innerFieldName) throws BusinessException {
		return decoratedBook.get(innerFieldName);
	}

	/**
	 * @param innerFieldName
	 * @param innerFieldOccur
	 * @return
	 * @throws BusinessException
	 * @see com.expert.jmfc.util.Book#get(java.lang.String, int)
	 */
	public Field get(String innerFieldName, int innerFieldOccur)
			throws BusinessException {
		return decoratedBook.get(innerFieldName, innerFieldOccur);
	}

	/**
	 * @return
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return decoratedBook.hashCode();
	}

	/**
	 * @param name
	 * @see com.expert.jmfc.util.Book#setName(java.lang.String)
	 */
	public void setName(String name) {
		decoratedBook.setName(name);
	}

	/**
	 * @param innerFields
	 * @see com.expert.jmfc.util.Book#setInnerFields(java.util.Map)
	 */
	public void setInnerFields(Map<String, Field> innerFields) {
		decoratedBook.setInnerFields(innerFields);
	}

	/**
	 * @param fieldName
	 * @param field
	 * @see com.expert.jmfc.util.Book#putInnerField(java.lang.String, com.expert.jmfc.util.Field)
	 */
	public void putInnerField(String fieldName, Field field) {
		decoratedBook.putInnerField(fieldName, field);
	}

	/**
	 * @return
	 * @see com.expert.jmfc.util.Book#toString()
	 */
	public String toString() {
		return decoratedBook.toString();
	}
}
