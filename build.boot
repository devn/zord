#!/usr/bin/env boot

#tailrecursion.boot.core/version "2.2.1"

(set-env!
  :project      'zord
  :version      "0.1.0-SNAPSHOT"
  :dependencies '[[tailrecursion/boot.task   "2.1.1"]
                  [tailrecursion/hoplon      "5.5.1"]
                  [tailrecursion/boot.notify "2.0.0-SNAPSHOT"]
                  [tailrecursion/boot.ring   "0.1.0-SNAPSHOT"]
                  [org.clojure/clojurescript "0.0-2156"]
                  [repl "0.1.0-SNAPSHOT"]
                  [hum "0.3.0"]
                  [com.andrewmcveigh/cljs-time "0.1.1"]]
  :profiles     {:dev {:repl-options {:init-ns 'tailrecursion.hoplon.app_pages._index_DOT_html}}}
  :out-path     "resources/public"
  :src-paths    #{"src"})

;; Static resources (css, images, etc.):
(add-sync! (get-env :out-path) #{"assets"})

(require '[tailrecursion.hoplon.boot :refer :all]
         '[tailrecursion.boot.task.notify :refer [hear]]
         '[tailrecursion.boot.task.ring  :as r])

(deftask development
  "Build zord for development."
  []
  (comp (watch)
        (hoplon {:prerender false :pretty-print true})
        (r/dev-mode)
        (r/files)
        (r/jetty)))

(deftask production
  "Build zord for production."
  []
  (hoplon {:optimizations :advanced}))
