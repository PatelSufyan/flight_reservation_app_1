<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Reservation Details</title>
</head>
<body>
	<h2>Flight Details</h2>
	
	Flight Number :  ${flight.flightNumber} <br>
	Operating Airlines : ${flight.operatingAirlines} <br>
	Departure City : ${flight.departureCity} <br>
	Arrival City : ${flight.arrivalCity} <br>
	Departure Date : ${flight.dateOfDeparture}
	
	<h2>Enter Passenger Details</h2>
	
	<form action="confirmReservation" method="post">
		First Name <input type="text" name="firstName"/>
		Last Name <input type="text" name="lastName"/>
		Middle Name <input type="text" name="middleName"/>
		Email <input type="text" name="email"/>
		Phone <input type="text" name="phone"/><br>
		<input type="hidden" name="flightId" value="${flight.id}"><br>
	
		Card Number <input type="text" name="cardNumber"/>
		Card Name <input type="text" name="cardName"/>
		Cvv  <input type="text" name="cvv"/>
		Expiry Date <input type="text" name="expiryDate"> <br> <br>
				
		<input type="submit" value="Complete Reservation">
	</form>
		
	
</body>
</html>