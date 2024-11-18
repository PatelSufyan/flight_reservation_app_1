package com.flight_reservation_app_1.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flight_reservation_app_1.dto.ReservationRequest;
import com.flight_reservation_app_1.entity.Flight;
import com.flight_reservation_app_1.entity.Passenger;
import com.flight_reservation_app_1.entity.Reservation;
import com.flight_reservation_app_1.repository.FlightRepository;
import com.flight_reservation_app_1.repository.PassengerRepository;
import com.flight_reservation_app_1.repository.ReservationRepository;
import com.flight_reservation_app_1.util.EmailUtil;
import com.flight_reservation_app_1.util.PDFGenerator;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	private PassengerRepository passengerRepo;

	@Autowired
	private FlightRepository flightRepo; 

	@Autowired
	private ReservationRepository reservationRepo;

	@Autowired
	private PDFGenerator pdfGenerator;
	
	@Autowired
	private EmailUtil emailUtil;

	@Override
	public Reservation bookFlight(ReservationRequest request) {
		
		Passenger passenger = new Passenger();
		passenger.setFirstName(request.getFirstName());
		passenger.setLastName(request.getLastName());
		passenger.setMiddleName(request.getMiddleName());
		passenger.setEmail(request.getEmail());
		passenger.setPhone(request.getPhone());
		passengerRepo.save(passenger);

		long flightId = request.getFlightId();
		Optional<Flight> findById = flightRepo.findById(flightId);
		Flight flight = findById.get();

		Reservation reservation = new Reservation();
		reservation.setFlight(flight);
		reservation.setPassenger(passenger);
		reservation.setCheckedIn(false);
		reservation.setNumberOfBags(0);

		String filePath = "F:\\STS Workspace\\flight_reservation_app_1\\tickets\\reservation"+reservation.getId()+".pdf";
		
		reservationRepo.save(reservation);

		pdfGenerator.generateItenary(reservation, filePath);
		
		emailUtil.sendItinerary(passenger.getEmail(), filePath);

		return reservation;
	}

}
