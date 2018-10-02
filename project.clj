(defproject obelisk-log "0.1.0"
  :description "Log interesting data from the obelisk."
  :license {:name "MIT License"}
  :dependencies [[org.clojure/clojure "1.9.0"]

                 ;; Web scraping
                 [http-kit "2.3.0"]
                 [cheshire "5.8.1"]

                 ;; Dockerizing
                 [environ "1.1.0"]

                 ;; Database
                 [com.layerware/hugsql "0.4.9"]
                 [mysql/mysql-connector-java "8.0.12"]
                 [clj-time "0.14.4"]
                 [migratus "1.1.1"]
                 [org.slf4j/slf4j-log4j12 "1.7.25"]]
  :main ^:skip-aot obelisk-log.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
