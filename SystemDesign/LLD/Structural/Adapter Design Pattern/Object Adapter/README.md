# Object Adapter Pattern

## What is it?

The Object Adapter pattern allows incompatible interfaces to work together by using composition. The adapter holds a reference to the adaptee and implements the target interface, delegating calls to the adaptee.

## Real-World Analogy

A universal remote (adapter) can control different devices (adaptees) by holding a reference to each and translating your commands.

## Key Points

- Uses composition (has an adaptee instance)
- More flexible than class adapter (can adapt multiple adaptees)

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

// Object Adapter
class VlcObjectAdapter implements MediaPlayer {
    private VlcPlayer vlcPlayer;
    public VlcObjectAdapter(VlcPlayer vlcPlayer) {
        this.vlcPlayer = vlcPlayer;
    }
    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("vlc")) {
            vlcPlayer.playVlc(fileName);
        } else {
            System.out.println("Invalid media. VLC only.");
        }
    }
}

// Usage
public class Main {
    public static void main(String[] args) {
        MediaPlayer player = new VlcObjectAdapter(new VlcPlayer());
        player.play("vlc", "movie.vlc");
    }
}
```
