package airports;

import airports.exceptions.FlightScheduleException;
import flyingObjects.Aircraft;

import java.util.Date;

public class Flight implements  ScheduledTravel{
    /*TODO: Ensure that flight implements interface ScheduledTravel*/

    private String id;
    private String origin;
    private String destination;
    private Aircraft aircraft;
    private Date departure;
    private Date arrival;


    /**
     * Creates a flight with the given parameters. Throws the following exceptions:
     * - IllegalArgumentException if id is null
     * - IllegalArgumentException if aircraft is null
     * - FlightScheduleException if either departure or arrival (or both) are null
     * - FlightScheduleException if either origin or destination (or both) are null
     * - FlightScheduleException if departure date is later than or equal to arrival date
     * Ensure that the exceptions include a relevant message informing of the error
     * @param id
     * @param aircraft
     * @param origin
     * @param destination
     * @param departure
     * @param arrival
     */
    public Flight(String id, Aircraft aircraft, String origin, String destination, Date departure, Date arrival){
        /*COMPLETE*/
        if(id == null){
            throw new IllegalArgumentException();
        }else if(aircraft == null){
            throw new IllegalArgumentException();
        }else if(arrival == null || departure == null) {
            throw new FlightScheduleException("Arrival o departure no puede ser null");
        }else if (destination == null || origin == null){
            throw new FlightScheduleException("destination o origin no puede ser null");
        }else if (departure.after(arrival) || departure.equals(arrival)){
            throw new FlightScheduleException("Fecha error");
        }
        this.id = id;
        this.aircraft = aircraft;
        this.origin = origin;
        this.destination = destination;
        this.departure = departure;
        this.arrival = arrival;
    }

    /*COMPLETE*/

    /**
     * Two flights are equal if their id is the same (case insensitive).
     * @param o
     * @return true if both flights have the same id, false otherwise
     */
    @Override
    public boolean equals(Object o){
        /*COMPLETE*/
        if(o instanceof Flight){
            return false;
        }else{
            Flight f = (Flight) o;
            if (f.id == this.id){
                return true;
            }else{
                return false;
            }
        }
    }

    public String getOrigin() {
        return this.origin;
    }
    public String getDestination(){
        return this.destination;
    }
    public Aircraft getAircraft(){
        return this.aircraft;
    }

    @Override
    public Date getDepartureTime() {
        return this.departure;
    }

    @Override
    public Date getArrivalTime() {
        return this.arrival;
    }
}


