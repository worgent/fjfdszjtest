
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

public class GetListDatebyBytesClient {

    private static XFireProxyFactory proxyFactory = new XFireProxyFactory();
    private HashMap endpoints = new HashMap();
    private Service service0;

    public GetListDatebyBytesClient() {
        create0();
        Endpoint GetListDatebyBytesHttpPortEP = service0 .addEndpoint(new QName("http://service.gpsdata.application.qzgf.com", "GetListDatebyBytesHttpPort"), new QName("http://service.gpsdata.application.qzgf.com", "GetListDatebyBytesHttpBinding"), "http://localhost:8080/TestWebServices/services/GetListDatebyBytes");
        endpoints.put(new QName("http://service.gpsdata.application.qzgf.com", "GetListDatebyBytesHttpPort"), GetListDatebyBytesHttpPortEP);
        Endpoint GetListDatebyBytesPortTypeLocalEndpointEP = service0 .addEndpoint(new QName("http://service.gpsdata.application.qzgf.com", "GetListDatebyBytesPortTypeLocalEndpoint"), new QName("http://service.gpsdata.application.qzgf.com", "GetListDatebyBytesPortTypeLocalBinding"), "xfire.local://GetListDatebyBytes");
        endpoints.put(new QName("http://service.gpsdata.application.qzgf.com", "GetListDatebyBytesPortTypeLocalEndpoint"), GetListDatebyBytesPortTypeLocalEndpointEP);
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
        service0 = asf.create((com.qzgf.GetListDatebyBytesPortType.class), props);
        {
            AbstractSoapBinding soapBinding = asf.createSoap11Binding(service0, new QName("http://service.gpsdata.application.qzgf.com", "GetListDatebyBytesHttpBinding"), "http://schemas.xmlsoap.org/soap/http");
        }
        {
            AbstractSoapBinding soapBinding = asf.createSoap11Binding(service0, new QName("http://service.gpsdata.application.qzgf.com", "GetListDatebyBytesPortTypeLocalBinding"), "urn:xfire:transport:local");
        }
    }

    public GetListDatebyBytesPortType getGetListDatebyBytesHttpPort() {
        return ((GetListDatebyBytesPortType)(this).getEndpoint(new QName("http://service.gpsdata.application.qzgf.com", "GetListDatebyBytesHttpPort")));
    }

    public GetListDatebyBytesPortType getGetListDatebyBytesHttpPort(String url) {
        GetListDatebyBytesPortType var = getGetListDatebyBytesHttpPort();
        org.codehaus.xfire.client.Client.getInstance(var).setUrl(url);
        return var;
    }

    public GetListDatebyBytesPortType getGetListDatebyBytesPortTypeLocalEndpoint() {
        return ((GetListDatebyBytesPortType)(this).getEndpoint(new QName("http://service.gpsdata.application.qzgf.com", "GetListDatebyBytesPortTypeLocalEndpoint")));
    }

    public GetListDatebyBytesPortType getGetListDatebyBytesPortTypeLocalEndpoint(String url) {
        GetListDatebyBytesPortType var = getGetListDatebyBytesPortTypeLocalEndpoint();
        org.codehaus.xfire.client.Client.getInstance(var).setUrl(url);
        return var;
    }

    public static void main(String[] args) {
        

        GetListDatebyBytesClient client = new GetListDatebyBytesClient();
        
		//create a default service endpoint
        GetListDatebyBytesPortType service = client.getGetListDatebyBytesHttpPort();
        
		//TODO: Add custom client code here
        		//
        		//service.yourServiceOperationHere();
        
		System.out.println("test client completed");
        		System.exit(0);
    }

}
