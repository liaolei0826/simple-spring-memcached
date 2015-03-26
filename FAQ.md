# How do I use multiple/secondary keys for a cached value? #
If you need to store an individual object (`User`) by several different keys (`primaryKey`, `userName`, and `email`), Simple-Spring-Memcached can still help you, thought not via a natively built-in pattern.
This projects' recommended pattern is to decide on one key to be the default key for the 'canonical' reference, and then the 'secondary' keys can be mapped to the default key.
Here's an example:
```
    @ReadThroughSingleCache(namespace = "ComplexObject", expiration = 300)
    public ComplexObject getObjectByPrimaryKey(@ParameterValueKeyProvider Long primaryKey) {...}
    
    @ReadThroughSingleCache(namespace = "ComplexObjectByEmail", expiration = 300)
    public Long getPrimaryKeyByEmail(@ParameterValueKeyProvider String email) {...}
```
A feature request has been added to consider providing multi-key or primary-and-secondary support natively, but for the time being, this pattern can effectively get you to where you need.
(Thanks to Ben Manes, of the [concurrentlinkedhashmap](http://code.google.com/p/concurrentlinkedhashmap/) project for challenging me with this question.)

# Why use `toString()` rather than `hashCode()` for generating keys? #
(This question was asked on the feedback page from my presentation at SVCC.)
_To Be Added._

# How to use SSM without having to use annotations? #
If for some reasons direct access to cache is required it can be achieved by injecting Cache object:
```
  @Autowired
  // it's required when more than one cache is defined
  //@Qualifier("defaultMemcachedClient") 
  com.google.code.ssm.Cache cache;
```
