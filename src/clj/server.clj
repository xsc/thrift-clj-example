(ns server
  (:require [thrift-clj.core :as thrift])
  (:use clojure.tools.logging))

;; --- Import Thrift Classes
(thrift/import
  (:types [org.example Person])
  (:services org.example.PersonIndex))

;; --- Data Store
(defonce person-db (atom {}))

;; --- Service Implementation
(thrift/defservice person-index-service
  PersonIndex
  (storePerson [id p]
    (if-not (@person-db id)
      (do
        (info "Storing Person:" p)
        (swap! person-db assoc id p)
        true)
      false))
  (getPerson [id]
    (info "Retrieving Person for ID:" id)
    (@person-db id))) 

;; --- Main
(defn -main
  ([] (-main "7007"))
  ([port & args]
   (let [port (Integer/parseInt port)]
     (info "Running Server on Port" port "...")
     (let [server (thrift/multi-threaded-server 
                    person-index-service
                    :socket port)]
       (thrift/start-server! server)))))
