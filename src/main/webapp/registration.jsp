<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Join to Library</title>
    <link type="Image/x-icon" href="images/icon_book.svg" rel="icon">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="registration.css"/>
    <link rel="stylesheet" href="login_page.css"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <script>
        function verifyUserRegistration() {
            let firstname = document.getElementById("firstname").value;
            let lastname = document.getElementById("lastname").value;
            let username = document.getElementById("username").value;
            let password = document.getElementById("password").value;
            let birthdate = document.getElementById("birthday").value;

            if (password.trim() === '') {
                alert("Please enter a password.");
                return;
            }

            window.location.href = "/registration?firstName=" + encodeURIComponent(firstname) +
                "&lastName=" + encodeURIComponent(lastname) +
                "&username=" + encodeURIComponent(username) +
                "&password=" + encodeURIComponent(password) +
                "&birthDate=" + encodeURIComponent(birthdate);
        }
    </script>
</head>
<body>

<section class="vh-100" style="background-color: #eee;">
    <div class="container h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-lg-12 col-xl-11">
                <div class="card text-black" style="border-radius: 25px;">
                    <div class="card-body p-md-5">
                        <div class="row justify-content-center">
                            <div class="col-md-10 col-lg-6 col-xl-5 order-2 order-lg-1">

                                <p class="text-center h1 fw-bold mb-5 mx-1 mx-md-4 mt-4">Sign up</p>

                                <form class="mx-1 mx-md-4">

                                    <div class="d-flex flex-row align-items-center mb-4">
                                        <i class="fas fa-user fa-lg me-3 fa-fw"></i>
                                        <div class="form-outline flex-fill mb-0">
                                            <input type="text" id="firstname" class="form-control"
                                                   placeholder="First name"/>
<%--                                            <label class="form-label" for="firstname">First name</label>--%>
                                        </div>
                                    </div>

                                    <div class="d-flex flex-row align-items-center mb-4">
                                        <i class="fas fa-envelope fa-lg me-3 fa-fw"></i>
                                        <div class="form-outline flex-fill mb-0">
                                            <input type="text" id="lastname" class="form-control"
                                                   placeholder="Last name"/>
                                        </div>
                                    </div>

                                    <div class="d-flex flex-row align-items-center mb-4">
                                        <i class="fas fa-lock fa-lg me-3 fa-fw"></i>
                                        <div class="form-outline flex-fill mb-0">
                                            <input type="text" id="username" class="form-control"
                                                   placeholder="Username"/>
                                        </div>
                                    </div>

                                    <div class="d-flex flex-row align-items-center mb-4">
                                        <i class="fas fa-key fa-lg me-3 fa-fw"></i>
                                        <div class="form-outline flex-fill mb-0">
                                            <input type="password" id="password" class="form-control"
                                                   placeholder="Password"/>
                                        </div>
                                    </div>

                                    <div class="d-flex flex-row align-items-center mb-4">
                                        <i class="fas fa-envelope fa-lg me-3 fa-fw"></i>
                                        <div class="form-outline flex-fill mb-0">
                                            <input type="date" id="birthday" class="form-control"/>
                                        </div>
                                    </div>

                                    <div class="form-check d-flex justify-content-center mb-5">
                                        <input class="form-check-input me-2" type="checkbox" value=""
                                               id="form2Example3c"/>
                                        <label class="form-check-label" for="form2Example3c">
                                            I agree all statements in <a href="#!">Terms of service</a>
                                        </label>
                                    </div>

                                    <div class="d-flex justify-content-center mx-4 mb-3 mb-lg-4">
                                        <button type="button" class="btn btn-primary btn-lg"
                                                onclick="verifyUserRegistration()">Register
                                        </button>
                                    </div>

                                </form>

                            </div>

                            <div class="col-md-10 col-lg-6 col-xl-7 d-flex align-items-center order-1 order-lg-2">
                                <img src="images/book_registration.jpeg"
                                     class="img-fluid" alt="Book image">
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
        crossorigin="anonymous"></script>
</body>
</html>
