# PIDReservation

## Database setup

I use docker to setup a mysql database.

To start the database, run 

```
docker run --name mysql  -e MYSQL_ROOT_PASSWORD=secret -e MYSQL_DATABASE=reservations -p 3306:3306 -d mysql:latest
```

To stop the database, run 

```
docker stop mysql
```

To delete the database, run

```
docker rm mysql
```

