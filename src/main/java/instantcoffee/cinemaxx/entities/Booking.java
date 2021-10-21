package instantcoffee.cinemaxx.entities;

import instantcoffee.cinemaxx.authentication.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private int bookingId;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @ManyToOne
    @JoinColumn(name = "time_slot_id")
    private TimeSlot timeSlot;


    @ManyToOne()
    @JoinColumn(name = "seat_id")
    private Seat seat;

    public Booking(User user, TimeSlot timeSlot, Seat seat) {
        this.user = user;
        this.timeSlot = timeSlot;
        this.seat = seat;
    }

    public Booking() {
        this.user = new User();
        this.seat = new Seat();
        this.timeSlot = new TimeSlot();
    }

}
