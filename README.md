## Database Migration (FLyWay) (PostgreSQL)

Untuk migrasi database diwajibkan export database schema (DDL) terlebih dahulu sebelum INSERT DATA
	
`pg_dump -U postgres -s db_bus > V1.0__ddl.sql`
	
1. dump database schema
2. masukan file V1.0__ddl.sql kedalam "src/main/resources/db/migration"
3. Insert data
4. buat file V1.1__data.sql
5. isi file dengan INSERT(data)


## Database Migration (FLyWay) (PostgreSQL)
