# Strategy game in java with GUI

design patterns used:
- singleton
- command
- manager
- observer
- factory
- DDD

tested on ubuntu wayland with docker only. we need to build the container before running it so it would work using `build.sh`.

execute the following command on host before running the container:

```sh
socat TCP-LISTEN:6000,reuseaddr,fork UNIX-CLIENT:/tmp/.X11-unix/X0
```

then run using `run.sh`