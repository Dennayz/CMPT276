# CMPT276 - Final Project (Group 6)

## Maven-getting-started

### Install Apache Maven
- Make sure to have Apache Maven installed on your system
    - A detailed explanation and installation guide[ here](https://maven.apache.org/install.html "This link takes you to Apache Maven installation guide!")
- If Maven is already installed, skip this step

## How To Run Game

```sh
$ git clone git@csil-git1.cs.surrey.sfu.ca:group-6/project.git
$ cd project
$ mvn clean
$ mvn package
```
wait till build is finished
```sh
$ java -cp target/untitled-1.0-SNAPSHOT.jar game.Main
```

## How To Test Game

### Set up Test Environment
- Change directory to the maven project:
```sh
$ cd project
```

- Inside the project folder, there should be the pom.xml file
- This file needs to be present for maven to test the project
- All maven dependencies are in the pom.xml

### Run Tests
- Make sure you are in the directory with the pom.xml file
- To run all tests in the maven project, execute this line:
- **Do not try to play the game or touch your computer until tests finishes!**
```sh
$ mvn test
```
- To run individual tests:
```sh
$ mvn -Dtest=<Insert test name> test
```
- All individual test classes are found below
- Copy and paste a name into the "insert test name" field without the "<>" brackets
- For example:
```sh
$ mvn -Dtest=CharacterTest test
```
## Generate JavaDocs
### Production Code JavaDocs
- After building the game, If Javadocs are requested, run this command
- Make sure you are in the directory where the pom.xml file is present
- This command will generate production code JavaDocs
```sh
$ mvn javadoc:javadoc
```
- When the build finishes, execute this command to open the html
```sh
$ open target/site/apidocs/allpackages-index.html 
```
### Test Code JavaDocs
- If JavaDocs for test codes are desired as well, run this command
- Make sure you are still in the directory where the pom.xml file is present
```sh
$ mvn javadoc:test-javadoc
```
- This command will generate JavaDocs for the test codes
- When the build finishes, execute this command to open the html
```sh
$  open target/site/testapidocs/allpackages-index.html 
```

## Artifacts
- The Jar file for the game and the JavaDocs are pushed into the repository
- They are found under the artifacts folder
- You can run the game like this as well
```sh
$ java -cp artifacts/untitled-1.0-SNAPSHOT.jar game.Main
```
- accessing the JavaDocs in the artifacts folder as well
```sh
$ open artifacts/apidocs/allpackages-index.html
```

## Individual Test Classes
- CharacterTest
- EnemyTest
- HeroTest
- UtilTest
- ImageLoaderTest
- GameTimeTest
- GraphicTest
- EndLoseScreenTest
- EndWinScreenTest
- MapTest

## Documentation
- For more information on Apache maven
    - [Apache Maven](https://maven.apache.org/what-is-maven.html "This link takes you to Apache Maven")

```sh
[
    {"unqiue value": "occurences"},
    {"unqiue value": "occurences"},
    {"unqiue value": "occurences"},
    {"unqiue value": "occurences"},
    ...
]
```
```sh
if (count > 10) {
    return 0;
}
```

| Resources | POST 
              (Create)|
| --------- | ---- |
| datasets/id/header/frequency |  |
| datasets/id/frequency | content |

