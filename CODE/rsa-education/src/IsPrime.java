/**
 * Tests if int is prime.
 * 
 * @author Petri Tuononen
 * @since 22.1.2009
 */
public class IsPrime {

	public IsPrime() {
	}
	
	public boolean isPrime(int x) {
		if (x <= 0 || x == 1 || (x % 2 == 0 && x != 2)) return false;
		for (int i = 3; i < x; i += 2) {
			if (x % i == 0) return false;
		}
		return true;
	}
	
//	public static void main(String[] args) {
//		int x = 10;
//		IsPrime prime = new IsPrime();
//		boolean onko = prime.isPrime(x);
//		if (onko){
//			System.out.println(+x+ " IS a prime.");
//		}
//		else {
//			System.out.println(+x+ " is NOT a prime.");
//		}
//	}
}
