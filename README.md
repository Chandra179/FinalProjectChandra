## Informasi 
Chandra <br>
Kode peserta : `JVSB001ONL010` <br>
Github : `https://github.com/Chandra179/FinalProjectChandra` <br>


<br>


## Link aplikasi
[Demo aplikasi heroku](https://bus-alexandria.herokuapp.com/swagger-ui.html)


<br>


## Api endpoint

1. agency-controller
2. auth-controller
3. bus-controller
4. home-controller
5. reservation-controller `untuk pemesanan tiket`
6. stop-controller
7. ticket-controller
8. trip-controller
9. trip-schedule-controller
10. user-controller `pendaftaran user baru`

	
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
2. `pg_dump --host=<host> --port=<port> --username=<username> --password --dbname=<database> > V1.0__ddl.sql`
3. masukan file `V1.0__ddl.sql` kedalam `src/main/resources/db/migration`


<br>


## Dependencies
> java 8, spring JPA, spring Security, spring Web, spring Devtools, PostgreSQL, Swagger, Flyway, Lombok, JWT


