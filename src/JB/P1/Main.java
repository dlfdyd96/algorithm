package JB.P1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String input = br.readLine();
    
    StringTokenizer st = new StringTokenizer(input);
    int l = Integer.parseInt(st.nextToken());
    int x = Integer.parseInt(st.nextToken());

    Encrypter encrypter = new Encrypter(l, x);

    String sentence = br.readLine();

    String answer = encrypter.encryptSentence(sentence);

    System.out.println(answer);
  }
}

class Encrypter {
  private static final int ALPHABET_SIZE = 'z' - 'a' + 1;

  private int l;
  private int x;

  public Encrypter() {
  }

  public Encrypter(int l, int x) {
    this.l = l;
    this.x = x;
  }

  public String encryptSentence(String sentence) {
    StringBuilder ans = new StringBuilder();
    for (int i = 0; i < l; i++) {
      ans.append(
          encryptCharacter(sentence.charAt(i), i)
      );
    }
    return ans.toString();
  }

  public char encryptCharacter(char targetChar, int index) {
    BigInteger target = new BigInteger(targetChar - 'a' + "");
    BigInteger bigX = new BigInteger(x + "");
    BigInteger bigAlphabetSize = new BigInteger(ALPHABET_SIZE + "");

    String result = bigX.pow(index + 1).add(target).mod(bigAlphabetSize).toString();

    return (char) ('a' + Integer.parseInt(result));
  }
}