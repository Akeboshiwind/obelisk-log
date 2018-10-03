create table if not exists `obelisk-logs` (
  id         int not null primary key auto_increment,
  `date`     datetime not null,
  `fan0`     int,
  `fan1`     int
);
