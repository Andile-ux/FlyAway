//package src;
//
//@RestController
//@RequestMapping("/bookings")
//public class BookingController {
//    @Autowired
//    private BookingRepository bookingRepository;
//    @Autowired
//    private FlightRepository flightRepository;
//
//    @PostMapping
//    public ResponseEntity<String> bookFlight(@RequestParam Long flightId, @RequestParam Long userId) {
//        // Check if the flight exists
//        Flight flight = flightRepository.findById(flightId).orElse(null);
//        if (flight == null) {
//            return ResponseEntity.badRequest().body("Flight not found");
//        }
//
//        // Check if the flight is available and not fully booked (you may need to add more checks)
//        if (isFlightFullyBooked(flight)) {
//            return ResponseEntity.badRequest().body("The selected flight is fully booked");
//        }
//
//        // Check if the user has already booked this flight
//        if (hasUserBookedThisFlight(userId, flightId)) {
//            return ResponseEntity.badRequest().body("You have already booked this flight");
//        }
//
//        // Create and save the booking
//        Booking booking = new Booking();
//        booking.setFlightId(flightId);
//        booking.setUserId(userId);
//        booking.setPaid(false); // Payment is not completed initially
//        bookingRepository.save(booking);
//
//        return ResponseEntity.ok("Booking successful");
//    }
//
//    // Helper methods for validation checks
//    private boolean isFlightFullyBooked(Flight flight) {
//        // Implement your logic to check if the flight is fully booked
//        // For example, you can compare the number of booked seats with the total capacity
//        return false; // Replace with your logic
//    }
//
//    private boolean hasUserBookedThisFlight(Long userId, Long flightId) {
//        // Implement your logic to check if the user has already booked this flight
//        List<Booking> userBookings = bookingRepository.findByUserId(userId);
//        return userBookings.stream().anyMatch(booking -> booking.getFlightId().equals(flightId));
//    }
//}
