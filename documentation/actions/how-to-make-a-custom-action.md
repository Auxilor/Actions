---
title: How to make Actions
sidebar_position: 1
---



## Creating a Custom Action

Each action has its own config file, placed in the `/actions/` folder, and you can add or remove them as you please. There's an example config called `_example.yml` to help you out!

The ID of the Action is the file name. This is what you use in [placeholders](https://plugins.auxilor.io/actions/placeholderapi).
ID's must be lowercase letters, numbers, and underscores only.

## Example Action Config

```yaml
# If the action should be enabled
enabled: true

# Read https://plugins.auxilor.io/effects/configuring-an-effect
# The effects for the action
effects:
  - id: give_money
    args:
      amount: "0.25 * %victim_max_health%"
    triggers:
      - kill
    filters:
      not_entities:
        - player

# Read https://plugins.auxilor.io/effects/configuring-a-condition
# The conditions for the action to work
conditions: [ ]
```
The example effect: Pay players for killing non-player entities at a rate of $0.25 per health point.

## Understanding all the sections

### The Effects Section
:::dangerEffects Section

The effects section is the core functionality of the action. You can configure effects, conditions, filters, mutators and triggers in this section to run when the action is triggered.

Check out [Configuring an Effect](https://plugins.auxilor.io/effects/configuring-an-effect) to understand how to configure this section correctly.

For more advanced users or setups, you can configure chains in this section to string together different effects under one trigger. Check out [Configuring an Effect Chain](https://plugins.auxilor.io/effects/configuring-a-chain) for more info.

:::

<hr/>

## Default Configs

The default configs can be found [here](https://github.com/Auxilor/Actions/blob/master/eco-core/core-plugin/src/main/resources/actions/). <br/>
You can find additional user-created configs on [lrcdb](https://lrcdb.auxilor.io/).