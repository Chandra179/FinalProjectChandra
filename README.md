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


## Format input API endpoint

1.  POST `/api/v1/user/signup` <br>
   `email : chandra@gmail.com` <br>
   `firstName : chandra` <br>
   `lastName : chandra` <br>
   `mobileNumber : chandra` <br>
   `password : chandra123` <br>
   `role : [ROLE_ADMIN]` role hanya ada 2 `ROLE_USER / ROLE_ADMIN` jika ingin mengakses ke semua endpoint pakai `ROLE_ADMIN` pastikan `huruf kapital semua` <br>
   `username : chandra` username untuk login/autentikasi <br><br>
   
2.  POST `/api/auth` untuk autentikasi user/login <br>
   `username : chandra` <br> username untuk login/autentikasi <br><br>
   `password : chandra123` <br><br>
    
3.  POST `/api/v1/reservation/bookticket` <br>
   `journeyDate : 12/01/2023` wajib pakai slash <br>
   `tripScheduleId : Id TripSchedule` ID trip schedule <br><br>
    Input tidak valid jika TripSchedule tidak mempunyai Tanggal keberangkatan yang sama dengan `journeyDate` <br><br>

4.  POST `/api/v1/reservation/fare` <br>
   `lowerValue : 20000` maskimal harga tiket <br>
   `upperValue : 1000` minimal harga tiket <br><br>
   Pembelian tiket dengan menentukan harga minimal-maksimal <br><br>

5.  POST `/api/v1/reservation/journeytime` <br>
   `lowerValue : 600` maskimal waktu perjalanan dalam menit <br>
   `upperValue : 10` minimal waktu perjalanan dalam menit <br><br>
   
6.  POST `/api/v1/reservation/stop` <br>
   `lowerValue : 2` Id sourceStop/pemberangkatan halte <br>
   `upperValue : 5` Id destStop/pemberhentian halte <br><br>
   
7.  POST `/api/v1/trip` <br>
   `agencyId : 1` Id agency <br>
   `busId : 1` Id bus <br>
   `destStopId : 1` Id destStop/pemberhentian <br>
   `fare : 100000` Biaya trip <br>
   `journeyTime : 300` Waktu trip dalam menit <br>
   `sourceStopId : 2` Id sourceStop/pemberangkatan <br><br>

8.  POST `/api/v1/tripschedules` <br>
   `availableSeats : 10` jumlah kursi bus <br>
   `tripDate : 25/01/2023` wajib pakai slash, dan tanggal input harus lebih besar dari hari ini <br>
   `tripDetail : 1` Id trip <br><br>


## Instalasi lombok (Eclipse)
[Instalasi Lombok](https://www.youtube.com/watch?v=8DZub6SybKE&t=616s)


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


