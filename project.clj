(defproject org.clojars.mkhoeini/mocha-tester "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://www.github.com/mkhoeini/mocha-tester"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :source-paths ["src"]
  :resources-paths ["resources"]

  :hooks [leiningen.cljsbuild]

  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-2268"]
                 [enlive "1.1.5"]]

  :plugins [[lein-cljsbuild "1.0.3"]]

  :cljsbuild
  {:builds []})
