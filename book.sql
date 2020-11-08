drop table book;
CREATE TABLE Book (
  bookno      NUMBER(4) PRIMARY KEY AUTO_INCREMENT,
  title       VARCHAR2(40),
  publisher   VARCHAR2(40),
  price       NUMBER(8) 
);

/* MariaDB */
drop table book;
CREATE TABLE Book (
  bookno      INT PRIMARY KEY AUTO_INCREMENT,
  title       VARCHAR(40),
  publisher   VARCHAR(40),
  price       INT
);



insert into book (bookno ,title,publisher,price)
values ((select nvl(max(bookno),0)+1 from book)  ,'�옄諛�','�븳鍮�',900);

insert into book (bookno ,title,publisher,price)
values ((select nvl(max(bookno),0)+1 from book)  ,'java','david',900);

insert into book (bookno ,title,publisher,price)
values (1 ,'java','kmove',900);

select * from book order by bookno desc

delete from book where bookno = ?

        
commit;

select * from book;

String sql = "select * from book order by bookno desc";

String sql = "insert into book (bookno ,title,publisher,price) " + 
       		"values ((select nvl(max(bookno),0)+1 from book)  ,?,?,?)";
       		
String sql = "delete from book where bookno = ?";

		




			    
 					    

 /* user table */
drop table users;
create table users(
	id varchar2(10) constraint users_id_pk primary key,
	password varchar2(10) constraint password_not_null not null,
	name varchar2(20),
	role varchar2(10) default 'user' check(role in ('user','admin'))
);

 /* user table MariaDB */
drop table users;
create table user(
	id varchar(10)  primary key,
	password varchar(10)  not null,
	name varchar(20),
	role varchar(10) default 'user' check(role in ('user','admin'))
);

insert into user (id,password,name,role) values ('admin','1234','gwanri','admin');
insert into user (id,password,name) values ('java01','1234','hong gildong');

/* board table */
drop table board;

create table board(
	seq number(5) constraint board_seq_pk primary key,
	title varchar2(100) constraint title_not_null not null,
	content varchar2(1000),
	regdate date default sysdate,
	cnt number(5) default 0,
	id varchar2(10) constraint id_fk references users
);




##############
rownum
##############

select rownum,ename,sal,hiredate
from emp;

select rownum,ename,sal,hiredate
from emp
order by sal desc;


select rownum,ename,sal,hiredate
from (select * from emp order by sal desc);

select rownum,ename,sal,hiredate
from (select * from emp order by sal desc)
where rownum<4;    // O

##二쇱쓽 
select rownum,ename,sal,hiredate
from (select * from emp order by sal desc)
where rownum>4;       // X

select rownum,ename,sal,hiredate
from (select * from emp order by sal desc)
where rownum between 1 and 4;    // O


select rownum,ename,sal,hiredate
from (select * from emp order by sal desc)
where rownum between 4 and 7;    //   X

 
select rownum,ename,sal,hiredate
from (select * from emp order by sal desc)
where rownum between 1 and 4; 


##################################
page 泥섎━
##################################

select * from (
select rownum row#,ename,sal,hiredate
from (select * from emp order by sal desc)
)
where row# between 11 and 15; 


                start       end
1page  1~5      5*0+1       +5
2page  6~10     5*1+1       +10
3page  11~15    5*2+1       +15


select * from (
     select rownum row# , deptno,dname,loc 
     from (select * from dept order by deptno)
) where row# between 3 and 5; 




String sql="";
sql="SELECT r, seq,title,contents,writer,time,viewcount " + 
		"FROM(select rownum r, seq,title,contents,writer,time,viewcount " + 
		"from(select * from board order by time desc)) " + 
		"WHERE r>=? and r<=?";//�씤�뜳�뒪 1踰덈��꽣


PreparedStatement st=con.prepareStatement(sql);
int start=((pageNum-1)*count)+1;
int end=(pageNum*count);
st.setInt(1, start);
st.setInt(2,end);


