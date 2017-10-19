<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>NoonEnterprise | Add New Employee</title>
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
       	p {
       		font-style: italic;
       		font-weight: 600;
       		color: red;
       	}
    </style>
</head>

<body>
	<c:import url="Header.jsp"></c:import>
    <h3>We couldn't perform this operation due the following reason:</h3>
    <p>${ errorMessage }</p>
    <a href="mvc?task=ListContact"><button>List Contacts</button></a>
    <c:import url="Footer.jsp"></c:import>
</body>

</html>