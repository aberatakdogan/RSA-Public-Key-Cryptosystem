
import java.math.BigInteger;
import java.util.Scanner;


public class RSA {

	public static BigInteger p;
	
	public static BigInteger q;

	public static  BigInteger n;

	

	public static BigInteger e;

	public static BigInteger d;

	public static BigInteger phi;



	public RSA(BigInteger e, BigInteger d, BigInteger n) {

		this.e = e;
		this.d = d;
		this.n = n;

	}



	public static BigInteger convertInt(String plaintext) {

		String message = "";
		for(int i=0; i<plaintext.length(); i++) {
			int ch = (int) plaintext.charAt(i);
			message = message + ch;
			
		}
		BigInteger ciphered = new BigInteger(String.valueOf(message));

		return ciphered;

	}


	public static BigInteger nValue(BigInteger p, BigInteger q) {
		return p.multiply(q);

	}
	
	public static BigInteger phiValue(BigInteger p, BigInteger q) {
		return p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

	}
	
	public static void calculateKeys()
	{
		
		for(e= BigInteger.valueOf(2); e.compareTo(phi)<0; e=e.add(BigInteger.ONE))
		{
			BigInteger bigVal= e.gcd(phi);

			if (bigVal.equals(BigInteger.valueOf(1))) 
			{
				System.out.println("Public Key (e,n): ("+e+","+n+")");
				d = e.modInverse(phi);
				System.out.println("Private Key (d,n): ("+d+","+n+")");
				System.out.println();
			}


		}
		
		
	}


	public static void main (String[] args)  {



		Scanner sc = new Scanner(System.in);

		System.out.print("Enter initial prime number:");
		p = sc.nextBigInteger();

		
		while(!p.isProbablePrime(1)) {
			System.out.println("This is not a prime number.");
			System.out.print("Enter initial prime number: ");
			p = sc.nextBigInteger();

		}

		System.out.print("Enter second prime number:");
		q = sc.nextBigInteger();
		while(!q.isProbablePrime(1)) {
			System.out.println("This is not a prime number.");
			System.out.print("Enter second prime number: ");
			q = sc.nextBigInteger();
		}

		if(p.equals(q))
		{
			System.out.println("Prime numbers should be different. Please re-run the program.");
			System.exit(0);
		}

		n= nValue(p,q);

		phi = phiValue(p,q);

        System.out.println();
        
        System.out.println("List Of the Public and Private Keys");
        calculateKeys();

		Scanner in=new Scanner(System.in);
		
		System.out.print("Enter the e value from list: ");
		BigInteger newE = in.nextBigInteger();

		System.out.print("Enter the d value from list: ");
		BigInteger newD = in.nextBigInteger();


		Scanner s = new Scanner(System.in);
		String plaintext;
		System.out.print("Enter the plain text: ");

		plaintext=s.nextLine();
		BigInteger message = convertInt(plaintext); //cipher message ascii


		BigInteger encrypted = message.modPow(newE, n);
		BigInteger decrypted = encrypted.modPow(newD, n);


		System.out.println();
		System.out.println("Plaintext message: " + plaintext);
		System.out.println("Mathematical representation of message: " + message);
		System.out.println("Encrypted value: " + encrypted);
		System.out.println("Decrypted value: " + decrypted);
		System.out.println("Message after decryption: "+plaintext);


	}
	
	




}