---
title: "API"
sidebar_position: 5
---

This page is for developers who want to depend on Actions from their own plugin, e.g. to read registered actions or check their conditions in code. Add the repository and dependency below, then build against the API.

## Setup

1. Add the Auxilor repository and the Actions dependency to your `build.gradle.kts`:

   ```kts
   repositories {
       maven("https://repo.auxilor.io/repository/maven-public/")
   }

   dependencies {
       compileOnly("com.willfp:Actions:<version>") // Replace <version> with the latest tag
   }
   ```

2. Replace `<version>` with the latest release, found on the [tags page](https://github.com/Auxilor/Actions/tags).

The full source code is available [on GitHub](https://github.com/Auxilor/Actions).

<hr/>

## Where to go next

- **See the data model:** browse [the source](https://github.com/Auxilor/Actions).
- **Build actions as a user:** [How to Make a Custom Action](how-to-make-a-custom-action).