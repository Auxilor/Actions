---
title: "How to Make a Custom Action"
sidebar_position: 1
---

An action is a single YAML file that runs a set of **effects** when a **trigger** fires, optionally gated by **conditions**. This page takes you from an empty file to a working action and explains each section you can configure.

## Quick start

1. Open the `/actions/` folder in your plugin directory. Each `.yml` file here is one action.
2. Copy `_example.yml` to a new file, e.g. `coins_on_kill.yml`. The file name is the action's **ID**, so use lowercase letters, numbers, and underscores only.
3. Edit the `effects` and `conditions` sections to do what you want (see below).
4. Run `/actions reload` in-game or from console.
5. Trigger the action in-game. The chat message confirms the reload: `Reloaded in Xms! Loaded N actions.`

:::warning Action IDs
The ID is the file name without `.yml`. Stick to lowercase letters, numbers, and underscores, e.g. `coins_on_kill`. The free version of Actions loads at most 5 actions.
:::

## Action structure

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

## Effects

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
Effects, conditions, filters, mutators, and triggers are shared libreforge systems, not unique to Actions. Read [Configuring an Effect](https://plugins.auxilor.io/effects/configuring-an-effect) to configure this section correctly. To string several effects under one trigger, see [Configuring an Effect Chain](https://plugins.auxilor.io/effects/configuring-a-chain).
:::

## Conditions

Conditions are requirements the player must meet for the action to run. An empty list means it always runs when triggered.

```yaml
conditions:
  - id: above_health # Condition ID from the libreforge conditions list
    args:
      health: 10 # This action only runs while the player is above 10 health
```

Conditions are also what the [`%actions_<id>_is_met%` placeholder](placeholderapi) reads, so you can reuse an action's conditions in other plugins like EcoShop. See [Configuring a Condition](https://plugins.auxilor.io/effects/configuring-a-condition) for the full option set.

## Organising your actions

You can place action files anywhere inside `/actions/`, including subfolders, to keep large setups tidy. The folder layout has no effect on behaviour; only the file name (the ID) matters. The file `_example.yml` is never loaded.

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