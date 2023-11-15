package wolf_parking_system.menu;

import wolf_parking_system.connection.conn;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class Main {

    public static void main(String[] args) {

        while (true) {
            System.out.println("WOLFPUB DB");
            System.out.println("Menu:");
            System.out.println("1. Admin");
            System.out.println("2. Driver");
            System.out.println("3. Security");
            System.out.println("4. EXIT");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                String choice = reader.readLine();
                switch (Integer.parseInt(choice)) {
                    case 1:
                        AdminUI.adminUI(reader);
                        break;
                    case 2:
                        EditorUI.editorUI(reader);
                        break;
                    case 3:
                        DistributorUI.distributorUI(reader);
                        break;
                    case 4:
                        System.exit(0);
                        break;
                }
                System.out.println("Please enter a valid choice");
            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }

        }
    }
}




