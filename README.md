# Part one

### I designed a program that takes a file that contains a list of family members. Each family member should be on a new line as shown below:

#### Jack<br/> Jill<br/> Julie<br/> Joe<br/>

##### To compile the program, naviagate to the src directory and run the following:

```javac -cp . mlb/*.java```

##### To run the program, run the following:

```java -cp . <filename>```


# Part two

### I modified the program to account for past santas of the participants.  No participant will be able to have the same Secret Santa more than once every 3 years.<br/> <br/>  The program now takes 2 parameters, the file followed by the number of years you would like to generate Secret Santas for.  If only the filename is given the program will generate Santas for 1 year.

##### To generate Santas for 3 years, run the following:

```java -cp . <filename> 3```

# Part three

### I modified the program further to consider the constraint of Secret Santas being from the immediate family.<br/> <br/>   I believe it is only possible to meet this requirement if no immediate families makes up more than half of the total family, so I implemented this constraint in such that is is only applied when this is true.<br/> <br/>

My program assumes that any participant with the same last name is from the same immediate family.<br/> <br/>

Each family member's first and last name should be on a new line as shown below:

#### Jack Lastname<br/> Jill Lastname<br/> Julie Lastname<br/> Joe Lastname<br/>

##### To run the program Santas for 3 years, run the following:
```java -cp . <filename> 3```
