---
title: "Actions"
---

Actions lets you add and tweak server behaviour without writing a plugin. You build small, self-contained scripts in YAML, each one a single **action** file, and the plugin runs them when the trigger you choose fires.

## What is Actions?

Actions is a no-code automation plugin built on [libreforge](https://plugins.auxilor.io/effects/configuring-an-effect). Each action is a YAML file that pairs a set of **effects** (what happens) with **conditions** and **triggers** (when it happens). Because effects and triggers are shared across the whole Auxilor ecosystem, anything those plugins can do, an action can do.

## What sets Actions apart?

- **No code required.** If you can edit a YAML file, you can build an action.
- **Small to large.** Lose coins on death, make a netherite hoe autosell crops, run random server events, or stand up an entire custom currency that other plugins read.
- **Reuses libreforge.** The same effects, conditions, filters, mutators, and triggers you already know from other Auxilor plugins work here unchanged.

## Where to go next

- **Build your first action:** [How to make Actions](how-to-make-a-custom-action) walks you from an empty file to a working result.
- **Read conditions from other plugins:** [PlaceholderAPI](placeholderapi) exposes whether an action's conditions are met.
- **Commands and permissions:** [Commands and Permissions](commands-and-permissions) covers reloading and sharing configs.