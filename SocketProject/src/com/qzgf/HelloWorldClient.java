
package com.qzgf;

import java.net.MalformedURLException;
import java.util.Collection;
import java.util.HashMap;
import javax.xml.namespace.QName;
import org.codehaus.xfire.XFireRuntimeException;
import org.codehaus.xfire.aegis.AegisBindingProvider;
import org.codehaus.xfire.annotations.AnnotationServiceFactory;
import org.codehaus.xfire.annotations.jsr181.Jsr181WebAnnotations;
import org.codehaus.xfire.client.XFireProxyFactory;
import org.codehaus.xfire.jaxb2.JaxbTypeRegistry;
import org.codehaus.xfire.service.Endpoint;
import org.codehaus.xfire.service.Service;
import org.codehaus.xfire.soap.AbstractSoapBinding;
import org.codehaus.xfire.transport.TransportManager;

public class HelloWorldClient {

    private static XFireProxyFactory proxyFactory = new XFireProxyFactory();
    private HashMap endpoints = new HashMap();
    private Service service0;

    public HelloWorldClient() {
        create0();
        Endpoint HelloWorldPortTypeLocalEndpointEP = service0 .addEndpoint(new QName("http://qzgf.com", "HelloWorldPortTypeLocalEndpoint"), new QName("http://qzgf.com", "HelloWorldPortTypeLocalBinding"), "xfire.local://HelloWorld");
        endpoints.put(new QName("http://qzgf.com", "HelloWorldPortTypeLocalEndpoint"), HelloWorldPortTypeLocalEndpointEP);
        Endpoint HelloWorldHttpPortEP = service0 .addEndpoint(new QName("http://qzgf.com", "HelloWorldHttpPort"), new QName("http://qzgf.com", "HelloWorldHttpBinding"), "http://localhost/TestWebServices/services/HelloWorld");
        endpoints.put(new QName("http://qzgf.com", "HelloWorldHttpPort"), HelloWorldHttpPortEP);
    }

    public Object getEndpoint(Endpoint endpoint) {
        try {
            return proxyFactory.create((endpoint).getBinding(), (endpoint).getUrl());
        } catch (MalformedURLException e) {
            throw new XFireRuntimeException("Invalid URL", e);
        }
    }

    public Object getEndpoint(QName name) {
        Endpoint endpoint = ((Endpoint) endpoints.get((name)));
        if ((endpoint) == null) {
            throw new IllegalStateException("No such endpoint!");
        }
        return getEndpoint((endpoint));
    }

    public Collection getEndpoints() {
        return endpoints.values();
    }

    private void create0() {
        TransportManager tm = (org.codehaus.xfire.XFireFactory.newInstance().getXFire().getTransportManager());
        HashMap props = new HashMap();
        props.put("annotations.allow.interface", true);
        AnnotationServiceFactory asf = new AnnotationServiceFactory(new Jsr181WebAnnotations(), tm, new AegisBindingProvider(new JaxbTypeRegistry()));
        asf.setBindingCreationEnabled(false);
        service0 = asf.create((com.qzgf.HelloWorldPortType.class), props);
        {
            AbstractSoapBinding soapBinding = asf.createSoap11Binding(service0, new QName("http://qzgf.com", "HelloWorldHttpBinding"), "http://schemas.xmlsoap.org/soap/http");
        }
        {
            AbstractSoapBinding soapBinding = asf.createSoap11Binding(service0, new QName("http://qzgf.com", "HelloWorldPortTypeLocalBinding"), "urn:xfire:transport:local");
        }
    }

    public HelloWorldPortType getHelloWorldPortTypeLocalEndpoint() {
        return ((HelloWorldPortType)(this).getEndpoint(new QName("http://qzgf.com", "HelloWorldPortTypeLocalEndpoint")));
    }

    public HelloWorldPortType getHelloWorldPortTypeLocalEndpoint(String url) {
        HelloWorldPortType var = getHelloWorldPortTypeLocalEndpoint();
        org.codehaus.xfire.client.Client.getInstance(var).setUrl(url);
        return var;
    }

    public HelloWorldPortType getHelloWorldHttpPort() {
        return ((HelloWorldPortType)(this).getEndpoint(new QName("http://qzgf.com", "HelloWorldHttpPort")));
    }

    public HelloWorldPortType getHelloWorldHttpPort(String url) {
        HelloWorldPortType var = getHelloWorldHttpPort();
        org.codehaus.xfire.client.Client.getInstance(var).setUrl(url);
        return var;
    }

    public static void main(String[] args) {
        

        HelloWorldClient client = new HelloWorldClient();
        
		//create a default service endpoint
        HelloWorldPortType service = client.getHelloWorldHttpPort();
        System.out.println(service.getDateSet(""));
		//TODO: Add custom client code here
        		//
        		//service.yourServiceOperationHere();
        
		System.out.println("test client completed");
        		System.exit(0);
    }

}
