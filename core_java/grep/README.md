# Introduction
The project has implemented a Grep app in Java. The app searches for a text pattern in a given directory recursively, and outputs matched lines to an output file. In this project, Java I/O (FileReader, BufferedReader, OutputStreamWriter, FileOutputStream, and BufferedWriter) was learned to be able to read lines from each file and write the matched lines to the output file. Later, the same implementations were done using Lambda expressions, and Stream APIs to compact the code lines. Using IDE for Grep app implementation, helped in learning to write Javadoc, debugging and java coding style.
# Usage
The app takes three program arguments:
1. `regex` - This is a unique text which describes the search pattern
2. `rootPath` - Root directory path from where the search begins to all files and sub-directories.
3. `outFile` - Output file name where matched lines will be written.
Program USAGE: regex rootPath outFile 
Search `.*IllegalArgumentException.*` pattern from `./grep/src` folder recursively and output the result to `/tmp/grep.out` file.
Program arguments: `.*IllegalArgumentException.* ./grep/src /tmp/grep.out`

# Pseudocode
`process` method pseudocode is as follows:
```
matchedLines = []
for file in listFilesRecursively(rootDir)
  for line in readLines(file)
      if containsPattern(line)
        matchedLines.add(line)
writeToFile(matchedLines)
```

# Performance Issue
The grep app cannot be used for processing huge files, because `readLines` method declared under interface has `List<Strings>` as return type and have used `List<String>` for storing `matchedLines`.  `List<Strings>` stores data in RAM and when files are enormous, it can raise out of Memory issues. For instance, if we have many read lines from a file, then we can get out of memory issues/errors. 

# Improvement
1. By using Streams in place of List for storing `matchedLines` and `readLines`. This would help in reloving out of memory issues and helps in dealing with huge files.
2. For readability and usability purpose, it would be great to not only output `matchedLines` but also details like the location of the data, under which file, directory or subdirectory and perhaps line number.
3. Currently, we are setting program arguments. We can probably take inputs from the user using `Scanner`.