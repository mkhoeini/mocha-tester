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
  (format
   "<link rel='stylesheet' type='text/css' href='%1$s/mocha.css'>
   <script src='%1$s/mocha.js'></script>
   <script>mocha.setup('bdd')</script>" base))

(def ^:private body-str
  "<div id='mocha'></div>
   <script>mocha.run()</script>")

(defn apply-mocha [html base]
  (enlive/sniptest html
                   [:head] (enlive/append (head-str base))
                   [:body] (enlive/append body-str)))
