-- Run this script as Administrator in SQL Server Management Studio
-- or using sqlcmd with admin privileges

USE master;
GO

-- Create SQL login for the attendance application
IF NOT EXISTS (SELECT name FROM sys.server_principals WHERE name = 'attendance_app')
BEGIN
    CREATE LOGIN attendance_app WITH PASSWORD = 'Attendance@2025!';
    PRINT 'Login attendance_app created successfully';
END
ELSE
BEGIN
    PRINT 'Login attendance_app already exists';
END
GO

-- Switch to AttendanceDB
USE AttendanceDB;
GO

-- Create user for the login
IF NOT EXISTS (SELECT name FROM sys.database_principals WHERE name = 'attendance_app')
BEGIN
    CREATE USER attendance_app FOR LOGIN attendance_app;
    PRINT 'User attendance_app created successfully';
END
ELSE
BEGIN
    PRINT 'User attendance_app already exists';
END
GO

-- Grant full permissions
ALTER ROLE db_owner ADD MEMBER attendance_app;
GO

PRINT 'Setup complete!';
PRINT 'Login: attendance_app';
PRINT 'Password: Attendance@2025!';
GO
