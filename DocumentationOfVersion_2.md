

# Introduction #
Distributed caching can be a big, hairy, intricate, and complex proposition when using it extensively.

Simple-Spring-Memcached (SSM) attempts to simplify implementation for several basic use cases.

This project enables caching in Spring-managed beans, by using Java 5 Annotations and Spring/AspectJ AOP on top of the [spymemcached](http://code.google.com/p/spymemcached/) or [xmemcached](http://code.google.com/p/xmemcached/) client. Using Simple-Spring-Memcached requires only a little bit of configuration and the addition of some specific annotations on the methods whose output or input is being cached.

# Dependencies #
The first thing you'll need, of course, is at least one running Memcached server. (Installation and usage instructions may be found [on the memcached project page](http://memcached.org/).)

Here is the dependency list from my `pom.xml` file:
```
    <dependency>
      <groupId>com.google.code.simple-spring-memcached</groupId>
      <artifactId>simple-spring-memcached</artifactId>
      <version>2.0.0</version>
    </dependency>
```
In addition to the Simple-Spring-Memcached JAR file, the following libraries must be accessible:
  * Spring is an assumed dependency, and this project was built against the 3.0.7.RELEASE version. A Spring library that is 3.0.7.RELEASE compatible must included be on the classpath.
  * Two AspectJ libraries are also required, and this project was built against the 1.6.8 versions. These libraries may have version dependencies with your Spring version, so you are free to add whatever versions are necessary to the classpath.
  * This project uses Simple Logging Facade for Java (SLF4J), and is built against [SLF4J](http://www.slf4j.org/), though you are welcome to provide any other implementation.

Next step is to choose one of the available java memcached clients.

## Spymemcached ##

For [spymemcached](http://code.google.com/p/spymemcached/) add dependency:
```
    <dependency>
	<groupId>spy</groupId>
	<artifactId>spymemcached</artifactId>
	<version>2.7.3</version>
    </dependency>
```
Unfortunately spymemcached isn't available in central maven repository, currently it's hosted by:
```
   <repositories>
      ........
     <repository>
       <id>spy</id>
       <name>Spy Repository</name>
       <layout>default</layout>
       <url>http://files.couchbase.com/maven2/</url>
       <snapshots>
         <enabled>false</enabled>
       </snapshots>
     </repository>
   </repositories>
```

## Xmemcached ##

For [xmemcached](http://code.google.com/p/xmemcached/) add dependency:
```
    <dependency>
	<groupId>com.googlecode.xmemcached</groupId>
	<artifactId>xmemcached</artifactId>
        <version>1.3.5</version>
    </dependency>	
```

# Configuration #
You will need to inform your `ApplicationContext` of Simple-Spring-Memcached's configuration. This is done with an `import` configuration directive:
```
	<import resource="simplesm-context.xml" />
```

Simple-Spring-Memcached also requires an annotation to enable AOP access, which in turn requires the AOP namespace being defined:
```
<beans xmlns="http://www.springframework.org/schema/beans" 
    xmlns:aop="http://www.springframework.org/schema/aop"
    xsi:schemaLocation="http://www.springframework.org/schema/aop
              http://www.springframework.org/schema/aop/spring-aop-3.0.xsd">

...

	<aop:aspectj-autoproxy />
```


In order to tell Simple-Spring-Memcached about the specifics of **your** environment, you need to configure default client:
  * spymemcached:
```
     <bean name="defaultMemcachedClient" class="com.google.code.ssm.CacheFactory">
		<property name="cacheClientFactory">
			<bean class="com.google.code.ssm.providers.spymemcached.MemcacheClientFactoryImpl" />
		</property>
		<property name="addressProvider">
			<bean class="com.google.code.ssm.config.DefaultAddressProvider">
				<property name="address" value="127.0.0.1:11211 127.0.0.1:11311" />
			</bean>
		</property>
		<property name="configuration">
			<bean class="com.google.code.ssm.providers.CacheConfiguration">
				<property name="consistentHashing" value="true" />
			</bean>
		</property>
	</bean>
```
  * xmemcached:
```
     <bean name="defaultMemcachedClient" class="com.google.code.ssm.CacheFactory">
		<property name="cacheClientFactory">
			<bean class="com.google.code.ssm.providers.xmemcached.MemcacheClientFactoryImpl" />
		</property>
		<property name="addressProvider">
			<bean class="com.google.code.ssm.config.DefaultAddressProvider">
				<property name="address" value="127.0.0.1:11211 127.0.0.1:11311" />
			</bean>
		</property>
		<property name="configuration">
			<bean class="com.google.code.ssm.providers.CacheConfiguration">
				<property name="consistentHashing" value="true" />
			</bean>
		</property>
	</bean>
```

The `consistentHashing` parameter refers to a [method of choosing to which node a value is written](http://code.google.com/p/memcached/wiki/FAQ#What_is_a_%22consistent_hashing%22_client?), as implemented in the [spymemcached ](http://code.google.com/p/spymemcached/) or [xmemcached](http://code.google.com/p/xmemcached/) client.
The `address` parameter in addressProvider bean is how you tell the Simple-Spring-Memcached library upon which IPs and ports your Memcached server(s) are listening.

# Usage #

Any discussion of Memcached usage requires an understanding of _keys_ and _values_. To get an understanding of this, see this [helpful tutorial](http://www.majordojo.com/2007/03/memcached-howto.php).

### `@CacheKeyMethod` ###
In SSM, you are able to identify 'key objects'. These are the objects that Simple-Spring-Memcached will rely upon to generate a (non-namespaced) unique key for referring to values within the cache. For any given key object, it will first be checked if any of its methods are annotated with `@CacheKeyMethod`. If a `@CacheKeyMethod` is found, and it conforms to the required signature (no-arg, with an output of type `String`), this is the method that will be relied upon to generate a unique key. If there is no conforming `@CacheKeyMethod`, then the basic `Object.toString()` method will be used. <br />
Key can be generated using more than one key object. The (non-namespaced) unique key is a concatenation of single keys generated from each key object and joined using '/'.

## The Big 9 Annotations ##
The next 9 annotations are the real meat of this project. These are the annotations that mark where caching is to be applied. There are two dimensions that these annotations cover: cardinality (are we working with a single value with an assigned key, a single value with a calculated key, or a 'multiplexed' set of values with calculated keys); and usage pattern (read-through, update, or invalidate).

### `@ReadThroughSingleCache`, `@ReadThroughMultiCache`, `@ReadThroughAssignCache` ###
These are the most frequently used annotations. They follow the standard caching flow as follows:
  1. Check if value exists in the cache. If it does, return it and exit cleanly.
  1. If not found in the cache, then ask the underlying method for the data.
  1. Before returning the data, write it out to the cache for future access.
  1. Return the calculated data.

### `@InvalidateSingleCache`, `@InvalidateMultiCache`, `@InvalidateAssignCache` ###
Whereas the `ReadThrough*Cache` annotations are involved with populating the cache when asked for information, the `Invalidate*Cache` annotations effectively take information out of the cache. These annotations are useful when you know that a value has been made stale and you want to force it to refresh on the next time it is referenced in a `ReadThrough*Cache` method.

### `@UpdateSingleCache`, `@UpdateMultiCache`, `@UpdateAssignCache` ###
The `Update*Cache` annotations can serve as a bit of performance improvement. Unlike the `Invalidate*Cache` annotations which remove a value from the cache to be looked up again in the future, the `Update*Cache` annotations force an update to the value in the cache which obviates the need to check the underlying method in a `ReadThrough*Cache` method.

## Counter Annotations ##
Counters can be use to count methods invocation or user's actions. Memcached store counters as 64-bit no-negative integers.

### `@ReadCounterFromCache` ###
Reads counter's value from cache. This annotation follows the standard caching flow as follows:
  1. Check if value exists in the cache and can be cast to number. If it does, return it and exit cleanly.
  1. If not found in the cache, then ask the underlying method for the data.
  1. Before returning the data, write it out to the cache (using `incr` command) for future access.
  1. Return the value (counter).
Annotated method must return one of int, Integer, long or Long.

### `@IncrementCounterInCache`, `@DecrementCounterInCache` ###
Those annotations increment or decrement by 1 counter in cache. In case of incrementing if counter doesn't exist in cache it is initialized with 1. Counter value is always no negative number and can't be decrementing below 0.

### `@UpdateCounterInCache` ###
Similar to `@UpdateSingleCache` but sets new counter's value in cache. The value used to update counter should be of type int, Integer, long or Long.

# Examples #

### `@ReadThroughSingleCache` ###
In this example, we are working with a fairly standard DAO-like pattern:
```
    @ReadThroughSingleCache(namespace = "CplxObj", expiration = 3600)
    public ComplexObject getComplexObjectFromDB(@ParameterValueKeyProvider Long complexObjectPk) {
        ...
        return result;
    }
```
The basic usage is that you've got a primary key (`complexObjectPk`) and you want to get the corresponding `ComplexObject` from the DB. This is kind of an expensive operation, and you'd like to do it less frequently because the data rarely changes. So, you slap on the Simple-Spring-Memcached annotation. You assign a (fairly arbitrary) namespace of "CplxObj". (Namespacing this way helps ensure that you don't have cache collisions with another type of object that may have the same primary key.) You tell Simple-Spring-Memcached that they key object (`@ParameterValueKeyProvider`) is `complexObjectPk` parameter. And lastly, you tell Simple-Spring-Memcached that you'd like for the value to stick around in the cache for at most 1 hour (3600 seconds).

### `@InvalidateMultiCache` ###
Here, we deal with another fairly common DAO-like pattern:
```
    @InvalidateMultiCache(namespace = "CplxObj")
    public String saveModifiedComplexObjectsToDB(Credentials principal, @ParameterValueKeyProvider List<ComplexObject> complexObjects) {
       ...
    }
```
This example show off the multiplexing capabilities of SSM. Let's say you made a common change to several of the `ComplexObject`s, and you want to write them out to the database. This means any previously cache-stored versions of these `ComplexObject`s will be stale (out-of-date). We use this `@InvalidateMultiCache` annotation to remove cache copies of that data, which will hopefully require future calls to an `@ReadThrough*Cache` method to hit the underlying method for a fresh copy of the data.
You'll notice that the complexObjects is annotated with `@ParameterValueKeyProvider`. This is because (in this made-up example) the `ComplexObject`s either have a method with an `@CacheKeyMethod` annotation that outputs the primary key, or an implementation of `toString()` that returns the primary key.

### `@UpdateAssignCache` ###
This use-case is a bit more contrived than the above DAO-ish examples, but it gets the point across:
```
    @ReturnDataUpdateContent
    @UpdateAssignCache(namespace = "CplxObj", assignedKey = "MyFav", expiration = 7200)
    public List<Long> resetMyFavoriteComplexObjectPksFromDB() {
        ...
        return results;
    }
```
This `@Update*Cache` annotations are designed to force an update to the cache. This may be preferable in many cases rather than the invalidate-readthrough pattern, which ends up requiring a later trip back to the underlying method/db.
This example also shows how the `@*AssignCache` annotations work. Instead of relying on input/output values for the key, use an annotation-declared key.
The data that gets cached in this scenario is the output data (`@ReturnDataUpdateContent`). The list of primary keys is stored as a single value under the id "MyFav" within the namespace of "CplxObj".

### Looking for more examples? ###
More examples are in [integration-test](http://code.google.com/p/simple-spring-memcached/source/browse/#svn%2Ftags%2Fsimple-spring-memcached-parent-2.0.0%2Fintegration-test%2Fsrc) project.

# Additional Information #

### Exceptions ###
Great care was taken while writing SSM to preserve the functionality of any surrounding code. If an exception is thrown anywhere in the SSM code, we've gone to great lengths to catch and log (slf4j) as much information as possible from the exception, but we refrain from passing that exception up to the end user.

### Serialization ###
Any of the objects being saved to the cache **should** implement `java.io.Serializable` in the very least, but it is [highly recommended](http://www.onjava.com/pub/a/onjava/excerpt/JavaRMI_10/index.html?page=5) that you implement `java.io.Externalizable` in as many classes of the cache data as is possible. Java serialization is a default mechanism. <br />
Instead of java serialization saved object may be serialized using custom serialization mechanism. You may use json serializer which usual make saved object smaller and human readable, by default amazing [jackson](http://wiki.fasterxml.com/JacksonHome) library is used. To use json serialization for given class `JsonTranscoder` has to be registered.

# Contact Us #
If you have any questions, feel free to ask them on the [Google Group](http://groups.google.com/group/simple-spring-memecached). (**UPDATE**: Sorry, this link was bad up until 02 Aug '09, because I fat-fingered when creating the Google Group. I incorrectly misspelled it as 'simple-spring-memEcached'. So sorry about that!)

Also, let us know if you are using SSM in your project, and we will [list it in on the Wiki](http://code.google.com/p/simple-spring-memcached/wiki/ProjectsUsingSSM).