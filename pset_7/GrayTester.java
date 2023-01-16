/**
 * @author wolf.meuller@informatik.hu-berlin.de
 * test class for your implementation of the Gray codes
 * Your class Gray has to be in the same directory or 
 * in the CLASSPATH
 * good luck :-)
 */
public class GrayTester {

	static int points = 0;

	/**
	 * Gives the bit representation on an Integer.
	 * 
	 * @param Interger
	 *            i
	 * @return String of bits
	 */
	public static String bits(int i) {
		String s = String.format("%32s", Integer.toBinaryString(i));
		return s.replace(' ', '0');
	}

	public static void feedback(int nr, String check, Boolean status,
			String details) {
		System.out.println("Test " + nr);
		System.out.println("======");
		System.out.printf("%-60s [%5b]\n%s\n", check, status, details);
	}

	public static void test_1() {
		String details = "";
		boolean ok = Gray.toGray(0) == 0;
		if (ok) {
			points++;
		} else {
			details = bits(Gray.toGray(0)) + " is not 0\n";
		}
		feedback(1, "Gray.toGray(0)==0", ok, details);
	}

	public static void test_2() {
		String details = "";
		boolean ok = Gray.fromGray(0) == 0;
		if (ok) {
			points++;
		} else {
			details = bits(Gray.fromGray(0)) + " is not 0\n";
		}
		feedback(2, "Gray.fromGray(0)==0", ok, details);
	}

	public static void test_3() {
		String details = "";
		// choose a random sample point
		int n = (int) (Math.random() * (Integer.MAX_VALUE - 1));
		int xor = Gray.toGray(n) ^ Gray.toGray(n + 1);
		// Hamming distance = 1?
		details += "n  : " + bits(Gray.toGray(n)) + "\n";
		details += "n+1: " + bits(Gray.toGray(n + 1)) + "\n";
		details += "xor: " + bits(xor) + "\n";
		boolean ok = (xor & (xor - 1)) == 0;
		if (ok) {
			points += 2;
		} else {
			details += "bit count of " + Integer.bitCount(xor)
					+ " is different from 1\n";
		}
		feedback(3, "Gray.toGray(" + n + ")^Gray.toGray(" + (n + 1) + ")", ok,
				details);
	}

	public static void test_4() {
		String details = "";
		// choose a random sample point
		int n = (int) (Math.random() * (Integer.MAX_VALUE - 1));
		boolean ok = Gray.fromGray(Gray.toGray(n)) == n;
		if (ok) {
			points += 2;
		} else {
			details += Gray.fromGray(Gray.toGray(n)) + " is different from "
					+ n + "\n";
		}
		feedback(4, "Gray.fromGray(Gray.toGray(" + n + ")", ok, details);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//Testen
		test_1();
		test_2();
		test_3();
		test_4();
		System.out.println("=============================");
		System.out.println("Estimated Result: " + points + "/6 points!");
		System.out.println("=============================");
	}

}