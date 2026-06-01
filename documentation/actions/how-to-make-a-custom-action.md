---
title: "How to Make a Custom Action"
sidebar_position: 1
---

An action is a single YAML file that runs a set of **effects** when a **trigger** fires, optionally gated by **conditions**. This page takes you from an empty file to a working action and explains each section you can configure.

## Quick start

1. Open the `/actions/` folder in your plugin directory. Each `.yml` file here is one action.
2. Copy `_example.yml` to a new file, e.g. `coins_on_kill.yml`. The file name becomes the action's ID.
3. Edit the `effects` and `conditions` sections to do what you want (see below).
4. Run `/actions reload` in-game or from console.
5. Trigger the action in-game. The chat message confirms the reload: `Reloaded in Xms! Loaded N actions.`

:::tip
`_example.yml` is included as a reference and is **never loaded**, so copy or rename it to make a real action. You can also organise actions into subfolders inside `actions/`, and they'll still load.
:::

## Naming and IDs

The **file name (without `.yml`) is the action's ID**. So `coins_on_kill.yml` has the ID `coins_on_kill`.

That ID is what you use in commands and in the [`%actions_<id>_is_met%` placeholder](placeholderapi).

:::warning ID rules
IDs may only contain **lowercase letters, numbers, and underscores** (`a-z`, `0-9`, `_`). No spaces, capitals, or hyphens, or the action will not load. The free version of Actions loads at most 5 actions.
:::

## The structure of an action

An action file has three top-level parts.

| Part | What it controls |
| --- | --- |
| **enabled** | Whether the action loads at all. |
| **effects** | What the action does, when it triggers, and what it filters on. |
| **conditions** | Requirements the player must meet for the effects to run. |

Here is the full `_example.yml`, which pays players for killing non-player entities at a rate of $0.25 per victim level:

```yaml
# === enabled: load this action or skip it ===
enabled: true # Set to false to disable without deleting the file

# === effects: what happens, and when ===
effects:
  - id: give_money # The effect to run; see the libreforge effects list
    args:
      amount: "0.25 * %victim_level%" # Effect arguments; placeholders are evaluated as expressions
    triggers:
      - kill # The event that fires this effect
    filters:
      not_entities:
        - player # Only run for non-player victims

# === conditions: requirements to run at all ===
conditions: [ ] # Empty means no conditions; the action always runs when triggered
```

### Enabled

Whether the action loads at all. Set it to `false` to switch an action off without deleting the file.

```yaml
enabled: true # Set to false to disable without deleting the file
```

### Effects

The effects section is the core of the action: it defines what runs, what triggers it, and what it filters or mutates.

```yaml
effects:
  - id: give_money # Effect ID from the libreforge effects list
    args:
      amount: "0.25 * %victim_level%" # Arguments for that effect
    triggers:
      - kill # One or more triggers that fire the effect
    filters:
      not_entities:
        - player # Filters that narrow when the effect runs
```

:::danger Effects are their own system
Effects, conditions, filters, mutators, triggers, and chains are a shared libreforge system, not specific to Actions, with hundreds of options. They are **not** documented here, so see the dedicated guides:

- [Configuring an Effect](https://plugins.auxilor.io/effects/configuring-an-effect) is the full effect, trigger, and condition reference.
- [Configuring an Effect Chain](https://plugins.auxilor.io/effects/configuring-a-chain) strings multiple effects under one trigger for advanced actions.
:::

### Conditions

Conditions are requirements the player must meet for the action to run. An empty list means it always runs when triggered.

```yaml
conditions:
  - id: above_health # Condition ID from the libreforge conditions list
    args:
      health: 10 # This action only runs while the player is above 10 health
```

Conditions are also what the [`%actions_<id>_is_met%` placeholder](placeholderapi) reads, so you can reuse an action's conditions in other plugins like EcoShop. See [Configuring a Condition](https://plugins.auxilor.io/effects/configuring-a-condition) for the full option set.

:::tip Troubleshooting
- **Action not running?** Check `enabled: true`, then confirm the trigger matches the event you expect. Run `/actions reload` after every edit.
- **Only some actions load?** The free version caps you at 5 actions. The console logs a warning when the limit is hit; upgrade to the full version to remove it.
- **Reload says fewer actions than you have files?** A file failed to parse. Check the console for the YAML error and confirm the ID is lowercase letters, numbers, and underscores only.
:::

<hr/>

## Where to go next

- **Default configs:** browse the bundled examples [on GitHub](https://github.com/Auxilor/Actions/blob/master/eco-core/core-plugin/src/main/resources/actions/).
- **Community configs:** find and share user-made actions on [lrcdb](https://lrcdb.auxilor.io/).
- **Reuse conditions elsewhere:** see [PlaceholderAPI](placeholderapi).
- **Reload and share:** see [Commands and Permissions](commands-and-permissions).