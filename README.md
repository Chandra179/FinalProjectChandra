## Informasi
Chandra <br>
Kode peserta : `JVSB001ONL010` <br>
Github : `https://github.com/Chandra179/FinalProjectChandra` <br>


<br>


## Link aplikasi
[Demo aplikasi heroku](https://bus-alexandria.herokuapp.com/swagger-ui.html)


<br>


## Akses Api endpoint

ROLE_ADMIN <br>
- mempunyai semua akses ke semua api endpoints <br><br>

ROLE_USER <br>
    
    User controller
    	signup 

    Auth controller
		get current user ticket 
		book new ticket 

	Trip Controller
		get all trip 
		get trip by fare 
		get trip by journey time 
		get trip by stop (source - destination)
		get trip by bus id 
		get trip by destination stop 
		get trip by source stop 
		get trip by agency 

	TripSchedule Controller
		get all trip schedule
		get trip schedule by id
	
	
<br>



## Konfigurasi database postgres (HEROKU)
1. tambah `heroku postgres` addons pada heroku
2. navigate ke `https://data.heroku.com/datastores` > settings > view credentials
3. update application properties sesuai dengan credentials

> spring.datasource.url=jdbc:postgresql://[ host ]:5432/[ database ] <br>
spring.datasource.username=[ user ] <br>
spring.datasource.password=[ password ] <br>


<br>


## Generate schema database (HIBERNATE)
1. untuk pembuatan schema pertama kali, set `spring.jpa.hibernate.ddl-auto=update`
2. setelah selesai, ubah `spring.jpa.hibernate.ddl-auto=validate`, untuk melakukan validasi spring JPA dengan database schema


<br>


## Database Migration (FLyWay) (PostgreSQL)
1. export database schema dari database 
2. PROD `pg_dump --host=<host> --port=<port> --username=<username> --password --dbname=<database> > V1.0__ddl.sql`
3. masukan file `V1.0__ddl.sql` kedalam `src/main/resources/db/migration`


<br>


## Dependencies
> java 8, spring JPA, spring Security, spring Web, spring Devtools, PostgreSQL, Swagger, Flyway, Lombok, JWT


