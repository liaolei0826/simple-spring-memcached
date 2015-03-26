## Further Ideas ##
  * Namespace invalidation
  * Namespace cataloging
  * anti-stampeding support?
  * "Soft" expirations?
  * blocking cache gets (a la Ehcache Blocking Cache)?
  * JMX Mgmt
  * Client pooling
  * Provide a class/bean name to process input params
  * Access to Memcached stats (plus decorations for %ages)
  * Internal stats
  * Multiple logical memcached nodes
  * Built-in second-level cache?
  * 'Observer' pattern access for writes/reads/updates/deletes
  * Thread name/id on logging?
  * Accept a Spring bean name as the converter from parameters to ids
  * [SpEL](http://static.springsource.org/spring/docs/3.0.7.RELEASE/reference/expressions.html) for custom cache key generation
  * Conditional caching
  * Boom protect
  * Cache annotations on interfaces

## Requested Features ##
  * Provide a way for a single value to be referred to by several keys (either multiple-keys or primary-and-secondary keys)

## Implemented Ideas ##
  * Use inline parameter annotation rather than an index in the method annotation --> in **2.0.0**
  * Instead of using keyIndex = -1 to mean the return value holds the key, change the meaning of -1 to signify assigned ID? (Doesn't work for multi-cache annotations.) --> in **2.0.0**
  * Generate cache key using several method's parameters --> in **2.0.0**
  * Counters support --> in **2.0.0**
  * Serialization to JSON --> in **2.0.0**
  * Integration with different memcached clients --> in **2.0.0**
  * Cache Disabling --> in **2.0.0**
  * Runtime memcached node switching --> in **2.0.0**
  * Integration with Spring 3.1 Cache abstraction --> in **3.0.0**
  * Infinispan support (through memcached protocol) --> in **3.0.0**
