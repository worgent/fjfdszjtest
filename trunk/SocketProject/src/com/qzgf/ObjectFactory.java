
package com.qzgf;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.qzgf package. 
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


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.qzgf
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetDateSet }
     * 
     */
    public GetDateSet createGetDateSet() {
        return new GetDateSet();
    }

    /**
     * Create an instance of {@link ExampleResponse }
     * 
     */
    public ExampleResponse createExampleResponse() {
        return new ExampleResponse();
    }

    /**
     * Create an instance of {@link GetDateSetResponse }
     * 
     */
    public GetDateSetResponse createGetDateSetResponse() {
        return new GetDateSetResponse();
    }

    /**
     * Create an instance of {@link Example }
     * 
     */
    public Example createExample() {
        return new Example();
    }

}
