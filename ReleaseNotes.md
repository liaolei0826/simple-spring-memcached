# Release Notes #


---

## Version 3.5.0 (15-06-2014) ##
  1. Add new provider for AWS ElastiCache Cluster Client with memcached instances auto discovery feature.
  1. Fix NPE when using ssm.cache.disable=true and SSM as Spring Cache backend (~~[issue 35](https://github.com/ragnor/simple-spring-memcached/issues/35)~~).
  1. Expose native memcached client (~~[issue 36](https://github.com/ragnor/simple-spring-memcached/issues/36)~~).
  1. Remove one again redundant dependency to aspectjrt (~~[issue 31](https://github.com/ragnor/simple-spring-memcached/issues/31)~~).
  1. Update xmemcached to 2.0.0.
  1. Update spymemcached to 2.11.3.

<br />

## Version 3.4.0 (28-03-2014) ##
  1. Update jackson from 1.9.x to latest 2.3.x ([issue 28](https://code.google.com/p/simple-spring-memcached/issues/detail?id=28)).
  1. Custom json/jackson serializers are not called ([issue 29](https://code.google.com/p/simple-spring-memcached/issues/detail?id=29)).
  1. MetricCollector support for spymemcached ([issue 30](https://code.google.com/p/simple-spring-memcached/issues/detail?id=30)).
  1. Remove redundant dependency to aspectjrt ([issue 31](https://code.google.com/p/simple-spring-memcached/issues/detail?id=31)).
  1. Target execution method should not interrupt if memcached server is not reachable (~~[issue 32](https://github.com/ragnor/simple-spring-memcached/issues/32)~~).
  1. Update spymemcached to 2.10.6.
  1. Fix compatibility issue with Spring 4.0 Cache Abstraction.

<br />

## Version 3.3.0 (17-01-2014) ##
  1. Use cache name or alias as a prefix in key ([issue 21](https://code.google.com/p/simple-spring-memcached/issues/detail?id=21)).
  1. Unsorted results when using ReadThroughMultiCacheAdvice ([issue 23](https://code.google.com/p/simple-spring-memcached/issues/detail?id=23)).
  1. Support server weights for XMemcachedClientBuilder ([issue 24](https://code.google.com/p/simple-spring-memcached/issues/detail?id=24)).
  1. Update spymemcached to 2.10.4.
  1. Instead of Throwable catch Exception to avoid catching Error.

<br />

## Version 3.2.1 (27-09-2013) ##
  1. Incomplete results in ReadThroughMultiCache if memcached client times out ([issue 20](https://code.google.com/p/simple-spring-memcached/issues/detail?id=20)).
  1. Update spymemcached to 2.10.0.
  1. Update xmemcached to 1.4.2.

<br />

## Version 3.2.0 (26-06-2013) ##
  1. Extend spymemcached provider to support more settings from ConnectionFactoryBuilder.
  1. Extend xmemcached provider to support more settings from XMemcachedClientBuilder.
  1. Add connect timeout from CacheClientFactory for xmemcached ([issue 18](https://code.google.com/p/simple-spring-memcached/issues/detail?id=18))
  1. Update spymemcached to 2.9.0 and switch to official artifact that is now available in central repository in net.spy group.
  1. Remove deprecated classes.
  1. Integration of ssm with mvc spring application throws exception with jackson 1.7.1 ([issue 12](https://code.google.com/p/simple-spring-memcached/issues/detail?id=12)).
  1. Using @ReturnValueKeyProvider for an @UpdateSingleCache annotated method does not generate key properly ([issue 15](https://code.google.com/p/simple-spring-memcached/issues/detail?id=15)).
  1. Update xmemcached to 1.4.1.

<br />

## Version 3.1.0 (08-10-2012) ##
  1. Ability to order the cache advice ([issue 11](https://code.google.com/p/simple-spring-memcached/issues/detail?id=11)).
  1. Instead of default toString() use @CacheKeyMethod from superclass ([issue 8](https://code.google.com/p/simple-spring-memcached/issues/detail?id=8)).
  1. Update spymemcached to version 2.8.4 and deploy to central repository.
  1. Improve quality of SSM using Continuous Integration [Jenkins at CloudBees](https://ragnor.ci.cloudbees.com/).
  1. Maven plugin to run embedded memcached (jmemcached) in integration tests on Jenkins.

<br />

## Version 3.0.2 (07-08-2012) ##
  1. CacheFactory should be a DisposableBean to permit server shutdown ([issue 6](https://code.google.com/p/simple-spring-memcached/issues/detail?id=6)).

<br />

## Version 3.0.1 (31-07-2012) ##
  1. Remove log4j.property from simple-spring-memcached modue ([issue 5](https://code.google.com/p/simple-spring-memcached/issues/detail?id=5)).

<br />

## Version 3.0.0 (25-07-2012) ##
  1. Support a Spring 3.1 [Cache](http://static.springsource.org/spring/docs/3.1.x/javadoc-api/org/springframework/cache/Cache.html) / [CacheManager](http://static.springsource.org/spring/docs/3.1.x/javadoc-api/org/springframework/cache/CacheManager.html) implementation ([issue 3](https://code.google.com/p/simple-spring-memcached/issues/detail?id=3)).
  1. Providers (spymemcached and xmemcached) in separated modules ([issue 2](https://code.google.com/p/simple-spring-memcached/issues/detail?id=2)).
  1. Sample project [spring-cache-integration-test](http://code.google.com/p/simple-spring-memcached/source/browse/#svn%2Ftrunk%2Fspring-cache-integration-test) that shows how to use SSM as a Spring Cache backend.
  1. [Per method expiration time](Getting_Started#Custom_expiration_time.md) while using SSM as a backend for Spring 3.1 Cache.
  1. Custom default transcoder.
  1. Rewrite serialization logic, for each type: java, json or custom serialization can be used.
  1. In case of json serialization store information about serialized type.
  1. Remove dependency to external maven repositories, deploy spymemcached to central maven repo.

<br />

## Version 2.0.0 (16-04-2012) ##
  1. Cache key can be generated using several input method's parameters.
  1. [Counters support](http://code.google.com/p/simple-spring-memcached/wiki/Getting_Started#Counter_Annotations): increment, decrement, update (overwrite) and read.
  1. Inline parameter annotation, rather than an integer _keyIndex_, to identify the object that will provide the key.
  1. Support different memcached clients: [spymemcached](http://code.google.com/p/spymemcached/) and [xmemcached](http://code.google.com/p/xmemcached/).
  1. [Runtime memcached node switching](http://code.google.com/p/simple-spring-memcached/wiki/UserGuide#Runtime_node_switching). IPs of memcached servers can be modified in the fly without redeploying application.
  1. [Object can be serialized to json](http://code.google.com/p/simple-spring-memcached/wiki/UserGuide#Serialization), pluggable json transcoders mechanism configurable per class.
  1. [Cache zones](http://code.google.com/p/simple-spring-memcached/wiki/UserGuide#Cache_zone) - instead of one global cache many caches using different providers, servers and configuration can be defined.
  1. Integration with Spring 3.
  1. Order of missed keys are the same as order of input arguments in intercepted method.
  1. Extend [ReadThroughMultiCache](https://simple-spring-memcached.googlecode.com/svn/docs/2.0.0/apidocs/com/google/code/ssm/api/ReadThroughMultiCache.html) with options to:
    * generate cache key from result, objects from result will be added to cache not by concatenating method arguments but invoking  [CacheKeyMethod](https://simple-spring-memcached.googlecode.com/svn/docs/2.0.0/apidocs/com/google/code/ssm/api/CacheKeyMethod.html) on each object in result list
    * skip null values in result
    * add null values to cache under keys that don't occurred in result list.
  1. Extend [UpdateMultiCache](https://simple-spring-memcached.googlecode.com/svn/docs/2.0.0/apidocs/com/google/code/ssm/api/UpdateMultiCache.html) with options to:
    * add null values to cache under keys ([@ParameterValueKeyProvider](https://simple-spring-memcached.googlecode.com/svn/docs/2.0.0/apidocs/com/google/code/ssm/api/ParameterValueKeyProvider.html) + [@ReturnValueKeyProvider](https://simple-spring-memcached.googlecode.com/svn/docs/2.0.0/apidocs/com/google/code/ssm/api/ReturnValueKeyProvider.html)) \ ([@ParameterDataUpdateContent](https://simple-spring-memcached.googlecode.com/svn/docs/2.0.0/apidocs/com/google/code/ssm/api/ParameterDataUpdateContent.html) + [@ReturnDataUpdateContent](https://simple-spring-memcached.googlecode.com/svn/docs/2.0.0/apidocs/com/google/code/ssm/api/ReturnDataUpdateContent.html)).
  1. [Cache Disabling](http://code.google.com/p/simple-spring-memcached/wiki/UserGuide#Disabling_cache).