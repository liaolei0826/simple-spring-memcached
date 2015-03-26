

# Disabling cache #

To disable cache define system property: ` ssm.cache.disable=true`.

# Cache zone #

Cache zone is a group of memcached servers (instances) supported by one of the available providers. Using cache zones data can be split across different groups of servers. It's useful when depending on type cached data should be separated and stored on dedicated servers, so one type of data doesn't influence (evict) another.
  * Cache zones can use different providers and settings
  * Using aliases cache zone can be visible under many names, so at the beginning data are stored on one group of servers but in the future it easy to move data associated to alias to another cache zone without changing code.
  * By default data are stored on default cache zone. To store data on specific cache zone mark class or method with [@CacheName](https://simple-spring-memcached.googlecode.com/svn/docs/3.1.0/apidocs/com/google/code/ssm/api/CacheName.html) and provide name or alias of cache zone. The name of default cache zone is 'default'.
  * Cache zones shouldn't overlap (use the same memcached servers).
Keep in mind that all methods/classes that operate on given type of cached data should use the same cache zone, otherwise cache may be inconsistent or useless.

Sample cache zones definition:
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:aop="http://www.springframework.org/schema/aop"
	xsi:schemaLocation="
	       http://www.springframework.org/schema/beans
               http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
	       http://www.springframework.org/schema/aop 
               http://www.springframework.org/schema/aop/spring-aop-3.0.xsd">

	<aop:aspectj-autoproxy />
	<bean name="memcachedClient1" class="com.google.code.ssm.CacheFactory">
		<property name="cacheClientFactory">
			<bean class="com.google.code.ssm.providers.xmemcached.MemcacheClientFactoryImpl" />
		</property>
		<property name="addressProvider">
			<bean class="com.google.code.ssm.config.DefaultAddressProvider">
				<property name="address" value="127.0.0.1:11211,127.0.0.1:11212" />
			</bean>
		</property>
		<property name="configuration">
			<bean class="com.google.code.ssm.providers.CacheConfiguration">
				<property name="consistentHashing" value="true" />
			</bean>
		</property>
		<property name="cacheName" value="cache1" />
		<property name="cacheAliases">
			<list>
				<value>cache1Alias</value>
			</list>
		</property>
	</bean>

        <bean name="memcachedClient2" class="com.google.code.ssm.CacheFactory">
		<property name="cacheClientFactory">
			<bean class="com.google.code.ssm.providers.spymemcached.MemcacheClientFactoryImpl" />
		</property>
		<property name="addressProvider">
			<bean class="com.google.code.ssm.config.DefaultAddressProvider">
				<property name="address" value="127.0.0.1:11213,127.0.0.1:11214,127.0.0.1:11215" />
			</bean>
		</property>
		<property name="cacheName" value="cache2" />
		<property name="cacheAliases">
			<list>
				<value>cache2Alias</value>
                                <value>anotherAlias</value>
			</list>
		</property>
	</bean>
</beans>
```

# Serialization #

By default all objects stored in cache are serialized/deserialized using standard java serialization, so **should** implement   `java.io.Serializable` in the very least, but it is [highly recommended](http://www.onjava.com/pub/a/onjava/excerpt/JavaRMI_10/index.html?page=5) that you implement `java.io.Externalizable` in as many classes of the cache data as is possible.

Saved object may be serialized to json which make it smaller and human readable. In this case [jackson](http://wiki.fasterxml.com/JacksonHome) library is used. To enable json serialization annotate method with [@UseJson](https://simple-spring-memcached.googlecode.com/svn/docs/3.1.0/apidocs/com/google/code/ssm/api/format/UseJson.html) and define map of [JsonTranscoders](https://simple-spring-memcached.googlecode.com/svn/docs/3.1.0/apidocs/com/google/code/ssm/transcoders/JsonTranscoder.html) in [CacheFactory](https://simple-spring-memcached.googlecode.com/svn/docs/3.1.0/apidocs/index.html?com/google/code/ssm/CacheFactory.html), one transcoder for each type.

Keep in mind that all methods that operate on given type of cached data should use the same serialization, otherwise cache may be useless.

# Runtime node switching #
Memcached instances used by each cache zone can be change on the fly without redeploying or restarting application. This is available by invoking [changeAddresses](https://simple-spring-memcached.googlecode.com/svn/docs/3.1.0/apidocs/com/google/code/ssm/CacheFactory.html#changeAddresses(java.util.List)) method on cache factory. <br />
One of the way of updating addresses is to define memcacheds' IPs in JNDI and periodically check if they have changed. To do this:
  * use [JndiAddressProvider](https://simple-spring-memcached.googlecode.com/svn/docs/3.1.0/apidocs/com/google/code/ssm/config/JndiAddressProvider.html) as a address provider in [cache factory](https://simple-spring-memcached.googlecode.com/svn/docs/3.1.0/apidocs/com/google/code/ssm/CacheFactory.html#setAddressProvider(com.google.code.ssm.config.AddressProvider))
  * define [JndiChangeNotifier](https://simple-spring-memcached.googlecode.com/svn/docs/3.1.0/apidocs/com/google/code/ssm/util/jndi/JndiChangeNotifier.html) and set it in [cache factory](https://simple-spring-memcached.googlecode.com/svn/docs/3.1.0/apidocs/com/google/code/ssm/CacheFactory.html#setAddressChangeNotifier(com.google.code.ssm.config.AddressChangeNotifier))
  * set up some mechanism to invoke defined JndiChangeNotifier periodically ([quartz](http://quartz-scheduler.org/))

Sample configuration using quartz 1.8.5 and spring context support
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xmlns:aop="http://www.springframework.org/schema/aop"
        xsi:schemaLocation="
               http://www.springframework.org/schema/beans
               http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
               http://www.springframework.org/schema/aop 
               http://www.springframework.org/schema/aop/spring-aop-3.0.xsd">

        <aop:aspectj-autoproxy />
        <bean id="jndiChangeNotifier" class="com.google.code.ssm.util.jndi.JndiChangeNotifier">
		<property name="jndiKey" value="memcached/ips"/>
	</bean>

	<bean name="memcachedClient" class="com.google.code.ssm.CacheFactory">
		<property name="cacheClientFactory">
			<bean class="com.google.code.ssm.providers.xmemcached.MemcacheClientFactoryImpl" />
		</property>
                <!-- initial memcacheds' IPs, jndiChangeNotifier can be used also as a provider -->
		<property name="addressProvider" ref="jndiChangeNotifier" />
                <property name="addressChangeNotifier" ref="jndiChangeNotifier" />
	</bean>

         <!--
		****************************************************************************
		      check periodically if IPs of memcached servers have changed
		****************************************************************************
	-->
	<bean id="checkMemcachedIPsJob" class="org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean">
		<property name="targetObject" ref="jndiChangeNotifier" />
		<property name="targetMethod" value="check" />
	</bean>

	<bean id="checkMemcachedIPsTrigger" class="org.springframework.scheduling.quartz.CronTriggerBean">
		<property name="jobDetail" ref="checkMemcachedIPsJob" />
		<!-- run every minute -->
		<property name="cronExpression" value="0 0/1 * * * ?" />
	</bean>

	<bean id="scheduler" class="org.springframework.scheduling.quartz.SchedulerFactoryBean">
		<property name="triggers">
			<list>
				<ref bean="checkMemcachedIPsTrigger" />
			</list>
		</property>
		<property name="quartzProperties">
			<props>
				<prop key="org.quartz.threadPool.threadCount">1</prop>
			</props>
		</property>
	</bean>
</beans>
```

# Snapshot build #

Current snapshot (development) build of SSM is available in [SSM CloudBees maven repository](http://repository-ragnor.forge.cloudbees.com/snapshot/com/google/code/simple-spring-memcached/).
```
  <repositories>
     <repository>
        <id>ssm.snaphost</id>
        <name>SSM snapshots</name>
        <url>http://repository-ragnor.forge.cloudbees.com/snapshot/</url>
        <snapshots>
           <enabled>true</enabled>
        </snapshots>
    </repository>
 </repositories>
```