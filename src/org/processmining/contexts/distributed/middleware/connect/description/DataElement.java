//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB)
// Reference Implementation, v2.1-b02-fcs
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source
// schema.
// Generated on: 2008.08.01 at 12:14:32 PM CEST
//

package org.processmining.contexts.distributed.middleware.connect.description;

/**
 * <p>
 * Java class for DataElement complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="DataElement">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fileSize" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="physicalName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
//@XmlAccessorType(XmlAccessType.FIELD)
//@XmlType(name = "DataElement", propOrder = {
//    "fileSize",
//    "physicalName"
//})
public class DataElement {

	protected double fileSize;
	//    @XmlElement(required = true)
	protected String physicalName;

	/**
	 * Gets the value of the fileSize property.
	 * 
	 */
	public double getFileSize() {
		return fileSize;
	}

	/**
	 * Sets the value of the fileSize property.
	 * 
	 */
	public void setFileSize(double value) {
		fileSize = value;
	}

	/**
	 * Gets the value of the physicalName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPhysicalName() {
		return physicalName;
	}

	/**
	 * Sets the value of the physicalName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPhysicalName(String value) {
		physicalName = value;
	}

}
