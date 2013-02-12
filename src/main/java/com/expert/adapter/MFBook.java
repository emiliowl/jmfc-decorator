package com.expert.adapter;

import java.util.Map;

import com.expert.adapter.helpers.BlankXmlFactory;
import com.expert.jmfc.util.Book;
import com.expert.jmfc.util.BusinessException;
import com.expert.jmfc.util.Field;

public class MFBook extends Book {
	
	public Book decoratedBook = null;
	
	public MFBook(Book bookToDecorate) {
		this.decoratedBook = bookToDecorate;
	}
	
	public String toXml() {
		String xmlStringRepresentation = BlankXmlFactory.getNewBlankDocument("root").asString();		
		xmlStringRepresentation = xmlStringRepresentation.replace("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>", "");
		return xmlStringRepresentation;
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
