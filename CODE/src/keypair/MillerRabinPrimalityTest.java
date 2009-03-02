package keypair;
/* Copyright (c) 2009 the authors listed at the following URL, and/or
the authors of referenced articles or incorporated external code:
http://en.literateprograms.org/Miller-Rabin_primality_test_(Java)?action=history&offset=20080201093914

Permission is hereby granted, free of charge, to any person obtaining
a copy of this software and associated documentation files (the
"Software"), to deal in the Software without restriction, including
without limitation the rights to use, copy, modify, merge, publish,
distribute, sublicense, and/or sell copies of the Software, and to
permit persons to whom the Software is furnished to do so, subject to
the following conditions:

The above copyright notice and this permission notice shall be
included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

Retrieved from: http://en.literateprograms.org/Miller-Rabin_primality_test_(Java)?oldid=12469
*/

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Scanner;

/**
 * Randomly selects numbers of defined size until a prime is found.
 * Rapidly generates large primes for use in cryptography. 
 * 
 * Modified by Petri Tuononen
 * 22.1.2009
 */
public class MillerRabinPrimalityTest {
	
	//Creates secure random numbers
    private static final SecureRandom rnd = new SecureRandom();

    /**
     * Miller-Rabin. 
     * 
     * @param a		Randomly generated coprime.
     * @param n		Prime.
     * @return		Boolean
     */
    private static boolean miller_rabin_pass(BigInteger a, BigInteger n) {
        BigInteger n_minus_one = n.subtract(BigInteger.ONE);
        BigInteger d = n_minus_one;
        int s = d.getLowestSetBit();
        d = d.shiftRight(s);
        BigInteger a_to_power = a.modPow(d, n);
        if (a_to_power.equals(BigInteger.ONE)) return true;
        for (int i = 0; i < s-1; i++) {
            if (a_to_power.equals(n_minus_one)) return true;
            a_to_power = a_to_power.multiply(a_to_power).mod(n);
        }
        if (a_to_power.equals(n_minus_one)) return true;
        return false;
    }


    /**
     * Tests primality.
     * A highly accurate probabilistic implementation.
     * Repeating the test for n random choices of a gives
     * a probability of 1 - 1 / 4power(n) that the number is prime.
     * 
     * @param n		BigInteger tested for primality
     * @return		Boolean
     */
    public static boolean miller_rabin(BigInteger n) {
        for (int repeat = 0; repeat < 10; repeat++) {
            BigInteger a;
            do {
                a = new BigInteger(n.bitLength(), rnd);
            } while (a.equals(BigInteger.ZERO));
            if (!miller_rabin_pass(a, n)) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Returns true if x is prime.
     * 
     * @param x		BigInteger tested for primality
     * @return		Boolean
     */
    public boolean isPrime(BigInteger x) {
    	if (x.intValue() == 1) {
    		return false;
    	}
    	//check 1000 first primes
		if (new TestPrimality().isFoundFromTable(x)) {
			return true;
		}
		//check using miller rabin algorithm
		else {
			return miller_rabin(x);
		}
    }
    
    /**
     * Generates a prime of wanted bitsize.
     * 
     * @param bitsize	The wanted bitsize of prime. 
     * @return			Generated prime.
     */
    public BigInteger genPrime(int bitsize) {
    	BigInteger p;
        do {
            p = new BigInteger(bitsize, rnd);
            //test for small factors
            if (p.mod(BigInteger.valueOf(2)).equals(BigInteger.ZERO)) continue;
            if (p.mod(BigInteger.valueOf(3)).equals(BigInteger.ZERO)) continue;
            if (p.mod(BigInteger.valueOf(5)).equals(BigInteger.ZERO)) continue;
            if (p.mod(BigInteger.valueOf(7)).equals(BigInteger.ZERO)) continue;
        } while (!miller_rabin(p));
        return p;
    }

    /**
     * Tests class.
     * Contains a primality test and prime generation.
     * 
     * @param args
     */
    public static void main(String[] args) {
    	MillerRabinPrimalityTest primeTest = new MillerRabinPrimalityTest();
    	Scanner sc = new Scanner(System.in);
    	System.out.println("Type a large integer to test if it's prime: ");
    	BigInteger x = sc.nextBigInteger();
    	System.out.println(primeTest.isPrime(x) ? "Prime" : "Composite");
    	System.out.println("Type number of digits you want the generated prime to be: ");
    	int bitsize = sc.nextInt();
    	System.out.println("The test may take some time depending on the computer hardware used.");
    	long startTime = System.currentTimeMillis();
        System.out.println(primeTest.genPrime(bitsize));
        long timeElapsed = System.currentTimeMillis()-startTime;
        System.out.println("Prime generated in " + timeElapsed + " ms.");
    }
}

