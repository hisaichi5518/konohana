# Konohana

SharedPreferences + RxJava2 + SharedPreferences manager = Konohana

# Synopsis

First, Define store interface annotated with `@Store` and `@Key`.

```java
package com.github.hisaichi5518.konohana.example.store;

import com.github.hisaichi5518.konohana.annotation.Key;
import com.github.hisaichi5518.konohana.annotation.Store;

@Store
interface User {
    @Key
    String name = "default name";
}
```

Second, Run the `Build > Rebuild Project` to generate Konohana classes, and instantiate a `Konohana` class, which is generated by konohana processor.

```java
Konohana konohana = new Konohana(context);

// Create `name` preference.
konohana.storeOfUser().setName("hisaichi5518");

// Read `name` preference.
konohana.storeOfUser().getName();

// Update `name` preference.
konohana.storeOfUser().setName("new-hisaichi5518");

// Delete `name` preference.
konohana.storeOfUser().removeName();

// preferences has `name` ?
konohana.storeOfUser().hasName();

// Observe changes to `name` preference.
konohana.storeOfUser().nameAsObservable().subscribe(name -> {
  // ...
});
```

# Support types

- `boolean`
- `String`
- `float`
- `int`
- `long`
- `String`
- `Set<String>`
- `Enum`
- `List<*>`
- `Set<*>`
- `POJO`

# Installation

```
compile "com.github.hisaichi5518.konohana:konohana:0.1.1"
compile "com.github.hisaichi5518.konohana:konohana-processor:0.1.1"
```

# Migration

```
@Store(name = "your-xml-name", klass = "YourKonohana")
interface User {
    @Key(name = "your-key-name")
    String yourKey;
}
```
