
public class Books {
	private String bookTitle; //title of the book
	private String bookAuthor; //author of the book
	private String bookCategory; //category of the book
	private long ISBN; //ISBN of the book
	private double starRating; //star rating of the book
	private double bookCost; //cost of the book
	private int identity; //this value is used to identify the book, in case there are duplicates

	//the reference to the Students object that may have checked out the book
	private Students studentsOut;

	/*
	 * A Constructor that requires the title, author, category, ISBN, rating 
	 * and cost of the book
	 */

	public Books(String title, String author, String category, long ISBN, double rating, double cost, int identity){
		bookTitle=title;
		bookAuthor=author;
		bookCategory=category;
		this.ISBN=ISBN;
		if (rating > 5)
			starRating = 5;
		else if (rating < 1)
			starRating = 1;
		else
			starRating=rating;
		bookCost=cost;
		this.identity=identity;
	}

	/*
	 * returns the ISBN of the book
	 */

	public long getISBN (){
		return ISBN;
	}

	/*
	 * returns the identity (Integer) of the book
	 */

	public int getIdentity(){
		return identity;
	}

	/*
	 * returns the Title of the book
	 */

	public String getBookTitle(){
		return bookTitle;
	}

	/*
	 * returns the author of the book
	 */

	public String getBookAuthor(){
		return bookAuthor;
	}

	/*
	 * returns the category of the book
	 */

	public String getBookCategory(){
		return bookCategory;
	}

	/*
	 * returns a reference to the Students object that has checked out the book
	 */

	public Students getBookStu(){
		return studentsOut;
	}

	/*
	 * returns the star rating of the book
	 */

	public double getBookStarRating(){
		return this.starRating;
	}

	/*
	 * returns the cost of the book
	 */

	public double getBookCost(){
		return bookCost;
	}

	/*
	 * saves a reference of the Students object that has checked out the book
	 */

	public void setCheckOutBook (Students stu){
		studentsOut=stu;
	}

	/*
	 * sets the reference of the Students object to null, 
	 * since the book is not checked out anymore
	 */

	public void setReturnBook (){
		studentsOut=null;
	}

}

