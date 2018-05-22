package Client;
import Utils.*;
import java.io.*;
import java.net.*;

public class Client {

    MyDataObject myObject = new MyDataObject();
    Socket socketToServer;
    int port = Functions.getPort();
    String ipString = Functions.getIpToConnect();

    public Client() {
        System.out.println("Client Connected To Server..");
    }

    
 

public String sendLoginInfo(String username, String password) throws IOException {
        try {
            socketToServer = new Socket(ipString, port);
            myObject.setUsernamePassword(username, password);
            ObjectOutputStream myOutputStream =
                    new ObjectOutputStream(socketToServer.getOutputStream());
            myOutputStream.writeObject(myObject);
            ObjectInputStream myInputStream =
                    new ObjectInputStream(socketToServer.getInputStream());
            myObject = (MyDataObject) myInputStream.readObject();
            String rec = myObject.getMessage();
            myOutputStream.close();
            socketToServer.close();
            myInputStream.close();
            return rec;
        } catch (IOException | ClassNotFoundException ex) {
            Functions.ExceptionPrinter(ex);
            return "error";
        }
        
    }
    
    public String sendUserGetRequest(String user_id) throws IOException {
        try {
            socketToServer = new Socket(ipString, port);
            myObject.setUserGetRequest(user_id);
            ObjectOutputStream myOutputStream =
                    new ObjectOutputStream(socketToServer.getOutputStream());
            myOutputStream.writeObject(myObject);
            ObjectInputStream myInputStream =
                    new ObjectInputStream(socketToServer.getInputStream());
            myObject = (MyDataObject) myInputStream.readObject();
            String rec = myObject.getMessage();
            myOutputStream.close();
            socketToServer.close();
            myInputStream.close();
            return rec;
        } catch (IOException | ClassNotFoundException ex) {
            Functions.ExceptionPrinter(ex);
            return "error";
        }
        
    }
    
    public String sendUserData(
            String username,
            String password,
            String firstname,
            String lastname,
            String country,
            String state,
            String city,
            String address,
            String mobile,
            String email) throws IOException {
        try {
            socketToServer = new Socket(ipString, port);
            myObject.setUserData(username, password, firstname, lastname, country, state, city, address, mobile, email);
            ObjectOutputStream myOutputStream =
                    new ObjectOutputStream(socketToServer.getOutputStream());
            myOutputStream.writeObject(myObject);
            ObjectInputStream myInputStream =
                    new ObjectInputStream(socketToServer.getInputStream());
            myObject = (MyDataObject) myInputStream.readObject();
            String rec = myObject.getMessage();
            myOutputStream.close();
            socketToServer.close();
            myInputStream.close();
            return rec;
        } catch (IOException | ClassNotFoundException ex) {
            Functions.ExceptionPrinter(ex);
            return "error";
        }
    }
    
    public String sendSearchData(
            String username,
            String firstname,
            String lastname,
            String country,
            String state,
            String city) throws IOException {
        try {
            socketToServer = new Socket(ipString, port);
            myObject.setSearchData(username, firstname, lastname, country, state, city);
            ObjectOutputStream myOutputStream =
                    new ObjectOutputStream(socketToServer.getOutputStream());
            myOutputStream.writeObject(myObject);
            ObjectInputStream myInputStream =
                    new ObjectInputStream(socketToServer.getInputStream());
            myObject = (MyDataObject) myInputStream.readObject();
            String rec = myObject.getMessage();
            myOutputStream.close();
            socketToServer.close();
            myInputStream.close();
            //  System.out.println("Search data" + rec);
            return rec;
            
        } catch (IOException | ClassNotFoundException ex) {
            Functions.ExceptionPrinter(ex);
            return "error";
        }
    }
    
    public String sendUserEditData(String user_id,
            String username,
            String password,
            String firstname,
            String lastname,
            String country,
            String state,
            String city,
            String address,
            String mobile,
            String email) throws IOException {
        try {
            socketToServer = new Socket(ipString, port);
            myObject.setUserEditData(user_id, username, password, firstname, lastname, country, state, city, address, mobile, email);
            ObjectOutputStream myOutputStream =
                    new ObjectOutputStream(socketToServer.getOutputStream());
            myOutputStream.writeObject(myObject);
            ObjectInputStream myInputStream =
                    new ObjectInputStream(socketToServer.getInputStream());
            myObject = (MyDataObject) myInputStream.readObject();
            String rec = myObject.getMessage();
            myOutputStream.close();
            socketToServer.close();
            myInputStream.close();
            return rec;
        } catch (IOException | ClassNotFoundException ex) {
            Functions.ExceptionPrinter(ex);
            return "error";
        }
        
    }
    
    public String sendUserDeleteData(String user_id) throws IOException {
        try {
            socketToServer = new Socket(ipString, port);
            myObject.setUserDeleteData(user_id);
            ObjectOutputStream myOutputStream =
                    new ObjectOutputStream(socketToServer.getOutputStream());
            myOutputStream.writeObject(myObject);
            ObjectInputStream myInputStream =
                    new ObjectInputStream(socketToServer.getInputStream());
            myObject = (MyDataObject) myInputStream.readObject();
            String rec = myObject.getMessage();
            myOutputStream.close();
            socketToServer.close();
            myInputStream.close();
            return rec;
        } catch (IOException | ClassNotFoundException ex) {
            Functions.ExceptionPrinter(ex);
            return "error";
        }
        
    }
    
    public String getCountryList() {
        try {
            socketToServer = new Socket(ipString, port);
            myObject.setCountryList();
            ObjectOutputStream myOutputStream =
                    new ObjectOutputStream(socketToServer.getOutputStream());
            myOutputStream.writeObject(myObject);
            ObjectInputStream myInputStream =
                    new ObjectInputStream(socketToServer.getInputStream());
            myObject = (MyDataObject) myInputStream.readObject();
            String rec = myObject.getMessage();
            myOutputStream.close();
            socketToServer.close();
            myInputStream.close();
            
            return rec;
        } catch (IOException | ClassNotFoundException ex) {
            Functions.ExceptionPrinter(ex);
            return "error";
        }
    }
    
    public String getStateList(String country) {
        try {
            socketToServer = new Socket(ipString, port);
            myObject.setStateList(country);
            ObjectOutputStream myOutputStream =
                    new ObjectOutputStream(socketToServer.getOutputStream());
            myOutputStream.writeObject(myObject);
            ObjectInputStream myInputStream =
                    new ObjectInputStream(socketToServer.getInputStream());
            myObject = (MyDataObject) myInputStream.readObject();
            String rec = myObject.getMessage();
            myOutputStream.close();
            socketToServer.close();
            myInputStream.close();
            
            return rec;
        } catch (IOException | ClassNotFoundException ex) {
            Functions.ExceptionPrinter(ex);
            return "error";
        }
    }
    
    public String getCityList(String state) {
        try {
            socketToServer = new Socket(ipString, port);
            myObject.setCityList(state);
            ObjectOutputStream myOutputStream =
                    new ObjectOutputStream(socketToServer.getOutputStream());
            myOutputStream.writeObject(myObject);
            ObjectInputStream myInputStream =
                    new ObjectInputStream(socketToServer.getInputStream());
            myObject = (MyDataObject) myInputStream.readObject();
            String rec = myObject.getMessage();
            myOutputStream.close();
            socketToServer.close();
            myInputStream.close();
            return rec;
        } catch (IOException | ClassNotFoundException ex) {
            Functions.ExceptionPrinter(ex);
            return "error";
        }
    }
}
