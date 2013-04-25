<jsp:include page="template-top.jsp" />

<%@page import="java.util.List"%>

<%@page import="databeans.BlogBean"%>
<html>
    <head>
        <title>Add A new Blog </title>
    </head>
    
    <body>
    
        <h2>Add A new Blog</h2>
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

        <form action="addBlog.do" method="POST">
            <table>
                <tr> <td colspan="3"> <hr /> </td> </tr>
                <tr>
                    <td style="font-size: large">
                        Blog Name:
                    </td>
                    <td colspan="2">
                        <input id="item" type="text" size="40" name="item"/>
                    </td>
                    </tr>
                     <tr>
                      <td style="font-size: large">
                        Blog Title:
                    </td>
                    <td colspan="2">
                        <input id="title" type="text" size="40" name="title"/>
                    </td>
                     </tr>
                    
                      <tr>
                       <td style="font-size: large">
                        Content:
                    </td>
                    <td colspan="2">
                    	<div class="container">
    						<div class="controls">
     					   <textarea class="span8" rows="15" type="text" id="content"  name="content"></textarea>
  					   </div>
                       
                    </td>
                     </tr>
                      <tr>
                       <td style="font-size: large">
                        Category:
                    </td>
                     <td colspan="2">
                        <select name="category">
						<option value="Child Care">Child Care</option>
						<option value="Old Age">Old Age</option>
						<option value="Poverty">Poverty</option>
						<option value="Food">Food</option>
						<option value="Violence">Violence</option>
						<option value="Others">Others</option>
					</select>
                    </td>
                     <tr>
                </tr>
                <tr>
                    <td/>
                    <td>
                        <input type="submit" name="action" value="Add" />
                    </td>
                   
                </tr>
                <tr> <td colspan="3"> <hr /> </td> </tr>
            </table>
        </form>
        
<%
		BlogBean[] items = (BlogBean[]) request.getAttribute("items");
%>
		<p style="font-size: x-large">The list now has <%= items.length %> items.</p>

		<table>
<%
			for (int i = 0; i < items.length; i++) {
       			BlogBean item = items[i];
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
		</table>
		Click <a href="addEvent.do">here</a> to add an event
		Click <a href="donation.do">Donation</a> to add an event
       	Click <a href="logout.do">here</a> to log out.
    </body>
</html>