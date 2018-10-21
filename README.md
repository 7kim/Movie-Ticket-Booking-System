# Movie Ticket Booking System

This is a movie ticket booking systems implemented in Java, involving in interation with Database with **DAO pattern**
and crawling movie information from the web with jsoup.

## Getting Started

The following instructions will get you a copy of the project and running on your local machine for testing purposes.

### Prerequisite & Toolkits

The following are some toolkits and their version I used when developing this project

* [Java 8](https://www.oracle.com/technetwork/java/javase/overview/java8-2100321.html)
* [jsoup](https://jsoup.org/)
* [MySQL](https://www.mysql.com/)

## Functions

* [Collecting movie data]: Using jsoup to crawl movie information from 10+ websites to our database.
* [Movie information search]: Provide users to search for interested movie, showing them the movie's information, available seats left,
and the schedule of the week.
* [Booking/Canceling ticket]: Users are allowed to book/cancel a ticket if their are seats available and 20 minutes before show time. If an user
try to book/cancel ticket after this threshold, the system would block this operation.
* [Seats selection]: Implemented a user friendly interface to allow user to select and cancel their seats with an image of the seats positions in
the cinema.
* [Transaction management]: Store transaction records in database to allow manager to analyize user behaviors and for system purposes.
