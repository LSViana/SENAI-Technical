<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>NoonEnterprise | Edit Employees</title>
    <style>
        * {
            box-sizing: border-box;
        }
        
        body {
            margin: 10px;
        }
        input {
            display: block;
            margin-bottom: 10px;
        }
        h6 {
            margin: 0px;
            margin-top: -10px;
            color: gray;
            font-style: italic;
            font-weight: 100;
        }
       	button {
       		margin-top: 10px;       	
       	}
    </style>
</head>

<body>
	<c:import url="Noon-Header.jsp"></c:import>
    <h3>Edit Employees</h3>
    <hr>
    <form action="mvc?task=UpdateEmployee&id=${ id }" method="post">
        <label for="name">Name</label>
        <input type="text" name="name" value="${ name }">
        <label for="email">E-mail</label>
        <input type="email" name="email" value="${ email }">
        <label for="cpf">CPF</label>
        <input type="text" name="cpf" id="cpf" maxlength="11" value="${ cpf }">
        <h6>Maximum length is 11</h6>
        <label for="password">Password</label>
        <input type="password" name="password" maxlength="20" value="${ password }">
        <h6>Maximum length is 20</h6>
        <button type="submit">Save</button>
    </form>
    <a href="mvc?task=ListEmployee"><button>List Employees</button></a>
    <c:import url="Noon-Footer.jsp"></c:import>
</body>

</html>