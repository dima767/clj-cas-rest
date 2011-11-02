(ns clj-cas-rest.test.core
  (:use [clj-cas-rest.core])
  (:use [clojure.test]))

(defn- call-cas-rest
	[]
	(binding [*cas-uri* "https://cas1.cloudfoundry.com/v1/tickets"]
		(get-service-ticket "test" "test" "http://example.org")))

(deftest test-calling-cas-rest-client-with-success
  (is true (call-cas-rest))
	(is (instance? String (call-cas-rest)))
	(is (.startsWith  (call-cas-rest) "ST"))
	(is (.endsWith  (call-cas-rest) "cas")))
		
