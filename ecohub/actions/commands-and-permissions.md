---
title: "Commands and Permissions"
sidebar_position: 3
---

Actions ships a single base command, `/actions`, with subcommands for reloading your configs and for sharing them through [lrcdb](https://lrcdb.auxilor.io/). Every subcommand has its own permission, listed below. All permissions default to operators.

| Command | Description | Permission |
| --- | --- | --- |
| `/actions reload` | Reload the plugin and all action configs. | `actions.command.reload` |
| `/actions import <id>` | Import an action config from [lrcdb](https://lrcdb.auxilor.io/). | `actions.command.import` |
| `/actions export <id>` | Export an action config to [lrcdb](https://lrcdb.auxilor.io/). | `actions.command.export` |

:::tip Reload after every edit
Changes to `config.yml` or any file in `/actions/` only take effect once you run `/actions reload`. The confirmation message reports the reload time and how many actions loaded.
:::

<hr/>

## Where to go next

- **Build an action to reload:** [How to Make a Custom Action](how-to-make-a-custom-action).
- **Plugin settings:** [Plugin Config](plugin-config).
- **Share configs:** browse community actions on [lrcdb](https://lrcdb.auxilor.io/).