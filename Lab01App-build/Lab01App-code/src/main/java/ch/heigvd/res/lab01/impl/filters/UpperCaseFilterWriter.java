package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

/**
 *
 * @author Olivier Liechti
 */
public class UpperCaseFilterWriter extends FilterWriter {
  
  public UpperCaseFilterWriter(Writer wrappedWriter) {
    super(wrappedWriter);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    str = str.substring(off, off + len).toUpperCase();
    out.write(str);
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    String s = new String(cbuf);
    out.write(s.toUpperCase(), off, len);
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
  }

  @Override
  public void write(int c) throws IOException {
    int cUpper = Character.toUpperCase(c);
    out.write(cUpper);
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
  }

}
