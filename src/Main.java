// JAN DARGE

/*
*
*   FUNCTIONALITY:
*       -
*
*   EXAMPLE:
*       -
*       1.
*
* */

import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static final Scanner scanner = new Scanner(System.in);
    public static final HashMap<Character, String> hexTable = HexTable();

    public static void main(String[] args) {

        String hex_input;
        String hex_key;
        String string_shift;

        while (true) {
            System.out.print("Enter a HEXADECIMAL KEY value   : ");
            hex_key = scanner.nextLine().toUpperCase();
            System.out.print("Enter a HEXADECIMAL INPUT value : ");
            hex_input = scanner.nextLine().toUpperCase();
            System.out.println("Enter a SHIFT COUNT value     : ");
            string_shift = scanner.nextLine();

            if (hex_key.length() > hex_input.length()) {
                System.out.println("\nKey cannot be greater than the hexadecimal value, please try again.\n");
                continue;
            }

            if (Valid(hex_input) && Valid(hex_key)) {

                int shift;

                try{
                    shift = Integer.parseInt(string_shift);
                } catch (Exception e) {
                    System.out.println("\nSHIFT amount must be an integer, please try again.\n");
                    continue;
                }

                String bin_key = HexToBin(hex_key);
                String bin_input = HexToBin(hex_input);
                String bin_unary_input = FlipBits(bin_input);

                if (bin_key.length() < bin_input.length()) {
                    bin_key = FixKey(bin_key, bin_input.length());
                }

                String xor_ = BinaryStringXOR(bin_key, bin_unary_input);
                String shifted = ShiftBits(xor_, shift);

                System.out.println("Binary KEY    :\t" + bin_key);
                System.out.println("Binary INPUT  :\t" + bin_input);
                System.out.println("Unary Binary  :\t" + bin_unary_input);
                System.out.println("KEY XOR UNARY :\t" + xor_);
                System.out.println("FINAL RESULT  :\t" + shifted);

            } else {
                System.out.println("\nInvalid Input, please try again.\n");
            }
        }
    }

    private static boolean Valid(String input) { // CALLED IN "main"

        // ENSURES THAT THE USER INPUT CAN BE MANIPULATED

        for (int i = 0; i < input.length(); i++) {
            char current = input.charAt(i);
            if (!Character.isDigit(current)) {
                if (!ValidHexLetter(current)) {
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean ValidHexLetter(char input) { // CALLED IN "Valid"

        // CHECKS IF AN INPUT HAS A VALID HEX LETTER ASSOCIATED WITH IT

        return input == 'A' || input == 'B' || input == 'C' || input == 'D' || input == 'E' || input == 'F';
    }

    private static String HexToBin(String input) {

        // CONVERTS A HEX VALUE (STRING) TO A BIN VALUE (STRING)

        char current;
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            current = input.charAt(i);

            if (hexTable.containsKey(current)) {
                result.append(hexTable.get((current)));
            }
        }

        return result.toString();
    }

    private static String FlipBits(String input) { // CALLED IN "main"

        // REPLACES ALL 0's WITH 1's, AND VISE VERSA

        return input.replace("1", "2").replace("0", "1").replace("2", "0");
    }

    private static String FixKey(String bin_key, int n) { //CALLED IN "main"

        // IF THE INPUT KEY IS TOO SHORT, XOR'ing THE 'bin_key' AND 'bin_unary_input' WILL BE IMPOSSIBLE
        // REPEATS 'bin_key' VALUES AT INDEX 'i' UNTIL IT IS THE APPROPRIATE LENGTH

        StringBuilder fixedKey = new StringBuilder();

        fixedKey.append(bin_key);

        int pos = 0;
        for (int i = bin_key.length(); i < n; i++) {
            if (pos == bin_key.length()) {
                pos = 0;
            }

            fixedKey.append(bin_key.charAt(pos++));
        }

        return fixedKey.toString();
    }

    private static String BinaryStringXOR(String bin_key, String bin_unary_input) { // TODO

        // TODO

        int n = bin_unary_input.length();

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < n; i++) {
            if (bin_key.charAt(i) != bin_unary_input.charAt(i)) {
                result.append("1");
            } else {
                result.append("0");
            }
        }

        return result.toString();
    }

    private static String ShiftBits(String xor_, int shift) { // TODO

        // TODO



        return "";
    }

    public static HashMap<Character, String> HexTable() { // CALLED IN "HexToBin"

        // BINARY REPRESENTATION OF ALL HEX VALUES
        // EACH BIN VALUE (STRING) IS MAPPED TO A HEX VALUE (STRING)

        HashMap<Character, String> hexTable = new HashMap<>();

        hexTable.put('0', "0000");
        hexTable.put('1', "0001");
        hexTable.put('2', "0010");
        hexTable.put('3', "0011");
        hexTable.put('4', "0100");
        hexTable.put('5', "0101");
        hexTable.put('6', "0110");
        hexTable.put('7', "0111");
        hexTable.put('8', "1000");
        hexTable.put('9', "1001");
        hexTable.put('A', "1010");
        hexTable.put('B', "1011");
        hexTable.put('C', "1100");
        hexTable.put('D', "1101");
        hexTable.put('E', "1110");
        hexTable.put('F', "1111");

        return hexTable;
    }
}
