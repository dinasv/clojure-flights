(ns producer
  (:require [environ.core :refer [env]]
            [com.stuartsierra.component :as component])
  (:import [org.apache.kafka.clients.producer KafkaProducer ProducerRecord])
  (:gen-class))

(defrecord Producer [cfg]
  ;; Implement the Lifecycle protocol
  component/Lifecycle

  (start [this]
    (println ";; Starting producer")
    ((assoc this :producer (KafkaProducer. cfg))))

  (stop [this]
    (println ";; Stopping producer")
    (let [p (this :producer)]
       (.close p))
    (assoc this :producer nil)))

(defn make-producer
  [cfg]
  (map->Producer {:cfg cfg}))