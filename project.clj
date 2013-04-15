(defproject thrift-clj-example "0.1.0"
  :description "Example for thrift-clj (https://github.com/xsc/thrift-clj)"
  :url "https://github.com/xsc/thrift-clj-example"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [thrift-clj "0.1.0-alpha3"]]
  :source-paths ["src/clj"]
  :plugins [[lein-thriftc "0.1.0"]]
  :prep-tasks ["thriftc"])
