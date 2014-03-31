(ns zord.util)

(defn chop
  "Removes the last character of string."
  [s]
  (subs s 0 (dec (count s))))

(defn choose-n
  "Choose n random elements from col."
  [n col]
  (take n (shuffle col)))

(defn reverse-get
  "Returns the key of the first val in maps vals that equals
  v. Non-deterministic if (vals m) contains duplicates and map isn't
  sorted."
  [m v]
  (let [f (first m)
        n (next m)]
    (if (= (val f) v)
      (key f)
      (when n (reverse-get n v)))))
