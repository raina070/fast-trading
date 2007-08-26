package es.us.lsi.tdg.fast.core.services;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;

import es.us.lsi.tdg.fast.FAST;
import es.us.lsi.tdg.fast.domains.fom.components.fominformation.services.InformantService;

public class ServiceInvoker {

	
	
	public static Object getService(URL url,QName qname,Class serviceClass){
			
			Object service=null;
						
			int errCount = 1;
			
			while((errCount<10) && (service==null)){
				FAST.shell.showMessage("Accessing endpoint <"+url+">(attempt number "+errCount+")");				
				try{
	
					
					Class parameterTypes[] = new Class[2];
					parameterTypes[0] = URL.class;
					parameterTypes[1] = QName.class;
					
					Constructor serviceConstructor = serviceClass.getConstructor(parameterTypes);
					
					Object initargs[] = new Object[2];
					initargs[0] = url;
					initargs[1] = qname;
					service = serviceConstructor.newInstance(initargs);
					
				
				}catch(RuntimeException e){
					FAST.shell.showMessage("Endpoint not ready");				
					errCount++;
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
			return service;
			
		}
}
