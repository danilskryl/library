<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.danilskryl.petprojects.library.model.User" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.danilskryl.petprojects.library.repository.LibraryManager" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Locale" %>
<%@ page import="com.danilskryl.petprojects.library.model.Book" %>
<%@ page import="java.util.List" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="org.slf4j.Logger" %>
<%@ page import="org.slf4j.LoggerFactory" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html class="html_style">
<head>
    <title>Library</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="styles/cabinet.css">
    <link rel="stylesheet" href="styles/html_style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <link type="Image/x-icon" href="images/icon_book.svg" rel="icon">
</head>
<body>

<%
    final Logger LOG = LoggerFactory.getLogger("Cabinet.jsp");
    final LibraryManager dbManager = LibraryManager.getInstance();

    Cookie[] cookies = request.getCookies();
    long id = 0;

    for (Cookie cookie : cookies) {
        if (cookie.getName().equals("id")) {
            id = Long.parseLong(cookie.getValue());
            break;
        }
    }

    User user = dbManager.getUserById(id);
    List<Book> bookList = dbManager.getUserBooks(id);

    LOG.info("User [{}] log in to account", id);

    request.setAttribute("user", user);
    request.setAttribute("user_books", bookList);

    String firstName = user.getFirstName();
    String lastName = user.getLastName();
    String fullName = firstName + " " + lastName;

    Date date = user.getBirthDate();
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy", new Locale("en"));
    String birthDate = dateFormat.format(date);

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.ENGLISH);
    LocalDateTime joinDateUser = user.getJoinDate();
    String joinDate =  formatter.format(joinDateUser);

    String username = user.getUsername();
%>

<section style="background-color: #eee; margin: 0; padding: 0; min-height: 100vh;">
    <div>
        <form action="${pageContext.request.contextPath}/logout" method="post"
              style="position: absolute; top: 10px; right: 10px;">
            <input class="btn btn-danger" type="submit" value="Log out">
        </form>
    </div>
    <div class="container py-5">
        <div class="row">
            <div class="col">
                <nav aria-label="breadcrumb" class="bg-light rounded-3 p-3 mb-4">
                    <ol class="breadcrumb mb-0">
                        <li class="breadcrumb-item active" aria-current="page">User Profile</li>
                    </ol>
                </nav>
            </div>
        </div>

        <div class="row">
            <div class="col-lg-4">
                <div class="card mb-4">
                    <div class="card-body text-center">
                        <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava3.webp"
                             alt="avatar"
                             class="rounded-circle img-fluid" style="width: 150px;">
                        <h5 class="my-3"><%= fullName %>
                        </h5>
                        <p class="text-muted mb-1">Online</p>
                        <p class="text-muted mb-4">Joined <%= joinDate %></p>
                        <div class="d-flex justify-content-center mb-2">
                            <button type="button" class="btn btn-primary">Follow</button>
                            <button type="button" class="btn btn-outline-primary ms-1">Message</button>
                        </div>
                    </div>
                </div>
                <div class="card mb-4 mb-lg-0">
                    <div class="card-body p-0">
                        <ul class="list-group list-group-flush rounded-3">
                            <li class="list-group-item d-flex justify-content-between align-items-center p-3">
                                <i class="fas fa-globe fa-lg text-warning"></i>
                                <p class="mb-0">t.me/restill</p>
                            </li>
                            <li class="list-group-item d-flex justify-content-between align-items-center p-3">
                                <i class="fab fa-github fa-lg" style="color: #333333;"></i>
                                <p class="mb-0">youtube.com</p>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="col-lg-8">
                <div class="card mb-4">
                    <div class="card-body">
                        <div class="row">
                            <div class="col-sm-3">
                                <p class="mb-0">Full Name</p>
                            </div>
                            <div class="col-sm-9">
                                <p class="text-muted mb-0"><%= fullName %>
                                </p>
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <p class="mb-0">Username</p>
                            </div>
                            <div class="col-sm-9">
                                <p class="text-muted mb-0"><%= username %>
                                </p>
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <p class="mb-0">Birthday</p>
                            </div>
                            <div class="col-sm-9">
                                <p class="text-muted mb-0"><%= birthDate %>
                                </p>
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <p class="mb-0">Amount books</p>
                            </div>
                            <div class="col-sm-9">
                                <p class="text-muted mb-0"><%= bookList.size() %></p>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <div class="card mb-4 mb-md-0">
                            <div class="card-body">

                                <form name="addBook" method="post"
                                      action="${pageContext.request.contextPath}/bookServlet">
                                    <h6>Add new book</h6>

                                    <div class="form-outline mb-4">
                                        <input type="text" id="title" name="title" class="form-control"
                                               placeholder="Title" required/>
                                        <span id="usernameError" style="color: red;"></span>
                                    </div>

                                    <div class="form-outline mb-4">
                                        <input type="text" id="author" name="author" class="form-control"
                                               placeholder="Author" required/>
                                        <span id="passwordError" style="color: red;"></span>
                                    </div>

                                    <div class="text-center pt-1 mb-5 pb-1">
                                        <button class="btn btn-primary btn-block fa-lg gradient-custom-2 mb-3"
                                                type="submit">Add book
                                        </button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="card mb-4 mb-md-0">
                            <div class="card-body">
                                <h6>List your books</h6>
                                <c:forEach var="book" items="${user_books}">
                                    <div class="book-entry" id="${book.getId()}">

                                        <span id="book-title-${book.getId()}">${book.getTitle()}</span> -
                                        <span id="book-author-${book.getId()}">${book.getAuthor()}</span>

                                        <button class="edit-button" id="save-button-${book.getId()}"
                                                style="display: none"
                                                onclick="saveBook(${book.getId()})">
                                            <img src="images/icon_save.svg" alt="Save" width="16" height="16"/>
                                        </button>

                                        <button class="edit-button" id="edit-button-${book.getId()}"
                                                onclick="editBook(${book.getId()})">
                                            <img src="images/icon_edit.svg" alt="Edit" width="16" height="16">
                                        </button>

                                        <button class="delete-button" id="delete-button-${book.getId()}"
                                                onclick="deleteBook(${book.getId()}, ${user.getId()})">
                                            <img src="images/icon_delete.svg" alt="Delete" width="16" height="16">
                                        </button>

                                        <hr>

                                    </div>
                                </c:forEach>

                                <script>
                                    function deleteBook(bookId, userId) {
                                        <% LOG.debug("User [{}] delete book", id); %>

                                        fetch("/bookServlet?bookId=" + bookId + "&userId=" + userId, {
                                            method: "DELETE"
                                        })
                                            .then(response => response.json())
                                            .then(data => {
                                                // alert(data.message);
                                                removeBookFromList(bookId);
                                            }).catch(error => {
                                                console.error("Ошибка удаления книги", error);
                                            });
                                    }

                                    function removeBookFromList(bookId) {
                                        let element = document.getElementById(bookId);
                                        if (element) {
                                            element.remove();
                                        }
                                    }
                                </script>

                                <script>
                                    function editBook(bookId) {
                                        <% LOG.debug("User [{}] update book", id); %>

                                        hideButton("edit-button-" + bookId);
                                        hideButton("delete-button-" + bookId);
                                        showButton("save-button-" + bookId);

                                        let titleSpan = document.getElementById("book-title-" + bookId);
                                        let authorSpan = document.getElementById("book-author-" + bookId);

                                        let titleInput = document.createElement("input");
                                        titleInput.id = "temp-title-input-" + bookId;
                                        titleInput.value = titleSpan.textContent;
                                        let authorInput = document.createElement("input");
                                        authorInput.id = "temp-author-input-" + bookId;
                                        authorInput.value = authorSpan.textContent;

                                        titleSpan.parentNode.replaceChild(titleInput, titleSpan);
                                        authorSpan.parentNode.replaceChild(authorInput, authorSpan);
                                    }

                                    function saveBook(bookId) {
                                        <% LOG.debug("User [{}] save book after update", id); %>

                                        let titleInput = document.getElementById("temp-title-input-" + bookId);
                                        let authorInput = document.getElementById("temp-author-input-" + bookId);

                                        let title = titleInput.value;
                                        let author = authorInput.value;

                                        let newTitleSpan = document.createElement("span");
                                        newTitleSpan.id = "book-title-" + bookId;
                                        newTitleSpan.textContent = title;
                                        let newAuthorSpan = document.createElement("span");
                                        newAuthorSpan.id = "book-author-" + bookId;
                                        newAuthorSpan.textContent = author;

                                        titleInput.parentNode.replaceChild(newTitleSpan, titleInput);
                                        authorInput.parentNode.replaceChild(newAuthorSpan, authorInput);

                                        hideButton("save-button-" + bookId);
                                        showButton("edit-button-" + bookId);
                                        showButton("delete-button-" + bookId);

                                        fetch("/bookServlet?bookId=" + bookId + "&bookTitle=" + title + "&bookAuthor=" + author, {
                                            method: "PUT"
                                        })
                                            .then(response => response.json())
                                            .then(data =>  {
                                                // alert(data.message)
                                        }).catch(error => {
                                            console.error("Ошибка обновления книги", error);
                                        });
                                    }

                                    function hideButton(buttonId) {
                                        let button = document.getElementById(buttonId);
                                        if (button) {
                                            button.style.display = "none";
                                        }
                                    }

                                    function showButton(buttonId) {
                                        let button = document.getElementById(buttonId);
                                        if (button) {
                                            button.style.display = "block";
                                        }
                                    }
                                </script>
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
    body {
        height: 100%;
    }
</style>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
        crossorigin="anonymous"></script>
</body>
</html>