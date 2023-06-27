INSERT INTO kitchen_category (`id`, `name`) VALUES
(NULL, 'Biologisch'),
(NULL, 'Glutenvrij'),
(NULL, 'Lactosevrij'),
(NULL, 'Bevat vlees'),
(NULL, 'Bevat vis'),
(NULL, 'Bevat schaaldieren'),
(NULL, 'Bevat mosterd'),
(NULL, 'Bevat ei'),
(NULL, 'Vegan'),
(NULL, 'Eenvoudig'),
(NULL, 'Snel'),
(NULL, 'Goedkoop'),
(NULL, 'Keto'),
(NULL, 'Diët'),
(NULL, 'Koolhydraatarm'),
(NULL, 'Feestelijk'),
(NULL, 'Bakken'),
(NULL, 'Koekjes'),
(NULL, 'Salade'),
(NULL, 'Ovenschotel'),
(NULL, 'Wokgerecht');

INSERT INTO kitchen_region (`id`, `name`) VALUES
(NULL, 'Afrikaans'),
(NULL, 'Amerikaans'),
(NULL, 'Argentijns'),
(NULL, 'Australisch'),
(NULL, 'Aziatisch'),
(NULL, 'Belgisch'),
(NULL, 'Chinees'),
(NULL, 'Duits'),
(NULL, 'Engels'),
(NULL, 'Filipijns'),
(NULL, 'Frans'),
(NULL, 'Italiaans'),
(NULL, 'Japans'),
(NULL, 'Marokkaans'),
(NULL, 'Mexicaans'),
(NULL, 'Nederlands'),
(NULL, 'Pools'),
(NULL, 'Scandinavisch'),
(NULL, 'Spaans'),
(NULL, 'Thais'),
(NULL, 'Vietnamees');

INSERT INTO kitchen_type (`id`, `name`) VALUES
(NULL, 'Vlees'),
(NULL, 'Vis'),
(NULL, 'Vegetarisch'),
(NULL, 'Vegan');

INSERT INTO `calendar_item` (`id`, `date`, `description`, `title`) VALUES 
(NULL, '2023-06-21 14:00:00', 'Een online workshop vegetarisch koken, onder leiding van Trientje Hupsakee', 'Vegetarisch koken'),
(NULL, '2023-06-28 14:00:00', 'Een online workshop vegetarisch koken, onder leiding van Trientje Hupsakee', 'Vegetarisch koken'),
(NULL, '2023-08-02 14:00:00', 'Een online workshop vegetarisch koken, onder leiding van Trientje Hupsakee', 'Vegetarisch koken'),
(NULL, '2023-08-09 14:00:00', 'Een online workshop vegetarisch koken, onder leiding van Trientje Hupsakee', 'Vegetarisch koken'),
(NULL, '2023-08-16 14:00:00', 'Een online workshop vegetarisch koken, onder leiding van Trientje Hupsakee', 'Vegetarisch koken'),
(NULL, '2023-08-23 14:00:00', 'Een online workshop vegetarisch koken, onder leiding van Trientje Hupsakee', 'Vegetarisch koken'),
(NULL, '2023-08-30 14:00:00', 'Een online workshop vegetarisch koken, onder leiding van Trientje Hupsakee', 'Vegetarisch koken');

INSERT INTO `rating` (`id`, `rating`, `recipe_id`, `user_id`) VALUES 
(NULL, '1', '1', '1'),
(NULL, '2', '2', '1'),
(NULL, '3', '3', '1'),
(NULL, '4', '4', '1');