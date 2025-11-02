/**
 * 
 */
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;


/**
 * 
 */
public class ProjectIteration01 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner input = new Scanner(System.in);
        SongLibrary library = new SongLibrary();
        SinglyLinkedList queue = new SinglyLinkedList();
        boolean running = true;

        while (running) {
            // Display menu
            System.out.println("\n--- Song Player Menu ---");
            System.out.println("1. Load songs from CSV");
            System.out.println("2. Display playlist");
            System.out.println("3. Play specific song");
            System.out.println("4. Add song to Up-Next queue");
            System.out.println("5. Display Up-Next queue");
            System.out.println("6. Play next song in queue");
            System.out.println("7. Exit");
            System.out.print("Select an option: ");

            String choice = input.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter CSV file path: ");
                    String filePath = input.nextLine();
                    library.loadFromCSV(filePath);
                    break;

                case "2":
                    library.displayPlaylist();
                    break;

                case "3":
                    System.out.print("Enter song index to play: ");
                        int index = input.nextInt();
                        Song song = library.getSong(index);
                        if (song != null) 
                        {
                            System.out.println("Now playing: " + song);
                        } 
                        else 
                        {
                            System.out.println("Invalid index.");
                        }
                    break;

                case "4":
                    System.out.print("Enter song index to add to queue: ");
                        int indexQueue = input.nextInt();
                        Song songQueue = library.getSong(indexQueue);
                        if (songQueue != null) 
                        {
                            queue.addQueue(songQueue);
                            System.out.println("Added to queue: " + songQueue);
                        } 
                        else 
                        {
                            System.out.println("Invalid index. Nothing added to queue.");
                        }
                    break;

                case "5":
                    queue.printQueue();
                    break;

                case "6":
                    Song nextSong = queue.removeQueue();
                    if (nextSong != null) 
                    {
                        System.out.println("Now playing: " + nextSong);
                    } 
                    else 
                    {
                        System.out.println("Queue is empty.");
                    }
                    break;

                case "7":
                    System.out.println("Exiting song player...");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid selection.");
            }
        }

        input.close();
	}

}


class Song 
{
	private String title;
	private String artist;
	private int duration;
	
	public Song(String title, String artist, int duration)
	{
		this.title = title;
		this.artist = artist;
		this.duration = duration;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public String getArtist()
	{
		return artist;
	}
	
	public int getDuration()
	{
		return duration;
	}
	
	public String toString()
	{
		int minutes = duration / 60;
		int seconds = duration % 60;
		return "\"" + title + "\" by " + artist + " (" + minutes + ":" + 
				String.format("%02d", seconds) + ")";
	}
}
	
class SinglyLinkedList
{
	private Node head;
	private Node tail;
	
	public SinglyLinkedList()
	{
		this.head = null;
        this.tail = null;
	}
	
	public void addQueue(Song song)
	{
		Node newNode = new Node(song);
		
	    if (isEmpty()) 
	    {
	        head = newNode;
	        tail = newNode;
	    } 
	    else 
	    {
	        tail.next = newNode;
	        tail = newNode;
	    }
	}
	
	public Song removeQueue()
	{
		if (isEmpty()) 
		{
	        return null;
	    }
		
	    Song removedSong = head.song;
	    head = head.next;
	    
	    if (head == null) 
	    {
	        tail = null;
	    }
	    return removedSong;
	}
	
	public Song firstQueue() 
	{
        if (isEmpty()) return null;
        return head.song;
    }
	
	public boolean isEmpty()
	{
		return head == null;
	}
	
	public void printQueue()
	{
		if (isEmpty()) 
		{
            System.out.println("Queue is empty.");
        }
		
		else 
		{
        Node current = head;
        int i = 0;
        	while (current != null) 
        	{
        		System.out.println("[" + i + "] " + current.song);
        		current = current.next;
        		i++;
        	}
		}
	}
}

	
class Node 
{
	public Song song;
	public Node next;
	
	public Node(Song song)
	{
		this.song = song;
        this.next = null;
	}
}

class SongLibrary 
{
    private ArrayList<Song> songs;

    public SongLibrary() 
    {
        songs = new ArrayList<>();
    }

    public void addSong(Song song) 
    {
        songs.add(song);
    }

    public Song getSong(int index) 
    {
        if (index < 0 || index >= songs.size())
        {
            return null;
        }
        return songs.get(index);
    }
    
    public void displayPlaylist() 
    {
        if (songs.isEmpty()) 
        {
            System.out.println("Playlist is empty.");
        }
        else 
        {
        	for (int i = 0; i < songs.size(); i++) 
        	{
        		System.out.println("[" + i + "] " + songs.get(i));
        	}
        }
    }
    
    public void loadFromCSV(String filePath) 
    {
        int loadedCount = 0;

        try 
        {
            Scanner scanner = new Scanner(new File(filePath));
            int lineNumber = 0;

            while (scanner.hasNextLine()) 
            {
                lineNumber++;
                String[] parts = scanner.nextLine().split(",");

                if (parts.length != 3) 
                {
                    System.out.println("Line " + lineNumber + " skipped: missing fields.");
                    continue;
                }
                
                String title = parts[0].trim();
                String artist = parts[1].trim();
                int duration = Integer.parseInt(parts[2].trim());
                
                Song newSong = new Song(title, artist, duration);
                addSong(newSong);
                loadedCount++;
            }

            System.out.println(loadedCount + " songs loaded.");
            scanner.close();

        } 
        catch (IOException e) 
        {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}