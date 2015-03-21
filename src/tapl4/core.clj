(ns tapl4.core
  (:gen-class))

(defn numericval? [t]
  (case (first t)
    :zero true
    :succ (numericval? (second t))
    :pred (numericval? (second t))
    false))

(defn val? [t]
  (case (first t)
    :zero  true
    :true  true
    :false true
    (numericval? t)))
