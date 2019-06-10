
package it.pastorini.soap.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the it.pastorini.soap.client package. 
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

    private final static QName _GetBiosResponse_QNAME = new QName("http://server.soap.pastorini.it/", "getBiosResponse");
    private final static QName _GetBios_QNAME = new QName("http://server.soap.pastorini.it/", "getBios");
    private final static QName _GetNumberOfArtists_QNAME = new QName("http://server.soap.pastorini.it/", "getNumberOfArtists");
    private final static QName _GetArtists_QNAME = new QName("http://server.soap.pastorini.it/", "getArtists");
    private final static QName _GetArtistsResponse_QNAME = new QName("http://server.soap.pastorini.it/", "getArtistsResponse");
    private final static QName _GetNumberOfArtistsResponse_QNAME = new QName("http://server.soap.pastorini.it/", "getNumberOfArtistsResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: it.pastorini.soap.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetArtists }
     * 
     */
    public GetArtists createGetArtists() {
        return new GetArtists();
    }

    /**
     * Create an instance of {@link GetArtistsResponse }
     * 
     */
    public GetArtistsResponse createGetArtistsResponse() {
        return new GetArtistsResponse();
    }

    /**
     * Create an instance of {@link GetNumberOfArtistsResponse }
     * 
     */
    public GetNumberOfArtistsResponse createGetNumberOfArtistsResponse() {
        return new GetNumberOfArtistsResponse();
    }

    /**
     * Create an instance of {@link GetBiosResponse }
     * 
     */
    public GetBiosResponse createGetBiosResponse() {
        return new GetBiosResponse();
    }

    /**
     * Create an instance of {@link GetBios }
     * 
     */
    public GetBios createGetBios() {
        return new GetBios();
    }

    /**
     * Create an instance of {@link GetNumberOfArtists }
     * 
     */
    public GetNumberOfArtists createGetNumberOfArtists() {
        return new GetNumberOfArtists();
    }

    /**
     * Create an instance of {@link ArtistImpl }
     * 
     */
    public ArtistImpl createArtistImpl() {
        return new ArtistImpl();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetBiosResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.soap.pastorini.it/", name = "getBiosResponse")
    public JAXBElement<GetBiosResponse> createGetBiosResponse(GetBiosResponse value) {
        return new JAXBElement<GetBiosResponse>(_GetBiosResponse_QNAME, GetBiosResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetBios }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.soap.pastorini.it/", name = "getBios")
    public JAXBElement<GetBios> createGetBios(GetBios value) {
        return new JAXBElement<GetBios>(_GetBios_QNAME, GetBios.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetNumberOfArtists }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.soap.pastorini.it/", name = "getNumberOfArtists")
    public JAXBElement<GetNumberOfArtists> createGetNumberOfArtists(GetNumberOfArtists value) {
        return new JAXBElement<GetNumberOfArtists>(_GetNumberOfArtists_QNAME, GetNumberOfArtists.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetArtists }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.soap.pastorini.it/", name = "getArtists")
    public JAXBElement<GetArtists> createGetArtists(GetArtists value) {
        return new JAXBElement<GetArtists>(_GetArtists_QNAME, GetArtists.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetArtistsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.soap.pastorini.it/", name = "getArtistsResponse")
    public JAXBElement<GetArtistsResponse> createGetArtistsResponse(GetArtistsResponse value) {
        return new JAXBElement<GetArtistsResponse>(_GetArtistsResponse_QNAME, GetArtistsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetNumberOfArtistsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.soap.pastorini.it/", name = "getNumberOfArtistsResponse")
    public JAXBElement<GetNumberOfArtistsResponse> createGetNumberOfArtistsResponse(GetNumberOfArtistsResponse value) {
        return new JAXBElement<GetNumberOfArtistsResponse>(_GetNumberOfArtistsResponse_QNAME, GetNumberOfArtistsResponse.class, null, value);
    }

}
