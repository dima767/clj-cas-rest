(ns clj-cas-rest.core
	(:require [clj-http.client :as http-client]))
	
(def ^:dynamic *cas-uri* nil)

(defmacro with-cas [cas-uri & body]
  `(binding [*cas-uri* ~cas-uri]
    ~@body))

(defn- req-tgt-ticket 
	[username password]	
	((:headers
	    (http-client/request
	      {:method :post
	       :url *cas-uri*
				 :accept "text/plain"
	       :form-params {:username username :password password}}) 
 	 ) "location")
)

(defn- tgt-ticket
	[username password]
	(->
		(req-tgt-ticket username password)
		(.split "/")
		(seq)
		(last)))

(defn- req-st-ticket 
	[tgt protected-service]	
	(:body
	    (http-client/request
	      {:method :post
	       :url (str *cas-uri* "/" tgt)
	       :form-params {:service protected-service}}) 
 	 )
)

(defn get-service-ticket
	[username password service-uri]
	(->
		(tgt-ticket username password)
		(req-st-ticket service-uri)))
		