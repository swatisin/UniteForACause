<jsp:include page="template-top.jsp" />

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Sign in &middot; Twitter Bootstrap</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Le styles -->
    <link href="css/bootstrap.css" rel="stylesheet">
    <style type="text/css">
      body {
        padding-top: 40px;
        padding-bottom: 40px;
        background-color: #f5f5f5;
      }

      .form-signin {
        max-width: 300px;
        padding: 19px 29px 29px;
        margin: 0 auto 20px;
        background-color: #fff;
        border: 1px solid #e5e5e5;
        -webkit-border-radius: 5px;
           -moz-border-radius: 5px;
                border-radius: 5px;
        -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.05);
           -moz-box-shadow: 0 1px 2px rgba(0,0,0,.05);
                box-shadow: 0 1px 2px rgba(0,0,0,.05);
      }
      .form-signin .form-signin-heading,
      .form-signin .checkbox {
        margin-bottom: 10px;
      }
      .form-signin input[type="text"],
      .form-signin input[type="password"] {
        font-size: 16px;
        height: auto;
        margin-bottom: 15px;
        padding: 7px 9px;
      }

    </style>
    <link href="css/bootstrap-responsive.css" rel="stylesheet">

    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="js/html5shiv.js"></script>
    <![endif]-->

    <!-- Fav and touch icons -->
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="ico/apple-touch-icon-114-precomposed.png">
      <link rel="apple-touch-icon-precomposed" sizes="72x72" href="ico/apple-touch-icon-72-precomposed.png">
                    <link rel="apple-touch-icon-precomposed" href="ico/apple-touch-icon-57-precomposed.png">
                                   <link rel="shortcut icon" href="ico/favicon.png">
  </head>
  
<p style="font-size:medium">
    
</p>

<jsp:include page="error-list.jsp" />

<p>
    <div class="container">

        <h2 class="form-signin-heading">To register, enter the following information. (All fields required.)</h2>

	<form method="post">
		<input type="hidden" class="input-block-level" name="redirect" value="${redirect}"/>
		<table>
			<tr>
				<td>First Name: </td>
				<td><input type="text" name="firstName" value="${form.firstName}"/></td>
			</tr>
			<tr>
				<td>Last Name: </td>
				<td><input type="text" name="lastName" value="${form.lastName}"/></td>
			</tr>
			<tr>
				<td>User Name: </td>
				<td><input type="text" name="userName" value="${form.userName}"/></td>
			</tr>
			<tr>
				<td>Email Address: </td>
				<td><input type="text" name="emailAddress" value="${form.emailAddress}"/></td>
			</tr>
			<tr>
				<td>Password: </td>
				<td><input type="password" name="password" value=""/></td>
			</tr>
			<tr>
				<td>Confirm Password: </td>
				<td><input type="password" name="confirm" value=""/></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" name="button" value="Register"/>
				</td>
			</tr>
		</table>
	</form>
</p>

<jsp:include page="template-bottom.jsp" />

