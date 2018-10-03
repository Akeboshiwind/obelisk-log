# obelisk-log

Logs interesting data from an obelisk to a MySQL database

## Installation

Todo: Look into github releases.
Todo: Change Dockerfile to use a multistage build.

## Docker

A docker image is provided at `akeboshiwind/obelisk-log`.
An example `docker-compose.yml` is included in the root of the repository.

## Usage

Expects the environment variables defined below.

    $ java -jar obelisk-log-0.1.0-standalone.jar

## Environment variables

Variable | Description | Default
-------- | ----------- | -------
DB_HOST | The host of the database | localhost
DB_PORT | The port of the database | 3306
DB_NAME | The MySQL database name | obelisk-logs
DB_USER | The username for the database | admin
DB_PASSWORD | The password for the database (recommended to change) | admin
REFRESH_RATE | The time between polls of the dashboard (in seconds) | 5
SERVER_ADDRESS | The address for the webpanel of the obelisk | localhost
AUTH_USER | The username for the basic auth login for the panel (leave blank if not using) | db
AUTH_PASSWORD | The password for the basic auth login for the panel (leave blank if not using) | password
PANEL_USER | The username for the panel | admin
PANEL_PASSWORD | The password for the panel | admin

## License

Copyright © 2018 Oliver Marshall

Distributed under the MIT License
