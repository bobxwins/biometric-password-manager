drop table if exists entry ;
create table entry (  
EntryName varchar (200), 
Titel varchar(200),
Username varchar(200),
URL varchar(200),
Password varchar (200),
Notes text,
primary key (EntryName)
)
;
 
insert into entry(EntryName,Titel,userName,URL,Password,Notes)
values ('bob@hotmail','Youtube','bobwins','youtube.com','kode123','min første youtube kanal')
;

select * from entry