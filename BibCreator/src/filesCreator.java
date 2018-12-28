/**
 * Name(s) and ID(s) (Maria Rizk 40020692 and Adelekan Faruq Aliu 27064896)
 * COMP249
 * Assignment # (3)
 * Due Date (11/9/2018)
 */
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.File;

//--------------------------------------------------------------------------------------------------
//Assignment (3)
//Question: (Tasks 5, 6 and 7)
//Written by: (Maria Rizk 40020692 and Adelekan Faruq Aliu 27064896)
//This class checks if the file is Invalid and if so deletes the output files, 
//if the file is valid it scans it and creates the corresponding output files.
//--------------------------------------------------------------------------------------------------

/**
 * 
 * @author Maria Rizk and Adelekan Faruq Aliu
 *
 */
public class filesCreator 
{
	//class attributes
	private static int InvalidFiles = 0;
	private static int validFiles = 0;

	/**
	 * This method checks if a file is valid.
	 * Declares the FileInvalidException.
	 * @param sc The scanner to read the file.
	 * @throws FileInvalidException Thrown if a file is invalid.
	 */
	public static void checkValid(Scanner sc) throws FileInvalidException 
	{
		String line = "";
		while(sc.hasNextLine())
		{
			line += sc.nextLine();
		}

		StringTokenizer tokens = new StringTokenizer(line);
		String l;

		while(tokens.hasMoreTokens())
		{
			l = tokens.nextToken();

			if(l.equals("author={},") || l.equals("author={},}"))
			{
				throw new FileInvalidException("Field is Invalid: Field \"author\" is Empty. Processing stopped at this point. Other empty fields may be present as well.");
			}
			if(l.equals("journal={},") || l.equals("journal={},}"))
			{
				throw new FileInvalidException("Field is Invalid: Field \"journal\" is Empty. Processing stopped at this point. Other empty fields may be present as well.");
			}
			if(l.equals("ISSN={},") || l.equals("ISSN={},}"))
			{
				throw new FileInvalidException("Field is Invalid: Field \"ISSN\" is Empty. Processing stopped at this point. Other empty fields may be present as well.");
			}
			if(l.equals("month={},") || l.equals("month={},}"))
			{
				throw new FileInvalidException("Field is Invalid: Field \"month\" is Empty. Processing stopped at this point. Other empty fields may be present as well.");
			}
			if(l.equals("keywords={},") || l.equals("keywords={},}"))
			{
				throw new FileInvalidException("Field is Invalid: Field \"keywords\" is Empty. Processing stopped at this point. Other empty fields may be present as well.");
			}
			if(l.equals("doi={},") || l.equals("doi={},}"))
			{
				throw new FileInvalidException("Field is Invalid: Field \"doi\" is Empty. Processing stopped at this point. Other empty fields may be present as well.");
			}
			if(l.equals("year={},") || l.equals("year={},}"))
			{
				throw new FileInvalidException("Field is Invalid: Field \"year\" is Empty. Processing stopped at this point. Other empty fields may be present as well.");
			}
			if(l.equals("number={},") || l.equals("number={},}"))
			{
				throw new FileInvalidException("Field is Invalid: Field \"number\" is Empty. Processing stopped at this point. Other empty fields may be present as well.");
			}
			if(l.equals("pages={},") || l.equals("pages={},}"))
			{
				throw new FileInvalidException("Field is Invalid: Field \"pages\" is Empty. Processing stopped at this point. Other empty fields may be present as well.");
			}
			if(l.equals("title={},") || l.equals("title={},}"))
			{
				throw new FileInvalidException("Field is Invalid: Field \"title\" is Empty. Processing stopped at this point. Other empty fields may be present as well.");
			}
			if(l.equals("volume={},") || l.equals("volume={},}"))
			{
				throw new FileInvalidException("Field is Invalid: Field \"volume\" is Empty. Processing stopped at this point. Other empty fields may be present as well.");
			}
		}
		sc.close(); // closes the scanner
	}

	/**
	 * This method uses the checkValid method to check the validity of files.
	 * Scans the file if it is valid and creates the corresponding output files.
	 * Deletes the created files of invalid files.
	 * @param sc  The Scanner that reads the input files.
	 * @param FiletoPrint Files written by the PrintWriter.
	 * @param FiletoScan Files read by the scanner.
	 * @param pw The PrintWriter that writes to the output files.
	 */
	public static void processFilesForValidation(Scanner[] sc, File[] FiletoPrint, File[] FiletoScan, PrintWriter[] pw)
	{
		boolean invalid = false;
		for(int i = 0, f = 0; i < sc.length; i++, f++)
		{
			// while the input file is invalid
			do
			{
				try
				{
					checkValid(sc[i]);
					sc[i] = new Scanner(new FileInputStream(FiletoScan[i]));
				}
				catch(FileInvalidException e)
				{
					++InvalidFiles;

					System.out.println("\nError Detected Empty Field!");
					System.out.println("===========================");
					System.out.println("\nProblem detected with input file: "+FiletoScan[i].getName());
					System.out.println(e.getMessage());

					// delete invalid files(IEEE, ACM and NJ)
					pw[f].close();
					FiletoPrint[f].delete();
					f++;
					pw[f].close();
					FiletoPrint[f].delete();
					f++;
					pw[f].close();
					FiletoPrint[f].delete();
					f++;
					i++;

					try
					{
						checkValid(sc[i]);
						sc[i] = new Scanner(new FileInputStream(FiletoScan[i]));
					}
					catch(FileInvalidException e1)
					{
						invalid = true;
					}
					catch(FileNotFoundException e2)
					{
						System.out.println(e2.getMessage());
					}
				}
				catch(FileNotFoundException e)
				{
					System.out.println(e.getMessage());
				}
			}
			while(invalid == true);

			++validFiles;

			/**
			 * IEEE format.
			 */
			// create ArrayList for each field
			ArrayList<String> IEEEauthors = new ArrayList<String>();        
			ArrayList<String> IEEEvolume = new ArrayList<String>();
			ArrayList<String> IEEEnumber = new ArrayList<String>();
			ArrayList<String> IEEEjournal = new ArrayList<String>();
			ArrayList<String> IEEEpages = new ArrayList<String>();
			ArrayList<String> IEEEyear = new ArrayList<String>();
			ArrayList<String> IEEEtitle = new ArrayList<String>();
			ArrayList<String> IEEEmonth = new ArrayList<String>();

			// checks every line of the input files for specific field and store it
			// into their corresponding Arrays
			while(sc[i].hasNextLine())
			{
				String lineInFile=sc[i].nextLine();

				if((lineInFile.indexOf("author={")) >-1)
				{
					IEEEauthors.add((lineInFile));
				}
				if((lineInFile.indexOf("journal={")) >-1)
				{
					IEEEjournal.add(lineInFile);
				}
				if((lineInFile.indexOf("volume={")) >-1)
				{
					IEEEvolume.add((lineInFile));
				}
				if((lineInFile.indexOf("number={")) >-1)
				{
					IEEEnumber.add((lineInFile));
				}
				if((lineInFile.indexOf("pages={")) >-1)
				{
					IEEEpages.add((lineInFile));
				}
				if((lineInFile.indexOf("month={")) >-1)
				{
					IEEEmonth.add((lineInFile));
				}
				if((lineInFile.indexOf("year={")) >-1)
				{
					IEEEyear.add((lineInFile));
				}
				if((lineInFile.indexOf("title={")) >-1)
				{
					IEEEtitle.add((lineInFile));
				}
			}

			// replaces specific parts to match the format of IEEE
			for(int j = 0; j < IEEEauthors.size();  j++)
			{
				IEEEauthors.set(j,IEEEauthors.get(j).replace("author={",""));
				IEEEauthors.set(j,IEEEauthors.get(j).replace(" and", ","));
				IEEEauthors.set(j,IEEEauthors.get(j).replace("},", "."));
			}
			for(int j = 0; j < IEEEtitle.size(); j++)
			{
				IEEEtitle.set(j,IEEEtitle.get(j).replace("title={","\""));
				IEEEtitle.set(j,IEEEtitle.get(j).replace("},","\","));	
			}
			for(int j = 0; j < IEEEjournal.size(); j++)
			{
				IEEEjournal.set(j,IEEEjournal.get(j).replace("journal={",""));
				IEEEjournal.set(j,IEEEjournal.get(j).replace("},", ","));	
			}
			for(int j = 0; j < IEEEvolume.size(); j++)
			{
				IEEEvolume.set(j,IEEEvolume.get(j).replace("volume={","vol. "));
				IEEEvolume.set(j,IEEEvolume.get(j).replace("},", ","));	
			}
			for(int j = 0; j < IEEEnumber.size(); j++)
			{
				IEEEnumber.set(j,IEEEnumber.get(j).replace("number={","no. "));
				IEEEnumber.set(j,IEEEnumber.get(j).replace("},", ","));	
			}
			for(int j = 0; j < IEEEpages.size(); j++)
			{
				IEEEpages.set(j,IEEEpages.get(j).replace("pages={","p. "));
				IEEEpages.set(j,IEEEpages.get(j).replace("},", ","));	
			}
			for(int j = 0; j < IEEEmonth.size(); j++)
			{
				IEEEmonth.set(j,IEEEmonth.get(j).replace("month={",""));
				IEEEmonth.set(j,IEEEmonth.get(j).replace("},", " "));	
			}
			for(int j = 0; j < IEEEyear.size(); j++)
			{
				IEEEyear.set(j,IEEEyear.get(j).replace("year={",""));
				IEEEyear.set(j,IEEEyear.get(j).replace("},", "."));	
			}

			// printing onto the output files
			for(int j = 0; j < IEEEauthors.size(); j++)
			{
				pw[f].println(IEEEauthors.get(j) + IEEEtitle.get(j) + IEEEjournal.get(j) + IEEEvolume.get(j) + IEEEnumber.get(j) + IEEEpages.get(j) + IEEEmonth.get(j) + IEEEyear.get(j));
				pw[f].println();
			}
			pw[f].flush();
			f++;

			/**
			 * ACM Format.
			 */
			// open scanner for the input file to be scanned.
			try
			{
				sc[i] = new Scanner(new FileInputStream(FiletoScan[i]));
			}

			catch(FileNotFoundException e)
			{
				System.out.println(e.getMessage());
			}

			// create ArrayList for each fields
			ArrayList<String> ACMmonth = new ArrayList<String>();
			ArrayList<String> ACMauthors = new ArrayList<String>();        
			ArrayList<String> ACMvolume = new ArrayList<String>();
			ArrayList<String> ACMnumber = new ArrayList<String>();
			ArrayList<String> ACMjournal = new ArrayList<String>();
			ArrayList<String> ACMpages = new ArrayList<String>();
			ArrayList<String> ACMyear = new ArrayList<String>();
			ArrayList<String> ACMtitle = new ArrayList<String>();
			ArrayList<String> ACMdoi = new ArrayList<String>();

			while(sc[i].hasNextLine())
			{
				String lineInFile=sc[i].nextLine();

				if((lineInFile.indexOf("author={")) >-1)
				{
					ACMauthors.add((lineInFile));
				}
				if((lineInFile.indexOf("journal={")) >-1)
				{
					ACMjournal.add(lineInFile);
				}
				if((lineInFile.indexOf("volume={")) >-1)
				{
					ACMvolume.add((lineInFile));
				}
				if((lineInFile.indexOf("number={")) >-1)
				{
					ACMnumber.add((lineInFile));
				}
				if((lineInFile.indexOf("pages={")) >-1)
				{
					ACMpages.add((lineInFile));
				}
				if((lineInFile.indexOf("month={")) >-1)
				{
					ACMmonth.add((lineInFile));
				}
				if((lineInFile.indexOf("year={")) >-1)
				{
					ACMyear.add((lineInFile));
				}
				if((lineInFile.indexOf("title={")) >-1)
				{
					ACMtitle.add((lineInFile));
				}
				if((lineInFile.indexOf("doi={")) >-1)
				{
					ACMdoi.add((lineInFile));
				}
			}

			for(int j = 0; j < ACMauthors.size(); j++)
			{
				ACMauthors.set(j,ACMauthors.get(j).replace("author={", ""));

				if(ACMauthors.get(j).indexOf("and") >-1)
				{
					ACMauthors.set(j,ACMauthors.get(j).substring(0, ACMauthors.get(j).indexOf("and")));
					ACMauthors.set(j,ACMauthors.get(j) + "et al. ");
				}
				// if only 1 author
				else
					ACMauthors.set(j,ACMauthors.get(j).replace("},", "."));
			}
			for(int j = 0; j < ACMyear.size(); j++)
			{
				ACMyear.set(j,ACMyear.get(j).replace("year={", ""));
				ACMyear.set(j,ACMyear.get(j).replace("},", "."));	
			}
			for(int j = 0; j < ACMtitle.size(); j++)
			{
				ACMtitle.set(j,ACMtitle.get(j).replace("title={", ""));	
				ACMtitle.set(j,ACMtitle.get(j).replace("},", "."));	
			}
			for(int j = 0; j < ACMjournal.size(); j++)
			{
				ACMjournal.set(j,ACMjournal.get(j).replace("journal={", ""));
				ACMjournal.set(j,ACMjournal.get(j).replace("},", "."));
			}
			for(int j = 0; j < ACMvolume.size(); j++)
			{
				ACMvolume.set(j,ACMvolume.get(j).replace("volume={", ""));
				ACMvolume.set(j,ACMvolume.get(j).replace("},", ","));
			}
			for(int j = 0; j < ACMnumber.size(); j++)
			{
				ACMnumber.set(j,ACMnumber.get(j).replace("number={", ""));
				ACMnumber.set(j,ACMnumber.get(j).replace("},", ""));
				ACMnumber.set(j,ACMnumber.get(j)+"("+ACMyear.get(j).replace(".", "),"));
			}
			for(int j = 0; j < ACMpages.size(); j++)
			{
				ACMpages.set(j,ACMpages.get(j).replace("pages={",""));
				ACMpages.set(j,ACMpages.get(j).replace("},","."));
			}
			for(int j = 0; j < ACMdoi.size(); j++)
			{
				ACMdoi.set(j,ACMdoi.get(j).replace("doi={", "DOI:https://doi.org/"));
				ACMdoi.set(j,ACMdoi.get(j).replace("},", "."));	
			}


			for(int j = 0; j < ACMjournal.size(); j++)
			{
				pw[f].println("["+(j+1)+"]"+"		"+ACMauthors.get(j) + ACMyear.get(j) + ACMtitle.get(j) + ACMjournal.get(j) + ACMvolume.get(j) + ACMnumber.get(j) + ACMpages.get(j) + ACMdoi.get(j));
				pw[f].println();
			}
			pw[f].flush();
			f++;

			/**
			 * NJ Format.
			 */
			try
			{
				sc[i] = new Scanner(new FileInputStream(FiletoScan[i]));
			}

			catch(FileNotFoundException e)
			{
				System.out.println(e.getMessage());
			}

			// create ArrayList for each fields
			ArrayList<String> NJauthors = new ArrayList<String>();
			ArrayList<String> NJvolume = new ArrayList<String>();
			ArrayList<String> NJjournal = new ArrayList<String>();
			ArrayList<String> NJpages = new ArrayList<String>();
			ArrayList<String> NJyear = new ArrayList<String>();
			ArrayList<String> NJtitle = new ArrayList<String>();

			while(sc[i].hasNextLine())
			{
				String lineInFile = sc[i].nextLine();

				if((lineInFile.indexOf("author={")) >-1)
				{
					NJauthors.add((lineInFile));
				}
				if((lineInFile.indexOf("journal={")) >-1)
				{
					NJjournal.add(lineInFile);
				}
				if((lineInFile.indexOf("volume={")) >-1)
				{
					NJvolume.add((lineInFile));
				}
				if((lineInFile.indexOf("pages={")) >-1)
				{
					NJpages.add((lineInFile));
				}
				if((lineInFile.indexOf("year={")) >-1)
				{
					NJyear.add((lineInFile));
				}
				if((lineInFile.indexOf("title={")) >-1)
				{
					NJtitle.add((lineInFile));
				}
			}

			for(int j = 0; j < NJauthors.size(); j++)
			{
				NJauthors.set(j,NJauthors.get(j).replace("author={", ""));
				NJauthors.set(j,NJauthors.get(j).replace("},", "."));
				NJauthors.set(j,NJauthors.get(j).replace("and", "&"));
			}
			for(int j = 0; j < NJtitle.size(); j++)
			{
				NJtitle.set(j,NJtitle.get(j).replace("title={", ""));
				NJtitle.set(j,NJtitle.get(j).replace("},", "."));

			}
			for(int j = 0; j < NJjournal.size(); j++)
			{
				NJjournal.set(j,NJjournal.get(j).replace("journal={", ""));
				NJjournal.set(j,NJjournal.get(j).replace("},", "."));	
			}
			for(int j = 0; j < NJvolume.size(); j++)
			{
				NJvolume.set(j,NJvolume.get(j).replace("volume={", ""));
				NJvolume.set(j,NJvolume.get(j).replace("},", ","));	
			}
			for(int j = 0; j < NJyear.size(); j++)
			{
				NJyear.set(j,NJyear.get(j).replace("year={", ""));
				NJyear.set(j,NJyear.get(j).replace("},", ")."));	
			}

			for(int j = 0; j < NJpages.size(); j++)
			{
				NJpages.set(j,NJpages.get(j).replace("pages={",""));
				NJpages.set(j,NJpages.get(j).replace("}, ", ""));	
				NJpages.set(j,NJpages.get(j)+"("+NJyear.get(j));

			}

			for(int j = 0; j < NJauthors.size(); j++)
			{
				pw[f].println(NJauthors.get(j) + NJtitle.get(j) + NJjournal.get(j) + NJvolume.get(j) + NJpages.get(j));
				pw[f].println();
			}
			pw[f].flush();
			sc[i].close();
		}
		// prints the number of invalid and valid files.
		System.out.println("\nA total of "+InvalidFiles+" files were invalid, and could not be processed. All other "+validFiles+" \"Valid\" files have been created.");
	}

	/**
	 * This method uses Scanner to accept user input and BufferedReader to read a file
	 * @param bf This is the BufferedReader that reads the file
	 */
	public static void checkInput()
	{
		BufferedReader bf = null;
		Scanner cin = new Scanner(System.in);
		String inputFile;

		System.out.print("Please enter the name of the file you need to review: ");
		inputFile = cin.next(); // accept user input

		File myfile = new File(inputFile);
		FileReader fr = null;
		String info;

		// First Try
		try 
		{
			// opens file and buffering input stream
			fr = new FileReader(myfile);
			bf = new BufferedReader(fr);

			// file does not exist
			if(!myfile.exists()) 
			{
				cin.close();
				fr.close();
				throw new FileNotFoundException();
			}

			// file exists
			else 
			{
				info = bf.readLine();
				while(info != null)
				{
					System.out.println(info); // print file info to screen
					info = bf.readLine();
				}
			}
		} 
		catch (FileNotFoundException e) 
		{
			System.out.println("Could not open input file. File does not exist; possibly it could not be created.");

			// Second Try
			try
			{
				System.out.println("\nHowever you will be allowed another chance to enter another file name.");
				System.out.print("Please enter the name of the file you need to review: ");

				// get user input
				inputFile = cin.next();

				// opens file and buffering input stream
				myfile = new File(inputFile);
				fr = new FileReader(myfile);
				bf = new BufferedReader(fr);

				// file does not exist
				if(!myfile.exists()) 
				{
					fr.close();
					cin.close();
					throw new FileNotFoundException();
				}

				// file exists, read file
				else 
				{
					info = bf.readLine();
					while(info != null)
					{
						System.out.println(info); // print file info to screen
						info = bf.readLine();
					}
				}
			}
			catch(FileNotFoundException e2)
			{
				System.out.println("\nCould not open input file. File does not exist; possibly it could not be created.\n"
						+ "Sorry! I am unable to display your desired files! Program will exit!");

				System.out.println("Goodbye! Thank you for using BibCreator for all your files needs.");
				System.exit(0); // exits
			} 
			catch (IOException e2) 
			{
				System.out.println(e.getMessage());
			}
			
			cin.close(); // close scanner
		} 
		catch (IOException e) 
		{
			System.out.println("An IO Error occured!");
		}
	}
}

