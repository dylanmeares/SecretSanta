# Part one

### I designed a program that takes a file that contains a list of family members. Each family member should be on a new line as shown below:

#### Jack<br/> Jill<br/> Julie<br/> Joe<br/>

##### To compile the program, naviagate to the src directory and run the following:

```javac -cp . mlb/*.java```

##### To run the program, run the following:

```java -cp . <filename>```


# Part two

### I modified the program to account for past santas of the participants.  No participant will be able to have the same Secret Santa more than once every 3 years.<br/>  The program now takes 2 parameters, the file followed by the number of years you would like to generate Secret Santas for.  If only the filename is given the program will generate Santas for 1 year.

##### To generate Santas for 3 years, run the following:

```java -cp . <filename> 3```
