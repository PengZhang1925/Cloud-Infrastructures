package Model;

import java.math.*;
import java.sql.SQLException;
import java.util.*;

public class Pailliar {
	private static BigInteger p;
	private static BigInteger q;
	private static BigInteger N;
	private static BigInteger N2;
	private static BigInteger lambda;
	private static BigInteger g;
	static Admin admin = new Admin();
	
	private static BigInteger LCM(BigInteger p, BigInteger q) {
		BigInteger gcd, mul;
		mul = p.multiply(q);
		gcd = p.gcd(q);
		return mul.divide(gcd);
	}
	
	// …˙≥…√‹‘ø
	public void kenGen() {
		Random rnd = new Random();
		p = BigInteger.probablePrime(512, rnd);
		rnd = new Random();
		q = BigInteger.probablePrime(512, rnd);
		N = p.multiply(q);
		N2 = N.multiply(N);
		
		// √‹‘ø
		lambda = LCM(p.subtract(BigInteger.ONE), q.subtract(BigInteger.ONE));
		
		rnd = new Random();
		BigInteger L;
		do {
			g = new BigInteger(160, rnd).mod(N2);
			L = g.modPow(lambda, N2).subtract(BigInteger.ONE).divide(N);
		} while (!L.gcd(N).equals(BigInteger.ONE));
		
		try {
			admin.SaveKeys(N.toString(), g.toString(), N2.toString(), lambda.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
