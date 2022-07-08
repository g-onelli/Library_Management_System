use library_schema;

select * from books;

INSERT INTO books(title,author,publisher,callNumber,genre)
values('booktitle1', 'author1', 'publisher1', 1, 'fiction'),
('booktitle2', 'author2', 'publisher1', 2, 'nonfiction'),
('booktitle1', 'author1', 'publisher1', 1, 'fiction');

select * from videos;
INSERT INTO videos(title,director,releaseDate,callNumber,genre)
values('videotitle1', 'director1', '1970-01-20', 3, 'horror'),
('videotitle2', 'director2', '2000-06-01', 4, 'documentary'),
('videotitle3', 'director1', '2020-12-21', 5, 'comedy');

select * from rooms;
INSERT INTO rooms(capacity,hasPresenterTools)
values('10', '1'),
('5', '0'),
('20', '1');

select * from checkedoutbooks;
INSERT INTO checkedoutbooks(books_id,patrons_id,dueDate)
values('1', '1','2000-06-01'),
('3', '3','2022-01-01');

select * from checkedoutrooms;
INSERT INTO checkedoutrooms(patrons_id,rooms_roomNumber,dueDate)
values('2', '2','2022-08-01');

select * from checkoutvideos;
INSERT INTO checkoutvideos(patrons_id,videos_id,dueDate)
values('1', '3','2010-09-01');

select * from requests;
INSERT INTO requests(description,submissionDate,title,patrons_id)
values('A good book','2021-05-20','Book300', 1),
('An okay book','2022-03-20','Book200', 3);

select * from events;
INSERT INTO events(date,description,title,librarians_id)
values('2022-08-31','Book showcase','Book Fair', 1),
('2022-09-21','','Book Reading', 2);

select * from fees;
INSERT INTO fees(total,datePaid,feeType,patrons_id)
values(100.00, '2021-12-31','Overdue', 1),
(2.00, '2021-12-31','Overdue', 3);


select * from librarians;
INSERT INTO librarians(name,salary,position,email,phoneNumber)
values('Gabriela', 80000, 'Head Librarian', 'email@email.com', '5555555555','test'),
('Librarian1',  60000, 'Associate Librarian', 'email@email.com', '5555555555','test');


ALTER TABLE librarians
ADD COLUMN password varchar(255) not NULL;

ALTER TABLE patrons
ADD COLUMN password varchar(255) not NULL;

update patrons
set password = 'test';

select * from patrons;

INSERT INTO patrons(name,cardExpirationDate,balance)
values('Gavin', '2022-12-31', 100.00),
('Jakob', '2023-12-31', 0.00),
('Ian', '2024-12-31', 20.00);

select * from librarianpatronconnection;

INSERT INTO librarianpatronconnection(librarians_id,patrons_id)
values(1, 1),
(1, 2),
(2, 3);
