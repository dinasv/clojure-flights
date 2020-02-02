(ns producer
  (:require [environ.core :refer [env]]
            [com.stuartsierra.component :as component])
  (:import [org.apache.kafka.clients.producer KafkaProducer ProducerRecord])
  (:gen-class))

(defn send-msg [producer topic msg-bytes]
  (println ";; Sending message")
  (.send (:producer producer) (ProducerRecord. topic msg-bytes)))

(defrecord Producer [cfg]
  ;; Implement the Lifecycle protocol
  component/Lifecycle

  (start [this]
    (println ";; Starting producer")
    (assoc this :producer (KafkaProducer. cfg)))

  (stop [this]
    (println ";; Stopping producer")
    (let [p (:producer this)]
       (.close p))
    (assoc this :producer nil)))

(defn make-producer
  [cfg]
  (map->Producer {:cfg cfg}))