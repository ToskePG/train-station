package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import data.Assistant;
import data.Driver;
import data.Locomotive;
import data.MedicalReview;
import data.Ticket;
import data.Trip;
import data.Wagon;
import data.Worker;

public class DatabaseConnection {
	
	Scanner input = new Scanner(System.in);
	
	private String url;
	private String user;
	private String pass;
	
	public DatabaseConnection(String url, String user, String pass) {
		this.url = url;
		this.user = user;
		this.pass = pass;
	}
	
	public Connection open() {
		try {
			return DriverManager.getConnection(url, user, pass);
		}catch(SQLException e) {
			System.out.println("Greska u konekciji sa bazom! ");
			return null;
		}
	}
	
	public boolean close(Connection conn) {
		try {
			conn.close();
			return true;
		}catch(SQLException e) {
			System.out.println("Greska. Nemoguce je zatvoriti konekciju ");
			return false;
		}
	}
	
	public boolean insertTicket(Ticket t) {
		Connection conn = open();
		String sql = "";
			sql = "INSERT INTO karta (ime_prezime,broj,cijena,datum_prodaje) VALUES ('" + t.getFullName() + "'," + t.getTicketNumber() + ","
					+ t.getTicketPrice() + ",'" + t.getTicketDate() + "');";
		try {
			Statement st = conn.createStatement();
			st.executeUpdate(sql);
			close(conn);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Nije moguce izvrsiti dodavanje! ");
		}
		return false;
	}
	
	public boolean insertWorker(Worker w) {
		Connection connection = open();
		String sql = "";
		sql = "INSERT INTO zaposleni (jmbg,ime,prezime,radni_staz,datum_zaposlenja) VALUES ('" + w.getJmbg() + "','" + w.getFirstName() + "','" + w.getLastName()
				+ "'," + w.getYearsOfExperience() + ",'" + w.getDateOfEmployment() + "');"; 
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate(sql);
			close(connection);
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean insertMedicalReview(MedicalReview m) {
		Connection connection = open();
		String sql = "";
 		sql = "INSERT INTO medicinski_pregled (nalaz,datum) VALUES ('" +m.getHealthReport()+ "','" +m.getDateOfReview()+ "');";
 		try {
 			Statement statement = connection.createStatement();
 			statement.executeUpdate(sql);
 			close(connection);
 			return true;
 		}catch(SQLException e) {
 			e.printStackTrace();
 			return false;
 		}
	}
	
	public boolean insertWagon(Wagon w) {
		Connection connection = open();
		String sql = "";
		sql = "INSERT INTO vagon (broj_sjedista,klasa,godina_proizvodnje,lokomotiva_id) VALUES (" +w.getNumberOfSeats() + "," +w.getWagonClass()+ "," +w.getYearOfManufacture()
				+"," +w.getLocomotiveId()+ ");";
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate(sql);
			close(connection);
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean insertLocomotive(Locomotive l) {
		Connection connection = open();
		String sql = "";
		sql = "INSERT INTO lokomotiva (broj_vagona,godiste_proizvodnje) VALUES (" +l.getNumberOfWagons()+ "," +l.getYearOfManufacture()+ ");";
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate(sql);
			close(connection);
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public int findWagonId(Wagon w) {
		Connection conn = open();
		String sql = "SELECT id FROM vagon WHERE broj_sjedista = " +w.getNumberOfSeats()+ "AND lokomtiva_id = " +w.getLocomotiveId()+ ";";
		try {
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			return rs.getInt(":id");
		}catch(SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	
	public int insertKuset(Wagon w) {
		Connection connection = open();
		String sql = "INSERT INTO kuset (vagon.id,broj_sjedista) VALUES (" + w.getNumberOfSeats() + "," +findWagonId(w)+ ");";
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate(sql);
			close(connection);
			return 1;
		}catch(SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public boolean insertComposition(int number) {
		Connection connection = open();
		String sql = "";
		sql = "INSERT INTO kompozicija (id) VALUES (" +number+");";
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate(sql);
			close(connection);
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean insertDriver(Driver driver) {
		Connection connection = open();
		String sql = "";
		sql = "INSERT INTO vozac (sati_voznje,zaposleni_id) VALUES (" +driver.getWorkHours()+"," +driver.getWorkerId()+");";
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate(sql);
			close(connection);
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean insertTrip(Trip trip) {
		Connection connection = open();
		String sql = "";
		sql = "INSERT INTO polazak (destinacija, vrijeme, datum, karta_id, kompozicija_id, vozac_id) VALUES ('"
		+trip.getDestination()+"','"
		+trip.getTime()+"','"
		+trip.getTripStart()+"',"
		+trip.getTripCardId()+","
		+trip.getTripCompositionId()+","
		+trip.getDriverId()+");";
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate(sql);
			close(connection);
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public String findWorkerByJMBG(String jmbg) {
		Connection connection = open();
		String sql = "SELECT * FROM zaposleni WHERE jmbg = '" +jmbg+ "';";
		try {
			Statement statemenet = connection.createStatement();
			ResultSet rs = statemenet.executeQuery(sql);
			if(rs.next()) {
				return rs.getString("ime") + " " + rs.getString("prezime");
			}
			close(connection);
			return "Nema radnika sa odabranim JMBG";
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return "Nema radnika sa odabranim JMBG";
	}
	
	public boolean checkIfWorkerExists(String jmbg) {
		Connection connection = open();
		String sql = "SELECT * FROM zaposleni WHERE jmbg = '" +jmbg+ "';";
		try {
			Statement statemenet = connection.createStatement();
			ResultSet rs = statemenet.executeQuery(sql);
			if(rs.next()) {
				return true;
			}
			close(connection);
			return false;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean checkForWorkerId(int id) {
		Connection connection = open();
		String sql = "SELECT * FROM zaposleni WHERE id = " +id+ ";";
		try {
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if(rs.next()) {
				return true;
			}
			close(connection);
			return false;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean checkIfLocomotiveExist(int number) {
		Connection connection = open();
		String sql = "SELECT * FROM lokomotiva WHERE id = " +number+ ";";
		try {
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if(rs.next()) {
				return true;
			}
			close(connection);
			return false;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean checkIfCompositionExists(int number) {
		Connection connection = open();
		String sql = "SELECT * FROM kompozicija WHERE id = " +number+ ";";
		try {
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if(rs.next()) {
				return true;
			}
			close(connection);
			return false;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean insertAssistant(Assistant assistant) {
		Connection connection = open();
		String sql = "";
		sql = "INSERT INTO pomocnik (zaposleni_id) VALUES (" +assistant.getWorkerId()+ ");";
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate(sql);
			close(connection);
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public int findWorkerId(Worker w) {
		Connection connection = open();
		String sql = "";
		sql = "SELECT id FROM zaposleni WHERE jmbg = '" +w.getJmbg()+ "');";
		try {
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(sql);
			return rs.getInt("id");
		}catch(SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public int findLocomitiveId(Locomotive l) {
		Connection connection = open();
		String sql = "";
		sql = "SELECT id FROM lokomotiva WHERE jmbg = '" +l.getNumberOfWagons()+ "," +l.getYearOfManufacture()+ ");";
		try {
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(sql);
			return rs.getInt("id");
		}catch(SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public boolean insertWorkerLocomitive(Worker w, Locomotive l) {
		int workerID = findWorkerId(w);
		int locomotiveId = findLocomitiveId(l);
		Connection connection = open();
		String sql = "";
		sql = "INSERT INTO pomocnik (zaposleni_id) VALUES (" +workerID+ "," +locomotiveId+ ");";
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate(sql);
			close(connection);
			return true;
		}catch(SQLException e) {
			// e.printStackTrace();
			return false;
		}
	}
	
	public boolean insertCargo(int ID) {
		Connection connection = open();
		String sql = "";
		sql = "INSERT INTO teretna_lokomotiva (lokomotiva_id) VALUES (" +ID+ ");";
		try {
			Statement st = connection.createStatement();
			st.executeUpdate(sql);
			close(connection);
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean insertWayfairing(int ID) {
		Connection connection = open();
		String sql = "";
		sql = "INSERT INTO putnicka_lokomotiva (lokomotiva_id) VALUES (" +ID+ ");";
		try {
			Statement st = connection.createStatement();
			st.executeUpdate(sql);
			close(connection);
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	public boolean isCargo(int ID) {
		Connection conn = open();
		String sql = "";
		sql = "SELECT * FROM teretna_lokomotiva WHERE lokomotiva_id = " +ID+ ";";
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if(rs.next()) {
				return true;
			}
			close(conn);
			return false;
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean checkForDriverId(int id) {
		Connection connection = open();
		String sql = "SELECT * FROM vozac WHERE id = " +id+ ";";
		try {
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if(rs.next()) {
				return true;
			}
			close(connection);
			return false;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean checkReviewId(int id) {
		Connection connection = open();
		String sql = "SELECT * FROM medicinski_pregled WHERE id = " +id+ ";";
		try {
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if(rs.next()) {
				return true;
			}
			close(connection);
			return false;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean connectDriverAndMedical(int driverId, int reviewId) {
		Connection connection = open();
		boolean flag = (checkForDriverId(driverId) && checkReviewId(reviewId));
		while(!flag) {
			System.out.println("Podaci nisu validni. Pokusajte ponovo");
			driverId = input.nextInt();
			reviewId = input.nextInt();
			flag = (checkForDriverId(driverId) && checkReviewId(reviewId));
		}
		String sql = "INSERT INTO vozac_pregled (vozac_id,medicinski_pregled_id) VALUES (" +driverId+ "," +reviewId+ ");";
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate(sql);
			close(connection);
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public List<Worker> getAllWorkers(){
		Connection connection = open();
		String sql = "SELECT * FROM zaposleni;";
		Statement statement;
		try {
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			List<Worker> workers= new ArrayList<>();
			while(resultSet.next()) {
				Worker w = new Worker(resultSet.getString("jmbg"), resultSet.getString("ime"), resultSet.getString("prezime"), resultSet.getInt("radni_staz"), resultSet.getString("datum_zaposlenja"));
				workers.add(w);
			}
			close(connection);
			return workers;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Worker> getAllDrivers(){
		Connection connection = open();
		String sql = "Select z.*, v.id FROM zaposleni z, vozac v WHERE v.zaposleni_id = z.id;";
		Statement statement;
		try {
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			List<Worker> workers = new ArrayList<>();
			while(resultSet.next()) {
				Worker w = new Worker(resultSet.getString("jmbg"), resultSet.getString("ime"), resultSet.getString("prezime"), resultSet.getInt("radni_staz"), resultSet.getString("datum_zaposlenja"));
				workers.add(w);
			}
			close(connection);
			return workers;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public List<MedicalReview> getAllReviews(){
		Connection connection = open();
		String sql = "SELECT * FROM medicinski_pregled";
		Statement statement;
		try {
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			List<MedicalReview> reviews = new ArrayList<>();
			while(resultSet.next()) {
				MedicalReview r = new MedicalReview(resultSet.getString("nalaz"), resultSet.getString("datum"));
				reviews.add(r);
			}
			close(connection);
			return reviews;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean deleteDriver(int id) {
		Connection connection = open();
		String sql = "DELETE FROM vozac WHERE vozac.zaposleni_id = " +id+";";
		Statement statement;
		try {
			statement = connection.createStatement();
			statement.executeUpdate(sql);
			close(connection);
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public boolean deleteAssistent(int id) {
		Connection connection = open();
		String sql = "DELETE FROM pomocnik WHERE pomocnik.zaposleni_id = " +id+";";
		Statement statement;
		try {
			statement = connection.createStatement();
			statement.executeUpdate(sql);
			close(connection);
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public List<Ticket> getAllTickets(){
		Connection connection = open();
		String sql = "SELECT * FROM karta";
		Statement statement;
		try {
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			List<Ticket> tickets = new ArrayList<>();
			while(resultSet.next()) {
				Ticket t = new Ticket(resultSet.getString("ime_prezime"), resultSet.getInt("broj"), resultSet.getDouble("cijena"), resultSet.getString("datum_prodaje"));
				tickets.add(t);
			}
			close(connection);
			return tickets;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean updateTicket(int price,int id) {
		Connection conn = open();
		String sql = "UPDATE karta SET karta.cijena = " +price+ " WHERE karta.id = " +id+ ";";
		Statement statement;
		try {
			statement = conn.createStatement();
			statement.executeUpdate(sql);
			close(conn);
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
