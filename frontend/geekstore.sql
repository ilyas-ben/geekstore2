

INSERT INTO category (name) VALUES
('Electronics'),
('Books'),
('Home & Kitchen'),
('Clothing'),
('Sports & Outdoors');

INSERT INTO user (email, password, phone, roles, user_type, username) VALUES
('ali.benbrahim@example.com', '$2a$10$h68vEq0.0Y5Jlma3pgDx9.F9AFO4GWX.bvOxhCVEfaW09o.k7HIZy', 2122596, 'admin', 1, 'ilyas'),
('sara.elalami@example.com', 'password2', 2122596, 'ADMIN', 2, 'Sara Elalami'),
('omar.nassiri@example.com', 'password3', 2122596, 'USER', 1, 'Omar Nassiri'),
('fatima.bennani@example.com', 'password4', 2122596, 'MODERATOR', 3, 'Fatima Bennani'),
('hassan.mahfoud@example.com', 'password5', 2122596, 'USER', 1, 'Hassan Mahfoud');

INSERT INTO product (description, image_path, name, price, quantity_stock, category_id, seller_id) VALUES
('Smartphone Android avec écran OLED', NULL, 'Galaxy Z Nova', 7500, 25, 1, 1),
('Roman historique primé', NULL, 'Les Sables de Fès', 120, 40, 2, 2),
('Blender multifonction 1200W', NULL, 'BlendMaster Pro', 890, 15, 3, 3),
('Veste en cuir véritable', NULL, 'Cuir Royal', 1350, 10, 4, 4),
('Raquette de tennis professionnelle', NULL, 'SpinMax 3000', 680, 30, 5, 5);
