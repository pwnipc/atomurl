# atomurl by <a href="https://github.com/Chal13W1zz">Chalie</a>

A free and opensource link shortener.

Works like bit.ly

### Live App <a href="https://at0murl.herokuapp.com/"> View Here </a>

### Live Release
Live  : <a href="https://github.com/Chal13W1zz/atomurl/releases">Download Here</a>
Run the package using `java -jar atomurl<version>.jar`

## Projects Home page
![image](https://user-images.githubusercontent.com/60155767/163875893-669f96f8-5157-4b8e-b34a-5778cd2d68fe.png)


## Dependencies
1. [junit-jupiter-api] 'org.junit.jupiter:junit-jupiter-api:5.8.1'
2. [junit-jupiter-engine] 'org.junit.jupiter:junit-jupiter-engine:5.8.1'
3. [spark-core] 'com.sparkjava:spark-core:2.9.3'
4. [slf4j-simple] 'org.slf4j:slf4j-simple:1.7.32'
5. [gson] 'com.google.code.gson:gson:2.8.9'
6. [sql2o]  group: 'org.sql2o', name: 'sql2o', version: '1.5.4'
7. [postgresql] group: 'org.postgresql', name: 'postgresql', version: '42.2.2'    

## Development setup
#### Cloning and setting up a local development database
- `git clone https://github.com/Chal13W1zz/atomurl.git && cd atomurl && psql < drop.sql && psql < create.sql`
- open the project using your favourite IDE
- edit postgresql username, password and database in App.java 

NB: use Gradle For development and Maven For Deployment



## Clone and run With Gradle
- `git clone https://github.com/Chal13W1zz/atomurl.git && cd atomurl && gradle run`
- open http://localhost:4567 on your favourite browser and enjoy :)


## Technologies Used
* Java
* Spark
* Handlebars
* PostgreSQL
* Gradle
* Maven
* Bootstrap
* Fontawesome
* Heroku


## Contributions
If you'd like to contribute.
- Fork the repo
- Create a new branch (git checkout -b feature)
- Make the appropriate changes in the files
- Add changes to reflect the changes made
- Commit your changes (git commit -m 'Improve/Add feature')
- Push to the branch (git push origin feature)
- Create a Pull Request
[Make sure your code is properly commented]
If you find a bug, kindly open an issue <a href="https://github.com/Chal13W1zz/atomurl/issues/new">Here</a> .
If you'd like to request a new function, feel free to do so by opening an issue <a href="https://github.com/Chal13W1zz/atomurl/issues/new">Here</a>.



## Known Bugs
N/A

## <a href="https://github.com/Chal13W1zz/atomurl/blob/main/LICENSE">Licence</a>
MIT License

Copyright (c) 2022, <a href="https://github.com/Chal13W1zz">Chalie</a>
All rights reserved.

