<jsp:include page="template-top.jsp" />

<%@page import="java.util.List"%>

<%@page import="databeans.CharityBean"%>
<%@page import="databeans.EventBean"%>
<%@page import="databeans.BlogBean"%>
<%@page import="databeans.RegisterEventBean"%>



<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<div class="hero-unit">
<h2>Admin Home Page</h2>
<table class="table table-hover table-bordered table-condensed">
		<tr>
    		<th>Total number of Donations</th>
    		<th>Amount Accumulated Till Today</th>
    		<th> Number of Users</th>
    		<th> Number of blogs </th>
 	 </tr>
 	 <tr>
 	 
 	 	<td > 156 </td>
                   	<td>   1736.67   </td>
        			<td> 268</td>
        			<td> 67</td>
 	 </tr>
    <h1>Upcoming Events</h1>
        <%
		EventBean[] items = (EventBean[]) request.getAttribute("events");
%>

		<table class="table table-hover table-bordered table-condensed">
		<tr>
    		<th>#</th>
    		<th>Event Name</th>
    		<th>Event Date</th>
    		<th>Category</th>
  </tr>
<%
			for (int i = 0; i < items.length; i++) {
       			EventBean item = items[i];
       			
%>        			<td>
			            <form action="deleteEvent.do" method="POST">
                			<input type="hidden" name="id" value="<%= item.getId() %>" />
                			<input type="submit" name="button" value="X" />
           				</form>
        			</td>
        			<td ><%= i + 1 %> </td>
        			<td><%= item.getTitle().replace("<", "&lt;").replace(">","&gt;").replace("\"","&quot;") %> </td>
                   	<td>     <%= item.getEventDate() %> </td>
                    <td>     <%= item.getCategory()  %> </td>
        			</td>
   				</tr>
<%
       		}
%>
		</table>
		</div>
		    <a href="addEvent.do" class="btn btn-large btn-primary ">Add A New Event</a>
		
      </div>
      <div class="hero-unit">
        <h1>Your Donations</h1>
        <%
	CharityBean[] charities = (CharityBean[]) request.getAttribute("charities");
%>
		<p style="font-size: x-large">You have <%= charities.length %> upcoming Events.</p>

		<table class="table table-hover table-bordered table-condensed">
		<tr>
    		<th>#</th>
    		<th>Event Name</th>
    		<th>Event Date</th>
    		<th>Category</th>
  </tr>
<%
			
			for (int i = 0; i < charities.length; i++) {
				CharityBean item = charities[i];
       					
%>        
					<td>
			            <form action="deleteCharity.do" method="POST">
                			<input type="hidden" name="id" value="<%= item.getId() %>" />
                			<input type="submit" name="button" value="X" />
           				</form>
        			</td>
        			<td ><%= i + 1 %> </td>
        			<td><%= item.getTitle() %> </td>
                   	<td>     <%= item.getAmount() %> </td>
                    <td>     <%= item.getCategory()  %> </td>
        			</td>
   				</tr>
<%
       		}
%>
		</table>
		</div>
		<a href="donation.do" class="btn btn-large btn-primary ">Donate</a>
		
		
      </div>
      <div class="hero-unit">
        <h1>Your Blogs</h1>
        <%
		BlogBean[] blogs = (BlogBean[]) request.getAttribute("blogs");
		%>
		<p style="font-size: x-large">The list now has <%= blogs.length %> items.</p>

		<table>
<%
			for (int i = 0; i < blogs.length; i++) {
       			BlogBean item = blogs[i];
%>        
           		<tr>
       				<td>
			            <form action="deleteBlog.do" method="POST">
                			<input type="hidden" name="id" value="<%= item.getId() %>" />
                			<input type="submit" name="button" value="X" />
           				</form>
        			</td>
        			<td valign="baseline" style="font-size: x-large"> &nbsp; <%= i + 1 %>. &nbsp; </td>
        			<td valign="baseline">
        				<span style="font-size: x-large">
        					<%= item.getItem().replace("<", "&lt;").replace(">","&gt;").replace("\"","&quot;") %>
        				</span>
                        (uniqueId = <%= item.getId() %>,
                        user = <%= item.getUserName()  %>)
        			</td>
   				</tr>
<%
       		}
%>
      </div>
      </table>
      </div>
     
	
    
</body>
</html>