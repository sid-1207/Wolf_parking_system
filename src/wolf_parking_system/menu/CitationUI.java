package wolf_parking_system.menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;

import wolf_parking_system.crud.CitationCRUD;
import wolf_parking_system.crud.ZoneCRUD;

public class CitationUI {
    public static void citationUI(BufferedReader reader) throws NumberFormatException, IOException, SQLException {
        String CitationNumber;
        String PaymentStatus;
        Boolean AppealStatus;
        Date CitationDate;
        Time CitationTime;
        String LotName;
        String Category;

        String[] args;
        boolean exit_val = true;
        String[] main_args = null;
        CitationCRUD CitationCRUD= new CitationCRUD(Main.statement, Main.connection ,Main.result);
        while (exit_val) {

        System.out.println("1. Enter Citation Information");
        System.out.println("2. Update Citation Information");
        System.out.println("3. Delete Citation Information");
        System.out.println("4. View Citation Information");
    
        
        String input = reader.readLine();

        switch(Integer.parseInt(input)){
            case 1:
                System.out.println("Enter | separated String CitationNumber,String PaymentStatus,Boolean AppealStatus,Date CitationDate,Time CitationTime, String LotName, String Category");
                args = reader.readLine().split("[|]");
                   CitationNumber=args[0];
                   PaymentStatus=args[1];
                    AppealStatus=Boolean.valueOf(args[2]);
                    CitationDate=Date.valueOf(args[3]);
                    CitationTime=Time.valueOf(args[4]);
                    LotName=args[5];
                    Category=args[6];

                if (CitationCRUD.addCitation(CitationNumber,PaymentStatus, AppealStatus, CitationDate,CitationTime,LotName,Category)) {
                    System.out.println("Operation Successful");
                } else {
                    System.out.println("Operation Failed");
                }
                return;

            case 2:
                System.out.println("Enter | separated String CitationNumber,String PaymentStatus,Boolean AppealStatus,Date CitationDate,Time CitationTime, String LotName, String Category");
                args = reader.readLine().split("[|]");
                   CitationNumber=args[0];
                   PaymentStatus=args[1];
                    AppealStatus=Boolean.valueOf(args[2]);
                    CitationDate=Date.valueOf(args[3]);
                    CitationTime=Time.valueOf(args[4]);
                    LotName=args[5];
                    Category=args[6];

                if (CitationCRUD.updateCitation(CitationNumber,PaymentStatus, AppealStatus, CitationDate,CitationTime,LotName,Category)) {
                    System.out.println("Operation Successful");
                } else {
                    System.out.println("Operation Failed");
                }
                return;

            case 3:
               // CODE FOR DELETE CITATION
                return;
            case 4:
                if (!CitationCRUD.getCitation().isEmpty()) {
                    System.out.println("Operation Successful");
                } else {
                    System.out.println("Operation Failed");
                }
                return;
            case 5:
                exit_val= false;
                break;
            default:
                System.out.println("Enter a valid choice");


        }

        System.out.println("Enter a valid choice");

    }
      }
}
