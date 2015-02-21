(ns tapl4.core
  (:gen-class))

(defn -main
  "TaPL 4 Clojure implementaion"
  [& args]
  (println "Hello, World!"))

(defn numeric? [t]
  (case (first t)
    :zero true
    :succ (numeric? (rest t))
    :pred (numeric? (rest t))
    false))

(defn myeval [t]
  (cond
    (keyword? t) (case t
                   :true :true
                   :false :false
                   :zero :zero)
    (list? t) (let [f (first t)
                    s (second t)]
                (case f
                  :succ (cond s
                          (= s :zero) '(:succ :zero)
                          (list? s)   (let [fs (first s)
                                            rs (rest s)]
                                        (case fs
                                          :pred (myeval rs)
                                          (list :succ (myeval ss))))
                          :else       9
                          (comment
                           '(:pred :zero) :zero
                           (list :succ (myeval s))))
                  :pred (case s
                          '(:succ :zero) :zero
                          (list :pred (myeval s)))
                  :iszero (case (second t)
                            :zero :true
                            :false)
                  :ifthenelse (case (second t)
                                :true (nth t 2)
                                :false (nth t 3)
                                999)
                  99))
    :else 9))

(defn reload []
  (load-file "src/tapl4/core.clj"))

(defn rt []
  (do
    (reload)
    (in-ns 'tapl4.core-test)
    (load-file "test/tapl4/core_test.clj")
    (clojure.test/run-tests)
    (in-ns 'tapl4.core)))
