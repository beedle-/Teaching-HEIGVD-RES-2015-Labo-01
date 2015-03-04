package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;
import java.io.File;

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
  public void explore(File rootDirectory, IFileVisitor vistor) {
    throw new UnsupportedOperationException("The student has not implemented this method yet.");
  }

}
