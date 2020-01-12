(ns scheduler
  (:require [com.stuartsierra.component :as component])
  (:import (org.apache.kafka.clients.producer KafkaProducer ProducerRecord)))

(defn send-msg [producer topic msg-bytes]
  (.send producer (ProducerRecord. topic msg-bytes)))

(defrecord Scheduler [flights-api producer]
  ;; Implement the Lifecycle protocol
  component/Lifecycle

  (start [this]
    (println ";; Starting scheduler")
    (while true
      (do
        (let [flight-bytes flights-api/request
              topic "topic"
              sleep 100000]
          (send-msg producer topic flight-bytes)
          (Thread/sleep sleep))))

    (assoc this :flights-api flights-api :producer producer))

  (stop [this]
    (println ";; Stopping scheduler")
    (assoc this :flights-api nil :producer nil)))


(defn make-scheduler
  []
  (map->Scheduler {}))