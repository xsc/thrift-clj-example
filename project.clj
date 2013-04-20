(defproject thrift-clj-example "0.1.0"
  :description "Example for thrift-clj (https://github.com/xsc/thrift-clj)"
  :url "https://github.com/xsc/thrift-clj-example"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/tools.logging "0.2.6"]
                 [org.clojure/tools.cli "0.2.2"]
                 [thrift-clj "0.1.0"]]
  :source-paths ["src/clj"]
  :plugins [[lein-thriftc "0.1.0"]]
  :prep-tasks ["thriftc"])
