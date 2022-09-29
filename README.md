# Show Booking Application

A simple Java 8 command line application for booking show.

## Usage

1. Execute `run.sh` in a Linux environment that exist in `application` directory
2. Execute `java -jar application/show-booking-1.0.jar` in any environment that as installed Java 8

## Application Commands

Usage: Command  [Parameter(s)]

**Note: All command are `case-insensitive`**

### Admin Mode

1. Setup  `Show Number` `Number of Rows` `Number of Seats`  `Cancellation window in minutes`

> Purpose: To setup the number of seats per show

| Parameter(s)                   | Value Type                   | Description                                                                   |
|--------------------------------|------------------------------|-------------------------------------------------------------------------------|
| Show Number / Show #           | Numerical                    | Number that will be used to identify the show                                 |
| Number of Rows                 | Numerical; Valid Range: 1-26 | Number of Rows Available at the show                                          |
| Number of Seats                | Numerical; Valid Range: 1-26 | Number of Seats Available Per Row                                             |
| Cancellation Window In Minutes | Numerical; Valid Range: 1-5  | Duration, in minutes, of window of which user are able to cancel their ticket |

2. View `Show Number`

> Purpose: To setup the number of seats per show

| Parameter(s)                   | Value Type                   | Description                                                                   |
|--------------------------------|------------------------------|-------------------------------------------------------------------------------|
| Show Number / Show #           | Numerical                    | Number that will be used to identify the show                                 |

### Buyer Mode

1. View

> Purpose: View all Show Number

2. Availability `Show Number`

> Purpose: To list all available seat numbers for a show

| Parameter(s)                   | Value Type                   | Description                                                                   |
|--------------------------------|------------------------------|-------------------------------------------------------------------------------|
| Show Number / Show #           | Numerical                    | Number that will be used to identify the show                                 |

3. Book `Show Number` `Phone Number` `Seats Number(s)`

> Purpose: To book seats in a show. Ticket Number will be displayed when booking is successful

| Parameter(s)                   | Value Type                                                | Description                                                                   |
|--------------------------------|-----------------------------------------------------------|-------------------------------------------------------------------------------|
| Show Number / Show #           | Numerical                                                 | Number that will be used to identify the show                                 |
| Phone Number                   | -                                                         | Phone Number of User                                                          |
| Seats Number(s)                | Format A1, A2. Multiple Seats can be seperated by command | Selected Seats' Number                                                        |

4. Cancel `Ticket Number` `Phone Number`

> Purpose: To cancel a ticket

| Parameter(s)             | Value Type | Description                                    |
|--------------------------|------------|------------------------------------------------|
| Ticket Number / Ticket # | -          | Ticket Number that was generated after booking |
| Phone Number             | -          | Phone Number of User                           |

