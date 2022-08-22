package app.Servlet;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public interface Servlet {
  void service(DataInputStream in, DataOutputStream out);
}
