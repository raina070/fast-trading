package es.us.lsi.tdg.fast.core.services;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService()
public class myService {
  private String message = new String("Hello, ");

  //public void Hello() {}

  @WebMethod()
  public String add(String name) {
    return "added.";
  }
} 