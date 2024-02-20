CREATE TABLE `travel_group` (
  `id` BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `budget` decimal(38,2) DEFAULT NULL,
  `departure_date` datetime(6) DEFAULT NULL,
  `destination` varchar(255) DEFAULT NULL,
  `itinerary` varchar(255) DEFAULT NULL,
  `minimum_number_of_members` int(11) DEFAULT NULL,
  `return_date` datetime(6) DEFAULT NULL,
  `owner_id` bigint(20) DEFAULT NULL
);