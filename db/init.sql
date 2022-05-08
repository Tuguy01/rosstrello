Create table Users (
	id Serial primary key,
	email varchar(256) NOT NULL,
	name varchar(256) NOT NULL
);

Create table Boards (
	id Serial primary key,
	name varchar(256) NOT NULL
);

Create table User_Roles (
	id Serial primary key,
	name varchar(256) NOT NULL
);

Create table User_Works_With_Board (
	user_id int references Users(id) ON DELETE CASCADE ON UPDATE CASCADE,
	board_id int references Boards(id) ON DELETE CASCADE ON UPDATE CASCADE,
	role_id int references User_Roles(id) ON DELETE CASCADE ON UPDATE CASCADE
);

Create table Board_Columns (
	id Serial primary key,
	name text NOT NULL,
	board_id int references Boards(id) ON DELETE CASCADE ON UPDATE CASCADE
);

Create table Board_Cards (
	id Serial primary key,
	name text NOT NULL,
	description text,
	creator_id int references Users(id) ON DELETE CASCADE ON UPDATE CASCADE,
	column_id int references Board_Columns(id) ON DELETE CASCADE ON UPDATE CASCADE
);

Create table User_Works_With_Card (
	card_id int references Board_Cards(id) ON DELETE CASCADE ON UPDATE CASCADE,
	user_id int references Users(id) ON DELETE CASCADE ON UPDATE CASCADE
);

insert into Users(email, name) values('ivanov@example.com', 'Ivan Ivanov');

insert into Boards(name) values('Ivanov board');

insert into User_Roles(name) values('Creator');

insert into User_Works_With_Board(user_id, board_id, role_id) values (1,1,1);

insert into Board_Columns(name, board_id) values ('To do', 1);
insert into Board_Columns(name, board_id) values ('In progress', 1);
insert into Board_Columns(name, board_id) values ('Done', 1);

insert into Board_Cards(name, description, creator_id, column_id) values ('Покормить кота', 'Необходимо покормить кота', 1, 1);
insert into Board_Cards(name, description, creator_id, column_id) values ('Покормить собаку', NULL, 1, 1);
insert into Board_Cards(name, description, creator_id, column_id) values ('Выбросить мусор', NULL, 1, 3);

insert into User_Works_With_Card(card_id, user_id) values(1,1);
insert into User_Works_With_Card(card_id, user_id) values(2,1);
insert into User_Works_With_Card(card_id, user_id) values(3,1);