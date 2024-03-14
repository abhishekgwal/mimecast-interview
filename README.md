
                                                  The Library - Backend 

Please connect to the zoom invitation in the mail.
Prerequisites & Installations 
1. GitHub account
2. Java 11 (Can be installed from Oracle’s website or OpenJDK) 
3. IDE(preferably Intellij)


   We generated an empty web application of “library” using spring boot and uploaded the source code to this Github    
   Repository: https://github.com/tomerblecher/abhisehk-tech-task

Step 0: 
Make sure you know how to run the application.
An “in memory” Mongodb would automatically rise as specified in the source code.
Step 1: 
Add Create REST API for “Author”. 
The endpoint should receive the author’s name, fetch his books and save them in DB.

When creating an author, the application should call to a 3rd party API to return the list of books of that author. For this task you will use the book_api of https://developer.nytimes.com/ to get books for the author (for testing you can search for the author ‘Stephen King’).  Use the ‘Book Reviews Services’ endpoint to fetch the data.
An author should have the following properties:
Name
Books
Book name
Summary
Step 2: 
Add GET REST API for “Author”.
The GET endpoint should accept Author name and return the list of results saved in your db.



Please keep in mind that one day you may want to replace the books_api with another provider (e.g  https://developers.google.com/books/), and it should be done as smooth as possible. 
Commit and push everything to Github. 
Please write down at the end of each part how long it took you to complete the task. 
Good luck.

1. Successfully saved to DB with Authors name
![POST.png](src%2Fmain%2Fresources%2Fimages%2FPOST.png)

2. Get all books from DB with Authors name
![GET.png](src%2Fmain%2Fresources%2Fimages%2FGET.png)

3. Used studio 3T to access the stored data
![Studio3T.png](src%2Fmain%2Fresources%2Fimages%2FStudio3T.png)
