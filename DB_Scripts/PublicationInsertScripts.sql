SET IDENTITY_INSERT publication off 
GO
INSERT INTO PUBLICATION( TITLE ,
  AUTHOR,
  PUBLISH_DATE,
  PUBLICATION_TYPE,
  DESCRIPTION,
  PUB_URL,
  PRICE) VALUES ('Paper.pdf','Nivedita','2012-12-12 00:00:00','paper','UK Investment Fund','http://localhost:8080/gtn/pdf/Paper.pdf',500.00
);
INSERT INTO PUBLICATION VALUES ('Book.pdf','Nivedita','2012-12-12 00:00:00','Book','My Book','http://localhost:8080/gtn/pdf/Book.pdf',400.00
);


SET IDENTITY_INSERT publication off 
GO
INSERT INTO PUBLICATION VALUES ('Canterbury Tales','Geoffrey Chaucer','2012-12-12 00:00:00','Book','Pilgrimages were a major part of medieval life and you can enjoy several different perspectives as Chaucer characters travel to Canterbury Cathedral','http://localhost:8080/gtn/pdf/Book.pdf',150.00
, '', '');
INSERT INTO PUBLICATION VALUES ('Madame Bovary','Gustave Flaubert','2012-12-12 00:00:00','Book','French bourgeois life in all of its soul-crushing triviality is explored through the character of Emma Bovary in this novel.','http://localhost:8080/gtn/pdf/Book.pdf',2500.00
, '', '');
INSERT INTO PUBLICATION VALUES ('20,000 Leagues Under the Sea','Jules Verne','2012-12-12 00:00:00','Book','This sci-fi novel is full of adventure as a group of sailors try to track down a deadly sea monster.','http://localhost:8080/gtn/pdf/Book.pdf',400.00
, '', '');
INSERT INTO PUBLICATION VALUES ('Pride and Prejudice','Jane Austen','2012-12-12 00:00:00','Book','This story explores the many difficulties associated with marriage and morality in 18th century England.','http://localhost:8080/gtn/pdf/Book.pdf',1400.00
, '', '');
INSERT INTO PUBLICATION VALUES ('Jane Eyre','Charlotte Bronte','2012-12-12 00:00:00','Book','Jane Eyre follows Jane, an orphan, throughout her life providing readers with a compelling story full of love, social criticisms and many elements characteristic of the Gothic novel.','http://localhost:8080/gtn/pdf/Book.pdf',300.00
, '', '');
INSERT INTO PUBLICATION VALUES ('A Tale of Two Cities','Charles Dickens','2012-12-12 00:00:00','Book','Get a more personalized tale of life during the French Revolution as the monarchy is replaced by a regime responsible for terror and numerous executions.','http://localhost:8080/gtn/pdf/Book.pdf',60.00
, '', '');
INSERT INTO PUBLICATION VALUES ('War and Peace','Leo Tolstoy','2012-12-12 00:00:00','Book',' Many know little of this novel other than that its long, but the story takes readers through the impact of the Napoleonic invasion of Russia through a vast and varied cast of characters.','http://localhost:8080/gtn/pdf/Book.pdf',700.00
, '', '');
INSERT INTO PUBLICATION VALUES ('The Count of Monte','Alexandre Dumas','2012-12-12 00:00:00','Book','Treachery and lost love form the basis for this novel by Three Musketeers author Dumas.Heart of Darkness by Joseph Conrad: This novella takes readers to the depths of the Congo to find the mysterious Kurtz, along the way exploring ideas of imperialism.','http://localhost:8080/gtn/pdf/Book.pdf',900.00
, '', '');
INSERT INTO PUBLICATION VALUES ('Ulysses','James Joyce','2012-12-12 00:00:00','Book','The classical Modernist novel, Ulysses follows the story of the Odyssey through stream of consciousness writing that’s been called everything from brilliant to obscene.','http://localhost:8080/gtn/pdf/Book.pdf',400.00
, '', '');
INSERT INTO PUBLICATION VALUES ('Siddhartha','Herman Hesse','2012-12-12 00:00:00','Book','This novel parallels the life of the Buddha, employing Eastern philosophy in a beautiful and poignant tale of a quest for enlightenment.','http://localhost:8080/gtn/pdf/Book.pdf',400.00
, '', '');
INSERT INTO PUBLICATION VALUES ('This Side of Paradise','F. Scott Fitzgerald','2012-12-12 00:00:00','Book','Set in the years following WWI, this book explores the life of Princeton student Amory Blaine as he struggles with greed, morality, status and more.','http://localhost:8080/gtn/pdf/Book.pdf',400.00
, '', '');
INSERT INTO PUBLICATION VALUES ('The Time Machine','H.G. Wells','2012-12-12 00:00:00','Book','When the Victorian scientist at the center of this story propels himself forward in time he discovers a world that may not be all that it’s cracked up to be.','http://localhost:8080/gtn/pdf/Book.pdf',400.00
, '', '');
INSERT INTO PUBLICATION VALUES ('Little Women','Louise May Alcott','2012-12-12 00:00:00','Book','Follow along with the young women in this novel who come of age in this 19th century setting. Don Quixote by Cervantes: This famous Spanish novel follows the often absurd travels of Don Quixote and his faithful squire Sancho Panza.','http://localhost:8080/gtn/pdf/Book.pdf',400.00
, '', '');
INSERT INTO PUBLICATION VALUES ('The Devils Dictionary','Ambrose Bierce','2012-12-12 00:00:00','Book','Originally published in a magazine, this collection of definitions is entertaining, enlightening and controversial.','http://localhost:8080/gtn/pdf/Book.pdf',400.00
, '', '');
INSERT INTO PUBLICATION VALUES ('Dr. Jekyll and Mr. Hyde','Robert Louis Stevenson','2012-12-12 00:00:00','Book','Readers will be familiar with the title character in this novel who leads a double life as the unpredictable Mr. Hyde.','http://localhost:8080/gtn/pdf/Book.pdf',400.00
, '', '');

SET IDENTITY_INSERT publication ON
GO


update PUBLICATION set type = 'regulatory' where id > 2;
update PUBLICATION set currency_code = 'USD' where id > 2;