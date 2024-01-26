use ver2;
show tables;

drop table member;

create table member (
	id bigint auto_increment not null COMMENT '순번',
    username varchar(50) unique not null COMMENT '사용자 아이디',
    password varchar(100) not null COMMENT '비밀번호',
    role varchar(10) not null COMMENT '권한',
    primary key(id)
);

## 설정된 코멘트 보기
show full columns from member;

select * from member;