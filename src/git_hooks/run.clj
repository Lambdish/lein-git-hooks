(ns git-hooks.run
  (:require [leiningen.core.eval :refer [sh]]
            [leiningen.core.main :refer [info abort]]))

(defn- parse-cmd-args [cmd]
  (map #(clojure.string/replace % "\"" "")
       (clojure.string/split cmd #"[ ]+(?=([^\"]*\"[^\"]*\")*[^\"]*$)")))

(defn- run-cmd! [cmd]
  (let [command-with-arguments (parse-cmd-args cmd)
        exit-code (apply sh command-with-arguments)]
    (when-not (zero? exit-code)
      (abort (format "The execution of the command <%s> has exited with a <%d> status code." cmd exit-code)))))

(defn run-cmds! [cmds]
  (doseq [cmd cmds] (run-cmd! cmd)))
