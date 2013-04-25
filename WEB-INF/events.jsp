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
	 <div class="span9">
        <form action="addEvent.do" method="POST">
            <table>
                <tr> <td colspan="3"> <hr /> </td> </tr>
                <tr>
                    <td style="font-size: large">
                        Event Name:
                    </td>
                    <td colspan="2">
                        <input id="item" type="text" size="40" name="item" />
                    </td>
                    </tr>
                     <tr>
                      <td style="font-size: large">
                        Event Title:
                    </td>
                    <td colspan="2">
                        <input id="title" type="text" size="40" name="title"/>
                    </td>
                     </tr>
                    
                      <tr>
                       <td style="font-size: large">
                        description:
                    </td>
                    <td colspan="2">
                    	<div class="container">
    						<div class="controls">
     					   <textarea class="span6" rows="5" type="text" id="description"  name="description"></textarea>
  					   </div>
                       
                    </td>
                     </tr>
                      <tr>
                       <td style="font-size: large">
                        Category:
                    </td>
                     <td colspan="2">
                        <input id="category" type="text" size="40" name="category"/>
                    </td>
                     <tr>
                </tr>
                 <tr>
                       <td style="font-size: large">
                        Event Date:
                    </td>
                     <td colspan="2">
                        <input id="eventDate" type="date" size="40" name="eventDate"/>
                    </td>
                     <tr>
                </tr>
                <tr>
                    <td/>
                    <td>
                        <input type="submit" name="action" value="Add Event" />
                    </td>
                   
                </tr>
                <tr> <td colspan="3"> <hr /> </td> </tr>
            </table>
        </form>
  </div>
  
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
  </tr>
<%
			for (int i = 0; i < items.length; i++) {
       			EventBean item = items[i];
       			
%>        
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

       	Click <a href="logout.do">here</a> to log out.
    </body>
</html>