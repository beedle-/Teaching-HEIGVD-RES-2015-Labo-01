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
 * @author Bastien Rouiller
 */
public class FileNumberingFilterWriter extends FilterWriter {

  private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());

    //index de ligne
    private int indexLine = 1;
    
    //flag indiquant la présence d'un R
    private boolean flagR = false;
  
  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  // Fonction prenant un string en parametre et renvoyant ce même string avec
  // numérotation des lignes
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
   
    //on parcourt la chaine
    for(int i = 0; i < sb.length(); i++)
    {
        if(sb.charAt(i) == '\r')
            if(sb.charAt(i+1)== '\n')  //traitement du cas \r\n
            {
                i = i + 2;
                sb.insert(i, indexLine+"\t");
                indexLine++;
            }
            else    //simple \r
            {   
                i++;
                sb.insert(i, indexLine+"\t");
                indexLine++;
            }
        else if(sb.charAt(i)=='\n')     // simple \n
        {
            i++;
            sb.insert(i, indexLine+ "\t");
            indexLine++;
        }
        
    }
    return sb.toString();
  }
  
  @Override
  public void write(String str, int off, int len) throws IOException {
    out.write(insertLineNumber(str.substring(off, off+len)));
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    String str = new String(cbuf);  
    out.write(insertLineNumber(str.substring(off, off+len)));
  }

  @Override
  public void write(int c) throws IOException {    
   
    //Numérotation de la première ligne
    if(indexLine == 1)
    {
        out.write(indexLine+"\t");
        indexLine++;
    }
        
    //si on rencontre un r, on memorise afin de détecter un \r\n plus tard
    if(c == '\r')
    {
        flagR = true;
        out.write("\r");
    }       
    else if(c == '\n')
    {
        if(flagR)    //  \r suivit d'un \n
        {
            flagR = false;
            out.write(c);
        } 
        else   // \n seul
        {
            out.write(c);    
        }
        out.write(indexLine+"\t");
        indexLine++;
        flagR = false;
    }
    else  //dans le cas d'un caractère standard
    {
        if(flagR)
        {
            out.write(indexLine+"\t");
            indexLine++;
        }
        flagR = false;
        out.write(c);
    }
  }

}
