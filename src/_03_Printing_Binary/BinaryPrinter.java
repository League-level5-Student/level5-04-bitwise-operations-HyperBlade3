package _03_Printing_Binary;

public class BinaryPrinter {
	//Complete the methods below so they print the passed in parameter in binary.
	//Use bit shifting and bit masking to print the binary numbers.
	//Do not use the Integer.toBinaryString method!
	//Don't be afraid to use the methods that are already complete to finish the others.
	//Create a main method to test your methods.
	
	public void printByteBinary(byte b) {
		
		int mask = 1;
		int[] bits = new int[8];
		
		for (int i = 7; i >= 0; i--) {
			bits[i] = (b >> i) & mask;
		}
		
		
		for (int i = bits.length-1; i >= 0; i--) {
			System.out.print(bits[i]);
		}
		
		System.out.println("\n");
	}
	
	public void printShortBinary(short s) {
		int mask = 1;
		int[] bits = new int[16];
		
		for (int i = 0; i < bits.length; i++) {
			bits[i] = (s >> i) & mask;
		}
		
		
		for (int i = bits.length-1; i >= 0; i--) {
			System.out.print(bits[i]);
		}
		System.out.println("\n");
	}
	
	public void printIntBinary(int i) {
		int mask = 1;
		int[] bits = new int[32];
		
		for (int j = 0; j < bits.length; j++) {
			bits[j] = (i >> j) & mask;
		}
		
		
		for (int j = bits.length-1; j >= 0; j--) {
			System.out.print(bits[j]);
		}
		System.out.println("\n");
    }
	
	
	public void printLongBinary(long l) {
		int mask = 1;
		int[] bits = new int[64];
		
		for (int i = 0; i < bits.length; i++) {
			bits[i] = (int) ((l >> i) & mask);
		}
		
		
		for (int i = bits.length-1; i >= 0; i--) {
			System.out.print(bits[i]);
		}
		System.out.println("\n");
	}
	
	public static void main(String[] args) {
		BinaryPrinter printer = new BinaryPrinter();
		
		int num = 15;
		
		System.out.println("Byte: ");
		byte test = (byte) num;
		printer.printByteBinary(test);
		
		System.out.println("Short: ");
		short test2 = (short) num;
		printer.printShortBinary(test2);
		
		System.out.println("Integer: ");
		int test3 = num;
		printer.printIntBinary(test3);
		
		System.out.println("Long: ");
		long test4 = (long) num;
		printer.printLongBinary(test4);
		
	}
}
