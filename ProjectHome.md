### Project has been moved to Github. ###
### Source code and issue tracking are available on [github SSM page](https://github.com/ragnor/simple-spring-memcached). ###
### Download latest distribution package (3.5.0) from [here](https://github.com/ragnor/simple-spring-memcached/releases/download/3.5.0/simple-spring-memcached-3.5.0-dist.zip). ###

---



# Introduction #

Distributed caching can be a big, hairy, intricate, and complex proposition when using it extensively.

**Simple Spring Memcached** (**SSM**) attempts to simplify implementation for several basic use cases.

**(15-06-2014) New version 3.5.0 with Amazon ElastiCache support is available! Since version 3.0.0 it can work as a cache back-end for Spring Cache (@Cacheable). Please check [release notes](ReleaseNotes.md).**

This project enables caching in Spring-managed beans, by using Java 5 Annotations and Spring/AspectJ AOP on top of the [spymemcached](http://code.google.com/p/spymemcached/) or [xmemcached](http://code.google.com/p/xmemcached/) client. Using Simple Spring Memcached requires only a little bit of configuration and the addition of some specific annotations on the methods whose output or input is being cached.

<table align='left' cellspacing='20'> <tr> <td><wiki:gadget url="https://www.ohloh.net/p/56137/widgets/project_users.xml" height="125" border="0"/></td>  <td> <wiki:gadget url="https://www.ohloh.net/p/56137/widgets/project_partner_badge.xml" height="70" border="0"/> </td>
<td> <a href='https://ragnor.ci.cloudbees.com'><img src='http://web-static-cloudfront.s3.amazonaws.com/images/badges/BuiltOnDEV.png' /></a> </td>
</tr> </table>

# Usage #

If you are using maven, you can try it now:
```
<dependencies>
  <dependency>
    <groupId>com.google.code.simple-spring-memcached</groupId>
    <artifactId>xmemcached-provider</artifactId>
    <version>3.5.0</version>
  </dependency> 
</dependencies>
```

and define connection to memcached on localhost:
```

<beans xmlns="http://www.springframework.org/schema/beans" 
    xmlns:aop="http://www.springframework.org/schema/aop"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
            http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
            http://www.springframework.org/schema/aop
            http://www.springframework.org/schema/aop/spring-aop-3.0.xsd">

    <import resource="simplesm-context.xml" />
    <aop:aspectj-autoproxy />

    <bean name="defaultMemcachedClient" class="com.google.code.ssm.CacheFactory">
          <property name="cacheClientFactory">
                <bean class="com.google.code.ssm.providers.xmemcached.MemcacheClientFactoryImpl" />
          </property>
          <property name="addressProvider">
                <bean class="com.google.code.ssm.config.DefaultAddressProvider">
                     <property name="address" value="127.0.0.1:11211" />
                </bean>
          </property>
          <property name="configuration">
                <bean class="com.google.code.ssm.providers.CacheConfiguration">
                      <property name="consistentHashing" value="true" />
                </bean>
          </property>
     </bean>
</beans>
```

Now you can annotate method to cache result:
```
@ReadThroughSingleCache(namespace = "CplxObj", expiration = 3600)
public ComplexObject getComplexObjectFromDB(@ParameterValueKeyProvider Long complexObjectPk) {
  // ...
  return result;
}
```

If you already using Spring Cache you may use SSM as an another [back-end](Getting_Started#Spring_3.1_Cache_Integration.md).

If you are not using maven you can download current SSM distribution from [github](https://github.com/ragnor/simple-spring-memcached/releases/download/3.5.0/simple-spring-memcached-3.5.0-dist.zip).

Need more? Please read [getting started guide](Getting_Started.md).

# Contact Us #
If you have any questions, feel free to ask them on the [Google Group](http://groups.google.com/group/simple-spring-memecached). (**UPDATE**: Sorry, this link was bad up until 02 Aug '09, because I fat-fingered when creating the Google Group. I incorrectly misspelled it as 'simple-spring-memEcached'. So sorry about that!)

Also, let us know if you are using SSM in your project, and we will [list it in on the Wiki](ProjectsUsingSSM.md).

# Status #
Current trunk status: <a href='https://ragnor.ci.cloudbees.com/job/Simple%20Spring%20Memcached%20(SSM)/'><img src='https://ragnor.ci.cloudbees.com/job/Simple%20Spring%20Memcached%20(SSM)/badge/icon?abc=sth.png'></img></a>

<table align='left' cellspacing='20'> <tr><td> <wiki:gadget url="https://www.ohloh.net/p/56137/widgets/project_basic_stats.xml" height="235" width="360" border="0"/> </td><td valign='middle'> <a href='http://www3.clustrmaps.com/user/f27f046a'><img src='http://www3.clustrmaps.com/stats/maps-no_clusters/code.google.com-p-simple-spring-memcached-thumb.jpg' alt='Locations of visitors to this page' /> </a> </td>
</tr> </table>