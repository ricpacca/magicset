# MagicSet

A MagicSet can be used whenever there is the need to retrieve an item from a Set.
It has all the advantages of a Set, but gives some additional methods like getFromId(), removeFromId(), etc.

This can be useful whenever there is the need to retrieve the original instance of an object from a Set (which is a 
[popular issue](https://stackoverflow.com/questions/7283338/getting-an-element-from-a-set)).

## Motivation

Imagine a multiplayer video-game, where a common model (with all the components of the game) is stored in the server,
 and clients can send their actions to modify this model.

There are multiple ways to handle the situation when a client needs to modify the server model. 

Imagine that each client has a small copy of this common model, that it uses to let the player interact with the
 game components.

How can a client indicate to the server what precise components of the game the player wants to modify?

One possible approach to handle the matching between server and client components is to use ids.
 
All the "interactive" components of the game can be marked with a unique id which than can be used by clients to refer 
 univocally to game components when communicating with the server.
 
Given this approach, how can the server retrieve efficiently the original instance of the items indicated by the clients
 upon performing an action?
 
A MagicSet answers this need in an efficient and elegant way, allowing to store unique items, and later manage them 
 given only their ids.

## Usage

```Java
class City extends UniqueItem {
    
    // Somewhere in this class
    
    public void doSomething() {
        // Whatever
    }
}

public class Map {
    private MagicSet<City> cities;
    
    public Map(Collection<Card> cities) {
        cities = new MagicHashSet<>(cities);
    }
    
    public void doSomethingInCity(UUID cityId) {
        City city = cities.getFromId(cityId);
        city.doSomething();
    }
    
    // Other methods can be called on a MagicSet too
}
```


## Installation

MagicSet is available from the Maven Central Repository using the following coordinates:

```xml
    <dependency>
        <groupId>com.github.ricpacca</groupId>
        <artifactId>magicset</artifactId>
        <version>1.0.0</version>
    </dependency>
```
    
You can also download binary release and javadoc from the 
[maven central repository](http://search.maven.org/#search|ga|1|magicset). 
Of course you can always clone the repository and build from source.

NB: The library is written in Java 8.

## Structure

A MagicSet can contain items of any class extending UniqueItem. This class gives each instance of a child classes
a random UUID (or a specific one can be passed too).

Two implementations of a MagicSet are currently provided:

- MagicHashSet: it's similar to a HashSet, but instead of using the items as key and a dummy object as value in the 
backing HashMap, it uses the UUID of the item as key and the item as value. Thus, this implementation
does not need more memory than a normal HashSet.

- LinkedMagicHashSet: this implementation differentiates from a MagicHashSet the same way in which
 a LinkedHashSet differentiates from a HashSet, i.e. any iterator on a LinkedMagicHashset will follow
 the insertion order of the items.
 
Calling equals method on any of these implementations will follow the same behaviour of an AbstractSet, i.e. it 
works properly across different implementations of the Set interface (e.g. checking equality between a LinkedMagicHashset 
and a HashSet will return true if they both have the same elements).

All the methods of the library are documented and easily understandable. Do not hesitate to contact me should you need
 more detailed explainations.

## Contribute

Please feel invited to contribute by creating a pull request to submit the code you would like to be included. 

Sample code and documentation are both very appreciated contributions.


## License

This project is distributed under the terms of the MIT License. 
See file "LICENSE" for further reference.