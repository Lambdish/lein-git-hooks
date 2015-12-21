(ns leiningen.git-hooks
  (:require [git-hooks.run :refer [run-cmds!]]))

(defn extract-cmds-from-projec [project hook-type]
  (get-in project [:git-hooks (keyword hook-type)]))

(defn git-hooks [project & [action hook-type]]
  (case action
    "run" (run-cmds! (extract-cmds-from-projec project hook-type))))
