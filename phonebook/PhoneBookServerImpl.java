package classapp;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class PhoneBookServerImpl extends UnicastRemoteObject implements PhoneBookServer {

    private final static String FILE_DIRECTORY = "/home/matt/Workspace/java-rmi/phonebook";
	private final static String FILE_NAME = "data.txt";
    private static final long serialVersionUID = 1L;

    public PhoneBookServerImpl() throws RemoteException {
		super();
	}

    public static void main(String[] args) {
        try {
            // Instancia o objeto servidor e o seu stub
        	PhoneBookServerImpl server = new PhoneBookServerImpl();

            // Registra o stub para que possa ser obtido pelos clientes
            Registry registry = LocateRegistry.createRegistry(5099);
            registry.bind("PhoneBook", server);

            System.out.println("Servidor pronto");
        } catch (RemoteException | AlreadyBoundException ex) {
            System.err.println(ex);
        }
    }

    /**
     * Retorna uma lista com todos os objetos salvos
     * 
     * @throws RemoteException
     */
	public ArrayList<PhoneBookEntry> getPhoneBook() throws RemoteException {
		ArrayList<PhoneBookEntry> listPhones = new ArrayList<PhoneBookEntry>();
		try {

			File file = new File(FILE_DIRECTORY, FILE_NAME);
			Scanner s = new Scanner(file);

			while (s.hasNextLine()) {
				String data = s.nextLine();
				PhoneBookEntry phone = generateObject(data);
				listPhones.add(phone);
			}
			s.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		return listPhones;
	}

	/**
	 * Adiciona um objeto no data.txt
	 * 
     * @param entry
     * @throws RemoteException
     */
	public void addEntry(PhoneBookEntry entry) throws RemoteException {
		try {
            FileWriter writer = new FileWriter(new File(FILE_DIRECTORY, FILE_NAME), true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            bufferedWriter.write(generateData(entry));
			bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * Gera uma string do objeto para ser salvo no data.txt
	 * 
	 * @param phone
	 * @return
	 */
	private static String generateData(PhoneBookEntry phone) {
		return phone.getName() + "&" + phone.getLastname() + "&" + phone.getPhone() + "\n";
	}

	/**
	 * Gera o objeto a partir de uma string salva no data.txt
	 * 
	 * @param stringPhone
	 * @return
	 */
	private static PhoneBookEntry generateObject(String stringPhone) {
		String[] fields = stringPhone.split("&");
		PhoneBookEntry phone = new PhoneBookEntry(
			fields[0], fields[1], fields[2]
		);
		return phone;
	}
}
