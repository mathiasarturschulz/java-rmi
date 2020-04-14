package classapp;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.util.ArrayList;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class PhoneBookClient {

    public static void main(String[] args) throws MalformedURLException {

        try {
            // Obtêm o stub do servidor
            PhoneBookServer stub = (PhoneBookServer)
            Naming.lookup("rmi://localhost:5099/PhoneBook");

            // Chama os métodos do servidor e apresenta os resultados
            PhoneBookEntry phone1 = new PhoneBookEntry(
                "Mathias", "Schulz", "(83) 99553-1521"
            );
            stub.addEntry(phone1);
            PhoneBookEntry phone2 = new PhoneBookEntry(
                "Fyoli", "Doar", "(83) 2695-0469"
            );
            stub.addEntry(phone2);
            PhoneBookEntry phone3 = new PhoneBookEntry(
                "Peazu", "Mibau", "(55) 2551-1089"
            );
            stub.addEntry(phone3);
            
            ArrayList<PhoneBookEntry> listPhoneBook = stub.getPhoneBook();
            
            System.out.println("Lista de felefones cadastrados: " + listPhoneBook);
        } catch (RemoteException | NotBoundException ex) {
            System.err.println(ex);
        }
    }
}
