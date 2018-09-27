import java.io.*;

class SubstitutionCipher {

  BufferedReader reader;

  public SubstitutionCipher() {
    reader = new BufferedReader(new InputStreamReader(System.in));
  }

  String getMessage() {
    try {
      return reader.readLine();
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  String getROT13(String plainText) {
    StringBuffer cipherText = new StringBuffer();

    for (int i=0; i<plainText.length(); i++) {
      char pChar = plainText.charAt(i);

      // Upper-case alphabet (A-M)
      if ((pChar >= 65) && (pChar <= 77))
        pChar = (char)(pChar + 13);
     
      // Lower-case alphabet (a-m)
      else if ((pChar >= 97) && (pChar <= 109))
        pChar = (char)(pChar + 13);
     
      // Upper-case alphabet (N-Z)
      else if ((pChar >= 78) && (pChar <= 90))
        pChar = (char)(pChar - 13);
     
      // Lower-case alphabet (n-z)
      else if ((pChar >= 110) && (pChar <= 122))
        pChar = (char)(pChar - 13);
     
      // Numbers 0-4
      else if ((pChar >= 48) && (pChar <= 52))
        pChar = (char)(pChar + 5);
     
      // Numbers 5-9
      else if ((pChar >= 53) && (pChar <= 57))
        pChar = (char)(pChar - 5);
     
      cipherText.append("" + pChar);

    }
    return cipherText.toString();
  }

  public static void main(String args[]) {
    SubstitutionCipher sc;
    sc = new SubstitutionCipher();
   
    System.out.print("Enter plain string: ");
    String plainText = sc.getMessage();
   
    // Encode original message
    String encoded = sc.getROT13(plainText);
   
    // Recover original message
    String decoded = sc.getROT13(encoded);
   
    System.out.println("\n---------------------");
    System.out.println("Plain Text: " + plainText);
    System.out.println("Encoded Text: " + encoded);
    System.out.println("Decoded Text: " + decoded);
  }
}