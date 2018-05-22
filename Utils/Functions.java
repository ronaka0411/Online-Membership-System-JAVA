package Utils;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JLabel;

/**
 *
 * @author rsa38 mgs33
 */
public class Functions {

    static int width = 1400;
    static int debug = 1;
    static int port = 19152;
    static String ipToConnect = "128.235.211.21"; //ip address on the AFS server, we are using afs1.njit.edu
    //static String ipToConnect = "127.0.0.1"; //local host

    public static boolean validatePhoneNumber(String phoneNo) {

        if (phoneNo.matches("\\d{15}")) {
            return true;
        } else if (phoneNo.matches("\\d{14}")) {
            return true;
        } else if (phoneNo.matches("\\d{13}")) {
            return true;
        } else if (phoneNo.matches("\\d{12}")) {
            return true;
        } else if (phoneNo.matches("\\d{11}")) {
            return true;
        } else if (phoneNo.matches("\\d{10}")) {
            return true;
        } else {
            return false;
        }
    }

    public static String formatPhoneNumber(String phnumber) {
        String separator = "-";
        String cc = "";
        if (phnumber.contains("+")) {
            cc = phnumber.substring(0, phnumber.indexOf(" "));
            phnumber = phnumber.substring(phnumber.indexOf(" "), phnumber.length());
        }
        phnumber = phnumber.replace("(", "");
        phnumber = phnumber.replace(")", "");
        phnumber = phnumber.replace(".", "");
        phnumber = phnumber.replace("-", "");
        phnumber = phnumber.toString().trim();

        phnumber = (phnumber == null) ? "" : phnumber;

        if ((phnumber.length() != 7) && (phnumber.length() != 10)) {
            return cc + " " + phnumber;
        }

        Pattern p = Pattern.compile("([0-9]*)");
        Matcher m = p.matcher(phnumber);
        if (m.matches()) {
            if (phnumber.length() == 7) {
                return cc + " " + phnumber.substring(0, 3) + separator + phnumber.substring(4);
            } else {
                return cc + " " + phnumber.substring(0, 3) + separator + phnumber.substring(3, 6)
                        + separator + phnumber.substring(6);
            }

        }
        return phnumber;
    }

    public static boolean validateEmail(String email) {
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher((CharSequence) email);
        return matcher.matches();
    }

    public static int getPort() {
        return port;
    }

    public static String getIpToConnect() {
        return ipToConnect;
    }

    public static void setIpToConnect(String ipToConnect) {
        Functions.ipToConnect = ipToConnect;
    }

    public static void setPort(int port) {
        Functions.port = port;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
    private static final long serialVersionUID = 1000000;
    static String AppName = "CS 602 Spring 2018 :: Online Membership System";

    public static String getAppName() {
        return AppName;
    }
    static int height = 900;
    static int UserID, UserTypeID;

    public static int getUserTypeID() {
        return UserTypeID;
    }

    public static void setUserTypeID(int UserTypeID) {
        Functions.UserTypeID = UserTypeID;
    }
    static String UserType;

    public static String getUserType() {
        return UserType;
    }

    public static void setUserType(String UserType) {
        Functions.UserType = UserType;
    }

    public static int getUserID() {
        return UserID;
    }

    public static void setUserID(int UserID) {
        Functions.UserID = UserID;
    }
    static Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    static int x = (screen.width - width) / 2;
    static int y = (screen.height - height) / 2;
    static int x_d = (screen.width - 200) / 2;
    static int y_d = (screen.height - 200) / 2;

    public static int getX_d() {
        return x_d;
    }

    public static int getY_d() {
        return y_d;
    }

    public static JLabel welcomeNote() {
        JLabel welcome;
        welcome = new JLabel("Online Membership System");
        welcome.setBounds(210, 10, 445, 120);
        welcome.setFont(new Font("Sans-Serif", Font.BOLD, 20));
        return welcome;
    }

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }

    public static int getX() {
        return x;
    }

    public static int getY() {
        return y;
    }

    public static String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            ExceptionPrinter(e);
        }
        return null;
    }

    public static void ExceptionPrinter(Exception ex) {
        if (debug == 1) {
            ex.printStackTrace();
        }
    }
}
