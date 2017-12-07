<div class="full">
    <div class="enterprise-info">
        <h1 class="title-enterprise">GameCentury</h1>
        <span></span>
        <p>The most wanted game house at Latin America.</p>
    </div>
    <div class="menu">
        <ul>
            <a href="/GameCentury/redirect?page=home">
                <li>Home</li>
            </a>
            <a href="/GameCentury/redirect?page=games">
                <li>Games</li>
            </a>
            <a href="/GameCentury/redirect?page=about-us">
                <li>About Us</li>
            </a>
            <a href="/GameCentury/redirect?page=find-us">
                <li>Find Us</li>
            </a>
            <a href="/GameCentury/redirect?page=login">
                <li>
                	<!--<%= (Boolean)request.getAttribute("permission-1") ? "Logout" : "Login" %>-->
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