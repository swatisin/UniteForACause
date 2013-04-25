<jsp:include page="template-top.jsp" />

<%@page import="java.util.List"%>

<%@page import="databeans.BlogBean"%>
<html>
    <head>
        <title>Search Results </title>
        <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
  
    </head>
    
    <body>
    <p>
    <p>
    <div class="hero-unit">
    
   <div class="row">  
    <div class="span6">  
    
        <h2>Search Results</h2>
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

        
<%
		BlogBean[] items = (BlogBean[]) request.getAttribute("blogs");
%>
		<p style="font-size: x-large">The list now has <%= items.length %> items.</p>

		<table>
<%
			for (int i = 0; i < items.length; i++) {
       			BlogBean item = items[i];
%>        
           		<tr>
       				
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
		</div>
		</div>
<div class="container">
<div class="span6">  

</div>	
    </body>
    
</html>