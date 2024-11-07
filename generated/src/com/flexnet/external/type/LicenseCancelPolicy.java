
package com.flexnet.external.type;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for LicenseCancelPolicy complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LicenseCancelPolicy">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cancelLicense" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="isIgnored" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LicenseCancelPolicy", propOrder = {
    "cancelLicense",
    "isIgnored"
})
public class LicenseCancelPolicy {

    @XmlElement(required = true, type = Boolean.class, nillable = true)
    protected Boolean cancelLicense;
    @XmlElement(required = true, type = Boolean.class, nillable = true)
    protected Boolean isIgnored;

    /**
     * Gets the value of the cancelLicense property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCancelLicense() {
        return cancelLicense;
    }

    /**
     * Sets the value of the cancelLicense property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCancelLicense(Boolean value) {
        this.cancelLicense = value;
    }

    /**
     * Gets the value of the isIgnored property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsIgnored() {
        return isIgnored;
    }

    /**
     * Sets the value of the isIgnored property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsIgnored(Boolean value) {
        this.isIgnored = value;
    }

}
