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
 */

//traverser le système de fichier en dfs
//obtenir la liste des enfants
//descendre recursivement dans le système de fichier
//visite(noeud recontré)
//v.visite(first dossier), v.visite(dossier.sousdossier)
//visite traite un noeud )(que ce soit un dossier ou un fichier)
public class DFSFileExplorer implements IFileExplorer {

  @Override
  public void explore(File rootDirectory, IFileVisitor visitor) {
    visitor.visit(rootDirectory);
    if(rootDirectory.isDirectory())
    {
        File[] maListe;
        maListe = rootDirectory.listFiles();
        
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
