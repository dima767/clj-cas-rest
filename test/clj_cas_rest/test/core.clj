(ns clj-cas-rest.test.core
  (:use [clj-cas-rest.core])
  (:use [clojure.test]))


(deftest test-calling-cas-rest-client-with-success
	(with-cas "https://cas1.cloudfoundry.com/v1/tickets"
		(let [st (get-service-ticket "test" "test" "http://example.org")]
			(is true st)
			(is (instance? String st))
			(is (.startsWith  st "ST"))
			(is (.endsWith  st "cas")))))
		
