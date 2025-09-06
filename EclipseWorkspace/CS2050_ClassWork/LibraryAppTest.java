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

		// --- unit test checks for Book ---
		System.out.println("Unit Test Book Class");
		Book unitTestBook = new Book("Unmasking AI", "Joy Buolamwini", 2023);
		System.out.println("getTitle():   " + unitTestBook.getTitle());
		System.out.println("getAuthor():  " + unitTestBook.getAuthor());
		System.out.println("getYear():    " + unitTestBook.getYear());
		System.out.println("stringOfBookDetails():   " + unitTestBook.displayAllInfo());
		System.out.println();
		System.out.println("Setting up Test Library");
		int numberOfShelves = 3;
		int shelfCapacity = 4;
		System.out.println("Shelves (rows): " + numberOfShelves);
		System.out.println("Slots per shelf (columns): " + shelfCapacity);
		System.out.println("Total capacity: " + (numberOfShelves * shelfCapacity));
		System.out.println();
		Library library = new Library("Test Library", numberOfShelves, shelfCapacity);
		library.countPerShelf();
		library.displayAllBooks();
		library.getOldestBook();
		// Row 0
		library.addBook(null);
		library.addBook(new Book("Unmasking AI", "Joy Buolamwini", 2023));
		library.addBook(new Book("Hello World", "Hannah Fry", 2018));
		library.addBook(new Book("Race After Technology", "Ruha Benjamin", 2019));
		library.addBook(new Book("Deep Learning", "Ian Goodfellow", 2016));
		library.countPerShelf();
		library.displayAllBooks();
		library.getOldestBook();
		// Row 1
		library.addBook(new Book("Algorithms to Live By", "Brian Christian", 2016));
		library.addBook(new Book("Weapons of Math Destruction", "Cathy O'Neil", 2016));
		library.addBook(new Book("The Mythical Man-Month", "Fred Brooks", 1975));
		library.addBook(new Book("Refactoring", "Martin Fowler", 1999));
		// Row 2
		library.addBook(new Book("The Pragmatic Programmer", "Andrew Hunt & David Thomas", 1999));
		library.addBook(new Book("Peopleware", "Tom DeMarco & Tim Lister", 1987));
		library.addBook(new Book("Computer Lib / Dream Machines", "Ted Nelson", 1975));
		library.addBook(new Book("Unmasking AI", "Joy Buolamwini", 2023));
		library.countPerShelf();
		library.displayAllBooks();
		library.getOldestBook();
		System.out.println();
		System.out.println("\nTest add more books than capacity...");
		library.addBook(new Book("Extra Title", "Extra Author", 2024)); // should trigger "full" message
		library.countPerShelf();
		library.displayAllBooks();
		library.getOldestBook();
	}// end main
	
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

	// convert information into String and return
	public String displayAllInfo() 
	{
		return String.format("\"%s\" by %s (%d)", title, author, year);
	}
}

class Library
{
	private String name;
	private Book[][] bookShelf;
	private int numberOfShelves;
	private int shelfCapacity;
	private int currentShelf;
	private int currentSlot;
	private boolean isFull;
	private int totalNumberBooks;
	
	//Constructor
	public Library(String name, int numberOfShelves, int shelfCapacity)
	{
		this.name = name;
		this.numberOfShelves = numberOfShelves;
		this.shelfCapacity = shelfCapacity;
		this.bookShelf = new Book[numberOfShelves][shelfCapacity];
		
		isFull = false;
	}
	
	// adding a book to the shelf
	public void addBook(Book newBook)
	{
		if (newBook == null)
		{
			System.out.println("\nInvalid book.");
		}
		
		else if (isFull == true)
		{
			System.out.printf("%s is full.\n ", name);
		}
		
		else if( isFull == false)
		{
		bookShelf[currentShelf][currentSlot] = newBook;
		
		totalNumberBooks = totalNumberBooks + 1;
		
		System.out.println("\nAdded " + newBook.displayAllInfo() + " to shelf "
				+ (currentShelf+1) + ", slot " + (currentSlot+1));
		}
		
			if (totalNumberBooks == numberOfShelves*shelfCapacity)
			{
				isFull = true;
			}
			else
			{
				int nextIndex = totalNumberBooks;
				currentShelf = nextIndex / shelfCapacity;
				currentSlot = nextIndex % shelfCapacity;
			}
	}
	
	public void displayAllBooks()
	{
		int slotCount = 0;
		
		if(!this.isEmpty())
		{
			System.out.print("\n\n");
			System.out.println("Shelf\tSlot\tBook Details");
			System.out.println("******************************");
		
			for(int shelfCount = 0; shelfCount < numberOfShelves; shelfCount++)
			{
				for(slotCount = 0; slotCount < shelfCapacity; slotCount++)
					{
						if (bookShelf[shelfCount][slotCount] != null)
							{
								// System.out.println(bookShelf[shelfCount][slotCount].displayAllInfo());
								
								System.out.printf("%d\t%d\t%s\n", 
								shelfCount + 1, slotCount + 1, bookShelf[shelfCount][slotCount].displayAllInfo());
							}
					}
			}
			
			System.out.printf("%d of %d slots filled", totalNumberBooks, numberOfShelves*shelfCapacity);
			
			System.out.print("\n");
		}
	}
	
	public void getOldestBook()
	{
	
		int currentTopShelf = 0;
		int currentTopSlot = 0;
		
		if(!this.isEmpty())
		{
			
			int oldestBook = bookShelf[0][0].getYear();
		
		
			for(int shelfCount = 0; shelfCount < numberOfShelves; shelfCount++)
			{
				for(int slotCount = 0; slotCount < shelfCapacity; slotCount++)
				{
					if(bookShelf[shelfCount][slotCount] != null)
					{
						if (bookShelf[shelfCount][slotCount].getYear() < oldestBook)
						{
							oldestBook = bookShelf[shelfCount][slotCount].getYear();
							currentTopShelf = shelfCount;
							currentTopSlot = slotCount;
						}
					}
				}
			}
		
		System.out.printf("\n%s is the oldest book in %s.", 
				(bookShelf[currentTopShelf][currentTopSlot].displayAllInfo()),
				name);
		}
		
	}
	
	public void countPerShelf()
	{
		if(!this.isEmpty())
		{
			for(int shelfCount = 0; shelfCount < numberOfShelves; shelfCount++)
			{
				int currentCount = 0;
			
				for(int slotCount = 0; slotCount < shelfCapacity; slotCount++)
				{
					if(bookShelf[shelfCount][slotCount] != null)
					{
						currentCount = currentCount +1;
					}
				}
			
				System.out.printf("\nShelf %d has %d books.", shelfCount + 1, currentCount);
			}
		}
	}
	
	private boolean isEmpty()
	{
		if(bookShelf[0][0] == null)
		{
			System.out.println("\nThe library is empty");
			return true;
		}
		else
		{
			return false;
		}
	}
}

