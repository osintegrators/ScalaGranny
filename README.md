These instructions were written using Ubuntu 12.10

1.) Install the Postgres database software if it is not installed
    a. sudo apt-get install postegresql
2.) Start the postgresql instance
    a. sudo service postgresql start
3.) Create a new schema for the granny application. There is no need to create
      the tables, just the schema.
    a. sudo su postgres - To log in as the postgres user.
    b. createdb granny - To create the schema
    c. exit - To go back to being the user you are logged in as.
    d. sudo passwd postgres - and change the password to postgres. This is
       what the application is expecting. If you choose to use something
       different it can be changed in application.conf.
4.) Install Play - Official instructions are located at
       http://www.playframework.com/documentation/2.1.0/Installing
       To run Play, you need JDK 6 or later. (Please see 
         http://www.oracle.com/technetwork/java/javase/downloads/index.html for
         instructions if you do not have this)
    a. Download the Play binary from http://www.playframework.com/download
         (I used version 2.10)
    b. Unzip it into the folder of your choice.
    c. include the play executable in your path in order to allow it to be used
         from the command line
       i. cd /usr/bin
       ii. sudo ln -s /play/installation/dir/play
    d. To verify play is working. Type play help. You should get a play screen
5.) Download the code - https://github.com/osintegrators/ScalaGranny (There is
       a zip button towards the top left of the screen
6.) Change directory to the directory that you would like the code to reside
       and unzip the code
    a. unzip ~/Downloads/ScalaGranny-master.zip
7.) Run the program
    a. cd ScalaGranny-master
    b. play run
8.) Go to a browser and type http://localhost:9000 to get to the site. If all
    is successful, you will get a screen prompting you to Apply a script to the
    database. Run this script and the application is now up and running.
