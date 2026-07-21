# 🛠️ RanjanTasker

RanjanTasker is a full-stack task marketplace inspired by platforms such as Airtasker. It connects people who need help completing everyday tasks with local taskers looking for work.

The application supports two account types:

- **Posters** can create and manage tasks, set budgets and locations, and review applicants.
- **Taskers** can browse opportunities, filter and save tasks, apply for work, and maintain a profile with a CV.

The project was built with **Java 21**, **Spring Boot**, **Thymeleaf**, **Spring Security**, **JPA/Hibernate**, and **MySQL**.

> **Live demo:**
> https://ranjantasker.onrender.com/

---

## ✨ Features

### Authentication and accounts

- Secure registration and login using Spring Security
- BCrypt password hashing
- Role-based access for Poster and Tasker accounts
- Separate profile and dashboard experiences for each account type

### Poster features

- Create task listings with a title, description, budget, location, work type, and work arrangement
- View and manage posted tasks
- Edit or delete existing tasks
- Review the taskers who have applied
- Open applicant profiles and download submitted CVs

### Tasker features

- Browse available tasks
- Search by task title and location
- Filter opportunities by employment type, location arrangement, and date posted
- Save tasks for later
- Apply to multiple different tasks
- Upload a profile photo and CV
- View previously saved and applied-for tasks

### Application features

- Responsive Thymeleaf user interface
- Role-based navigation and protected routes
- Persistent MySQL storage through Spring Data JPA and Hibernate
- Rich-text task descriptions using Summernote
- File upload support for profile photographs and CVs
- Composite database constraints that prevent duplicate applications to the same task

---

## ⚙️ Tech Stack

| Technology | Purpose |
|---|---|
| **Java 21** | Primary programming language |
| **Spring Boot 3** | Application framework and embedded web server |
| **Spring MVC** | Controllers, routing, and form handling |
| **Spring Security** | Authentication, password hashing, and role-based authorization |
| **Thymeleaf** | Server-side HTML rendering |
| **Spring Data JPA** | Repository and persistence layer |
| **Hibernate** | Object-relational mapping and schema integration |
| **MySQL** | Relational database |
| **Bootstrap** | Responsive layout utilities and components |
| **HTML / CSS / JavaScript** | Frontend structure, styling, and interactions |
| **Summernote** | Rich-text task description editor |
| **Maven** | Dependency management and build tooling |
| **Docker** | Production deployment packaging |
| **Render** | Planned application hosting |
| **Aiven** | Hosted MySQL database |

---

## 🏗️ Project Structure

```text
src/
├── main/
│   ├── java/com/shraddha/ranjantasker/
│   │   ├── config/       # Security and application configuration
│   │   ├── controller/   # MVC controllers and routes
│   │   ├── entity/       # JPA entities
│   │   ├── repository/   # Spring Data repositories
│   │   └── services/     # Business logic and service classes
│   └── resources/
│       ├── static/       # CSS, JavaScript, images, and other assets
│       ├── templates/    # Thymeleaf templates
│       └── application.properties
├── test/
pom.xml
mvnw
mvnw.cmd
```

---

## 🚀 Running Locally

### Prerequisites

Install:

- Java 21 or newer
- Git
- MySQL, or access to a hosted MySQL database

Maven does not need to be installed separately because the repository includes the Maven Wrapper.

Check your installation:

```powershell
java --version
git --version
```

### 1. Clone the repository

```powershell
git clone https://github.com/shraddharanjan/ranjantasker.git
cd ranjantasker
```

### 2. Configure the application

The application reads database credentials from environment variables.

`src/main/resources/application.properties` should contain:

```properties
spring.application.name=ranjantasker

spring.datasource.url=${DATABASE_URL}
spring.datasource.username=${DATABASE_USERNAME}
spring.datasource.password=${DATABASE_PASSWORD}

spring.jpa.hibernate.ddl-auto=${DDL_AUTO:update}
spring.jpa.show-sql=false

server.address=0.0.0.0
server.port=${PORT:8080}
```

### 3. Set the database variables

For local MySQL:

```powershell
$env:DATABASE_URL="jdbc:mysql://localhost:3306/ranjantasker?useSSL=false&serverTimezone=UTC"
$env:DATABASE_USERNAME="root"
$env:DATABASE_PASSWORD='your-local-mysql-password'
$env:PORT="8080"
```

For Aiven MySQL:

```powershell
$env:DATABASE_URL="jdbc:mysql://YOUR_AIVEN_HOST:YOUR_AIVEN_PORT/defaultdb?sslMode=REQUIRED"
$env:DATABASE_USERNAME="avnadmin"
$env:DATABASE_PASSWORD='your-aiven-database-password'
$env:PORT="8080"
```

Use single quotes around the password in PowerShell when it contains characters such as `$`.

### 4. Start the application

On Windows:

```powershell
.\mvnw.cmd clean spring-boot:run
```

On macOS or Linux:

```bash
./mvnw clean spring-boot:run
```

Open:

```text
http://localhost:8080
```

---

## 🗄️ Database

The main tables include:

- `users`
- `users_category`
- `requester_account`
- `task_seeker_account`
- `task_post`
- `task_location`
- `task_seeker_apply`
- `task_seeker_save`

The application prevents a tasker from applying to the same task more than once through a composite unique constraint on:

```text
(user_id, task)
```

A tasker can still apply to multiple different tasks.

---

## ☁️ Deployment with Render and Aiven

The recommended deployment setup is:

```text
GitHub repository
       ↓
Render Docker web service
       ↓
Aiven hosted MySQL database
```

### Required Render environment variables

| Variable | Example |
|---|---|
| `DATABASE_URL` | `jdbc:mysql://HOST:PORT/defaultdb?sslMode=REQUIRED` |
| `DATABASE_USERNAME` | `avnadmin` |
| `DATABASE_PASSWORD` | Aiven database password |
| `DDL_AUTO` | `none` |

Render supplies the `PORT` environment variable automatically.

### Docker deployment

The repository can be deployed using a root-level `Dockerfile`:

```dockerfile
FROM eclipse-temurin:21-jdk AS build

WORKDIR /app
COPY . .

RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:21-jre

WORKDIR /app
COPY --from=build /app/target/ranjantasker-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 10000
ENTRYPOINT ["java", "-jar", "app.jar"]
```

On Render:

1. Create a new **Web Service**.
2. Connect this GitHub repository.
3. Select the `main` branch.
4. Choose **Docker** as the runtime.
5. Add the environment variables listed above.
6. Deploy the service.

---

## 📁 File Upload Note

Profile photographs and CVs are currently written to the application filesystem.

Free Render services use an ephemeral filesystem, so uploaded files can disappear after a restart or redeployment. For a production version, these files should be moved to persistent object storage such as:

- Amazon S3
- Cloudinary
- Supabase Storage
- Azure Blob Storage

The database should then store the uploaded file URL rather than a local filesystem path.

---

## 🔐 Security Notes

Do not commit:

- Database passwords
- `.env` files
- SQL database backups
- Uploaded CVs
- User photographs

Recommended `.gitignore` entries:

```gitignore
target/
.env
*.log
*.sql
photos/
```

---

## 🧪 Suggested Test Flow

Before deploying a new version, test that:

1. A Poster can register and log in.
2. A Tasker can register and log in.
3. A Poster can create, edit, and delete a task.
4. A Tasker can browse, save, and apply for tasks.
5. A Tasker can apply to multiple different tasks.
6. A Poster can view applicants for their own tasks.
7. Profile photo and CV upload validation works.
8. Role-protected pages cannot be opened by the wrong account type.
9. Logout clears the user session.

---

## 🔭 Future Improvements

- Move user uploads to persistent cloud storage
- Add email verification and password reset
- Add task categories and richer search filters
- Add messaging between Posters and Taskers
- Add application status tracking
- Add automated unit and integration tests
- Add Flyway or Liquibase database migrations
- Add CI/CD checks with GitHub Actions
- Add pagination for large task lists

---

## 👩‍💻 Author

**Shraddha Ranjan**

Built as a full-stack Java and Spring Boot portfolio project.
