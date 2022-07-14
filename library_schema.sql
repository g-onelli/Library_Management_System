use library_schema;

INSERT INTO librarians(name,salary,position,email,phoneNumber,password)
values('Gabriela', 80000, 'Head Librarian', 'email@email.com', '5555555555','test'),
('Librarian1',  60000, 'Associate Librarian', 'email@email.com', '5555555555','test');

INSERT INTO patrons(name,cardExpirationDate,balance,password)
values('Gavin', '2022-12-31', 100.00,'test'),
('Jakob', '2023-12-31', 0.00,'test'),
('Ian', '2024-12-31', 20.00,'test');

INSERT INTO books(title,author,publisher,callNumber,genre)
values('booktitle1', 'author1', 'publisher1', 1, 'fiction'),
('booktitle2', 'author2', 'publisher1', 2, 'nonfiction'),
('booktitle1', 'author1', 'publisher1', 1, 'fiction');

INSERT INTO videos(title,director,releaseDate,callNumber,genre)
values('videotitle1', 'director1', '1970-01-20', 3, 'horror'),
('videotitle2', 'director2', '2000-06-01', 4, 'documentary'),
('videotitle3', 'director1', '2020-12-21', 5, 'comedy');

INSERT INTO rooms(capacity,hasPresenterTools)
values('10', '1'),
('5', '0'),
('20', '1');

INSERT INTO checkedoutbooks(books_id,patrons_id,dueDate)
values('4', '4','2000-06-01'),
('6', '6','2022-01-01');

INSERT INTO checkedoutrooms(patrons_id,rooms_roomNumber,dueDate)
values('5', '4','2022-08-01');

select * from checkedoutvideos;
INSERT INTO checkedoutvideos(patrons_id,videos_id,dueDate)
values('4', '6','2010-09-01');

INSERT INTO requests(description,submissionDate,title,patrons_id,author)
values('A good book','2021-05-20','Book300', 4,'author1'),
('An okay book','2022-03-20','Book200', 6,'author2');

INSERT INTO events(date,description,title,librarians_id)
values('2022-08-31','Book showcase','Book Fair', 3),
('2022-09-21','','Book Reading', 4);

INSERT INTO fees(total,datePaid,feeType,patrons_id)
values(100.00, '2021-12-31','Overdue', 4),
(2.00, '2021-12-31','Overdue', 6);

INSERT INTO librarianpatronconnection(librarians_id,patrons_id)
values(3, 4),
(3, 5),
(4, 6);