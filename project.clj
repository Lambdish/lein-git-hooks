(defproject lein-git-hooks "0.1.0-SNAPSHOT"
  :description "Lein plugin for managing custom git hooks"
  :url "https://github.com/Lambdish/lein-git-hooks"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}
  :eval-in-leiningen true
  :profiles {:dev {:plugins [[lein-githooks "0.1.0"]
                             [lein-kibit "0.1.2"]
                             [lein-bikeshed "0.2.0"]]
                   :git-hooks
                            {:pre-commit
                             ["lein do kibit, bikeshed -m 120, eastwood \"{:namespaces [:source-paths]}\""]}}})
