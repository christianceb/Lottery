package lottery;

import java.util.Arrays;

public class Seven99 {
  private FileControl file;
  private int size = 7;
  private int max = 99;
  private int[] numbers = new int[size];

  /**
   * 7/99 Lottery class that takes 7 numbers to guess with a range of 1-99 each number.
   *
   * @param fileName Filename of file to load containing the 7 winning numbers. Numbers are normally comma-separated
   *                 values. The file is then opened and a stream of the file is instantiated inside the class.
   */
  public Seven99 ( String fileName ) {
    this.file = new FileControl( fileName );

    if ( file.open() == false ) {
      file.create();
    }
  }

  /**
   * Generates the 7 random numbers and stores them into the class. Conveniently returns the 7 winning numbers also.
   *
   * @return The 7 winning numbers.
   */
  public int[] generate() {
    RNG rng = new RNG( max );

    for (int i = 0; i < numbers.length; i++) {
      int generatedNumber = rng.generate();
      boolean unique = true;

      while ( unique ) {
        int search = Arrays.binarySearch( numbers, generatedNumber );

        if ( search >= 0 ) {
          generatedNumber = rng.generate( generatedNumber );
        }
        else {
          numbers[i] = generatedNumber;
          unique = false;
        }
      }
    }

    Arrays.sort( numbers );

    return numbers;
  }

  /**
   * Given the instantiated file stream on the constructor, parses the numbers and checks if it is fit for usage as 7
   * winning numbers. If successful, stores the numbers as the winning number in the class.
   *
   * @return True if the file was parsed successfully and is now the winning numbers in the class. Otherwise, false.
   */
  public boolean parseFile() {
    boolean success = false;

    String line = file.readFirstLine();
    String[] split = line.split(",");

    if ( split.length == size ) {
      for (int i = 0; i < split.length; i++) {
        numbers[i] = Integer.parseInt(split[i]);
      }

      success = true;
    }

    return success;
  }

  /**
   * Saves the current winning numbers in the class into the file set in the constructor.
   *
   * @return True if the numbers were written successfully. Otherwise, false.
   */
  public boolean saveNumbers() {
    String numbersInString[] = new String[size];

    for (int i = 0; i < size; i++) {
      numbersInString[i] = String.valueOf( numbers[i] );
    }

    return file.writeFirstLine( String.join( ",", numbersInString ) );
  }

  /**
   * Returns the 7 winning numbers in the class.
   *
   * @return The 7 winning numbers in array
   */
  public int[] getNumbers() {
    return this.numbers;
  }

  /**
   * Set the winning numbers given a parameter of 7 numbers in an array.
   *
   * @param newNumbers The 7 numbers to be used as winning numbers. Note that we do not check if the numbers are within
   *                   ranges.
   */
  public void setNumbers( int newNumbers[] ) {
    for (int i = 0; i < newNumbers.length ; i++) {
      numbers[i] = newNumbers[i];
    }

    Arrays.sort( numbers );
  }
}