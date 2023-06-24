<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <title>Document</title>

</head>
<body>
<div class="container">
    <form action="." method="post" class="form-horizontal">
        <fieldset>

            <!-- Form Name -->
            <legend>User page</legend>
            <!-- Text input-->
            <div class="form-group">
                <label class="col-md-4 control-label" for="login">Login</label>
                <div class="col-md-4">
                    <input id="login" name="login" value="${user.login}"
                           type="text" placeholder="" class="form-control input-md" required="">
                </div>
            </div>

            <!-- Password input-->
            <div class="form-group">
                <label class="col-md-4 control-label" for="password">Password</label>
                <div class="col-md-4">
                    <input id="password" name="password" value="${user.password}"
                           type="password" placeholder="" class="form-control input-md"
                           required="">
                </div>
            </div>
            <input type="hidden" value="${user.id}">

            <!-- Select Basic -->
            <div class="form-group">
                <label class="col-md-4 control-label" for="role">Role</label>
                <div class="col-md-4">
                    <select id="role" name="role" class="form-control">
                        <c:forEach items="${sessionScope.roles}" var="role">
                            <option value="${role}" ${user.role==role?"selected":""}>${role}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <!-- Button (Double) -->
            <div class="form-group">
                <label class="col-md-4 control-label" for="operation">Operation</label>
                <div id="operation" class="col-md-8">
                    <c:choose>
                        <c:when test="${requestScope.user!=null}">
                            <button name="updateUser" class="btn btn-success">Update</button>
                            <button name="deleteUser" class="btn btn-danger">Delete</button>
                        </c:when>
                        <c:otherwise>
                            <button name="createUser" class="btn btn-danger">Create</button>
                            <button name="loginUser" class="btn btn-primary">Login</button>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </fieldset>
    </form>


    <h2>List users</h2>
    <c:forEach var="user" items="${requestScope.users}">
        <div>
            <a href="/users/${user.id}/">Edit</a>: ${user}
        </div>
    </c:forEach>
    <c:if test="${sessionScope.currentUser!=null}">
        <div>
            Session demo: login user ${sessionScope.currentUser.login}
        </div>
    </c:if>
</div>


<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"
        integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js"
        integrity="sha384-cuYeSxntonz0PPNlHhBs68uyIAVpIIOZZ5JqeqvYYIcEL727kskC66kF92t6Xl2V"
        crossorigin="anonymous"></script>
</body>
</html>