# Spring Boot Multi-Environment Database Demo

Java Technologies — Lab 2 homework.

The application demonstrates Spring Boot profiles, JDBC access, typed configuration binding,
conditional bean creation with SpEL, command-line property precedence, and a custom Actuator
endpoint.

## Environment design

| Profile | Database engine | Host | Port | Database | User |
| --- | --- | --- | ---: | --- | --- |
| `dev` | PostgreSQL | localhost | 5433 | java_lab_dev | dev_user |
| `prod` | MySQL | localhost | 3307 | java_lab_prod | prod_user |

Profile-specific settings are in `application-dev.yml` and `application-prod.yml`.
Each value can also be overridden with OS environment variables such as `DEV_DB_HOST`,
`DEV_DB_PORT`, `PROD_DB_USER`, and so on.

## Why `@ConfigurationProperties`

The database has a coherent group of settings: engine, host, port, database name, username,
and password. `@ConfigurationProperties(prefix = "app.database")` binds them into the typed
`DatabaseProperties` object. This is cleaner than scattering six `@Value` annotations across
the code, gives automatic type conversion, and makes the configuration easier to reuse and test.

## `@Profile` + `@ConditionalOnExpression`

`DatabaseConfiguration` combines both mechanisms:

- `dev` plus `app.database.engine == postgresql` creates the PostgreSQL data source.
- `prod` plus `app.database.engine == mysql` creates the MySQL data source.

This means a profile alone is not enough: the engine condition must also match.

## Start the two databases

Docker Desktop must be running.

```powershell
docker compose up -d
docker compose ps
```

The initialization scripts create the same `environment_marker` table in both databases,
but with different values so the active connection is easy to prove.

## Build and test

```powershell
mvn clean test
mvn package
```

The test suite also proves that a command-line argument overrides the value from
`application.yml`.

## Development profile

```powershell
java -jar target/spring-boot-multi-environment-0.0.1-SNAPSHOT.jar --spring.profiles.active=dev
```

`DatabaseStartupRunner` queries `environment_marker` and prints the database values at startup.
For `dev`, the message identifies the PostgreSQL development database.

## Production profile

Stop the previous application, then run:

```powershell
java -jar target/spring-boot-multi-environment-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod
```

The same code now connects to MySQL and prints the production row.

## Command-line precedence

`application.yml` defines:

```yaml
app:
  demo:
    precedence-message: from-application-yml
```

Override it at launch:

```powershell
java -jar target/spring-boot-multi-environment-0.0.1-SNAPSHOT.jar --spring.profiles.active=dev --app.demo.precedence-message=from-command-line
```

The startup log prints `from-command-line`, proving command-line arguments have higher
precedence than `application.yml`. The same property-source precedence applies to
`app.database.*` values as well.

## Custom Actuator endpoint

While the application is running:

```powershell
curl.exe http://localhost:8080/actuator/database
```

It reports:

- active Spring profile
- configured engine, host, port, and database name
- actual JDBC URL
- database product and version
- connected database user

The password is intentionally not exposed.

Standard endpoints remain available at `/actuator/health` and `/actuator/info`.

## Clean up

```powershell
docker compose down
```
