<jsp:include page="template-top.jsp" />

<%@page import="java.util.List"%>

<%@page import="databeans.EventBean"%>
<%@page import="formbeans.*"%>


<html>
    <head>
        <title>Event Schedular </title>
    </head>
    
    <body>
    
        <h2>Event Schedular</h2>
<%
		List<String> errors = (List<String>) request.getAttribute("errors");
		if (errors != null) {
			for (String error : errors) {
%>		
				<h3 style="color:red"> <%= error %> </h3>
<%
			}
		}
		EventForm form = (EventForm) request.getAttribute("form");
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
        <%
		EventBean[] item = (EventBean[]) request.getAttribute("item");
		EventBean item1 = item[0];

		%>
	 <div class="span9">
        <form action="registerEvent.do" method="POST">
            <table>
                <tr> <td colspan="3"> <hr /> </td> </tr>
                <tr>
                    <td style="font-size: large">
                        Event Title:
                    </td>
                    <td colspan="2">
                        <input id="title" type="text" size="40" name="title" value="<%= item1.getTitle() %>" disabled/>
                    </td>
                    </tr>
                     <tr>
                      <td style="font-size: large">
                        Event Category:
                    </td>
                    <td colspan="2">
                        <input id="category" type="text" size="40" name="category" value="<%= item1.getCategory() %>" disabled/>
                    </td>
                     </tr>
                    
                      <tr>
                       <td style="font-size: large">
                        Description:
                    </td>
                    <td colspan="2">
                    	<div class="container">
    						<div class="controls">
     					   <textarea class="span6" rows="5" type="text" id="description"  name="description" value="<%= item1.getDescription() %>" disabled></textarea>
  					   </div>
                       
                    </td>
                     </tr>
                      <tr>
                       <td style="font-size: large">
                    </td>
                     <td colspan="2">
                    </td>
                     <tr>
                </tr>
                 <tr>
                       <td style="font-size: large">
                        Event Date:
                    </td>
                     <td colspan="2">
                        <input id="eventDate" type="text" size="40" name="eventDate" value="<%= item1.getEventDate() %>" disabled/>
                    </td>
                     <tr>
                </tr>
                <tr>
                    <td/>
                    <td>
                    <input type="hidden" name="item" id="item" value=<%=item1.getTitle()%> /><br />
                       <input type="hidden" name="id" id="id" value=<%=item1.getId()%> /><br />
                       <input type="submit" name="action" value="Register" />
                    </td>
                   
                </tr>
                <tr> <td colspan="3"> <hr /> </td> </tr>
            </table>
        </form>
  </div>
  
         	Click <a href="logout.do">here</a> to log out.
    </body>
</html>