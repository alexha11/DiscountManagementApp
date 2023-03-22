CREATE TABLE CUSTOMER (
	C_CODE 	SERIAL NOT NULL, 
	C_USER_NAME	VARCHAR(35) NOT NULL UNIQUE,
    C_PASSWORD VARCHAR(30) NOT NULL,
	C_AGE	INTEGER,
	PRIMARY KEY (C_CODE)
);

CREATE TABLE DISCOUNT (
    D_CODE      SERIAL CONSTRAINT PRODUCT_D_CODE_PK PRIMARY KEY,
    D_PRODUCT   VARCHAR(30) NOT NULL UNIQUE,
    D_PASSWORD  CHAR(16) NOT NULL
    );

CREATE TABLE CATEGORY (
    D_PRODUCT VARCHAR(30) NOT NULL,
    D_CATEGORY VARCHAR(30) NOT NULL,
    PRIMARY KEY (D_PRODUCT),
    FOREIGN KEY (D_PRODUCT) REFERENCES DISCOUNT(D_PRODUCT) ON DELETE CASCADE
);

CREATE TABLE PRICE (
    D_CODE      SERIAL NOT NULL,
    P_CODE      SERIAL NOT NULL,
    P_DISCOUNT  NUMERIC(4,2) NOT NULL, 
    P_INPRICE   NUMERIC(10, 2),
    PRIMARY KEY (P_CODE),
    FOREIGN KEY (D_CODE) REFERENCES DISCOUNT(D_CODE) ON DELETE CASCADE
);

CREATE TABLE SALE_PERIOD (
    D_CODE      SERIAL NOT NULL,
    S_CODE      SERIAL NOT NULL,
    S_STDATE    DATE,
    S_ENDDATE   DATE, 
    PRIMARY KEY (S_CODE),
    FOREIGN KEY (D_CODE) REFERENCES DISCOUNT(D_CODE) ON DELETE CASCADE
);

CREATE TABLE STORE (
    D_CODE      SERIAL NOT NULL,
    ST_CODE     SERIAL NOT NULL,
    ST_NAME     VARCHAR(30),
    ST_PHONE    CHAR(8),
    ST_STREET   VARCHAR(20),
    ST_CITY     VARCHAR(20) NOT NULL,
    PRIMARY KEY (ST_CODE),
    FOREIGN KEY (D_CODE) REFERENCES DISCOUNT(D_CODE) ON DELETE CASCADE
);

CREATE INDEX CityIndex ON STORE(ST_CITY);

INSERT INTO CUSTOMER VALUES 
    (1001, 'Alex123', '123', 35),
    (1002, 'DuongHa123', '111', 78),
    (1003, 'thanhduong11', 'haduong11', 34),
    (1004, 'thanhduong114', 'asdfghjkl', 46),
    (1005, 'hanhi123', 'ddd', 21),
    (1006, 'mia123', 'abc123', 42),
    (1007, 'mia31', 'hacker123', 34);

INSERT INTO DISCOUNT VALUES
    (101, 'TVs', 'Dc^5Ud]!+v6&4pzw'),
    (102, 'Refrigerators', 'JwSYddEP(ce;8%4M'),
    (103, 'Nazar Dream Holiday', 'xEpNZ8#\)@tu9(+e'),
    (104, 'Hambuger', 'wkhb<a@[x4mtAK^M'),
    (105, 'Gym Stuffs', 'uAFT5\xXT2e`N/!c'),
    (106, 'Jordan', 'GzQD;5r#`}"8-Xst'),
    (107, 'T-shirt', '+j$(5EQGSwn`r[e:'),
    (108, 'Chicken', 'zE~fAuQB=yw2,pD+'),
    (109, 'NetFlix Account', '99rx4[^,CdsU[?F}'),
    (110, 'Banana', '#!}bD@5a#B5~c%-_'),
    (111, 'Milk Tea', 'Bv8z4Vg7{%aZ2D!H'),
    (112, 'Meat', 'wenj2*_UhXu,:%Q&'),
    (113, 'Laptop', '_ur/RH/f;5`Wb;8V'),
    (114, 'Viet Nam Holiday', '$Tq;G3KD~a=UYJdy'),
    (115, 'Whey', 'N-4e-^Q9C2v$e}EQ'),
    (116, 'Iphone', '&3b!{2*gD_Jz,<dj'),
    (117, 'Knife', '8$[XzZ$J8-5J~y;G'),
    (118, 'Cooker', '7L;cE88w5,f]XjdY'),
    (119, 'Fish', 'bccP\"+8MTvFdxA2');

INSERT INTO CATEGORY VALUES
    ('TVs', 'Electronics'),
    ('Refrigerators', 'Electronics'),
    ('Nazar Dream Holiday', 'Travelling & Transportation'),
    ('Hambuger', 'Food'),
    ('Gym Stuffs', 'Sports & Training'),
    ('Jordan', 'Clothes'),
    ('T-shirt', 'Clothes'),
    ('Chicken', 'Food'),
    ('NetFlix Account', 'Leisure'),
    ('Banana', 'Grocery'),
    ('Milk Tea', 'Food'),
    ('Meat', 'Grocery'),
    ('Laptop', 'Electronics'),
    ('Viet Nam Holiday', 'Travelling & Transportation'),
    ('Whey', 'Sports & Training'),
    ('Iphone', 'Electronics'),
    ('Knife', 'Home & Family'),
    ('Cooker', 'Home & Family'),
    ('Fish', 'Grocery');

INSERT INTO PRICE VALUES
    (101, 1001, 30.00, 500.00),
    (102, 1002, 12.00, 1200.00),
    (103, 1003, 45.00, 200.00),
    (104, 1004, 10.00, 50.00),
    (105, 1005, 3.00, 25.00),
    (106, 1006, 12.00, 1000.00),
    (107, 1007, 6.00, 750.00),
    (108, 1008, 9.00, 850.00),
    (109, 1009, 23.00, 350.00),
    (110, 1010, 45.00, 400.00),
    (111, 1011, 54.00, 440.00),
    (112, 1012, 69.00, 230.00),
    (113, 1013, 23.00, 4230.00),
    (114, 1014, 40.00, 4334.00),
    (115, 1015, 50.00, 32.00),
    (116, 1016, 50.00, 70.00),
    (117, 1017, 80.00, 300.00),
    (118, 1018, 60.00, 230.00),
    (119, 1019, 90.00, 4230.00);

INSERT INTO SALE_PERIOD VALUES
    (101, 10001, '2023-01-23', '2023-05-15'),   
    (102, 10002, '2023-02-25', '2023-03-28'),
    (103, 10003, '2023-03-01', '2023-03-31'),
    (104, 10004, '2023-03-07', '2023-04-30'),
    (105, 10005, '2023-03-06', '2023-05-31'),
    (106, 10006, '2023-03-01', '2023-06-30'),
    (107, 10007, '2023-03-01', '2023-03-31'),
    (108, 10008, '2023-03-01', '2023-04-20'),
    (109, 10009, '2023-03-01', '2023-09-30'),
    (110, 10010, '2023-03-01', '2023-04-10'),
    (111, 10011, '2023-03-01', '2023-11-30'),
    (112, 10012, '2023-03-01', '2023-12-31'),
    (113, 10013, '2023-02-01', '2023-01-31'),
    (114, 10014, '2023-02-01', '2023-05-29'),
    (115, 10015, '2023-02-01', '2023-03-31'),
    (116, 10016, '2022-09-01', '2023-04-30'),
    (117, 10017, '2022-05-21', '2023-05-31'),
    (118, 10018, '2022-06-11', '2023-06-30'),
    (119, 10019, '2022-07-02', '2023-07-31');
    

INSERT INTO STORE VALUES
    (101, 1001, 'Best Buy', '12345678', '123 Main St', 'Helsinki'),
    (102, 1002, 'Home Depot', '87654321', '456 Elm St', 'Espoo'),
    (103, 1003, 'Safeway', '24681012', '789 Oak St', 'Espoo'),
    (104, 1004, 'Macy''s', '10101010', '321 Maple St', 'Lahti'),
    (105, 1005, 'Toys R Us', '12121212', '555 Pine St', 'Tempere'),
    (106, 1006, 'Target', '45454545', '999 Pine St', 'Seinajoki'),
    (107, 1009, 'Walmart', '78787878', '888 Oak St', 'Lahti'),
    (108, 1010, 'Walmart', '78787878', '888 Oak St', 'Lahti'),
    (109, 1011, 'Walgreens', '34343434', '666 Elm St', 'Lahti'),
    (110, 1012, 'CVS Pharmacy', '12121212', '555 Walnut St', 'Lahti'),
    (111, 1013, 'Costco', '65656565', '444 Cedar St', 'Helsinki'),
    (112, 1014, 'Staples', '22222222', '333 Oak St', 'Helsinki'),
    (113, 1015, 'Trader Joe''s', '98989898', '222 Pine St', 'Seinajoki'),
    (114, 1016, 'Whole Foods', '25252525', '111 Maple St', 'Tempere'),
    (115, 1017, 'Michaels', '73737373', '999 Walnut St', 'Tempere'),
    (116, 1018, 'Bed Bath & Beyond', '89898989', '888 Cedar St', 'Espoo'),
    (117, 1019, 'Dollar Tree', '48484848', '777 Oak St', 'Espoo'),
    (118, 1020, 'Office Depot', '63636363', '666 Maple St', 'Tempere'),
    (119, 1021, 'PetSmart', '38383838', '555 Elm St', 'Lahti');