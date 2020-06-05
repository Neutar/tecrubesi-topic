create table topic (
	id uuid PRIMARY KEY,
	correct_answer_id uuid,
	description varchar(255),
	status varchar(255),
	title varchar(255),
	user_id uuid not null);

create table comment (
	id uuid PRIMARY KEY,
	topic_id uuid references topic(id),
	message varchar(255));

create table flag (
	id uuid PRIMARY KEY,
	topic_id uuid references topic(id),
	comment_id uuid references comment(id),
	reason varchar(255) not null,
	user_id uuid not null);

create table topic_bookmarked_user (
	topic_id uuid references topic(id),
	bookmarked_user_id uuid);

create table topic_followed_user (
	topic_id uuid references topic(id),
	followed_user_id uuid);

create table topic_tag (
	id uuid PRIMARY KEY,
	topic_id uuid references topic(id),
	name varchar(255) not null);

create table vote (id uuid PRIMARY KEY,
	topic_id uuid references topic(id),
	user_id uuid not null,
	vote_type varchar(255) not null);