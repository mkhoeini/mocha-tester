(ns mocha-tester.core)

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
