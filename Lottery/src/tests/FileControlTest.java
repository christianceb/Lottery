package tests;

import lottery.FileControl;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class FileControlTest {
  String fileName = "testopenfile.txt";
  String testMessage = "Le quick brown foxx";

  @Test
  void open() {
    File testOpenFile = new File(fileName);

    if ( testOpenFile.isFile() ) {
      testOpenFile.delete();
    }

    try {
      testOpenFile.createNewFile();
    } catch (Exception error) {
      fail( error );
    }

    FileControl fileControl = new FileControl(fileName);
    assertTrue( fileControl.open() );
  }

  @Test
  void create() {
    File testOpenFile = new File(fileName);

    if ( testOpenFile.isFile() ) {
      testOpenFile.delete();
    }

    FileControl fileControl = new FileControl(fileName);

    assertTrue( fileControl.create() );
  }

  @Test
  void writeFirstLine() {
    File testOpenFile = new File(fileName);

    if ( testOpenFile.isFile() ) {
      testOpenFile.delete();
    }

    try {
      testOpenFile.createNewFile();

      FileControl fileControl = new FileControl(fileName);
      fileControl.writeFirstLine(testMessage + "writeFirstLine");
      fileControl.close();

      Scanner scanner = new Scanner(new FileReader(this.fileName));

      assertEquals( scanner.nextLine(), testMessage + "writeFirstLine" );

    } catch (Exception error) {
      fail(error);
    }
  }

  @Test
  void readFirstLine() {
    File testOpenFile = new File(fileName);

    if ( testOpenFile.isFile() ) {
      testOpenFile.delete();
    }

    try {
      testOpenFile.createNewFile();
      PrintWriter fileWriteStream = new PrintWriter(fileName);
      fileWriteStream.println(testMessage + "readFirstLine");
      fileWriteStream.close();

      FileControl fileControl = new FileControl(fileName);
      fileControl.open();

      assertEquals( fileControl.readFirstLine(), testMessage + "readFirstLine" );

    } catch (Exception error) {
      fail(error);
    }
  }
}