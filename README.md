# Art Store
The project is an online art store.
There are functions in it:
 1. Registration (for any visitor as customer)
 2. Authentication (for customer and business user)
 3. Changing language (for any visitor)
 4. Product catalog viewing (for any visitor)
 5. Additional product information viewing (for any visitor)
 6. Viewing and editing personal information (for customer and business user)
 7. Adding product to cart (for customer)
 8. Quantity changing and deleting goods in cart (for customer)
 9. Ordering goods from cart (for customer)
 10. Viewing all past orders (for customer)
 11. Adding new products at special page (for business user)
 12. Modifying and deleting an existing product (for business user)

# setup
To run the project at your computer you should:
1. Download or clone repository to your computer.
2. Install maven.
3. Open command line at directory where file pom.xml is.
4. Write "mvn clean install".
5. Run docs/creation.sql in your MySQL Workbench to create test database.
6. Unpack .war (done in 4th) to run at your local Tomcat server.
7. Write address "http://localhost:8080/webproject/".

You can log in as 
- usual user (login - natali, password - natali) 
- business user (login - andrey, password - andrey)
- administrator (login - aadmin, password - aadmin).

#Used technologies
Java Core
Java EE
Java Core
Maven
MySQL
Spring Framework / Security / MVC
JDBС
Hibernate
JUnit / Mockito
HTML / CSS / Bootstrap
JavaScript / Ajax
Git
Tomcat