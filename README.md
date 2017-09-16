# Cashback API

## Dependencies
  * Java 8
  * Gradle
  * MySQL
  
## Configuration
The following environment variables are used (found in `src/main/resources/application.properties`):

 | Variable Name | Description |
 | ------ | ------ |
 | **CLEARDB_DATABASE_URL** | JDBC URL to the database. 
 |  | Example: `jdbc:mysql://localhost:3306/cashback.` |
 |  | **Note: The database must exist** |
 | **DB_USERNAME** | The database username |
 | **DB_PASSWORD** | The database password |
 
 ## Migrations
 
 [Liquibase](http://www.liquibase.org/) is used for generating and executing database migrations. Migration XML files are located in `db/migrations` folder. `changelog_master.xml` lists all migrations that need to be executed, sorted by data.
 
 ### Updating the database to the latest version
 
 ```
 $ gradle updateDatabase
 ```
 
 This command is ran as part of ```$ gradle build```
 
 ### Adding a new migration
 
```
$ gradle addMigration -Pname={migration name here}
```

The command compares the entities from the package `com.cashback.api.entities` with the database and generates the necessary changes in a XML file with the specified name + a timestamp at the end of the file name. The migration name should not contain spaces (use `_` instead of space as a convention).

### Rolling back migrations

```
$ gradle rollbackDatabase -Pdate={date}
```

The command restores the database to the model it was at the specifed date. The date should be in the format `yyyy-MM-dd'T'HH:mm:ss`

## Building

```
$ gradle build
```

Compiles the Java classes and runs the ```updateDatabase``` command

## Running

```
$ gradle bootRun
```

The Spring Boot application is ran on the port specified in `application.properties` file as the property `server.port` (default is **8080**)

## Branching and pull requests

The following branching convention is used:

| Branch | Description |
| ------ | ------ |
| master | Used for production |
| dev | Used for dev/QA |
| Feature branch | Used for a separate feature. The name should be `CS-{Trello task ID}`. Example: `CS-30` |

When a feature is ready, a PR should be made to `dev`. The feature branch should not break the Gradle build.
The PR, branch and commit(s) should be attached to the Trello task manually. via (Power-Ups/GitHug option on the right when editing a task)
