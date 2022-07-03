### Informasi
Chandra <br>
Kode peserta : `JVSB001ONL010` <br>
Github : `https://github.com/Chandra179/FinalProjectChandra`




### Link aplikasi
[Demo aplikasi heroku](https://bus-alexandria.herokuapp.com/swagger-ui.html)




### Akses Api endpoint

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
	
	




### Database Migration (FLyWay) (PostgreSQL)

Untuk migrasi database diwajibkan export database schema (DDL) terlebih dahulu sebelum INSERT DATA
	
DEV  : `pg_dump -U postgres -s db_bus > V1.0__ddl.sql` <br>
PROD : `pg_dump --host=<host> --port=<port> --username=<username> --password --dbname=<database> > dump_file.sql`
	
1. dump database schema
2. masukan file V1.0__ddl.sql kedalam "src/main/resources/db/migration"
3. Insert data
4. buat file V1.1__data.sql
5. isi file dengan INSERT(data)

### Production
1. database `heroku postgres`
  
### Development
1. database `local database`

