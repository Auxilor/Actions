---
title: "API"
sidebar_position: 5
---

Actions is fully open-source, and you can build against it from your own plugin, e.g. to read registered actions or check their conditions in code. This page shows how to add it as a dependency.

## Source code

The full source is on [GitHub](https://github.com/Auxilor/Actions).

## Adding the dependency

1. Add the Auxilor repository and the Actions dependency to your `build.gradle.kts`:

   ```kotlin
   repositories {
       maven("https://repo.auxilor.io/repository/maven-public/")
   }

   dependencies {
       compileOnly("com.willfp:Actions:<version>")
   }
   ```

The latest version available on the repo can be found [here](https://github.com/Auxilor/Actions/tags)

<hr/>

## Where to go next

- **The framework:** Actions is built on [eco](https://github.com/Auxilor/eco), where most shared APIs live.
- **Build actions as a user:** [How to Make a Custom Action](how-to-make-a-custom-action).