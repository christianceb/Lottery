package lottery;

import java.util.Random;

public final class RNG {
    private Random random = new Random();
    private int range;

    /**
     * A random number generator class that uses the system clock as seed to significantly decrease repeat number being
     * generated.
     *
     * @param range When a number is being generated, only returns a number between 1 and this range.
     */
    public RNG( int range ) {
        this.range = range;
        timeAsSeed();
    }
    
    private void timeAsSeed() {
        random.setSeed( System.currentTimeMillis() );
    }

    /**
     * Generates a random number between 1 and the range set in the class.
     *
     * @return A random number between 1 and the range.
     */
    public int generate() {
        int number = random.nextInt( range );

        if ( number == 0 ) {
            number = generate();
        }

        return number;
    }

    /**
     * Generates a random number between 1 and the range. If parameter is set, reruns the function recursively until it
     * gets a number not equal to the parameter.
     *
     * @param previousNumber The random number not to return
     * @return A random number between 1 and the range but not previousNumber.
     */
    public int generate( int previousNumber ) {
        int number = random.nextInt( range );
        
        if ( number == previousNumber || number == 0) {
            timeAsSeed(); // Reset seed
            number = generate( number );
        }
        
        return number;
    }
}
