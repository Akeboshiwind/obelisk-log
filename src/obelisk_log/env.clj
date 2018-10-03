(ns obelisk-log.env
  (:require [environ.core :refer [env]]
            [obelisk-log.utils :refer [parse-int]]))

(def db-host (or (env :db-host) "localhost"))

(def db-port (or (env :db-port) 3306))

(def db-name (or (env :db-name) "obelisk-logs"))

(def db-user (or (env :db-user) "admin"))

(def db-password (or (env :db-password) "admin"))

(def refresh-rate (or (parse-int (env :refresh-rate)) 5))

(def server-address (or (env :server-address) "localhost"))

(def auth-user (or (env :auth-user) "db"))

(def auth-password (or (env :auth-password) "password"))

(def panel-user (or (env :panel-user) "admin"))

(def panel-password (or (env :panel-password) "admin"))
