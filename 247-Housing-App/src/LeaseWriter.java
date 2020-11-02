import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class LeaseWriter {

	private PrintWriter output;
	private Scanner inputByLine;
	private Scanner inputByWord;

	public void SignLease( ArrayList<Integer> tenantIDs, Date startDate, Date endDate, int propertyID, int roomID) throws FileNotFoundException {
		
		inputByLine = new Scanner(new File("LeaseAgreement.txt"));
		output = new PrintWriter("SignedLease.txt");

		while (inputByLine.hasNextLine()) {

			/**
			 * Reads in line of the file.
			 * The purpose of the first scanner is to start a new line 
			 * when the read in file does so
			 */
			String fileLine = inputByLine.nextLine();
			
			/**
			 * The purpose of the scanner is to read in the line
			 * given by the first scanner to edit each word 
			 * individually
			 */
			inputByWord = new Scanner(fileLine);
			
			while (inputByWord.hasNext()) {

				String fileWord = inputByWord.next();

				if (fileWord.equalsIgnoreCase("<DATE>"))
					fileWord = startDate.toString();
				if (fileWord.equalsIgnoreCase("<LANDLOARD>"))
					fileWord = DataReader.getProperty(propertyID).getSeller().getName();
				if (fileWord.equalsIgnoreCase("<NUM_BED>"))
					fileWord = Integer.toString(DataReader.getRoom(roomID).getBeds());
				if (fileWord.equalsIgnoreCase("<NUM_BATH>"))
					fileWord = Integer.toString(DataReader.getRoom(roomID).getBaths());
				if (fileWord.equalsIgnoreCase("<PROPERTY_ADDRESS>"))
					fileWord = DataReader.getProperty(propertyID).getAddress();
				if (fileWord.equalsIgnoreCase("<ZIP>"))
					fileWord = DataReader.getProperty(propertyID).getZipCode();
				if (fileWord.equalsIgnoreCase("<START DATE>"))
					fileWord = startDate.toString();
				if (fileWord.equalsIgnoreCase("<END DATE>"))
					fileWord = endDate.toString();
				if (fileWord.equalsIgnoreCase("<RENT>"))
					fileWord = Double.toString(DataReader.getRoom(roomID).getPrice());
				if (fileWord.equalsIgnoreCase("<PAYMENT ADDRESS>"))
					fileWord = DataReader.getProperty(propertyID).getAddress();
				if (fileWord.equalsIgnoreCase("<DAMAGE COST>"))
					fileWord = "$" + Double.toString(DataReader.getRoom(roomID).getPrice() * .85);
				if (fileWord.equalsIgnoreCase("<TENANT(s)>"))
					for(int i = 0; i < tenantIDs.size()-1; i++) {
						
						if (i == tenantIDs.size()-2) {
						fileWord += DataReader.getUser(tenantIDs.get(i)).getName();
						} else {
							fileWord += DataReader.getUser(tenantIDs.get(i)).getName() +", ";
						}

					}
				

				fileWord += " ";

				
			}
			output.print(fileLine);
			inputByWord.close();
			
			
		}
		inputByLine.close();
			output.close();
	}
}
