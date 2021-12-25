--
-- PostgreSQL port of the MySQL "World" database.
--
-- The sample data used in the world database is Copyright Statistics 
-- Finland, http://www.stat.fi/worldinfigures.
--

BEGIN;

SET client_encoding = 'LATIN1';

CREATE TABLE products {
	id INT PRIMARY KEY NOT NULL,
	name TEXT NOT NULL,
	category TEXT NOT NULL,
	price decimal(8, 2),
        specifications TEXT NOT NULL

};

CREATE TABLE inventory {
	productid INT NOT NULL,
	qunatity integer NOT NULL
};

COPY products(id, name, category, price , specifications) FROM stdin;
1	Music System	Electronics	100.00	Bose Audio
2	Lap Top High	Electronics	900.00	Dell 
3	Shoes	Consumers	51.25	Nike
4	Shirts	Apparels	12.25	Kohls	
5	Pants	Apperels	55.33	Macys
\.

COPY inventory(productid, qunatity) FROM stdin;
1	90
2	199
3	222
4	111
5	323
\.



COMMIT;

ANALYZE products;
ANALYZE inventory;
