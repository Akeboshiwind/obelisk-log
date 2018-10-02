(ns obelisk-log.api
  (:require [org.httpkit.client :as http]
            [cheshire.core :refer [generate-string parse-string]]))

(defn make-address
  [server-address endpoint]
  (str "http://" server-address "/api" endpoint))

(defn login
  [username password {:keys [server-address
                             basic-auth]}]
  (get-in
   @(http/post (make-address server-address "/login")
               {:basic-auth basic-auth
                :body (generate-string {:username username
                                        :password password})})
   [:headers
    :set-cookie]))

(defn get
  [endpoint
   {:keys [server-address
           basic-auth
           cookie]}]
  (let [response (http/get (make-address server-address endpoint)
                           {:basic-auth basic-auth
                            :keep-alive 3000
                            :headers {"Cookie" cookie}})]
    (if (= (:status response) 401)
      :unauthorized ;; Don't like, what would be a better solution?
      (->> response
           :body
           (parse-string true)))))

(defn curr-user
  [opts]
  (get "/currUser" opts))

(defn versions
  [opts]
  (get "/inventory/versions" opts))

(defn dashboard
  [opts]
  (get "/status/dashboard" opts))
