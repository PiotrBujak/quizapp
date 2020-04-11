/*ROLES*/
insert into role (role_id, role) values (1, 'ADMIN');
insert into role (role_id, role) values (2, 'USER');

/*USERS*/
insert into users (id, active, name, password) values (1, 1, 'ADMIN', '$2a$10$vz.EgfxtT8Bb/v/dy/rnyOMtl1O3t/caIZVAat1JapXZFIgiHSuYS');
insert into users (id, active, name, password) values (2, 1, 'USER', '$2a$10$FgANR8aoABnSbJ82uxcIQOWd3U/Bh3BZy8SxxGSoTBVTHrO3SbowW');

/*ADD ROLE TO USER*/
insert into user_role (user_id, role_id) values (1, 1);
insert into user_role (user_id, role_id) values (1, 2);
insert into user_role (user_id, role_id) values (2, 2);

/*ANSWERS*/
insert into answers (id, content, correct, question_id) values (1, 'odpowiedź1', true, 1);
insert into answers (id, content, correct, question_id) values (2, 'odpowiedź2', false, 1);
insert into answers (id, content, correct, question_id) values (3, 'odpowiedź3', true, 2);
insert into answers (id, content, correct, question_id) values (4, 'odpowiedź4', false, 2);
insert into answers (id, content, correct, question_id) values (5, 'odpowiedź5', true, 3);
insert into answers (id, content, correct, question_id) values (6, 'odpowiedź6', false, 4);
insert into answers (id, content, correct, question_id) values (7, 'odpowiedź7', true, 4);
insert into answers (id, content, correct, question_id) values (8, 'odpowiedź8', false, 4);

/*QUESTIONS*/
insert into questions (id, content, test_id) values (1, "pytanie1", 1);
insert into questions (id, content, test_id) values (2, "pytanie2", 1);
insert into questions (id, content, test_id) values (3, "pytanie3", 2);
insert into questions (id, content, test_id) values (4, "pytanie4", 2);

/*TESTS*/
insert into tests (id, content, user_id) values (1, "test1", 1);
insert into tests (id, content, user_id) values (2, "test2", 2);