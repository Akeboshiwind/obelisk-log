(ns obelisk-log.db
  (:require [hugsql.core :as hugsql]
            [obelisk-log.env :as env]
            [migratus.core :as migratus]))

(def db {:classname "com.mysql.cj.jdbc.Driver"
         :subprotocol "mysql"
         :subname (str "//" env/db-host ":" env/db-port "/" env/db-name)
         :user env/db-user
         :password env/db-password})

(def config {:store :database
             :migration-dir "migrations"
             :db db})

(def init migratus/init)

(def migrate migratus/migrate)

(def pending-list migratus/pending-list)

(def create migratus/create)

;; Note:
;; To create a new migration just run (db/create db/config "migration-name")
;; in the core namespace.

(hugsql/def-db-fns "logs.sql")
