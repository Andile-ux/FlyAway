//package src;
//@RestController
//@RequestMapping("/flights")
//public class FlightController {
//    @Autowired
//    private FlightRepository flightRepository;
//
//    @GetMapping("/search")
//    public List<Flight> searchFlights(
//            @RequestParam String source,
//            @RequestParam String destination,
//            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date departureDate
//    ) {
//        return flightRepository.findBySourceAndDestinationAndDepartureDate(source, destination, departureDate);
//    }
//
//    // Implement endpoints for booking flights and payment handling
//}
