package com.expert.adapter.helpers;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.security.InvalidParameterException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Comment;
import org.w3c.dom.DOMConfiguration;
import org.w3c.dom.DOMException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.EntityReference;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.Text;
import org.w3c.dom.UserDataHandler;

/**
 * This class is made to introduce a "monkey path" into the {@link Document} class.
 * It's a decorator that includes some methods that made developers life easier when
 * dealing with JMFC mainframe book xml model.
 * @author emiliowl
 *
 */
public class JmfcXmlDocument implements Document {
	// this block will initialize the JmfcXmlDocument with a blank Document to be the base
	// of this class behavior
	{ 
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = null;
		try {
			docBuilder = docFactory.newDocumentBuilder();
			baseDocument = docBuilder.newDocument();
		} catch (ParserConfigurationException e) {
			throw new FactoryConfigurationError(e);
		}
	}

	private final Document baseDocument;
	
	/**
	 * This method will add an Item Node (with the Jmfc expected format) into the node informed
	 * of this XML Document.
	 * @param id
	 * @param parentElement
	 * @return
	 */
	public JmfcXmlDocument addItem(String id, String parentElementId) {
		Element parentElement = this.getElementById(parentElementId);
		if (parentElement == null) {
			throw new InvalidParameterException("The parent element informed doesn't exist!");
		}
		//creating elements
		Element newItem = this.createElement("item");
		Element contentToItem = this.createElement("content");
		Element nameToContent = this.createElement("name");
		CDATASection cDataToName = this.createCDATASection(id);
		//setting the association between elements
		nameToContent.appendChild(cDataToName);
		contentToItem.appendChild(nameToContent);
		newItem.appendChild(contentToItem);
		newItem.setAttribute("id", id);
		newItem.setIdAttribute("id", true);
		//writing elements to root node
		parentElement.appendChild(newItem);		
		
		return this;
	}
	
	/**
	 * This method will add an Item Node (with the Jmfc expected format) into the root node
	 * of this XML Document.
	 * @param id
	 * @return
	 */
	public JmfcXmlDocument addItem(String id) {
		Element parentElement = (Element)this.getElementsByTagName("root").item(0);
		if (parentElement == null) {
			throw new IllegalStateException("Corrupted Document without root node!");
		}
		//creating elements
		Element newItem = this.createElement("item");
		Element contentToItem = this.createElement("content");
		Element nameToContent = this.createElement("name");
		CDATASection cDataToName = this.createCDATASection(id);
		//setting the association between elements
		nameToContent.appendChild(cDataToName);
		contentToItem.appendChild(nameToContent);
		newItem.appendChild(contentToItem);
		newItem.setAttribute("id", id);
		newItem.setIdAttribute("id", true);
		//writing elements to root node
		parentElement.appendChild(newItem);		
		
		return this;
	}
	
	/**
	 * return this Document in String format
	 * @return
	 */
	public String asString() {
		
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer;
		try {
			transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(this);
			
			StreamResult result = new StreamResult(pw);
			transformer.transform(source, result);
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
		return sw.toString();
	}
	
	
	// FROM HERE ARE ALL THE DELEGATE METHODS

	public Node appendChild(Node newChild) throws DOMException {
		return baseDocument.appendChild(newChild);
	}

	public Node cloneNode(boolean deep) {
		return baseDocument.cloneNode(deep);
	}

	public short compareDocumentPosition(Node other) throws DOMException {
		return baseDocument.compareDocumentPosition(other);
	}

	public NamedNodeMap getAttributes() {
		return baseDocument.getAttributes();
	}

	public String getBaseURI() {
		return baseDocument.getBaseURI();
	}

	public NodeList getChildNodes() {
		return baseDocument.getChildNodes();
	}

	public Object getFeature(String feature, String version) {
		return baseDocument.getFeature(feature, version);
	}

	public Node getFirstChild() {
		return baseDocument.getFirstChild();
	}

	public Node getLastChild() {
		return baseDocument.getLastChild();
	}

	public String getLocalName() {
		return baseDocument.getLocalName();
	}

	public String getNamespaceURI() {
		return baseDocument.getNamespaceURI();
	}

	public Node getNextSibling() {

		return baseDocument.getNextSibling();
	}

	public String getNodeName() {

		return baseDocument.getNodeName();
	}

	public short getNodeType() {

		return baseDocument.getNodeType();
	}

	public String getNodeValue() throws DOMException {

		return baseDocument.getNodeValue();
	}

	public Document getOwnerDocument() {

		return baseDocument.getOwnerDocument();
	}

	public Node getParentNode() {

		return baseDocument.getParentNode();
	}

	public String getPrefix() {

		return baseDocument.getPrefix();
	}

	public Node getPreviousSibling() {

		return baseDocument.getPreviousSibling();
	}

	public String getTextContent() throws DOMException {

		return baseDocument.getTextContent();
	}

	public Object getUserData(String key) {

		return baseDocument.getUserData(key);
	}

	public boolean hasAttributes() {

		return baseDocument.hasAttributes();
	}

	public boolean hasChildNodes() {

		return baseDocument.hasChildNodes();
	}

	public Node insertBefore(Node newChild, Node refChild) throws DOMException {

		return baseDocument.insertBefore(newChild, refChild);
	}

	public boolean isDefaultNamespace(String namespaceURI) {

		return baseDocument.isDefaultNamespace(namespaceURI);
	}

	public boolean isEqualNode(Node arg) {

		return baseDocument.isEqualNode(arg);
	}

	public boolean isSameNode(Node other) {

		return baseDocument.isSameNode(other);
	}

	public boolean isSupported(String feature, String version) {

		return baseDocument.isSupported(feature, version);
	}

	public String lookupNamespaceURI(String prefix) {

		return baseDocument.lookupNamespaceURI(prefix);
	}

	public String lookupPrefix(String namespaceURI) {

		return baseDocument.lookupPrefix(namespaceURI);
	}

	public void normalize() {
		baseDocument.normalize();

	}

	public Node removeChild(Node oldChild) throws DOMException {

		return baseDocument.removeChild(oldChild);
	}

	public Node replaceChild(Node newChild, Node oldChild) throws DOMException {

		return baseDocument.replaceChild(newChild, oldChild);
	}

	public void setNodeValue(String nodeValue) throws DOMException {
		baseDocument.setNodeValue(nodeValue);

	}

	public void setPrefix(String prefix) throws DOMException {
		baseDocument.setPrefix(prefix);

	}

	public void setTextContent(String textContent) throws DOMException {
		baseDocument.setTextContent(textContent);

	}

	public Object setUserData(String key, Object data, UserDataHandler handler) {

		return baseDocument.setUserData(key, data, handler);
	}

	public Node adoptNode(Node source) throws DOMException {

		return baseDocument.adoptNode(source);
	}

	public Attr createAttribute(String name) throws DOMException {

		return baseDocument.createAttribute(name);
	}

	public Attr createAttributeNS(String namespaceURI, String qualifiedName)
			throws DOMException {

		return baseDocument.createAttributeNS(namespaceURI, qualifiedName);
	}

	public CDATASection createCDATASection(String data) throws DOMException {

		return baseDocument.createCDATASection(data);
	}

	public Comment createComment(String data) {

		return baseDocument.createComment(data);
	}

	public DocumentFragment createDocumentFragment() {

		return baseDocument.createDocumentFragment();
	}

	public Element createElement(String tagName) throws DOMException {

		return baseDocument.createElement(tagName);
	}

	public Element createElementNS(String namespaceURI, String qualifiedName)
			throws DOMException {

		return baseDocument.createElementNS(namespaceURI, qualifiedName);
	}

	public EntityReference createEntityReference(String name)
			throws DOMException {

		return baseDocument.createEntityReference(name);
	}

	public ProcessingInstruction createProcessingInstruction(String target,
			String data) throws DOMException {

		return baseDocument.createProcessingInstruction(target, data);
	}

	public Text createTextNode(String data) {

		return baseDocument.createTextNode(data);
	}

	public DocumentType getDoctype() {

		return baseDocument.getDoctype();
	}

	public Element getDocumentElement() {

		return baseDocument.getDocumentElement();
	}

	public String getDocumentURI() {

		return baseDocument.getDocumentURI();
	}

	public DOMConfiguration getDomConfig() {

		return baseDocument.getDomConfig();
	}

	public Element getElementById(String elementId) {

		return baseDocument.getElementById(elementId);
	}

	public NodeList getElementsByTagName(String tagname) {

		return baseDocument.getElementsByTagName(tagname);
	}

	public NodeList getElementsByTagNameNS(String namespaceURI, String localName) {

		return baseDocument.getElementsByTagNameNS(namespaceURI, localName);
	}

	public DOMImplementation getImplementation() {

		return baseDocument.getImplementation();
	}

	public String getInputEncoding() {

		return baseDocument.getInputEncoding();
	}

	public boolean getStrictErrorChecking() {

		return baseDocument.getStrictErrorChecking();
	}

	public String getXmlEncoding() {

		return baseDocument.getXmlEncoding();
	}

	public boolean getXmlStandalone() {

		return baseDocument.getXmlStandalone();
	}

	public String getXmlVersion() {

		return baseDocument.getXmlVersion();
	}

	public Node importNode(Node importedNode, boolean deep) throws DOMException {

		return baseDocument.importNode(importedNode, deep);
	}

	public void normalizeDocument() {
		baseDocument.normalizeDocument();

	}

	public Node renameNode(Node n, String namespaceURI, String qualifiedName)
			throws DOMException {

		return baseDocument.renameNode(n, namespaceURI, qualifiedName);
	}

	public void setDocumentURI(String documentURI) {
		baseDocument.setDocumentURI(documentURI);

	}

	public void setStrictErrorChecking(boolean strictErrorChecking) {
		baseDocument.setStrictErrorChecking(strictErrorChecking);

	}

	public void setXmlStandalone(boolean xmlStandalone) throws DOMException {
		baseDocument.setXmlStandalone(xmlStandalone);

	}

	public void setXmlVersion(String xmlVersion) throws DOMException {
		baseDocument.setXmlVersion(xmlVersion);

	}

}
