use casinoroyale;

select * from user;
insert into user values
(0, 'Lucas', 'Viana', 'lsviana', 'lv201122'),
(0, 'Rafael', 'Thayto', 'rafa-thayto', 'senha123'),
(0, 'João', 'Olsen', 'olsen', 'jolsen132');

select * from store;
insert into store values
(0, 'Thaytolicia', 'R. das Programadoras, 666'),
(0, 'Dex Racer', 'R. Niterói, 280');

select * from brand;
insert into brand values
(0, 'Softmicro', 'https://d144mzi0q5mijx.cloudfront.net/img/S/O/SoftMicro.png'),
(0, 'Fail', 'http://1.bp.blogspot.com/-S6grgUfyuz0/VHSQPwSKuCI/AAAAAAAAAEA/ESP4XE9yXj0/s1600/00005257.jpg');

select * from product;
insert into product values
(0, 1, 49.99, 'Office Mouse', 'Black and Wireless', 'https://media.flixcar.com/f360cdn/Microsoft-1325391278-WM1000_ATop_FY11_350x350.png'),
(0, 1, 1899.99, 'Microsoft Surface', 'Blue and TouchPen', 'https://i5.walmartimages.com/asr/4d73edfb-cfac-4afd-bd9e-9cf1870194d6_1.00c754dbaa4efd60e4e65bb51f6bbd4a.jpeg?odnHeight=450&odnWidth=450&odnBg=FFFFFF'),
(0, 2, 4879.99, 'Ford Fiesta', 'Blue and Custom Wheels', 'http://st.motortrend.com/uploads/sites/10/2016/11/2017-Ford-Fiesta-front-three-quarter-01.jpg');

select * from sale;
insert into sale values
(0, 1, now(), 1),
(0, 2, now(), 1);

select * from saleitem;
insert into saleitem values
(0, 1, 1, (select price from product where id = 1)),
(0, 1, 2, (select price from product where id = 2)),
(0, 2, 3, (select price from product where id = 3));