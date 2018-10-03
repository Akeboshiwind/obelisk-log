(ns obelisk-log.core
  (:require [obelisk-log.api :as api]
            [obelisk-log.db :as db]
            [clj-time.core :as t]
            [clj-time.format :as f]
            [obelisk-log.env :as env]
            [obelisk-log.utils :refer [parse-int]])
  (:gen-class))

(defn dashboard->data
  [dashboard]
  (->> dashboard
       :systemInfo
       ((juxt #(nth % 3) #(nth % 4)))
       (map #(->> %
                  :value
                  (re-find #"^\d+")
                  (parse-int)))
       (zipmap [:fan0 :fan1])))

(def output-formatter (f/formatter "YYYY-MM-dd HH:mm:ss"))

(defn -main
  [& args]
  (let [opts {:server-address env/server-address
              :basic-auth [env/auth-user
                           env/auth-password]}]

    (println "Running database migrations")
    (db/migrate db/config)

    (doall
     (loop [opts opts]
       (let [request (api/dashboard opts)]
         (if (= :failed request)
           (do
             (println "Refreshing cookie")
             (let [cookie (api/login env/panel-user
                                     env/panel-password opts)]
               (recur (assoc opts :cookie cookie))))
           (do
             (println "Logging")
             (let [data (dashboard->data request)
                   date (f/unparse output-formatter (t/now))]
               (db/add-log db/db
                           (assoc data :date date)))
             (println "Sleeping")
             (Thread/sleep (* env/refresh-rate 1000))
             (recur opts))))))))

(comment

  (def opts {:server-address env/server-address
             :basic-auth [env/auth-user
                          env/auth-password]})

  (def cookie (api/login env/panel-user
                         env/panel-password opts))

  (def opts (assoc opts :cookie cookie))

  (def request (api/dashboard opts))

  [])
