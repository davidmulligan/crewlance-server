INSERT INTO crewlance.user (id, first_name, last_name, email, telephone1, telephone2, created_by, created_on, modified_by, modified_on)
VALUES ('111111', 'Hayley', 'Mulligan', 'hayleymulligan@btopenworld.com', '01473 123456', '07795 123456', '1', now(), '1', now()),
       ('222222', 'Jack', 'Mulligan', 'jackmulligan@btopenworld.com', '01473 123456', '07795 123456','1', now(), '1', now()),
       ('333333', 'Kimberly', 'Mulligan', 'kimberlymulligan@btopenworld.com', '01473 123456', '07795 123456','1', now(), '1', now()),
       ('444444', 'Caitlin', 'Mulligan', 'caitlinmulligan@btopenworld.com', '01473 123456', '07795 123456','1', now(), '1', now()),
       ('555555', 'Connor', 'Youngman', 'connoryoungman@btopenworld.com', '01473 123456', '07795 123456','1', now(), '1', now());

INSERT INTO crewlance.project (id, title, description, location, start, end, status, created_by, created_on, modified_by, modified_on)
VALUES ('111111', 'Project1', 'Project One', 'London', now(), now(), 'NEW', '1', now(), '1', now()),
       ('222222', 'Project2', 'Project Two', 'Birmingham', now(), now(), 'NEW', '1', now(), '1', now()),
       ('333333', 'Project3', 'Project Three', 'Cardiff', now(), now(), 'NEW', '1', now(), '1', now()),
       ('444444', 'Project4', 'Project Four', 'Ipswich', now(), now(), 'NEW', '1', now(), '1', now()),
       ('555555', 'Project5', 'Project Five', 'Colchester', now(), now(), 'NEW', '1', now(), '1', now());

INSERT INTO crewlance.project_keyword (id, project_id, value, notes, auto_generated, created_by, created_on, modified_by, modified_on)
VALUES ('111111', '111111', 'Madonna', 'Notes', 0, '1', now(), '1', now());

INSERT INTO crewlance.availability (id, user_id, start, end, notes, created_by, created_on, modified_by, modified_on)
VALUES ('111111', '111111', now(), now(), 'Notes', '1', now(), '1', now());

INSERT INTO crewlance.preference (id, user_id, type, notes, created_by, created_on, modified_by, modified_on)
VALUES ('111111', '111111', 'KEYWORD', 'Notes', '1', now(), '1', now()),
       ('222222', '555555', 'WORK_WITH', 'Notes', '1', now(), '1', now()),
       ('333333', '555555', 'KEYWORD', 'Notes', '1', now(), '1', now());

INSERT INTO crewlance.preference_keyword (id, preference_id, value, notes, created_by, created_on, modified_by, modified_on)
VALUES ('111111', '111111', 'Madonna', 'Notes', '1', now(), '1', now()),
       ('333333', '333333', 'Madonna', 'Notes', '1', now(), '1', now());

INSERT INTO crewlance.preference_user (id, preference_id, user_id, notes, created_by, created_on, modified_by, modified_on)
VALUES ('111111', '222222', '111111', 'Notes', '1', now(), '1', now());