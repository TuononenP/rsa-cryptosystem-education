import java.math.BigInteger;
import java.util.Random;

/**
 * Randomly selects numbers of defined size until it finds a prime one.
 * This code rapidly generates large primes for use in cryptography. 
 */
public class Miller_Rabin_primality_test {

	private static final Random rnd = new Random();
	
//	<<Miller-Rabin pass>>=
	private static boolean miller_rabin_pass(BigInteger a, BigInteger n) {
		BigInteger n_minus_one = n.subtract(BigInteger.ONE);
//		compute s and d
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
		
//	<<Miller-Rabin>>=
	public static boolean miller_rabin(BigInteger n) {
	    for (int repeat = 0; repeat < 20; repeat++) {
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
	
    public static void main(String[] args) {
        if (args[0].equals("test")) {
            BigInteger n = new BigInteger(args[1]);
            System.out.println(miller_rabin(n) ? "PRIME" : "COMPOSITE");
        } else if (args[0].equals("genprime")) {
            int nbits = Integer.parseInt(args[1]);
            BigInteger p;
            do {
                p = new BigInteger(nbits, rnd);
//                test for small factors
            } while (!miller_rabin(p));
            System.out.println(p);
        }
    }
    
//    <<test for small factors>>=
//    	if (p.mod(BigInteger.valueOf(2)).equals(BigInteger.ZERO)) continue;
//    	if (p.mod(BigInteger.valueOf(3)).equals(BigInteger.ZERO)) continue;
//    	if (p.mod(BigInteger.valueOf(5)).equals(BigInteger.ZERO)) continue;
//    	if (p.mod(BigInteger.valueOf(7)).equals(BigInteger.ZERO)) continue;

//    $ java MillerRabin test 516119616549881
//    PRIME
//    $ java MillerRabin test 516119616549887
//    COMPOSITE
//    $ java MillerRabin genprime 128
//    277003545840181465186202237665148044033
//    $ java MillerRabin genprime 512
//    69070670148391210520342329574373473828883687815325200193110546590937028225649711\
//    62269141121965617545613897865904269544243721348093953041840506282278098321
}
