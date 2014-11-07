package de.extra.extraClientLight.webservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

import de.drv.dsrv.spoc.extra.v1_3.jaxb.request.TransportRequestType;
import de.drv.dsrv.spoc.extra.v1_3.jaxb.response.TransportResponseType;

/**
 * This class was generated by Apache CXF 3.0.1 2014-10-16T08:59:46.208+02:00
 * Generated source version: 3.0.1
 * 
 */
@WebService(targetNamespace = "http://www.extra-standard.de/namespace/webservice", name = "extra")
@XmlSeeAlso({ de.drv.dsrv.spoc.extra.v1_3.jaxb.plugins.ObjectFactory.class,
		org.w3._2000._09.xmldsig.ObjectFactory.class,
		de.drv.dsrv.spoc.extra.v1_3.jaxb.service.ObjectFactory.class,
		de.drv.dsrv.spoc.extra.v1_3.jaxb.response.ObjectFactory.class,
		de.drv.dsrv.spoc.extra.v1_3.jaxb.components.ObjectFactory.class,
		org.w3._2001._04.xmlenc.ObjectFactory.class,
		de.drv.dsrv.spoc.extra.v1_3.jaxb.request.ObjectFactory.class,
		de.drv.dsrv.spoc.extra.v1_3.jaxb.logging.ObjectFactory.class, 
		de.drv.dsrv.spoc.extra.v1_3.jaxb.messages.ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface Extra {

	@WebResult(name = "Transport", targetNamespace = "http://www.extra-standard.de/namespace/response/1", partName = "response")
	@WebMethod(action = "http://www.extra-standard.de/namespace/webservice/execute")
	public TransportResponseType execute(
			@WebParam(partName = "request", name = "Transport", targetNamespace = "http://www.extra-standard.de/namespace/request/1") TransportRequestType request)
			throws ExtraFault;
}
