//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.10.24 at 11:30:02 AM CEST 
//


package de.drv.dsrv.spoc.extra.v1_3.jaxb.request;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import de.drv.dsrv.spoc.extra.v1_3.jaxb.components.AnyPlugInContainerType;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the de.drv.dsrv.spoc.extra.v1_3.jaxb.request package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _PackagePlugIns_QNAME = new QName("http://www.extra-standard.de/namespace/request/1", "PackagePlugIns");
    private final static QName _Package_QNAME = new QName("http://www.extra-standard.de/namespace/request/1", "Package");
    private final static QName _Message_QNAME = new QName("http://www.extra-standard.de/namespace/request/1", "Message");
    private final static QName _PackageHeader_QNAME = new QName("http://www.extra-standard.de/namespace/request/1", "PackageHeader");
    private final static QName _MessageBody_QNAME = new QName("http://www.extra-standard.de/namespace/request/1", "MessageBody");
    private final static QName _XMLTransport_QNAME = new QName("http://www.extra-standard.de/namespace/request/1", "XMLTransport");
    private final static QName _TransportBody_QNAME = new QName("http://www.extra-standard.de/namespace/request/1", "TransportBody");
    private final static QName _TransportPlugIns_QNAME = new QName("http://www.extra-standard.de/namespace/request/1", "TransportPlugIns");
    private final static QName _Transport_QNAME = new QName("http://www.extra-standard.de/namespace/request/1", "Transport");
    private final static QName _MessagePlugIns_QNAME = new QName("http://www.extra-standard.de/namespace/request/1", "MessagePlugIns");
    private final static QName _MessageHeader_QNAME = new QName("http://www.extra-standard.de/namespace/request/1", "MessageHeader");
    private final static QName _TransportHeader_QNAME = new QName("http://www.extra-standard.de/namespace/request/1", "TransportHeader");
    private final static QName _PackageBody_QNAME = new QName("http://www.extra-standard.de/namespace/request/1", "PackageBody");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: de.drv.dsrv.spoc.extra.v1_3.jaxb.request
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link PackageRequestType }
     * 
     */
    public PackageRequestType createPackageRequestType() {
        return new PackageRequestType();
    }

    /**
     * Create an instance of {@link MessageRequestType }
     * 
     */
    public MessageRequestType createMessageRequestType() {
        return new MessageRequestType();
    }

    /**
     * Create an instance of {@link PackageRequestBodyType }
     * 
     */
    public PackageRequestBodyType createPackageRequestBodyType() {
        return new PackageRequestBodyType();
    }

    /**
     * Create an instance of {@link TransportRequestType }
     * 
     */
    public TransportRequestType createTransportRequestType() {
        return new TransportRequestType();
    }

    /**
     * Create an instance of {@link MessageRequestBodyType }
     * 
     */
    public MessageRequestBodyType createMessageRequestBodyType() {
        return new MessageRequestBodyType();
    }

    /**
     * Create an instance of {@link TransportRequestBodyType }
     * 
     */
    public TransportRequestBodyType createTransportRequestBodyType() {
        return new TransportRequestBodyType();
    }

    /**
     * Create an instance of {@link PackageRequestHeaderType }
     * 
     */
    public PackageRequestHeaderType createPackageRequestHeaderType() {
        return new PackageRequestHeaderType();
    }

    /**
     * Create an instance of {@link MessageRequestHeaderType }
     * 
     */
    public MessageRequestHeaderType createMessageRequestHeaderType() {
        return new MessageRequestHeaderType();
    }

    /**
     * Create an instance of {@link TransportRequestHeaderType }
     * 
     */
    public TransportRequestHeaderType createTransportRequestHeaderType() {
        return new TransportRequestHeaderType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AnyPlugInContainerType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.extra-standard.de/namespace/request/1", name = "PackagePlugIns")
    public JAXBElement<AnyPlugInContainerType> createPackagePlugIns(AnyPlugInContainerType value) {
        return new JAXBElement<AnyPlugInContainerType>(_PackagePlugIns_QNAME, AnyPlugInContainerType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PackageRequestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.extra-standard.de/namespace/request/1", name = "Package")
    public JAXBElement<PackageRequestType> createPackage(PackageRequestType value) {
        return new JAXBElement<PackageRequestType>(_Package_QNAME, PackageRequestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MessageRequestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.extra-standard.de/namespace/request/1", name = "Message")
    public JAXBElement<MessageRequestType> createMessage(MessageRequestType value) {
        return new JAXBElement<MessageRequestType>(_Message_QNAME, MessageRequestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PackageRequestHeaderType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.extra-standard.de/namespace/request/1", name = "PackageHeader")
    public JAXBElement<PackageRequestHeaderType> createPackageHeader(PackageRequestHeaderType value) {
        return new JAXBElement<PackageRequestHeaderType>(_PackageHeader_QNAME, PackageRequestHeaderType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MessageRequestBodyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.extra-standard.de/namespace/request/1", name = "MessageBody")
    public JAXBElement<MessageRequestBodyType> createMessageBody(MessageRequestBodyType value) {
        return new JAXBElement<MessageRequestBodyType>(_MessageBody_QNAME, MessageRequestBodyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TransportRequestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.extra-standard.de/namespace/request/1", name = "XMLTransport")
    public JAXBElement<TransportRequestType> createXMLTransport(TransportRequestType value) {
        return new JAXBElement<TransportRequestType>(_XMLTransport_QNAME, TransportRequestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TransportRequestBodyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.extra-standard.de/namespace/request/1", name = "TransportBody")
    public JAXBElement<TransportRequestBodyType> createTransportBody(TransportRequestBodyType value) {
        return new JAXBElement<TransportRequestBodyType>(_TransportBody_QNAME, TransportRequestBodyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AnyPlugInContainerType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.extra-standard.de/namespace/request/1", name = "TransportPlugIns")
    public JAXBElement<AnyPlugInContainerType> createTransportPlugIns(AnyPlugInContainerType value) {
        return new JAXBElement<AnyPlugInContainerType>(_TransportPlugIns_QNAME, AnyPlugInContainerType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TransportRequestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.extra-standard.de/namespace/request/1", name = "Transport")
    public JAXBElement<TransportRequestType> createTransport(TransportRequestType value) {
        return new JAXBElement<TransportRequestType>(_Transport_QNAME, TransportRequestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AnyPlugInContainerType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.extra-standard.de/namespace/request/1", name = "MessagePlugIns")
    public JAXBElement<AnyPlugInContainerType> createMessagePlugIns(AnyPlugInContainerType value) {
        return new JAXBElement<AnyPlugInContainerType>(_MessagePlugIns_QNAME, AnyPlugInContainerType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MessageRequestHeaderType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.extra-standard.de/namespace/request/1", name = "MessageHeader")
    public JAXBElement<MessageRequestHeaderType> createMessageHeader(MessageRequestHeaderType value) {
        return new JAXBElement<MessageRequestHeaderType>(_MessageHeader_QNAME, MessageRequestHeaderType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TransportRequestHeaderType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.extra-standard.de/namespace/request/1", name = "TransportHeader")
    public JAXBElement<TransportRequestHeaderType> createTransportHeader(TransportRequestHeaderType value) {
        return new JAXBElement<TransportRequestHeaderType>(_TransportHeader_QNAME, TransportRequestHeaderType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PackageRequestBodyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.extra-standard.de/namespace/request/1", name = "PackageBody")
    public JAXBElement<PackageRequestBodyType> createPackageBody(PackageRequestBodyType value) {
        return new JAXBElement<PackageRequestBodyType>(_PackageBody_QNAME, PackageRequestBodyType.class, null, value);
    }

}
