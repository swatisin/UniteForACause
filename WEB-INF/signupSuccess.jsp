<jsp:include page="template-top.jsp" />

<%@page import="java.util.List"%>

<%@page import="databeans.signupEmail"%>
<html>
    <head>
        <title>Sign up </title>
    </head>
    
    <body>
    
        <h2>Sign up </h2>
<%
		List<String> errors = (List<String>) request.getAttribute("errors");
		if (errors != null) {
			for (String error : errors) {
%>		
				<h3 style="color:red"> <%= error %> </h3>
<%
			}
		}
%>	
<div class="container-fluid">
      <div class="row-fluid">
        <div class="span2">
          <div class="well sidebar-nav">
            <ul class="nav nav-list">
              <li class="nav-header">Sidebar</li>
              <li class="active"><a href="#">Link</a></li>
              <li><a href="#">Link</a></li>
              <li><a href="#">Link</a></li>
              <li><a href="#">Link</a></li>
              <li class="nav-header">Sidebar</li>
              <li><a href="#">Link</a></li>
              <li><a href="#">Link</a></li>
              <li><a href="#">Link</a></li>
              <li><a href="#">Link</a></li>
              <li><a href="#">Link</a></li>
              <li><a href="#">Link</a></li>
              <li class="nav-header">Sidebar</li>
              <li><a href="#">Link</a></li>
              <li><a href="#">Link</a></li>
              <li><a href="#">Link</a></li>
            </ul>
          </div><!--/.well -->
        </div><!--/span-->
	
  
  <div class="hero-unit" >      
<%
	signupEmail[] items = (signupEmail[]) request.getAttribute("items");
%>
		<h2>You have registered for Weekly Newsletters From Us. Thank You !</h2>
  </tr>
<%
			
%>        
</div>
        			
<%
       		
%>
		</table>
		</div>

    </body>
</html>