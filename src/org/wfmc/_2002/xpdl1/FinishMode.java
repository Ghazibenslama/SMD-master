//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.10.23 at 04:10:25 PM EST 
//


package org.wfmc._2002.xpdl1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element ref="{http://www.wfmc.org/2002/XPDL1.0}Automatic"/>
 *         &lt;element ref="{http://www.wfmc.org/2002/XPDL1.0}Manual"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "automatic",
    "manual"
})
@XmlRootElement(name = "FinishMode")
public class FinishMode {

    @XmlElement(name = "Automatic")
    protected Automatic automatic;
    @XmlElement(name = "Manual")
    protected Manual manual;

    /**
     * Gets the value of the automatic property.
     * 
     * @return
     *     possible object is
     *     {@link Automatic }
     *     
     */
    public Automatic getAutomatic() {
        return automatic;
    }

    /**
     * Sets the value of the automatic property.
     * 
     * @param value
     *     allowed object is
     *     {@link Automatic }
     *     
     */
    public void setAutomatic(Automatic value) {
        this.automatic = value;
    }

    /**
     * Gets the value of the manual property.
     * 
     * @return
     *     possible object is
     *     {@link Manual }
     *     
     */
    public Manual getManual() {
        return manual;
    }

    /**
     * Sets the value of the manual property.
     * 
     * @param value
     *     allowed object is
     *     {@link Manual }
     *     
     */
    public void setManual(Manual value) {
        this.manual = value;
    }

}
