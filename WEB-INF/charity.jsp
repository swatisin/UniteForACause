<jsp:include page="template-top.jsp" />

<%@page import="java.util.List"%>

<%@page import="databeans.CharityBean"%>

<html lang="en">
    <head>
        <meta http-equiv="Content-type" content="text/html; charset=utf-8" />
        <title>Stripe Sample Form</title>
 
        <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>
        <script type="text/javascript" src="https://ajax.aspnetcdn.com/ajax/jquery.validate/1.8.1/jquery.validate.min.js"></script>
        <script type="text/javascript" src="https://js.stripe.com/v1/"></script>
        <script type="text/javascript">
          Stripe.setPublishableKey('pk_test_KcdsOSI9ltSMqKOULjJvRhhD');
            $(document).ready(function() {
                function addInputNames() {
                    // Not ideal, but jQuery's validate plugin requires fields to have names
                    // so we add them at the last possible minute, in case any javascript 
                    // exceptions have caused other parts of the script to fail.
                    $(".card-number").attr("name", "card-number")
                    $(".card-cvc").attr("name", "card-cvc")
                    $(".card-expiry-year").attr("name", "card-expiry-year")
                }
 
                function removeInputNames() {
                    $(".card-number").removeAttr("name")
                    $(".card-cvc").removeAttr("name")
                    $(".card-expiry-year").removeAttr("name")
                }
 
                function submit(form) {
                    // remove the input field names for security
                    // we do this *before* anything else which might throw an exception
                    removeInputNames(); // THIS IS IMPORTANT!
 
                    // given a valid form, submit the payment details to stripe
                    $(form['submit-button']).attr("disabled", "disabled")
 
                    Stripe.createToken({
                        number: $('.card-number').val(),
                        cvc: $('.card-cvc').val(),
                        exp_month: $('.card-expiry-month').val(), 
                        exp_year: $('.card-expiry-year').val()
                    }, function(status, response) {
                        if (response.error) {
                            // re-enable the submit button
                            $(form['submit-button']).removeAttr("disabled")
        
                            // show the error
                            $(".payment-errors").html(response.error.message);
 
                            // we add these names back in so we can revalidate properly
                            addInputNames();
                        } else {
                            // token contains id, last4, and card type
                            var token = response['id'];
 
                            // insert the stripe token
                            var input = $("<input name='stripeToken' value='" + token + "' style='display:none;' />");
                            form.appendChild(input[0])
 
                            // and submit
                            form.submit();
                        }
                    });
                    
                    return false;
                }
                
                // add custom rules for credit card validating
                jQuery.validator.addMethod("cardNumber", Stripe.validateCardNumber, "Please enter a valid card number");
                jQuery.validator.addMethod("cardCVC", Stripe.validateCVC, "Please enter a valid security code");
                jQuery.validator.addMethod("cardExpiry", function() {
                    return Stripe.validateExpiry($(".card-expiry-month").val(), 
                                                 $(".card-expiry-year").val())
                }, "Please enter a valid expiration");
 
                // We use the jQuery validate plugin to validate required params on submit
                $("#form").validate({
                    submitHandler: submit,
                    rules: {
                        "card-cvc" : {
                            cardCVC: true,
                            required: true
                        },
                        "card-number" : {
                            cardNumber: true,
                            required: true
                        },
                        "card-expiry-year" : "cardExpiry" // we don't validate month separately
                    }
                });
 
                // adding the input field names is the last step, in case an earlier step errors                
                addInputNames();
            });
        </script>
           <style>
  .bottom-three {
     margin-bottom: 3cm;
  }
</style>
    </head>
    <body>
     </div>
  
    <p class="bottom-three">
        <h2>Donation Till Now</h2>
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
    </div>
<div class="hero-unit"	 >      
<%
CharityBean[] items = (CharityBean[]) request.getAttribute("items");
%>
		<p style="font-size: x-large">You have <%= items.length %> upcoming Events.</p>

		<table class="table table-hover table-bordered table-condensed">
		<tr>
    		<th>#</th>
    		<th>Charity Event</th>
    		<th>Amount</th>
    		<th>Category</th>
  </tr>
<%
			for (int i = 0; i < items.length; i++) {
				CharityBean item = items[i];
       			
%>        
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
 <%
	CharityBean newItem = (CharityBean) request.getAttribute("itemSent");
%>
       
 <div class="hero-unit" type="primary" title="primary">   
  <h1 >Donate Today</h1>
        <form action="donation.do" method="post" id="form" style="display: none;">
 
 			<div class="form-row" style="float:right; width:50%;">
                <label for="title" class="stripeLabel">Title</label>
                <%
				    String flag = (String) request.getAttribute("flag");
						if (flag == null) {
				%>
                	<input type="text" name="title" class="required " />
                <%
    					} else {
				%>
                	<input type="text" name="title" class="required value=" value="<%= newItem.getTitle()%>" />
               	<%
    					}
				%>
            </div> 
            
           <div class="form-row" style="float:right; width:50%;">
                <label for="amount" class="stripeLabel">Amount</label>
                <%
				     flag = (String) request.getAttribute("flag");
						if (flag == null) {
				%>
                	<input type="text" name="amount" class="required " />
                <%
    					} else {
				%>
                	<input type="text" name="amount" class="required value=" value="<%= newItem.getAmount()%>" />
               	<%
    					}
				%>
            </div>           
            <div class="form-row" style="float:right; width:50%;">
                <label for="description" class="stripeLabel">Description</label>
                <%
				     flag = (String) request.getAttribute("flag");
						if (flag == null) {
				%>
                	<input type="text" name="description" class="required " />
                <%
    					} else {
				%>
                	<input type="text" name="description"class="required value=" value="<%= newItem.getDescription()%>" />
               	<%
    					}
				%>
            </div>             
    		 <div class="form-row" style="float:right; width:50%;">
                <label for="name" class="stripeLabel">Your Name</label>
                <input type="text" name="name" class="required" />
            </div>  
             <div class="form-row" style="float:right; width:50%;">
                <label for="category" class="stripeLabel">Category</label>
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
            </div>  
            <div class="form-row" style="float:right; width:50%;">
                <label>Card Number</label>
                <input type="text" maxlength="20" autocomplete="off" class="card-number stripe-sensitive required" name="cardNumber"/>
            </div>
            
            <div class="form-row" style="float:right; width:50%;">
                <label>CVC</label>
                <input type="text" maxlength="4" autocomplete="off" class="card-cvc stripe-sensitive required" />
            </div>
            
            <div class="form-row" style="float:right; width:50%;">
                <label>Expiration</label>
                <div class="expiry-wrapper">
                    <select class="card-expiry-month stripe-sensitive required">
                    </select>
                    <script type="text/javascript">
                        var select = $(".card-expiry-month"),
                            month = new Date().getMonth() + 1;
                        for (var i = 1; i <= 12; i++) {
                            select.append($("<option value='"+i+"' "+(month === i ? "selected" : "")+">"+i+"</option>"))
                        }
                    </script>
                    <span> / </span>
                    <select class="card-expiry-year stripe-sensitive required"></select>
                    <script type="text/javascript">
                        var select = $(".card-expiry-year"),
                            year = new Date().getFullYear();
 
                        for (var i = 0; i < 12; i++) {
                            select.append($("<option value='"+(i + year)+"' "+(i === 0 ? "selected" : "")+">"+(i + year)+"</option>"))
                        }
                    </script>
                </div>
            </div>
           <input type="submit" name="action" value="Add" />
            <span class="payment-errors"></span>
        </form>
        </div>
        

        <!-- 
            The easiest way to indicate that the form requires JavaScript is to show
            the form with JavaScript (otherwise it will not render). You can add a
            helpful message in a noscript to indicate that users should enable JS.
        -->
        <script>if (window.Stripe) $("#form").show()</script>
        <noscript><p>JavaScript is required for the registration form.</p></noscript>
 
    </body>
</html>