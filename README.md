# obelisk-log

Logs interesting data from an obelisk to a MySQL database

## Installation

Todo: Look into releases.
Todo: Put up on Dockerhub.
Todo: Create a sample docker-compose.yml

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
REFRESH_RATE | The time between polls of the dashboard (in seconds) | 1
SERVER_ADDRESS | The address for the webpanel of the obelisk | localhost
AUTH_USER | The username for the basic auth login for the panel | db
AUTH_PASSWORD | The password for the basic auth login for the panel | password
PANEL_USER | The username for the panel | admin
AUTH_PASSWORD | The password for the panel | admin
TOKEN_REFRESH_RATE | The time between refreshed of the authentication token | 20

## License

Copyright © 2018 Oliver Marshall

Distributed under the MIT License
