(ns client
  (:require [thrift-clj.core :as thrift])
  (:use [clojure.tools.logging :only [info debug warn error]]
        [clojure.tools.cli :only [cli]]))

;; --- Import Classes
(thrift/import
  (:types [org.example Person])
  (:clients org.example.PersonIndex))

;; --- Commands
(defn- run-command
  [client cmd args]
  (case cmd
    "store" (let [[id f l a] args
                  id (Integer/parseInt (str id))
                  a (Integer/parseInt (str a))
                  p (Person. f l a)]
              (info "Storing Person using ID" id "...")
              (let [r (PersonIndex/storePerson client id p)]
                (if r
                  (info "Storing successful.")
                  (warn "Storing failed. (Duplicate ID?)"))))
    "get" (let [[id] args
                id (Integer/parseInt (str id))]
            (info "Retrieving Person using ID" id "...")
            (let [p (PersonIndex/getPerson client id)]
              (info "Person:" p)))
    (warn "No such Command:" cmd)))

;; --- Main
(defn -main
  [& args]
  (let [[data params help] (cli args
                                ["-h" "--host" "Host to connect to." :default "localhost"]
                                ["-p" "--port" "Port to connect to." :parse-fn #(Integer. ^String %) :default 7007]
                                ["-c" "--command" "Command to execute."])
        {:keys[host port command]} data]
    (if command
      (do
        (info "Connecting to" (str host ":" port) "...")
        (with-open [c (thrift/connect! PersonIndex [host port])]
          (run-command c command params))
        (info "Done."))
      (println help))))
