package main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import data.Driver;
import data.Locomotive;
import data.MedicalReview;
import data.Ticket;
import data.Trip;
import data.Wagon;
import data.Worker;

public class Menu {
	
	Scanner input = new Scanner(System.in);
	public void start(DatabaseConnection databaseConnection) {
		while(true) {
			System.out.println("Unesite 1 za unos zaposlenog: ");
			System.out.println("Unesite 2 za unos nove karte: ");
			System.out.println("Unesite 3 za unos novog medicinskog pregleda");
			System.out.println("Unesite 4 za unos nove lokomotive");
			System.out.println("Unesite 5 za unos novog vagona");
			System.out.println("Unesite 6 za unos kompozicije");
			System.out.println("Unesite 7 za unos vozaca: ");
			System.out.println("Unesite 8 za unos polaska: ");
			System.out.println("Unesite 9 za pretragu zaposlenog: ");
			System.out.println("Unesite 10 za povezivanje radnika i lokomotive: ");
			System.out.println("Unesite 11 za unos teretne lokomitve");
			System.out.println("Unesite 12 za unos putnicke lokomotive: ");
			System.out.println("Unesite 13 za povezivanje vozaca i medicinskog pregleda: ");
			System.out.println("Unesite 14 za prikaz svih zaposlenih i njihovih podataka: ");
			System.out.println("Unesite 15 za prikaz svih vozaca i njihovih podataka: ");
			System.out.println("Unesite 16 za prikaz svih medicinskih pregleda:  ");
			System.out.println("Unesite 17 za brisanje vozaca: ");
			System.out.println("Unesite 18 za brisanje pomocnika: ");
			System.out.println("Unesite 19 za promjenu cijene karte: ");
			System.out.println("Unesite 20 za prikaz podataka o prodatim kartama: ");
			System.out.println("Unesite 0 za prekid programa. ");
			
			int a = checkInput(input.nextLine(), "Nevalidan unos u meniju");
			if(a == 1) {
				// jmbg
				System.out.println("Unesite matični broj radnika: ");
				String jmbg = input.nextLine();
				jmbg = input.nextLine();
				boolean jmbgCheck = checkJMBG(jmbg);
				while(!jmbgCheck || databaseConnection.checkIfWorkerExists(jmbg)) {
					System.out.println("Nepravilan unos JMBG. ");
					jmbg = input.nextLine();
					jmbgCheck = checkJMBG(jmbg);
				}
				// ime
				System.out.println("Unesite ime novog radnika: ");
				String firstName = input.nextLine();
				// prezime 
				System.out.println("Unesite prezime novog radnika: ");
				String lastName = input.nextLine();
				// radni_staz
				System.out.println("Unesite radni staž radnika: ");
				int yearsOfService = checkInput(input.nextLine(), "Nevalidan unos radnog staža! Pokušajte ponovo. ");
				// datum_zaposlenja
				System.out.println("Unesite datum zaposlenja");
				String date = input.nextLine();
				boolean dateCheck = checkIfDateIsValid(date);
				while(!dateCheck) {
					System.out.println("Nevalidan format datuma. Unesite ponovo. (Traženi format je yyyy-MM-dd)");
					date = input.nextLine();
					dateCheck = checkIfDateIsValid(date);
				}
				Worker w = new Worker(jmbg, firstName, lastName, yearsOfService, date);
				// try query
				boolean flag = databaseConnection.insertWorker(w);
				if(flag) {
					System.out.println("Uspješno unesen novi radnik! ");
				}else {
					System.out.println("Greška pri unosu novog radnika. ");
				}
			}else if(a == 2) {
				System.out.println("Unesite puno ime trgovca: ");
				String fullName = input.nextLine();
				fullName = input.nextLine();
				System.out.println("Unesite broj karte: ");
				int ticketNumber = checkInput(input.nextLine(), "Nevalidan unos broja karte");
				System.out.println("Unesite cijenu karte: ");
				double ticketPrice = checkInputDouble(input.nextLine(), "Nevalidan unos cijene karte. ");
				System.out.println("Unesite datum prodaje: ");
				String date = input.nextLine();
				boolean dateCheck = checkIfDateIsValid(date);
				while(!dateCheck) {
					System.out.println("Unos datuma nije validan, unesite ponovo. Trazeni format je yyyy-MM-dd");
					date = input.nextLine();
					dateCheck = checkIfDateIsValid(date);
				}
				Ticket t = new Ticket(fullName, ticketNumber, ticketPrice, date);
				boolean flag = databaseConnection.insertTicket(t);
				if(flag) {
					System.out.println("Uspjesno ste dodali kartu!");
				}
				else {
					System.out.println("Greska pri dodavanju karte. ");
				}
			}else if( a == 3) {
				System.out.println("Unesite nalaz medicinskog pregleda: ");
				String healthReport = input.nextLine();
				healthReport = input.nextLine();
				System.out.println("Unesite datum pregleda: ");
				String date = input.nextLine();
				while(!checkIfDateIsValid(date)) {
					System.out.println("Unos datuma nije validan, unesite ponovo. Trazeni format je yyyy-MM-dd");
					date = input.nextLine();
				}
				MedicalReview report = new MedicalReview(healthReport, date);
				boolean flag = databaseConnection.insertMedicalReview(report);
				if(flag) {
					System.out.println("Medicinski pregled uspjesno dodat. ");
				}else {
					System.out.println("Nije moguce dodati medicinski pregled.");
				}
			}else if(a == 4) {
				System.out.println("Unesite broj vagona: ");
				String numOfWagons = input.nextLine();
				numOfWagons = input.nextLine();
				int numberOfWagons = checkInput(numOfWagons, "Nevalidan unos broja vagona");
				System.out.println("Unesite godinu proizvodnje: ");
				int yearOfManufacture = checkInput(input.nextLine(), "Nevalidan unos godine. ");
				Locomotive l = new Locomotive(numberOfWagons, yearOfManufacture);
				boolean flag = databaseConnection.insertLocomotive(l);
				if(flag) {
					System.out.println("Lokomotiva je uspjesno dodata! ");
				}else {
					System.out.println("Nije moguce dodati ovu lokomotivu!");
				}
			}else if(a == 5) {
				System.out.println("Unesite broj sjedista: ");
				String numOfSeats = input.nextLine();
				numOfSeats = input.nextLine();
				int numberOfSeats = checkInput(numOfSeats, "Nepravilan unos broja sjedista. ");
				System.out.println("Unesite klasu vagona: ");
				int wagonClass = checkInput(input.nextLine(), "Nepravilan unos klase vagona. ");
				System.out.println("Unesite godinu proizvodnje: ");
				int yearOfManufacture = checkInput(input.nextLine(), "Nepravilan unos godine proizvodnje. ");
				System.out.println("Unesite Id lokomotive na koju je vagon vezan: ");
				int locomotiveId = input.nextInt();
				Wagon w = new Wagon(numberOfSeats, wagonClass, yearOfManufacture, locomotiveId);
				boolean flag = databaseConnection.insertWagon(w);
				if(flag) {
					System.out.println("Vagon je uspjesno dodat!");
				}else {
					System.out.println("Nije moguce dodati ovaj vagon. ");
				}
			}else if(a == 6) {
				System.out.println("Unesite broj kompozicije: ");
				String compositionID = input.nextLine();
				compositionID = input.nextLine();
				int ID = checkInput(compositionID, "Nepravilan unos ID-a");
				boolean flag = databaseConnection.insertComposition(ID);
				if(flag) {
					System.out.println("Kompozicija uspjesno dodata. ");
				}else {
					System.out.println("Greska pri dodavanju kompozicije. ");
				}
			}else if(a == 7) {
				System.out.println("Unesite ID zaposlenog za registraciju vozaca: ");
				String driverId = input.nextLine();
				driverId = input.nextLine();
				int ID = checkInput(driverId, "Nevalidan unos ID! ");
				boolean indicator = databaseConnection.checkForWorkerId(ID);
				while(!indicator) {
					System.out.println("ID zaposlenog ne postoji. Pokusajte ponovo. ");
					System.out.println("Unesite ID zaposlenog za registraciju vozaca: ");
					driverId = input.nextLine();
					ID = checkInput(driverId, "Nevalidan unos ID! ");
					indicator = databaseConnection.checkForWorkerId(ID);
				}
				System.out.println("Unesite sate voznje za vozaca: ");
				int workHours = checkInput(input.nextLine(), "Nevalidan unos radnih sati");
				Driver driver = new Driver(ID, workHours);
				boolean flag = databaseConnection.insertDriver(driver);
				if(flag) {
					System.out.println("Uspjesno dodat vozac! ");
				}else {
					System.out.println("Neuspjesno dodavanje vozaca! ");
				}
			}else if(a == 8) {
				System.out.println("Unesite destinaciju polaska: ");
				String destination = input.nextLine();
				destination = input.nextLine();
				System.out.println("Unesite vrijeme polaska: ");
				String time = input.nextLine();
				System.out.println("Unesite datum polaska: ");
				String date = input.nextLine();
				while(!checkIfDateIsValid(date)) {
					System.out.println("Unos datuma nije validan, unesite ponovo. Trazeni format je yyyy-MM-dd");
					date = input.nextLine();
				}
				System.out.println("Unesite ID karte za polazak: ");
				int cardId = checkInput(input.nextLine(), "Nevalidan unos ID karte!");
				System.out.println("Unesite ID kompozicije za polazak: ");
				int compositionId = checkInput(input.nextLine(), "Nevalidan unos ID kompozicije!");
				System.out.println("Unesite ID vozaca za polazak: ");
				int driverId = checkInput(input.nextLine(), "Nevalidan unos ID vozaca!");
				Trip trip = new Trip(destination, time, date, cardId, compositionId, driverId);
				boolean flag = databaseConnection.insertTrip(trip);
				if(flag) {
					System.out.println("Uspjesno dodavanje polaska! ");
				}else {
					System.out.println("Neuspjesno dodavanje polaska! ");
				}
			}else if(a == 9) {
				System.out.println("Unesite maticni broj zaposlenog: ");
				String jmbg = input.nextLine();
				jmbg = input.nextLine();
				boolean jmbgCheck = checkJMBG(jmbg);
				while(!jmbgCheck) {
					System.out.println("Nepravilan unos JMBG. ");
					jmbg = input.nextLine();
					jmbgCheck = checkJMBG(jmbg);
				}
				System.out.println(databaseConnection.findWorkerByJMBG(jmbg));
			}else if(a == 10) {
				System.out.println("Unesite jmbg radnika: ");
				String jmbg = input.nextLine();
				jmbg = input.nextLine();
				boolean jmbgCheck = checkJMBG(jmbg);
				while(!jmbgCheck || databaseConnection.checkIfWorkerExists(jmbg)) {
					System.out.println("Nepravilan unos JMBG. ");
					jmbg = input.nextLine();
					jmbgCheck = checkJMBG(jmbg);
				}
				System.out.println("Unesite ime i prezime radnika: ");
				String ime = input.nextLine();
				String prezime = input.nextLine();
				System.out.println("Unesite datum zaposlenja: ");
				System.out.println("Unesite datum zaposlenja");
				String date = input.nextLine();
				boolean dateCheck = checkIfDateIsValid(date);
				while(!dateCheck) {
					System.out.println("Nevalidan format datuma. Unesite ponovo. (Traženi format je yyyy-MM-dd)");
					date = input.nextLine();
					dateCheck = checkIfDateIsValid(date);
				}
				System.out.println("Unesite godine iskustva");
				int yearsOfExp = input.nextInt();
				Worker w = new Worker(jmbg, ime, prezime, yearsOfExp, date);
				System.out.println("Unesite broj vagona i godinu proizvodnje vase lokomotive: ");
				int numOfWagons = input.nextInt();
				int yearOfManufacture = input.nextInt();
				Locomotive l = new Locomotive(numOfWagons, yearOfManufacture);
				boolean flag = databaseConnection.insertWorkerLocomitive(w, l);
				if(flag) {
					System.out.println("Uspjeno dodata informacija. ");
				}else {
					System.out.println("Nepravilan unos podataka. ");
				}
			}else if(a == 11) {
				System.out.println("Unesite id lokomotive: ");
				int locomotiveID = input.nextInt();
				boolean indicator = databaseConnection.checkIfLocomotiveExist(locomotiveID);
				while(!indicator) {
					System.out.println("ID lokomotive nije validan. Pokusajte ponovo");
					locomotiveID = input.nextInt();
					indicator = databaseConnection.checkIfLocomotiveExist(locomotiveID);
				}
				boolean flag = databaseConnection.insertCargo(locomotiveID);
				if(flag) {
					System.out.println("Uspjesno unijeta teretna lokomotiva. ");
				}else {
					System.out.println("Greska pri unosu teretne lokomotive. ");
				}
			}else if(a == 12) {
				System.out.println("Unesite id lokomotive: ");
				int locomotiveID = input.nextInt();
				boolean indicator = (databaseConnection.checkIfLocomotiveExist(locomotiveID) && !databaseConnection.isCargo(locomotiveID));
				while(!indicator) {
					System.out.println("ID lokomotive nije validan. Pokusajte ponovo");
					locomotiveID = input.nextInt();
					indicator = (databaseConnection.checkIfLocomotiveExist(locomotiveID) && !databaseConnection.isCargo(locomotiveID));
				}
				boolean flag = databaseConnection.insertWayfairing(locomotiveID);
				if(flag) {
					System.out.println("Uspjesno unijeta putnicka lokomotiva. ");
				}else {
					System.out.println("Greska pri unosu putnicka lokomotive. ");
				}
			}else if(a == 13) {
				System.out.println("Unesite id vozaca: ");
				int driverID = input.nextInt();
				System.out.println("Unesite ID medicinskog pregleda: ");
				int reviewID = input.nextInt();
				boolean flag = databaseConnection.connectDriverAndMedical(driverID, reviewID);
				if(flag) {
					System.out.println("Vozac i pregled su povezani. ");
				}else {
					System.out.println("Greska pri povezivanju. ");
				}
			}else if(a == 14) {
				List<Worker> workers = databaseConnection.getAllWorkers();
				for(int i = 0; i < workers.size(); i++) {
					System.out.println(workers.get(i).toString());
				}
			}else if(a == 15) {
				System.out.println("Vozaci i njihovi podaci su: ");
				List<Worker> workers = databaseConnection.getAllDrivers();
				for(int i = 0; i < workers.size(); i++) {
					System.out.println(workers.get(i).toString());
				}
			}else if(a == 16) {
				System.out.println("Medicinski pregledi: ");
				List<MedicalReview> reviews = databaseConnection.getAllReviews();
				for(int i = 0; i < reviews.size(); i++) {
					System.out.println(reviews.get(i).toString());
				}
			}else if(a == 17) {
				System.out.println("Unesite id broj radnika: ");
				int id = input.nextInt();
				boolean flag = databaseConnection.deleteDriver(id);
				if(flag) {
					System.out.println("Vozac je uspjesno uklonjen. ");
				}else {
					System.out.println("Greska pri uklanjanju vozaca. ");
				}
			}else if(a == 18) {
				System.out.println("Unesite id broj pomocnika: ");
				int id = input.nextInt();
				boolean flag = databaseConnection.deleteAssistent(id);
				if(flag) {
					System.out.println("Pomocnik je uspjesno uklonjen. ");
				}else {
					System.out.println("Greska pri uklanjanju pomocnika. ");
				}
			}else if(a == 19) {
				System.out.println("Unesite id karte koju zelite da azurirate: ");
				int id = input.nextInt();
				System.out.println("Unesite novu cijenu karte: ");
				int newPrice = input.nextInt();
				boolean flag = databaseConnection.updateTicket(newPrice, id);
				if(flag) {
					System.out.println("Uspjesno azurirana cijena karte! ");
				}else {
					System.out.println("Greska pri azuriranju karte! ");
				}
			}else if(a == 20) {
				List<Ticket> tickets = databaseConnection.getAllTickets();
				for(int i = 0; i<tickets.size(); i++) {
					System.out.println(tickets.get(i).toString());
				}
			}else if(a == 0) {
				System.out.println("Gasenje programa.....");
				System.out.println("Program je ugasen");
				break;
			}else {
				System.out.println("Morate unijeti broj izmedju 0 i 20");
			}
		}
	}
	
	private boolean checkJMBG(String jmbg) {
		while(true) {
			if(jmbg.length()==13) {
				try {
					Integer.parseInt(jmbg.substring(0, 8));
					return true;
				}catch(Exception e) {
					return false;
				}
			}else {
				return false;
			}
		}
	}
	
	private int checkInput(String number, String message) {
		boolean interceptor = true;
		while(interceptor) {
			try {
				return Integer.parseInt(number);
			}catch(Exception e) {
				System.out.println(message);
				number = input.nextLine();
			}
		}
		return 0;
	}
	
	private double checkInputDouble(String number, String message) {
		boolean interceptor = true;
		while(interceptor) {
			try {
				return Double.parseDouble(number);
			}catch(Exception e) {
				System.out.println(message);
				number = input.nextLine();
			}
		}
		return 0.0;
	}
	
	private static boolean checkIfDateIsValid(String date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        // With lenient parsing, the parser may use heuristics to interpret
        // inputs that do not precisely match this object's format.
        format.setLenient(false);
        try {
            format.parse(date);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
}
