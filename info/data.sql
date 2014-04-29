INSERT INTO Groups VALUES
(1, 'Test users'),
(2, 'Inserted by unit test'),
(10, 'Group of dispatchers'),
(11, 'Group of clients');

INSERT INTO Users VALUES
('alex', 'alex', 11),
('client_root', 'root', 11),
('dispatcher_root', 'root', 10),
('root', 'root', 1),
('vla', 'vla', 10);

INSERT INTO Person VALUES
('AA111111','Яковенко','Александр','Николаевич','alex'),
('ZZ999999','Фамилия Клиента','Имя Клиента','Отчество Клиента','root');

INSERT INTO Flat VALUES 
(99, 'Адресс', 0, 1),
(256, 'Тестовый Адресс', 0,0),
(257, 'Тестовый Адресс', 0,0),
(258, 'Наб.-Корчеватская', 84,6);

INSERT INTO Owner VALUES 
((SELECT Person_ID FROM Person Where Person_ID='ZZ999999'),(SELECT Flat_ID FROM Flat Where Flat_ID=99)),
((SELECT Person_ID FROM Person Where Person_ID='AA111111'),(SELECT Flat_ID FROM Flat Where Flat_ID=258)); 

INSERT INTO Worker VALUES
(9999,'Имя','Специализация'),
(NULL,'Сергеев Д.В.','Сантехник'),
(NULL,'Михайлов В.А.','Сантехник'),
(NULL,'Соловьев И.Д.','Столяр'),
(NULL,'Воробьев Д.Л.','Столяр'),
(NULL,'Авдеенко Е.Ю.','Маляр'),
(NULL,'Лебедев В.В.','Грузчик'),
(NULL,'Сергеева Л.Н.','Уборщица'),
(NULL,'Федорова Елена Васильевна','Маляр'),
(NULL,'Авдеенко Людмила Валериевна','Штукатур'),
(NULL,'Бондаренко Игорь Викторович','Сварщик'),
(NULL,'Попова Ольга Дмитриевна', 'Штукатур'),
(NULL,'Федоров Александр Васильевич','Грузчик'),
(NULL,'Семенова Ольга Валериевна','Штукатур'),
(NULL,'Богданов Виктор Никанорович','Сварщик'),
(NULL,'Федорова Любовь Денисовна','Уборщица'),
(NULL,'Богданов Денис Николаевич','Сантехник'),
(NULL,'Кудрявцев Виктор Юрьевич','Столяр'),
(NULL,'Ильин Игорь Валериевич','Разнорабочий'),
(NULL,'Богданов Виктор Денисович','Разнорабочий'),
(NULL,'Веприк Василий Николаевич','Разнорабочий');

INSERT INTO Works VALUES
(10000, 'Ремонт трубы канализации','Устранение подтекания'),
(10001, 'Ремонт трубы Х водоснабж.','Устранение подтекания'),
(10002, 'Ремонт трубы Г водоснабж.','Устранение подтекания'),
(10003, 'Замена трубы канализации','Замена трубы подразумевает сварные работы'),
(10004, 'Замена трубы Х водоснабж.','Замена трубы подразумевает сварные работы'),
(10005, 'Замена трубы Г водоснабж.','Замена трубы подразумевает сварные работы'),
(10006, 'Ремонтные работы(стены)','Штукатурка, покраска, поклека обоев'),
(10007, 'Замена окон/дверей','Без учета ремонтный работ'),
(10008, 'Замена окон/дверей','С учетом ремонтный работ'),
(10009, 'Уборка помещения','Уборка помещения после ремонта'),
(10010, 'Уборка помещения','уборка помещения');

INSERT INTO Dispatcher (Dispatcher_ID, Name, Login) VALUES
(0,'expected' , null);
INSERT INTO Dispatcher (Dispatcher_ID, Name, Login) VALUES
(10000,'Тест' , (Select Login From Users where Login='dispatcher_root'));
INSERT INTO Dispatcher VALUES
(10001,'Воробьева Л.А.',(Select Login From Users where Login='vla'));
INSERT INTO Dispatcher VALUES
(10002,'Веремей Н.В.',null),
(10003,'Бабенко Е.Д.',null),
(10004,'Семенова Ю.В.',null);
UPDATE Dispatcher SET Dispatcher_ID=0 WHERE Name='expected';