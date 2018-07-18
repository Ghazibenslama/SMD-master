//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.3 in JDK 1.6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2009.09.15 at 04:54:36 PM EST 
//


package de.epml;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for typeArc complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="typeArc">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.epml.de}tExtensibleElements">
 *       &lt;sequence>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;choice>
 *           &lt;element name="flow" type="{http://www.epml.de}typeFlow" minOccurs="0"/>
 *           &lt;element name="relation" type="{http://www.epml.de}typeRelation" minOccurs="0"/>
 *         &lt;/choice>
 *         &lt;choice maxOccurs="unbounded" minOccurs="0">
 *           &lt;element name="graphics" type="{http://www.epml.de}typeMove"/>
 *         &lt;/choice>
 *         &lt;choice maxOccurs="unbounded" minOccurs="0">
 *           &lt;element name="attribute" type="{http://www.epml.de}typeAttribute"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlRootElement(name = "arc")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "typeArc", propOrder = {
    "name",
    "description",
    "flow",
    "relation",
    "graphics",
    "attribute"
})
public class TypeArc
    extends TExtensibleElements
{

    protected String name;
    protected String description;
    protected TypeFlow flow;
    protected TypeRelation relation;
    protected List<TypeMove> graphics;
    protected List<TypeAttribute> attribute;
    @XmlAttribute(required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger id;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the flow property.
     * 
     * @return
     *     possible object is
     *     {@link TypeFlow }
     *     
     */
    public TypeFlow getFlow() {
        return flow;
    }

    /**
     * Sets the value of the flow property.
     * 
     * @param value
     *     allowed object is
     *     {@link TypeFlow }
     *     
     */
    public void setFlow(TypeFlow value) {
        this.flow = value;
    }

    /**
     * Gets the value of the relation property.
     * 
     * @return
     *     possible object is
     *     {@link TypeRelation }
     *     
     */
    public TypeRelation getRelation() {
        return relation;
    }

    /**
     * Sets the value of the relation property.
     * 
     * @param value
     *     allowed object is
     *     {@link TypeRelation }
     *     
     */
    public void setRelation(TypeRelation value) {
        this.relation = value;
    }

    /**
     * Gets the value of the graphics property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the graphics property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGraphics().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TypeMove }
     * 
     * 
     */
    public List<TypeMove> getGraphics() {
        if (graphics == null) {
            graphics = new ArrayList<TypeMove>();
        }
        return this.graphics;
    }

    /**
     * Gets the value of the attribute property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the attribute property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAttribute().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TypeAttribute }
     * 
     * 
     */
    public List<TypeAttribute> getAttribute() {
        if (attribute == null) {
            attribute = new ArrayList<TypeAttribute>();
        }
        return this.attribute;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setId(BigInteger value) {
        this.id = value;
    }

}
