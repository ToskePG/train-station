package main;

public class Main {

	DatabaseConnection dc;
	public static void main(String[] args) {
		DatabaseConnection dc = new DatabaseConnection("jdbc:mysql://localhost/zeljeznicka_stanica", "root", "");
		Menu m = new Menu();
		m.start(dc);
	}
}
