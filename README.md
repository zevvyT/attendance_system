# Employee Attendance Dashboard System

A comprehensive web-based employee attendance management system built with **Java**, **Struts 2**, **Hibernate 5**, **Microsoft SQL Server**, and **Bootstrap 5**.

## Features

- ✅ **ID Card Sign-In/Out**: Simple card-based attendance tracking
- ✅ **Automated Absence Detection**: End-of-day processing to mark absences
- ✅ **Leave Management**: Submit, approve, and track leave requests
- ✅ **Off-Site Work Tracking**: Register and monitor remote work
- ✅ **Dual Dashboards**: Separate views for employees and managers
- ✅ **Real-Time Updates**: AJAX-powered interface for instant feedback
- ✅ **Responsive Design**: Bootstrap 5 for mobile-friendly UI

## Technology Stack

### Backend
- **Java 8+**
- **Struts 2.5.30** - MVC Framework
- **Hibernate 5.6.15** - ORM
- **Microsoft SQL Server** - Database
- **HikariCP** - Connection Pooling
- **Maven** - Build Tool

### Frontend
- **JSP** - Server-side rendering
- **Bootstrap 5.3.2** - CSS Framework
- **Vanilla JavaScript** - Client-side logic
- **Bootstrap Icons** - Icon library

## Prerequisites

Before you begin, ensure you have the following installed:

- **Java JDK 8 or higher**
- **Apache Maven 3.6+**
- **Microsoft SQL Server 2012 or higher**
- **Apache Tomcat 9.0+**
- **IDE** (IntelliJ IDEA, Eclipse, or VS Code with Java extensions)

## Database Setup

### 1. Create Database

Connect to your SQL Server instance and run:

```sql
CREATE DATABASE AttendanceDB;
GO

USE AttendanceDB;
GO
```

### 2. Configure Connection

Edit `src/main/resources/hibernate.cfg.xml` and update the following properties:

```xml
<property name="hibernate.connection.url">jdbc:sqlserver://localhost:1433;databaseName=AttendanceDB;encrypt=false</property>
<property name="hibernate.connection.username">YOUR_USERNAME</property>
<property name="hibernate.connection.password">YOUR_PASSWORD</property>
```

### 3. Auto-Generate Tables

Hibernate will automatically create the required tables on first run based on the entity annotations. The following tables will be created:

- `employees` - Employee master data
- `leave_balance` - Leave balance tracking
- `attendance_log` - Daily attendance records
- `leave_requests` - Leave request records
- `offsite_work` - Off-site work records

## Installation & Setup

### 1. Clone or Download the Project

```bash
cd attendance_system
```

### 2. Build the Project

```bash
mvn clean install
```

This will:
- Download all dependencies
- Compile Java classes
- Package the application as a WAR file

### 3. Deploy to Tomcat

#### Option A: Using Maven Tomcat Plugin

```bash
mvn tomcat7:run
```

The application will be available at: `http://localhost:8080/attendance`

#### Option B: Manual Deployment

1. Copy `target/attendance-system.war` to Tomcat's `webapps` directory
2. Start Tomcat
3. Access the application at: `http://localhost:8080/attendance-system`

## Project Structure

```
attendance_system/
├── pom.xml                                 # Maven configuration
├── src/
│   ├── main/
│   │   ├── java/com/attendance/
│   │   │   ├── model/                      # Hibernate entities
│   │   │   │   ├── Employee.java
│   │   │   │   ├── LeaveBalance.java
│   │   │   │   ├── AttendanceLog.java
│   │   │   │   ├── LeaveRequest.java
│   │   │   │   └── OffSiteWork.java
│   │   │   ├── dao/                        # Data Access Objects
│   │   │   │   ├── EmployeeDAO.java
│   │   │   │   └── AttendanceLogDAO.java
│   │   │   ├── service/                    # Business Logic
│   │   │   │   └── AttendanceService.java
│   │   │   ├── action/                     # Struts Actions
│   │   │   │   └── AttendanceAction.java
│   │   │   └── util/                       # Utilities
│   │   │       └── HibernateUtil.java
│   │   ├── resources/
│   │   │   ├── hibernate.cfg.xml           # Hibernate config
│   │   │   └── struts.xml                  # Struts config
│   │   └── webapp/
│   │       ├── WEB-INF/
│   │       │   └── web.xml                 # Web app descriptor
│   │       ├── css/
│   │       │   └── custom.css              # Custom styles
│   │       ├── js/
│   │       │   └── app.js                  # JavaScript utilities
│   │       ├── jsp/                        # JSP pages
│   │       └── index.jsp                   # Main page
│   └── test/                               # Unit tests
└── README.md
```

## Usage

### 1. Sign In/Out

1. Navigate to the home page (`index.jsp`)
2. Enter your Card ID
3. Click "Sign In" to record arrival
4. Click "Sign Out" to record departure

### 2. View Employee Dashboard

- Access: `/employeeDashboard`
- Features:
  - Today's attendance status
  - Attendance history calendar
  - Leave balance display
  - Apply for leave
  - Register off-site work

### 3. View Manager Dashboard

- Access: `/managerDashboard`
- Features:
  - Real-time attendance counter
  - Absence alerts
  - Off-site employees list
  - Approve/reject leave requests
  - Monthly reports

## API Endpoints (Struts Actions)

| Action | URL | Method | Description |
|--------|-----|--------|-------------|
| Sign In | `/signIn` | POST | Record employee sign-in |
| Sign Out | `/signOut` | POST | Record employee sign-out |
| Employee Dashboard | `/employeeDashboard` | GET | Load employee view |
| Manager Dashboard | `/managerDashboard` | GET | Load manager view |
| Submit Leave | `/submitLeave` | POST | Submit leave request |
| Approve Leave | `/approveLeave` | POST | Approve leave request |
| Register Off-Site | `/registerOffSite` | POST | Register off-site work |

## Sample Data

To test the system, you can insert sample employee data:

```sql
USE AttendanceDB;
GO

-- Insert sample employees
INSERT INTO employees (name, department, role, card_id, email, is_active)
VALUES 
    ('John Doe', 'IT', 'Developer', 'CARD001', 'john.doe@company.com', 1),
    ('Jane Smith', 'HR', 'Manager', 'CARD002', 'jane.smith@company.com', 1),
    ('Bob Johnson', 'Finance', 'Accountant', 'CARD003', 'bob.johnson@company.com', 1);

-- Insert leave balances
INSERT INTO leave_balance (employee_id, medical_leave, annual_leave, year)
VALUES 
    (1, 14, 14, 2025),
    (2, 14, 14, 2025),
    (3, 14, 14, 2025);
```

## Development

### Running in Development Mode

Struts dev mode is enabled by default in `struts.xml`:

```xml
<constant name="struts.devMode" value="true"/>
```

This provides:
- Detailed error messages
- Automatic configuration reloading
- Debug information

**Remember to disable this in production!**

### Building for Production

1. Update `struts.xml`:
   ```xml
   <constant name="struts.devMode" value="false"/>
   ```

2. Update `hibernate.cfg.xml`:
   ```xml
   <property name="hibernate.show_sql">false</property>
   <property name="hibernate.hbm2ddl.auto">validate</property>
   ```

3. Build:
   ```bash
   mvn clean package
   ```

## Troubleshooting

### Database Connection Issues

- Verify SQL Server is running
- Check connection string in `hibernate.cfg.xml`
- Ensure SQL Server is configured to accept TCP/IP connections
- Verify firewall settings allow port 1433

### Hibernate Errors

- Check entity mappings in `hibernate.cfg.xml`
- Verify database schema matches entity definitions
- Review Hibernate logs for detailed error messages

### Struts Action Not Found

- Verify action mappings in `struts.xml`
- Check package names and class paths
- Ensure Struts filter is configured in `web.xml`

## Future Enhancements

- [ ] Biometric integration (fingerprint scanner)
- [ ] Mobile app for attendance
- [ ] Email notifications for leave approvals
- [ ] Advanced reporting and analytics
- [ ] Integration with payroll systems
- [ ] Geolocation tracking for off-site work
- [ ] Multi-language support

## License

This project is for educational and internal use.

## Support

For issues or questions, please contact the development team.

---

**Built with ❤️ using Java, Struts, Hibernate, and Bootstrap**
