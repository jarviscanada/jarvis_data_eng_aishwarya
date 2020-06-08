package ca.jrvs.apps.grep;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JavaGrepLambdaImp extends JavaGrepImp {

  public static void main(String[] args) {
    if (args.length !=3) {
      throw new IllegalArgumentException("USAGE: JavaGrep regex rootPath outFile");
    }

    JavaGrepLambdaImp javaGrepLambdaImp = new JavaGrepLambdaImp();
    javaGrepLambdaImp.setRegex(args[0]);
    javaGrepLambdaImp.setRootPath(args[1]);
    javaGrepLambdaImp.setOutFile(args[2]);

    try {
      javaGrepLambdaImp.process();
    } catch (Exception ex) {
      javaGrepLambdaImp.logger.error(ex.getMessage(),ex);
    }
  }

  @Override
  public void process() throws IOException {
    List<String> matchesLines = new ArrayList<>();
    this.listFiles(this.getRootPath()).stream().forEach(file->{this.readLines(file).stream().filter(line->this.containsPattern(line)).forEach(line->matchesLines.add(line));});

    try {
      this.writeToFile(matchesLines);
    } catch (IOException ex) {
      this.logger.error(ex.getMessage(), ex);
    }
  }

  @Override
  public List<File> listFiles(String rootDir) {
    try {
      return Files.walk(Paths.get(rootDir)).filter(Files::isRegularFile).map(Path::toFile).collect(Collectors.toList());
    } catch (IOException ex) {
      this.logger.error(ex.getMessage(), ex);
    }
    return null;
  }

  @Override
  public List<String> readLines(File inputFile) {
    try {
      return Files.lines(Paths.get(inputFile.toString())).collect(Collectors.toList());
    } catch (IOException ex) {
      this.logger.error(ex.getMessage(),ex);
    }
    return null;
  }

  @Override
  public boolean containsPattern(String line) {
    return super.containsPattern(line);
  }

  @Override
  public void writeToFile(List<String> lines) throws IOException {
    Files.write(Paths.get(this.getOutFile()),lines);
  }

  @Override
  public String getRegex() {
    return super.getRegex();
  }

  @Override
  public void setRegex(String regex) {
    super.setRegex(regex);
  }

  @Override
  public String getRootPath() {
    return super.getRootPath();
  }

  @Override
  public void setRootPath(String rootPath) {
    super.setRootPath(rootPath);
  }

  @Override
  public String getOutFile() {
    return super.getOutFile();
  }

  @Override
  public void setOutFile(String outFile) {
    super.setOutFile(outFile);
  }
}