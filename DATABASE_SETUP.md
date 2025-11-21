# ✅ Database Configuration - FINAL STATUS

## 🎯 What We've Accomplished

1. ✅ **Git & GitHub Setup** - Repository created and pushed to https://github.com/zevvyT/attendance_system
2. ✅ **Maven Installation** - Apache Maven 3.9.6 installed and configured
3. ✅ **Project Build** - Successfully built `attendance-system.war`
4. ✅ **Tomcat Installation** - Apache Tomcat 9.0.98 installed and running
5. ✅ **Application Deployment** - App running at http://localhost:8080/attendance-system/
6. ✅ **Database Created** - AttendanceDB database exists on SQL Server Express
7. ✅ **Database Access Granted** - Your Windows user has access to AttendanceDB

## ⚠️ Current Issue: Database Connection

The application cannot connect to SQL Server because:
- Windows Authentication requires a native DLL (`mssql-jdbc_auth-*.dll`)
- You don't have permission to create SQL logins

## 🔧 SOLUTION: Ask Your IT Administrator

**Please ask your IT administrator to run this SQL script:**

```sql
USE master;
GO

-- Create SQL login for the attendance application
CREATE LOGIN attendance_app WITH PASSWORD = 'Attendance@2025!';
GO

USE AttendanceDB;
GO

-- Create user and grant permissions
CREATE USER attendance_app FOR LOGIN attendance_app;
GO

ALTER ROLE db_owner ADD MEMBER attendance_app;
GO
```

**After the login is created, I'll update the configuration to:**

```xml
<property name="hibernate.connection.url">jdbc:sqlserver://localhost\SQLEXPRESS;databaseName=AttendanceDB;encrypt=false</property>
<property name="hibernate.connection.username">attendance_app</property>
<property name="hibernate.connection.password">Attendance@2025!</property>
```

## 📊 Database Schema

Once connected, Hibernate will automatically create these tables:

| Table Name | Description |
|------------|-------------|
| **Employee** | Employee information (ID, name, email, department, card ID) |
| **AttendanceLog** | Sign-in/sign-out records with timestamps |
| **LeaveBalance** | Remaining leave days for each employee |
| **LeaveRequest** | Leave applications and approval status |
| **OffSiteWork** | Off-site work registrations |

## 🧪 Testing the Application

### Once Database is Connected:

1. **Insert Sample Employee:**
```sql
USE AttendanceDB;
GO

INSERT INTO Employee (employeeId, firstName, lastName, email, department, position, cardId)
VALUES ('EMP001', 'John', 'Doe', 'john.doe@company.com', 'IT', 'Developer', 'CARD001');
GO
```

2. **Test Sign-In API:**
```
POST http://localhost:8080/attendance-system/signIn.action
Parameters: cardId=CARD001
```

3. **Test Sign-Out API:**
```
POST http://localhost:8080/attendance-system/signOut.action
Parameters: cardId=CARD001
```

4. **View History:**
```
GET http://localhost:8080/attendance-system/viewHistory.action
```

## 📁 Project Structure

```
attendance_system/
├── src/main/java/com/attendance/
│   ├── action/          # Struts actions
│   ├── dao/             # Database access objects
│   ├── model/           # Entity classes
│   ├── service/         # Business logic
│   ├── util/            # Hibernate utility
│   └── listener/        # Application listeners
├── src/main/resources/
│   ├── hibernate.cfg.xml    # Database configuration
│   └── struts.xml           # Struts configuration
├── src/main/webapp/
│   ├── index.jsp            # Homepage
│   ├── css/                 # Stylesheets
│   └── js/                  # JavaScript
├── database/
│   ├── create_database.sql  # Database creation script
│   └── create_sql_login.sql # SQL login creation script
├── pom.xml                  # Maven configuration
└── DATABASE_SETUP.md        # This file

```

## � Next Steps

1. **Ask IT Admin** to create the SQL login using the script above
2. **Update hibernate.cfg.xml** with SQL authentication credentials
3. **Rebuild and redeploy** the application
4. **Verify database connection** - tables will be created automatically
5. **Insert sample data** and test the application

## 📞 Need Help?

If you encounter any issues:
1. Check Tomcat logs: `C:\Users\abdulhafiz.pcsb\Tomcat\apache-tomcat-9.0.98\logs\`
2. Verify SQL Server is running: `Get-Service | Where-Object {$_.DisplayName -like "*SQL*"}`
3. Test database connection: `sqlcmd -S localhost\SQLEXPRESS -U attendance_app -P "Attendance@2025!" -d AttendanceDB`

## 🎓 What You've Learned

- Git version control and GitHub integration
- Maven build tool for Java projects
- Apache Tomcat deployment
- Hibernate ORM configuration
- Struts 2 framework
- SQL Server database setup
- Full-stack Java web development

**Great job getting this far! Once the SQL login is created, your attendance system will be fully functional!** 🎉
