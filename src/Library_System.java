
public class Library_System {
	//max amount of books in the library system
	private final static int MAX_BOOKS_IN_LIBRARY = 4500; 
	//max amount of students in the library system
	private final static int MAX_STUDENTS_IN_SLSS = 1500; 
	private Books[] booksList; //array of Books object
	private Students[] studentsList; //array of Students object
	private int currentBooks; //the current amount of books in the library system
	private int currentStudents; //the current amount of students in the library system

	public Library_System(){

		booksList = new Books[MAX_BOOKS_IN_LIBRARY];
		studentsList = new Students[MAX_STUDENTS_IN_SLSS];

		//sets the current amount of books in the library system to 0
		currentBooks = 0; 
		//sets the current amount of students in the library system to 0
		currentStudents = 0; 
	}

	/*
	 * Adds a new book to the booksList
	 * return 1: the book has been successfully added
	 * return 2: the book could not be added 
	 * because there is no space left in the booksList
	 * return 3: the book could not be added 
	 * because the book has the same ISBN as a book already in the booksList and the book is not a duplicate
	 */

	public int addBook (Books book){
		//goes through the booksList if return 3, if the ISBN already exists
		for (int i=0; i<currentBooks;i++){
			if ((booksList[i].getISBN()== book.getISBN() && !(booksList[i].getBookTitle().equalsIgnoreCase(book.getBookTitle()))) || (booksList[i].getISBN()== book.getISBN() && !(booksList[i].getBookAuthor().equalsIgnoreCase(book.getBookAuthor()))) || (booksList[i].getISBN()== book.getISBN() && !(booksList[i].getBookCategory().equalsIgnoreCase(book.getBookCategory()))) || (booksList[i].getISBN()== book.getISBN() && booksList[i].getBookStarRating()!=book.getBookStarRating()) || (booksList[i].getISBN()== book.getISBN() && booksList[i].getBookCost()!=book.getBookCost())){
				return 3;
			}
		}
		//returns 3, if the booksList is full
		if (currentBooks>=MAX_BOOKS_IN_LIBRARY)
			return 2;
		//adds the new book into booksList and returns 1
		else{
			booksList[currentBooks] = book;
			currentBooks++;
			return 1;
		}
	}

	/*
	 * Adds a new student to the studentsList
	 * return 1: the students has been successfully added
	 * return 2: the student could not be added 
	 * because there is no space left in the studentsList
	 * return 3: the student could not be added because they have the 
	 * same first and last name as a student already in the studentsList
	 * return 4:the student could not be added 
	 * because the students number already exists
	 */

	public int addStudent (Students stu){
		//goes through the studentsList and returns 3, 
		//if the student has the same first and last name as a student already in the studentsList
		//returns 4, if the student has the same student number as a student already in the studentsList
		for (int i=0; i<currentStudents;i++){
			//the if statement makes sure the new student doesn't 
			//have same first and last name as a previously existing student or the same student number
			if (studentsList[i].getStuFName().equalsIgnoreCase(stu.getStuFName()) && studentsList[i].getStuLName().equalsIgnoreCase(stu.getStuLName()))
				return 3;
			//the if statement makes sure the new student doesn't 
			//have same student number as a previously existing student
			if (studentsList[i].getStuNum() == stu.getStuNum())
				return 4;
		}
		//if the studentsList is full, returns 2
		if (currentStudents>=MAX_STUDENTS_IN_SLSS)
			return 2;
		else{
			//the new students is added to studentsList and returns 1
			studentsList[currentStudents] = stu;
			currentStudents++;
			return 1;
		}
	}

	/*
	 * Checks out a book for a student
	 * return 0: either the stuNum or ISBN number do not match up
	 * return 1: the book has been successfully checked out
	 * return 2: the book could not be checked out 
	 * because the book is already checked out
	 * return 3: the book could not be checked out 
	 * because the student already has 3 books out
	 * return 4: the book could not be checked out 
	 * because the student has $5.00 or more in fines
	 */

	public int checkOut(int stuNum, long ISBN){
		//goes through the booksList and tries to match the ISBN to identify the book
		int count = 0;
		for (int i=0; i<currentBooks;i++){
			if (booksList[i].getISBN()== ISBN){
				//tries to match the stuNum of a stuNum of a student already in studentsList
				for (int y=0; y<currentStudents;y++){
					if (studentsList[y].getStuNum()==stuNum){
						if (booksList[i].getBookStu()==null){
							//if the students already has 3 books out, returns 4
							if (studentsList[y].getBooksOut()<3){
								if (studentsList[y].getStuFines()<=5.00){
									//the student checks out the book and a reference of the student is 
									//saved to the book and a reference of the book is saved to the student
									studentsList[y].setCheckOutStudent(booksList[i]);
									booksList[i].setCheckOutBook(studentsList[y]);
									return 1;
								}
								else {
									return 4;
								}
							}
							else
								return 3;
						}
						else
							for (int z=0; z<currentBooks;z++){
								if (booksList[z].getISBN()== ISBN)
									count = z;
							}
						if (count == i)
							return 2;
					}
				}
			}
		}
		return 0;
	}

	/*
	 * Returns a book back to the library for a student
	 * return 0: the ISBN number do not match up
	 * return 1: the book has been successfully returned
	 */

	public int returnBook(long ISBN, int timePassed){
		//goes through the booksList and tries to match the ISBN to identify the book
		for (int i=0; i<currentBooks;i++){
			if (booksList[i].getISBN()== ISBN){
				//tries to match the reference student of the book to a students in studentsList
				for (int y=0; y<currentStudents;y++){
					if (studentsList[y]==booksList[i].getBookStu()){
						//the book is returned and fines are updated
						studentsList[y].setReturnStudent(booksList[i]);
						studentsList[y].setStuFines(timePassed);
						booksList[i].setReturnBook();
						return 1;
					}
				}
			}
		}
		return 0;
	}

	/*
	 * Returns a book back to the library for a student 
	 * (identity is used if there is multiple copies of a book)
	 * return 0: the ISBN or identity number do not match up
	 * return 1: the book has been successfully returned
	 */

	public int returnBook(long ISBN, int timePassed, int identity){
		//goes through the booksList and tries to match the ISBN to identify the book
		for (int i=0; i<currentBooks;i++){
			if (booksList[i].getISBN()== ISBN && booksList[i].getIdentity()==identity){
				//tries to match the reference student of the book to a students in studentsList
				for (int y=0; y<currentStudents;y++){
					if (studentsList[y]==booksList[i].getBookStu()){
						//the book is returned and fines are updated
						studentsList[y].setReturnStudent(booksList[i]);
						studentsList[y].setStuFines(timePassed);
						booksList[i].setReturnBook();
						return 1;
					}
				}
			}
		}
		return 0;
	}

	/*
	 * Deletes a book object from the booksList
	 * return 0: either the stuNum or ISBN number do not match up
	 * return 1: the method has successfully run
	 * return 2: the book cannot be lost, since no student has checked it out
	 */

	public int bookLost(long ISBN){
		//goes through the booksList and tries to match the ISBN to identify the book
		for (int i=0; i<currentBooks;i++){
			if (booksList[i].getISBN()== ISBN){
				//tries to match the reference student of the book to a students in studentsList
				for (int y=0; y<currentStudents;y++){
					if (studentsList[y]==booksList[i].getBookStu()){
						//the students "returns" the book and fines are updated
						studentsList[y].setReturnStudent(booksList[i]);
						studentsList[y].setStuFines(booksList[i]);

						//changes the position of the bookList up one space to 
						//accommodate for the missing book
						booksList[i]=null;
						for (int z=i; z<(currentBooks-1);z++){
							booksList[z]=booksList[z + 1];
						}
						currentBooks--;
						return 1;
					}
				}
				return 2;
			}
		}
		return 0;
	}

	/*
	 * Deletes a book object from the booksList (identity is used if there is multiple copies of a book)
	 * return 0: either the stuNum or ISBN number or identity do not match up
	 * return 1: the method has successfully run
	 * return 2: the book cannot be lost, since no student has checked it out
	 */

	public int bookLost(long ISBN, int identity){
		//goes through the booksList and tries to match the ISBN to identify the book
		for (int i=0; i<currentBooks;i++){
			if (booksList[i].getISBN()== ISBN && booksList[i].getIdentity()==identity){
				//tries to match the reference student of the book to a students in studentsList
				for (int y=0; y<currentStudents;y++){
					if (studentsList[y]==booksList[i].getBookStu()){
						//the students "returns" the book and fines are updated
						studentsList[y].setReturnStudent(booksList[i]);
						studentsList[y].setStuFines(booksList[i]);

						//changes the position of the bookList up one space to 
						//accommodate for the missing book
						booksList[i]=null;
						for (int z=i; z<(currentBooks-1);z++){
							booksList[z]=booksList[z + 1];
						}
						currentBooks--;
						return 1;
					}
				}
				return 2;
			}

		}
		return 0;
	}

	/*
	 * Deletes a book and if the book is checked out by any student, 
	 * the student will not have the book anymore
	 * return 0: the ISBN do not match up
	 * return 1: the book has been successfully deleted
	 * return 2: the book cannot be lost, since no student has checked it out
	 */

	public int deleteBook(long ISBN){
		//goes through the booksList and tries to match the ISBN to identify the book
		for (int i=0; i<currentBooks;i++){
			if (booksList[i].getISBN()== ISBN){

				Students stu = booksList[i].getBookStu();

				//tries to match the reference student of the book to a students in studentsList
				for (int y=0; y<currentStudents; y++){
					if (studentsList[y]==stu)
						//the students "returns" the book
						studentsList[y].setReturnStudent(booksList[i]);
				}

				//changes the position of the bookList up one space to accommodate for deleting a book
				booksList[i]=null;
				for (int y=i; y<(currentBooks-1);y++)
					booksList[y] = booksList[y + 1];
				currentBooks--;
				return 1;
			}
		}
		return 0;
	}

	/*
	 * Deletes a book and if the book is checked out by any student, 
	 * the student will not have the book anymore (identity is used if there is multiple copies of a book)
	 * return 0: the ISBN or identity do not match up
	 * return 1: the book has been successfully deleted
	 */

	public int deleteBook(long ISBN, int identity){
		//goes through the booksList and tries to match the ISBN to identify the book
		for (int i=0; i<currentBooks;i++){
			if (booksList[i].getISBN()== ISBN && booksList[i].getIdentity()==identity){

				Students stu = booksList[i].getBookStu();

				//tries to match the reference student of the book to a students in studentsList
				for (int y=0; y<currentStudents; y++){
					if (studentsList[y]==stu)
						//the students "returns" the book
						studentsList[y].setReturnStudent(booksList[i]);
				}

				//changes the position of the bookList up one space to accommodate for deleting a book
				booksList[i]=null;
				for (int y=i; y<(currentBooks-1);y++)
					booksList[y] = booksList[y + 1];
				currentBooks--;
				return 1;
			}
		}
		return 0;
	}

	/*
	 * Deletes a student and if the student has any books checked out, 
	 * the books are returned
	 * return 0: the stuNum do not match up
	 * return 1: the student has been successfully deleted
	 */

	public int deleteStudent(int stuNum){
		//goes through the studentsList and tries to match the student number to identify the student
		for (int i=0; i<currentStudents;i++){
			if (studentsList[i].getStuNum()==stuNum){
				//any books the student has taken out are returned before the student is deleted
				Books books[] = new Books[studentsList[i].getBooksOut()];
				books=studentsList[i].getStuBooks();
				if (books!=null){
					for (int y=0; y<books.length;y++){
						for (int z=0; z<currentBooks;z++){
							if (books[y]==booksList[z]){
								booksList[z].setReturnBook();
							}
						}
					}
				}

				//changes the position of the studentsList up one space to 
				//accommodate for deleting a student
				studentsList[i]=null;
				for (int y=i; y<(currentStudents-1);y++)
					studentsList[y] = studentsList[y + 1];
				currentStudents--;
				return 1;
			}

		}
		return 0;
	}

	/*
	 * Sets the Student Fines to zero
	 * return 0: the stuNum do not match up
	 * return 1: the Student fines have been successfully reset to 0
	 */

	public int resetStuFines(int stuNum){

		for (int i=0; i<currentStudents;i++){
			if (studentsList[i].getStuNum()==stuNum){
				studentsList[i].neutralizeStuFines();
				return 1;
			}
		}
		return 0;
	}

	/*
	 * Compares two books and returns the book with the higher star rating 
	 * or a book with basic values if the books have the same star rating
	 * return null: the ISBNs do not match up
	 */

	public Books compareBooks(long ISBN1, long ISBN2){
		//creates a null book object 
		Books sameStarRating= new Books ("Title", "Author", "Category", 0, 0, 0, 0);

		//goes through the booksList and tries to match the ISBN to identify the book
		for (int i=0; i<currentBooks; i++){
			if (booksList[i].getISBN()==ISBN1){
				//goes through the booksList and tries to match the ISBN to identify the book
				for (int y=0; y<currentBooks; y++){
					if(booksList[y].getISBN()==ISBN2)
						//if the books have the same star rating a null book object is returned
						if (booksList[i].getBookStarRating()==booksList[y].getBookStarRating())
							return sameStarRating;
					//the book with the higher star rating is returned
						else if (booksList[i].getBookStarRating()>booksList[y].getBookStarRating()){
							return booksList[i];
						}
						else if (booksList[i].getBookStarRating()<booksList[y].getBookStarRating()){
							return booksList[y];
						}
				}
			}
		}
		return null;
	}

	/*
	 * Returns all the books the library system has
	 */

	public Books[] getBook(){
		//creates a new book array that has the size of however many books are currently in the library
		Books book[] = new Books[currentBooks];

		//goes through the booksList and equals book to booksList
		for (int i=0; i<currentBooks; i++){
			book[i]=booksList[i];
		}
		return book;
	}

	/*
	 * Returns a book (searching by ISBN)
	 * If a book does not exist with the specific ISBN, 
	 * the method will return null
	 */

	public Books getBook(long ISBN){
		//goes through the booksList and tries to match the ISBN to identify the book
		for (int i=0; i<currentBooks; i++){
			if (booksList[i].getISBN()==ISBN)
				return booksList[i];
		}
		return null;
	}

	/*
	 * Returns a book (searching by ISBN) (identity is used if there is multiple copies of a book)
	 * If a book does not exist with the specific ISBN and identity, 
	 * the method will return null
	 */

	public Books getBook(long ISBN, int identity){
		//goes through the booksList and tries to match the ISBN to identify the book
		for (int i=0; i<currentBooks; i++){
			if (booksList[i].getISBN()==ISBN && booksList[i].getIdentity()==identity)
				return booksList[i];
		}
		return null;
	}

	/*
	 * Returns a books array with all the books that have the same author
	 * return null: if the sizeOfArray is 0, the method will return null
	 */

	public Books[] getBooksByAuthor(String name){
		int sizeOfArray=0;
		int z=0;
		Books[] books;

		for (int i=0; i<currentBooks;i++){
			if (booksList[i].getBookAuthor().equalsIgnoreCase(name))
				sizeOfArray++;
		}

		//creates a new book array that has the size of however many books have the same author
		books = new Books[sizeOfArray];

		//goes through the booksList and equals book to booksList
		for (int i=0; i<books.length;i++){
			for (int y=z; y<currentBooks;y++){
				if (booksList[y].getBookAuthor().equalsIgnoreCase(name)){
					books[i]=booksList[y];
					z=(y+1);
					y=currentBooks;
				}
			}
		}
		//if the size of book is 0, returns null, else returns books
		if (sizeOfArray==0)
			return null;
		else 
			return books;
	}

	/*
	 * Returns a books array with all the books that have the same category
	 * return null: if the sizeOfArray is 0, the method will return null
	 */

	public Books[] getBooksByCategory(String name){
		int sizeOfArray=0;
		int z=0;
		Books[] books;

		for (int i=0; i<currentBooks;i++){
			if (booksList[i].getBookCategory().equalsIgnoreCase(name))
				sizeOfArray++;
		}

		//creates a new book array that has the size of however many books have the same category
		books = new Books[sizeOfArray];

		//goes through the booksList and equals book to booksList
		for (int i=0; i<books.length;i++){
			for (int y=z; y<currentBooks;y++){
				if (booksList[y].getBookCategory().equalsIgnoreCase(name)){
					books[i]=booksList[y];
					z=(y+1);
					y=currentBooks;
				}
			}
		}
		//if the size of book is 0, returns null, else returns books
		if (sizeOfArray==0)
			return null;
		else 
			return books;
	}

	/*
	 * Returns all the students the library system has
	 */

	public Students[] getStudent(){
		//creates a new stu array that has the size of however many students are currently in the library
		Students stu[] = new Students[currentStudents];

		//goes through the studentsList and equals stu to studentsList
		for (int i=0; i<stu.length; i++){
			stu[i]=studentsList[i];
		}
		return stu;
	}

	/*
	 * Returns a student (searching by Student number)
	 * If a student does not exist with the specific student number, 
	 * the method will return null
	 */

	public Students getStudent(int stuNum){
		//goes through the studentsList and tries to match the student number to identify the student
		for (int i=0; i<currentStudents; i++){
			if (studentsList[i].getStuNum()==stuNum)
				return studentsList[i];
		}
		return null;
	}
}
