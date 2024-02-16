SELECT 1 FROM dual;
INSERT INTO category(name) VALUES ('Eurogames');
INSERT INTO category(name) VALUES ('Ameritrash');
INSERT INTO category(name) VALUES ('Familiar');

INSERT INTO author(name, nationality) VALUES ('Alan R. Moon', 'US');
INSERT INTO author(name, nationality) VALUES ('Vital Lacerda', 'PT');
INSERT INTO author(name, nationality) VALUES ('Simone Luciani', 'IT');
INSERT INTO author(name, nationality) VALUES ('Perepau Llistosella', 'ES');
INSERT INTO author(name, nationality) VALUES ('Michael Kiesling', 'DE');
INSERT INTO author(name, nationality) VALUES ('Phil Walker-Harding', 'US');

INSERT INTO game(title, age, category_id, author_id) VALUES ('On Mars', '14', 1, 2);
INSERT INTO game(title, age, category_id, author_id) VALUES ('Aventureros al tren', '8', 3, 1);
INSERT INTO game(title, age, category_id, author_id) VALUES ('1920: Wall Street', '12', 1, 4);
INSERT INTO game(title, age, category_id, author_id) VALUES ('Barrage', '14', 1, 3);
INSERT INTO game(title, age, category_id, author_id) VALUES ('Los viajes de Marco Polo', '12', 1, 3);
INSERT INTO game(title, age, category_id, author_id) VALUES ('Azul', '8', 3, 5);

INSERT INTO client(name) VALUES ('Alejandro');
INSERT INTO client(name) VALUES ('Mireia');
INSERT INTO client(name) VALUES ('Jordi');



INSERT INTO loan(game_id, client_id, init_date, fin_date) VALUES (1,1,'2024-02-13','2024-02-12');
INSERT INTO loan(game_id, client_id, init_date, fin_date) VALUES (2,2,'2023-03-13','2023-03-12');
INSERT INTO loan(game_id, client_id, init_date, fin_date) VALUES (2,3,'2023-04-13','2023-04-12');
INSERT INTO loan(game_id, client_id, init_date, fin_date) VALUES (3,3,'2023-05-13','2023-05-12');
INSERT INTO loan(game_id, client_id, init_date, fin_date) VALUES (1,2,'2023-06-13','2023-06-12');
INSERT INTO loan(game_id, client_id, init_date, fin_date) VALUES (2,1,'2023-07-13','2023-07-12');
INSERT INTO loan(game_id, client_id, init_date, fin_date) VALUES (1,3,'2023-08-13','2023-08-12');
