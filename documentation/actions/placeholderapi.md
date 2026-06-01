---
title: "PlaceholderAPI"
sidebar_position: 2
---

Actions exposes a PlaceholderAPI placeholder for every action you create, so other plugins can read whether an action's conditions are currently met. This lets you reuse an action's conditions anywhere a placeholder is accepted, e.g. EcoShop.

| Placeholder | Description |
| --- | --- |
| `%actions_<id>_is_met%` | Returns `1` if the action's conditions are met for the player, or `0` if not. Replace `<id>` with the action's file name. |

For example, an action file named `vip_zone.yml` is read with `%actions_vip_zone_is_met%`. The placeholder evaluates the `conditions` section of that action against the player; the `effects` section is not involved.

<hr/>

## Where to go next

- **Set conditions on an action:** [How to Make a Custom Action](how-to-make-a-custom-action).
- **Condition options:** [Configuring a Condition](https://plugins.auxilor.io/effects/configuring-a-condition).