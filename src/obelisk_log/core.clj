(ns obelisk-log.core
  (:require [obelisk-log.api :as api]
            [obelisk-log.db :as db]
            [clj-time.core :as t]
            [clj-time.coerce :refer [from-long]]
            [clj-time.format :as f]
            [obelisk-log.env :as env])
  (:gen-class))

(defn parse-int [number-string]
  (try (Integer/parseInt number-string)
       (catch Exception e nil)))

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
                           env/auth-password]}
        get-cookie #(api/login env/auth-user env/auth-password opts)
        get-timeout #(t/plus (t/now) (t/minutes env/token-refresh-rate))]

    (println "Running database migrations")
    (db/migrate db/config)

    (doall
     (loop [opts opts]
       (let [request (api/dashboard opts)]
         (if (= :unauthorized request)
           (do
             (println "Refreshing cookie")
             (let [cookie (get-cookie)]
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
