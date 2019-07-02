package lottery;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileControl {
  FileReader fileStream = null;
  Scanner scanner;
  String fileName;
  boolean found = false;

  /**
   * Simple File API that opens, creates, writes and reads a file. Limited to reading and writing first line of a file
   * only.
   *
   * @param fileName The filename of the file to manipulate.
   */
  public FileControl( String fileName ) {
    this.fileName = fileName;
  }

  /**
   * Opens the file set in filename
   *
   * @return True if the file can be opened. False for a very vague error (could be that file does not exist or another)
   */
  public boolean open() {
    try {
      fileStream = new FileReader( this.fileName );
      scanner = new Scanner( fileStream );
      this.found = true;
    } catch ( FileNotFoundException error ) {
      this.found = false;
      System.out.println(error.toString());
    }

    return this.found;
  }

  /**
   * Indiscriminately creates a file based on the filename set.
   *
   * @return True if the file was successfully created. False for a very vague error
   */
  public boolean create() {
    boolean operation = false;
    try {
      File file = new File( fileName );
      file.createNewFile();

      if ( this.open() == false ) {
        throw new Exception("File created but still has issues opening the file");
      } else {
        writeFirstLine("");
      }

      operation = true;
    } catch( Exception error ) {
      System.out.println(error.toString());
      operation = false;
    }

    return operation;
  }

  /**
   * Given a string, writes that string into the first line of the file.
   *
   * @param line The string to write into the first line of the file. Note that this replaces everything in the line.
   * @return True if write was successful. False for a very vague error.
   */
  public boolean writeFirstLine( String line ) {
    PrintWriter fileWriteStream = null;
    boolean operation = false;

    try {
      fileWriteStream = new PrintWriter( this.fileName );
      fileWriteStream.println( line );
      operation = true;
    } catch ( Exception error ) {
      System.out.println(error.toString());
    } finally {
      fileWriteStream.close();
    }

    return operation;
  }

  /**
   * Tries to read the very first line of the file in the stream.
   *
   * @return The line in string if it exists. Else, returns blank.
   */
  public String readFirstLine() {
    if ( scanner.hasNextLine() ) {
      return scanner.nextLine();
    }
    return "";
  }

  /**
   * Safely closes an open file stream of the file in the class. Not in use as the lifetime of the stream is not
   * significantly long. Will probably be used in unit testing.
   *
   * @return True if the stream was closed. Else, false for a very vague error.
   */
  public boolean close() {
    boolean operation = false;
    try {
      fileStream.close();
      operation = true;
    } catch ( Exception error ) {
      System.out.println(error.toString());
    }
    return operation;
  }
}
