package pracadomowa_agierek;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Aplikacja {

	static class Login {
		static String url = "*******";
		static String uzytkownik = "*******";
		static String haslo = "********";
	}

	public static void main(String[] args) throws ClassNotFoundException {

		// ładowanie sterownika
		Driver.loadDriver("oracle.jdbc.driver.OracleDriver");

		// Tworzenie tabeli
		String sql1 = "CREATE TABLE przedmiot (idp integer not null, nazwa_przedmiotu char(20) not null)";
		String sql2 = "CREATE TABLE nauczyciel (idn integer not null, nazwisko_nauczyciela char(30) not null, imie_nauczyciela char(20) not null)";
		String sql3 = "CREATE TABLE uczen (idu integer not null, nazwisko_ucznia char(30) not null, imie_ucznia char(20) not null)";
		String sql4 = "CREATE TABLE ocena (ido integer not null, wartosc_opisowa char(20) not null, wartosc_numeryczna float not null)";
		String sql5 = "CREATE TABLE ocenianie (rodzaj_oceny char(1) not null, idp integer not null, idn integer not null, idu integer not null, ido integer not null)";

		// wypełnienie tabeli przedmioty
		String p1 = "INSERT INTO przedmiot VALUES (100, 'bilogia')";
		String p2 = "INSERT INTO przedmiot VALUES (101, 'chemia')";
		String p3 = "INSERT INTO przedmiot VALUES (102, 'fizyka')";
		String p4 = "INSERT INTO przedmiot VALUES (103, 'matematyka')";
		String p5 = "INSERT INTO przedmiot VALUES (104, 'geografia')";
		String p6 = "INSERT INTO przedmiot VALUES (105, 'informatyka')";

		// wyplnienie tabeli nauczyciel
		String n1 = "INSERT INTO nauczyciel VALUES (20, 'abacki', 'adam')";
		String n2 = "INSERT INTO nauczyciel VALUES (21, 'babacki', 'bartosz')";
		String n3 = "INSERT INTO nauczyciel VALUES (22, 'cabacki', 'cyprian')";
		String n4 = "INSERT INTO nauczyciel VALUES (23, 'dabacki', 'dariusz')";
		String n5 = "INSERT INTO nauczyciel VALUES (24, 'fabacki', 'frnciszek')";
		String n6 = "INSERT INTO nauczyciel VALUES (25, 'gabacki', 'grzegorz')";

		// wyplnienie tabeli uczen
		String u1 = "INSERT INTO uczen VALUES (30, 'habacki', 'henryk')";
		String u2 = "INSERT INTO uczen VALUES (31, 'jabacki', 'jacek')";
		String u3 = "INSERT INTO uczen VALUES (32, 'kabacki', 'konrad')";
		String u4 = "INSERT INTO uczen VALUES (33, 'mabacki', 'mateusz')";
		String u5 = "INSERT INTO uczen VALUES (34, 'nabacki', 'norbert')";
		String u6 = "INSERT INTO uczen VALUES (35, 'pabacki', 'piotr')";

		// wypelnianie tabeli ocena
		String o1 = "INSERT INTO ocena VALUES (1, 'niedostateczny', 2)";
		String o2 = "INSERT INTO ocena VALUES (2, 'niedostateczny+', 2.5)";
		String o3 = "INSERT INTO ocena VALUES (3, 'dostateczny', 3)";
		String o4 = "INSERT INTO ocena VALUES (4, 'dostateczny+', 3.5)";
		String o5 = "INSERT INTO ocena VALUES (5, 'dobry', 4)";
		String o6 = "INSERT INTO ocena VALUES (6, 'dobry+', 4.5)";
		String o7 = "INSERT INTO ocena VALUES (7, 'bardzodobry', 5)";

		// zmienne
		Statement polecenie;
		DatabaseMetaData meta;
		Connection polaczenie;
		Scanner input;
		String in, ocenaString;
		char ocenaChar;
		int idp, idn, idu, ido;

		try {

			polaczenie = DriverManager.getConnection(Login.url, Login.uzytkownik, Login.haslo);
			meta = polaczenie.getMetaData();
			polecenie = polaczenie.createStatement();
			// Tworzenie tabel oraz wype�nianie ich danymi (je�eli tabele nie istniej�)
			if (!exists("PRZEDMIOT", meta)) {
				polecenie.executeUpdate(sql1);
				polecenie.executeUpdate(p1);
				polecenie.executeUpdate(p2);
				polecenie.executeUpdate(p3);
				polecenie.executeUpdate(p4);
				polecenie.executeUpdate(p5);
				polecenie.executeUpdate(p6);
			}
			if (!exists("NAUCZYCIEL", meta)) {
				polecenie.executeUpdate(sql2);
				polecenie.executeUpdate(n1);
				polecenie.executeUpdate(n2);
				polecenie.executeUpdate(n3);
				polecenie.executeUpdate(n4);
				polecenie.executeUpdate(n5);
				polecenie.executeUpdate(n6);
			}
			if (!exists("UCZEN", meta)) {
				polecenie.executeUpdate(sql3);
				polecenie.executeUpdate(u1);
				polecenie.executeUpdate(u2);
				polecenie.executeUpdate(u3);
				polecenie.executeUpdate(u4);
				polecenie.executeUpdate(u5);
				polecenie.executeUpdate(u6);
			}
			if (!exists("OCENA", meta)) {
				polecenie.executeUpdate(sql4);
				polecenie.executeUpdate(o1);
				polecenie.executeUpdate(o2);
				polecenie.executeUpdate(o3);
				polecenie.executeUpdate(o4);
				polecenie.executeUpdate(o5);
				polecenie.executeUpdate(o6);
				polecenie.executeUpdate(o7);
			}
			if (!exists("OCENIANIE", meta)) {
				polecenie.executeUpdate(sql5);
			}

			System.out.println("WITAJ!");

			// Wprowadzanie danych z konsoli do tabeli ocenianie
			while (true) {

				System.out.println(
						"Wprowadzanie oceny (Je�eli chcesz wprowadzi� now� ocen� naci�nij ENTER. Je�eli chcesz wyj�c z programu naci�nij f i zatwierdz enterem)");
				input = new Scanner(System.in);
				in = input.nextLine();

				if (in.equalsIgnoreCase("f")) {
					break;
				}
				System.out.println("Wprowadz kolejno: rodzaj oceny, idp, idn, idu, ido");
				System.out.println("Podaj rodzaj oceny 'c' - cz�stkowa, 's' - semestralna");
				
				ocenaChar = input.next().charAt(0);
				
				System.out.println("podaj identyfikator przedmiotu z zakresu 100-105!");
				
				idp = input.nextInt();
			
				
				System.out.println("podaj identyfikator nauczyciela z zakresu 20-25!");
				
				idn = input.nextInt();
				
				
				System.out.println("podaj identyfikator ucznia z zakresu 30-35!");
				
				idu = input.nextInt();
				
				
				System.out.println("podaj ocene ucznia z zakresu 1-7!");
				
				ido = input.nextInt();
				
				
				
				String sql11 = String.format("INSERT INTO ocenianie VALUES ('%c', %d, %d, %d, %d)", ocenaChar, idp, idn,
						idu, ido);
				
				System.out.println(sql11);
				
				System.out.println("execute: " + polecenie.executeUpdate(sql11));
				System.out.println("Wprowadzenie oceny zakonczyńo się sukcesem\n");
			}
			polaczenie.close();
			input.close();
			polecenie.close();
		} catch (SQLException e) {
			System.out.println("Błąd programu");
			e.printStackTrace();
		}

		System.out.println("KONIEC");
	}

//Metoda sprawdzaj�ca czy istnieje tabela o danej nazwie
	public static Boolean exists(String tableName, DatabaseMetaData dmd) {
		ResultSet res = null;
		try {
			res = dmd.getTables(null, null, tableName, null);
			if (res.next()) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				res.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	// Metoda sprawdzaj�ca czy istnieje dane ID
	public static boolean checkIfIdExist(String id, String tab, int s, Statement stmt) {
		ResultSet res = null;
		String a = String.format("select %s from %s WHERE %s = %d", id, tab, id, s);
		try {
			res = stmt.executeQuery(a);
			if (res.next())
				return true;
			else
				return false;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				res.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
}