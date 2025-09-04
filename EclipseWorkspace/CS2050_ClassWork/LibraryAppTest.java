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

		Book newBook = new Book("John Doe", "Literature", 1978);

		System.out.println(newBook.displayAllInfo());

	}

}

class Book {
	private String author;
	private String title;
	private int year;

	// Constructor
	public Book(String author, String title, int year) {
		this.author = author;
		this.title = title;
		this.year = year;
	}

	public String getAuthor() {
		return author;
	}

	public String getTitle() {
		return title;
	}

	public int getYear() {
		return year;
	}

	public int setYear(int newYear) {
		this.year = newYear;
		return newYear;
	}

	// convert information into String and return
	public String displayAllInfo() {
		return String.format("\"%s\" by %s (%d)", title, author, year);
	}
}
