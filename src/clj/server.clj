(ns server
  (:require [thrift-clj.core :as thrift])
  (:use clojure.tools.logging))

;; --- Import Thrift Classes
(thrift/import
  (:types [person.index Person PersonNotFound])
  (:services person.index.PersonIndex))

;; --- Data Store
(defonce person-db (atom {}))

;; --- Service Implementation
(thrift/defservice person-index-service
  PersonIndex
  (storePerson [id p]
    (boolean
      (when-not (@person-db id)
          (info "Storing Person:" p)
          (swap! person-db assoc id p)
          true)))
  (getPerson [id]
    (info "Retrieving Person for ID:" id)
    (or (@person-db id)
        (thrift/throw (PersonNotFound. id)))))

;; --- Main
(defn -main
  ([] (-main "7007"))
  ([port & args]
   (let [port (Integer/parseInt port)]
     (info "Running Server on Port" port "...")
     (let [server (thrift/multi-threaded-server person-index-service port)]
       (thrift/serve-and-block! server)))))
