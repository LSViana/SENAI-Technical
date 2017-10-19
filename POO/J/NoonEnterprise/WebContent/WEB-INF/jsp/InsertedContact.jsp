<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Agenda | Add New Contact</title>
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
    <h3>Contact ${ contactName } successfully added!</h3>
    <a href="mvc?task=ListContact"><button>List Contacts</button></a>
    <c:import url="Footer.jsp"></c:import>
</body>

</html>