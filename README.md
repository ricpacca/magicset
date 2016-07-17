## MagicSet

This library contains the implementation of a collection that I called MagicSet.

A MagicSet can be used whenever there is the need to retrieve an item from a Set.
It has all the advantages of a Set, but gives some additional methods like getFromId(), removeFromId(), etc.

This can be useful when we want to retrieve the original instance of an object from a Set (which is a 
[popular issue](https://stackoverflow.com/questions/7283338/getting-an-element-from-a-set)).

For example, imagine a client-server videogame where multiple clients have a smaller copy of a same model
(stored in the Server). Imagine that in this model there are collections of objects like cards,
cities, rewards, etc. 

There are multiple ways to handle the situation when a client needs to modify this model.
 
I found helpful to design a protocol that uses ids (more specifically, Java UUIDs) to indicate the objects that a player
wants to use/modify in his actions, and this is why I wrote this library. 

A MagicSet can contain items of any class extending UniqueItem. This class gives each instance of a child classes
a random UUID (or a specific one can be passed too).

### Installation

### Usage example

NB: Requires Java 8.

```Java
public class Card extends UniqueItem {
    private Color color;
    
    public Card(Color color) {
        this.color = color;
    }
}

public class Deck {
    private MagicSet<Card> cards;
    
    public Deck() {
        cards = new MagicHashSet<>();
    }
    
    public Deck(Collection<Card> cards) {
        cards = new MagicHashSet<>(cards);
    }
    
    public Card getCard(UUID cardId) {
        return cards.getFromId(cardId);
    }
    
    public Card popCard(UUID cardId) {
        return cards.popFromId(cardId);
    }
    
    public Card getCards(Collection<UUID> cardIds) {
        return cards.popFromIds(cardIds);
    }
    
    // Other methods are possible too
}
```

### Details

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