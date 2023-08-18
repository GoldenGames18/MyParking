# MyParking


Install


Install docker Mysql
```cmd
docker pull mysql
```


And juste run this command to create your database
````cmd
docker run -d -e MYSQL_ROOT_PASSWORD=rootroot -e MYSQL_DATABASE=parking --name mysqldb -p 3307:3306 mysql:8.0
````

Run program and find swagger page
````
http://localhost:8000/swagger-ui/index.html#/
````