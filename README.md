# CinemaXX

KEA - AP Computer Science\
Third Semester\
Instant Coffee Group\
CinemaXX Project

**Authors:**

* v3s1e: Veselin Ivanov


## Veselin Ivanov


### Create booking

POST /api/booking/create takes in `theaterHallId`, `movieId`, `timeSlotId` and `seatId`

We query the database for a Theater Hall with the given `theaterHallId` and if
it exists we get the Movies that are shown in that Theater Hall. We filter out
the movies that do not match the given `movieId`. If there is a Movie left, we
take the available Time Slots and filter out the ones that do not match the
given `timeSlotId`. If there is a Time Slot left, we check if the seat with the
given `seatId` is taken by taking the bookings of the Time Slot and filtering
out the ones that do not match the `seatId`. If the Seat is not taken, we check
if the Theater Hall has that seat and if it does we create a new Booking.

### Security

Right now every endpoint requires authentication except:

* `/api/authenticate/signup`
* `/api/authenticate/login`
* `/api/swagger`
* `/api/api-docs`

There are no roles implemented at the moment, meaning if you are signed up, you
can perform all available operations.

#### How to sign up

POST `/api/authenticate/signup` with `username` and `password`


#### How to log in

POST `/api/authenticate/login` with `username` and `password`

You will get a token back.


There is OpenAPI specification for the two endpoints mentioned above.


#### Authenticate

Whenever you make a request add the token to the Authorization header as a Bearer token.


#### How to get the User inside a controller

```
@GetMapping
public String hello(@AuthenticationPrincipal User user) {
  System.out.println("HELLO");
  System.out.println(user);
  return "hello";
}
```
