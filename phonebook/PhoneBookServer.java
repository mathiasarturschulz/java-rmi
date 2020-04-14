package classapp;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface PhoneBookServer extends Remote {
	
    public ArrayList<PhoneBookEntry> getPhoneBook() throws RemoteException;
    public void addEntry(PhoneBookEntry entry) throws RemoteException;
    
}
