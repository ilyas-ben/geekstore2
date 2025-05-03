INSERT INTO geekstore.category (id, name)
VALUES (11, 'Electronics'),
       (12, 'Books'),
       (13, 'Clothing'),
       (14, 'Home Appliances'),
       (15, 'Sports Equipment');


insert into geekstore.product (id, description, image_path, name, price, quantity_stock, category_id, seller_id)
values (21, 'Latest 4K UHD Smart TV with voice control', '/images/tv.jpg', 'Smart TV 55"', 7999, 25, 11, null),
       (22, 'Bestselling fantasy novel trilogy', '/images/book.jpg', 'Fantasy Trilogy Set', 349, 120, 12, null),
       (23, 'Waterproof winter jacket for men', '/images/jacket.jpg', 'Winter Jacket M', 899, 40, 13, null),
       (24, 'High-capacity refrigerator with inverter technology', '/images/fridge.jpg', 'Refrigerator 350L', 5499, 15,
        14, null),
       (25, 'Professional-grade tennis racket', '/images/racket.jpg', 'Tennis Racket Pro', 1299, 30, 15, null);