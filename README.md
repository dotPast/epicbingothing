# EpicBingoThing

Yet Another Bingo Plugin.

(currently w.i.p.)

## Tasks 
### for v0.1
- [x] Adding cards to player's container
- [x] Item tasks
- [x] Advancement tasks
- [ ] Oddball tasks
- [ ] Game loop
- [ ] Bingo detection

### for v0.1+
- [ ] Custom tasks
- [ ] More game modes
- [ ] Config screen for predefining cards
- [ ] Cards displayed in maps
- [ ] and more

## Build Dependencies
[Origami](https://github.com/AsoDesu/Origami) is not available through Maven repositories or JitPack, so a local `.jar` build is used.  
Build the `engine` dependency and move the build artifact to `libs/origami.engine.jar`. If your build fails, make sure you are not using Java 8.

Rest of the required libraries are using Maven repositories or JitPack.