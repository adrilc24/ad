package serpis.ad;

import java.math.BigDecimal;
import java.util.Scanner;

public class ScannerHelper {
	
	private static Scanner scanner = new Scanner(System.in);
	
	public static int getInt(String label) {
		while(true) {
			System.out.print(label);
			String intString = scanner.nextLine();
			try {
				return Integer.parseInt(intString);
			} catch (NumberFormatException ex) {
				System.out.println("Formato inválido. Vuelva a introducrilo.");
			}
		}
	}
		
	public static BigDecimal getBigDecimal(String label) {
		while(true) {
			System.out.print(label);
			String bigDecimalSting = scanner.nextLine();
			try {
				return new BigDecimal(bigDecimalSting);
			} catch (NumberFormatException ex) {
				System.out.println("Formato inválido. Vuelva a introducrilo.");
			}
		}
	}
}
