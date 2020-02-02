(ns flights-api
  (:require [com.stuartsierra.component :as component]
            [taoensso.nippy :as nippy]))


(def flights-results-bytes (nippy/freeze {:date  1234
                                          :time  22
                                          :price 300}))

(defn request
  [flights-api]
  flights-results-bytes)

(defrecord FlightsApi []
  ;; Implement the Lifecycle protocol
  component/Lifecycle

  (start [this]
    (println ";; Starting flights-api")
    this)

  (stop [this]
    (println ";; Stopping scheduler")
    this))

(defn make-api
  []
  (map->FlightsApi {}))
