package it.univaq.connectpa.publicbillposting.sia.endpoints.manager.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.transform.dom.DOMResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import it.univaq.connectpa.publicbillposting.sia.endpoints.manager.exception.SiaEndpointsManagerException;
import net.sf.saxon.dom.NodeWrapper;

public class Utils {

	private static Logger LOGGER = LoggerFactory.getLogger(Utils.class);

	public static Object unmarshallNodeWrapperToObject(NodeWrapper nodeWrapper, Class<?> classObject){

		Object object = new Object();

		Node node = (Node)nodeWrapper.getUnderlyingNode();
        JAXBContext jc;
        JAXBElement<Object> jaxbElement;
		try {
			jc = JAXBContext.newInstance(classObject);		
			jaxbElement = (JAXBElement<Object>) jc.createUnmarshaller().unmarshal(node, classObject);
			object = jaxbElement.getValue();
		} catch (JAXBException e) {
            LOGGER.error(e.getMessage(), e);
            throw new SiaEndpointsManagerException("Exception into unmarshallNodeWrapperToObject see log file for details");
		}
		return object;
	}

	public static Document createDocumentFromObject(Object object){

	    DOMResult res = new DOMResult();
	    Document doc = null;
	    try {
	    	JAXBContext.newInstance(object.getClass()).createMarshaller().marshal(object, res);
			doc = (Document) res.getNode();
		} catch (JAXBException e) {
            LOGGER.error(e.getMessage(), e);
            throw new SiaEndpointsManagerException("Exception into createDocumentFromObject see log file for details");
		}
	    return doc;
	}
}
