(ns 
  person-index.server
  (:require [thrift-clj.core :as thrift])
  (:use clojure.tools.logging
        person-index.service))

;; --- Main
(defn -main
  ([] (-main "7007"))
  ([& args]
   (run-person-index
     thrift/multi-threaded-server
     args)))
