package es.us.lsi.tdg.fast.core.server.logging;


import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.Binding;
import javax.xml.ws.Endpoint;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import javax.xml.ws.handler.Handler;

/*
 * Log the whole SOAP message
 */
public class EnvelopeLoggingSOAPHandler extends BaseHandler<SOAPMessageContext> implements SOAPHandler<SOAPMessageContext> {
    
    private static PrintStream out = System.out;
	private static final String HANDLER_NAME = "EnvelopeLoggingSOAPHandler";
     
    public EnvelopeLoggingSOAPHandler(){
		super();
		super.setHandlerName(HANDLER_NAME);		
    }
    
    public boolean handleMessage(SOAPMessageContext smc) {
		out.println("------------------------------------");
		out.println("In SOAPHandler " + HandlerName + ":handleMessage()");
		logToSystemOut(smc);
		out.println("Exiting SOAPHandler " + HandlerName + ":handleMessage()");
		out.println("------------------------------------");    	
        return true;
    }

    private void logToSystemOut(SOAPMessageContext smc) {
        Boolean outboundProperty = (Boolean)
            smc.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        
        if (outboundProperty.booleanValue()) {
            out.println("\ndirection = outbound ");
        } else {
            out.println("\ndirection = inbound ");
        }
        
        SOAPMessage message = smc.getMessage();
        try {
        	out.println("");   
        	message.writeTo(out);
            out.println("");   
        } catch (Exception e) {
            out.println("Exception in SOAP Handler: " + e);
        }
    }

	public Set<QName> getHeaders() {
		return null;
	}
	
	public void bind(Endpoint endpoint){
		Binding binding = endpoint.getBinding();
	    List<Handler> handlerChain = new LinkedList<Handler>();
	    handlerChain.add(this);
	    binding.setHandlerChain(handlerChain);
	}
}
