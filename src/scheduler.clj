(ns scheduler
  (:require [com.stuartsierra.component :as component]
            [flights-api]
            [producer]
            [chime :refer [chime-ch]]
            [overtone.at-at :as at]))

(defrecord Scheduler
  [producer flights-api]
  ;; Implement the Lifecycle protocol
  component/Lifecycle

  (start [this]
    (println ";; Starting scheduler")

    (let [pool (at/mk-pool)]
      (at/every 1000 #(producer/send-msg producer "topic" (flights-api/request flights-api)) pool))

    this)

  (stop [this]
    (println ";; Stopping scheduler")
    this))


(defn make-scheduler
  []
  (map->Scheduler {}))