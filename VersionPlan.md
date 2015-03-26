## Base Assumptions ##
  * Each version fully unit tested
  * Each version fully integration tested

---



# Version 3 #

## 3.6.0 (In Progress) ##
  * [Issue 37](https://github.com/ragnor/simple-spring-memcached/issues/37): Simple hash-code based strategy (**In Progress**)
  * ~~[Issue 38](https://github.com/ragnor/simple-spring-memcached/issues/38)~~: Support objects greater than 1MB  (**Done**)
  * ~~[Issue 39](https://github.com/ragnor/simple-spring-memcached/issues/39)~~: Disable SSM via property (**Done**)
  * ~~[Issue 40](https://github.com/ragnor/simple-spring-memcached/issues/40)~~: Support binary protocol in jmemcached-maven-plugin (**Done**)
  * ~~[Issue 42](https://github.com/ragnor/simple-spring-memcached/issues/42)~~: When using cache name or alias as a prefix in key, @ReadThroughMultiCache does not work properly (**Done**)
  * ~~[Issue 43](https://github.com/ragnor/simple-spring-memcached/issues/43)~~: Fix compatibility issue with Spring 4.1 Cache Abstraction (**Done**)

## 3.5.0 (Done) ##
  * Add new provider for [AWS ElastiCache Cluster Client](https://github.com/amazonwebservices/aws-elasticache-cluster-client-memcached-for-java) (**Done**)
  * ~~[Issue 35](https://github.com/ragnor/simple-spring-memcached/issues/35)~~: NPE when using ssm.cache.disable=true and SSM as Spring Cache backend (**Done**)
  * ~~[Issue 36](https://github.com/ragnor/simple-spring-memcached/issues/36)~~: Expose native memcached client (**Done**)
  * Update xmemcached to 2.0.0 (**Done**)
  * Update spymemcached to 2.11.3 (**Done**)

## 3.4.0 (Done) ##
  * [Issue 28](https://code.google.com/p/simple-spring-memcached/issues/detail?id=28): Update jackson from 1.9.x to latest 2.3.x (**Done**)
  * [Issue 29](https://code.google.com/p/simple-spring-memcached/issues/detail?id=29): Custom json/jackson serializers are not called (**Done**)
  * [Issue 30](https://code.google.com/p/simple-spring-memcached/issues/detail?id=30): MetricCollector support for spymemcached (**Done**)
  * [Issue 31](https://code.google.com/p/simple-spring-memcached/issues/detail?id=31): Remove redundant dependency to aspectjrt (**Done**)
  * ~~[Issue 32](https://github.com/ragnor/simple-spring-memcached/issues/32)~~: Target execution method should not interrupt if memcached server is not reachable (**Done**)
  * Update spymemcached to 2.10.6 (**Done**)
  * Fix compatibility issue with Spring 4.0 Cache Abstraction (**Done**)

## 3.3.0 (Done) ##
  * [Issue 21](https://code.google.com/p/simple-spring-memcached/issues/detail?id=21): Use cache name or alias as a prefix in key (**Done**)
  * [Issue 23](https://code.google.com/p/simple-spring-memcached/issues/detail?id=23): Unsorted results when using ReadThroughMultiCacheAdvice (**Done**)
  * [Issue 24](https://code.google.com/p/simple-spring-memcached/issues/detail?id=24): Support server weights for XMemcachedClientBuilder (**Done**)
  * Update spymemcached to 2.10.4 (**Done**)
  * Instead of Throwable catch Exception to avoid catching Error (**Done**)

## 3.2.1 (Done) ##
  * [Issue 20](https://code.google.com/p/simple-spring-memcached/issues/detail?id=20): ReadThroughMultiCache returns incomplete results if memcached client times out (**Done**)
  * Update spymemcached to 2.10.0 (**Done**)
  * Update xmemcached to 1.4.2 (**Done**)

## 3.2.0 (Done) ##
  * Extend spymemcached provider to support more settings from ConnectionFactoryBuilder (**Done**)
  * Extend xmemcached provider to support more settings from XMemcachedClientBuilder (**Done**)
  * [Issue 18](https://code.google.com/p/simple-spring-memcached/issues/detail?id=18): Add connect timeout from CacheClientFactory for xmemcached (**Done**)
  * Update spymemcached to 2.9.0 and switch to official artifact that is now available in central repository in net.spy group (**Done**)
  * Remove deprecated classes (**Done**)
  * [Issue 12](https://code.google.com/p/simple-spring-memcached/issues/detail?id=12): Integration of ssm with mvc spring application throws exception with jackson 1.7.1 (**Done**)
  * [Issue 15](https://code.google.com/p/simple-spring-memcached/issues/detail?id=15): Using @ReturnValueKeyProvider for an @UpdateSingleCache annotated method does not generate key properly (**Done**)
  * Update xmemcached to 1.4.1 (**Done**)


## ~~3.1.1~~ (Rejected and moved to 3.2.0) ##
  * Remove deprecated classes (**Done**)
  * [Issue 12](https://code.google.com/p/simple-spring-memcached/issues/detail?id=12): Integration of ssm with mvc spring application throws exception with jackson 1.7.1 (**Done**)
  * [Issue 15](https://code.google.com/p/simple-spring-memcached/issues/detail?id=15): Using @ReturnValueKeyProvider for an @UpdateSingleCache annotated method does not generate key properly
  * Update xmemcached to 1.4.1 (**Done**)


## 3.1.0 (Done) ##
  * [Issue 8](https://code.google.com/p/simple-spring-memcached/issues/detail?id=8): Instead of default toString() use @CacheKeyMethod from superclass (**Done**)
  * [Issue 11](https://code.google.com/p/simple-spring-memcached/issues/detail?id=11): Ability to order the cache advice (**Done**)
  * Update spymemcached to version 2.8.4 and deploy to central repository (**Done**)
  * Improve quality of SSM using Continuous Integration:  [Jenkins at CloudBees](https://ragnor.ci.cloudbees.com/job/SSM/) (**Done**)
  * Create maven plugin to run embedded memcached and use it in integration tests on [Jenkins](https://ragnor.ci.cloudbees.com/) (**Done**)

## ~~3.0.3~~ (Rejected and moved to 3.1.0) ##
  * [Issue 8](https://code.google.com/p/simple-spring-memcached/issues/detail?id=8): Instead of default toString() use @CacheKeyMethod from superclass (**Done**)

## 3.0.2 (Done) ##
  * [Issue 6](https://code.google.com/p/simple-spring-memcached/issues/detail?id=6): CacheFactory should be a DisposableBean to permit server shutdown (**Done**)

## 3.0.1 (Done) ##
  * [Issue 5](https://code.google.com/p/simple-spring-memcached/issues/detail?id=5): Default log4j.properties in deployed maven artifact (**Done**)

## 3.0.0 (Done) ##
  * [Issue 2](https://code.google.com/p/simple-spring-memcached/issues/detail?id=2): Providers (spymemcached and xmemcached) in separated modules (**Done**)
  * [Issue 3](https://code.google.com/p/simple-spring-memcached/issues/detail?id=3): Support a Spring 3.1 [Cache](http://static.springsource.org/spring/docs/3.1.x/javadoc-api/org/springframework/cache/Cache.html) / [CacheManager](http://static.springsource.org/spring/docs/3.1.x/javadoc-api/org/springframework/cache/CacheManager.html) implementation (**Done**)
  * Sample project [spring-cache-integration-test](http://code.google.com/p/simple-spring-memcached/source/browse/#svn%2Ftrunk%2Fspring-cache-integration-test) that shows how to use SSM as a Spring Cache backend (**Done**)
  * Per method expiration time while using SSM as a backend for Spring 3.1 Cache (**Done**)
  * Custom default transcoder (**Done**)
  * Rewrite serialization logic, for each type: java, json or custom serialization can be used (**Done**)
  * In case of json serialization store information about serialized type (**Done**)
  * Remove dependency to external maven repositories, deploy spymemcached to central maven repo (**Done**)

# Version 2 #

## ~~2.1.0~~ (Rejected and moved to 3.0.0) ##
  * [Issue 2](https://code.google.com/p/simple-spring-memcached/issues/detail?id=2): Providers in separated modules
  * [Issue 3](https://code.google.com/p/simple-spring-memcached/issues/detail?id=3): Support a Spring 3.1 [Cache](http://static.springsource.org/spring/docs/3.1.x/javadoc-api/org/springframework/cache/Cache.html) / [CacheManager](http://static.springsource.org/spring/docs/3.1.x/javadoc-api/org/springframework/cache/CacheManager.html) implementation
  * Custom default transcoder


## 2.0.0 (Done) ##
  * Inline parameter annotation, rather than an integer _keyIndex_, to identify the object that will provide the key (**Done**)
  * Cache key can be generated using several input method's parameters (**Done**)
  * Counters support: increment, decrement, update (overwrite) and read (**Done**)
  * ~~Ability for the key generation to be done via a referenced bean that adheres to a SSM-specified interface~~
  * Runtime memcached node switching. IPs of memcached servers can be modified in the fly without redeploying application (**Done**)
  * Object can be serialized to json, pluggable json transcoders mechanism configurable per class (**Done**)
  * Support different memcached clients: [spymemcached](http://code.google.com/p/spymemcached/) and [xmemcached](http://code.google.com/p/xmemcached/) (**Done**)
  * Integration with Spring 3 (**Done**)
  * Order of missed keys are the same as order of input arguments in intercepted method (**Done**)
  * Extend [ReadThroughMultiCache](https://simple-spring-memcached.googlecode.com/svn/docs/2.0.0/apidocs/com/google/code/ssm/api/ReadThroughMultiCache.html) with options to:
    * generate cache key from result, objects from result will be added to cache not by concatenating method arguments but invoking  [CacheKeyMethod](https://simple-spring-memcached.googlecode.com/svn/docs/2.0.0/apidocs/com/google/code/ssm/api/CacheKeyMethod.html) on each object in result list (**Done**)
    * skip null values in result (**Done**)
    * add null values to cache under keys that don't occurred in result list (**Done**)
  * Extend [UpdateMultiCache](https://simple-spring-memcached.googlecode.com/svn/docs/2.0.0/apidocs/com/google/code/ssm/api/UpdateMultiCache.html) with options to:
    * add null values to cache under keys ([@ParameterValueKeyProvider](https://simple-spring-memcached.googlecode.com/svn/docs/2.0.0/apidocs/com/google/code/ssm/api/ParameterValueKeyProvider.html) + [@ReturnValueKeyProvider](https://simple-spring-memcached.googlecode.com/svn/docs/2.0.0/apidocs/com/google/code/ssm/api/ReturnValueKeyProvider.html)) \ ([@ParameterDataUpdateContent](https://simple-spring-memcached.googlecode.com/svn/docs/2.0.0/apidocs/com/google/code/ssm/api/ParameterDataUpdateContent.html) + [@ReturnDataUpdateContent](https://simple-spring-memcached.googlecode.com/svn/docs/2.0.0/apidocs/com/google/code/ssm/api/ReturnDataUpdateContent.html)) (**Done**)
  * Cache zones - instead of one global cache many caches using different providers, servers and configuration can be defined (**Done**)
  * ~~Custom spring namespace (ssm)~~ (**moved to 2.?.0**)
  * [Cache Disabling](http://code.google.com/p/simple-spring-memcached/wiki/UserGuide#Disabling_cache) (**Done**)
  * Deploy artifacts to maven central repository (**Done**)


---

# Version 1 #

## ~~1.0.0~~ (Done) ##
  * If there's no problems with RC1, publish a full 1.0 version

## ~~1.0.0-RC1~~ (Done) ##
  * Package up a 'code complete' set for release to the wild.

## ~~0.11~~ (Done) ##
  * DOCUMENTATION IN PREPARATION FOR 1.0 RELEASE

## ~~0.10~~ (Done) ##
  * Fix Update\*Cache. They assume the output is the source of the cachable data.

## ~~0.9~~ (Done) ##
  * Update individual item cache by assigned id

## ~~0.8~~ (Done) ##
  * Invalidate individual item cache by assigned id

## ~~0.7~~ (Done) ##
  * Read-through individual item cache by assigned id

## ~~0.6~~ (Done) ##
  * Invalidate multiplexed item cache

## ~~0.5~~ (Done) ##
  * Invalidate individual item cache

## ~~0.4~~ (Done) ##
  * Update multiplexed item cache

## ~~0.3~~ (Done) ##
  * Update individual item cache

## ~~0.2~~ (Done) ##
  * Read-through multiplexed item cache

## ~~0.1~~ (Done) ##
  * Read-through individual item cache