-- Create Attendance Database
-- Run this script in SQL Server Management Studio or sqlcmd

-- Create database if it doesn't exist
IF NOT EXISTS (SELECT name FROM sys.databases WHERE name = 'AttendanceDB')
BEGIN
    CREATE DATABASE AttendanceDB;
    PRINT 'Database AttendanceDB created successfully';
END
ELSE
BEGIN
    PRINT 'Database AttendanceDB already exists';
END
GO

-- Use the database
USE AttendanceDB;
GO

-- Create a login and user for the application (optional, if not using Windows Auth)
-- IF NOT EXISTS (SELECT name FROM sys.server_principals WHERE name = 'attendance_user')
-- BEGIN
--     CREATE LOGIN attendance_user WITH PASSWORD = 'Attendance@2025';
--     PRINT 'Login attendance_user created';
-- END
-- GO

-- IF NOT EXISTS (SELECT name FROM sys.database_principals WHERE name = 'attendance_user')
-- BEGIN
--     CREATE USER attendance_user FOR LOGIN attendance_user;
--     ALTER ROLE db_owner ADD MEMBER attendance_user;
--     PRINT 'User attendance_user created and granted db_owner role';
-- END
-- GO

PRINT 'Database setup complete!';
PRINT 'You can now run your application and Hibernate will create the tables automatically.';
