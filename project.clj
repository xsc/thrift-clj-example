(defproject thrift-clj-example "0.1.0"
  :description "Example for thrift-clj (https://github.com/xsc/thrift-clj)"
  :url "https://github.com/xsc/thrift-clj-example"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/tools.logging "0.3.1"]
                 [org.clojure/tools.cli "0.3.1"]
                 [ch.qos.logback/logback-classic "1.1.2"]
                 [thrift-clj "0.2.1"]]
  :source-paths ["src/clj"]
  :plugins [[lein-thriftc "0.2.1"]]
  :hooks [leiningen.thriftc])
