<jsp:include page="template-top.jsp" />

<%@page import="java.util.List"%>

<%@page import="databeans.EventBean"%>
<html>
    <head>
        <title>Event Schedular </title>
    </head>
    
    <body>
    
  
<div class="container-fluid">
      <div class="row-fluid">
        <div class="span2">
          <div class="well sidebar-nav">
            <ul class="nav nav-list">
              <li class="nav-header">UniteForACause</li>
              <li><a href="listBlog.do">Blogs</a></li>
                    <li><a href="listEvent.do">Events</a></li>
                    <li><a href="listDonation.do">Donation</a></li>
                    <li class="divider"></li>
                    <li class="nav-header">External</li>
                    <li><a href="news.jsp">News</a></li>
            </ul>
          </div><!--/.well -->
        </div><!--/span-->
	 <div class="span9">
 
  
  <div class="hero-unit" >      
<%
		EventBean[] items = (EventBean[]) request.getAttribute("items");
%>
		<p style="font-size: x-large">You have <%= items.length %> upcoming Events.</p>

		<table class="table table-hover table-bordered table-condensed">
		<tr>
    		<th>#</th>
    		<th>Event Name</th>
    		<th>Event Date</th>
    		<th>Category</th>
    		<th>Registeration Link</th>
    		
    		
  </tr>
<%
			for (int i = 0; i < items.length; i++) {
       			EventBean item = items[i];
       			
%>        
        			<td ><%= i + 1 %> </td>
        			<td><%= item.getTitle().replace("<", "&lt;").replace(">","&gt;").replace("\"","&quot;") %> </td>
                   	<td>     <%= item.getEventDate() %> </td>
                    <td>     <%= item.getCategory()  %> </td>
                     <td>
                    <form action="registerEvent.do" method="POST">
                     <input type="hidden" name="id" id="id" value=<%=item.getId()%> /><br />
                     <input type="submit" name="action" value="Register" />
                    </td>
                    </form>
                    
        			</td>
   				</tr>
<%
       		}
%>
		</table>
		</div>

       	Click <a href="logout.do">here</a> to log out.
    </body>
</html>