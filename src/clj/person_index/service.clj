(ns ^{ :doc "Service Implementation of PersonIndex"
       :author "Yannick Scherer" }
  person-index.service
  (:require [thrift-clj.core :as thrift])
  (:use clojure.tools.logging))

;; --- Import
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

;; --- Running
(defn run-person-index
  "Run Person Index using the given Server Function and argument vector."
  [server-fn [port & _]]
  (let [port (Integer/parseInt port)]
    (info "Running Server on Port" port "...")
    (let [server (server-fn person-index-service port)]
      (thrift/serve-and-block! server))))
