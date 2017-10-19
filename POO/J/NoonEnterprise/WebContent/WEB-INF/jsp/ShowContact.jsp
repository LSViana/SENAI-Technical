<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Agenda | Edit Contacts</title>
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
	<c:import url="Header.jsp"></c:import>
    <h3>Edit Your Contacts</h3>
    <hr>
    <form action="mvc?task=UpdateContact&id=${ id }" method="post">
        <label for="name">Name</label>
        <input type="text" name="name" value="${ name }">
        <label for="email">E-mail</label>
        <input type="email" name="email" value="${ email }">
        <label for="address">Address</label>
        <input type="text" name="address" id="address" value="${ address }">
        <label for="dateOfBirthday">Date of Birthday</label>
        <input type="date" name="dateOfBirthday" required="required" maxlength="10" value="${ dateOfBirthday }">
        <button type="submit">Save</button>
    </form>
    <a href="mvc?task=ListContact"><button>List Contacts</button></a>
    <c:import url="Footer.jsp"></c:import>
</body>

</html>