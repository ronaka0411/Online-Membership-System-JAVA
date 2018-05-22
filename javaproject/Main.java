
//JSON 200 Login 201 success 202 fail
package javaproject;

import Utils.Functions;
import java.awt.Color;
import java.util.Scanner;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;

/**
 *
 * @author rsa38 mgs33
 */
public class Main {

    public static void main(String[] args) {
        try {
            UIManager.put("TitledBorder.border", new LineBorder(new Color(200, 200, 200), 1));
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
	//com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            Functions.ExceptionPrinter(e);
        }

        int port;
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Enter Opened Port of Server to connect: ");
            port = sc.nextInt();
        } catch (Exception ex) {
            Functions.ExceptionPrinter(ex);
            port = 21315;
        }
        Functions.setPort(port);
        System.out.println("Client will connect on PORT : " + Functions.getPort());
        //SplashScreen splash = new SplashScreen(5000);

        Login.open();
        //ViewMember.open();

    }
}