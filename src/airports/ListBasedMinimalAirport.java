package airports;

import airports.exceptions.*;
import flyingObjects.Aircraft;

import java.util.*;

public class ListBasedMinimalAirport  implements  MinimalAirport{

	/*COMPLETE - Ha d'implementar interface MinimalAirport*/

	private List<Aircraft> infrastructure;
	private List<Flight> scheduledFlights;
	private String airportId;
	
	public ListBasedMinimalAirport ()  {
		airportId = "BCN";
		/*COMPLETE*/
		this.scheduledFlights = new ArrayList<>();
		this.infrastructure = new ArrayList<>();
	}

	@Override
	public String getAirportId() {
		return this.airportId;
	}

	@Override
	public int getCapacity() {
		return Integer.MAX_VALUE;
	}

	@Override
	public int size() {
		return 0;
	}

	@Override
	public boolean isFull() {
		return false;
	}

	@Override
	public boolean isEmpty() {
		return infrastructure.isEmpty();
	}

	@Override
	public int getNumFlights() {
		return this.scheduledFlights.size();
	}

	@Override
	public void land(Aircraft a) {
		if (a == null) {
			throw new NullPointerException();
		}
		if(infrastructure.contains(a)){
			throw new AlreadyInAirportException("Ya contiene en la lista");
		}
		infrastructure.add(a);
	}

	@Override
	public void takeOff(Aircraft a) {
		if(a == null){
			throw new NullPointerException();
		}
		if(!infrastructure.contains(a)){
			throw new NotInAirportException("El aircraft no esta en la lista");
		}
		infrastructure.remove(a);
	}

	@Override
	public void addFlight(Flight f) {
		if(f == null){
			throw new NullPointerException();
		}
		if(!this.airportId.equals(f.getOrigin()) || !this.airportId.equals(f.getDestination())){
			throw new FlightScheduleException("Origon or Destination Error");
		}
		if(this.scheduledFlights.contains(f)){
			throw new FlightAlreadyExistsException();
		}
		this.scheduledFlights.add(f);
	}

	@Override
	public void takeOff(Flight f) {
		if(f == null){
			throw new NullPointerException();
		}
		if(!scheduledFlights.contains(f)){
			throw new NotInAirportException("No esta en la lista");
		}
		if(!this.airportId.equals(f.getDestination()) || !this.airportId.equals(f.getOrigin())){
			throw new FlightScheduleException("El origin o Destination esta mal");
		}
		try {
			scheduledFlights.remove(f);
		}catch (Exception e){
			throw new FlightScheduleException("No es pot remove");
		}
	}

	@Override
	public void land(Flight f) {
		if (f == null)
			throw new NullPointerException();

		if (!scheduledFlights.contains(f))
			throw new NotInAirportException("No esta en la lista");

		if (!this.airportId.equals(f.getDestination()))
			throw new FlightScheduleException("Error");

		try {
			land(f.getAircraft());
		} catch (Exception e) {
			throw new FlightScheduleException("Error");
		}

		scheduledFlights.remove(f);
	}


	@Override
	public Flight[] byFlightDepartureTime() {
		List<Flight> sorted = new ArrayList<>(scheduledFlights);
		sorted.sort(Comparator.comparing(Flight::getDepartureTime));
		return sorted.toArray(new Flight[0]);
	}

	@Override
	public Aircraft[] byAircraftName() {
		List<Aircraft> sorted = new ArrayList<>(infrastructure);
		sorted.sort(new AircraftByNameComparator());
		return sorted.toArray(new Aircraft[0]);
	}

	@Override
	public Aircraft[] allAircrafts() {
		List<Aircraft> sorted = new ArrayList<>(infrastructure);
		Collections.sort(sorted);
		return sorted.toArray(new Aircraft[0]);
	}


	
}

class AircraftByNameComparator implements Comparator<Aircraft> {
	
	public AircraftByNameComparator () {}

	public int compare(Aircraft a0, Aircraft a1) {
		/*COMPLETE*/
		String name1 = a0.getName();
		String name2 = a1.getName();

		if(name1 == null && name2 == null) {
			return 0;
		};
		if(name1 == null){
			return -1;
		}
		if(name2 == null){
			return 1;
		}

		return -1; //Change as appropriate
	}
}

