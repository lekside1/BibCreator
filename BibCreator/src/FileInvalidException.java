/**
 * Name(s) and ID(s) (Maria Rizk 40020692 and Adelekan Faruq Aliu 27064896)
 * COMP249
 * Assignment # (3)
 * Due Date (11/9/2018)
 */

//-----------------------------------------------------
//Assignment (3)
//Question: (Task 2)
//Written by: (Maria Rizk 40020692 and Adelekan Faruq Aliu 27064896)
//This class is for the FileInvalidException. It will be used later on in filesCreator to check if a field in empty.
//-----------------------------------------------------

/**
 * 
 * @author Maria Rizk and Adelekan Faruq Aliu
 *
 */
public class FileInvalidException extends Exception
{
	/**
	 * A default constructor that sets an error message.
	 */
	public FileInvalidException()
	{
		super("Error: Input file cannot be parsed due to missing information(i.e. month={}, title={}, etc.)");
	}

	/**
	 * A constructor that takes a String parameter
	 * @param s a String variable that represents an error message.
	 */
	public FileInvalidException(String s)
	{
		// Allows the possibility to display any other desired exception message
		super(s);
	}
	
	/**
	 * An accessor method that returns the error.
	 */
	public String getMessage()
	{
		return super.getMessage(); 
	}

}
