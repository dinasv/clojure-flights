(ns system
  (:require [com.stuartsierra.component :as component]
            [info.producer :as producer]
            [info.scheduler :as scheduler]
            )
  (:import (org.apache.kafka.common.serialization ByteArraySerializer)))

(def port "localhost:9092")

(def producer-cfg {"value.serializer"  ByteArraySerializer
                   "key.serializer"    ByteArraySerializer
                   "bootstrap.servers" port})

(defn system [producer-cfg]
  (-> (component/system-map
      :flights-api (flights-api/make-api)
      :producer (producer/make-producer producer-cfg)
      :scheduler (scheduler/make-scheduler))
      (component/system-using {:scheduler [:flights-api :producer]})))


(defn -main [& args]
  (system producer-cfg)
  (system component/start-system))