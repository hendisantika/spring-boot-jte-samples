# spring-boot-jte-samples

[![CI](https://github.com/hendisantika/spring-boot-jte-samples/actions/workflows/ci.yml/badge.svg)](https://github.com/hendisantika/spring-boot-jte-samples/actions/workflows/ci.yml)
![Java](https://img.shields.io/badge/Java-25-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1.2-brightgreen)
![jte](https://img.shields.io/badge/jte-3.2.4-blue)

Spring Boot samples using [jte](https://jte.gg) (Java Template Engine) for server-side rendered HTML.

## Modules

| Module                     | Description                                                                 | Port |
|----------------------------|-----------------------------------------------------------------------------|------|
| [`jte`](jte)               | Minimal page rendered with jte from a `Page` record.                        | 8080 |
| [`jte-forms`](jte-forms)   | Form with Bean Validation, persisted to PostgreSQL via Spring Data JDBC.     | 8080 |

Each module is a standalone Maven project with its own Maven wrapper.

## Tech stack

- Java 25
- Spring Boot 4.1.2 (Web MVC, Validation, Data JDBC, Docker Compose support)
- jte 3.2.4 + `jte-spring-boot-starter-4` + `jte-maven-plugin` (templates precompiled at build time)
- Lombok
- PostgreSQL 17 (jte-forms)

## Prerequisites

- JDK 25
- Docker (for `jte-forms`, which starts PostgreSQL from `compose.yml` automatically)

## Running

```bash
# Simple jte page
cd jte
./mvnw spring-boot:run
# open http://localhost:8080

# Form sample (Spring Boot starts the Postgres container from compose.yml)
cd jte-forms
./mvnw spring-boot:run
# open http://localhost:8080
```

`jte-forms` endpoints:

| Method | Path    | Description                         |
|--------|---------|-------------------------------------|
| GET    | `/`     | Show the personal information form  |
| POST   | `/save` | Validate and save the submitted user |

Templates live in `src/main/jte`. `gg.jte.development-mode: true` in `application.yml` enables hot reload; generated classes go to `jte-classes/` (git-ignored).

### Running the packaged jar (`prod` profile)

Development mode needs the template sources, so run the jar with the `prod` profile. It turns off development mode and uses the templates precompiled by `jte-maven-plugin`:

```bash
cd jte
./mvnw package
java -jar target/jte-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod
```

Spring Boot leaves Docker Compose support out of the jar, so for `jte-forms` you start PostgreSQL yourself (e.g. `docker compose up -d`). The `prod` profile connects to it with these environment variables:

| Variable      | Default                                  |
|---------------|------------------------------------------|
| `DB_URL`      | `jdbc:postgresql://localhost:5433/form`  |
| `DB_USERNAME` | `yu71`                                   |
| `DB_PASSWORD` | `53cret`                                 |

```bash
cd jte-forms
docker compose up -d
./mvnw package
java -jar target/jte-forms-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod
```

## Building and testing

```bash
cd jte && ./mvnw verify
```

The `jte-forms` context test needs a running PostgreSQL (Docker Compose support is skipped in tests). Start one and point the app at it:

```bash
docker run -d --name jte-pg -e POSTGRES_DB=form -e POSTGRES_USER=yu71 -e POSTGRES_PASSWORD=53cret \
  -p 5433:5432 postgres:17.7-alpine3.21

cd jte-forms
SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5433/form \
SPRING_DATASOURCE_USERNAME=yu71 \
SPRING_DATASOURCE_PASSWORD=53cret \
./mvnw verify
```

## CI/CD

- **GitHub Actions** ([`.github/workflows/ci.yml`](.github/workflows/ci.yml)): builds and tests both modules on Temurin JDK 25 for every push and pull request to `main`, with a PostgreSQL service container for `jte-forms`.
- **Dependabot** ([`.github/dependabot.yml`](.github/dependabot.yml)): weekly updates for Maven dependencies (Spring Boot, jte and Maven plugins grouped), GitHub Actions and the `jte-forms` Docker Compose image.

## License

[MIT](LICENSE)
