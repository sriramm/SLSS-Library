/*  
 * Alex and Sriram 
 * ICS4U0 - A 
 * March 18, 2014          
 * Sayed, A.          
 *                          
 * SLSS Library Program v1.0                                                   
 * This program features a library system, which allows students to checkout and return books, calculates fines based on late days and lost books, takes into 
 * account deleted books, and allows the user to add and search books. In addition, users can request a search to see which books are better based on star 
 * rating, and can view a list of all the books written by a certain author or those that belong in a certain category. The system is also based on realism,  
 * and does not allow students to checkout more than 3 books, does not allow them to checkout books if they have more than $5 in fines, and the user cannot 
 * search for or checkout a book that has been lost or deleted. One feature adds support for multiple copies of the same book, and the program makes this 
 * possible by taking into account an "identity number". Different copies of the same book would have the same ISBN, but every single book in the library 
 * system would have a different identity number, and in order to delete or declare a lost book (a specific copy), then the identity number can be input 
 * and that specific copy will be dealt with rather than the first copy found in the list of books. The identity number increases by 1 each time a book 
 * is successfully added, so no two books will have the same identity number. The identity number for a specific book can be searched up in the list of books 
 * in the books list menu, so that it can be input to complete a particular function. The GUI is designed to be easy-to-use and flows from one screen to  
 * the other in sequence with a pace the user requires. The program has also been thoroughly tested to get rid of an sudden glitches and errors that can  
 * cause the program to malfunction. HAPPY USING! 
 * 
 * Bugs List:
 * No bugs found so far (all the bugs found have been fixed)
 * 
 * Important Notice (before using the program): when dealing with multiple copies of the same book, it is recommended to enter not only the ISBN but also the
 * identity number of the book as well (the identity can be found by using the "list of all books" button)
 * This is because, if the identity is not entered, the program automatically assumes the user is talking about the first copy of the book found in the 
 * booksList array (library) 
 */
  
  
  
import java.applet.*; 
import java.awt.*; 
import java.awt.event.*; 
public class Interface_GUI extends Applet implements ActionListener{ 
    /** 
     *  
     */
  
    /* 
     * declaration of all of the buttons, text fields, book objects and corresponding arrays, student objects and corresponding arrays: all declared in  
     * in this section here. all of these variables are used in one way or another. 
     */
      
      
    //Declares constant values that are used for representing GUI statements and controlling logic in the actionEvent method, and these values are 
    //are for the return integers that are returned from the library methods. 
    final static int SUCCESS_MESSAGE=1; 
    final static int RETURN_VALUE_2=2; 
    final static int RETURN_VALUE_3=3; 
    final static int RETURN_VALUE_4=4; 
    final static int RETURN_VALUE_0=0; 
  
    Button checkout,returnToMainScreen,studentsList,booksList,returnBook,studentNameSubmit; 
    TextField mainScreenText,timePassedText,returnBookText,studentName,studentNumber,studentNumberReturn; 
    boolean checkoutScreen,returnScreen,mainScreen,checkoutErrorScreen,returnErrorScreen; 
    String bookISBN,returnBookISBN,bookTitle,returnBookTitle; 
    String bookTitleForReturn; 
    int statusOfCheckout,statusOfReturn; 
    Library_System library; 
    Books arbitraryBook; 
    TextField checkoutBookIdentity,returnBookIdentity; 
    Students arbitraryStu; 
    int identityTrackNumber; 
    Books returnArbBook; 
    Button searchBook, addBook,deleteBook,bookLost; 
    Button searchStudent,addStudent,deleteStudent; 
    TextField isbnForSearch, bookIdentityForSearch, isbnForAdd,authorForAdd,titleForAdd,categoryForAdd,costForAdd,starRatingForAdd,identityForDelete,isbnForDelete,isbnForLost,identityForLost; 
    TextField stuNumForSearch,stuFNameForAdd,stuLNameForAdd,stuNumForAdd,stuNumForDelete; 
    Boolean booksListScreen=false,studentsListScreen=false,bookSearchDispScreen=false,stuSearchDispScreen=false,deleteBookScreen=false,bookLostScreen=false; 
    Students arbStuForReturn; 
    String bookIdentityString2; 
    Button showAllStudents; 
    Button showAllBooks; 
    String isbnBookString; 
    Button returnToBooksListScreen; 
    Students arbStu3; 
    boolean up; 
    boolean stuDelete=false; 
    boolean down; 
    int caseForStuDelete; 
    Books []book; 
    int sizeOfBooks=0; 
    Books arbBookForSearch; 
    Students arbStuForAdd; 
    Boolean bookSearch=false,delBook=false; 
    Boolean allStuScreen=false; 
    int addStuStatus; 
    int addBookStatus; 
    Button getAllBooks; 
    Boolean allBooksScreen; 
    Button returnToStuListScreen; 
    int lostStatus; 
    Boolean addBookScreen; 
    Button payStuFines; 
    TextField stuNumForFines; 
    Boolean payStuFinesScreen =false; 
    Boolean stuFines; 
    int delBookStatus; 
    Boolean searchStu; 
    Books [] allBooks; 
    Button compareBooks; 
    Books betterBook; 
    TextField isbn1ForCompare,isbn2ForCompare; 
    Boolean compareBookScreen=false; 
    int stuFineStatus; 
    Books[]byAuthor; 
    Books[]byCategory; 
    Button getByAuthor,getByCategory; 
    Boolean authorScreen=false,categoryScreen=false; 
    TextField authorName,categoryName; 
    Students [] allStu; 
    Button UP,DOWN; 
    int y; 
    int timesPressed=0; 
    int yValForAllBooks=0; 
  
  
    boolean addStu=false; 
  
  
    public void init(){ //init method initializes the program and declares buttons and text fields, sets boundaries on the screen 
        //, sets screen sizes, and adds action listeners for all the buttons 
        setSize(500,500); 
        up=false; 
        down=false; 
  
        UP=new Button("Page Up"); 
        DOWN=new Button("Page Down"); 
        UP.setBounds(860,395,80,40); 
        DOWN.setBounds(860,450,80,40); 
        UP.addActionListener(this); 
        DOWN.addActionListener(this); 
  
  
  
        //buttons and text fields declared for the searching books by author and category sections of the program 
  
        getByAuthor=new Button("Get Books by Author"); 
        getByCategory=new Button("Get Books by Category"); 
        getByAuthor.addActionListener(this); 
        getByAuthor.setBounds(10,205,150,40); 
        getByCategory.addActionListener(this); 
        getByCategory.setBounds(10,310,150,40); 
        authorName=new TextField("Enter the name of the author"); 
        authorName.setBounds(10,175,230,20); 
        categoryName=new TextField("Enter the name of the category"); 
        categoryName.setBounds(10,280,230,20); 
  
        //buttons and text fields declared for comparing books using ISBNs to see which book has a higher star rating 
  
        compareBooks=new Button("Compare Books"); 
        compareBooks.addActionListener(this); 
        compareBooks.setBounds(690,330,150,40); 
        isbn1ForCompare=new TextField("Enter an ISBN to compare"); 
        isbn1ForCompare.setBounds(690,280,250,20); 
        isbn2ForCompare=new TextField("Enter another ISBN to compare"); 
        isbn2ForCompare.setBounds(690,300,250,20); 
  
        //button to display a list of all the books in the system on the screen 
  
        getAllBooks=new Button("List of all Books"); 
        getAllBooks.setBounds(740,415,200,50); 
        getAllBooks.addActionListener(this); 
  
        //button text field to take in student number input and pay off a student's fines 
  
        payStuFines=new Button("Pay Student's Fines"); 
        payStuFines.setBounds(670,100,150,40); 
        payStuFines.addActionListener(this); 
        stuNumForFines=new TextField("Enter student number for student paying fines"); 
        stuNumForFines.setBounds(670,50,270,40); 
  
        //button that returns from different to the students list menu screen 
  
        returnToStuListScreen=new Button("Return to Students List"); 
        returnToStuListScreen.setBounds(10,415,200,50); 
        returnToStuListScreen.addActionListener(this); 
  
        //buttons and text fields declared to delete books from the system and to display all the students in the system on the screen 
  
        isbnForDelete=new TextField("Enter the ISBN of the book to delete"); 
        identityForDelete=new TextField("Enter a book identity to delete (if applicable)"); 
        isbnForDelete.setBounds(365,280,250,20); 
        identityForDelete.setBounds(365,300,250,20); 
        showAllStudents=new Button("List of all Students"); 
        showAllStudents.setBounds(740,415,200,50); 
  
        //======================================================================== 
        /* 
         * buttons and text fields which take input for the different requirements for a book and ultimately add the book to the system 
         */
  
        addBook=new Button("Add Book"); 
        addBook.setBounds(365,230,150,40); 
        isbnForAdd=new TextField("Enter the ISBN of the book"); 
        isbnForAdd.setBounds(365,110,250,20); 
        authorForAdd=new TextField("Enter the author of the book"); 
        authorForAdd.setBounds(365,80,250,20); 
        titleForAdd=new TextField("Enter the title of the book"); 
        titleForAdd.setBounds(365,50,250,20); 
        categoryForAdd=new TextField("Enter the category of the book"); 
        categoryForAdd.setBounds(365,140,250,20); 
        starRatingForAdd= new TextField("Enter the star rating of the book (1.00-5.00)"); 
        starRatingForAdd.setBounds(365,170,250,20); 
        costForAdd=new TextField("Enter the cost of the book"); 
        costForAdd.setBounds(365,200,250,20); 
  
        //======================================================================== 
  
        //buttons and text fields which take input info for lost books in order to take these books into consideration in the system 
  
        bookLost=new Button("Lost Book"); 
        bookLost.setBounds(690,110,150,40); 
        //stuNumForLost=new TextField("Enter student number of student who lost book"); 
        //stuNumForLost.setBounds(630,50,270,20); 
        isbnForLost=new TextField("Enter ISBN of the lost book"); 
        isbnForLost.setBounds(690,50,250,20); 
        identityForLost=new TextField("Enter identity for lost book (if applicable)"); 
        identityForLost.setBounds(690,80,250,20); 
  
  
        //======================================================================== 
  
        /* 
         * list of different text fields and buttons created that perform a variety of functions, from deleting students, adding students, and searching students 
         * to the button which returns to the books list screen 
         */
  
        showAllBooks=new Button("List of all Books"); 
        stuNumForDelete=new TextField("Enter a student number to delete"); 
        stuNumForDelete.setBounds(670,280,220,20); 
        deleteStudent= new Button("Delete a Student"); 
        deleteStudent.setBounds(670,310,150,40); 
        returnToBooksListScreen=new Button("Return to Books Screen"); 
        stuFNameForAdd=new TextField("Enter a first name for the student"); 
        stuLNameForAdd=new TextField("Enter a last name for the student"); 
        stuNumForAdd=new TextField("Enter a student number for the student"); 
        stuFNameForAdd.setBounds(365,50,250,40); 
        stuLNameForAdd.setBounds(365,100,250,40); 
        stuNumForAdd.setBounds(365,150,250,40); 
        returnToBooksListScreen.setBounds(10,415,200,50); 
        addStudent=new Button("Add Student"); 
        addStudent.setBounds(365,200,150,40); 
        stuNumForSearch=new TextField("Enter a student number to search for a student"); 
        stuNumForSearch.setBounds(10,50,300,40); 
        searchStudent=new Button("Search Student"); 
        searchStudent.setBounds(10,100,150,40); 
        searchStudent.addActionListener(this); 
        setLayout(null); 
        isbnForSearch=new TextField("Enter the ISBN of the book to search for"); 
        bookIdentityForSearch=new TextField("Enter the book identity to search (if applicable)"); 
        isbnForSearch.setBounds(10,50,250,20); 
        bookIdentityForSearch.setBounds(10,70,280,20); 
        searchBook=new Button("Search for Book"); 
        searchBook.setBounds(10,100,150,40); 
        searchBook.addActionListener(this); 
        returnToBooksListScreen.addActionListener(this); 
  
        deleteBook=new Button("Delete book"); 
        deleteBook.setBounds(365,330,150,40); 
  
  
        /* 
         * creates new library system called library with 5 random students and books (including all the information) and stores them in the library 
         * object. more books and/or students can be added on top of this up to a total of 4500 students and books (each). 
         */
  
        library = new Library_System(); 
        Books book1 = new Books ("Percy Jackson & the Olympians", "Rick Riordan", "Fantasy", 0000000000001, 4.18, 9.88, 1); 
        Books book2 = new Books ("Percy Jackson & the Olympians", "Rick Riordan", "Fantasy", 0000000000001, 4.18, 9.88, 2); 
        Books book3 = new Books ("And Then There Were None", "Agatha Christie", "Mystery", 0000000000002, 4.19, 14.70, 3); 
        Books book4 = new Books ("Life of Pi", "Yann Martel", "Adventure", 0000000000003, 3.86, 16.62, 4); 
        Books book5 = new Books ("How We Decide", "Jonah Lehrer", "Non Fiction", 0000000000004, 3.81, 21.75, 5); 
        Books book6 = new Books ("Infernal Devices", "K.W. Jeter", "Fantasy", 0000000000005, 3.38, 18.21, 6); 
        Books book7 = new Books ("Infernal Devices", "K.W. Jeter", "Fantasy", 0000000000005, 3.38, 18.21, 7); 
        Books book8 = new Books ("Infernal Devices", "K.W. Jeter", "Fantasy", 0000000000005, 3.38, 18.21, 8); 
        identityTrackNumber=9; 
        library.addBook(book1); 
        library.addBook(book2); 
        library.addBook(book3); 
        library.addBook(book4); 
        library.addBook(book5); 
        library.addBook(book6); 
        library.addBook(book7); 
        library.addBook(book8); 
        Students stu1 = new Students ("Alex", "Patel", 123456); 
        Students stu2 = new Students ("Sriram", "Madhusudhan", 456789); 
        Students stu3 = new Students ("Jainesh", "Kant", 789456); 
        Students stu4 = new Students ("Vasisht", "Chari", 456123); 
        Students stu5 = new Students ("Ramesh", "Raghunathan", 123789); 
        library.addStudent(stu1); 
        library.addStudent(stu2); 
        library.addStudent(stu3); 
        library.addStudent(stu4); 
        library.addStudent(stu5); 
        checkout = new Button ("Checkout a Book"); 
        checkout.setBounds(180,140,130,50); 
        checkout.addActionListener(this); 
        checkoutScreen = false; 
        returnBook=new Button("Return a Book"); 
        returnBook.setBounds(180,300,120,50); 
        returnBook.addActionListener(this); 
        mainScreen=true; 
  
  
        returnBookIdentity=new TextField("Enter the identity number of the book (if applicable)"); 
  
        returnBookIdentity.setBounds(50,240,400,20); 
  
        /* 
         *Continuation of the above declaration of new buttons and their corresponding action listeners  
         */
  
        studentsList=new Button ("Students List"); 
        booksList=new Button ("Books List"); 
        studentsList.setBounds(50,400,200,100); 
        booksList.setBounds(250,400,200,100); 
        studentsList.addActionListener(this); 
        booksList.addActionListener(this); 
        returnToMainScreen = new Button ("Return to Main Screen"); 
        returnToMainScreen.setBounds(10,415,200,50); 
        returnToMainScreen.addActionListener(this); 
        deleteStudent.addActionListener(this); 
        showAllStudents.addActionListener(this); 
        addStudent.addActionListener(this); 
        addBook.addActionListener(this); 
        bookLost.addActionListener(this); 
        deleteBook.addActionListener(this); 
        repaint(); //calls the paint method to update any graphics changes to the display screen 
  
        if(mainScreen==true){ //if statement which checks if the main screen is true before executing (in this case, due to the declaration in the init method 
            //main screen will always be true) 
            //add(checkoutBookIdentity); 
  
            /* 
             * section of code that adds the different required buttons and text fields to the screen as per necessity to display what is required for the user 
             * to view 
             */
  
            add(returnBookIdentity); 
            mainScreenText = new TextField ("Enter a book ISBN to checkout"); 
            timePassedText=new TextField("How many days have passed since this book was checked out?"); 
            studentNumber=new TextField("Enter the student number of the student checking out this book"); 
            //studentNumberReturn=new TextField("Enter the student number of the student returning this book"); 
            mainScreenText.setBounds(50,80,400,20); 
            //studentNumberReturn.setBounds(50,240,400,20); 
            timePassedText=new TextField("Enter the number of days passed since this book was checked out"); 
            timePassedText.setBounds(50,270,400,20); 
            add(timePassedText); 
            returnBookText=new TextField("Enter the book ISBN to return"); 
            studentNumber.setBounds(50,110,400,20); 
            returnBookText.setBounds(50,210,400,20); 
            add(returnBookText); 
            add(studentNumber); 
            add(mainScreenText); 
  
            add(checkout);//for example, adds the previously declared checkout button the main screen 
  
            add(returnBook); 
  
  
            add(studentsList); 
            add(booksList); 
  
  
            Students [] allStu; //declares new array of student objects for use in this class 
  
        } 
    } 
  
    /* 
     * method controlling all of the action events when a button is pressed and the corresponding actions are performed in conjunction with the 'if' statements 
     * controlling the flow of logic and events 
     *  
     */
  
    public void actionPerformed(ActionEvent evt) { 
  
        /* 
         * if statement that runs when the 'students list' button is pressed on the main screen 
         */
  
  
  
        if(evt.getSource()==returnToStuListScreen){ //if button return to students list screen is pressed, then this following series of code executes 
  
            /* 
             * standardized setting of the corresponding screens to false and true in order for the user to view what is required before the  
             * actual code executes(sets all boolean variable to what they should be when this button is pressed) 
             */
  
            stuSearchDispScreen=false; 
            mainScreen=false; 
            allBooksScreen=false; 
            checkoutScreen=false; 
            booksListScreen=false; 
            stuFines=false; 
            authorScreen=false; 
            searchStu=false; 
            bookSearchDispScreen=false; 
            returnScreen=false; 
            allStuScreen=false; 
            payStuFinesScreen=false; 
            addStu=false; 
            categoryScreen=false; 
            compareBookScreen=false; 
            delBook=false; 
            deleteBookScreen=false; 
            stuDelete=false; 
            yValForAllBooks=0; 
            returnErrorScreen=false; 
            checkoutErrorScreen=false; 
            studentsListScreen=true; 
  
            /* 
             * removes and adds buttons and text fields based on what the user should have access to in this screen and what should be viewable by them 
             */
  
            remove (returnToMainScreen); 
            remove(isbnForSearch); 
            remove(searchBook); 
            remove(isbnForDelete); 
            remove(identityForDelete); 
            remove(bookIdentityForSearch); 
            remove(mainScreenText); 
            remove(checkout); 
            remove(addBook); 
            remove(getByAuthor); 
            remove(UP); 
            remove(DOWN); 
            remove(getByCategory); 
            remove(authorName); 
            remove(categoryName); 
            remove(isbnForLost); 
            remove(identityForLost); 
            remove(searchStudent); 
            add(showAllStudents); 
            remove(studentsList); 
            remove(titleForAdd); 
            remove(authorForAdd); 
            remove(bookLost); 
            remove(categoryForAdd); 
            remove(isbnForAdd); 
            remove(costForAdd); 
            remove(starRatingForAdd); 
            remove(addBook); 
            remove(booksList); 
            remove(returnBookText); 
            remove(stuNumForSearch); 
            remove(stuFNameForAdd); 
            remove(stuLNameForAdd); 
            remove(stuNumForAdd); 
            remove(returnBook); 
            remove(payStuFines); 
            remove(stuNumForFines); 
            remove(deleteBook); 
            add(addStudent); 
            add(deleteStudent); 
            add(payStuFines); 
            add(stuNumForFines); 
            add(stuNumForDelete); 
            remove(studentNumber); 
            remove(returnBookIdentity); 
            remove(returnToBooksListScreen); 
            //add(studentNumberReturn); 
            remove(timePassedText); 
  
            remove(returnToStuListScreen); 
            add(returnToMainScreen); 
            add(returnToMainScreen); 
            add(stuNumForSearch); //search text field which takes input on the student number to search for from the students list screen 
            add(searchStudent); //button which searches for a student based on the inputed student number 
  
            add(stuFNameForAdd); 
            add(stuLNameForAdd); 
            add(stuNumForAdd); 
            add(stuNumForDelete); 
            add(showAllStudents); 
  
            add(addStudent); 
  
            add(deleteStudent); 
  
  
        } 
  
  
        else if (evt.getSource()==UP){ 
            yValForAllBooks+=5; 
        } 
        else if (evt.getSource()==DOWN){ 
            yValForAllBooks-=5; 
        } 
  
        else if (evt.getSource()==getByCategory){ //this following series of code executes when the get by category button is pressed to get all books by category 
            //in the books list menu 
  
            /* 
             * yet again sets all the screen an other booleans to a "standard" state so as to produce an effect of they should be before the code for this button 
             * executes 
             */
  
            stuSearchDispScreen=false; 
            mainScreen=false; 
            allBooksScreen=false; 
            checkoutScreen=false; 
            booksListScreen=true; 
            stuFines=false; 
            searchStu=false; 
            authorScreen=false; 
            bookSearchDispScreen=false; 
            returnScreen=false; 
            allStuScreen=false; 
            payStuFinesScreen=false; 
            addStu=false; 
            compareBookScreen=false; 
            delBook=false; 
            categoryScreen=false; 
            deleteBookScreen=false; 
            stuDelete=false; 
            returnErrorScreen=false; 
            checkoutErrorScreen=false; 
            studentsListScreen=false; 
  
            /* 
             * first if statement will execute only if the text in the category name text input is not the same as the text that was already there (basically, dummy-proofing) 
             * next if statement will only execute if the method "library.getBooksByCategory" does not return a null value: when it executes, the new array 
             * declared above called "byCategory" will be declared as to the size of the array returned from the method and then equaled to it 
             */
  
  
            if(!(categoryName.getText().equalsIgnoreCase("Enter the name of the category"))){ 
                if(library.getBooksByCategory(categoryName.getText())!=null){ 
                    byCategory=new Books[library.getBooksByCategory(categoryName.getText()).length]; 
                    add(UP); 
                    add(DOWN); 
                } 
                byCategory=library.getBooksByCategory(categoryName.getText()); 
  
                //sets appropriate screens true and false to display what is required on the screen 
  
                categoryScreen=true; 
                stuSearchDispScreen=false; 
                mainScreen=false; 
                allBooksScreen=false; 
                checkoutScreen=false; 
                booksListScreen=false; 
                stuFines=false; 
                searchStu=false; 
                authorScreen=false; 
                bookSearchDispScreen=false; 
                returnScreen=false; 
                allStuScreen=false; 
                payStuFinesScreen=false; 
                addStu=false; 
                compareBookScreen=false; 
                delBook=false; 
  
                deleteBookScreen=false; 
                stuDelete=false; 
                returnErrorScreen=false; 
                checkoutErrorScreen=false; 
                studentsListScreen=false; 
  
                //removes and adds what is appropriate to display the necessary information on the screen 
  
                remove(bookIdentityForSearch); 
                remove(isbnForDelete); 
                remove(identityForDelete); 
                remove(deleteBook); 
                remove (mainScreenText); 
                remove (checkout); 
                remove(bookLost); 
                remove(addBook); 
  
                remove(getByAuthor); 
                remove(getByCategory); 
                remove(authorName); 
                remove(categoryName); 
                remove(isbnForLost); 
                remove(getAllBooks); 
                remove(identityForLost); 
                remove(studentsList); 
                remove(booksList); 
                remove(isbnForAdd); 
                remove(titleForAdd); 
                remove(isbn1ForCompare); 
                remove(isbn2ForCompare); 
                remove(compareBooks); 
  
                remove(authorForAdd); 
                remove(categoryForAdd); 
                remove(costForAdd); 
                remove(starRatingForAdd); 
                //remove(stuNumForLost); 
                remove(isbnForLost); 
                remove(identityForLost); 
                remove(returnBookText); 
                //remove(searchStudent); 
                remove(returnBook);  
                remove(stuNumForSearch); 
                remove(returnBookIdentity); 
                remove(studentNumber);  
                remove(timePassedText); 
                remove(returnToMainScreen); 
                remove(isbnForSearch); 
                remove(searchBook); 
                remove(bookIdentityForSearch); 
                add(returnToBooksListScreen); 
  
            } 
  
  
  
        } 
  
        else if(evt.getSource()==getByAuthor){ //if the button get books by author is pressed, then the following code executes 
  
            //same as above buttons 
  
            stuSearchDispScreen=false; 
            mainScreen=false; 
            allBooksScreen=false; 
            checkoutScreen=false; 
            booksListScreen=true; 
            stuFines=false; 
            searchStu=false; 
            authorScreen=false; 
            bookSearchDispScreen=false; 
            returnScreen=false; 
            allStuScreen=false; 
            payStuFinesScreen=false; 
            addStu=false; 
            compareBookScreen=false; 
            delBook=false; 
            categoryScreen=false; 
            deleteBookScreen=false; 
            stuDelete=false; 
            returnErrorScreen=false; 
            checkoutErrorScreen=false; 
            studentsListScreen=false; 
  
            //same concept as buttons above: if statement control and the flow of logic and prevent "null" values from disrupting the program and ruining what the  
            //user can view 
  
            if(!(authorName.getText().equalsIgnoreCase("Enter the name of the author"))){ 
                if(library.getBooksByAuthor(authorName.getText())!=null){ 
                    byAuthor=new Books[library.getBooksByAuthor(authorName.getText()).length]; 
                } 
                byAuthor=library.getBooksByAuthor(authorName.getText()); 
  
                //same as above button with the addition and removal of buttons/text fields and the display of screen booleans 
  
                stuSearchDispScreen=false; 
                mainScreen=false; 
                allBooksScreen=false; 
                checkoutScreen=false; 
                booksListScreen=false; 
                stuFines=false; 
                searchStu=false; 
                authorScreen=true; 
                bookSearchDispScreen=false; 
                returnScreen=false; 
                allStuScreen=false; 
                payStuFinesScreen=false; 
                addStu=false; 
                compareBookScreen=false; 
                categoryScreen=false; 
                delBook=false; 
                deleteBookScreen=false; 
                stuDelete=false; 
                returnErrorScreen=false; 
                checkoutErrorScreen=false; 
                studentsListScreen=false; 
  
                remove(bookIdentityForSearch); 
                remove(isbnForDelete); 
                remove(identityForDelete); 
                remove(deleteBook); 
                remove (mainScreenText); 
                remove (checkout); 
                remove(bookLost); 
                remove(addBook); 
  
                remove(getByAuthor); 
                remove(getByCategory); 
                remove(authorName); 
                remove(categoryName); 
                remove(isbnForLost); 
                remove(getAllBooks); 
                remove(identityForLost); 
                remove(studentsList); 
                remove(booksList); 
                remove(isbnForAdd); 
                remove(titleForAdd); 
                add(UP); 
                add(DOWN); 
                remove(isbn1ForCompare); 
                remove(isbn2ForCompare); 
                remove(compareBooks); 
                remove(authorForAdd); 
                remove(categoryForAdd); 
                remove(costForAdd); 
                remove(starRatingForAdd); 
                //remove(stuNumForLost); 
                remove(isbnForLost); 
                remove(identityForLost); 
                remove(returnBookText); 
                //remove(searchStudent); 
                remove(returnBook);  
                remove(stuNumForSearch); 
                remove(returnBookIdentity); 
                remove(studentNumber);  
                remove(timePassedText); 
                remove(returnToMainScreen); 
                remove(isbnForSearch); 
                remove(searchBook); 
                remove(bookIdentityForSearch); 
                add(returnToBooksListScreen); 
  
            } 
  
  
  
        } 
  
  
        else if(evt.getSource()==compareBooks){ //this following code executes if the compare books button is pressed 
  
            stuSearchDispScreen=false; 
            mainScreen=false; 
            allBooksScreen=false; 
            checkoutScreen=false; 
            booksListScreen=true; 
            stuFines=false; 
            authorScreen=false; 
            searchStu=false; 
            bookSearchDispScreen=false; 
            returnScreen=false; 
            allStuScreen=false; 
            categoryScreen=false; 
            payStuFinesScreen=false; 
            addStu=false; 
            compareBookScreen=false; 
            delBook=false; 
            deleteBookScreen=false; 
            stuDelete=false; 
            returnErrorScreen=false; 
            checkoutErrorScreen=false; 
            studentsListScreen=false; 
  
            /* 
             * if statement will execute if the text in the respective input text fields are equivalent to what was already previously set to be there 
             * essentially, this if statement makes sure nothing happens if the user presses the button without inputting anything  
             */
  
            if(isbn1ForCompare.getText().equalsIgnoreCase("Enter an ISBN to compare")||isbn2ForCompare.getText().equalsIgnoreCase("Enter another ISBN to compare")){ 
  
                //screen and buttons/text fields stays the same as above (no input from the user) 
  
                stuSearchDispScreen=false; 
                mainScreen=false; 
                allBooksScreen=false; 
                checkoutScreen=false; 
                booksListScreen=true; 
                stuFines=false; 
                authorScreen=false; 
                searchStu=false; 
                bookSearchDispScreen=false; 
                returnScreen=false; 
                allStuScreen=false; 
                payStuFinesScreen=false; 
                addStu=false; 
                categoryScreen=false; 
                compareBookScreen=false; 
                delBook=false; 
                deleteBookScreen=false; 
                stuDelete=false; 
                returnErrorScreen=false; 
                checkoutErrorScreen=false; 
                studentsListScreen=false; 
            } 
            else{ 
  
                //===================================================== 
  
                //example of tester code: the if statement was put into place in order to filter out "null" statements, but the program worked without the if 
                //statements, rendering it useless 
  
                //if((library.compareBooks(Integer.parseInt(isbn1ForCompare.getText()), Integer.parseInt(isbn2ForCompare.getText())))!=null){ 
                //==================================================== 
  
                //finds which is better book through the method "library.compareBooks" and equals that to the Books object "betterBook" 
  
                betterBook=library.compareBooks(Integer.parseInt(isbn1ForCompare.getText()), Integer.parseInt(isbn2ForCompare.getText())); 
  
                //} 
  
                /* 
                 * sets screens and buttons/text fields according to what is needed to be displayed on the screen for the user when the above 
                 * code executes 
                 */
  
                stuSearchDispScreen=false; 
                mainScreen=false; 
                allBooksScreen=false; 
                checkoutScreen=false; 
                booksListScreen=false; 
                stuFines=false; 
                searchStu=false; 
                bookSearchDispScreen=false; 
                returnScreen=false; 
                authorScreen=false; 
                categoryScreen=false; 
                allStuScreen=false; 
                payStuFinesScreen=false; 
                addStu=false; 
                compareBookScreen=true; 
                delBook=false; 
                deleteBookScreen=false; 
                stuDelete=false; 
                returnErrorScreen=false; 
                checkoutErrorScreen=false; 
                studentsListScreen=false; 
  
                remove(bookIdentityForSearch); 
                remove(isbnForDelete); 
                remove(identityForDelete); 
                remove(deleteBook); 
                remove (mainScreenText); 
                remove (checkout); 
                remove(bookLost); 
                remove(addBook); 
  
                remove(getByAuthor); 
                remove(getByCategory); 
                remove(authorName); 
                remove(categoryName); 
                remove(isbnForLost); 
                remove(getAllBooks); 
                remove(identityForLost); 
                remove(studentsList); 
                remove(booksList); 
                remove(isbnForAdd); 
                remove(titleForAdd); 
                remove(isbn1ForCompare); 
                remove(isbn2ForCompare); 
                remove(compareBooks); 
                remove(authorForAdd); 
                remove(categoryForAdd); 
                remove(costForAdd); 
                remove(starRatingForAdd); 
                //remove(stuNumForLost); 
                remove(isbnForLost); 
                remove(identityForLost); 
                remove(returnBookText); 
                //remove(searchStudent); 
                remove(returnBook);  
                remove(stuNumForSearch); 
                remove(returnBookIdentity); 
                remove(studentNumber);  
                remove(timePassedText); 
                remove(returnToMainScreen); 
                remove(isbnForSearch); 
                remove(searchBook); 
                remove(bookIdentityForSearch); 
                add(returnToBooksListScreen); 
  
            } 
  
  
        } 
  
  
        else if(evt.getSource()==payStuFines){//following code executes if the pay student fines button is pressed 
            allBooksScreen=false; 
            stuSearchDispScreen=false; 
            mainScreen=false; 
            checkoutScreen=false; 
            booksListScreen=false; 
            bookSearchDispScreen=false; 
            returnScreen=false; 
            payStuFinesScreen=false; 
            allStuScreen=false; 
            addStu=false; 
            payStuFinesScreen=false; 
            delBook=false; 
            stuFines=false; 
            deleteBookScreen=false; 
            stuDelete=false; 
            returnErrorScreen=false; 
            checkoutErrorScreen=false; 
            studentsListScreen=true; 
  
            //if the student number text that originally present is still there(i.e no input from user) then don't do anything (dummy-proofing) 
  
            if(stuNumForFines.getText().equalsIgnoreCase("Enter student number for student paying fines")){ 
                allBooksScreen=false; 
                stuSearchDispScreen=false; 
                mainScreen=false; 
                checkoutScreen=false; 
                booksListScreen=false; 
                bookSearchDispScreen=false; 
                returnScreen=false; 
                payStuFinesScreen=false; 
                allStuScreen=false; 
                compareBookScreen=false; 
                addStu=false; 
                stuFines=false; 
                categoryScreen=false; 
                authorScreen=false; 
                payStuFinesScreen=false; 
                delBook=false; 
                searchStu=false; 
                deleteBookScreen=false; 
                stuDelete=false; 
                returnErrorScreen=false; 
                checkoutErrorScreen=false; 
                studentsListScreen=true; 
            } 
            else{ //if the above if statement is false then this code executes 
  
                //method "library.resetStuFines" is called upon with the inputs from the user and pays off the student's fines by converting the string text field 
                //input to an integer using the "Integer.parseInt" method 
  
                stuFineStatus=library.resetStuFines(Integer.parseInt(stuNumForFines.getText())); 
  
                //sets screens accordingly 
  
                allBooksScreen=false; 
                stuSearchDispScreen=true; 
                mainScreen=false; 
                checkoutScreen=false; 
                booksListScreen=false; 
                bookSearchDispScreen=false; 
                returnScreen=false; 
                payStuFinesScreen=false; 
                authorScreen=false; 
                allStuScreen=false; 
                addStu=false; 
                compareBookScreen=false; 
                searchStu=false; 
                categoryScreen=false; 
                stuFines=true; 
                stuNumForFines.setText("Enter student number for student paying fines"); //resets text field to original state 
                payStuFinesScreen=false; 
                delBook=false; 
                deleteBookScreen=false; 
                stuDelete=false; 
                returnErrorScreen=false; 
                checkoutErrorScreen=false; 
                studentsListScreen=false; 
  
  
            } 
        } 
  
        else if(evt.getSource()==getAllBooks){ //executes if get all books button is pressed in books list menu 
  
            //sets screens accordingly 
  
            allBooksScreen=true; 
            stuSearchDispScreen=false; 
            mainScreen=false; 
            checkoutScreen=false; 
            categoryScreen=false; 
            booksListScreen=false; 
            bookSearchDispScreen=false; 
            returnScreen=false; 
            compareBookScreen=false; 
            payStuFinesScreen=false; 
            authorScreen=false; 
            allStuScreen=false; 
            addStu=false; 
            searchStu=false; 
            payStuFinesScreen=false; 
            delBook=false; 
            stuFines=false; 
            deleteBookScreen=false; 
            stuDelete=false; 
            returnErrorScreen=false; 
            checkoutErrorScreen=false; 
            studentsListScreen=false; 
  
            //sets size of array to the size of array of books returned and equals this array to that returned array in order to display it to the user, using the  
            //"library.getBook" method which gets all of the books in the system 
  
            allBooks=new Books[library.getBook().length]; 
            allBooks=library.getBook(); 
  
            //removes and adds buttons/text fields accordingly 
            remove(bookIdentityForSearch); 
            remove(isbnForDelete); 
            remove(identityForDelete); 
            remove(deleteBook); 
            remove (mainScreenText); 
            remove (checkout); 
            remove(bookLost); 
            remove(addBook); 
  
            remove(getByAuthor); 
            remove(getByCategory); 
            remove(authorName); 
            remove(categoryName); 
            remove(isbnForLost); 
            remove(getAllBooks); 
            remove(identityForLost); 
            remove(studentsList); 
            remove(booksList); 
            remove(isbnForAdd); 
            remove(titleForAdd); 
            add(UP); 
            add(DOWN); 
            remove(isbn1ForCompare); 
            remove(isbn2ForCompare); 
            remove(compareBooks); 
            remove(authorForAdd); 
            remove(categoryForAdd); 
            remove(costForAdd); 
            remove(starRatingForAdd); 
            //remove(stuNumForLost); 
            remove(isbnForLost); 
            remove(identityForLost); 
            remove(returnBookText); 
            //remove(searchStudent); 
            remove(returnBook);  
            remove(stuNumForSearch); 
            remove(returnBookIdentity); 
            remove(studentNumber);  
            remove(timePassedText); 
            remove(returnToMainScreen); 
            remove(isbnForSearch); 
            remove(searchBook); 
            remove(bookIdentityForSearch); 
            add(returnToBooksListScreen); 
  
  
        } 
  
  
        /* 
         * The delete book method works based on the assumption that the user wants to delete a book instantaneously and deal with the possession of the book 
         * later. If the book that is to be deleted is checked out, then the system 'force-returns' the book and then deletes the book from the array of  
         * books. This is a design choice, and this choice is opposed to making the librarian wait until the book is returned before deleting it. It 
         * instantaneously gets rid of the book from the system. 
         */
  
        else if(evt.getSource()==deleteBook){ //executes following code if delete book button is pressed 
  
  
            stuSearchDispScreen=false; 
            mainScreen=false; 
            checkoutScreen=false; 
            booksListScreen=true; 
            bookSearchDispScreen=false; 
            returnScreen=false; 
            allBooksScreen=false; 
            stuFines=false; 
            searchStu=false; 
            categoryScreen=false; 
            allStuScreen=false; 
            authorScreen=false; 
            compareBookScreen=false; 
            payStuFinesScreen=false; 
            addStu=false; 
            delBook=false; 
            deleteBookScreen=false; 
            stuDelete=false; 
            returnErrorScreen=false; 
            checkoutErrorScreen=false; 
            studentsListScreen=false; 
  
            //same concept as above buttons: does not change screens (i.e. does nothing) if the original text is still in place (i.e. no input) 
  
            if ((identityForDelete.getText()).equalsIgnoreCase("Enter a book identity to delete (if applicable)")&&(isbnForDelete.getText().equalsIgnoreCase("Enter the ISBN of the book to delete"))){ 
  
                delBook=false; 
                stuSearchDispScreen=false; 
                mainScreen=false; 
                allStuScreen=false; 
                checkoutScreen=false; 
                booksListScreen=true; 
                bookSearchDispScreen=false; 
                compareBookScreen=false; 
                returnScreen=false; 
                authorScreen=false; 
                categoryScreen=false; 
                stuFines=false; 
                searchStu=false; 
                payStuFinesScreen=false; 
                addStu=false; 
                deleteBookScreen=false; 
                delBook=true; 
                allBooksScreen=false; 
                stuDelete=false; 
                returnErrorScreen=false; 
                checkoutErrorScreen=false; 
                studentsListScreen=false; 
  
            } 
  
            //if only the identity is not input by the user (as it is not a required field unless there are multiple copies of a book) then the following code executes 
  
            else if((identityForDelete.getText()).equalsIgnoreCase("Enter a book identity to delete (if applicable)")){ 
  
                /* 
                 * the "library.deleteBook" method is called taking only the ISBN input from the user in order to delete the book 
                 */
  
                delBookStatus=library.deleteBook(Integer.parseInt(isbnForDelete.getText())); 
                //=========================================== 
  
                //example of testing and debugging errors by displaying outputs on another console to see if certain pieces of code execute 
                //System.out.println(delBookStatus); 
                //========================================== 
  
  
                //sets screens and buttons/text fields accordingly 
                delBook=true; 
                stuSearchDispScreen=false; 
                mainScreen=false; 
                checkoutScreen=false; 
                categoryScreen=false; 
                compareBookScreen=false; 
                booksListScreen=false; 
                authorScreen=false; 
                allBooksScreen=false; 
                searchStu=false; 
                bookSearchDispScreen=false; 
                returnScreen=false; 
                payStuFinesScreen=false; 
                addStu=false; 
                allStuScreen=false; 
                stuFines=false; 
                deleteBookScreen=true; 
                delBook=true; 
                stuDelete=false; 
                returnErrorScreen=false; 
                checkoutErrorScreen=false; 
                studentsListScreen=false; 
  
  
                remove(bookIdentityForSearch); 
                remove(isbnForDelete); 
                remove(identityForDelete); 
                remove(deleteBook); 
                remove (mainScreenText); 
                remove (checkout); 
                remove(bookLost); 
                remove(addBook); 
  
                remove(getByAuthor); 
                remove(getByCategory); 
                remove(authorName); 
                remove(categoryName); 
                remove(isbnForLost); 
                remove(getAllBooks); 
                remove(identityForLost); 
                remove(studentsList); 
                remove(booksList); 
                remove(isbnForAdd); 
                remove(titleForAdd); 
  
                remove(isbn1ForCompare); 
                remove(isbn2ForCompare); 
                remove(compareBooks); 
                remove(authorForAdd); 
                remove(categoryForAdd); 
                remove(costForAdd); 
                remove(starRatingForAdd); 
                //remove(stuNumForLost); 
                remove(isbnForLost); 
                remove(identityForLost); 
                remove(returnBookText); 
                //remove(searchStudent); 
                remove(returnBook);  
                remove(stuNumForSearch); 
                remove(returnBookIdentity); 
                remove(studentNumber);  
                remove(timePassedText); 
                remove(returnToMainScreen); 
                remove(isbnForSearch); 
                remove(searchBook); 
                remove(bookIdentityForSearch); 
                add(returnToBooksListScreen); 
  
            } 
  
  
            else{ 
                //if there is text input in both text fields then the method which uses both an identity number and an ISBN number to delete a book is used 
                delBookStatus=library.deleteBook(Integer.parseInt(isbnForDelete.getText()),Integer.parseInt(identityForDelete.getText())); 
  
                //sets screens and buttons accordingly 
  
                delBook=true; 
                stuSearchDispScreen=false; 
                mainScreen=false; 
                checkoutScreen=false; 
                booksListScreen=false; 
                allStuScreen=false; 
                bookSearchDispScreen=false; 
                returnScreen=false; 
                categoryScreen=false; 
                addStu=false; 
                delBook=true; 
                compareBookScreen=false; 
                payStuFinesScreen=false; 
                allBooksScreen=false; 
                authorScreen=false; 
                deleteBookScreen=true; 
                searchStu=false; 
                stuFines=false; 
                stuDelete=false; 
                returnErrorScreen=false; 
                checkoutErrorScreen=false; 
                studentsListScreen=false; 
                remove(bookIdentityForSearch); 
                remove(isbnForDelete); 
                remove(identityForDelete); 
                remove(deleteBook); 
                remove (mainScreenText); 
                remove (checkout); 
                remove(bookLost); 
                remove(addBook); 
  
                remove(getByAuthor); 
                remove(getByCategory); 
                remove(authorName); 
                remove(categoryName); 
                remove(isbnForLost); 
                remove(getAllBooks); 
                remove(identityForLost); 
                remove(studentsList); 
                remove(booksList); 
                remove(isbnForAdd); 
                remove(titleForAdd); 
  
                remove(isbn1ForCompare); 
                remove(isbn2ForCompare); 
                remove(compareBooks); 
                remove(authorForAdd); 
                remove(categoryForAdd); 
                remove(costForAdd); 
                remove(starRatingForAdd); 
                //remove(stuNumForLost); 
                remove(isbnForLost); 
                remove(identityForLost); 
                remove(returnBookText); 
                //remove(searchStudent); 
                remove(returnBook);  
                remove(stuNumForSearch); 
                remove(returnBookIdentity); 
                remove(studentNumber);  
                remove(timePassedText); 
                remove(returnToMainScreen); 
                remove(isbnForSearch); 
                remove(searchBook); 
                remove(bookIdentityForSearch); 
                add(returnToBooksListScreen); 
            } 
  
  
        } 
  
  
  
        if(evt.getSource()==deleteStudent){ //following code executes if the delete student button is pressed 
            stuSearchDispScreen=false; 
            mainScreen=false; 
            checkoutScreen=false; 
            booksListScreen=false; 
            bookSearchDispScreen=false; 
            returnScreen=false; 
            authorScreen=false; 
            payStuFinesScreen=false; 
            addStu=false; 
            searchStu=false; 
            compareBookScreen=false; 
            categoryScreen=false; 
            allBooksScreen=false; 
            stuFines=false; 
            allStuScreen=false; 
            delBook=false; 
            stuDelete=false; 
            returnErrorScreen=false; 
            checkoutErrorScreen=false; 
            studentsListScreen=true; 
  
            if(!((stuNumForDelete.getText()).equalsIgnoreCase("Enter a student number to delete"))){ //if statement only executes if the text field has input from the  
                //user 
  
                //using the method "library.deleteStudent" and the input from the text field (user) the student is deleted 
  
                caseForStuDelete=library.deleteStudent(Integer.parseInt(stuNumForDelete.getText())); 
  
                //sets screen accordingly 
  
                stuDelete=true; 
                stuNumForDelete.setText("Enter a student number to delete"); 
                stuSearchDispScreen=true; 
                mainScreen=false; 
                checkoutScreen=false; 
                authorScreen=false; 
                allStuScreen=false; 
                payStuFinesScreen=false; 
                booksListScreen=false; 
                bookSearchDispScreen=false; 
                searchStu=false; 
                compareBookScreen=false; 
                returnScreen=false; 
                allBooksScreen=false; 
                categoryScreen=false; 
                stuFines=false; 
                addStu=false; 
                delBook=false; 
                returnErrorScreen=false; 
                checkoutErrorScreen=false; 
                studentsListScreen=false; 
  
  
            } 
  
  
        } 
  
        else if(evt.getSource()==searchStudent){  //if search student button is pressed in the students list screen, code executes 
  
            mainScreen=false; 
            checkoutScreen=false; 
            booksListScreen=false; 
            bookSearchDispScreen=false; 
            returnScreen=false; 
            allBooksScreen=false; 
            addStu=false; 
            allStuScreen=false; 
            searchStu=false; 
            compareBookScreen=false; 
            authorScreen=false; 
            categoryScreen=false; 
            payStuFinesScreen=false; 
  
            stuFines=false; 
            stuDelete=false; 
            stuSearchDispScreen=false; 
            returnErrorScreen=false; 
            checkoutErrorScreen=false; 
            studentsListScreen=true; 
            book=null; //sets the book array value to null so that it "forgets" the previous student's books 
            if(!(stuNumForSearch.getText().equalsIgnoreCase("Enter a student number to search for a student"))){ //if user input is entered and the original text 
                //is not present 
  
                /* 
                 *gets the student number for the student to search and finds the students then uses the method "getBooksOut" method to get that student's books 
                 *and store it in an array "book" to display to the user  
                 */
  
                int stuNumInt=Integer.parseInt(stuNumForSearch.getText()); 
                arbStu3=library.getStudent(stuNumInt); 
                if(arbStu3!=null){ 
                    if((arbStu3.getBooksOut())>0){ 
                        book=new Books [(arbStu3.getStuBooks()).length]; 
                        book=arbStu3.getStuBooks(); 
                        sizeOfBooks=book.length;         
  
                    } 
                } 
  
                //sets screens accordingly 
                stuSearchDispScreen=true; 
                allStuScreen=false; 
                mainScreen=false; 
                checkoutScreen=false; 
                authorScreen=false; 
                booksListScreen=false; 
                searchStu=true; 
                bookSearchDispScreen=false; 
                returnScreen=false; 
                compareBookScreen=false; 
                categoryScreen=false; 
                payStuFinesScreen=false; 
                addStu=false; 
                allBooksScreen=false; 
                stuFines=false; 
                stuDelete=false; 
                returnErrorScreen=false; 
                checkoutErrorScreen=false; 
                studentsListScreen=false; 
  
                stuNumForSearch.setText("Enter a student number to search for a student"); 
  
  
            } 
  
  
        } 
        else if (evt.getSource()==addStudent){ //code executes when add student button is pressed 
  
            mainScreen=false; 
            checkoutScreen=false; 
            booksListScreen=false; 
            authorScreen=false; 
            bookSearchDispScreen=false; 
            returnScreen=false; 
            compareBookScreen=false; 
            addStu=false; 
            categoryScreen=false; 
            searchStu=false; 
            stuDelete=false; 
            payStuFinesScreen=false; 
            allBooksScreen=false; 
            stuFines=false; 
            allStuScreen=false; 
            stuSearchDispScreen=false; 
            returnErrorScreen=false; 
            checkoutErrorScreen=false; 
            studentsListScreen=true; 
            String fName=stuFNameForAdd.getText(); 
            String lName=stuLNameForAdd.getText(); 
  
            //does nothing if any of the text inputs are still in their original state (i.e. no user input) 
            if((fName.equals("Enter a first name for the student"))||((lName.equals("Enter a last name for the student")))||(stuNumForAdd.getText().equals("Enter a student number for the student"))){ 
                stuSearchDispScreen=false; 
                mainScreen=false; 
                checkoutScreen=false; 
                booksListScreen=false; 
                bookSearchDispScreen=false; 
                returnScreen=false; 
                authorScreen=false; 
                payStuFinesScreen=false; 
                searchStu=false; 
                stuDelete=false; 
                categoryScreen=false; 
                compareBookScreen=false; 
                allStuScreen=false; 
                allBooksScreen=false; 
                stuFines=false; 
                returnErrorScreen=false; 
                checkoutErrorScreen=false; 
                studentsListScreen=true; 
                addStu=false; 
  
            } 
            else{ //if there is input from user then the following code executes 
  
                //sets screens accordingly 
                stuSearchDispScreen=true; 
                mainScreen=false; 
                checkoutScreen=false; 
                booksListScreen=false; 
                bookSearchDispScreen=false; 
                searchStu=false; 
                allBooksScreen=false; 
                returnScreen=false; 
                stuFines=false; 
                authorScreen=false; 
                compareBookScreen=false; 
                allStuScreen=false; 
                categoryScreen=false; 
                returnErrorScreen=false; 
                checkoutErrorScreen=false; 
                payStuFinesScreen=false; 
                studentsListScreen=false; 
                stuDelete=false; 
  
                //takes student information and adds it to the list of students using the "library.addStudent" method 
  
                int stuNum=Integer.parseInt(stuNumForAdd.getText()); 
                addStu=true; 
                arbStuForAdd=new Students(fName,lName,stuNum); 
                addStuStatus=library.addStudent(arbStuForAdd); 
                stuFNameForAdd.setText("Enter a first name for the student"); 
                stuLNameForAdd.setText("Enter a last name for the student"); 
                stuNumForAdd.setText("Enter a student number for the student"); 
            } 
        } 
  
        else if(evt.getSource()==studentsList){ //if the students list button is pressed then the following code executes 
  
            /* 
             * setting all of the screens appropriately to true and false so that the correct screen can be repainted on the screen, corresponding 
             * to the button pressed 
             */
  
  
            mainScreen=false; 
            checkoutScreen=false; 
            booksListScreen=false; 
            bookSearchDispScreen=false; 
            authorScreen=false; 
            searchStu=false; 
            returnScreen=false; 
            compareBookScreen=false; 
            allBooksScreen=false; 
            addStu=false; 
            stuFines=false; 
            payStuFinesScreen=false; 
            stuDelete=false; 
            categoryScreen=false; 
            allStuScreen=false; 
            stuSearchDispScreen=false; 
            returnErrorScreen=false; 
            checkoutErrorScreen=false; 
            studentsListScreen=true; 
  
  
            /* 
             * removes and adds the necessary buttons and text fields so as to keep what is necessary on the screen, to correspond to the button pressed 
             */
  
            remove (mainScreenText); //takes in the ISBN of the book in the main screen 
            remove (checkout); 
            remove(studentsList); //students list button on the main screen clicked to access the students list 
            remove(booksList); 
            remove(returnBookText); 
            remove(returnBook); //button clicked to return a book after filling out information 
            remove(studentNumber);  
            remove(returnBookIdentity); //text field taking input on the book identity number upon return of the book 
            //remove(studentNumberReturn); 
            remove(timePassedText); 
  
            remove(returnToBooksListScreen); 
            add(returnToMainScreen); 
            remove(isbn1ForCompare); 
            remove(isbn2ForCompare); 
            remove(compareBooks); 
            add(payStuFines); 
            add(stuNumForFines); 
            add(stuNumForSearch); //search text field which takes input on the student number to search for from the students list screen 
            add(searchStudent); //button which searches for a student based on the inputed student number 
  
            add(stuFNameForAdd); 
            add(stuLNameForAdd); 
            add(stuNumForAdd); 
            add(stuNumForDelete); 
            add(showAllStudents); 
  
            add(addStudent); 
  
            add(deleteStudent); 
  
  
  
            //stuNumForSearch.setText("Enter a student number to search for a student"); 
        } 
  
        /* 
         * this piece of code runs when the checkout button is pressed on the main screen 
         */
  
        else if (evt.getSource()==showAllStudents){ //if the show all students button, in the students list menu, is pressed then the following code executes 
  
            allStuScreen=true; 
            mainScreen=false; 
            checkoutScreen=false; 
            categoryScreen=false; 
            booksListScreen=false; 
            authorScreen=false; 
            bookSearchDispScreen=false; 
            returnScreen=false; 
            allBooksScreen=false; 
            stuDelete=false; 
            compareBookScreen=false; 
            payStuFinesScreen=false; 
            stuFines=false; 
            returnErrorScreen=false; 
            searchStu=false; 
            stuSearchDispScreen=false; 
            checkoutErrorScreen=false; 
            studentsListScreen=false; 
  
  
            //declares new Students objects array "allStu" to be size of array returned from method "library.getStudent" 
  
            allStu=new Students[(library.getStudent()).length]; 
            allStu=library.getStudent(); 
  
            //sets buttons and text fields accordingly on the screen 
  
            add(returnToStuListScreen); 
            remove(stuNumForSearch); //search text field which takes input on the student number to search for from the students list screen 
            remove(searchStudent); //button which searches for a student based on the inputed student number 
  
            remove(stuFNameForAdd); 
            remove(stuLNameForAdd); 
            remove(stuNumForAdd); 
            remove(stuNumForDelete); 
            remove(payStuFines); 
            add(UP); 
            add(DOWN); 
            remove(stuNumForFines); 
            remove(isbn1ForCompare); 
            remove(isbn2ForCompare); 
            remove(compareBooks); 
            remove(showAllStudents); 
            remove(returnToMainScreen); 
  
            remove(addStudent); 
  
            remove(deleteStudent); 
  
        } 
  
  
        else if(evt.getSource()==checkout){ //if button checkout is pressed, on the main screen, then the following code executes 
  
            bookISBN = mainScreenText.getText(); 
            String stuNumString = studentNumber.getText(); 
            //String identityNumString = checkoutIdentityNumber.getText(); 
            if(bookISBN.equals("Enter a book ISBN to checkout") || stuNumString.equals("Enter the student number of the student checking out this book")){ 
                /* 
                 * sets all of the necessary screens to true and false to display the correct screen corresponding to the button pressed 
                 */
                mainScreen=true; 
                checkoutScreen=false; 
                booksListScreen=false; 
                bookSearchDispScreen=false; 
                returnScreen=false; 
                stuDelete=false; 
                payStuFinesScreen=false; 
                searchStu=false; 
                stuFines=false; 
                compareBookScreen=false; 
                categoryScreen=false; 
                authorScreen=false; 
                allBooksScreen=false; 
                allStuScreen=false; 
                returnErrorScreen=false; 
                stuSearchDispScreen=false; 
                checkoutErrorScreen=false; 
                studentsListScreen=false; 
  
  
            } 
            else{ 
                int stuNum=Integer.parseInt(stuNumString); 
                int ISBN=Integer.parseInt(bookISBN); 
  
                statusOfCheckout = library.checkOut(stuNum,ISBN);//uses the library.checkOut method to checkout a book and the returned integer from the method 
                //is stored in a variable which keeps track of the status of the checkout (i.e. successful or not) to display respective messages to the user 
  
                //int identityNum=Integer.parseInt(identityNumString); 
  
  
  
                if(statusOfCheckout==SUCCESS_MESSAGE) //if this status is equal to 1 then the following code executes (1 means successful checkout) 
                { 
                    //sets screen, buttons, text fields, and variable information from text fields according to requirements to display to the user 
  
                    checkoutScreen=true; 
                    mainScreen=false; 
                    checkoutErrorScreen=false; 
                    returnErrorScreen=false; 
                    stuSearchDispScreen=false; 
                    bookSearchDispScreen=false; 
                    searchStu=false; 
                    booksListScreen=false; 
                    authorScreen=false; 
                    stuFines=false; 
                    categoryScreen=false; 
                    allStuScreen=false; 
                    payStuFinesScreen=false; 
                    stuDelete=false; 
                    allBooksScreen=false; 
                    compareBookScreen=false; 
                    returnScreen=false; 
                    studentsListScreen=false; 
  
                    //stores the necessary information from each text field in a variable for later use 
                    arbitraryBook = library.getBook(ISBN); 
                    arbitraryStu=library.getStudent(stuNum); 
                    bookTitle=arbitraryBook.getBookTitle(); 
  
                    //changes background color 
                    remove (mainScreenText); 
                    remove (checkout); 
                    remove(studentsList); 
                    remove(isbn1ForCompare); 
                    remove(isbn2ForCompare); 
                    remove(compareBooks); 
                    remove(booksList); 
                    remove(returnBookText); 
                    remove(returnBook);  
                    //remove(searchStudent); 
                    remove(studentNumber);  
                    remove(returnBookIdentity); 
                    remove(stuNumForSearch); 
                    //remove(studentNumberReturn); 
                    remove(timePassedText); 
                    remove(returnToBooksListScreen); 
                    add(returnToMainScreen); 
                } 
                else if(statusOfCheckout!=SUCCESS_MESSAGE) { //if the status for checkout is not equal to 1 then the following code executes 
  
                    //sets screens and buttons/text fields accordingly 
  
                    checkoutErrorScreen=true; 
                    checkoutScreen=false; 
                    mainScreen=false; 
                    stuSearchDispScreen=false; 
                    bookSearchDispScreen=false; 
                    returnScreen=false; 
                    compareBookScreen=false; 
                    allBooksScreen=false; 
                    stuFines=false; 
                    allStuScreen=false; 
                    categoryScreen=false; 
                    stuDelete=false; 
                    searchStu=false; 
                    studentsListScreen=false; 
                    authorScreen=false; 
                    returnErrorScreen=false; 
                    payStuFinesScreen=false; 
                    booksListScreen=false; 
                    remove (mainScreenText); 
                    remove (checkout); 
                    remove(studentsList); 
                    remove(booksList); 
                    remove(returnBookText); 
                    remove(returnBook);  
                    remove(isbn1ForCompare); 
                    remove(isbn2ForCompare); 
                    remove(compareBooks); 
                    //remove(searchStudent); 
                    remove(returnBookIdentity); 
                    remove(studentNumber);  
                    remove(stuNumForSearch); 
                    remove(returnToBooksListScreen); 
                    //remove(studentNumberReturn); 
                    remove(timePassedText); 
                    add(returnToMainScreen); 
                } 
            } 
        } 
        else if(evt.getSource()==returnToMainScreen){ //if return to main screen button is pressed then the following code is executed 
            //sets screens accordingly by setting every other screen to false and the mainScreen to true 
            //removes and adds necessary buttons and text fields to display main screen to the user 
            mainScreen=true; 
            checkoutScreen=false; 
            returnScreen=false; 
            checkoutErrorScreen=false; 
            returnErrorScreen=false; 
            stuDelete=false; 
            allStuScreen=false; 
            stuFines=false; 
            compareBookScreen=false; 
            payStuFinesScreen=false; 
            authorScreen=false; 
            allBooksScreen=false; 
            categoryScreen=false; 
            stuSearchDispScreen=false; 
            studentsListScreen=false; 
            searchStu=false; 
            bookSearchDispScreen=false; 
            booksListScreen=false; 
            remove (returnToMainScreen); 
            remove(isbnForSearch); 
            remove(searchBook); 
            remove(getAllBooks); 
            remove(isbn1ForCompare); 
            remove(isbn2ForCompare); 
            remove(compareBooks); 
            remove(isbn1ForCompare); 
            remove(isbn2ForCompare); 
            remove(getByAuthor); 
            remove(getByCategory); 
            remove(authorName); 
            remove(categoryName); 
            remove(compareBooks); 
            remove(isbnForDelete); 
            remove(identityForDelete); 
            remove(bookIdentityForSearch); 
            add(mainScreenText); 
            add(checkout); 
            remove(payStuFines); 
            remove(stuNumForFines); 
            remove(isbnForLost); 
            remove(identityForLost); 
            remove(searchStudent); 
            remove(showAllStudents); 
            add(studentsList); 
            remove(titleForAdd); 
            remove(authorForAdd); 
            remove(bookLost); 
            remove(categoryForAdd); 
            remove(isbnForAdd); 
            remove(costForAdd); 
            remove(starRatingForAdd); 
            remove(addBook); 
            add(booksList); 
            add(returnBookText); 
            remove(stuNumForSearch); 
            remove(stuFNameForAdd); 
            remove(stuLNameForAdd); 
            remove(stuNumForAdd); 
            add(returnBook); 
            remove(deleteBook); 
            remove(addStudent); 
            remove(deleteStudent); 
            remove(stuNumForDelete); 
            add(studentNumber); 
            add(returnBookIdentity); 
            remove(returnToBooksListScreen); 
            //add(studentNumberReturn); 
            add(timePassedText); 
  
            //sets textfields to their original state 
            mainScreenText.setText("Enter a book ISBN to checkout"); 
            studentNumber.setText("Enter the student number of the student checking out this book"); 
            timePassedText.setText("Enter the number of days passed since this book was checked out"); 
            returnBookText.setText("Enter the book ISBN to return"); 
            returnBookIdentity.setText("Enter the identity number of the book (if applicable)"); 
  
        } 
        else if(evt.getSource()==returnToBooksListScreen){ //if return to books list screen is pressed then the following code executes 
  
            //removes and adds necessary buttons/text fields to display the books list screen 
  
            remove (mainScreenText); 
            remove (checkout); 
            remove(studentsList); 
            remove(booksList); 
  
            remove(returnBookText); 
            remove(returnBook);  
            remove(UP); 
            remove(DOWN); 
            //remove(searchStudent); 
            remove(returnBookIdentity); 
            remove(studentNumber);  
            remove(stuNumForSearch); 
            remove(timePassedText); 
  
            add(getAllBooks); 
            add(addBook); 
            add(isbn1ForCompare); 
            add(isbn2ForCompare); 
            add(compareBooks); 
            add(isbnForAdd); 
            add(titleForAdd); 
            add(authorForAdd); 
            add(categoryForAdd); 
            add(costForAdd); 
            add(starRatingForAdd); 
            add(authorName); 
            add(categoryName); 
            add(getByAuthor); 
            add(getByCategory); 
            add(isbnForLost); 
            add(identityForLost); 
            //add(stuNumForLost); 
            add(bookLost); 
            add(returnToMainScreen); 
            add(isbnForSearch); 
            add(searchBook); 
            add(identityForDelete); 
            add(deleteBook); 
            add(isbnForDelete); 
            //sets text fields to their original state 
  
            isbnForSearch.setText("Enter the ISBN of the book to search for"); 
            bookIdentityForSearch.setText("Enter the book identity to search (if applicable)"); 
            identityForDelete.setText("Enter a book identity to delete (if applicable)"); 
            isbnForDelete.setText("Enter the ISBN of the book to delete"); 
            isbnForLost.setText("Enter ISBN of the lost book"); 
            identityForLost.setText("Enter identity for lost book (if applicable)"); 
            isbn1ForCompare.setText("Enter an ISBN to compare"); 
            isbn2ForCompare.setText("Enter another ISBN to compare"); 
            authorName.setText("Enter the name of the author"); 
            categoryName.setText("Enter the name of the category"); 
            add(bookIdentityForSearch); 
  
            remove(returnToBooksListScreen);     
  
            //sets all screens to false and the booksListScreen to true 
            yValForAllBooks=0; 
            bookSearchDispScreen=false; 
            mainScreen=false; 
            stuFines=false; 
            allStuScreen=false; 
            searchStu=false; 
            deleteBookScreen=false; 
            checkoutScreen=false; 
            studentsListScreen=false; 
            categoryScreen=false; 
            addBookScreen=false; 
            authorScreen=false; 
            compareBookScreen=false; 
            allBooksScreen=false; 
            returnScreen=false; 
            payStuFinesScreen=false; 
            bookLostScreen=false; 
            stuDelete=false; 
            stuSearchDispScreen=false; 
            checkoutErrorScreen=false; 
            returnErrorScreen=false; 
            booksListScreen=true; 
            repaint(); //calls the paint method to update any graphical changes to the GUI for the user 
        } 
        else if (evt.getSource()==returnBook){ //when the return book button is pressed, then the following code executes 
  
            //gets the necessary text from the required text fields 
            returnBookISBN=returnBookText.getText(); 
            //String stuNumText=studentNumberReturn.getText(); 
            String bookIdentityString=returnBookIdentity.getText(); 
            String timePassed=timePassedText.getText(); 
  
            //if the text for the ISBN is unchanged then essentially nothing happens (i.e. no user input) 
  
            if(returnBookISBN.equals("Enter the book ISBN to return")){ 
                mainScreen=true; 
                checkoutScreen=false; 
                bookSearchDispScreen=false; 
                returnScreen=false; 
                categoryScreen=false; 
                payStuFinesScreen=false; 
                searchStu=false; 
                compareBookScreen=false; 
                allBooksScreen=false; 
                allStuScreen=false; 
                authorScreen=false; 
                stuFines=false; 
                studentsListScreen=false; 
                booksListScreen=false; 
                stuSearchDispScreen=false; 
                checkoutErrorScreen=false; 
                returnErrorScreen=false; 
            } 
            else{ //if there is user input then the following code executes 
  
                //converts the required text into integers for use in the methods 
                int returnISBN=Integer.parseInt(returnBookISBN); 
  
                int timePassedInt = Integer.parseInt(timePassed); 
                Books arbBook = library.getBook(returnISBN); 
  
                //uses the methods "getBookStu" to get the student owner of the book, "getBookTitle" to get the title of the book, "library.returnBook" to actually return 
                //the book to the library, and "library.getBook" to get the book required 
                arbStuForReturn=arbBook.getBookStu(); 
                bookTitleForReturn=arbBook.getBookTitle(); 
  
                //executes if there is no user input for the identity number of the book (only required for multiple copies of a book) 
  
                if(bookIdentityString.equalsIgnoreCase("Enter the identity number of the book (if applicable)")){ 
                    statusOfReturn=library.returnBook(returnISBN, timePassedInt); //uses the time passed input (how many days have passed)  
                    //to calculate fines based on due dates and return days 
                    returnArbBook=library.getBook(returnISBN); 
                } 
                else{ 
  
                    //if there is input for the identity number then "library.returnBook" is used with the time passed, ISBN, and identity number: status of the return 
                    //is stored in a variable to display a message according to success or failure 
                    int bookIdentityNum=Integer.parseInt(bookIdentityString); 
                    statusOfReturn=library.returnBook(returnISBN, timePassedInt,bookIdentityNum); 
                    returnArbBook=library.getBook(returnISBN,bookIdentityNum); 
                } 
                if(statusOfReturn==SUCCESS_MESSAGE){ //if status is 1 (successful) then the following code executes 
  
                    //screens set accordingly 
                    mainScreen=false; 
                    checkoutScreen=false; 
                    returnScreen=true; 
                    allStuScreen=false; 
                    stuFines=false; 
                    checkoutErrorScreen=false; 
                    bookSearchDispScreen=false; 
                    stuDelete=false; 
                    searchStu=false; 
                    compareBookScreen=false; 
                    authorScreen=false; 
                    categoryScreen=false; 
                    payStuFinesScreen=false; 
                    allBooksScreen=false; 
                    stuSearchDispScreen=false; 
                    returnErrorScreen=false; 
                    booksListScreen=false; 
                    studentsListScreen=false; 
                    remove (mainScreenText); 
                    remove (checkout); 
                    remove(studentsList); 
                    remove(booksList); 
                    remove(returnBookText); 
                    remove(stuNumForSearch); 
                    remove(isbn1ForCompare); 
                    remove(isbn2ForCompare); 
                    remove(compareBooks); 
                    remove(returnBook);  
                    remove(getAllBooks); 
                    //remove(searchStudent); 
                    remove(returnBookIdentity); 
                    remove(studentNumber);  
                    remove(timePassedText); 
                    remove(returnToMainScreen); 
                    remove(isbnForSearch); 
                    remove(searchBook); 
                    remove(bookIdentityForSearch); 
                    add(returnToMainScreen); 
                } 
                else{ 
                    //if status is not 1 then the following code executes 
                    //screens and buttons/text fields set accordingly 
                    mainScreen=false; 
                    checkoutScreen=false; 
                    returnScreen=false; 
                    allStuScreen=false; 
                    payStuFinesScreen=false; 
                    checkoutErrorScreen=false; 
                    bookSearchDispScreen=false; 
                    compareBookScreen=false; 
                    returnErrorScreen=true; 
                    allBooksScreen=false; 
                    stuDelete=false; 
                    searchStu=false; 
                    categoryScreen=false; 
                    stuFines=false; 
                    authorScreen=false; 
                    stuSearchDispScreen=false; 
                    booksListScreen=false; 
                    studentsListScreen=false; 
                    remove (mainScreenText); 
                    remove (checkout); 
                    remove(studentsList); 
                    remove(booksList); 
                    remove(returnBookText); 
                    remove(returnBook);  
                    //remove(searchStudent); 
                    remove(returnBookIdentity); 
                    remove(studentNumber);  
                    remove(isbn1ForCompare); 
                    remove(isbn2ForCompare); 
                    remove(compareBooks); 
                    remove(timePassedText); 
                    remove(getAllBooks); 
                    remove(stuNumForSearch); 
                    remove(returnToMainScreen); 
                    remove(isbnForSearch); 
                    remove(searchBook); 
                    remove(bookIdentityForSearch); 
                    add(returnToMainScreen); 
                } 
            } 
        } 
        else if (evt.getSource()==booksList){ //if the books list button is pressed then the following code executes 
            setSize(950,500); //sets screen size to 1200px by 500px 
  
            //sets screens,buttons,and text fields accordingly 
            mainScreen=false; 
            checkoutScreen=false; 
            returnScreen=false; 
            stuDelete=false; 
            allBooksScreen=false; 
            payStuFinesScreen=false; 
            compareBookScreen=false; 
            stuFines=false; 
            categoryScreen=false; 
            searchStu=false; 
            addBookScreen=false; 
            authorScreen=false; 
            checkoutErrorScreen=false; 
            returnErrorScreen=false; 
            stuSearchDispScreen=false; 
            allStuScreen=false; 
            booksListScreen=true; 
            bookLostScreen=false; 
            bookSearchDispScreen=false; 
            studentsListScreen=false; 
            remove (mainScreenText); 
            remove (checkout); 
            remove(studentsList); 
            remove(booksList); 
            remove(returnBookText); 
  
            remove(returnBook);  
            remove(returnBookIdentity); 
            //remove(searchStudent); 
            remove(studentNumber);  
            remove(isbn1ForCompare); 
            remove(isbn2ForCompare); 
            remove(compareBooks); 
  
            remove(stuNumForSearch); 
            remove(timePassedText); 
            add(returnToMainScreen); 
            add(addBook); 
            add(getByAuthor); 
            add(getByCategory); 
            add(authorName); 
            add(categoryName); 
  
  
  
  
  
            add(isbnForAdd); 
            add(titleForAdd); 
            add(authorForAdd); 
            add(isbn1ForCompare); 
            add(isbn2ForCompare); 
            add(compareBooks); 
            add(isbnForLost); 
            add(identityForLost); 
            //add(stuNumForLost); 
            add(bookLost); 
            add(getAllBooks); 
            add(categoryForAdd); 
            add(costForAdd); 
            add(starRatingForAdd); 
            add(isbnForDelete); 
            add(identityForDelete); 
            add(isbnForSearch); 
            isbnForSearch.setText("Enter the ISBN of the book to search for"); 
            add(searchBook); 
            add(bookIdentityForSearch); 
            add(deleteBook); 
  
            bookIdentityForSearch.setText("Enter the book identity to search (if applicable)"); 
            remove(returnToBooksListScreen); 
  
        } 
  
  
        /* 
         * The lost book method works on the assumption that only the student can 'lose' a book. So, if a book that has been checked out has been lost, then 
         * the book is considered a lost book and the cost of the book is added as a fine to the student's account; however, if the book that is 
         * presumably lost has not been checked out by any student, then the system will not take the book into consideration as a lost book, but 
         * rather force the user to delete the book instead as it is not a student's fault the book has been misplaced. 
         */
  
        else if (evt.getSource()==bookLost){ //if book lost button is pressed then the following code executes 
  
            bookLostScreen=false; 
            mainScreen=false; 
            checkoutScreen=false; 
            authorScreen=false; 
            returnScreen=false; 
            categoryScreen=false; 
            stuFines=false; 
            allBooksScreen=false; 
            stuDelete=false; 
            searchStu=false; 
            compareBookScreen=false; 
            payStuFinesScreen=false; 
            checkoutErrorScreen=false; 
            returnErrorScreen=false; 
            stuSearchDispScreen=false; 
            allStuScreen=false; 
            booksListScreen=true; 
            addBookScreen=false; 
            bookSearchDispScreen=false; 
            studentsListScreen=false; 
  
  
            //if no input from the user is received then nothing happens (same as for other buttons) 
            if(isbnForLost.getText().equalsIgnoreCase("Enter ISBN of the lost book")&&identityForLost.getText().equalsIgnoreCase("Enter identity for lost book (if applicable)")){ 
  
                //screens set accordingly 
                bookLostScreen=false; 
                mainScreen=false; 
                checkoutScreen=false; 
                returnScreen=false; 
                categoryScreen=false; 
                stuDelete=false; 
                allBooksScreen=false; 
                stuFines=false; 
                searchStu=false; 
                compareBookScreen=false; 
                checkoutErrorScreen=false; 
                payStuFinesScreen=false; 
                returnErrorScreen=false; 
                stuSearchDispScreen=false; 
                authorScreen=false; 
                allStuScreen=false; 
                booksListScreen=true; 
                addBookScreen=false; 
                bookSearchDispScreen=false; 
                studentsListScreen=false; 
                isbnForLost.setText("Enter ISBN of the lost book"); 
                identityForLost.setText("Enter identity for lost book (if applicable)"); 
                //stuNumForLost.setText("Enter student number of student who lost book"); 
            } 
  
            //if the above statement if false and only the identity number is not entered then the following code is executed 
            else if(identityForLost.getText().equalsIgnoreCase("Enter identity for lost book (if applicable)")){ 
  
                //using the "library.bookLost" method the lost book is taken into account with the status stored as a returned integer value 
  
                lostStatus=library.bookLost(Integer.parseInt(isbnForLost.getText())); 
  
                //screens set accordingly 
                bookLostScreen=true; 
                mainScreen=false; 
                checkoutScreen=false; 
                returnScreen=false; 
                allBooksScreen=false; 
                authorScreen=false; 
                stuDelete=false; 
                compareBookScreen=false; 
                payStuFinesScreen=false; 
                checkoutErrorScreen=false; 
                returnErrorScreen=false; 
                categoryScreen=false; 
                stuFines=false; 
                stuSearchDispScreen=false; 
                searchStu=false; 
                allStuScreen=false; 
                booksListScreen=false; 
                addBookScreen=false; 
                bookSearchDispScreen=false; 
                studentsListScreen=false; 
                isbnForLost.setText("Enter ISBN of the lost book"); 
                identityForLost.setText("Enter identity for lost book (if applicable)"); 
                //stuNumForLost.setText("Enter student number of student who lost book"); 
  
                remove(addBook); 
                remove(searchBook); 
                remove(deleteBook); 
                remove(isbnForAdd); 
                remove(getAllBooks); 
                remove(titleForAdd); 
                remove(isbn1ForCompare); 
                remove(isbn2ForCompare); 
                remove(compareBooks); 
                remove(authorForAdd); 
                remove(categoryForAdd); 
                remove(addBook); 
                remove(getByAuthor); 
                remove(getByCategory); 
                remove(authorName); 
                remove(categoryName); 
                remove(costForAdd); 
                remove(starRatingForAdd); 
  
                remove(isbnForSearch); 
                remove(bookIdentityForSearch); 
                remove(isbnForDelete); 
                remove(identityForDelete); 
                //remove(stuNumForLost); 
                remove(bookLost); 
                remove(isbnForLost); 
                remove(identityForLost); 
                remove(returnToMainScreen); 
                add(returnToBooksListScreen); 
  
            } 
            else{ 
  
                //if both the identity and isbn have received inputs then the bookLost method with both these values as inputs is used and 
                //the status is stored as a returned integer value 
                lostStatus=library.bookLost(Integer.parseInt(isbnForLost.getText()), Integer.parseInt(identityForLost.getText())); 
  
                //screens set accordingly 
                bookLostScreen=true; 
                mainScreen=false; 
                checkoutScreen=false; 
                returnScreen=false; 
                allBooksScreen=false; 
                stuDelete=false; 
                payStuFinesScreen=false; 
                searchStu=false; 
                checkoutErrorScreen=false; 
                authorScreen=false; 
                returnErrorScreen=false; 
                stuSearchDispScreen=false; 
                compareBookScreen=false; 
                stuFines=false; 
                categoryScreen=false; 
                allStuScreen=false; 
                booksListScreen=false; 
                addBookScreen=false; 
                bookSearchDispScreen=false; 
                studentsListScreen=false; 
                remove(addBook); 
                remove(searchBook); 
                remove(deleteBook); 
                remove(isbnForAdd); 
                remove(addBook); 
                remove(getByAuthor); 
                remove(getByCategory); 
                remove(authorName); 
                remove(categoryName); 
                remove(titleForAdd); 
                remove(authorForAdd); 
                remove(categoryForAdd); 
                remove(costForAdd); 
                remove(bookLost); 
                remove(getAllBooks); 
                remove(starRatingForAdd); 
                remove(isbnForSearch); 
                remove(isbn1ForCompare); 
                remove(isbn2ForCompare); 
                remove(compareBooks); 
                remove(bookIdentityForSearch); 
                remove(isbnForDelete); 
                remove(identityForDelete); 
                //remove(stuNumForLost); 
                remove(isbnForLost); 
                remove(identityForLost); 
                remove(returnToMainScreen); 
                add(returnToBooksListScreen); 
            } 
  
        } 
  
        else if (evt.getSource()==addBook){ //if the add book button is pressed in the books list menu then the following code is executed 
            mainScreen=false; 
            checkoutScreen=false; 
            authorScreen=false; 
            returnScreen=false; 
            stuDelete=false; 
            checkoutErrorScreen=false; 
            returnErrorScreen=false; 
            categoryScreen=false; 
            stuSearchDispScreen=false; 
            bookLostScreen=false; 
            allStuScreen=false; 
            searchStu=false; 
            compareBookScreen=false; 
            stuFines=false; 
            booksListScreen=true; 
            addBookScreen=false; 
            bookSearchDispScreen=false; 
            studentsListScreen=false; 
  
            //if no inputs are received, then nothing happens (same as above buttons) 
            if(isbnForAdd.getText().equalsIgnoreCase("Enter the ISBN of the book")||titleForAdd.getText().equalsIgnoreCase("Enter the title of the book")||authorForAdd.getText().equalsIgnoreCase("Enter the author of the book")||categoryForAdd.getText().equalsIgnoreCase("Enter the category of the book")||costForAdd.getText().equalsIgnoreCase("Enter the cost of the book")||starRatingForAdd.getText().equalsIgnoreCase("Enter the star rating of the book")){ 
  
                mainScreen=false; 
                checkoutScreen=false; 
                returnScreen=false; 
                stuDelete=false; 
                authorScreen=false; 
                checkoutErrorScreen=false; 
                payStuFinesScreen=false; 
                returnErrorScreen=false; 
                stuSearchDispScreen=false; 
                allStuScreen=false; 
                stuFines=false; 
                compareBookScreen=false; 
                allBooksScreen=false; 
                categoryScreen=false; 
                bookLostScreen=false; 
                searchStu=false; 
                booksListScreen=true; 
                addBookScreen=false; 
                bookSearchDispScreen=false; 
                studentsListScreen=false; 
                isbnForAdd.setText("Enter the ISBN of the book"); 
                titleForAdd.setText("Enter the title of the book"); 
                authorForAdd.setText("Enter the author of the book"); 
                categoryForAdd.setText("Enter the category of the book"); 
                costForAdd.setText("Enter the cost of the book"); 
                starRatingForAdd.setText("Enter the star rating of the book"); 
  
  
            } 
            else{ 
  
                //if inputs are received then a new Books object is created taking in all the parameters for a Books object, and then is input into the 
                //"library.addBook" method 
                //whenever a book is successfully added, the identity number is increased by 1 each time to make sure every book has a different identity number 
  
                Books arbBook4=new Books(titleForAdd.getText(),authorForAdd.getText(),categoryForAdd.getText(),Integer.parseInt(isbnForAdd.getText()),Double.parseDouble(starRatingForAdd.getText()),Double.parseDouble(costForAdd.getText()),identityTrackNumber); 
                addBookStatus=library.addBook(arbBook4); 
                if(addBookStatus==SUCCESS_MESSAGE){ 
                    identityTrackNumber++; 
                } 
  
                //screens set accordingly 
                mainScreen=false; 
                checkoutScreen=false; 
                returnScreen=false; 
                payStuFinesScreen=false; 
                stuFines=false; 
                compareBookScreen=false; 
                stuDelete=false; 
                allBooksScreen=false; 
                authorScreen=false; 
                allStuScreen=false; 
                checkoutErrorScreen=false; 
                searchStu=false; 
                returnErrorScreen=false; 
                categoryScreen=false; 
                stuSearchDispScreen=false; 
                booksListScreen=false; 
                addBookScreen=true; 
                bookLostScreen=false; 
                bookSearchDispScreen=false; 
                studentsListScreen=false; 
                remove(addBook); 
                remove(searchBook); 
                remove(deleteBook); 
  
                //remove(stuNumForLost); 
                remove(isbnForLost); 
                remove(identityForLost); 
                remove(isbnForAdd); 
                remove(titleForAdd); 
                remove(authorForAdd); 
                remove(getAllBooks); 
                remove(categoryForAdd); 
                remove(costForAdd); 
                remove(starRatingForAdd); 
                remove(bookLost); 
                remove(isbnForLost); 
                remove(identityForLost); 
                remove(addBook); 
                remove(getByAuthor); 
                remove(getByCategory); 
                remove(authorName); 
                remove(categoryName); 
                remove(isbnForSearch); 
                remove(isbn1ForCompare); 
                remove(isbn2ForCompare); 
                remove(compareBooks); 
                remove(bookIdentityForSearch); 
                remove(isbnForDelete); 
                remove(identityForDelete); 
                remove(returnToMainScreen); 
                add(returnToBooksListScreen); 
                isbnForAdd.setText("Enter the ISBN of the book"); 
                titleForAdd.setText("Enter the title of the book"); 
                authorForAdd.setText("Enter the author of the book"); 
                categoryForAdd.setText("Enter the category of the book"); 
                costForAdd.setText("Enter the cost of the book"); 
                starRatingForAdd.setText("Enter the star rating of the book"); 
  
            } 
        } 
  
  
        else if((evt.getSource()==searchBook)){ //if the button search book is pressed from the books list menu then the following code is executed 
            isbnBookString=isbnForSearch.getText(); 
  
            bookIdentityString2=bookIdentityForSearch.getText(); 
  
            //if inputs are not given by the user then nothing happens (same as above buttons) 
            if(bookIdentityString2.equalsIgnoreCase("Enter the book identity to search (if applicable)")&&(isbnBookString.equalsIgnoreCase("Enter the ISBN of the book to search for"))){ 
                bookSearchDispScreen=false; 
                mainScreen=false; 
                allStuScreen=false; 
                categoryScreen=false; 
                checkoutScreen=false; 
                searchStu=false; 
                payStuFinesScreen=false; 
                compareBookScreen=false; 
                allBooksScreen=false; 
                authorScreen=false; 
                returnScreen=false; 
                bookLostScreen=false; 
                stuSearchDispScreen=false; 
                stuDelete=false; 
                studentsListScreen=false; 
                stuFines=false; 
                checkoutErrorScreen=false; 
                returnErrorScreen=false; 
                booksListScreen=true; 
            } 
  
            //if only the identity input is not given then the following code executes 
  
            else if((bookIdentityString2).equalsIgnoreCase("Enter the book identity to search (if applicable)")){ 
                int ISBNForSearch=Integer.parseInt(isbnBookString); 
                arbBookForSearch=library.getBook(ISBNForSearch); 
  
                //"library.getBook" method is used to search for the book required 
                //sets screens accordingly 
                bookSearchDispScreen=true; 
                bookSearch=true; 
                mainScreen=false; 
                allStuScreen=false; 
                checkoutScreen=false; 
                compareBookScreen=false; 
                authorScreen=false; 
                studentsListScreen=false; 
                returnScreen=false; 
                payStuFinesScreen=false; 
                searchStu=false; 
                stuFines=false; 
                allBooksScreen=false; 
                stuDelete=false; 
                categoryScreen=false; 
                stuSearchDispScreen=false; 
                bookLostScreen=false; 
                checkoutErrorScreen=false; 
                returnErrorScreen=false; 
                booksListScreen=false; 
                remove(bookIdentityForSearch); 
                remove(isbnForDelete); 
                remove(identityForDelete); 
                remove(deleteBook); 
                remove (mainScreenText); 
                remove (checkout); 
                remove(bookLost); 
                remove(addBook); 
                remove(addBook); 
                remove(getByAuthor); 
                remove(getByCategory); 
                remove(authorName); 
                remove(categoryName); 
                remove(isbnForLost); 
                remove(getAllBooks); 
                remove(identityForLost); 
                remove(isbn1ForCompare); 
                remove(isbn2ForCompare); 
                remove(compareBooks); 
                remove(studentsList); 
                remove(booksList); 
                remove(isbnForAdd); 
                remove(titleForAdd); 
                remove(authorForAdd); 
                remove(categoryForAdd); 
                remove(costForAdd); 
                remove(starRatingForAdd); 
                //remove(stuNumForLost); 
                remove(isbnForLost); 
                remove(identityForLost); 
                remove(returnBookText); 
                //remove(searchStudent); 
                remove(returnBook);  
                remove(stuNumForSearch); 
                remove(returnBookIdentity); 
                remove(studentNumber);  
                remove(timePassedText); 
                remove(returnToMainScreen); 
                remove(isbnForSearch); 
                remove(searchBook); 
                remove(bookIdentityForSearch); 
                add(returnToBooksListScreen); 
  
  
            } 
  
  
            else{ 
  
                //if both inputs are given by the user, then use the code uses the method which taken identity and ISBN inputs ("library.getBook") 
                //this book object is then stored and used in the paint method to display to the user 
  
                int identityForSearch=Integer.parseInt(bookIdentityString2); 
                int ISBNForSearch=Integer.parseInt(isbnBookString); 
                arbBookForSearch=library.getBook(ISBNForSearch,identityForSearch); 
  
                //sets screens accordingly 
                bookSearchDispScreen=true; 
                mainScreen=false; 
                checkoutScreen=false; 
                returnScreen=false; 
                authorScreen=false; 
                allStuScreen=false; 
                checkoutErrorScreen=false; 
                payStuFinesScreen=false; 
                searchStu=false; 
                compareBookScreen=false; 
                bookSearch=true; 
                bookLostScreen=false; 
                stuFines=false; 
                categoryScreen=false; 
                studentsListScreen=false; 
                returnErrorScreen=false; 
                allBooksScreen=false; 
                booksListScreen=false; 
                remove(bookIdentityForSearch); 
                remove(isbnForDelete); 
                remove(identityForDelete); 
                remove(deleteBook); 
                remove(bookLost); 
                remove(authorName); 
                remove(categoryName); 
                remove(getByAuthor); 
                remove(getByCategory); 
                remove(isbnForLost); 
                remove(getAllBooks); 
                remove(identityForLost); 
                remove (mainScreenText); 
                remove(addBook); 
                remove (checkout); 
                remove(studentsList); 
                remove(isbnForAdd); 
                remove(titleForAdd); 
                remove(authorForAdd); 
                remove(isbn1ForCompare); 
                remove(isbn2ForCompare); 
                remove(compareBooks); 
                remove(categoryForAdd); 
                remove(costForAdd); 
                remove(starRatingForAdd); 
                remove(booksList); 
                //remove(stuNumForLost); 
                remove(isbnForLost); 
                remove(identityForLost); 
                remove(returnBookText); 
                //remove(searchStudent); 
                remove(returnBook);  
                remove(stuNumForSearch); 
                remove(returnBookIdentity); 
                remove(studentNumber);  
                remove(timePassedText); 
                remove(returnToMainScreen); 
                remove(isbnForSearch); 
                remove(searchBook); 
                remove(bookIdentityForSearch); 
                add(returnToBooksListScreen); 
  
            } 
        }                
        repaint(); //calls the paint method to update graphics for the screen for the user to view 
    } 
    //the paint method is the method which controls the "visuals" as to what the user sees and through the use of if statement and booleans, the method can be 
    //harnessed to efficiently display what is required at the correct times 
    public void paint(Graphics g){ 
        Font textFont = new Font("Calibri",Font.BOLD,30); //creates new fonts with specific font type and size 
        Font textFont2 = new Font("Calibri",Font.ITALIC,15); 
        Font textFont3=new Font("Calibri",Font.ITALIC,20); 
        Font fancy=new Font("Verdana",Font.BOLD,30); 
        Font fancy2=new Font("Castellar",Font.BOLD,25); 
        Color lightBlue = new Color (100, 149, 237); 
        g.setFont(fancy2); //sets a specific font type for the following text 
        g.setColor(Color.black); //sets the color of paint to white 
        //executes if the main screen is true 
        if (mainScreen==true&&checkoutScreen==false&&returnScreen==false&&checkoutErrorScreen==false&&returnErrorScreen==false&&booksListScreen==false&&bookSearchDispScreen==false&&studentsListScreen==false){ 
            setSize(500,500); //sets size 500px by 500px 
            g.drawString ("Stephen Lewis Library System",10,45);  //writes whatever is specified at the x,y coordinates 
            g.setColor(Color.BLUE); 
            g.fillRect(0, 380, 500, 130); 
            g.setColor(Color.YELLOW); 
            setBackground(Color.CYAN); //sets the background color 
            g.setFont(textFont); 
            g.drawString ("___________________________________________________",0,375); //draws a line at the specified x,y coordinates 
  
        } 
        //executes if the checkout screen is true 
        else if(checkoutScreen==true&&mainScreen==false&&returnScreen==false&&checkoutErrorScreen==false&&returnErrorScreen==false&&booksListScreen==false&&bookSearchDispScreen==false&&studentsListScreen==false){ 
            setSize(800,500); //sets the screen size 800px by 500px 
  
            /* 
             * display the book information and the student information using the arbitrary book and student information from the above button methods, at specified 
             * coordinates 
             */
            g.setFont(textFont); 
            g.drawString("Checkout: "+"'"+bookTitle+"'", 10, 30); 
            g.setFont(textFont2); 
            g.drawString("Title: "+arbitraryBook.getBookTitle()+"",10,90); 
            g.drawString("Author: "+arbitraryBook.getBookAuthor()+"",10,110); 
            g.drawString("Category: "+arbitraryBook.getBookCategory()+"",10,130); 
            g.drawString("Cost: $"+arbitraryBook.getBookCost()+"",10,150); 
            g.drawString("ISBN: 000000000"+arbitraryBook.getISBN()+"",10,170); 
            g.drawString("Star Rating: "+arbitraryBook.getBookStarRating()+"",10,190); 
            g.drawString("Star Rating: "+arbitraryBook.getIdentity()+"",10,210); 
            //================================================================================= 
            g.drawString("Name: "+arbitraryStu.getStuFName()+" "+arbitraryStu.getStuLName()+"",500,90); 
            g.drawString("Student Number: "+arbitraryStu.getStuNum()+"",500,110); 
            g.drawString("Student Fines: $"+arbitraryStu.getStuFines()+"",500,130); 
            g.drawString("Number of Books Checked Out: "+arbitraryStu.getBooksOut()+"",500,150); 
  
  
            g.setColor(Color.BLUE); 
            g.fillRect(0, 385, 950, 220); 
            g.setColor(Color.YELLOW); 
            g.setFont(textFont); 
            g.drawString("_______________________________________________________________________________________",0,380); 
        } 
        //executes if the students list screen is true 
        else if(checkoutScreen==false&&mainScreen==false&&returnScreen==false&&checkoutErrorScreen==false&&returnErrorScreen==false&&booksListScreen==false&&bookSearchDispScreen==false&&studentsListScreen==true){ 
  
            //sets specific size and font type 
            setSize(950,500); 
            g.setFont(fancy2); 
            g.drawString("Students List Screen",320,30); 
            g.setColor(Color.BLUE); 
            g.fillRect(0, 385, 950, 220); 
            g.setColor(lightBlue); 
            g.fillRect(0, 275, 950, 110); 
            g.setColor(Color.YELLOW); 
            g.setFont(textFont); 
            g.drawString("_______________________________________________________________________________________",0,380); 
            g.setFont(textFont2); 
            g.drawString("______________________________________________________________________________________________________________________________________________________________________________",0,273); 
        } 
  
  
        //if all students screen is equal to true then the following code executes 
        else if (allStuScreen==true){ 
            g.setColor(Color.black); 
            g.setFont(textFont3); //sets specific font type 
  
            //using the for loop and the size of the students list array, the information for each student is printed out at specific y-value intervals, constantly 
            //increasing within the for loop 
            int yval=130; 
            for(int counter=0;counter<allStu.length;counter++){ 
                g.drawString(""+allStu[counter].getStuFName()+" "+allStu[counter].getStuLName()+", "+allStu[counter].getStuNum()+", Student Fines: $"+allStu[counter].getStuFines()+", Number of books checked out: "+allStu[counter].getBooksOut(),10,yval+yValForAllBooks); 
                yval+=30; 
            } 
  
            //adds some designs on the screen to make appearances more appealing 
            g.setFont(fancy); 
            g.setColor(Color.yellow); 
            g.fillRect(0,0,getWidth(),100); 
            g.setColor(Color.black); 
            g.drawString("ALL THE STUDENTS CURRENTLY IN THE SYSTEM:",10,60); 
            g.setColor(Color.BLUE); 
            g.fillRect(0, 385, 950, 220); 
            g.setColor(Color.YELLOW); 
            g.setFont(textFont); 
            g.drawString("_______________________________________________________________________________________",0,380); 
        } 
        else if (stuSearchDispScreen==true){ //if the student search display screen is true then the following code executes 
            g.drawString("Students List Screen",320,30); 
            g.setFont(textFont); 
            g.setColor(lightBlue); 
            g.fillRect(0, 275, 950, 110); 
            g.setColor(Color.BLUE); 
            g.fillRect(0, 385, 950, 220); 
            g.setColor(Color.YELLOW); 
            g.drawString("_______________________________________________________________________________________",0,380); 
            g.setFont(textFont2); 
            g.drawString("______________________________________________________________________________________________________________________________________________________________________________",0,273); 
            g.setColor(Color.BLACK); 
            g.setFont(textFont2); 
            //executes if the arbitrary student value is null and the search student boolean is true  
            if(arbStu3==null&&addStu==false&&stuDelete==false&&allStuScreen==false&&searchStu==true){ 
                //draws desired message on screen 
                g.drawString("This student number does not match a student in the", 10, 160); 
                g.drawString("database. Please enter the student number again or", 10, 180); 
                g.drawString("add the student to the database from students list.", 10, 200); 
            } 
            //executes if arbitrary student is not null and student search is true 
            else if(arbStu3!=null&&addStu==false&&searchStu==true&&stuDelete==false&&allStuScreen==false){ 
                //prints out student information and their books with the for loop spacing technique used above 
                g.drawString("Name: "+arbStu3.getStuFName()+" "+arbStu3.getStuLName()+"",10,160); 
                g.drawString("Student Number: "+arbStu3.getStuNum()+"",10,180); 
                g.drawString("Student Fines: $"+arbStu3.getStuFines()+"",10,200); 
                g.drawString("Number of Books Checked Out: "+arbStu3.getBooksOut()+"",10,220); 
                if(sizeOfBooks!=0){ 
                    int yVal=295; 
                    if(book!=null){ 
                        for(int counter=0;counter<sizeOfBooks;counter++){ 
  
                            g.drawString(book[counter].getBookTitle()+" by "+book[counter].getBookAuthor()+", ISBN: "+book[counter].getISBN()+", Identity: "+book[counter].getIdentity(),10,yVal); 
                            yVal+=20; 
                        } 
                    } 
                } 
            } 
            //executes if addStu is true and the status for it is 1 
            else if (addStu==true && addStuStatus==SUCCESS_MESSAGE&&stuDelete==false&&allStuScreen==false&&searchStu==false){ 
  
                g.drawString("Student successfully added.",365,260); 
            } 
            //executes if addStu is true and the status for it is 2 
            else if (addStu==true  &&addStuStatus==RETURN_VALUE_2&&stuDelete==false&&allStuScreen==false&&searchStu==false){ 
                g.drawString("Max students added. Cannot add student.",365,260); 
            } 
            //executes if addStu is true and the status for it is 3 
            else if (addStu==true  &&addStuStatus==RETURN_VALUE_3&&stuDelete==false&&allStuScreen==false&&searchStu==false){ 
                g.drawString("Cannot add student. Same first and last names already exist.",365,260); 
            } 
            //executes if addStu is true and the status for it is 4 
            else if (addStu==true &&addStuStatus==RETURN_VALUE_4&&stuDelete==false&&allStuScreen==false&&searchStu==false){ 
                g.drawString("Cannot add student. Same student number already exists.",365,260); 
            } 
            //executes if deleting student and case for delete is 1 
            else if(stuDelete==true&&addStu==false&&caseForStuDelete==SUCCESS_MESSAGE&&allStuScreen==false&&searchStu==false){ 
                g.drawString("Student successfully deleted.",670,370); 
            } 
            //executes if deleting student and case for delete is 0 
            else if(stuDelete==true&&addStu==false&&caseForStuDelete==RETURN_VALUE_0&&allStuScreen==false&&searchStu==false){ 
                g.drawString("Student not deleted. The student number entered is incorrect or it does not exist in the system.",365,370); 
  
            } 
            //executes when displaying student paying off fines and status for this is 0 and 1 
            else if(stuFines==true&&stuDelete==false&&addStu==false&&caseForStuDelete==RETURN_VALUE_0&&allStuScreen==false&&searchStu==false){ 
                if(stuFineStatus==RETURN_VALUE_0){ 
                    g.drawString("The entered student number does not",670,160); 
                    g.drawString("exist. Please enter information again",670,180); 
                    g.drawString("or add student to students list.",670,200); 
                } 
                else if(stuFineStatus==SUCCESS_MESSAGE){ 
                    g.drawString("Successfully paid student's fines.",670,160); 
                } 
  
            } 
        } 
        //executes when return screen is true 
        else if(checkoutScreen==false && mainScreen==false&&returnScreen==true&&checkoutErrorScreen==false&&returnErrorScreen==false&&booksListScreen==false&&bookSearchDispScreen==false&&studentsListScreen==false){ 
            setSize(800,500); 
            g.drawString("Return: "+"'"+bookTitleForReturn+"'", 10, 30); 
            g.setFont(textFont2); 
            g.drawString("Title: "+returnArbBook.getBookTitle()+"",10,90); 
            g.drawString("Author: "+returnArbBook.getBookAuthor()+"",10,110); 
            g.drawString("Category: "+returnArbBook.getBookCategory()+"",10,130); 
            g.drawString("Cost: $"+returnArbBook.getBookCost()+"",10,150); 
            g.drawString("ISBN: 000000000"+returnArbBook.getISBN()+"",10,170); 
            g.drawString("Star Rating: "+returnArbBook.getBookStarRating()+"",10,190); 
            g.drawString("Book Identity: "+returnArbBook.getIdentity()+"",10,210); 
            //================================================================================= 
            g.drawString("Name: "+arbStuForReturn.getStuFName()+" "+arbStuForReturn.getStuLName()+"",500,90); 
            g.drawString("Student Number: "+arbStuForReturn.getStuNum()+"",500,110); 
            g.drawString("Student Fines: $"+arbStuForReturn.getStuFines()+"",500,130); 
            g.drawString("Number of Books Checked Out: "+arbStuForReturn.getBooksOut()+"",500,150); 
  
            g.setColor(Color.BLUE); 
            g.fillRect(0, 385, 950, 220); 
            g.setColor(Color.YELLOW); 
            g.setFont(textFont); 
            g.drawString("_______________________________________________________________________________________",0,380); 
        } 
        else if(checkoutScreen==false && mainScreen==false&&returnScreen==false&&checkoutErrorScreen==true&&returnErrorScreen==false&&booksListScreen==false&&bookSearchDispScreen==false&&studentsListScreen==false){ 
            setSize(800,500); 
            //executes if status is 0 
            if(statusOfCheckout==RETURN_VALUE_0){ 
                g.drawString("This student number and/or ISBN do not exist. Please", 10, 30); 
                g.drawString("enter the information again or add the student or", 10, 65); 
                g.drawString("book to the system by going to the students list", 10, 100); 
                g.drawString("or books list menu.",10,135); 
            } 
            //executes if status is 2 
            else if(statusOfCheckout==RETURN_VALUE_2){ 
                g.drawString("All copies of this book have already been",10,30); 
                g.drawString("checked out!",10,65); 
            } 
            //executes if status is 3 
            else if(statusOfCheckout==RETURN_VALUE_3){ 
                g.drawString("This student has already checked out 3 books!",10,30); 
            } 
            //executes if status is 4 
            else if(statusOfCheckout==RETURN_VALUE_4){ 
                g.drawString("The student has $5.00 or more in fines so",10,30); 
                g.drawString("this book could not be checked out.",10,65); 
            } 
            g.setColor(Color.BLUE); 
            g.fillRect(0, 385, 950, 220); 
            g.setColor(Color.YELLOW); 
            g.setFont(textFont); 
            g.drawString("_______________________________________________________________________________________",0,380); 
  
        } 
        //executes if return status is not successful 
        else if(checkoutScreen==false && mainScreen==false&&returnScreen==false&&checkoutErrorScreen==false&&returnErrorScreen==true&&booksListScreen==false&&bookSearchDispScreen==false&&studentsListScreen==false){ 
            setSize(800,500); 
            //executes if status is 0 
            if(statusOfReturn==RETURN_VALUE_0){ 
                g.drawString("The entered ISBN does not exist or this", 10, 30); 
                g.drawString("book has never been checked out. Please enter", 10, 65); 
                g.drawString("the information again or add the student or", 10, 100); 
                g.drawString("book to the system by going to the students", 10, 135); 
                g.drawString("list or books list menu.", 10, 170); 
            } 
            g.setColor(Color.BLUE); 
            g.fillRect(0, 385, 950, 220); 
            g.setColor(Color.YELLOW); 
            g.setFont(textFont); 
            g.drawString("_______________________________________________________________________________________",0,380); 
        } 
        //executes if books list screen is true 
        else if(checkoutScreen==false && mainScreen==false&&returnScreen==false&&checkoutErrorScreen==false&&returnErrorScreen==false&&booksListScreen==true&&bookSearchDispScreen==false&&studentsListScreen==false&&deleteBookScreen==false&&addBookScreen==false&&bookLostScreen==false){ 
            setSize(950,500); 
            g.setFont(fancy2); 
            g.drawString("Books List Screen",350,30); 
            g.setColor(Color.BLUE); 
            g.fillRect(0, 385, 950, 220); 
            g.setColor(lightBlue); 
            g.fillRect(0, 275, 950, 110); 
            g.setColor(Color.YELLOW); 
            g.setFont(textFont); 
            g.drawString("_______________________________________________________________________________________",0,380); 
            g.setFont(textFont2); 
            g.drawString("______________________________________________________________________________________________________________________________________________________________________________",0,273); 
  
  
        } 
        //executes if book search display screen is true 
        else if (checkoutScreen==false && mainScreen==false&&returnScreen==false&&checkoutErrorScreen==false&&returnErrorScreen==false&&booksListScreen==false&&bookSearchDispScreen==true&&studentsListScreen==false&&deleteBookScreen==false&&addBookScreen==false&&bookLostScreen==false){ 
            g.setFont(textFont); 
            //executes if arbitrary book is null 
            if(arbBookForSearch==null&&bookSearch==true){ 
                g.drawString("The entered ISBN does not exist in the database or does", 10, 30); 
                g.drawString("not match up with the book identity number. Please enter", 10, 65); 
                g.drawString("the information again or add the book to the system by ", 10, 100); 
                g.drawString("going to the books list menu.", 10, 135); 
                g.setColor(Color.BLUE); 
                g.fillRect(0, 385, 950, 220); 
                g.setColor(Color.YELLOW); 
                g.drawString("_______________________________________________________________________________________",0,380); 
            } 
            //executes if arbitrary book is not null 
            else if (arbBookForSearch!=null&&bookSearch==true){ 
                g.drawString("Book Search: "+"'"+arbBookForSearch.getBookTitle()+"'", 10, 30); 
                g.setFont(textFont2); 
                g.drawString("Title: "+arbBookForSearch.getBookTitle()+"",10,90); 
                g.drawString("Author: "+arbBookForSearch.getBookAuthor()+"",10,110); 
                g.drawString("Category: "+arbBookForSearch.getBookCategory()+"",10,130); 
                g.drawString("ISBN: 000000000"+arbBookForSearch.getISBN()+"",10,150); 
                g.drawString("Star Rating: "+arbBookForSearch.getBookStarRating()+"",10,170); 
                g.drawString("Cost: $"+arbBookForSearch.getBookCost()+"",10,190); 
                g.drawString("Book Identity: "+arbBookForSearch.getIdentity()+"",10,210); 
                  
                if (arbBookForSearch.getBookStu()!=null){ 
                    g.drawString("Checked Out By: "+arbBookForSearch.getBookStu().getStuFName()+" "+arbBookForSearch.getBookStu().getStuLName()+", "+arbBookForSearch.getBookStu().getStuNum(),10,290); 
                } 
                      
                      
                g.setFont(textFont); 
                g.setColor(Color.BLUE); 
                g.fillRect(0, 385, 950, 220); 
                g.setColor(Color.YELLOW); 
                g.drawString("_______________________________________________________________________________________",0,380); 
            } 
  
  
        } 
        //executes if delete book screen is true 
        else if (checkoutScreen==false && mainScreen==false&&returnScreen==false&&checkoutErrorScreen==false&&returnErrorScreen==false&&booksListScreen==false&&bookSearchDispScreen==false&&studentsListScreen==false&&deleteBookScreen==true&&addBookScreen==false&&bookLostScreen==false){ 
            if(delBook==true&&delBookStatus==SUCCESS_MESSAGE){ 
                g.setFont(textFont); 
                g.drawString("Book has been successfully deleted.", 10, 30); 
                g.setColor(Color.BLUE); 
                g.fillRect(0, 385, 950, 220); 
                g.setColor(Color.YELLOW); 
                g.drawString("_______________________________________________________________________________________",0,380); 
  
            } 
            //executes if delete book status is 0 
            else if(delBook==true&&delBookStatus==RETURN_VALUE_0){ 
                g.setFont(textFont); 
                g.drawString("The entered ISBN and/or identity number do not match", 10, 30); 
                g.drawString("with anything in the system or the book may have already ", 10, 65); 
                g.drawString("been deleted. Please add the book to the books list menu ", 10, 100); 
                g.drawString("or enter the information again.", 10, 135); 
                g.setColor(Color.BLUE); 
                g.fillRect(0, 385, 950, 220); 
                g.setColor(Color.YELLOW); 
                g.drawString("_______________________________________________________________________________________",0,380); 
            } 
            else if (delBook==false){ 
  
                g.setColor(Color.BLUE); 
                g.fillRect(0, 385, 950, 220); 
                g.setColor(Color.YELLOW); 
                g.drawString("_______________________________________________________________________________________",0,380); 
            } 
  
  
        } 
        //executes if add book screen is true 
        else if (checkoutScreen==false && mainScreen==false&&returnScreen==false&&checkoutErrorScreen==false&&returnErrorScreen==false&&booksListScreen==false&&bookSearchDispScreen==false&&studentsListScreen==false&&deleteBookScreen==false&&addBookScreen==true&&bookLostScreen==false){ 
            g.setFont(textFont); 
            //executes if add book status is 1 
            if(addBookStatus==SUCCESS_MESSAGE){ 
                g.drawString("The book has been successfully added.", 10, 30); 
                g.setColor(Color.BLUE); 
                g.fillRect(0, 385, 950, 220); 
                g.setColor(Color.YELLOW); 
                g.drawString("_______________________________________________________________________________________",0,380); 
            } 
            //executes if add book status is 2 
            else if (addBookStatus==RETURN_VALUE_2){ 
                g.drawString("The book has not been successfully added.", 10, 30); 
                g.drawString("The book list limit has been reached.",10,65); 
                g.setColor(Color.BLUE); 
                g.fillRect(0, 385, 950, 220); 
                g.setColor(Color.YELLOW); 
                g.drawString("_______________________________________________________________________________________",0,380); 
            } 
            //executes if add book status is 3 
            else if (addBookStatus==RETURN_VALUE_3){ 
                g.drawString("The book you are trying to add has the same ISBN as a book already in the ",10,30); 
                g.drawString("system but different title,author,category,etc. Please revise the",10,65); 
                g.drawString("information you are adding.",10,100); 
                g.setColor(Color.BLUE); 
                g.fillRect(0, 385, 950, 220); 
                g.setColor(Color.YELLOW); 
                g.drawString("_______________________________________________________________________________________",0,380); 
            } 
        } 
        //executes if lost status screen is true 
        else if (checkoutScreen==false && mainScreen==false&&returnScreen==false&&checkoutErrorScreen==false&&returnErrorScreen==false&&booksListScreen==false&&bookSearchDispScreen==false&&studentsListScreen==false&&deleteBookScreen==false&&addBookScreen==false&&bookLostScreen==true){ 
            g.setFont(textFont); 
            //executes if status is 1 
            if(lostStatus==SUCCESS_MESSAGE){ 
                g.drawString("The system has successfully taken into account the lost book. The cost of",10,30); 
                g.drawString("the book has been added to the student's account as a fine.",10,65); 
                g.setColor(Color.BLUE); 
                g.fillRect(0, 385, 950, 220); 
                g.setColor(Color.YELLOW); 
                g.drawString("_______________________________________________________________________________________",0,380); 
            } 
            //executes if status is 0 
            else if (lostStatus==RETURN_VALUE_0){ 
                g.drawString("The ISBN and/or identity number and/or student number do not match those of",10,30); 
                g.drawString("any book currently in the system. Please re-enter your information or add",10,65); 
                g.drawString("the book to the system.",10,100); 
                g.setColor(Color.BLUE); 
                g.fillRect(0, 385, 950, 220); 
                g.setColor(Color.YELLOW); 
                g.drawString("_______________________________________________________________________________________",0,380); 
            } 
            //executes if status is 2 
            else if (lostStatus==RETURN_VALUE_2){ 
                g.drawString("This book is not checked out. It cannot be lost by a student.",10,30); 
                g.setColor(Color.BLUE); 
                g.fillRect(0, 385, 950, 220); 
                g.setColor(Color.YELLOW); 
                g.drawString("_______________________________________________________________________________________",0,380); 
            } 
  
        }//executes is all books screen is true 
        else if(allBooksScreen==true){ 
            //uses the for loop spacing technique to display book information for all books in the list 
  
  
            g.setFont(textFont3); 
            g.setColor(Color.black); 
            int yval=130; 
            for(int counter=0;counter<allBooks.length;counter++){ 
                g.drawString(""+allBooks[counter].getBookTitle()+" by "+allBooks[counter].getBookAuthor()+", "+allBooks[counter].getBookCategory()+", ISBN: "+allBooks[counter].getISBN()+", Star Rating: "+allBooks[counter].getBookStarRating()+", Cost: $"+allBooks[counter].getBookCost()+", Identity: "+allBooks[counter].getIdentity()+"",10,yval+yValForAllBooks); 
                //yValForAllBooks+=20; 
                yval+=30; 
            } 
  
            g.setFont(fancy); 
            g.setColor(Color.yellow); 
            g.fillRect(0,0,getWidth(),100); 
            g.setColor(Color.black); 
            g.drawString("ALL THE BOOKS CURRENTLY IN THE SYSTEM:",10,60); 
            g.setColor(Color.BLUE); 
            g.fillRect(0, 385, 950, 220); 
            g.setColor(Color.YELLOW); 
            g.setFont(textFont); 
            g.drawString("_______________________________________________________________________________________",0,380); 
        }//executes if the compare book screen is true 
        else if (compareBookScreen==true){ 
            //executes if method method "library.compareBooks" returns 'null' 
            if(library.compareBooks(Integer.parseInt(isbn1ForCompare.getText()), Integer.parseInt(isbn2ForCompare.getText()))==null){ 
                g.setFont(textFont); 
                g.drawString("One or both of the ISBNs that have been entered do not match those in the",10,30); 
                g.drawString("system. Please re-enter your information or add the book to the books list.",10,65); 
                g.setColor(Color.BLUE); 
                g.fillRect(0, 385, 950, 220); 
                g.setColor(Color.YELLOW); 
                g.drawString("_______________________________________________________________________________________",0,380); 
            } 
            //executes if the returned book's ISBN is not 0 
            else if (betterBook.getISBN()!=RETURN_VALUE_0){ 
                g.drawString("The better book is:",10,30); 
                g.setFont(textFont3); 
                g.drawString(betterBook.getBookTitle()+", Author: "+betterBook.getBookAuthor()+", ISBN: "+betterBook.getISBN()+", Category: "+betterBook.getBookCategory()+", Star Rating: "+betterBook.getBookStarRating()+"",10,65); 
                g.setFont(textFont); 
                g.setColor(Color.BLUE); 
                g.fillRect(0, 385, 950, 220); 
                g.setColor(Color.YELLOW); 
                g.drawString("_______________________________________________________________________________________",0,380); 
            } 
            //executes if the returned book's ISBN is 0 
            else if (betterBook.getISBN()==RETURN_VALUE_0){ 
                g.setFont(textFont); 
                g.drawString("Both the entered books have the same star rating. It comes down to",10,30); 
                g.drawString("personal choice.",10,65); 
                g.setColor(Color.BLUE); 
                g.fillRect(0, 385, 950, 220); 
                g.setColor(Color.YELLOW); 
                g.drawString("_______________________________________________________________________________________",0,380); 
            } 
  
        }//executes if author screen is true 
        else if (authorScreen==true){ 
            //if the method "library.getBooksByAuthor" returns null 
            if(library.getBooksByAuthor(authorName.getText())==null){ 
                g.setFont(textFont); 
                g.drawString("No books exist by this author in the library. Enter the information again",10,30); 
                g.drawString("or addthese books to the books list.",10,65); 
                g.setColor(Color.BLUE); 
                g.fillRect(0, 385, 950, 220); 
                g.setColor(Color.YELLOW); 
                g.drawString("_______________________________________________________________________________________",0,380); 
  
            } 
            //executes if the above does not return 'null' 
            else{ 
                g.setFont(textFont3); 
                //uses spacing technique as above to display all books by same author 
                int yval=130; 
                for(int counter=0;counter<byAuthor.length;counter++){ 
                    g.drawString(byAuthor[counter].getBookTitle()+", Author: "+byAuthor[counter].getBookAuthor()+", ISBN: "+byAuthor[counter].getISBN(),10,yval+yValForAllBooks); 
                    yval+=30; 
                } 
                g.setFont(fancy); 
                g.setColor(Color.yellow); 
                g.fillRect(0,0,getWidth(),100); 
                g.setColor(Color.black); 
                g.drawString("HERE ALL THE BOOKS CURRENTLY BY '"+authorName.getText().toUpperCase()+"' :",10,60); 
                g.setColor(Color.BLUE); 
                g.fillRect(0, 385, 950, 220); 
                g.setColor(Color.YELLOW); 
                g.setFont(textFont); 
                g.drawString("_______________________________________________________________________________________",0,380); 
            } 
  
  
        } 
  
        else if (categoryScreen==true){ //executes if category books screen is true 
            if(library.getBooksByCategory(categoryName.getText())==null){ //executes if "library.getBooksByCategory" returns 'null' 
                g.setFont(textFont); 
                g.drawString("No books exist by this category in the library. Enter the information",10,30); 
                g.drawString("again or add these books to the books list.",10,65); 
                g.setColor(Color.BLUE); 
                g.fillRect(0, 385, 950, 220); 
                g.setColor(Color.YELLOW); 
                g.drawString("_______________________________________________________________________________________",0,380); 
            } 
            else{ 
                //executes if "library.getBooksByCategory" does not return 'null' 
                g.setFont(textFont3); 
                //uses spacing technique as above to display all books by same author 
                int yval=130; 
                for(int counter=0;counter<byCategory.length;counter++){ 
                    g.drawString(byCategory[counter].getBookTitle()+", Category: "+byCategory[counter].getBookCategory()+", ISBN: "+byCategory[counter].getISBN(),10,yval+yValForAllBooks); 
                    yval+=30; 
                } 
                g.setFont(fancy); 
                g.setColor(Color.yellow); 
                g.fillRect(0,0,getWidth(),100); 
                g.setColor(Color.black); 
                g.drawString("HERE ALL THE BOOKS CURRENTLY IN '"+categoryName.getText().toUpperCase()+"' :",10,60); 
                g.setColor(Color.BLUE); 
                g.fillRect(0, 385, 950, 220); 
                g.setColor(Color.YELLOW); 
                g.setFont(textFont); 
                g.drawString("_______________________________________________________________________________________",0,380); 
            } 
        } 
  
  
    } 
  
} 
  
//end of program 