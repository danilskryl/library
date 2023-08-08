<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html class="html_style">
<head>
    <title>Library</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black"/>
    <link
            rel="apple-touch-icon"
            sizes="180x180"
            href="images/icon_book.svg"
    />
    <link rel="shortcut icon" href="images/icon_book.svg"/>
    <link rel="stylesheet" href="styles/login_page.css">
    <link rel="stylesheet" href="styles/html_style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <script>
        function redirectToRegistration() {
            window.location.href = "registration.jsp";
        }
    </script>
</head>
<body>
<%
    Cookie[] cookies = request.getCookies();

    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("id")) {
                response.sendRedirect("cabinet.jsp");
            }
        }
    }
%>
<section class="h-100 gradient-form" style="background-color: #eee;">
    <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-xl-10">
                <div class="card rounded-3 text-black">
                    <div class="row g-0">
                        <div class="col-lg-6">
                            <div class="card-body p-md-5 mx-md-4">

                                <div class="text-center">
                                    <img src="images/book_login.jpeg"
                                         style="width: 185px;" alt="logo">
                                    <h4 class="mt-1 mb-5 pb-1">Library of Danil Skryl</h4>
                                </div>

                                <form name="loginForm" method="post"
                                      action="${pageContext.request.contextPath}/loginServlet">
                                    <p>Please login to your account</p>

                                    <div class="form-outline mb-4">
                                        <input type="text" name="login" id="login" class="form-control"
                                               placeholder="Username" required/>
                                        <span id="usernameError" style="color: red;"></span>
                                    </div>

                                    <div class="form-outline mb-4">
                                        <input type="password" name="password" id="password" class="form-control"
                                               placeholder="Password" required/>
                                        <span id="passwordError" style="color: red;"></span>
                                    </div>

                                    <div class="text-center pt-1 mb-5 pb-1">
                                        <button class="btn btn-primary btn-block fa-lg gradient-custom-2 mb-3"
                                                type="submit">Log in
                                        </button>
                                        <a class="text-muted" href="#!">Forgot password?</a>
                                    </div>

                                    <div class="d-flex align-items-center justify-content-center pb-4">
                                        <p class="mb-0 me-2">Don't have an account?</p>
                                        <button type="button" class="btn btn-outline-danger"
                                                onclick="redirectToRegistration()">Create new
                                        </button>
                                    </div>

                                </form>

                            </div>
                        </div>
                        <div class="col-lg-6 d-flex align-items-center gradient-custom-2">
                            <div class="text-white px-3 py-4 p-md-5 mx-md-4">
                                <h4 class="mb-4">We are more than just a library</h4>
                                <p class="small mb-0">It's a place where you can add your books and management it. You
                                    have a personal cabinet where you can add information about you. Also, how I said
                                    before,
                                    you can add book, delete or update it. Enjoy!</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<style>
    html,
    body,
    .h-100 {
        height: 100%;
    }
</style>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
        crossorigin="anonymous"></script>
</body>
</html>