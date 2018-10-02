-- src/obelisk_log/fanspeed.sql
-- Obelisk logs

-- :name add-log :!
-- :doc Add a new log for a given time
insert
into `obelisk-logs` (`date`, `fan0`, `fan1`)
values (:date, :fan0, :fan1)
