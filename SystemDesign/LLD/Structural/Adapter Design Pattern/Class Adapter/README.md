# Class Adapter Pattern

## What is it?

The Class Adapter pattern allows incompatible interfaces to work together by using inheritance. The adapter inherits from both the target interface and the adaptee class, adapting the adaptee's interface to the target's.

## Real-World Analogy

Think of a travel plug adapter that inherits the shape of the wall socket (target) and the plug (adaptee) so it can fit both.

## Key Points

- Uses inheritance (extends adaptee, implements target interface)
- Only works in languages that support multiple inheritance (Java uses interfaces)

## Java Example

Suppose you have an `AudioPlayer` that can play MP3 files, but you want it to play VLC files using a `VlcPlayer` class.

```java
// Target interface
interface MediaPlayer {
    void play(String audioType, String fileName);
}

// Adaptee class
class VlcPlayer {
    public void playVlc(String fileName) {
        System.out.println("Playing vlc file: " + fileName);
    }
}

// Class Adapter
class VlcClassAdapter extends VlcPlayer implements MediaPlayer {
    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("vlc")) {
            playVlc(fileName);
        } else {
            System.out.println("Invalid media. VLC only.");
        }
    }
}

// Usage
public class Main {
    public static void main(String[] args) {
        MediaPlayer player = new VlcClassAdapter();
        player.play("vlc", "movie.vlc");
    }
}
```
