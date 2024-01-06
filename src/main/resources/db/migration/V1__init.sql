drop table if exists note;

create table `note`
(
    `id`          bigint       not null auto_increment,
    `title`       varchar(255) not null,
    `content`     mediumtext,
    `created_at`  datetime not null,
    `updated_at`  datetime not null,
    primary key (`id`)
) engine = InnoDB default character set = utf8mb4 collate = utf8mb4_0900_ai_ci;