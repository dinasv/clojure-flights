(defproject src "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [com.taoensso/nippy "2.14.0"]
                 [org.apache.kafka/kafka-clients "2.4.0" :exclusions [javax.jms/jms
                                                                      com.sun.jdmk/jmxtools
                                                                      com.sun.jmx/jmxri]]
                 [com.fzakaria/slf4j-timbre "0.3.15"]
                 [org.clojure/tools.logging "0.4.0"]
                 [environ "1.1.0"]
                 [org.clojure/data.json "0.2.6"]
                 [com.stuartsierra/component "0.3.2"]
                 [overtone/at-at "1.2.0"]]
  :repl-options {:init-ns src.core}
  :main main
  :aot [main])
