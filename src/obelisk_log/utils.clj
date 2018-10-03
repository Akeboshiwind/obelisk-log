(ns obelisk-log.utils)

(defn parse-int [number-string]
  (try (Integer/parseInt number-string)
       (catch Exception e nil)))
