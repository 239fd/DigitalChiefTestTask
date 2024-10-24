CREATE TABLE IF NOT EXISTS product (
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    description TEXT,
    price DECIMAL(10, 2),
    active BOOLEAN DEFAULT true,
    start_date DATE DEFAULT CURRENT_DATE
);

CREATE TABLE IF NOT EXISTS sku (
    id SERIAL PRIMARY KEY,
    product_id INT REFERENCES product(id) ON DELETE CASCADE,
    sku_code TEXT NOT NULL,
    quantity INT,
    color TEXT,
    available BOOLEAN DEFAULT true,
    weight DECIMAL(10, 2)
);

INSERT INTO product (name, description, price, active, start_date) VALUES
                                                                       ('Monitor', 'HD monitor 1920x1080', 150.00, true, '2024-01-01'),
                                                                       ('Laptop', 'Gaming laptop with 16GB RAM', 1200.00, true, '2024-01-02'),
                                                                       ('Keyboard', 'Mechanical keyboard with RGB lighting', 100.00, true, '2024-01-03'),
                                                                       ('Mouse', 'Wireless optical mouse', 25.99, true, '2024-01-04'),
                                                                       ('Headphones', 'Noise-cancelling over-ear headphones', 80.00, true, '2024-01-05'),
                                                                       ('Tablet', '10-inch tablet with 64GB storage', 300.00, true, '2024-01-06'),
                                                                       ('Smartphone', 'Latest smartphone with 128GB storage', 999.99, true, '2024-01-07'),
                                                                       ('Printer', 'Wireless printer with scanning capabilities', 200.00, true, '2024-01-08'),
                                                                       ('Camera', 'DSLR camera with 24MP', 800.00, true, '2024-01-09'),
                                                                       ('Smartwatch', 'Fitness smartwatch with heart rate monitor', 199.99, true, '2024-01-10'),
                                                                       ('Speakers', 'Bluetooth speakers with deep bass', 150.00, true, '2024-01-11'),
                                                                       ('Router', 'High-speed router for gaming and streaming', 120.00, true, '2024-01-12'),
                                                                       ('External HDD', '2TB external hard drive', 75.00, true, '2024-01-13'),
                                                                       ('USB Flash Drive', '64GB USB flash drive', 15.00, true, '2024-01-14'),
                                                                       ('Monitor Stand', 'Ergonomic monitor stand', 35.00, true, '2024-01-15'),
                                                                       ('Webcam', '1080p HD webcam for streaming', 60.00, true, '2024-01-16'),
                                                                       ('Charger', 'Fast charger for smartphones', 20.00, true, '2024-01-17'),
                                                                       ('Power Bank', 'Portable power bank with 20000mAh', 30.00, true, '2024-01-18'),
                                                                       ('Laptop Bag', 'Water-resistant laptop bag', 45.00, true, '2024-01-19'),
                                                                       ('Docking Station', 'USB-C docking station for laptops', 70.00, true, '2024-01-20');

INSERT INTO sku (product_id, sku_code, quantity, color, available, weight) VALUES
                                                                               (1, 'SKU001', 100, 'Red', true, 1.5),
                                                                               (1, 'SKU002', 50, 'Blue', true, 1.3),
                                                                               (1, 'SKU003', 20, 'Green', false, 1.8),
                                                                               (2, 'SKU004', 75, 'Black', true, 1.1),
                                                                               (2, 'SKU005', 60, 'White', true, 1.2),
                                                                               (2, 'SKU006', 30, 'Yellow', true, 1.4),
                                                                               (3, 'SKU007', 100, 'Red', true, 1.6),
                                                                               (3, 'SKU008', 80, 'Blue', true, 1.0),
                                                                               (3, 'SKU009', 40, 'Green', false, 1.9),
                                                                               (4, 'SKU010', 50, 'Black', true, 1.7),
                                                                               (4, 'SKU011', 20, 'White', true, 2.0),
                                                                               (5, 'SKU012', 90, 'Red', true, 1.5),
                                                                               (5, 'SKU013', 60, 'Blue', true, 1.3),
                                                                               (5, 'SKU014', 30, 'Green', false, 1.8),
                                                                               (6, 'SKU015', 75, 'Black', true, 1.1),
                                                                               (7, 'SKU016', 50, 'White', true, 1.2),
                                                                               (8, 'SKU017', 100, 'Red', true, 1.6),
                                                                               (9, 'SKU018', 80, 'Blue', true, 1.0),
                                                                               (10, 'SKU019', 40, 'Green', false, 1.9),
                                                                               (11, 'SKU020', 50, 'Black', true, 1.7),
                                                                               (12, 'SKU021', 20, 'White', true, 2.0),
                                                                               (13, 'SKU022', 90, 'Red', true, 1.5),
                                                                               (14, 'SKU023', 60, 'Blue', true, 1.3),
                                                                               (15, 'SKU024', 30, 'Green', false, 1.8),
                                                                               (16, 'SKU025', 75, 'Black', true, 1.1),
                                                                               (17, 'SKU026', 50, 'White', true, 1.2),
                                                                               (18, 'SKU027', 100, 'Red', true, 1.6),
                                                                               (19, 'SKU028', 80, 'Blue', true, 1.0),
                                                                               (20, 'SKU029', 40, 'Green', false, 1.9),
                                                                               (20, 'SKU030', 60, 'Black', true, 1.4),
                                                                               (20, 'SKU031', 20, 'White', false, 1.8),
                                                                               (20, 'SKU032', 25, 'Yellow', true, 1.5),
                                                                               (20, 'SKU033', 15, 'Purple', true, 2.0),
                                                                               (20, 'SKU034', 10, 'Pink', false, 1.6),
                                                                               (20, 'SKU035', 12, 'Orange', true, 1.3),
                                                                               (20, 'SKU036', 8, 'Gray', true, 1.9),
                                                                               (20, 'SKU037', 7, 'Brown', false, 1.4),
                                                                               (20, 'SKU038', 5, 'Teal', true, 1.1),
                                                                               (20, 'SKU039', 4, 'Maroon', false, 1.7),
                                                                               (20, 'SKU040', 3, 'Navy', true, 2.1),
                                                                               (20, 'SKU041', 2, 'Olive', false, 1.8),
                                                                               (20, 'SKU042', 1, 'Coral', true, 1.0),
                                                                               (20, 'SKU043', 0, 'Lavender', false, 1.2),
                                                                               (20, 'SKU044', 3, 'Gold', true, 1.5),
                                                                               (20, 'SKU045', 2, 'Silver', false, 1.3),
                                                                               (20, 'SKU046', 1, 'Copper', true, 1.4),
                                                                               (20, 'SKU047', 0, 'Charcoal', false, 1.6),
                                                                               (20, 'SKU048', 3, 'Crimson', true, 1.5),
                                                                               (20, 'SKU049', 2, 'Cyan', false, 1.3),
                                                                               (20, 'SKU050', 1, 'Magenta', true, 1.4);
