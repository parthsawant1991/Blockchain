import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Scanner;

public class Hasher {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);

		HashMap<String, Integer> hashfunc = new HashMap<String, Integer>();

		for (int i = 0; i < 5; i++) {

			System.out.println("Enter word");
			String word = sc.next();
			hashfunc.put(word, getHashInt(word));
		}
		
		
		for(String wd : hashfunc.keySet()) {
			System.out.println("Word: "+ wd + " hash: "+hashfunc.get(wd));
		}
		
		
	}

	public static int getHashInt(String s) {

		int hash = -1;
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] encodedhash = digest.digest(s.getBytes(StandardCharsets.UTF_8));
			hash = Math.abs(ByteBuffer.wrap(encodedhash).getInt() % 1000000);
			// System.out.println("Hash: " + hash);
		} catch (Exception e) {
		}
		return hash;

	}

}
