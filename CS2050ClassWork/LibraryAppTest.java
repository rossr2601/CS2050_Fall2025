/**
 * 
 */

/**
 * 
 */
public class LibraryAppTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		
		int number = 27;
	
		
		Book newBook = new Book("John Doe","Literature",1978);
		
		Book newBook1 = new Book("Jane Doe", "CS 2050", 2002);
		
		newBook.displayAllInfo();
		
		newBook.setYear(1002);
		
		newBook.displayAllInfo();

	}

}

class Book 
{
	private String author;
	private String title;
	private int year;
	
	// Constructor
	public Book(String author, String title, int year) 
	{
		this.author = author;
		this.title = title;
		this.year = year;
	}
	
	public String getAuthor()
	{
		return author;
	}
	
	public String getTitle() 
	{
		return title;
	}
	
	public int getYear() 
	{
		return year;
	}
	
	public int setYear(int newYear) 
	{
		this.year = newYear;
		return newYear;
	}
	
	// display all info as String
	public void displayAllInfo() 
	{
		System.out.println("Title: " + this.title);
		System.out.println("Author: " + this.author);
		System.out.println("Year: " + this.year);
	}
}
