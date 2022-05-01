Create table Users (
	id Serial primary key,
	name varchar(256) NOT NULL
);

Create table Boards (
	id Serial primary key,
	name varchar(256) NOT NULL
);

Create table UserRoles (
	id Serial primary key,
	name varchar(256) NOT NULL
);

Create table UserWorksWithBoard (
	userId int references Users(id),
	boardId int references Boards(id),
	roleId int references UserRoles(id)
);

Create table BoardColumns (
	id Serial primary key,
	name text NOT NULL,
	boardId int references Boards(id)
);

Create table BoardCards (
	id Serial primary key,
	name text NOT NULL,
	description text,
	creatorId int references Users(id),
	columnId int references BoardColumns(id)
);

Create table UserWorksWithCard (
	cardId int references BoardCards(id),
	userId int references Users(id)
);

insert into Users(name) values('Ivan Ivanov');

insert into Boards(name) values('Ivanov board');

insert into UserRoles(name) values('Creator');

insert into UserWorksWithBoard(userId, boardId, roleId) values (1,1,1);

insert into BoardColumns(name, boardId) values ('To do', 1);
insert into BoardColumns(name, boardId) values ('In progress', 1);
insert into BoardColumns(name, boardId) values ('Done', 1);

insert into BoardCards(name, description, creatorId, columnId) values ('Покормить кота', 'Необходимо покормить кота', 1, 1);
insert into BoardCards(name, description, creatorId, columnId) values ('Покормить собаку', NULL, 1, 1);
insert into BoardCards(name, description, creatorId, columnId) values ('Выбросить мусор', NULL, 1, 3);

insert into UserWorksWithCard(cardId, userId) values(1,1);
insert into UserWorksWithCard(cardId, userId) values(2,1);
insert into UserWorksWithCard(cardId, userId) values(3,1);