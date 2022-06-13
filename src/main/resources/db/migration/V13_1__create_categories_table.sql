CREATE TABLE `categories`
(
    `id`                int(11) NOT NULL AUTO_INCREMENT,
    `name`              varchar(10) NOT NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

ALTER TABLE `shows` ADD COLUMN category_id int(11) ;

ALTER TABLE `shows`
    ADD KEY `shows_category_id_blabla` (`category_id`);

ALTER TABLE `shows`
    ADD CONSTRAINT `shows_category_id_blabla` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`);

INSERT INTO categories (`id`, `name`)
VALUES (1, "unknown");

UPDATE shows SET category_id = 1 WHERE category_id is null;

ALTER TABLE shows MODIFY category_id int(10) NOT NULL;
