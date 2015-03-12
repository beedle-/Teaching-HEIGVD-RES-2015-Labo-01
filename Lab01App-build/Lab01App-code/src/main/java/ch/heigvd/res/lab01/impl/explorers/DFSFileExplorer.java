package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;
import java.io.File;
import java.util.LinkedList;

/**
 * This implementation of the IFileExplorer interface performs a depth-first
 * exploration of the file system and invokes the visitor for every encountered
 * node (file and directory). When the explorer reaches a directory, it visits all
 * files in the directory and then moves into the subdirectories.
 * 
 * @author Olivier Liechti
 * @author Bastien Rouiller
 */


public class DFSFileExplorer implements IFileExplorer {

  @Override
  public void explore(File rootDirectory, IFileVisitor visitor) {
    //on commence par visiter le fichier/dossier
    visitor.visit(rootDirectory);
    
    //on le parcourt uniquement si il s'agit d'un dossier
    if(rootDirectory.isDirectory())
    {
        File[] maListe;
        maListe = rootDirectory.listFiles();
        
        //on stocke séparemment fichiers et dossiers pour obtenir le bon ordre
        // en résultat
        LinkedList<File> listeFile = new LinkedList<>();
        LinkedList<File> listeDirectory = new LinkedList<>();
        
        for(File noeud : maListe)
        {       
            if(noeud.isDirectory())
                listeDirectory.add(noeud);
            if(noeud.isFile())
                listeFile.add(noeud);
        }
        
        for(File file : listeFile)
            explore(file, visitor);
        
        for(File directory : listeDirectory)
            explore(directory, visitor); 
    }
  }

}
