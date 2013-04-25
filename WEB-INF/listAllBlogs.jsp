<jsp:include page="template-top.jsp" />

<%@page import="java.util.List"%>

<%@page import="databeans.BlogBean"%>
<html>
    <head>
        <title>Internal Blogs By Users </title>
        <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <script src="http://www.google.com/jsapi?key=AIzaSyA5m1Nc8ws2BbmPRwKu5gFradvD_hgq6G0" type="text/javascript"></script>
    <script type="text/javascript">
    // Return up to eight results. 
    
    // This code generates a "Raw Searcher" to handle search queries. The Raw Searcher requires
    // you to handle and draw the search results manually.
    google.load('search', '1');
    
    var blogSearch;
    
    function searchComplete() {
    
      // Check that we got results
      document.getElementById('content').innerHTML = '';
      if (blogSearch.results && blogSearch.results.length > 0) {
        for (var i = 0; i < blogSearch.results.length; i++) {
    
          // Create HTML elements for search results
          var p = document.createElement('p');
          var a = document.createElement('a');
          a.href = blogSearch.results[i].postUrl;
          a.innerHTML = blogSearch.results[i].title;
    
          // Append search results to the HTML nodes
          p.appendChild(a);
          document.body.appendChild(p);
        }
      }
    }
    
    function onLoad() {
    
      // Create a BlogSearch instance.
      blogSearch = new google.search.BlogSearch();
      
      // Return up to eight results
      blogSearch.setResultSetSize(google.search.Search.LARGE_RESULTSET);
    
    
      // Set searchComplete as the callback function when a search is complete.  The
      // blogSearch object will have results in it.
      blogSearch.setSearchCompleteCallback(this, searchComplete, null);
    
      // Execute search query
      blogSearch.execute('Charity Blogs');
    
      // Specify that you want to return a large (max of 8) result set
      blogSearch.setResultSetSize(google.search.Search.LARGE_RESULTSET);
    
      // Include the required Google branding
      google.search.Search.getBranding('branding');
    }
    
    // Set a callback to call your code when the page loads
    google.setOnLoadCallback(onLoad);
    
    
    </script>
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
    <p>
    <p>
    <div class="row">  
    <div class="span6">  
     <form action="searchBlog.do" method="POST">
            <table>
                <tr> <td colspan="3"> <hr /> </td> </tr>
                <tr>
                    <td style="font-size: large">
                        Search Blog By Category:
                    
         <div align="center">
		<select name="category">
		<option value="Child Care">Child Care</option>
		<option value="Old Age">Old Age</option>
		<option value="Poverty">Poverty</option>
		<option value="Food">Food</option>
		<option value="Violence">Violence</option>
		<option value="Others">Others</option>
		</select>
		</td>
		</div>
                    </tr>
                    
                    <td>
                        <input type="submit" name="action" value="Search Blog" />
                    </td>
                    
                </tr>
                <tr> <td colspan="3"> <hr /> </td> </tr>
            </table>
        </form>
        </div>
        </div>
        </p>
        </div>
        </div>
      </div>
        
 <div class="hero-unit" >  
        
   <div class="row">  
    <div class="span6">  
    
        <h2>Internal Blogs By Users</h2>
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
		BlogBean[] items = (BlogBean[]) request.getAttribute("items");
%>

    <table class="table table-hover table-bordered table-condensed">
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
		</div>
		      <hr class="featurette-divider">
		
 <div class="hero-unit" >  
<h2>External Blogs on Charity Works</h2>  
<div id="branding" "></div><br />
    <div id="content"></div>
</div>
    </body>
    
</html>