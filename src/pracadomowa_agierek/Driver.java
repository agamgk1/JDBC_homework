package pracadomowa_agierek;

public class Driver {

	public static void loadDriver(String driverName) {
		try {
			// �aduj sterownik jdbc
			Class<?> c = Class.forName(driverName);
			System.out.println("Pakiet     : " + c.getPackage());
			System.out.println("Nazwa klasy: " + c.getName());

		} catch (Exception e) {
			// sterownik nieodnaleziony
			System.out.println("Exception " + e.getMessage());
			e.printStackTrace();
			System.out.println("Steriownik nieokreslony, KONIEC PROGRAMU");
			System.exit(0);
			return;
		}
	}
}
