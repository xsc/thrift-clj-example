(ns client
  (:require [thrift-clj.core :as thrift])
  (:use clojure.tools.logging))

;; --- Import Classes
(thrift/import
  (:types [org.example Person])
  (:clients org.example.PersonIndex))

;; --- Commands
(defn- run-command
  [client [cmd & args]]
  (case cmd
    "--store" (let [[id f l a] args
                    id (Integer/parseInt id)
                    a (Integer/parseInt a)
                    p (Person. f l a)]
                (info "Storing Person using ID" id "...")
                (let [r (PersonIndex/storePerson client id p)]
                  (if r
                    (info "Storing successful.")
                    (warn "Storing failed. (Duplicate ID?)"))))
    "--get" (let [[id] args
                  id (Integer/parseInt id)]
              (info "Retrieving Person using ID" id "...")
              (let [p (PersonIndex/getPerson client id)]
                (info "Person:" p)))
    (warn "No such Command:" cmd)))

;; --- Main
(defn -main
  [ip port & args]
  (let [port (Integer/parseInt port)]
    (info "Connecting to" (str ip ":" port) "...")
    (let [client (thrift/create-client PersonIndex :socket ip port)]
      (with-open [c (thrift/connect! client)]
        (run-command c args)))
    (info "Done.")))
