## Database Migration (FLyWay) (PostgreSQL)

Untuk migrasi database diwajibkan export database schema (DDL) terlebih dahulu
	
`pg_dump -U "db_user" -s db_bus`
	
1. dump database schema
2. masukan schema kedalam file V1.0__ddl.sql
3. masukan file V1.0__ddl.sql kedalam "src/main/resources/db/migration"
4. Insert data
5. buat file V1.1__data.sql
6. isi file dengan INSERT(data)


## Database Migration (FLyWay) (PostgreSQL)
