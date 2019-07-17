# PPW-2019.2
![Year][year] ![Id][id] ![T-P-I][tpi]

Repository for the code written in the discipline of
Web Development (*Programação para Web*) at the 
Federal University of ABC (UFABC).

[year]: https://flat.badgen.net/badge/year/2019.2/blue
[id]: https://flat.badgen.net/badge/id/MCZA019-13/orange
[tpi]: https://flat.badgen.net/badge/T-P-I/2-2-4/grey

## Schedule

- **03/06/2019** *(Theory)*: Personal presentation, Introduction
      and Project presentation.
- **05/06/2019** *(Laboratory)*: [Object-oriented Programming Review].
- **10/06/2019** *(Theory)*: [Database Review and JDBC].
- **12/06/2019** *(Laboratory)*: Database.
- **17/06/2019** *(Theory)*: [Hibernate].
- **19/06/2019** *(Laboratory)*: Project documentation review.
- **24/06/2019** *(Theory)*: [Gradle].
- **26/06/2019** *(Laboratory)*: Project first report review.
- **01/07/2019** *(Theory)*: [Spring MVC].
- **03/07/2019** *(Laboratory)*: Project database review.
- **10/07/2019** *(Theory)*: **First test**.
- **15/07/2019** *(Theory)*: HTML.

[Object-oriented Programming Review]: laboratory/2019.06.05/
[Database Review and JDBC]: laboratory/2019.06.10/
[Hibernate]: laboratory/2019.06.17/
[Gradle]: laboratory/2019.06.24/
[Spring MVC]: laboratory/2019.07.01/

## Using PostgreSQL with Docker

This repository has a `docker-compose.yml` that setup the [PostgreSQL],
[pgAdmin] and [pgweb] from the DockerHub. To use it, make sure you have
[Docker] and [Docker Compose] installed, and follow the commands below.

```bash
# To run the database in detached mode.
$ sudo docker-compose up -d
# To finish the database container.
$ sudo docker-compose down
```

Once the containers have started, you can access pgAdmin at
`https://localhost:15432` with the user `postgres@postgresql.org`
and password `postgres`. The PostgreSQL server is available at 
`test-postgres:5432` with both user and password `postgres`.
The pgweb client is available at `https://localhost:8081`,
with no authentication needed.

[PostgreSQL]: https://www.postgresql.org/
[pgAdmin]: https://www.pgadmin.org/
[pgweb]: https://sosedoff.github.io/pgweb/
[Docker]: https://www.docker.com/get-started
[Docker Compose]: https://docs.docker.com/compose/

## Bibliography

- Luckow, D. H.; De Melo, A. A. **Programação Java para a Web**.
  Editora Novatec, 2015.
- Duckett, J. **Javascript & Jquery**: Interactive Front-End 
  Web Development. Wiley, 2014.
- Fields, D. K; Kolb, M. A. **Web Development with JavaServer 
  Pages**. Manning Publications, 2001.
- Mecenas, I. **Java 2 - Fundamentos, Swing e JDBC**.
  Editora Alta Books, 2003.
- Sebesta, R. W. **Programming the World Wide Web**.
  Pearson Addison Wesley, 2014.
- Downey, T. **Guide to Web Development with Java**:
  Understanding Website Creation. Springer London, 2012.

## License

> You can check out the full license [here](LICENSE).

This repository is licensed under the terms of the **MIT** license.
