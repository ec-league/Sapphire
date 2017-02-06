
#!/bin/bash

# prepare database for test

mysql -e 'CREATE DATABASE IF NOT EXISTS sapphire;'
mysql -e 'USE sapphire;'

chmod -R 777 ~/input.sql

mysql -e 'source ~/input.sql'