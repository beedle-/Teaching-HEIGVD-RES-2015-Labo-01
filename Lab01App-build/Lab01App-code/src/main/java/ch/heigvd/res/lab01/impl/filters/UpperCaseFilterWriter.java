package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

/**
 *
 * @author Olivier Liechti
 * @author Bastien Rouiller
 */
public class UpperCaseFilterWriter extends FilterWriter {
  
  public UpperCaseFilterWriter(Writer wrappedWriter) {
    super(wrappedWriter);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    str = str.substring(off, off + len).toUpperCase();
    out.write(str);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    String s = new String(cbuf);
    out.write(s.toUpperCase(), off, len);
  }

  @Override
  public void write(int c) throws IOException {
    int cUpper = Character.toUpperCase(c);
    out.write(cUpper);
  }

}
