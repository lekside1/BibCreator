/**
 * Name(s) and ID(s) (Maria Rizk 40020692 and Adelekan Faruq Aliu 27064896)
 * COMP249
 * Assignment # (3)
 * Due Date (11/9/2018)
 */
import java.util.Scanner;  // for reading file
import java.io.FileNotFoundException; 
import java.io.FileInputStream; // open file input stream
import java.io.PrintWriter; // writes to files
import java.io.FileOutputStream; // opens file output stream 
import java.io.File; // for file creation

//-----------------------------------------------------
//Assignment (3)
//Question: (Tasks 3 and 4)
//Written by: (Maria Rizk 40020692 and Adelekan Faruq Aliu 27064896)
//This class contains the reads input files
//-----------------------------------------------------

/**
 * 
 * @author Maria Rizk and Adelekan Faruq Aliu
 *
 */
public class BibCreator 
{
	/**
	 * This is the main method in the default package which makes use of methods from the other classes
	 * @param args unused
	 */
	public static void main(String[] args) 
	{
		// welcome message
		System.out.println("Faruq and Maria welcomes you to BibCreator!\n");

		Scanner[] bibScanner = new Scanner[10];
		PrintWriter[] latexWriter = new PrintWriter[30];
		File[] FileName = new File[30];
		File[] ScanFileName = new File[10];
		/**
		 * for loop that initializes bibScanner and ScanFileName.
		 * If file isn't found it deletes all the files that were created and exits the system.
		 */
		for(int i = 0; i<bibScanner.length; i++) 
		{
			try
			{
				/**
				 * Initializing ScanFileName and bibScanner array with a file Object for every increment.
				 */
				ScanFileName[i] = new File("Latex"+(i+1)+".bib");
				bibScanner[i] = new Scanner(new FileInputStream(ScanFileName[i]));
			}
			catch(FileNotFoundException e)
			{
				System.out.println("Could not open input file Latex"+(i+1)+".bib for reading.\nPlease check if file exists! Program will terminate after closing any opened files.");

				/**
				 * for loop that deletes all the files that were created.
				 */
				for(int j = i; j >= 0; j--)
				{
					bibScanner[j].close();
				}

				System.out.println("Goodbye! Thank you for using BibCreator for all your files needs.");
				System.exit(0);
			}
		}

		/**
		 * For loop that will initialize and create latexWriter, and will check if the files can be created.
		 */
		for(int i = 0, f = 1; i < latexWriter.length; i++)
		{
			try
			{
				/**
				 * Initializing FileName array with a file Object for every increment. 
				 */
				FileName[i] = new File("IEEE"+f+".json");
				/**
				 * Initializing PrintWriter array with  FileName[i] as an argument.
				 */
				latexWriter[i] = new PrintWriter(new FileOutputStream(FileName[i]));

				/**
				 * Incrementing i and creating a File that will store the ACM references.
				 */
				i++;
				FileName[i] = new File("ACM"+f+".json");
				latexWriter[i] = new PrintWriter(new FileOutputStream(FileName[i]));

				/**
				 * Incrementing i and creating a File that will store the NJ references.
				 */
				i++;
				FileName[i] = new File("NJ"+f+".json");
				latexWriter[i] = new PrintWriter(new FileOutputStream(FileName[i]));

				f++;
			}
			/**
			 * Catch statement that will follow certain steps when a FileNotFoundException is thrown.
			 */
			catch(FileNotFoundException e)
			{
				System.out.println("Could not create file "+FileName[i].getName()+".json for reading.\nPlease check if file exists! Program will terminate after closing any opened files.");
				/**
				 * Deleting all Files that were created if any .json files cannot be created.
				 */
				for(int j = i; j >= 0; j--)
				{
					FileName[j].delete();
				}
				/**
				 * close all input files.
				 */
				for(int k = 0; k < bibScanner.length; k++) 
				{
					bibScanner[i].close();
				}

				System.out.println("\nGoodbye! Thank you for using BibCreator for all your files needs.");
				/**
				 * Exit program.
				 */
				System.exit(0);
			}
		}

		// call this method from filesCreator class
		filesCreator.processFilesForValidation(bibScanner,FileName,ScanFileName,latexWriter);

		System.out.println();

		// calls the checkInput method
		filesCreator.checkInput();

		// closing message
		System.out.println("\nGoodbye! Thank you for using BibCreator for all your files needs.");
	}
}
