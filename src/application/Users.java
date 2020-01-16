package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Users {
	private static String PATH = "src" + File.separator + "users.txt";
	static ArrayList<Users> u = new ArrayList<Users>();
	final String username, password;

	public Users(String uN, String pW) {
		this.username = uN;
		this.password = pW;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public static void importUsers() throws FileNotFoundException {
		File f = new File(PATH).getAbsoluteFile();
		Scanner s = new Scanner(new FileInputStream(f));

		String uN, pW;

		for(int n = 0; s.hasNext(); n++) {
			String nextLine = s.nextLine();
			String [] strs = nextLine.split(" ");

			uN = strs[0];
			pW = strs[1];

			Users newUser = new Users(uN, pW);

			u.add(newUser);
		}

		s.close();
	}

	public static void login(String uN, String pW) throws FileNotFoundException {
		importUsers();
		boolean userFound = false;
		
		for(int n = 0; n < u.size(); n++) {
			String username = u.get(n).getUsername();
			String password = u.get(n).getPassword();

			if(uN.equals(username) && pW.equals(password)) {
				successfulLogin();
				userFound = true;
				break;
			}
		}
		if(!userFound) {
			incorrectLogin();
		}
	}

	public static void successfulLogin() {
		Alert a = new Alert(AlertType.INFORMATION);
		a.setTitle("Success");
		a.setHeaderText("Logged in");
		a.setContentText("You have successfully logged in");
		a.showAndWait();
	}

	public static void incorrectLogin() {
		Alert a = new Alert(AlertType.WARNING);
		a.setTitle("Warning");
		a.setHeaderText("Incorrect Login");
		a.setContentText("Entered credentials not recognised. Try again");
		a.showAndWait();
	}
}
