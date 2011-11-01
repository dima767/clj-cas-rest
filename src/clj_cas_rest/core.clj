(ns clj-cas-rest.core
	(:require [clj-http.client :as http-client]))
	
(def cas-uri "https://cas1.cloudfoundry.com/v1/tickets")
(def protected-service "http://example.org")	
	

(defn- req-tgt-ticket 
	[]	
	((:headers
	    (http-client/request
	      {:method :post
	       :url cas-uri
				 :accept "text/plain"
	       :form-params {:username "test" :password "test"}}) 
 	 ) "location")
)

(defn- tgt-ticket
	[]
	(->
		(req-tgt-ticket)
		(.split "/")
		(seq)
		(last)))

(defn- req-st-ticket 
	[tgt]	
	(:body
	    (http-client/request
	      {:method :post
	       :url (str cas-uri "/" tgt)
	       :form-params {:service protected-service}}) 
 	 )
)

(defn get-service-ticket
	[]
	(->
		(tgt-ticket)
		(req-st-ticket)))










		
		