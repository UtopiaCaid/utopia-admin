package com.caid.utopia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.WebUtils;

import com.caid.utopia.entity.Flight;
import com.caid.utopia.entity.Paginator;
import com.caid.utopia.service.FlightService;

import exception.FlightByIdException;
import exception.FlightCreationException;
import exception.FlightDeletionException;
import exception.FlightDetailsException;
import exception.RecordNotFoundException;
import exception.RecordPaginationException;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/admin")
@RestController
public class FlightController {
	
	public static final String PAGE_NOT_FOUND_LOG_CATEGORY = "org.springframework.web.servlet.PageNotFound";

	@Autowired
	FlightService flightService;
	
	

	protected ResponseEntity<Object> handleExceptionInternal(
			Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

		if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
			request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
		}
		return new ResponseEntity<>(body, headers, status);
	}
	
	@ExceptionHandler(RecordNotFoundException.class)
	@RequestMapping(value = "/flights", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Flight>> getAllFlight(){
		List<Flight> flights = flightService.getAllFlight();
		if (flights.size() == 0) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<>(flights, HttpStatus.OK);
		}
		
	}
	

	@ExceptionHandler(FlightByIdException.class)
	@RequestMapping(value = "/flights/{flightId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Flight> getFlight(@PathVariable Integer flightId){
		Flight flights = flightService.getFlightById(flightId);
		return new ResponseEntity<>(flights, HttpStatus.OK);
	}
	
	@ExceptionHandler(FlightCreationException.class)
	@RequestMapping(value = "/flights", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<Flight> flightInsertion(@RequestBody Flight newFlight) {
		Flight updatedFlights = flightService.addFlight(newFlight);
		if (updatedFlights.getFlightNo() == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(newFlight, HttpStatus.OK);
		}			
	}
	
	@ExceptionHandler(FlightDeletionException.class)
	@RequestMapping(value = "/flights", method = RequestMethod.DELETE, consumes = "application/json")
	public ResponseEntity<Flight> flightDeletion(@RequestBody Flight flight) {
		if(flight.getFlightNo() == null) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
		Flight updatedFlight = flightService.deleteFlight(flight);
		if (updatedFlight == flight){
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		} else {
			return new ResponseEntity<>(flight, HttpStatus.OK);
		}
	}
	
	@ExceptionHandler(FlightDetailsException.class)
	@RequestMapping(value = "/flights", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	public ResponseEntity<Flight> flightDetailsUpdate(@RequestBody Flight flight) {
		List <Flight> updatedFlights = flightService.updateFlight(flight);
		if(updatedFlights.size() == 0) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		} else {
			return new ResponseEntity<>(flight, HttpStatus.OK);
		}
	}
	
	@ExceptionHandler(RecordPaginationException.class)
	@RequestMapping(value = "/flights/pagination", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<List<Flight>> getFlightPagination(@RequestBody Paginator paginator) {
		Page<Flight> flights = flightService.getPaginatedFlights(
				paginator.getField(), paginator.getSort(), 
				paginator.getPageSize(), paginator.getPage());
		if(flights.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(flights.getContent(), HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/flightcount", method = RequestMethod.GET)
	public ResponseEntity<Integer> getFlightCount() {
		return new ResponseEntity<Integer>(flightService.getFlightCount(), HttpStatus.OK); 
	}
}
