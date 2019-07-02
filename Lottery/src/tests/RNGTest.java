package tests;

import lottery.RNG;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RNGTest {

  public RNGTest() {
  }

  @Test
  public void randomButThisNumber() {
    RNG rng = new RNG( 77 );

    for ( int i = 0; i <= 1000; i++ ) {
      assertNotEquals( 36, rng.generate( 36 ) );
    }
  }

  @Test
  public void randomAndThisNumber() {
    RNG rng = new RNG( 77 );
    int random;

    for ( int i = 0; i <= 1000; i++ ) {
      random = rng.generate();
      if ( random == 36 ) {
        assertEquals( 36, random );
      }
    }
  }

  @Test
  public void noGreaterThan() {
    int range = 77;
    RNG rng = new RNG( range );

    for ( int i = 0; i <= 1000; i++ ) {
      assertFalse( rng.generate() > range );
    }
  }
}