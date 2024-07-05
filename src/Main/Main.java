/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Main;

import GUI.Init;
import MyJBDC.MyJDBC;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chg
 */
public class Main {

    private static final String LOCK_FILE = "app.lock";

    public static void main(String[] args) {
        try {
            if (isAppAlreadyRunning()) {
                throw new ApplicationAlreadyRunningException("Application is already running.");
            }

            createLockFile();

            FlatMacDarkLaf.setup();
            MyJDBC.connection();

            // shutdown hook to close the connection and delete the lock file when the program ends
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                MyJDBC.connectionClose();
                deleteLockFile();
            }));

            EventQueue.invokeLater(() -> new Init().setVisible(true));
        } catch (ApplicationAlreadyRunningException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static boolean isAppAlreadyRunning() {
        File lockFile = new File(LOCK_FILE);
        return lockFile.exists();
    }

    private static void createLockFile() {
        try {
            new File(LOCK_FILE).createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private static void deleteLockFile() {
        File lockFile = new File(LOCK_FILE);
        if (lockFile.exists()) {
            lockFile.delete();
        }
    }
}

class ApplicationAlreadyRunningException extends Exception {

    public ApplicationAlreadyRunningException(String message) {
        super(message);
    }
}
