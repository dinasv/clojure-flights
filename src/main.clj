(ns main
  (:gen-class)
  (:require [system])
  (:import (org.apache.kafka.common.serialization ByteArraySerializer)))

(def port "localhost:9092")

(def producer-cfg {"value.serializer"  ByteArraySerializer
                   "key.serializer"    ByteArraySerializer
                   "bootstrap.servers" port})
(defn join []
  (.start (Thread. (fn [] (.join (Thread/currentThread))) "staying alive")))

(defn -main [& args]
  (println "Main")
  (system/init-system producer-cfg)
  (system/start!)
  (join))

(-main)
