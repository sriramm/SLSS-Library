
public class Students {
	private String firstName; //the first name of the student
	private String lastName; //the last name of the student
	private int stuNum; //the students number of the student
	private double stuFines; //the amount of fines in dollars the student has

	//the current number of book the students has checked out
	private int numOfBooksOut; 
	//the reference to the books out the students has checked out
	private Books[] booksOut; 
	//the max number of books the students can check out
	private final static int MAX_BOOKS_OUT=3; 
	//the max number of days the students can check out a book 
	//before getting fined
	private final static int MAX_NUM_OF_DAYS=14;
	//the amount of money that gets added to stuFines 
	//every day the book is overdue
	private final static double EVERY_DAY_OVER_DUE=0.10;

	/*
	 * A constructor that requires the first 
	 * and last name and student number of the student
	 */

	public Students (String fName,String lName,int num){
		firstName=fName;
		lastName=lName;
		stuNum=num;
		stuFines=0;
		numOfBooksOut=0;
		booksOut=new Books [MAX_BOOKS_OUT];
	}

	/*
	 * returns the first name of the student
	 */

	public String getStuFName(){
		return firstName;
	}

	/*
	 * returns the last name of the student
	 */

	public String getStuLName(){
		return lastName;
	}

	/*
	 * returns the student number of the student
	 */

	public int getStuNum(){
		return stuNum;
	}

	/*
	 * returns the amount of money the student has to pay
	 */

	public double getStuFines(){
		return stuFines;
	}

	/*
	 * returns the amount of books the student has out
	 */

	public int getBooksOut(){
		return numOfBooksOut;
	}

	/*
	 * returns a books array with the references to all the books 
	 * the student has checked out
	 */

	public Books[] getStuBooks(){
		//if (the numOfBooksOut - 1) is less than 0, null is returned
		if (numOfBooksOut == 0)
			return null;
		else {
			//creates a books array the size of the current number of books 
			//the student has checked out
			Books books[] = new Books[numOfBooksOut];

			//equals the newly creates books array to booksOut
			for (int i=0; i<numOfBooksOut; i++) 
				books[i] = booksOut[i];
			return books;
		}

	}

	/*
	 * saves the reference of the book the students has taken out 
	 * in the booksOut array and adds one more to the numOfBooksOut
	 */

	public void setCheckOutStudent(Books book){
		booksOut[numOfBooksOut]=book;
		numOfBooksOut++;
	}

	/*
	 * sets the reference of the book the students has taken out to null 
	 * and shifts the position of the books in the array up by one
	 */

	public void setReturnStudent(Books book){
		for (int i=0; i<numOfBooksOut;i++){
			if (booksOut[i]==book)
				//sets the current position in the booksOut array to null
				booksOut[i]=null; 
			//goes through the booksOut array 
			//and shifts the position of the books in the array up by one
			for (int y = i; y <(numOfBooksOut-1); y++){
				booksOut[y]=booksOut[y + 1];
			}
		}
		numOfBooksOut--;
	}

	/*
	 * Updates stuFines when a book is returned
	 */

	public void setStuFines(int timePassed){
		//if more than 14 days have passed since the book was checked out, 
		//10 cents is added to stuFines ever day overdue
		if (timePassed > MAX_NUM_OF_DAYS){
			//calculates how many cents need to be added to stuFines
			stuFines += ((timePassed - MAX_NUM_OF_DAYS)*EVERY_DAY_OVER_DUE);
		}
	}

	/*
	 * Updates stuFines when a book is lost
	 */

	public void setStuFines(Books book){
		stuFines += book.getBookCost();
	}

	/*
	 * Sets the stuFines back to zero
	 */

	public void neutralizeStuFines(){
		stuFines=0;
	}

}

