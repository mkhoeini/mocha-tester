(ns mocha-tester.core
  (:require [net.cgrand.enlive-html :as enlive]))



;; Mocha helper macros

(defmacro describe [text & body]
  (let [text (str text)]
    `(js/describe ~text (fn [] ~@body))))

(defmacro it [text & body]
  (let [text (str text)
        [par & body] (if (vector? (first body)) body (cons [] body))]
    `(js/it ~text (fn ~par ~@body))))

(defmacro before [& body]
  (let [[par & body] (if (vector? (first body)) body (cons [] body))]
    `(js/before (fn ~par ~@body))))

(defmacro after [& body]
  (let [[par & body] (if (vector? (first body)) body (cons [] body))]
    `(js/after (fn ~par ~@body))))

(defmacro before-each [& body]
  (let [[par & body] (if (vector? (first body)) body (cons [] body))]
    `(js/beforeEach (fn ~par ~@body))))

(defmacro after-each [& body]
  (let [[par & body] (if (vector? (first body)) body (cons [] body))]
    `(js/afterEach (fn ~par ~@body))))



;; Ring helper stuff

(defn- head-str [base]
  (enlive/html
   [:link {:rel "stylesheet" :type "text/css" :href (str base "/mocha.css")}]
   [:script {:src (str base "/mocha.js")}]
   [:script "mocha.setup('bdd');"]))

(def ^:private body-str
  (enlive/html
   [:div#mocha]
   [:script "mocha.run();"]))

(defn apply-mocha [html base]
  (enlive/sniptest html
                   [:head] (enlive/append (head-str base))
                   [:body] (enlive/append body-str)))
