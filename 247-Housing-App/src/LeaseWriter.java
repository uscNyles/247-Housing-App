import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class LeaseWriter {

	private PrintWriter output;
	private Scanner input;/*Seller seller, Renter renter, Date startDate, Date endDate*/

	public void SignLease() throws FileNotFoundException {
		input = new Scanner(new File("LeaseAgreement.txt"));
		output = new PrintWriter("SignedLease.txt");

		while (input.hasNextLine()) {

			String line = input.nextLine();
			
			switch (line) {
			case "<SignDATE>":
				line = "<Must be replaced>";
				break;
			case "<LANDLOARD>":
				line = "<Must be replaced>";
				break;
			case "<NUM_BED>":
				line = "<Must be replaced>";
				break;
			case "<NUM_BATH>":
				line = "<Must be replaced>";
				break;
			case "<PROPERTY_ADDRESS>":
				line = "<Must be replaced>";
				break;
			case "<ZIP>":
				line = "<Must be replaced>";
				break;
			case "<START DATE>":
				line = "<Must be replaced>";
				break;
			case "<END DATE>":
				line = "<Must be replaced>";
				break;
			case "<RENT>":
				line = "<Must be replaced>";
				break;
			case "<PAYMENT ADDRESS>":
				line = "<Must be replaced>";
				break;
			case "<DAMAGE COST>":
				break;		
			}

			output.println(line);
		}
	}
}
