<div class="full">
    <div class="enterprise-info">
    	<a href="./">
    		<img src="./img/logo-header.svg">
    	</a>
        <h1 class="title-enterprise">GameCentury</h1>
        <span></span>
        <p>The most wanted game house at Latin America.</p>
    </div>
    <div class="menu">
    	<!-- <img src="./img/menu-icon-header.svg" /> -->
        <ul>
            <a href-smooth="#home">
                <li>Home</li>
            </a>
            <a href-smooth="#games">
                <li>Games</li>
            </a>
            <a href-smooth="#about-us">
                <li>About Us</li>
            </a>
            <a href-smooth="#find-us">
                <li>Find Us</li>
            </a>
            <a href="redirect?page=login">
                <li>
                	<%
                		String loginText = "Login";
                		HttpSession httpSession = request.getSession();
                		Object permission_1 = httpSession.getAttribute("permission-1");
                		if(permission_1 != null) {
                			if((Boolean)permission_1 == true)
                				loginText = "Logout";
                		}
                		out.print(loginText);
                	%>             	
                </li>
            </a>
        </ul>
    </div>
</div>