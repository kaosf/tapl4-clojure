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

(defn eval1 [t]
  (case (first t)
    :if (let [[t1 t2 t3] (rest t)]
          (case (first t1)
            :true t2
            :false t3
            `(:if ~(eval1 t1) ~t2 ~t3)))
    :succ (let [[t1] (rest t)]
            `(:succ ~(eval1 t1)))
    :pred (let [[t1] (rest t)]
               (case (first t1)
                 :zero '(:zero)
                 :succ (let [nv1 (second t1)]
                         (if (numericval? nv1)
                           nv1
                           t))
                 `(:pred ~(eval1 t1))))
    :iszero (let [[t1] (rest t)]
              (case (first t1)
                :zero '(:true)))
    t
    )
  )
