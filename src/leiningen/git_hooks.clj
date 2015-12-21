(ns leiningen.git-hooks
  (:require [git-hooks.run :refer [run-cmds!]]
            [git-hooks.install :refer [install!]]
            [leiningen.core.main :refer [info abort]]))

; @todo add pre-release and post-release
(def valid-hooks [:pre-commit :pre-push])

(defn- extract-cmds [project-hooks hook-type]
  (get project-hooks (keyword hook-type)))

(defn- extract-hook-types [project-hooks]
  (map name (filter #(contains? project-hooks %) valid-hooks)))

(defn git-hooks [project & [action hook-type]]
  (let [project-hooks (:git-hooks project)
        project-root (:root project)]
    (case action
      "install" (install! (extract-hook-types project-hooks) project-root)
      "run" (run-cmds! (extract-cmds project-hooks hook-type))
      (abort (format "The action <%s> does not exist" action)))))
