/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.pastorini.rest.server;

import it.pastorini.rest.server.resources.ArtistRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.core.MediaType;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;
import org.apache.cxf.jaxrs.provider.JAXBElementProvider;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

public class Server {
    
    public static void main(String args[]) throws Exception {
        JAXRSServerFactoryBean factoryBean = new JAXRSServerFactoryBean();
        factoryBean.setResourceClasses(ArtistRepository.class);
        factoryBean.setResourceProvider(new SingletonResourceProvider(new ArtistRepository()));
        factoryBean.setAddress("http://localhost:8081/");
        
        Map<Object, Object> extensionMappings = new HashMap<Object, Object>();
        extensionMappings.put("xml", MediaType.APPLICATION_XML);
        extensionMappings.put("json", MediaType.APPLICATION_JSON);
        factoryBean.setExtensionMappings(extensionMappings);
 
        List<Object> providers = new ArrayList<>();
        providers.add(new JAXBElementProvider());
        providers.add(new JacksonJsonProvider());
        factoryBean.setProviders(providers);
        
        org.apache.cxf.endpoint.Server server = factoryBean.create();
        
        System.out.println("Press a key in order to stop...");
        // Waits for input
        System.in.read();
 
        // Destroys the server
        server.destroy();
        System.exit(0);
    }
}
