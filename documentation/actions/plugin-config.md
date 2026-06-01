---
title: "Plugin Config"
sidebar_position: 4
---

The plugin's global settings live in `config.yml`, in the plugin's folder (`/plugins/Actions/config.yml`). Edit it, then run `/actions reload` to apply your changes; you do not need to restart the server.

:::info Per-action config lives elsewhere
`config.yml` holds plugin-wide settings only. Each action is its own file in the `/actions/` folder. To build an action, see [How to Make a Custom Action](how-to-make-a-custom-action).
:::

## Default config.yml

The bundled `config.yml` ships empty: Actions works out of the box with no global configuration required.

```yaml
#
# Actions
# by Auxilor
#
```

<hr/>

## Where to go next

- **Build an action:** [How to Make a Custom Action](how-to-make-a-custom-action).
- **Reload after editing:** [Commands and Permissions](commands-and-permissions).