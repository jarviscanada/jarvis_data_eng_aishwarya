package ca.jrvs.apps.grep;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JavaGrepImp implements JavaGrep {

  final Logger logger = LoggerFactory.getLogger(JavaGrep.class);

  private String regex;
  private String rootPath;
  private String outFile;

  @Override
  public String getRegex() {
    return regex;
  }

  @Override
  public void setRegex(String regex) {
    this.regex = regex;
  }

  @Override
  public String getRootPath() {
    return rootPath;
  }

  @Override
  public void setRootPath(String rootPath) {
    this.rootPath = rootPath;
  }

  @Override
  public String getOutFile() {
    return outFile;
  }

  @Override
  public void setOutFile(String outFile) {
    this.outFile = outFile;
  }

  @Override
  public void process() throws IOException {
    List<String> matchesLines = new ArrayList<>();
    for (File file : this.listFiles(getRootPath())) {
      for (String line : this.readLines(file)) {
        if (this.containsPattern(line)) {
          matchesLines.add(line);
        }
      }
    }
    try {
      this.writeToFile(matchesLines);
    } catch (IOException ex) {
      logger.error("Invalid input to write", ex);
    }
  }

  @Override
  public List<File> listFiles(String rootDir) {
    File f = new File(rootDir);
    List<File> listFile = new ArrayList<File>();
    File[] files = f.listFiles();
    if (files != null && files.length > 0) {
      for (File file : files) {
        if (file.isDirectory()) {
          for (File subFile : listFiles(file.getAbsolutePath())) {
            listFile.add(subFile);
          }
        } else {
          listFile.add(file);
        }
      }
    }
    return listFile;
  }

  @Override
  public List<String> readLines(File inputFile) {
    FileReader fileReader = null;
    try {
      fileReader = new FileReader(inputFile);
    } catch (FileNotFoundException ex) {
      if (!inputFile.isFile()) {
        throw new IllegalArgumentException("Not a file", ex);
      } else {
        logger.error("Invalid input file", ex);
      }
    }
    BufferedReader bufferedReader = new BufferedReader(fileReader);
    String line = null;
    List<String> readLine = new ArrayList<String>();
    while (true) {
      try {
        if (!((line = bufferedReader.readLine()) != null)) {
          break;
        }
      } catch (IOException ex) {
        logger.error("Invalid input read line", ex);
      }
      readLine.add(line);
    }
    return readLine;
  }

  @Override
  public boolean containsPattern(String line) {
    return Pattern.matches(this.getRegex(), line);
  }

  @Override
  public void writeToFile(List<String> lines) throws IOException {
    File file = new File(this.getOutFile());
    // If doesn't exists then create new file
    if (!file.exists()) {
      file.createNewFile();
    }
    FileOutputStream fout = new FileOutputStream(file);
    OutputStreamWriter out = new OutputStreamWriter(fout);
    BufferedWriter bout = new BufferedWriter(out);
    for (int i = 0; i < lines.size(); i++) {
      bout.write("\t" + lines.get(i).trim() + "\n");
      bout.flush();
    }
    bout.close();
    out.close();
    fout.close();
  }

  /**
   * The main is used to take program arguments and initial the process method.
   * @param args takes the regex, rootPath and outFile
   */
  public static void main(String[] args) {
    if (args.length != 3) {
      throw new IllegalArgumentException("USAGE: JavaGrep regex rootPath outFile");
    }
    BasicConfigurator.configure();
    JavaGrepImp javaGrepImp = new JavaGrepImp();
    javaGrepImp.setRegex(args[0]);
    javaGrepImp.setRootPath(args[1]);
    javaGrepImp.setOutFile(args[2]);

    try {
      javaGrepImp.process();
    } catch (IOException ex) {
      javaGrepImp.logger.error("Invalid input", ex);
    }
  }
}