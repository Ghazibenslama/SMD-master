//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.10.23 at 04:10:21 PM EST 
//


package org.apromore.pnml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for occuredtransitionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="occuredtransitionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="transitionID" type="{http://www.w3.org/2001/XMLSchema}IDREF" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "occuredtransitionType")
public class OccuredtransitionType {

    @XmlAttribute(name = "transitionID")
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected Object transitionID;

    /**
     * Gets the value of the transitionID property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getTransitionID() {
        return transitionID;
    }

    /**
     * Sets the value of the transitionID property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setTransitionID(Object value) {
        this.transitionID = value;
    }

}
