# SpringMybatisGenericExample
This project aim for absolute beginners of Spring and Mybatis integration. I hope this project may helps about the questions
* How should I create a project (*core module* that I called) for a real-world project ?
* How can I design maintainable,reuseable and easy to test ?
* What things should be setup for real-world prject (caching,history support etc) ?
* How to create [Generic Type](https://docs.oracle.com/javase/tutorial/java/generics/types.html) service layer and repository layer methods ?

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

* [GIT](https://git-scm.com/)
* [JDK-8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* [Eclipse](https://www.eclipse.org/downloads/?)
* [ermaster plugin](https://sourceforge.net/projects/ermaster/) to open the [db design file](https://github.com/cataclysmuprising/SpringMybatisGenericExample/tree/master/SpringMybatisGenericExample/data/db/design)
* [Microsoft SQL Server(2014)](https://www.microsoft.com/en-US/download/details.aspx?id=42299)
* [Maven](https://maven.apache.org/)


### Installing
Go to the directory where you want to put the project source codes from Command prompt(Window) or Terminal(Unix) as
```
C:\Users\John>cd workpace\myprojects
```
and get this project from command line.

```
$ git clone https://github.com/cataclysmuprising/SpringMybatisGenericExample.git
```
or you can download manually from [project home page](https://github.com/cataclysmuprising/SpringMybatisGenericExample) and extract it.

Connect your MSSQL Server and run the sql scripts file
1. create_db.sql
2. create_table.sql
3. master_data.sql
4. sample_data.sql
from [scripts](https://github.com/cataclysmuprising/SpringMybatisGenericExample/tree/master/SpringMybatisGenericExample/data/db/scripts) folder

## Running

### Run with maven from command-line

Go to root directory of [Project](https://github.com/cataclysmuprising/SpringMybatisGenericExample/tree/master/SpringMybatisGenericExample/data/db/scripts) and run the maven command

```
mvn clean test
```

### Run from Eclipse IDE

1. Import project from IDE as *Existing Maven Projects* 
2. wait for the maven build (during this state, maven will download required dependencies)
3. right click on the project and **Run As > Maven test**

## Contributing

Contributions to the project can be done using pull requests. You will be asked to sign a contribution agreement after creating the first one.


## Author

* **Cataclysm** - *Senior Web Programmer* - [Programmer at T3K Technology](http://www.t3ktechnology.com/)

## License

This project is licensed  under the Apache License, Version 2.0 (the "License"). 
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.

## Acknowledgments

As I said *This project aim for absolute beginners..* because I am still a newbie on both Spring and Mybatis. So if you found some weakpoints,errors or bad pratices, please get a chance of helping a noob. Every suggestions would be really appreciated. If you have some questions about this project ,you can leave messages on this hub or you can reach me via direct email rage.cataclysm@gmail.com.

