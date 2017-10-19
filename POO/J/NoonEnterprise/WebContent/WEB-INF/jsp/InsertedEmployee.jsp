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
    </style>
</head>

<body>
	<c:import url="Noon-Header.jsp"></c:import>
    <h3>Employee ${ employeeName } successfully added!</h3>
    <a href="mvc?task=ListEmployee"><button>List Employees</button></a>
    <c:import url="Noon-Footer.jsp"></c:import>
</body>

</html>