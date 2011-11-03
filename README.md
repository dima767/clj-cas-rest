# clj-cas-rest

A simple Clojure client to access JA-SIG CAS REST API

## Usage

```Clojure
(use '[clj-cas-rest.core])

(with-cas "https://cas.server.url"
	(let [st (get-service-ticket "username" "password" "http://protected.service.url")]
	(println "The service ticket is: " st)))
```	