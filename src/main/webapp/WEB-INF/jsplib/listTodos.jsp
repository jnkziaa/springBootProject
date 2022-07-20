<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <link href="webjars\bootstrap\5.1.3\css\bootstrap.min.css" rel="stylesheet">
        <title> To Do Page</title>
    </head>
    <body>
        <%@ include file="common/navigation.jspf"%>
        <div class="container">
            <h1>Welcome ${name}</h1>
            <hr>
            <pre>${errorMessage}</pre>
            <h2> Your Todos Are: </h2>
            <table>

                <thead>
                    <tr>

                        <th>Description</th>
                        <th>Target Date</th>
                        <th>Is Done?</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${todos}" var="todo">
                        <tr>

                            <td>${todo.description}</td>
                            <td>${todo.targetDate}</td>
                            <td>${todo.done}</td>
                            <td><a href="update-todo?id=${todo.id}" class="btn btn-success"> Finished </a></td>
                            <td><a href="delete-todo?id=${todo.id}" class="btn btn-danger"> Delete </a></td>
                        </tr>
                    </c:forEach>

                </tbody>
            </table>
            <hr>
            <a href="add-todo" class="btn btn-warning">Add Todo</a>
            <%@include file="common/footer.jspf"%>
        </div>
    </body>
</html>