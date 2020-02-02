(ns system
  (:require [com.stuartsierra.component :as component]
            [producer]
            [scheduler]))

(def ^:redef system
  "Holds our system."
  nil)

(defn build-system [producer-cfg]
  (-> (component/system-map
        :api (flights-api/make-api)
        :prod (producer/make-producer producer-cfg)
        :scheduler (scheduler/make-scheduler))
      (component/system-using {:scheduler {:flights-api :api
                                           :producer    :prod}})))

(defn init-system
  [producer-cfg]
  (let [sys (build-system producer-cfg)]
    (alter-var-root #'system (constantly sys))))

(defn stop!
  "Stop system"
  []
  (alter-var-root #'system component/stop-system))

(defn start!
  "Start system"
  []
  (alter-var-root #'system component/start-system)
  (println "System started"))


