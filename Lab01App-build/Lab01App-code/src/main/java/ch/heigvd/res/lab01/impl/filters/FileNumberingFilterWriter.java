package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Logger;

/**
 * This class transforms the streams of character sent to the decorated writer.
 * When filter encounters a line separator, it sends it to the decorated writer.
 * It then sends the line number and a tab character, before resuming the write
 * process.
 *
 * Hello\n\World -> 1\Hello\n2\tWorld
 *
 * @author Olivier Liechti
 */
public class FileNumberingFilterWriter extends FilterWriter {

  private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());

    //index de ligne
    private int indexLine = 1;
  
  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  private String insertLineNumber(String str)
  {
    //Objet permettant la modification dynamique de chaine  
    StringBuilder sb = new StringBuilder(str);
          
    //position de /n dans la chaîne
    int positionNewLine = 0;
    
    //insertion du premier numéro de ligne
    if(indexLine == 1)
    {
        sb.insert(positionNewLine, indexLine+"\t");
        indexLine++;
    }
    
    
    while((positionNewLine = sb.indexOf("\n", positionNewLine+1))!= -1)
    {
        sb.insert(positionNewLine+1, indexLine+"\t");
        indexLine++;
    }
    return sb.toString();
  }
  
  @Override
  public void write(String str, int off, int len) throws IOException {
   
    out.write(insertLineNumber(str.substring(off, off+len)));
   // out.write(insertLineNumber(str), off, len);
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    String str = new String(cbuf);  
    out.write(insertLineNumber(str.substring(off, off+len)));
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
  }

  @Override
  public void write(int c) throws IOException {    
    
    if(indexLine == 1)
    {
        out.write(indexLine+"\t");
        indexLine++;
    }
    
    out.write(c);
    
    if(c == '\n')
    {
        out.write(indexLine+"\t");
        indexLine++;
    }
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
  }

}
