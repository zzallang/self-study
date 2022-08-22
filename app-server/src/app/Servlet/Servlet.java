package app.Servlet;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public interface Servlet {
  String SUCESSS = "success";
  String FAIL = "fail";

  void service(DataInputStream in, DataOutputStream out);
}
