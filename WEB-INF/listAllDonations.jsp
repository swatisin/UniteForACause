<jsp:include page="template-top.jsp" />

<%@page import="java.util.List"%>

<%@page import="databeans.CharityBean"%>
<html>
    <head>
        <title>Donations </title>
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
		CharityBean[] items = (CharityBean[]) request.getAttribute("items");
%>
		<p style="font-size: x-large">You have <%= items.length %> upcoming Events.</p>

		<table class="table table-hover table-bordered table-condensed">
		<tr>
    		<th>#</th>
    		<th>Charity Name</th>
    		<th>Description</th>
    		<th>Category</th>
    		<th>Donation Link</th>
    		
    		
  </tr>
<%
			for (int i = 0; i < items.length; i++) {
				CharityBean item = items[i];
       			
%>        
        			<td ><%= i + 1 %> </td>
        			<td><%= item.getTitle().replace("<", "&lt;").replace(">","&gt;").replace("\"","&quot;") %> </td>
                   	<td>     <%= item.getDescription() %> </td>
                    <td>     <%= item.getCategory()  %> </td>
                     <td>
                    <form action="registerDonations.do" method="POST">
                     <input type="hidden" name="id" id="id" value=<%=item.getId()%> /><br />
                     <input type="submit" name="action" value="Donate" />
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