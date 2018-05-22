# Online-Membership-System-JAVA
JAVA desktop application using Swing framework for front end and JDBC for back end

Project Functionality:
The server runs on AFS and we have used the database provided by NJIT on sql2.nji.edu.
Two level of functionality available:
1.	Admin (password admin):
2.	Members
The difference of admin and member is determined by the user type field (0 for admin and 1 for member). The admin can add new members to the database, view the information of all the members in the database and edit the information of any member.
The member can see the information of all the other members but can only edit its own information. The program would not allow any member to edit information of other members.
The following information about the member is stored in the database:
1.	Username
2.	User Type
3.	Password
4.	First Name
5.	Last Name
6.	Country
7.	State
8.	City
9.	Address
10.	Phone
11.	Email
The database is created using dummy data generated using online tools. We have uses the data for country, states and cities along with their IDs to give choices to any member while creating or editing the profile.
We have used JSON object to parse and communicate data between processes of the program.
The DataObject class is used along with a child class MyDataObject which basically takes all the json data and sets it to the message variable of the DataObject.
Different methods are used for various purposes in the program:
1.	Java Security packages: MD5 function is used to convert pure string password to a md5 hash value which is then stored in the database. This confirms the confidentiality of the password with respect to the server as well.
2.	Swing/AWT packages for front end look (dimensions, frames)
3.	Java Networking packages: To retrieve the IP address of the clients connected to the server etc.


Run Instruction:
For AFS:
Compile:
javac -cp ".:json-simple-1.1.1.jar:mysql-connector-java-5.1.41-bin.jar" Server/*.java Client/*.java ServerUtils/*.java Utils/*.java javaproject/*.java
Run Server:
java -cp .:mysql-connector-java-5.1.41-bin.jar:json-simple-1.1.1.jar Server/ThreadedDataObjectServer



For Local Machine(windows):
Compile:
javac -cp ".:mysql-connector-java-5.1.41-bin.jar;json-simple-1.1.1.jar" Server/*.java Client/*.java ServerUtils/*.java Utils/*.java javaproject/*.java
Run Client:
java -cp .;mysql-connector-java-5.1.41-bin.jar;json-simple-1.1.1.jar javaproject/Main

