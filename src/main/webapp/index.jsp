<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Employee Attendance System</title>
    
    <!-- Bootstrap 5 CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap Icons -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    <!-- Custom CSS -->
    <link rel="stylesheet" href="css/custom.css">
</head>
<body>
    <!-- Navigation -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
        <div class="container-fluid">
            <a class="navbar-brand" href="index.jsp">
                <i class="bi bi-calendar-check"></i> Attendance System
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item">
                        <a class="nav-link active" href="index.jsp">Sign In/Out</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="employeeDashboard">Employee Dashboard</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="managerDashboard">Manager Dashboard</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <!-- Main Content -->
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-8 col-lg-6">
                <!-- Sign In/Out Card -->
                <div class="card shadow-lg border-0">
                    <div class="card-header bg-primary text-white text-center py-4">
                        <h3 class="mb-0"><i class="bi bi-person-badge"></i> Employee Sign In/Out</h3>
                    </div>
                    <div class="card-body p-5">
                        <!-- Sign In Form -->
                        <div id="signInSection">
                            <h5 class="mb-4 text-center">Tap Your ID Card</h5>
                            <form id="signInForm">
                                <div class="mb-4">
                                    <label for="cardIdSignIn" class="form-label">Card ID</label>
                                    <div class="input-group input-group-lg">
                                        <span class="input-group-text"><i class="bi bi-credit-card-2-front"></i></span>
                                        <input type="text" class="form-control" id="cardIdSignIn" 
                                               placeholder="Enter Card ID" required autofocus>
                                    </div>
                                </div>
                                <div class="d-grid gap-2">
                                    <button type="submit" class="btn btn-success btn-lg">
                                        <i class="bi bi-box-arrow-in-right"></i> Sign In
                                    </button>
                                    <button type="button" class="btn btn-outline-danger btn-lg" 
                                            onclick="switchToSignOut()">
                                        <i class="bi bi-box-arrow-left"></i> Sign Out Instead
                                    </button>
                                </div>
                            </form>
                        </div>

                        <!-- Sign Out Form (Hidden by default) -->
                        <div id="signOutSection" style="display: none;">
                            <h5 class="mb-4 text-center">Sign Out</h5>
                            <form id="signOutForm">
                                <div class="mb-4">
                                    <label for="cardIdSignOut" class="form-label">Card ID</label>
                                    <div class="input-group input-group-lg">
                                        <span class="input-group-text"><i class="bi bi-credit-card-2-front"></i></span>
                                        <input type="text" class="form-control" id="cardIdSignOut" 
                                               placeholder="Enter Card ID" required>
                                    </div>
                                </div>
                                <div class="d-grid gap-2">
                                    <button type="submit" class="btn btn-danger btn-lg">
                                        <i class="bi bi-box-arrow-left"></i> Sign Out
                                    </button>
                                    <button type="button" class="btn btn-outline-success btn-lg" 
                                            onclick="switchToSignIn()">
                                        <i class="bi bi-box-arrow-in-right"></i> Sign In Instead
                                    </button>
                                </div>
                            </form>
                        </div>

                        <!-- Response Message -->
                        <div id="responseMessage" class="mt-4" style="display: none;"></div>
                    </div>
                </div>

                <!-- Quick Stats -->
                <div class="row mt-4">
                    <div class="col-md-6">
                        <div class="card border-0 shadow-sm">
                            <div class="card-body text-center">
                                <i class="bi bi-clock-history text-primary" style="font-size: 2rem;"></i>
                                <h6 class="mt-2 mb-0">Today's Date</h6>
                                <p class="text-muted mb-0" id="currentDate"></p>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="card border-0 shadow-sm">
                            <div class="card-body text-center">
                                <i class="bi bi-clock text-success" style="font-size: 2rem;"></i>
                                <h6 class="mt-2 mb-0">Current Time</h6>
                                <p class="text-muted mb-0" id="currentTime"></p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap 5 JS Bundle -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    <!-- Custom JS -->
    <script src="js/app.js"></script>
    
    <script>
        // Update current date and time
        function updateDateTime() {
            const now = new Date();
            document.getElementById('currentDate').textContent = now.toLocaleDateString('en-US', {
                weekday: 'long', year: 'numeric', month: 'long', day: 'numeric'
            });
            document.getElementById('currentTime').textContent = now.toLocaleTimeString('en-US');
        }
        
        updateDateTime();
        setInterval(updateDateTime, 1000);
        
        // Switch between Sign In and Sign Out
        function switchToSignOut() {
            document.getElementById('signInSection').style.display = 'none';
            document.getElementById('signOutSection').style.display = 'block';
            document.getElementById('cardIdSignOut').focus();
        }
        
        function switchToSignIn() {
            document.getElementById('signOutSection').style.display = 'none';
            document.getElementById('signInSection').style.display = 'block';
            document.getElementById('cardIdSignIn').focus();
        }
        
        // Handle Sign In
        document.getElementById('signInForm').addEventListener('submit', function(e) {
            e.preventDefault();
            const cardId = document.getElementById('cardIdSignIn').value;
            
            fetch('signIn', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                },
                body: 'cardId=' + encodeURIComponent(cardId)
            })
            .then(response => response.json())
            .then(data => {
                showMessage(data);
                if (data.success) {
                    document.getElementById('cardIdSignIn').value = '';
                }
            })
            .catch(error => {
                showMessage({success: false, message: 'Error: ' + error});
            });
        });
        
        // Handle Sign Out
        document.getElementById('signOutForm').addEventListener('submit', function(e) {
            e.preventDefault();
            const cardId = document.getElementById('cardIdSignOut').value;
            
            fetch('signOut', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                },
                body: 'cardId=' + encodeURIComponent(cardId)
            })
            .then(response => response.json())
            .then(data => {
                showMessage(data);
                if (data.success) {
                    document.getElementById('cardIdSignOut').value = '';
                }
            })
            .catch(error => {
                showMessage({success: false, message: 'Error: ' + error});
            });
        });
        
        // Show response message
        function showMessage(data) {
            const messageDiv = document.getElementById('responseMessage');
            const alertClass = data.success ? 'alert-success' : 'alert-danger';
            const icon = data.success ? 'bi-check-circle-fill' : 'bi-x-circle-fill';
            
            let messageHTML = `
                <div class="alert ${alertClass} alert-dismissible fade show" role="alert">
                    <i class="bi ${icon}"></i> <strong>${data.message}</strong>
            `;
            
            if (data.employeeName) {
                messageHTML += `<br><small>Employee: ${data.employeeName}</small>`;
            }
            if (data.time) {
                messageHTML += `<br><small>Time: ${data.time}</small>`;
            }
            if (data.hoursWorked) {
                messageHTML += `<br><small>Hours Worked: ${data.hoursWorked} hours</small>`;
            }
            
            messageHTML += `
                    <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                </div>
            `;
            
            messageDiv.innerHTML = messageHTML;
            messageDiv.style.display = 'block';
            
            // Auto-hide after 5 seconds
            setTimeout(() => {
                messageDiv.style.display = 'none';
            }, 5000);
        }
    </script>
</body>
</html>
